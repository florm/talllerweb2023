package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPrueba {

    /*
    * definir URL
    * asociar a un metodo
    * */

    @RequestMapping("/saludo1")
    public ModelAndView irASaludo(){

        ModelMap modelo = new ModelMap();
        modelo.put("nombre", "Flor");
        modelo.put("apellido", "Martin");
        modelo.put("datosSumaDto", new DatosSumaDto());
        return new ModelAndView("prueba", modelo);
    }


    @RequestMapping("/despedida")
    public ModelAndView irADespedida(){

        return new ModelAndView("despedida");
    }

    @RequestMapping(path = "/sumarConRequestParam")
    public ModelAndView sumar(@RequestParam("val1") Integer val1, @RequestParam("val2") Integer val2)
    {

        Integer resultado = val1 + val2;
        ModelMap modelo = new ModelMap();
        modelo.put("resultado",resultado);
        modelo.put("datosSumaDto", new DatosSumaDto());
        return new ModelAndView("prueba", modelo);
    }

    @RequestMapping(path = "/sumarConPathVariable/{val1}/{val2}")
    public ModelAndView sumarConPathVariable(@PathVariable("val1") Integer val1, @PathVariable("val2") Integer val2)
    {

        Integer resultado = val1 + val2;
        ModelMap modelo = new ModelMap();
        modelo.put("resultado",resultado);
        modelo.put("datosSumaDto", new DatosSumaDto());
        return new ModelAndView("prueba", modelo);
    }


    @RequestMapping(path = "/sumarConForm", method = RequestMethod.POST)
    public ModelAndView sumar(@ModelAttribute DatosSumaDto datosSumaDto)
    {

        Integer resultado = datosSumaDto.getInput1() + datosSumaDto.getInput2();
        ModelMap modelo = new ModelMap();
        modelo.put("resultado",resultado);
        return new ModelAndView("prueba", modelo);
    }


}

