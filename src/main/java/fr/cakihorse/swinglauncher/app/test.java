package fr.cakihorse.swinglauncher.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ComboBoxExemple extends JFrame implements ItemListener {

    // frame 
    static JFrame frame;
    // combobox 
    static JComboBox combobox;
    // label 
    static JLabel l1, l2;

    public static void main(String[] args)
    {
        // créer un nouvea frame 
        frame = new JFrame("frame");

        // créer un objet
        ComboBoxExemple obj = new ComboBoxExemple();

        // définir la disposition du frame 
        frame.setLayout(new FlowLayout());

        // tableau de chaînes contenant des langages
        String s1[] = { "Java", "PHP", "Python", "C++", "Ruby" };

        // créer une case à cocher
        combobox = new JComboBox(s1);

        // ajouter ItemListener
        combobox.addItemListener(obj);

        // créer des étiquettes
        l1 = new JLabel("Quel est votre langage prefere? ");
        l2 = new JLabel("[Java]");

        // définir la couleur du texte
        l2.setForeground(Color.blue);

        // créer un nouveau panneau
        JPanel p = new JPanel();

        // ajouter combobox et labels au panneau
        p.add(l1);
        p.add(combobox);
        p.add(l2);

        // ajouter le panneau au frame
        frame.add(p);

        // définir la taille du frame 
        frame.setSize(400, 200);

        frame.show();
    }
    public void itemStateChanged(ItemEvent e)
    {
        // si l'état du combobox est modifiée 
        if (e.getSource() == combobox) {

            l2.setText(" ["+combobox.getSelectedItem()+"]");
        }
    }
}