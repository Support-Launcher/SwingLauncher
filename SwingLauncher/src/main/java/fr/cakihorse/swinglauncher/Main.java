package fr.cakihorse.swinglauncher;

import com.sun.management.OperatingSystemMXBean;
import fr.cakihorse.swinglauncher.panels.BackgroundPanel;
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
import java.lang.management.ManagementFactory;

public class Main {

    private static File saverFile = new File(String.valueOf(Launcher.getPath()), "user.infos");
    private static Saver saver = new Saver(saverFile);
    private static JLabel ramLabel;
    private static JComboBox<String> ramComboBox;


    public static void main(String[] args){

        //Is User already logged in ? :
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

            //load and create the saver file if doesn't exist.
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
            //you make it false if you want :
            f.setResizable(true);
            f.setSize(800, 600);
            f.setIconImage(ic);

            // panel bg
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg);
            // mainpanel
            JPanel mainPanel = new JPanel();


            //ramSlider
            // Got totalMemory available on the pc
            long totalMemory = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            long totalMemoryInGB = totalMemory / (1024 * 1024 * 1024);

            // make a values list for the comboBox
            String[] ramOptions = new String[(int) totalMemoryInGB];
            for (int i = 0; i < ramOptions.length; i++) {
                ramOptions[i] = Integer.toString(i + 1);
            }

            // JComboBox
            ramComboBox = new JComboBox<>(ramOptions);
            ramComboBox.addActionListener(e -> {
                String selectedValue = (String) ramComboBox.getSelectedItem();
                ramLabel.setText("RAM selected : " + selectedValue);

                int ramInGB = Integer.parseInt(selectedValue);
                saver.set("ram", String.valueOf(ramInGB));
            });
            //Show the ram values in a JLabel
            ramLabel = new JLabel("RAM selected : " + ramComboBox.getSelectedItem());






            //msButton
           JButton msButton = ImageButton.newButton("images/ms.png", 70, 50, false);
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
            JButton playBtn = ImageButton.newButton("images/play.png", 70, 50, false);
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
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
            mainPanel.add(msButton);
            mainPanel.add(Box.createHorizontalStrut(30)); // Ajoute un espace horizontal entre les boutons
            mainPanel.add(playBtn);
            mainPanel.add(Box.createHorizontalGlue()); // Étire les boutons pour qu'ils remplissent l'espace disponible
            mainPanel.add(ramComboBox);
            mainPanel.add(ramLabel);



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


}
