package versionuni;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CrearUsuarios extends JFrame {
    private JPanel PantallaSegundaria;
    private JLabel usi;
    private JLabel ni;
    private JLabel pn;
    private JLabel sn;
    private JLabel pa;
    private JLabel sa;
    private JLabel contra;
    private JLabel telefono;
    private JLabel ce;
    private JLabel foto;
    private JLabel ti;
    private JTextField ni2;
    private JTextField pn2;
    private JTextField sn2;
    private JTextField pa2;
    private JTextField sa2;
    private JPasswordField pass2;
    private JTextField tl2;
    private JTextField ce2;
    private JComboBox Boxti;
    private JButton btnfotoguardar;
    private JButton btnguardarinfo;
    private JButton btnactualizar;
    private JTextField txtUsername;
    private JLabel user;

    private File archivoSeleccionado = null;
    private String rutaFotoRelativa = "";
    private Usuario usuarioEditando = null;
    private JFrame ventana;

    public JTextField getNi2() { return ni2; }
    public void setNi2(JTextField ni2) { this.ni2 = ni2; }

    public JTextField getPn2() { return pn2; }
    public void setPn2(JTextField pn2) { this.pn2 = pn2; }

    public JTextField getSn2() { return sn2; }
    public void setSn2(JTextField sn2) { this.sn2 = sn2; }

    public JTextField getPa2() { return pa2; }
    public void setPa2(JTextField pa2) { this.pa2 = pa2; }

    public JTextField getSa2() { return sa2; }
    public void setSa2(JTextField sa2) { this.sa2 = sa2; }

    public JPasswordField getPass2() { return pass2; }
    public void setPass2(JPasswordField pass2) { this.pass2 = pass2; }

    public JTextField getTl2() { return tl2; }
    public void setTl2(JTextField tl2) { this.tl2 = tl2; }

    public JTextField getCe2() { return ce2; }
    public void setCe2(JTextField ce2) { this.ce2 = ce2; }

    public CrearUsuarios() {
        this.usuarioEditando = null;
        inicializarVentana();
        configurarValidacion();
        verificarCampos();
    }

    public CrearUsuarios(Usuario usuarioAEditar) {
        this.usuarioEditando = usuarioAEditar;
        inicializarVentana();

        if (this.usuarioEditando != null) {
            seleccionarTipoId(this.usuarioEditando.tipoId);
            ni2.setText(this.usuarioEditando.numId != null ? this.usuarioEditando.numId : "");

            String nombreCompleto = this.usuarioEditando.nombre != null ? this.usuarioEditando.nombre.trim() : "";
            String[] partesNombre = nombreCompleto.split("\\s+", 2);
            pn2.setText(partesNombre.length > 0 ? partesNombre[0] : "");
            sn2.setText(partesNombre.length > 1 ? partesNombre[1] : "");

            String apellidoCompleto = this.usuarioEditando.apellido != null ? this.usuarioEditando.apellido.trim() : "";
            String[] partesApellido = apellidoCompleto.split("\\s+", 2);
            pa2.setText(partesApellido.length > 0 ? partesApellido[0] : "");
            sa2.setText(partesApellido.length > 1 ? partesApellido[1] : "");

            pass2.setText(this.usuarioEditando.password != null ? this.usuarioEditando.password : "");
            tl2.setText(this.usuarioEditando.telefono != null ? this.usuarioEditando.telefono : "");
            ce2.setText(this.usuarioEditando.correo != null ? this.usuarioEditando.correo : "");
            txtUsername.setText(this.usuarioEditando.username != null ? this.usuarioEditando.username : "");

            if (this.usuarioEditando.foto != null && !this.usuarioEditando.foto.trim().isEmpty()) {
                archivoSeleccionado = new File(this.usuarioEditando.foto);
                rutaFotoRelativa = this.usuarioEditando.foto.trim();
                btnfotoguardar.setText("Foto actual: " + archivoSeleccionado.getName());
            }
        }

        configurarValidacion();
        generarUsername();
        verificarCampos();
    }

    private void seleccionarTipoId(String tipoId) {
        if (tipoId == null || tipoId.trim().isEmpty()) {
            return;
        }
        String buscado = tipoId.trim();
        for (int i = 0; i < Boxti.getItemCount(); i++) {
            Object item = Boxti.getItemAt(i);
            if (item != null && item.toString().trim().equalsIgnoreCase(buscado)) {
                Boxti.setSelectedIndex(i);
                return;
            }
        }
        Boxti.setSelectedItem(buscado);
    }

    private boolean cedulaYaExisteEnOtroUsuario(String cedula, Usuario usuarioActual) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return false;
        }
        String cedulaLimpia = cedula.trim();
        for (Usuario u : ListaUsuarios.usuarios) {
            if (u != usuarioActual && u.numId != null && u.numId.trim().equalsIgnoreCase(cedulaLimpia)) {
                return true;
            }
        }
        return false;
    }

    private String copiarFotoAlProyecto(File origen) {
        try {
            File carpetaDestino = new File("fotos_usuarios");
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs();
            }

            String nombreLimpio = System.currentTimeMillis() + "_" + origen.getName().replaceAll("\\s+", "_");
            File destino = new File(carpetaDestino, nombreLimpio);
            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return destino.getPath();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    "No se pudo copiar la foto al proyecto: " + ex.getMessage(),
                    "Error de foto",
                    JOptionPane.ERROR_MESSAGE
            );
            return "";
        }
    }

    private void inicializarVentana() {
        ventana = new JFrame("Registrar usuario");
        ventana.setTitle("BookUser - Crear / Actualizar usuarios");

        JPanel panelConFondo = new JPanel() {
            private final Image imagenFondo = new ImageIcon("src/imagenescute/gato_fondo.jpg.jpeg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConFondo.setLayout(new BorderLayout());

        PantallaSegundaria.setOpaque(false);
        for (Component c : PantallaSegundaria.getComponents()) {
            if (c instanceof JPanel) {
                ((JPanel) c).setOpaque(false);
            }
        }

        panelConFondo.add(PantallaSegundaria, BorderLayout.CENTER);

        Boxti.removeAllItems();
        Boxti.addItem("                ");
        Boxti.addItem("CC");
        Boxti.addItem("TI");
        Boxti.addItem("CE");
        Boxti.addItem("PA");
        Boxti.addItem("NUIP");
        Boxti.addItem("Registro Civil");
        Boxti.addItem("Tarjeta de Extrangeria");
        Boxti.addItem("Documento de identificacion extranjero");
        Boxti.addItem("PEP");

        txtUsername.setEditable(false);

        ventana.setContentPane(panelConFondo);
        ventana.setSize(800, 800);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        btnfotoguardar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(ventana);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File seleccionado = fileChooser.getSelectedFile();
                String rutaCopiada = copiarFotoAlProyecto(seleccionado);
                if (!rutaCopiada.isEmpty()) {
                    archivoSeleccionado = new File(rutaCopiada);
                    rutaFotoRelativa = rutaCopiada;
                    btnfotoguardar.setText("Foto cargada: " + seleccionado.getName());
                    verificarCampos();
                }
            }
        });

        btnguardarinfo.addActionListener(e -> {
            String cedulaNueva = ni2.getText().trim();
            if (cedulaYaExisteEnOtroUsuario(cedulaNueva, null)) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "Ese número de identificación ya está registrado.",
                        "Cédula duplicada",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            generarUsername();

            String nombreArmado = (pn2.getText().trim() + " " + sn2.getText().trim()).trim();
            String apellidoArmado = (pa2.getText().trim() + " " + sa2.getText().trim()).trim();

            Usuario u = new Usuario();
            int maxId = 0;
            for (Usuario user : ListaUsuarios.usuarios) {
                try {
                    int currentId = Integer.parseInt(user.id);
                    if (currentId > maxId) maxId = currentId;
                } catch (NumberFormatException ex) {
                }
            }
            u.id = String.valueOf(maxId + 1);
            u.tipoId = Boxti.getSelectedItem() != null ? Boxti.getSelectedItem().toString().trim() : "";
            u.numId = cedulaNueva;
            u.nombre = nombreArmado;
            u.apellido = apellidoArmado;
            u.username = txtUsername.getText();
            u.password = new String(pass2.getPassword());
            u.telefono = tl2.getText().trim();
            u.correo = ce2.getText().trim();
            u.foto = rutaFotoRelativa;

            ListaUsuarios.usuarios.add(u);
            ListaUsuarios.guardar();
            mostrarExitoYCerrar();
        });

        if (btnactualizar != null) {
            btnactualizar.addActionListener(e -> {
                if (usuarioEditando == null) {
                    return;
                }

                String cedulaEditada = ni2.getText().trim();
                if (cedulaYaExisteEnOtroUsuario(cedulaEditada, usuarioEditando)) {
                    JOptionPane.showMessageDialog(
                            ventana,
                            "Ese número de identificación ya pertenece a otro usuario.",
                            "Cédula duplicada",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                generarUsername();

                String nombreArmado = (pn2.getText().trim() + " " + sn2.getText().trim()).trim();
                String apellidoArmado = (pa2.getText().trim() + " " + sa2.getText().trim()).trim();

                Object seleccionado = Boxti.getSelectedItem();
                if (seleccionado != null && !seleccionado.toString().trim().isEmpty()) {
                    usuarioEditando.tipoId = seleccionado.toString().trim();
                }
                usuarioEditando.numId = cedulaEditada;
                usuarioEditando.nombre = nombreArmado;
                usuarioEditando.apellido = apellidoArmado;
                usuarioEditando.username = txtUsername.getText();
                usuarioEditando.password = new String(pass2.getPassword());
                usuarioEditando.telefono = tl2.getText().trim();
                usuarioEditando.correo = ce2.getText().trim();
                if (!rutaFotoRelativa.isEmpty()) {
                    usuarioEditando.foto = rutaFotoRelativa;
                }

                ListaUsuarios.guardar();
                mostrarExitoYCerrar();
            });
        }
    }

    private void mostrarExitoYCerrar() {
        ImageIcon iconoGato = new ImageIcon("src/imagenescute/gato_pensando.jpg.jpeg");
        Image imagenEscalada = iconoGato.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenEscalada);

        JOptionPane.showMessageDialog(
                null,
                "¡Información guardada correctamente!",
                "Operación Exitosa",
                JOptionPane.INFORMATION_MESSAGE,
                iconoFinal
        );

        ventana.dispose();
    }

    private void configurarValidacion() {
        DocumentListener listenerTexto = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                generarUsername();
                verificarCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                generarUsername();
                verificarCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                generarUsername();
                verificarCampos();
            }
        };

        ni2.getDocument().addDocumentListener(listenerTexto);
        pn2.getDocument().addDocumentListener(listenerTexto);
        sn2.getDocument().addDocumentListener(listenerTexto);
        pa2.getDocument().addDocumentListener(listenerTexto);
        sa2.getDocument().addDocumentListener(listenerTexto);
        pass2.getDocument().addDocumentListener(listenerTexto);
        tl2.getDocument().addDocumentListener(listenerTexto);
        ce2.getDocument().addDocumentListener(listenerTexto);

        Boxti.addActionListener(e -> verificarCampos());
    }

    private void generarUsername() {
        String primerNombre = pn2.getText().trim().toLowerCase().replace(" ", "");
        String primerApellido = pa2.getText().trim().toLowerCase();
        String segundoApellido = sa2.getText().trim().toLowerCase();

        String inicialPA = primerApellido.isEmpty() ? "" : primerApellido.substring(0, 1);
        String inicialSA = segundoApellido.isEmpty() ? "" : segundoApellido.substring(0, 1);

        txtUsername.setText(inicialPA + inicialSA + primerNombre);
    }

    private void verificarCampos() {
        boolean niOk = !ni2.getText().trim().isEmpty();
        boolean pnOk = !pn2.getText().trim().isEmpty();
        boolean paOk = !pa2.getText().trim().isEmpty();
        boolean passOk = !new String(pass2.getPassword()).trim().isEmpty();
        boolean tlOk = !tl2.getText().trim().isEmpty();
        boolean ceOk = !ce2.getText().trim().isEmpty();
        boolean camposBasicosOk = niOk && pnOk && paOk && passOk && tlOk && ceOk;

        if (usuarioEditando != null) {
            if (btnguardarinfo != null) {
                btnguardarinfo.setEnabled(false);
            }
            System.out.println("btnactualizar es: " + btnactualizar);
            if (btnactualizar != null) {
                btnactualizar.setEnabled(camposBasicosOk);
            }
        } else {
            boolean tipoIdOk = Boxti.getSelectedIndex() > 0;
            boolean fotoOk = archivoSeleccionado != null;
            if (btnguardarinfo != null) {
                btnguardarinfo.setEnabled(tipoIdOk && camposBasicosOk && fotoOk);
            }
            if (btnactualizar != null) {
                btnactualizar.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        ListaUsuarios.cargar();

        if (!ListaUsuarios.usuarios.isEmpty()) {
            Usuario usuarioAEditar = ListaUsuarios.usuarios.get(0);
            new CrearUsuarios(usuarioAEditar);
        } else {
            new CrearUsuarios();
        }
    }
}