package fr.cakihorse.swinglauncher.utils;

import fr.cakihorse.swinglauncher.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Resources {

    public static Image getResource(String name) {
        InputStream imageStream = Main.class.getClassLoader().getResourceAsStream(name);
        Image image;
        try {
            image = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
