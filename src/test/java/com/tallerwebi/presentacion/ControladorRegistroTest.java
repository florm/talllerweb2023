package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ControladorRegistroTest {

    /*
    para registrar a un usuario necesito email y contraseña.
    si no existe email el registro deberia fallar
    si no existe contraseña el registro deberia fallar

     */

    private final String email = "flor@gmail.com";
    private final String password = "1234";

    @Test
    public void conEmailYPasswordElRegistroEsExitoso() {
        //preparacion --> given()
        givenNoExiteUsuario();
        //ejecucion --> when()
        ModelAndView mav = whenRegistroUsuario(email, password);
        //comprobacion o validacion --> then()
        thenElRegistroEsExitoso(mav);

    }

    @Test
    public void siNoHayEmailElRegistroFalla() {
        //preparacion --> given()
        givenNoExiteUsuario();

        //ejecucion
        ModelAndView mav = whenRegistroUsuario("", password);
        thenElRegistroFalla(mav, "El email es obligatorio");
    }

    @Test
    public void siNoHayPasswordElRegistroFalla() {
        givenNoExiteUsuario();

        //ejecucion
        ModelAndView mav = whenRegistroUsuario(email, "");
        thenElRegistroFalla(mav, "El password es obligatorio");
    }

    private void thenElRegistroFalla(ModelAndView mav, String mensaje) {
        assertThat(mav.getViewName(), equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("mensajeError").toString(),
                equalToIgnoringCase(mensaje));
    }

    private void givenNoExiteUsuario() {

    }

    private ModelAndView whenRegistroUsuario(String email, String password) {
        ControladorRegistro controladorRegistro = new ControladorRegistro();
        ModelAndView mav = controladorRegistro.registrar(email, password);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("login"));
    }



}
