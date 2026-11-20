package versionuni;

import javax.swing.*;

public class Creditos extends JFrame {
    private JPanel panelPrincipalCreditos;
    private JLabel creditosDelCreadorButton;

    public Creditos() {
        setContentPane(panelPrincipalCreditos);
        setTitle("BookUsers - Creditos");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        creditosDelCreadorButton.setHorizontalAlignment(SwingConstants.CENTER);
        creditosDelCreadorButton.setText(
                "<html><div style='text-align: center;'>" +
                        "BookUsers v1.0<br><br>" +
                        "Integrantes:<br><br>" +
                        "-<br><br>" +
                        "Nombre: Leidy Niño<br>" +
                        "Codigo Estudiantil: UNP-QRMP-T7Z6<br>" +
                        "Correo: ninocastanedaleidy@gmail.com<br><br>" +
                        "-<br><br>" +
                        "Nombre: Adrian Lopez<br>" +
                        "Codigo Estudiantil: UNP-45L8-9PQ9<br>" +
                        "Correo: adriansneyder12@gmail.com<br><br>" +
                        "Materia: Poo - Año: 2026<br><br>" +
                        "-" +
                        "</div></html>"
        );
    }
}