/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search;

import java.io.File;

/**
 *
 * @author lugkhast
 */
public interface SearchStrategy {

    double compareImageFiles(PreprocessedImage queryImage, PreprocessedImage datasetImage);

    PreprocessedImage preprocessImage(File imageFile);
}
