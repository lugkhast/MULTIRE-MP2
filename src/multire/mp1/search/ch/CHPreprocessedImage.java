/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.ch;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import multire.mp1.search.PreprocessedImage;
import multire.mp1.cie.cieConvert;
import java.awt.image.ColorModel;

/**
 *
 * @author Earl
 */
public class CHPreprocessedImage extends PreprocessedImage{
    int[] chLUV = new int[4];
    double totalPixels;
    
    private void createCH(int index){
        if(index >= 0 && index <= 39){ chLUV[0]++; }
        else if(index >= 40 && index <= 79){ chLUV[1]++; }
        else if(index >= 80 && index <= 119){ chLUV[2]++; }
        else if(index >= 120 && index <= 158){ chLUV[3]++; }
    }
    
    //compares the histogram of the query image from one of images from the database
    private double ColorHistogramMethod(int [] histogramQuery, int [] histogramToMatch) {
        int totalPixelQuery = 0, totalPixelMatch = 0;
        double[] NHA = new double[4];
        double[] NHB = new double[4];
        double[] NHcompare = new double[4];
        double similarityAB = 0; 
        
        for(int i=0; i < 4; i++){ totalPixelQuery += histogramQuery[i]; }
        for(int j=0; j < 4; j++){ totalPixelMatch += histogramToMatch[j];}
        
        for(int i=0; i < 4; i++){
            NHA[i] = histogramQuery[i] / totalPixelQuery;
        }
        for(int i=0; i < 4; i++){
            NHB[i] = histogramToMatch[i] / totalPixelMatch;
        }
        for(int i=0; i < 4; i++){
            if(NHA[i] > NHB[i]){ NHcompare[i] = NHA[i]; }
            else { NHcompare[i] = NHB[i]; }
        }
        for(int i=0; i < 4; i++){
            similarityAB += 1 - (Math.abs(NHA[i] - NHB[i]) / NHcompare[i]);
        }
        
        return (similarityAB/4);
    }
    
    private void convertRGBtoLUV(BufferedImage image){
        ColorModel CM;
        double answer=0, RGB;
        double imageHeight, imageWidth;
        double R, G, B;
        int index;
        cieConvert ColorCIE = new cieConvert();
        
        imageHeight = image.getHeight();
        imageWidth = image.getWidth();
        totalPixels = imageHeight * imageWidth;
        CM = image.getColorModel();
        
        for(int x = 0; x < imageWidth; x++)
            for(int y = 0; y < imageHeight; y++) {
                RGB = image.getRGB(x,y);
               
                R = CM.getRed(RGB);
                G = CM.getGreen(RGB);
                B = CM.getBlue(RGB);
                
                ColorCIE.setValues(R/255.0, G/255.0, B/255.0);
                index = ColorCIE.IndexOf();
                createCH(index);
            }           
    }
}
