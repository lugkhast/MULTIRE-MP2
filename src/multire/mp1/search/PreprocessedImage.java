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
public abstract class PreprocessedImage {
    private File imageFile;

    /**
     * @return the imageFile
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
