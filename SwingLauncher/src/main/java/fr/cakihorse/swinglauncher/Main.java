package fr.cakihorse.swinglauncher;


import fr.cakihorse.swinglauncher.utils.BackgroundPanel;


import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        //set new frame
        String imgUrl="E:\\java\\Launchers\\SwingLauncher\\src\\main\\resources\\background.png";
        ImageIcon bg = new ImageIcon(imgUrl);

        String icUrl="E:\\java\\Launchers\\SwingLauncher\\src\\main\\resources\\ic.png";
        ImageIcon icone = new ImageIcon(icUrl);

            JFrame f = new JFrame("Swinglauncher | By Cakihorse");
            //Set the close operation
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //set size
            f.setSize(1080, 720);
            f.setIconImage(icone.getImage());
            f.setVisible(true);

            //URL de l'image


            //Création de JLable avec un alignement gauche
            JLabel jlabel = new JLabel(bg, JLabel.CENTER);

            //ajouter les deux JLabel a JFrame
            f.getContentPane().add(jlabel);
            f.validate();


    }

    }

