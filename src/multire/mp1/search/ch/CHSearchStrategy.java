/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.ch;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import multire.mp1.cie.cieConvert;
import multire.mp1.search.PreprocessedImage;
import multire.mp1.search.SearchStrategy;

/**
 *
 * @author lugkhast
 */
public class CHSearchStrategy implements SearchStrategy {

    public final int BIN_COUNT = 159;

    @Override
    public double compareImageFiles(PreprocessedImage queryImage, PreprocessedImage datasetImage) {
        int totalDifference = 0;
        List<Integer> queryBins, dsBins;
        CHPreprocessedImage chQueryImg = (CHPreprocessedImage) queryImage;
        CHPreprocessedImage chDatasetImg = (CHPreprocessedImage) datasetImage;
        
        queryBins = chQueryImg.getHistogramBins();
        dsBins = chDatasetImg.getHistogramBins();
        for (int i = 0; i < BIN_COUNT; i++) {
            totalDifference += Math.abs(
                    queryBins.get(i) - dsBins.get(i)
            );
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

    @Override
    public PreprocessedImage preprocessImage(File imageFile) throws IOException {
        CHPreprocessedImage preppedImage = new CHPreprocessedImage();
        BufferedImage image = ImageIO.read(imageFile);
        Integer[] colorBins = new Integer[BIN_COUNT];
        int bin, rgb;

        
        Arrays.fill(colorBins, 0);
        preppedImage.setImageFile(imageFile);
        preppedImage.setImage(image);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                rgb = image.getRGB(i, j);
                bin = getBinFromRGB(rgb);
                colorBins[bin] += 1;
            }
        }
        
        preppedImage.setHistogramBins(Arrays.asList(colorBins));

        return preppedImage;
    }
    
    @Override
    public String toString() {
        return "Color Histogram";
    }

}
