/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import multire.mp1.search.hrcc.HRCCSearchStrategy;

/**
 *
 * @author lugkhast
 */
public class ImageSearchEngine {

    private SearchStrategy searchStrategy = null;
    private File directoryToSearch = null;
    private List<PreprocessedImage> preprocessedImages = null;

    public ImageSearchEngine() {
    }

    private class ImageFileFilter implements FileFilter {

        /**
         * A really crude way to filter for image files
         */
        String[] acceptedExtensions = {
            "jpg", "jpeg",
            "png"
        };

        @Override
        public boolean accept(File file) {
            for (String acceptedExtension : acceptedExtensions) {
                if (file.getName().endsWith(acceptedExtension)) {
                    return true;
                }
            }

            return false;
        }

    }

    public List<SearchResult> runImageSearch(File queryImageFile) {
        if (searchStrategy == null || directoryToSearch == null) {
            return null;
        }

        List<SearchResult> results = new ArrayList<>();

        return null;
    }

    /**
     * @return the searchStrategy
     */
    public SearchStrategy getSearchStrategy() {
        return searchStrategy;
    }

    /**
     * @param searchStrategy the searchStrategy to set
     */
    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    /**
     * @return the directoryToSearch
     */
    public File getDirectoryToSearch() {
        return directoryToSearch;
    }

    /**
     * @param directoryToSearch the directoryToSearch to set
     */
    public void setDirectoryToSearch(File directoryToSearch) throws IOException {
        this.directoryToSearch = directoryToSearch;

        File[] imageFiles = directoryToSearch.listFiles(new ImageFileFilter());
        PreprocessedImage prepImage;
        preprocessedImages = new ArrayList<>();

        int prepCount = 0;
        for (File imageFile : imageFiles) {
            prepImage = searchStrategy.preprocessImage(imageFile);
            preprocessedImages.add(prepImage);

            System.out.printf(
                    "Preprocessed %d/%d\n",
                    ++prepCount, imageFiles.length
            );
        }
    }

    public static void main(String[] args) {
        File directory = new File("/home/lugkhast/Desktop/MULTIRE/");
        ImageSearchEngine searchEngine = new ImageSearchEngine();
        SearchStrategy strategy = new HRCCSearchStrategy();

        try {
            System.out.println("Starting...");
            searchEngine.setSearchStrategy(strategy);
            searchEngine.setDirectoryToSearch(directory);
        } catch (IOException ex) {
            Logger.getLogger(ImageSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
