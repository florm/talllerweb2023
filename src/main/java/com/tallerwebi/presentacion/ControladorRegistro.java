package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PasswordLongitudIncorrectaException;
import com.tallerwebi.dominio.ServicioRegistro;
import com.tallerwebi.dominio.ServicioRegistroImpl;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {


    private ServicioRegistro servicioRegistro;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

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
        try{
            servicioRegistro.registrar(email, password);
        }catch (PasswordLongitudIncorrectaException ex){
            ModelMap modelo = new ModelMap();
            modelo.put("mensajeError", "El password debe tener al menos cinco caracteres");
            return new ModelAndView("registro", modelo);
        }

        return new ModelAndView("login");
    }
}
