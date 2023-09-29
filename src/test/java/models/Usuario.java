package models;

public class Usuario {
    private String usuario;
    private String password;

    public Usuario(){
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() { return usuario; }

    public String getPassword() {
        return password;
    }
}
