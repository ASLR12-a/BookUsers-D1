package versionuni;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Listado extends JFrame {
    private JPanel panelPrincipalListado;
    private JButton btnVerDetalle;
    private JList<Usuario> lstUsuario;
    private JButton eliminarButton;

    private DefaultListModel<Usuario> modelo;

    public Listado(ArrayList<Usuario> lista) {
        setTitle("BookUsers - Listado de usuarios");
        setContentPane(panelPrincipalListado);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 380);
        setLocationRelativeTo(null);

        modelo = new DefaultListModel<>();
        lstUsuario.setModel(modelo);

        ArrayList<Usuario> datos = (lista != null) ? lista : ListaUsuarios.usuarios;
        for (Usuario u : datos) {
            modelo.addElement(u);
        }

        btnVerDetalle.addActionListener(e -> {
            int idx = lstUsuario.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario primero.");
                return;
            }
            Usuario seleccionado = modelo.getElementAt(idx);
            new Detalle(seleccionado).setVisible(true);
        });

        if (eliminarButton != null) {
            eliminarButton.addActionListener(e -> {
                int idx = lstUsuario.getSelectedIndex();
                if (idx == -1) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Selecciona un usuario de la lista para eliminar.",
                            "Sin selección",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                Usuario seleccionado = modelo.getElementAt(idx);
                String nombreCompleto = ((seleccionado.nombre != null ? seleccionado.nombre : "") + " " +
                        (seleccionado.apellido != null ? seleccionado.apellido : "")).trim();

                int opcion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que deseas eliminar al usuario \"" + nombreCompleto + "\"?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    // Opcional: borra la foto local si está dentro de fotos_usuarios
                    if (seleccionado.foto != null && !seleccionado.foto.trim().isEmpty()) {
                        File archivoFoto = new File(seleccionado.foto.trim());
                        String rutaNormalizada = archivoFoto.getPath().replace('\\', '/');
                        if (rutaNormalizada.startsWith("fotos_usuarios/") && archivoFoto.exists()) {
                            archivoFoto.delete();
                        }
                    }

                    ListaUsuarios.usuarios.remove(seleccionado);
                    if (lista != null && lista != ListaUsuarios.usuarios) {
                        lista.remove(seleccionado);
                    }
                    ListaUsuarios.guardar();
                    modelo.remove(idx);

                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                }
            });
        }
    }
}