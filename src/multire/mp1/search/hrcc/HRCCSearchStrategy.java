/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.hrcc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import multire.mp1.cie.cieConvert;
import multire.mp1.search.PreprocessedImage;
import multire.mp1.search.SearchStrategy;

/**
 *
 * @author lugkhast
 */
public class HRCCSearchStrategy implements SearchStrategy {

    public final int BIN_COUNT = 159;
    public final int COHERENCE_THRESHOLD = 6;

    @Override
    public double compareImageFiles(PreprocessedImage queryImage, PreprocessedImage datasetImage) {
        double totalDifference = 0.0;

        // Color Coherence II, in "06 - Image Retrieval.ppt"
        // queryImage is I, datasetImage is I'
        HRCCPreprocessedImage hrccQueryImg = (HRCCPreprocessedImage) queryImage;
        HRCCPreprocessedImage hrccDatasetImg = (HRCCPreprocessedImage) datasetImage;
        int coherentDifference, incoherentDifference;

        List<Integer> queryCoherent, queryIncoherent, dsCoherent, dsIncoherent;
        queryCoherent = hrccQueryImg.getCoherentColors();
        queryIncoherent = hrccQueryImg.getIncoherentColors();
        dsCoherent = hrccDatasetImg.getCoherentColors();
        dsIncoherent = hrccDatasetImg.getIncoherentColors();

        for (int i = 0; i < BIN_COUNT; i++) {
            coherentDifference = Math.abs(
                    queryCoherent.get(i) - dsCoherent.get(i)
            );
            incoherentDifference = Math.abs(
                    queryIncoherent.get(i) - dsIncoherent.get(i)
            );

            totalDifference += coherentDifference + incoherentDifference;
        }

        return totalDifference;
    }

    private int getBinFromRGB(int rgb) {
        int red, green, blue;
        Color color = new Color(rgb);
        cieConvert converter = new cieConvert();

        red = color.getRed();
        green = color.getGreen();
        blue = color.getBlue();

        converter.setValues(red / 255.0, green / 255.0, blue / 255.0);
        return converter.IndexOf();
    }

    private int getBinFromCoord(BufferedImage image, CoherenceCoord coord) {
        int rgb;

        rgb = image.getRGB(coord.x, coord.y);
        return getBinFromRGB(rgb);
    }

    private boolean isBinSameWithParent(CoherenceCoord coord, BufferedImage image) {
        int rgbCurrent = image.getRGB(coord.x, coord.y);
        int rgbParent = image.getRGB(coord.parent.x, coord.parent.y);

        int binParent = getBinFromRGB(rgbParent);
        int binCurrent = getBinFromRGB(rgbCurrent);
        return binParent == binCurrent;
    }

    private int countCoherentFromCoord(BufferedImage image, CoherenceCoord coord, boolean[][] checkedPixels) {
        int totalCoherent = 0;

        // Reject invalid coordinates
        if ((coord.x < 0 || coord.x >= image.getWidth())) {
            return 0;
        }
        if (coord.y < 0 || coord.y >= image.getHeight()) {
            return 0;
        }

        // If this pixel has already been marked as coherent, skip it.
        if (checkedPixels[coord.x][coord.y]) {
            return 0;
        }

        if (coord.parent == null) {
            // We're at the top of the recursion tree

            checkedPixels[coord.x][coord.y] = true;
            List<CoherenceCoord> connectedCoords = coord.getConnectedCoords(coord);
            for (CoherenceCoord connectedCoord : connectedCoords) {
                totalCoherent += countCoherentFromCoord(image, connectedCoord, checkedPixels);
            }
        } else {
            // We're in the middle of recursion

            // if bin of current pixel matches bin of parent
            if (isBinSameWithParent(coord, image)) {
                // Match! Count this as a coherent pixel.
                totalCoherent++;
                checkedPixels[coord.x][coord.y] = true;
                // Now check all unchecked adjacent pixels
                List<CoherenceCoord> connectedCoords = coord.getConnectedCoords(coord);
                for (CoherenceCoord connectedCoord : connectedCoords) {
                    totalCoherent += countCoherentFromCoord(image, connectedCoord, checkedPixels);
                }
            }
        }
        // Return integer indicating number of coherent pixels
        return totalCoherent;
    }

    private Integer[] countColorsByBin(BufferedImage image) {
        Integer[] incoherentCountPerBin = new Integer[BIN_COUNT];
        CoherenceCoord coord = new CoherenceCoord(0, 0, null);
        int bin;
        Arrays.fill(incoherentCountPerBin, 0);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                coord.x = i;
                coord.y = j;

                bin = getBinFromCoord(image, coord);
                incoherentCountPerBin[bin] += 1;
            }
        }

        return incoherentCountPerBin;
    }

    @Override
    public PreprocessedImage preprocessImage(File imageFile) throws IOException {
        boolean[][] checkedPixels;
        int coherentCount, bin;
        Integer[] coherentCountPerBin = new Integer[BIN_COUNT];
        Integer[] incoherentCountPerBin = new Integer[BIN_COUNT];
        Integer[] totalPixelsPerBin;
        HRCCPreprocessedImage preppedImage = new HRCCPreprocessedImage();
        BufferedImage inputImage, blurredImage;
        CoherenceCoord coord;

        inputImage = ImageIO.read(imageFile);
        preppedImage.setImageFile(imageFile);
        preppedImage.setImage(inputImage);
        blurredImage = preppedImage.getBlurredImage();

        Arrays.fill(coherentCountPerBin, 0);

        // The actual algorithm starts here
        // Count coherent pixels
        checkedPixels = new boolean[blurredImage.getWidth()][blurredImage.getHeight()];
        for (int i = 0; i < blurredImage.getWidth(); i++) {
            for (int j = 0; j < blurredImage.getHeight(); j++) {
                // System.out.printf("Starting check at (%d, %d)\n", i, j);
                coord = new CoherenceCoord(i, j, null);
                coherentCount = countCoherentFromCoord(blurredImage, coord, checkedPixels);

                if (coherentCount > COHERENCE_THRESHOLD) {
                    bin = getBinFromCoord(blurredImage, coord);
                    coherentCountPerBin[bin] += coherentCount;
                }
            }
        }

        // Count noncoherent pixels
        totalPixelsPerBin = countColorsByBin(blurredImage);
        for (int i = 0; i < BIN_COUNT; i++) {
            incoherentCountPerBin[i] = totalPixelsPerBin[i] - coherentCountPerBin[i];
        }

        preppedImage.setCoherentColors(Arrays.asList(coherentCountPerBin));
        preppedImage.setIncoherentColors(Arrays.asList(incoherentCountPerBin));

        return preppedImage;
    }

    public static void main(String[] args) {
        File file, similar, different;
        HRCCPreprocessedImage img = null;
        PreprocessedImage prepSimilar = null, prepDifferent = null;
        SearchStrategy strategy;
        System.out.println("Starting...");

        file = new File("/home/lugkhast/Desktop/MULTIRE/calmdown.jpg");
        similar = new File("/home/lugkhast/Desktop/MULTIRE/calmdownblurred.jpg");
        different = new File("/home/lugkhast/Desktop/MULTIRE/moefist.jpg");
        strategy = new HRCCSearchStrategy();
        try {
            System.out.println("Preprocessing...");
            img = (HRCCPreprocessedImage) strategy.preprocessImage(file);

            System.out.println("Preprocessing comparison images...");
            prepSimilar = strategy.preprocessImage(similar);
            prepDifferent = strategy.preprocessImage(different);
        } catch (IOException ex) {
            Logger.getLogger(HRCCSearchStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (img != null) {
            List<Integer> coherentBins = img.getCoherentColors();
            List<Integer> incoherentBins = img.getIncoherentColors();
            for (int i = 0; i < coherentBins.size(); i++) {
                System.out.printf(
                        "Bin %d: %d | %d\n",
                        i + 1,
                        coherentBins.get(i),
                        incoherentBins.get(i)
                );
            }
        }

        double diff1 = strategy.compareImageFiles(img, prepSimilar);
        double diff2 = strategy.compareImageFiles(img, prepDifferent);

        System.out.printf("Different with caldownblurred.jpg: %f\n", diff1);
        System.out.printf("Different with moefist.jpg: %f\n", diff2);

        System.out.println("Done!");
    }

    @Override
    public String toString() {
        return "Histogram Refinement - Color Coherence";
    }
}
