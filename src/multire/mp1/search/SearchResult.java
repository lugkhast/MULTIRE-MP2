/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search;

/**
 *
 * @author lugkhast
 */
public class SearchResult {

    private double score;
    private PreprocessedImage preprocessedImage;

    public SearchResult() {
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return the preprocessedImage
     */
    public PreprocessedImage getPreprocessedImage() {
        return preprocessedImage;
    }

    /**
     * @param preprocessedImage the preprocessedImage to set
     */
    public void setPreprocessedImage(PreprocessedImage preprocessedImage) {
        this.preprocessedImage = preprocessedImage;
    }
}
