package fr.cakihorse.swinglauncher.utils;

import javax.swing.*;
import java.awt.*;

public class ImageButton {
    public static JButton newButton(String path, int width, int height) {
        ImageIcon playIcon = new ImageIcon(Resources.getResource(path));
        int newWidth = width;
        int newHeight = height;
        Image resizedImage = playIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        //set the image resized on the button.
        JButton btn = new JButton(resizedIcon);
        return btn;
    }
}
