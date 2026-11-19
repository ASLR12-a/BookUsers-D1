package versionuni;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
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
                documento.open();


                Paragraph titulo = new Paragraph("BookUser - Lista de Usuarios Registrados\n\n");
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                documento.add(titulo);


                PdfPTable tabla = new PdfPTable(6);
                tabla.setWidthPercentage(100);


                float[] anchos = {0.5f, 1f, 2f, 1.5f, 2f, 1.5f};
                tabla.setWidths(anchos);

                tabla.addCell("ID");
                tabla.addCell("Documento");
                tabla.addCell("Nombre Completo");
                tabla.addCell("Username");
                tabla.addCell("Correo");
                tabla.addCell("Foto"); // Nueva columna

                for (Usuario u : ListaUsuarios.usuarios) {
                    tabla.addCell(u.id);
                    tabla.addCell(u.tipoId + " " + u.numId);
                    tabla.addCell(u.nombre + " " + u.apellido);
                    tabla.addCell(u.username);
                    tabla.addCell(u.correo);


                    PdfPCell celdaFoto;
                    try {

                        Image foto = Image.getInstance(u.foto);

                        foto.scaleToFit(80, 80);
                        celdaFoto = new PdfPCell(foto);
                        celdaFoto.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        celdaFoto.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    } catch (Exception e) {

                        celdaFoto = new PdfPCell(new Paragraph("Sin foto"));
                        celdaFoto.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        celdaFoto.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    }
                    tabla.addCell(celdaFoto);
                }

                documento.add(tabla);

                JOptionPane.showMessageDialog(null, "PDF generado correctamente en:\n" + ruta);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear PDF: " + e.getMessage());
            } finally {
                if (documento != null && documento.isOpen()) {
                    documento.close();
                }
            }
        }
    }
}