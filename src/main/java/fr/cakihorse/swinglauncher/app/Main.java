package fr.cakihorse.swinglauncher.app;


import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import fr.cakihorse.swinglauncher.app.panels.PCon;
import fr.cakihorse.swinglauncher.app.panels.PHome;
import fr.cakihorse.swinglauncher.utils.Resources;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

    private static File saverFile = new File(String.valueOf(Launcher.getPath()), "user.infos");
    private static Saver saver = new Saver(saverFile);
    private int mouseX, mouseY;

    public Main() throws IOException {

        /*
        *               WARNING !
        * This programme is gave but, the design must be changed !
        * Only keep the "Back-end" of the app !
        * (The design is only there because it has to be, please make it yours!)
        * -------------------------------------------------
        * To build the project with dependencies and make the jar executable use the
        * Gradle task "shadowJar".
        * -------------------------------------------------
        * Author: Cakihorse.
        *
        */


        this.setTitle("Launcher V2");
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new PCon(this));
        this.setIconImage(new ImageIcon(Resources.getResource("logo.png")).getImage());
        this.setResizable(false);

        //verify if the user is connected
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
            setContentPane(new PHome());
            repaint();
            revalidate();
        }


        //Set the ram to 2 if it's not set, as a precaution
        if (getSaver().get("ram") == null) {
            getSaver().set("ram", "2");
        }




        // This allows the user to move the window
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                Main.this.setLocation(x, y);
            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        try {
            //setuyo theme
            FlatArcDarkIJTheme.setup();
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du thème");
            e.printStackTrace();
        }

        Main main = new Main();



    }
    public static Saver getSaver() {
        return saver;
    }
}
