package com.kadu.frame;

import com.kadu.helper.Color;
import java.awt.Image;
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

    protected void updateMessage(JLabel label, String foreground, String message) {
        ArrayList list = new ArrayList();
        list.add(message);
        this.updateMessage(label, foreground, null, list);
    }

    protected void updateMessage(JLabel label, String foreground, ArrayList<String> message) {
        this.updateMessage(label, foreground, null, message);
    }

    protected void updateMessage(JLabel label, String foreground, String background, String message) {
        ArrayList list = new ArrayList();
        list.add(message);
        this.updateMessage(label, foreground, background, list);
    }

    protected void updateMessage(JLabel label, String foreground, String background, ArrayList<String> message) {
        label.setAlignmentX(JLabel.CENTER);
        if (null != foreground) {
            label.setForeground(Color.color(foreground));
        }
        if (null != background) {
            label.setBackground(Color.color(background));
        }
        label.setText(String.join(System.lineSeparator(), message));
        label.setVisible(true);
        label.repaint();
    }

    protected void displayErrors(JLabel label, ArrayList<String> errors, String defaultMessage) {
        if (errors.isEmpty()) {
            this.updateMessage(label, Color.COLOR_OK, defaultMessage);

            return;
        }

        this.updateMessage(label, Color.COLOR_WARNING, errors);
    }
}
