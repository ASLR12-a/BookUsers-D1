package versionuni;

import javax.swing.*;
        import java.awt.*;

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
        setLocationRelativeTo(null);
        setSize(800, 500);

        ListaUsuarios.cargar();

        verUsuariosButton.addActionListener(e -> {
            if (ListaUsuarios.usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            } else {
                String texto = "";
                for (Usuario u : ListaUsuarios.usuarios) {
                    texto += "ID: " + u.id + " | Usuario: " + u.username + " | Nombre: " + u.nombre + " " + u.apellido + "\n";
                }
                JOptionPane.showMessageDialog(null, texto);
            }
        });

        creditosDelCreadorButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Adrian Lopez y Leidy Niño");
        });

        crearUsuariosButton.addActionListener(e -> {
            new CrearUsuarios();
        });


        generarpdfButton.addActionListener(e -> {
            if (ListaUsuarios.usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay usuarios registrados para exportar.");
            } else {
                GeneradorPDF.exportarUsuarios();
            }
        });
    }

    public static void main(String[] args) {
        new VersionUni().setVisible(true);
    }
}