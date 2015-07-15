/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.gui;

import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.JFileChooser;
import multire.mp1.search.*;
import multire.mp1.search.hrcc.HRCCSearchStrategy;

/**
 *
 * @author lugkhast
 */
public class MainWindow extends javax.swing.JFrame {

    private ImageSearchEngine searchEngine;
    private boolean isPreprocessing = false;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        searchEngine = new ImageSearchEngine();
        searchEngine.setSearchStrategy(new HRCCSearchStrategy());
        updateUiComponentStates();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchDirectoryButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchDirectoryLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        selectImageButton = new javax.swing.JButton();
        algoSelectComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        preprocessButton = new javax.swing.JButton();
        preprocessProgressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MULTIRE MP1");
        setMinimumSize(new java.awt.Dimension(640, 480));

        searchDirectoryButton.setText("Set Search Directory");
        searchDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDirectoryButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel1.setText("Search Directory:");

        searchDirectoryLabel.setText("not specified - select a directory");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchDirectoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 474, Short.MAX_VALUE)
                .addComponent(searchDirectoryButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(searchDirectoryButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchDirectoryLabel)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Query"));

        selectImageButton.setText("Select Image");
        selectImageButton.setEnabled(false);

        algoSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(
            new SearchStrategy[] {new HRCCSearchStrategy()})
    );
    algoSelectComboBox.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            algoSelectComboBoxItemStateChanged(evt);
        }
    });

    jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText("Algorithm");

    preprocessButton.setText("Preprocess Images");
    preprocessButton.setEnabled(false);
    preprocessButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            preprocessButtonActionPerformed(evt);
        }
    });

    preprocessProgressBar.setIndeterminate(true);
    preprocessProgressBar.setString("Preprocessing...");
    preprocessProgressBar.setStringPainted(true);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(selectImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(algoSelectComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(preprocessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(preprocessProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(selectImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(preprocessButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(preprocessProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(algoSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    imageList.setModel(new javax.swing.AbstractListModel() {
        String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        public int getSize() { return strings.length; }
        public Object getElementAt(int i) { return strings[i]; }
    });
    jScrollPane1.setViewportView(imageList);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchDirectoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDirectoryButtonActionPerformed
        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            searchEngine.setDirectoryToSearch(selectedFile);
            searchDirectoryLabel.setText(selectedFile.getAbsolutePath());

            updateUiComponentStates();
        }
    }//GEN-LAST:event_searchDirectoryButtonActionPerformed

    private void algoSelectComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_algoSelectComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            SearchStrategy strategy = (SearchStrategy) evt.getItem();
            searchEngine.setSearchStrategy(strategy);
            
            updateUiComponentStates();
        }
    }//GEN-LAST:event_algoSelectComboBoxItemStateChanged

    private void preprocessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preprocessButtonActionPerformed
        isPreprocessing = true;
        updateUiComponentStates();
    }//GEN-LAST:event_preprocessButtonActionPerformed

    public void updateUiComponentStates() {
        boolean hasDirectory, hasAlgorithm, isPreprocessed;
        hasDirectory = searchEngine.getDirectoryToSearch() != null;
        hasAlgorithm = searchEngine.getSearchStrategy() != null;
        isPreprocessed = searchEngine.hasPreprocessedImages();
        
        preprocessProgressBar.setVisible(false);
        
        if (isPreprocessing) {
            preprocessProgressBar.setVisible(true);
            preprocessButton.setEnabled(false);
            selectImageButton.setEnabled(false);
            searchDirectoryButton.setEnabled(false);
        } else if (!isPreprocessed) {
            preprocessButton.setEnabled(hasDirectory && hasAlgorithm);
            selectImageButton.setEnabled(false);
            searchDirectoryButton.setEnabled(true);
        } else {
            preprocessButton.setEnabled(false);
            selectImageButton.setEnabled(true);
            searchDirectoryButton.setEnabled(true);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox algoSelectComboBox;
    private javax.swing.JList imageList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton preprocessButton;
    private javax.swing.JProgressBar preprocessProgressBar;
    private javax.swing.JButton searchDirectoryButton;
    private javax.swing.JLabel searchDirectoryLabel;
    private javax.swing.JButton selectImageButton;
    // End of variables declaration//GEN-END:variables
}
