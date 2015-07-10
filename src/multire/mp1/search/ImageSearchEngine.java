/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 *
 * @author lugkhast
 */
public class ImageSearchEngine {

    private SearchStrategy searchStrategy;
    private File directoryToSearch;

    public List<SearchResult> runImageSearch(BufferedImage queryImage) {
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
    public void setDirectoryToSearch(File directoryToSearch) {
        this.directoryToSearch = directoryToSearch;
    }

}
