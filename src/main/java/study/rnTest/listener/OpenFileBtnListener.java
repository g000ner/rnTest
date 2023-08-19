package study.rnTest.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileBtnListener implements ActionListener {
    private JFileChooser fileChooser;
    private JFrame jFrame;

    public OpenFileBtnListener(JFrame jFrame, JFileChooser fileChooser) {
        this.jFrame = jFrame;
        this.fileChooser = fileChooser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooser.setDialogTitle("Выбор файла");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(jFrame);
    }
}
