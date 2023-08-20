package fr.cakihorse.swinglauncher;

import fr.cakihorse.swinglauncher.utils.BackgroundPanel;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            String imgUrl = "E:\\java\\Launchers\\SwingLauncher\\src\\main\\resources\\background.png";
            ImageIcon bg = new ImageIcon(imgUrl);

            String icUrl = "E:\\java\\Launchers\\SwingLauncher\\src\\main\\resources\\ic.png";
            ImageIcon icone = new ImageIcon(icUrl);

            JFrame f = new JFrame("Swinglauncher | By Cakihorse");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(1080, 720);
            f.setIconImage(icone.getImage());

            // panel bg
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg.getImage());

            // mainapel
            JPanel mainPanel = new JPanel(new BorderLayout());

            //msButton
            JButton msButton = new JButton("Microsoft");
            msButton.setBounds(500, 600, 100, 50);

            msButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO : WebView microsoft
                    authenticateMS();
                }
                public void authenticateMS() {
                    MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
                    authenticator.loginWithAsyncWebview().whenComplete((response, error) -> {
                        if (error != null) {
                            JOptionPane.showMessageDialog(f, error.getMessage());
                            return;
                        }

                    });
                }

            });





            // add elements
            mainPanel.add(msButton, BorderLayout.SOUTH);

            // add bg to frame
            f.getContentPane().add(backgroundPanel);
            f.getContentPane().add(mainPanel, BorderLayout.SOUTH);

            f.pack();
            f.setVisible(true);
        });
    }
}
