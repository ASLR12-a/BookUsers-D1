package versionuni;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListaUsuarios {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void guardar() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter("usuarios.txt"));
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
}