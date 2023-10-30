package fr.cakihorse.swinglauncher.utils;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static void newPanel(JFrame frame, JPanel panel) {
        frame.getContentPane().removeAll();
        frame.setContentPane(panel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public static Font customFont(String path, int size) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Utils.class.getClassLoader().getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }
}
