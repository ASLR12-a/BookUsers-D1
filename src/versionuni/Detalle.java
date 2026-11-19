package versionuni;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Detalle extends JFrame {
    private JPanel panelPrincipalDetalle;
    private JButton btnGenerarPdf;
    private JLabel lblFoto;
    private JTextArea txtDatos;

    private Usuario usuario;

    public Detalle(Usuario usuario) {
        this.usuario = usuario;

        setTitle("BookUsers - Detalle de usuario");
        setContentPane(panelPrincipalDetalle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);

        txtDatos.setEditable(false);
        cargarDatos();
        cargarFoto();

        btnGenerarPdf.addActionListener(e -> {
            if (this.usuario != null) {
                GeneradorPDF.exportarUsuarioDetalle(this.usuario, this);
            }
        });
    }

    private void cargarDatos() {
        if (usuario == null) return;
        txtDatos.setText(
                "ID: " + usuario.id + "\n" +
                        "Tipo ID: " + usuario.tipoId + "\n" +
                        "Número ID: " + usuario.numId + "\n" +
                        "Nombre: " + usuario.nombre + "\n" +
                        "Apellido: " + usuario.apellido + "\n" +
                        "Username: " + usuario.username + "\n" +
                        "Teléfono: " + usuario.telefono + "\n" +
                        "Correo: " + usuario.correo
        );
    }

    private void cargarFoto() {
        if (usuario == null || usuario.foto == null || usuario.foto.trim().isEmpty()) {
            lblFoto.setText("Sin foto");
            return;
        }
        try {
            File f = new File(usuario.foto.trim());
            if (!f.exists()) {
                lblFoto.setText("Foto no encontrada");
                return;
            }
            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblFoto.setText("Error al mostrar foto");
        }
    }
}