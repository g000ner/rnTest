package study.rnTest.exception.handler;

import javax.swing.*;

public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {
    private JOptionPane messagePane;
    private JFrame frame;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String exceptionMessage = e.getMessage();
        String windowName = "Ошибка";

        JOptionPane.showMessageDialog(frame, exceptionMessage, windowName, JOptionPane.ERROR_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JOptionPane getMessagePane() {
        return messagePane;
    }

    public void setMessagePane(JOptionPane messagePane) {
        this.messagePane = messagePane;
    }
}
