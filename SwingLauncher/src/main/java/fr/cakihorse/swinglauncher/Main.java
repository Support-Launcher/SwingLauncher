package fr.cakihorse.swinglauncher;

import fr.cakihorse.swinglauncher.utils.BackgroundPanel;
import fr.cakihorse.swinglauncher.utils.ImageButton;
import fr.cakihorse.swinglauncher.utils.Resources;
import fr.cakihorse.swinglauncher.threads.MsThread;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.openlauncherlib.util.ramselector.RamSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static File saverFile = new File(String.valueOf(Launcher.getPath()), "user.infos");
    private static Saver saver = new Saver(saverFile);


    private static File ramFile = new File(Launcher.getPath().toFile(), "ram.infos");
    private static final RamSelector ramSelector = new RamSelector(getRamFile());



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

            //load and create the saver file if doesn't exists.
            if (!ramFile.exists()) {
                try {
                    ramFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!saverFile.exists()) {
                try {
                    saverFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
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


            //settingsButton
            JButton ramButton = ImageButton.newButton("images/settings.png", 70, 50);
            ramButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == ramButton) {
                            ramSelector.display();
                            ramSelector.setFile(ramFile.toPath());
                            ramSelector.save();
                            saver.set("ram", Arrays.toString(ramSelector.getRamArguments()));

                    }
                }


            });


            //msButton
            JButton msButton = ImageButton.newButton("images/ms.png", 70, 50);
            msButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == msButton) {
                        //if you want the crack connexion uncomment and use this:
                        //Launcher.authCrack();
                        //and comment this :
                        Thread t = new Thread(new MsThread());
                        t.start();
                        System.out.println("Authentication successful !");
                    }
                }
            });





            //playButton
            JButton playBtn = ImageButton.newButton("images/play.png", 70, 50);
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
                            JOptionPane.showMessageDialog(null, ex.getMessage() + " | Please login!");
                            throw new RuntimeException(ex);

                        }

                    }
                }
            });


            // Add buttons to the main panel
            mainPanel.add(msButton, BorderLayout.WEST);
            mainPanel.add(playBtn, BorderLayout.EAST);
            mainPanel.add(ramButton, BorderLayout.SOUTH);

            // Add bg to frame
            f.setContentPane(backgroundPanel);
            f.add(mainPanel);

            // ...

            f.pack();
            f.setVisible(true);
        });
    }

    public static Saver getSaver() {
        return saver;
    }

    public static File getRamFile() {
        return ramFile;
    }
}
