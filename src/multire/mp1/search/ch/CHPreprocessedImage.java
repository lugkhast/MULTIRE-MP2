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
import multire.mp1.provided.cieConvert;
import java.awt.image.ColorModel;

/**
 *
 * @author Earl
 */
public class CHPreprocessedImage extends PreprocessedImage{
    final static int[] chLUV = new int[4];
    
    private static void createCH(int index){
        if(index >= 0 && index <= 39){ chLUV[0]++; }
        else if(index >= 40 && index <= 79){ chLUV[1]++; }
        else if(index >= 80 && index <= 119){ chLUV[2]++; }
        else if(index >= 120 && index <= 158){ chLUV[3]++; }
    }
    
    private static void convertRGBtoLUV(BufferedImage image){
        ColorModel CM;
        double answer=0, RGB;
        double imageHeight, imageWidth;
        double totalPixels;
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
    
    public static void main(String[] args) {
        System.out.println("Starting!");
        File imagefile = new File("/home/lugkhast/Desktop/calmdown.jpg");
        BufferedImage srcImg = null;

        try {
            srcImg = ImageIO.read(imagefile);
            
        } catch (IOException ex) {
            Logger.getLogger(CHPreprocessedImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        convertRGBtoLUV(srcImg);
        
        System.out.println("Done!");
    }
}
