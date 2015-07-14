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
        return 0.0;
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

    @Override
    public PreprocessedImage preprocessImage(File imageFile) throws IOException {
        boolean[][] checkedPixels;
        int coherentCount, bin;
        Integer[] coherentCountPerBin = new Integer[BIN_COUNT];
        Integer[] incoherentCountPerBin = new Integer[BIN_COUNT];
        HRCCPreprocessedImage preppedImage = new HRCCPreprocessedImage();
        BufferedImage inputImage, blurredImage;
        CoherenceCoord coord;
        
        inputImage = ImageIO.read(imageFile);
        preppedImage.setImageFile(imageFile);
        preppedImage.setImage(inputImage);
        blurredImage = preppedImage.getBlurredImage();

        Arrays.fill(coherentCountPerBin, 0);
        Arrays.fill(incoherentCountPerBin, 0);

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

        preppedImage.setCoherentColors(Arrays.asList(coherentCountPerBin));
        preppedImage.setIncoherentColors(Arrays.asList(incoherentCountPerBin));

        return preppedImage;
    }

    public static void main(String[] args) {
        File file;
        HRCCPreprocessedImage img = null;
        SearchStrategy strategy;
        System.out.println("Starting...");

        file = new File("/home/lugkhast/Desktop/calmdown.jpg");
        strategy = new HRCCSearchStrategy();
        try {
            System.out.println("Preprocessing...");
            img = (HRCCPreprocessedImage) strategy.preprocessImage(file);
        } catch (IOException ex) {
            Logger.getLogger(HRCCSearchStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (img != null) {
            List<Integer> bins = img.getCoherentColors();
            for (int i = 0; i < bins.size(); i++) {
                System.out.printf("Bin %d: %d\n", i + 1, bins.get(i));
            }
        }

        System.out.println("Done!");
    }
}
