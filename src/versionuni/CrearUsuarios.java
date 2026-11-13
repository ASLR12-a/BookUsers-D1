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

    // Variable para controlar si ya se subió la foto
    private File archivoSeleccionado = null;

    // GETTERS AND SETTERS
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

    // Constructor
    public CrearUsuarios() {
        JFrame ventana = new JFrame("Registrar usuario");
        ventana.setTitle("BookUser - Crear usuarios");
        ventana.setContentPane(PantallaSegundaria);
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

        // 1. Iniciar validación en tiempo real
        configurarValidacion();

        // Importar clase para la foto
        btnfotoguardar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                archivoSeleccionado = fileChooser.getSelectedFile();
                btnfotoguardar.setText("Foto cargada: " + archivoSeleccionado.getName());
                verificarCampos(); // Revisa si ya se puede activar Guardar info
            }
        });

        // Guardar info final
        btnguardarinfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Usuario guardado: " + pn2.getText());
        });
    }

    private void configurarValidacion() {
        // Desactivar el botón al abrir la ventana
        btnguardarinfo.setEnabled(false);

        DocumentListener listenerTexto = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { verificarCampos(); }
            @Override
            public void removeUpdate(DocumentEvent e) { verificarCampos(); }
            @Override
            public void changedUpdate(DocumentEvent e) { verificarCampos(); }
        };

        // Escuchar cambios en los campos obligatorios (sn2 y sa2 quedan excluidos por ser opcionales)
        ni2.getDocument().addDocumentListener(listenerTexto);
        pn2.getDocument().addDocumentListener(listenerTexto);
        pa2.getDocument().addDocumentListener(listenerTexto);
        pass2.getDocument().addDocumentListener(listenerTexto);
        tl2.getDocument().addDocumentListener(listenerTexto);
        ce2.getDocument().addDocumentListener(listenerTexto);

        // Escuchar cambios en el JComboBox
        Boxti.addActionListener(e -> verificarCampos());
    }

    private void verificarCampos() {
        // Índice > 0 porque el índice 0 es el espacio en blanco ("                ")
        boolean tipoIdOk = Boxti.getSelectedIndex() > 0;
        boolean niOk = !ni2.getText().trim().isEmpty();
        boolean pnOk = !pn2.getText().trim().isEmpty();
        boolean paOk = !pa2.getText().trim().isEmpty();
        boolean passOk = !new String(pass2.getPassword()).trim().isEmpty();
        boolean tlOk = !tl2.getText().trim().isEmpty();
        boolean ceOk = !ce2.getText().trim().isEmpty();
        boolean fotoOk = archivoSeleccionado != null;

        // Se activa únicamente si todos los obligatorios están llenos
        btnguardarinfo.setEnabled(tipoIdOk && niOk && pnOk && paOk && passOk && tlOk && ceOk && fotoOk);
    }

    public static void main(String[] args) {
        new CrearUsuarios();
    }
}