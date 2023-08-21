package fr.cakihorse.swinglauncher;

import fr.cakihorse.swinglauncher.utils.BackgroundPanel;
import fr.cakihorse.swinglauncher.utils.Resources;
import fr.cakihorse.swinglauncher.utils.threads.MsThread;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
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

    private static File saverFile = new File(String.valueOf(Launcher.getPath()), "user.infos");
    private static Saver saver = new Saver(saverFile);



    public static void main(String[] args) {

        //Is User logged in ? :
        MicrosoftAuthenticator microsoftAuthenticator = new MicrosoftAuthenticator();
        final String refresh_token = getSaver().get("refresh_token");
        MicrosoftAuthResult result = null;

        if (refresh_token != null && !refresh_token.isEmpty()) {
            try {
                result = microsoftAuthenticator.loginWithRefreshToken(refresh_token);
            } catch (MicrosoftAuthenticationException ex) {
                throw new RuntimeException(ex);
            }
            Launcher.authInfos = new AuthInfos(result.getProfile().getName(), result.getAccessToken(), result.getProfile().getId());
            System.out.printf("Logged in with '%s'%n", result.getProfile().getName());
        }

        SwingUtilities.invokeLater(() -> {

            if (!saverFile.exists()) {
                try {
                    saverFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                getSaver().load();
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
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == msButton) {
                        //if you want the crack connexion uncomment and use this:
                        //Launcher.authCrack();
                        //and comment this :
                        Thread t = new Thread(new MsThread());
                        t.start();
                        System.out.println("Lancement réussi avec succès !");
                    }
                }



            });

            //playbtn
            JButton playBtn = new JButton("Jouer...");
            playBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == playBtn) {

                        try {
                            Launcher.update();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            Launcher.launch();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }



            });


            // add elements
            mainPanel.add(msButton, BorderLayout.SOUTH);
            mainPanel.add(playBtn, BorderLayout.EAST);

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
