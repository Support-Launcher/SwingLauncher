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

            // Créez un panneau personnalisé pour contenir le fond
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg.getImage());

            // Utilisez un gestionnaire de disposition pour le panneau principal
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Créez le bouton Microsoft
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

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setContentText(error.getMessage());
                            alert.show();
                            return;
                        }

                    });
                }

            });





            // Ajoutez le bouton au panneau principal
            mainPanel.add(msButton, BorderLayout.SOUTH);

            // Ajoutez le panneau de fond et le panneau principal à la fenêtre
            f.getContentPane().add(backgroundPanel);
            f.getContentPane().add(mainPanel, BorderLayout.SOUTH);

            f.pack();
            f.setVisible(true);
        });
    }
}
