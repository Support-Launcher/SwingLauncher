package fr.cakihorse.swinglauncher.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ImageButton {
    public static JButton newButton(String path, int width, int height, boolean isNotTransparent) {
        ImageIcon playIcon = new ImageIcon(Resources.getResource(path));
        int newWidth = width;
        int newHeight = height;
        Image resizedImage = playIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JButton btn = new JButton(resizedIcon);
        // Remove all default button decorations
        btn.setBorderPainted(isNotTransparent);
        btn.setFocusPainted(isNotTransparent);
        btn.setContentAreaFilled(isNotTransparent);

        // Create an empty border to remove any padding around the image
        btn.setBorder(new EmptyBorder(0, 0, 0, 0));
        //set the image resized on the button.

        return btn;
    }
}
