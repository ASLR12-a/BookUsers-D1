package versionuni;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.Component;
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
                tabla.addCell("Foto");

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

    public static void exportarUsuarioDetalle(Usuario u, Component padre) {
        if (u == null) {
            JOptionPane.showMessageDialog(padre, "No hay información del usuario para exportar.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Detalle de Usuario en PDF");
        fileChooser.setSelectedFile(new File("Usuario_" + u.username + ".pdf"));

        int seleccion = fileChooser.showSaveDialog(padre);

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

                Paragraph titulo = new Paragraph("BookUser - Detalle de Usuario\n\n");
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                documento.add(titulo);

                if (u.foto != null && !u.foto.trim().isEmpty()) {
                    try {
                        Image foto = Image.getInstance(u.foto.trim());
                        foto.scaleToFit(120, 120);
                        foto.setAlignment(Image.ALIGN_CENTER);
                        documento.add(foto);
                        documento.add(new Paragraph("\n"));
                    } catch (Exception e) {
                        documento.add(new Paragraph("Foto: No disponible\n\n"));
                    }
                }

                documento.add(new Paragraph("ID: " + u.id));
                documento.add(new Paragraph("Documento: " + u.tipoId + " " + u.numId));
                documento.add(new Paragraph("Nombre completo: " + u.nombre + " " + u.apellido));
                documento.add(new Paragraph("Username: " + u.username));
                documento.add(new Paragraph("Teléfono: " + u.telefono));
                documento.add(new Paragraph("Correo: " + u.correo));

                JOptionPane.showMessageDialog(padre, "PDF del usuario generado en:\n" + ruta);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(padre, "Error al crear PDF: " + e.getMessage());
            } finally {
                if (documento != null && documento.isOpen()) {
                    documento.close();
                }
            }
        }
    }
}