package study.rnTest.listener;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class FileChooserPropertyChangeListener implements PropertyChangeListener {
    private JFileChooser fileChooser;
    private JLabel selectedFileLabel;
    private JButton computeBtn;

    public FileChooserPropertyChangeListener(JFileChooser fileChooser, JLabel selectedFileLabel, JButton computeBtn) {
        this.fileChooser = fileChooser;
        this.selectedFileLabel = selectedFileLabel;
        this.computeBtn = computeBtn;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        File selectedFile = fileChooser.getSelectedFile();
        if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(evt.getPropertyName())) {
            if (selectedFile.exists()) {
                selectedFileLabel.setText(String.format("Выбранный файл: %s", selectedFile));
                computeBtn.setEnabled(true);
            } else {
                selectedFileLabel.setText("");
                computeBtn.setEnabled(false);
            }
        }
    }
}
