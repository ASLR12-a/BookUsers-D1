package versionuni;

import javax.swing.*;
import java.awt.*;


public class VersionUni extends JFrame {
    private JButton verUsuariosButton;
    private JButton creditosDelCreadorButton;
    private JButton crearUsuariosButton;
    private JPanel pantalla;

    public VersionUni () {
    setTitle("BookUser - Usuarios");
    setContentPane(pantalla);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setSize(800, 500);
    }

    public static void main (String[] args) {
        new VersionUni().setVisible(true);

    }

}
