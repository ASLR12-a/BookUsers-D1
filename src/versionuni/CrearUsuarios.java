package versionuni;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;

public class CrearUsuarios extends Component {
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
    private JTextField txtUsername;
    private JLabel user;

    private File archivoSeleccionado = null;

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
        JFrame ventana = new JFrame("Registrar usuario");
        ventana.setTitle("BookUser - Crear usuarios");

        // AQUÍ PONEMOS EL GATO DE LA PATICA COMO FONDO
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

        ventana.setContentPane(panelConFondo);
        ventana.setSize(800, 800);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

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

        configurarValidacion();

        btnfotoguardar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(ventana);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                archivoSeleccionado = fileChooser.getSelectedFile();
                btnfotoguardar.setText("Foto cargada: " + archivoSeleccionado.getName());
                verificarCampos();
            }
        });

        btnguardarinfo.addActionListener(e -> {
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
            u.tipoId = (String) Boxti.getSelectedItem();
            u.numId = ni2.getText();
            u.nombre = pn2.getText() + " " + sn2.getText();
            u.apellido = pa2.getText() + " " + sa2.getText();
            u.username = txtUsername.getText();
            u.password = new String(pass2.getPassword());
            u.telefono = tl2.getText();
            u.correo = ce2.getText();
            u.foto = (archivoSeleccionado != null) ? archivoSeleccionado.getAbsolutePath() : "";

            ListaUsuarios.usuarios.add(u);
            ListaUsuarios.guardar();

            // AQUÍ PONEMOS EL GATO PENSANDO PARA LA VENTANA DE ÉXITO
            ImageIcon iconoGato = new ImageIcon("src/imagenescute/gato_pensando.jpg.jpeg");
            Image imagenEscalada = iconoGato.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon iconoFinal = new ImageIcon(imagenEscalada);

            JOptionPane.showMessageDialog(
                    null,
                    "¡Termine de ingresar la información correctamente!",
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE,
                    iconoFinal
            );

            SwingUtilities.getWindowAncestor(panelConFondo).dispose();
        });
    }

    private void configurarValidacion() {
        btnguardarinfo.setEnabled(false);

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
        boolean tipoIdOk = Boxti.getSelectedIndex() > 0;
        boolean niOk = !ni2.getText().trim().isEmpty();
        boolean pnOk = !pn2.getText().trim().isEmpty();
        boolean paOk = !pa2.getText().trim().isEmpty();
        boolean passOk = !new String(pass2.getPassword()).trim().isEmpty();
        boolean tlOk = !tl2.getText().trim().isEmpty();
        boolean ceOk = !ce2.getText().trim().isEmpty();
        boolean fotoOk = archivoSeleccionado != null;

        btnguardarinfo.setEnabled(tipoIdOk && niOk && pnOk && paOk && passOk && tlOk && ceOk && fotoOk);
    }

    public static void main(String[] args) {
        new CrearUsuarios();
    }
}