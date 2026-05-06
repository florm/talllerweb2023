package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PasswordLongitudIncorrectaException;
import com.tallerwebi.dominio.ServicioRegistro;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ControladorRegistroTest {

    /*
    para registrar a un usuario necesito email y contraseña.
    si no existe email el registro deberia fallar
    si no existe contraseña el registro deberia fallar

     */

    private final String email = "flor@gmail.com";
    private final String password = "1234";

    ServicioRegistro servicioRegistro = mock(ServicioRegistro.class);

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
        ControladorRegistro controladorRegistro = new ControladorRegistro(servicioRegistro);
        ModelAndView mav = controladorRegistro.registrar(email, password);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("login"));
    }

    @Test
    public void siLaPasswordTieneMenosDeCincoCaraceresElRegistroFalla() {
        givenNoExiteUsuario();
        //Setea el comportamiento del mock (servicioRegistro)
        doThrow(PasswordLongitudIncorrectaException.class)
                .when(servicioRegistro).registrar("flor@gmail.com","1234");
        ModelAndView mav= whenRegistroUsuario("flor@gmail.com", "1234");
        thenElRegistroFalla(mav, "El password debe tener al menos cinco caracteres");
    }

    //v1
//    @Test
//    public void elRegistroFallaSiLaPasswordTieneMenosDe6Caracteres() {
//        givenNoExisteUsuario();
//        DatosRegistro datosRegistro = new DatosRegistro(email, passwordMenos6Caracteres);
//        ModelAndView modelAndView = whenRegistroUsuario(datosRegistro);
//        thenElRegistroFalla(modelAndView, "La contraseña debe tener al menos 6 caracteres");
//
//    }
    //v2
//    @Test
//    public void elRegistroFallaSiLaPasswordTieneMenosDe6Caracteres() {
//        givenNoExisteUsuario();
//        DatosRegistro datosRegistro = new DatosRegistro(email, passwordMenos6Caracteres);
//        doThrow(PasswordIncorrectaException.class)
//                .when(servicioRegistro).registrar(datosRegistro);
//        ModelAndView modelAndView = whenRegistroUsuario(datosRegistro);
//        thenElRegistroFalla(modelAndView, "La contraseña debe tener al menos 6 caracteres");
//
//    }

//    @Test
//    public void elRegistroFallaSiExisteUsuarioConMismoMail() {
//        DatosRegistro datosRegistro = new DatosRegistro(email, password);
//        givenExisteUsuario(datosRegistro);
//        doThrow(UsuarioExistente.class).when(servicioRegistro).registrar(datosRegistro);
//        ModelAndView mav = whenRegistroUsuario(datosRegistro);
//        thenElRegistroFalla(mav, "Ya existe un usuario con el mismo email");
//    }
//
//    private void givenExisteUsuario(DatosRegistro datosRegistro) {
//        controladorRegistro.registrar(datosRegistro);
//    }

}
