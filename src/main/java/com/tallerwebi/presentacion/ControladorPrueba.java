package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPrueba {

    /*
    * definir URL
    * asociar a un metodo
    * */

    @RequestMapping("/saludo")
    public ModelAndView irASaludo(){

        ModelMap modelo = new ModelMap();
        modelo.put("nombre", "Flor");
        modelo.put("apellido", "Martin");
        return new ModelAndView("prueba", modelo);
    }


    @RequestMapping("/despedida")
    public ModelAndView irADespedida(){

        return new ModelAndView("despedida");
    }


}

