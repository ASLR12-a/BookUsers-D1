package versionuni;

import javax.swing.*;
import java.awt.*;

// MENUUUUUUU



public class VersionUni extends JFrame {
    private JButton verUsuariosButton;
    private JButton creditosDelCreadorButton;
    private JButton crearUsuariosButton;
    private JButton generarpdfButton;
    private JPanel pantalla;

    public VersionUni() {
        setTitle("BookUser - Adrian Lopez y Leidy Niño");
        setContentPane(pantalla);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(800, 500);
        setLocationRelativeTo(null);

        ListaUsuarios.cargar();

        verUsuariosButton.addActionListener(e -> {
            if (ListaUsuarios.usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
            } else {
                new Listado(ListaUsuarios.usuarios).setVisible(true);
            }
        });

        creditosDelCreadorButton.addActionListener(e -> {
            Creditos ventanaCreditos = new Creditos();
            ventanaCreditos.setVisible(true);
        });

        crearUsuariosButton.addActionListener(e -> {
            new CrearUsuarios();
        });

        generarpdfButton.addActionListener(e -> {
            if (ListaUsuarios.usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados para exportar.");
            } else {
                GeneradorPDF.exportarUsuarios();
            }
        });
    }

    public static void main(String[] args) {
        new VersionUni().setVisible(true);
    }
}