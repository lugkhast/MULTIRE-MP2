/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author lugkhast
 */
public class ImageFileFilter extends javax.swing.filechooser.FileFilter implements FileFilter {

    /**
     * A really crude way to filter for image files
     */
    String[] acceptedExtensions = {
        "jpg", "jpeg",
        "png"
    };

    @Override
    public boolean accept(File file) {
        // Allow navigation
        if (file.isDirectory()) {
            return true;
        }
        
        for (String acceptedExtension : acceptedExtensions) {
            if (file.getName().endsWith(acceptedExtension)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Image files (*.jpg, *.png)";
    }

}
