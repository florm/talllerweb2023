package com.tallerwebi.presentacion;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistro {


    public ModelAndView registrar(String email, String password) {

        if(email.isEmpty()){
            ModelMap modelo = new ModelMap();
            modelo.put("mensajeError", "El email es obligatorio");
            return new ModelAndView("registro", modelo);
        }
        if(password.isEmpty()){
            ModelMap modelo = new ModelMap();
            modelo.put("mensajeError", "El password es obligatorio");
            return new ModelAndView("registro", modelo);
        }
        return new ModelAndView("login");
    }
}
