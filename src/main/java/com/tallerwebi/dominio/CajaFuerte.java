package com.tallerwebi.dominio;

public class CajaFuerte {

    private boolean abierta;

    public CajaFuerte(){
        abierta = true;
    }
    public boolean estaAbierta() {
        return abierta;
    }

    public void cerrar(String codigo) {
        if(codigo.isEmpty()){
            abierta = true;
        }else{
            abierta = false;
        }
    }

    public void abrir(String codigo) {
        abierta = true;
    }
}
