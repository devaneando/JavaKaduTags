package com.kadu.frame;

import com.kadu.helper.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

abstract public class AbstractKaduFrame extends JFrame {

    protected static final int TYPE_OK = 0;
    protected static final int TYPE_WARNING = 1;
    protected static final int TYPE_ERROR = 2;

    @Override
    public Image getIconImage() {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource("icons/96/tag.png"));
        } catch (IOException ex) {
            Logger.getLogger(FraConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    protected void setLocation() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    protected void showError(Exception ex, JLabel label) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

        label.setAlignmentX(JLabel.CENTER);
        label.setForeground(Color.color(Color.COLOR_ERROR));
        label.setText(ex.getMessage());
        label.setVisible(true);
        label.repaint();
    }

    protected void showWarning(JLabel label, String message) {
        label.setAlignmentX(JLabel.CENTER);
        label.setForeground(Color.color(Color.COLOR_WARNING));
        label.setText(message);
        label.setVisible(true);
        label.repaint();
    }

    protected void showSuccess(JLabel label, String message) {
        label.setAlignmentX(JLabel.CENTER);
        label.setForeground(Color.color(Color.COLOR_OK));
        label.setText(message);
        label.setVisible(true);
        label.repaint();
    }

    protected void handleErrors(JLabel label, ArrayList<String> errors, String successMessage) {
        if (errors.isEmpty()) {
            this.showSuccess(label, successMessage);

            return;
        }

        label.setAlignmentX(JLabel.CENTER);
        label.setForeground(Color.color(Color.COLOR_ERROR));
        label.setText(String.join(System.lineSeparator(), errors));
        label.setVisible(true);
        label.repaint();
    }
}
