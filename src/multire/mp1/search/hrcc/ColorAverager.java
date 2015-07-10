/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.hrcc;

import java.awt.Color;

/**
 *
 * @author lugkhast
 */
public class ColorAverager {
    private int rTotal, gTotal, bTotal;
    private int colorCount;

    public ColorAverager() {
        rTotal = 0;
        gTotal = 0;
        bTotal = 0;
        
        colorCount = 0;
    }
    
    public void add(Color color) {
        rTotal += color.getRed();
        gTotal += color.getGreen();
        bTotal += color.getBlue();
        
        colorCount += 1;
    }
    
    public void add(int rgb) {
        this.add(new Color(rgb));
    }
    
    public Color getAverage() {
        int rAvg, gAvg, bAvg;
        
        // Your standard averaging formula
        rAvg = rTotal / colorCount;
        gAvg = gTotal / colorCount;
        bAvg = bTotal / colorCount;
        
        // Pack them into an RGB value
        int rgb = (rAvg << 16) + (gAvg << 8) + bAvg;
        
        // And wrap it in a Color object
        return new Color(rgb);
    }
}
