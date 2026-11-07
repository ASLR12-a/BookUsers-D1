package versionone;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VersionOne {
    private JPanel panel1;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton guardarButton;

    public VersionOne() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();

                if (nombre.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario registrado: " + nombre);
                    txtNombre.setText("");
                }
            }
        });
    }
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("BookUsers - Adrian Lopez - Grupo D1");

        frame.setContentPane(new VersionOne().panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}