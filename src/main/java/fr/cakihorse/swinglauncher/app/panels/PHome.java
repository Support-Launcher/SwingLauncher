package fr.cakihorse.swinglauncher.app.panels;

import com.sun.management.OperatingSystemMXBean;
import fr.cakihorse.swinglauncher.threads.LaunchThread;
import fr.cakihorse.swinglauncher.utils.Resources;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.*;
import static fr.cakihorse.swinglauncher.app.Main.getSaver;
import static fr.cakihorse.swinglauncher.utils.Utils.customFont;

public class PHome extends JPanel {

    //initialize all components
    JLabel title = new JLabel("LAUNCHER");
    JButton playBtn = new JButton("Jouer !");
    JComboBox<Integer> ramSelector = new JComboBox<>();
    JLabel logo = new JLabel(new ImageIcon(Resources.getResource("logo.png")));

    JLabel ramLabel = new JLabel("RAM :");

    //progressBar
    JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL,0, 100);

    Font font = customFont("Merriweather-Regular.ttf" , 60);
    Font font2 = customFont("Merriweather-Regular.ttf" , 20);
    Font font3 = customFont("Merriweather-Regular.ttf" , 15);

    public PHome() throws IOException {
        setLayout(null);
        setBounds(0, 0, 1280, 720);


        //Design of the Panel :

        //title
        title.setBounds(100, 200, 400, 200);

        title.setFont(font);
        add(title);

        //btn
        playBtn.setBounds(290, 400, 290, 50);
        playBtn.setFont(font2);
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement en cours...");
            }
        });

        //add ActionListener to launch the game
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //launch a new thread to avoid the freeze of the launcher
               Thread t = new Thread(new LaunchThread());
                t.start();

            }
        });

        add(playBtn);



        //ramSelector and its label
        long totalMemory = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        long totalMemoryInGB = totalMemory / (1024 * 1024 * 1024);
        for (int i = 1; i <= totalMemoryInGB; i++) {
            ramSelector.addItem(i);
        }
        ramSelector.setBounds(28, 400, 200, 50);
        ramSelector.setFont(font2);

        add(ramSelector);
        ramSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == ramSelector) {
                    //set the selected item to the saver file.
                    getSaver().set("ram", String.valueOf(ramSelector.getSelectedItem()));
                }
            }
        });
        // -----------label---------------
        ramLabel.setBounds(30, 366, 200, 50);
        ramLabel.setForeground(Color.WHITE);
        ramLabel.setFont(font3);
        add(ramLabel);


        //logo
        logo.setBounds(800, 140, 300, 300);
        logo.setHorizontalAlignment(JLabel.CENTER);
        add(logo);


        //TODO: progressBar
        progressBar.setStringPainted(true);
        progressBar.setBounds(0, 680, 100, 40);
        this.add(progressBar);


        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Draw the background image
        g.drawImage(Resources.getResource("bg.png"), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
