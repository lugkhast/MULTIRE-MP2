/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import multire.mp1.search.*;

/**
 *
 * @author lugkhast
 */
public class SearchResultCellRenderer implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList jlist, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        SearchResult result = (SearchResult) value;
        PreprocessedImage prepImage = result.getPreprocessedImage();
        ImageIcon icon = new ImageIcon(prepImage.getImage());
        
        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setText(String.format("[%03d]%s", index + 1, result.toString()));
        
        if (isSelected) {
            label.setBackground(Color.BLUE);
        }
        
        return label;
    }
    
}
