package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorEjercicios {


    @RequestMapping("/saludo")
    public ModelAndView irASaludo() {
        return new ModelAndView("saludo-form");
    }

    @RequestMapping("/mostrar-saludo")
    public ModelAndView mostrarSaludo(
            @RequestParam String nombre) {

        ModelMap model = new ModelMap();

        model.put("mensaje", "Hola " + nombre);

        return new ModelAndView(
                "saludo-resultado",
                model);
    }

    @RequestMapping("/productos")
    public ModelAndView irABuscarProducto() {

        return new ModelAndView(
                "buscar-producto");
    }

    @RequestMapping(value = "/buscar-producto")
    public ModelAndView buscarProducto(
            @RequestParam Integer id) {

        return new ModelAndView(
                "redirect:/producto/" + id);
    }

    @RequestMapping("/producto/{id}")
    public ModelAndView verProducto(
            @PathVariable Integer id) {

        ModelMap model = new ModelMap();

        model.put("id", id);

        return new ModelAndView(
                "producto", model);
    }

    @RequestMapping("/calculadora")
    public ModelAndView irACalculadora() {

        ModelMap model = new ModelMap();

        model.put("calculadora", new CalculadoraDTO());

        return new ModelAndView(
                "calculadora",
                model);
    }

    @RequestMapping(value = "/calcular", method = RequestMethod.POST)
    public ModelAndView calcular(
            @ModelAttribute CalculadoraDTO calculadora) {

        ModelMap model = new ModelMap();

        Double resultado = null;

        if (calculadora.getOperacion().equals("dividir")
                && calculadora.getNumero2() == 0) {

            model.put(
                    "error",
                    "No se puede dividir por cero");

        } else {

            switch (calculadora.getOperacion()) {

                case "sumar":
                    resultado = calculadora.getNumero1()
                            + calculadora.getNumero2();
                    break;

                case "restar":
                    resultado = calculadora.getNumero1()
                            - calculadora.getNumero2();
                    break;

                case "multiplicar":
                    resultado = calculadora.getNumero1()
                            * calculadora.getNumero2();
                    break;

                case "dividir":
                    resultado = calculadora.getNumero1()
                            / calculadora.getNumero2();
                    break;
            }

            model.put("resultado", resultado);
        }

        model.put("calculadora", calculadora);

        return new ModelAndView(
                "calculadora",
                model);
    }


}
