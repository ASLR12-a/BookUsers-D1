package versionuni;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;

public class GeneradorPDF {

    public static void exportarUsuarios() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte PDF");
        fileChooser.setSelectedFile(new File("Reporte_Usuarios.pdf"));

        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoAGuardar = fileChooser.getSelectedFile();
            String ruta = archivoAGuardar.getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".pdf")) {
                ruta += ".pdf";
            }

            Document documento = new Document();
            try {
                PdfWriter.getInstance(documento, new FileOutputStream(ruta));

                // 1. ABRIR EL DOCUMENTO (¡Esto es vital!)
                documento.open();

                // 2. AGREGAR CONTENIDO
                documento.add(new Paragraph("BookUser - Lista de Usuarios Registrados\n\n"));

                PdfPTable tabla = new PdfPTable(5);
                tabla.addCell("ID");
                tabla.addCell("Documento");
                tabla.addCell("Nombre Completo");
                tabla.addCell("Username");
                tabla.addCell("Correo");

                for (Usuario u : ListaUsuarios.usuarios) {
                    tabla.addCell(u.id);
                    tabla.addCell(u.tipoId + " " + u.numId);
                    tabla.addCell(u.nombre + " " + u.apellido);
                    tabla.addCell(u.username);
                    tabla.addCell(u.correo);
                }

                documento.add(tabla);

                JOptionPane.showMessageDialog(null, "PDF generado correctamente en:\n" + ruta);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear PDF: " + e.getMessage());
            } finally {
                // 3. CERRAR EL DOCUMENTO AL FINAL (Siempre)
                if (documento != null && documento.isOpen()) {
                    documento.close();
                }
            }
        }
    }