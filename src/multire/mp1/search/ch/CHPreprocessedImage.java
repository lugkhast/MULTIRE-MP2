/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.ch;

import multire.mp1.search.PreprocessedImage;
import java.util.List;

/**
 *
 * @author Earl
 */
public class CHPreprocessedImage extends PreprocessedImage {
    private List<Integer> histogramBins;

    /**
     * @return the histogramBins
     */
    public List<Integer> getHistogramBins() {
        return histogramBins;
    }

    /**
     * @param histogramBins the histogramBins to set
     */
    public void setHistogramBins(List<Integer> histogramBins) {
        this.histogramBins = histogramBins;
    }
}
