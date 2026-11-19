package versionuni;

public class Usuario {
    public String id;
    public String tipoId;
    public String numId;
    public String nombre;
    public String apellido;
    public String username;
    public String password;
    public String telefono;
    public String correo;
    public String foto;

    @Override
    public String toString() {
        String nombreCompleto = ((nombre != null ? nombre : "") + " " + (apellido != null ? apellido : "")).trim();
        String user = (username != null && !username.isEmpty()) ? username : "sin usuario";
        return "ID " + id + " - " + nombreCompleto + " (" + user + ")";
    }
}
