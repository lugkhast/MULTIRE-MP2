/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author lugkhast
 */
public abstract class PreprocessedImage {

    private File imageFile;
    private BufferedImage image;

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

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
