package versionuni;

import java.io.*;
import java.util.ArrayList;

public class ListaUsuarios {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static void guardar() {
        try {

            PrintWriter escritor = new PrintWriter(new FileWriter("C:\\Users\\FLIA LOPEZ\\BookUsers-D1\\usuarios.txt"));
            for (Usuario u : usuarios) {
                escritor.println(u.id + ";" + u.tipoId + ";" + u.numId + ";" + u.nombre + ";"
                        + u.apellido + ";" + u.username + ";" + u.password + ";"
                        + u.telefono + ";" + u.correo + ";" + u.foto);
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo guardar");
        }
    }

    public static void cargar() {

        File archivo = new File("C:\\Users\\FLIA LOPEZ\\BookUsers-D1\\usuarios.txt");

        if (!archivo.exists()) return;

        usuarios.clear();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 10) {
                    Usuario u = new Usuario();
                    u.id = datos[0];
                    u.tipoId = datos[1];
                    u.numId = datos[2];
                    u.nombre = datos[3];
                    u.apellido = datos[4];
                    u.username = datos[5];
                    u.password = datos[6];
                    u.telefono = datos[7];
                    u.correo = datos[8];
                    u.foto = datos[9];
                    usuarios.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar");
        }
    }
}