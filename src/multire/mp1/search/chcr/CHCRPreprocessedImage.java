/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.chcr;

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
public class CHCRPreprocessedImage extends PreprocessedImage{
    
    private void ColorHistogramCenterRefMethod(int [] histogramQCen, int [] histogramQNonCen, int [] histogramTMCen, int [] histogramTMNonCen) {
        int totalPQCen = 0, totalPQNonCen = 0, totalPMCen = 0, totalPMNonCen = 0;
        
        double[] NHACenter = new double[4];
        double[] NHBCenter = new double[4];
        double[] NHANonCenter = new double[4];
        double[] NHBNonCenter = new double[4];
        double[] NHcompareCenter = new double[4];
        double[] NHcompareNonCenter = new double[4];
        double similarityABcenter = 0, similarityABnoncenter = 0; 
        
        //for center area
        for(int i=0; i < 4; i++){ totalPQCen += histogramQCen[i]; }
        for(int j=0; j < 4; j++){ totalPMNonCen += histogramTMNonCen[j];}
        
        for(int i=0; i < 4; i++){
            NHACenter[i] = histogramQCen[i] / totalPQCen;
        }
        for(int i=0; i < 4; i++){
            NHBCenter[i] = histogramTMCen[i] / totalPMCen;
        }
        for(int i=0; i < 4; i++){
            if(NHACenter[i] > NHBCenter[i]){ NHcompareCenter[i] = NHACenter[i]; }
            else { NHcompareCenter[i] = NHBCenter[i]; }
        }
        for(int i=0; i < 4; i++){
            similarityABcenter += 1 - (Math.abs(NHACenter[i] - NHBCenter[i]) / NHcompareCenter[i]);
        }
        
        //for non-center area
        for(int i=0; i < 4; i++){ totalPQNonCen += histogramQNonCen[i]; }
        for(int j=0; j < 4; j++){ totalPMNonCen += histogramTMNonCen[j];}
        
        for(int i=0; i < 4; i++){
            NHANonCenter[i] = histogramQNonCen[i] / totalPQNonCen;
        }
        for(int i=0; i < 4; i++){
            NHBNonCenter[i] = histogramTMNonCen[i] / totalPMNonCen;
        }
        for(int i=0; i < 4; i++){
            if(NHANonCenter[i] > NHBNonCenter[i]){ NHcompareNonCenter[i] = NHANonCenter[i]; }
            else { NHcompareNonCenter[i] = NHBNonCenter[i]; }
        }
        for(int i=0; i < 4; i++){
            similarityABnoncenter += 1 - (Math.abs(NHANonCenter[i] - NHBNonCenter[i]) / NHcompareNonCenter[i]);
        }
        
        similarityABcenter /= 4;
        similarityABnoncenter /= 4;
    }
}
