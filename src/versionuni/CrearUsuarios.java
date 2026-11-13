package versionuni;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CrearUsuarios extends Component {
    private JPanel PantallaSegundaria;
    private JLabel usi;
    private JLabel id;
    private JLabel ni;
    private JLabel pn;
    private JLabel sn;
    private JLabel pa;
    private JLabel sa;
    private JLabel usser;
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
    private JTextField usser2;
    private JPasswordField pass2;
    private JTextField tl2;
    private JTextField ce2;
    private JComboBox Boxti;
    private JLabel idlabel;
    private JButton btnfotoguardar;
    private JButton btnguardarinfo;

    //constructor

    public CrearUsuarios() {
        JFrame ventana = new JFrame("Registrar usuario");
        ventana.setContentPane(PantallaSegundaria);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        Boxti.addItem("CC");
        Boxti.addItem("TI");
        Boxti.addItem("CE");
        Boxti.addItem("PA");


        // importar clase para la foto
        btnfotoguardar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog (this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
            }
        });

        // guardar info final
        btnguardarinfo.addActionListener (e -> {
            JOptionPane.showMessageDialog(null, "Usuario guardado: " + pn2.getText());
        });
    }


    public static void main(String[] args) {
        new CrearUsuarios().setVisible(true);
    }

}
