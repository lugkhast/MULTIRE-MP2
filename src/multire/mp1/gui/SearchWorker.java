/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.gui;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import multire.mp1.search.ImageSearchEngine;
import multire.mp1.search.SearchResult;

/**
 *
 * @author lugkhast
 */
public class SearchWorker extends SwingWorker<List<SearchResult>, Void> {
    private ImageSearchEngine searchEngine;
    private File queryFile;
    private ISearchWorker isw;
    
    public static interface ISearchWorker {
        public void onSearchCompleted(List<SearchResult> results);
    }

    public SearchWorker(ImageSearchEngine searchEngine, File queryFile, ISearchWorker isw) {
        this.searchEngine = searchEngine;
        this.queryFile = queryFile;
        this.isw = isw;
    }

    @Override
    protected List<SearchResult> doInBackground() throws Exception {
        return searchEngine.runImageSearch(queryFile);
    }

    @Override
    protected void done() {
        try {
            List<SearchResult> results = get();
            isw.onSearchCompleted(results);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SearchWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
