package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioUsuario1;
import com.tallerwebi.dominio.ServicioUsuario1Impl;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class    ControladorRegistroTest {

    /*
    * 1. Usuario necesita mail y password para registrarse
    *
    * */

    ServicioUsuario1 servicioUsuario1 = mock(ServicioUsuario1Impl.class);
    ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuario1);

    @Test
    public void siExisteEmailYPasswordElRegistroEsExitoso(){

        //preparacion -> given
        givenNoExisteUsuario();
        //ejecucion -> when
        ModelAndView mav =  whenRegistroUsuario("flor@gmail.com");
        //comprobacion -> then
        thenElRegistroEsExitoso(mav);
    }

    private void givenNoExisteUsuario() {
    }

    private ModelAndView whenRegistroUsuario(String email) {
        ModelAndView mav = controladorRegistro.registrar(email);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void siElEmailEstaVacioElRegistroFalla(){

        //preparacion -> given
        givenNoExisteUsuario();
        //ejecucion -> when
        String emailVacio = "";
        ModelAndView mav =  whenRegistroUsuario(emailVacio);
        //comprobacion -> then
        thenElRegistroFalla(mav, "El email es obligatorio");
    }

    private void thenElRegistroFalla(ModelAndView mav, String mensaje) {
        assertThat(mav.getViewName(), equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("error").toString(), equalToIgnoringCase(mensaje));
    }

    @Test
    public void siLasPasswordSonDistintasElRegistroFalla(){

    }

    @Test
    public void siExisteUsuarioConEmailDelRegistroElRegistroFalla(){
        when(servicioUsuario1.registrar("flor@gmail.com", "")).thenThrow(UsuarioExistente.class);
        ModelAndView mav =  whenRegistroUsuario("flor@gmail.com");
        thenElRegistroFalla(mav, "El usuario ya existe");
    }

}
