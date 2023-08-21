package fr.cakihorse.swinglauncher;

import fr.cakihorse.swinglauncher.utils.BackgroundPanel;
import fr.cakihorse.swinglauncher.utils.Resources;
import fr.cakihorse.swinglauncher.utils.threads.MsThread;
import fr.flowarg.flowlogger.Logger;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {

    private static File saverFile = new File(String.valueOf(Launcher.getPath()), "user.stock");
    private static Saver saver = new Saver(saverFile);
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            if (!saverFile.exists()) {
                try {
                    saverFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            Image bg = Resources.getResource("images/background.png");
            Image ic = Resources.getResource("images/ic.png");


            JFrame f = new JFrame("Swinglauncher | By Cakihorse");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(800, 600);
            f.setIconImage(ic);

            // panel bg
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg);

            // mainpanel
            JPanel mainPanel = new JPanel(new BorderLayout());


            //msButton
            JButton msButton = new JButton("Connexion...");
            msButton.addActionListener(new ActionListener() {
                private Logger logger;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == msButton) {
                        //if you want the crack connexion use this:
                        //Launcher.authCrack();
                        //and comment this :
                        Thread t = new Thread(new MsThread());
                        t.start();
                    }
                }



            });


            // add elements
            mainPanel.add(msButton, BorderLayout.SOUTH);

            // add bg to frame
            f.getContentPane().add(backgroundPanel);
            f.getContentPane().add(mainPanel, BorderLayout.SOUTH);

            //Show the app
            f.pack();
            f.setVisible(true);


        });

    }

    public static Saver getSaver() {
        return saver;
    }
}
