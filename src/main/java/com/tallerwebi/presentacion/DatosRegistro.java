package com.tallerwebi.presentacion;

public class DatosRegistro {

    private String email;
    private String password;
    private String repitePassword;

    public DatosRegistro(){

    }

    public DatosRegistro(String email, String password, String repitePassword){
        this.email = email;
        this.password = password;
        this.repitePassword = repitePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepitePassword() {
        return repitePassword;
    }

    public void setRepitePassword(String repitePassword) {
        this.repitePassword = repitePassword;
    }
}
