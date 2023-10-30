package fr.cakihorse.swinglauncher.app.panels;

import fr.cakihorse.swinglauncher.threads.MsThread;
import fr.cakihorse.swinglauncher.utils.Resources;
import fr.cakihorse.swinglauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static fr.cakihorse.swinglauncher.utils.Utils.customFont;

public class PCon extends JPanel {
    JButton btn = new JButton("Se connecter avec microsoft");
    Font font = customFont("Merriweather-Regular.ttf" , 20);
    JLabel title = new JLabel("Bienvenue !");
    JLabel logo = new JLabel(new ImageIcon(Resources.getResource("logo.png")));

    public PCon(final JFrame frame) {



        this.setLayout(null);
        this.setBounds(0, 0, 1280, 720);


        //bouton
        btn.setBounds(30, 290, 200, 50);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.newPanel(frame, new PHome());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Thread t = new Thread(new MsThread());
                t.start();
            }
        });
        this.add(btn);


        //titre
        title.setBounds(65, 210, 200, 50);
        title.setFont(font);
        this.add(title);

        //logo in the middle
        logo.setBounds(800, 140, 300, 300);
        logo.setHorizontalAlignment(JLabel.CENTER);
        this.add(logo);





        this.setVisible(true);
    }

}
