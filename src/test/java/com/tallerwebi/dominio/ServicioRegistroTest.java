package com.tallerwebi.dominio;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServicioRegistroTest {

    /*
    si hay usuario y contraseña el rgistro es exitoso
    si la contraseña tiene menos de 5 caracteres el registro falla
     */

    ServicioRegistro servicioRegistro = new ServicioRegistroImpl();
    @Test
    public void siHayEmailYPasswordElRegistroEsExitoso() {
        givenUsuarioNoExiste();
        Usuario usuarioCreado = whenRegistroUsuario("flor@gmail.com", "123456");
        thenElRegistroEsExitoso(usuarioCreado);
    }

    private void thenElRegistroEsExitoso(Usuario usuarioCreado) {

        assertThat(usuarioCreado, is(notNullValue()));
    }

    private Usuario whenRegistroUsuario(String email, String password) {
//        datosRegistro.setMail(email);
//        datosRegistro.setPassword(password);
        return servicioRegistro.registrar(email, password);
    }

    private void givenUsuarioNoExiste() {
    }

    @Test
    public void siLaPasswordTieneMenosDeCincoCaracteresElRegistroFalla() {
        givenUsuarioNoExiste();
        assertThrows(PasswordLongitudIncorrectaException.class, ()->
                servicioRegistro.registrar("flor@gmail.com", "1234"));
        //Usuario usuarioCreado = whenRegistroUsuario("flor@gmail.com", "1234");
        //thenElRegistroFalla(usuarioCreado);

    }

    private void thenElRegistroFalla(Usuario usuarioCreado) {

        assertThat(usuarioCreado, is(nullValue()));
    }

    ///version con DTO
//    @Test
//    public void siExisteUsuarioConMismoMailElRegistroFalla() {
//        givenExisteUsuario(email, password);
//        assertThrows(UsuarioExistente.class, ()-> whenRegistroUsuario(email, password));
////        Usuario usuarioCreado = whenRegistroUsuario(email, password);
////        thenElRegistroFalla(usuarioCreado);
//    }
//
//    private void givenExisteUsuario(String email, String password) {
//        whenRegistroUsuario(email, password);
//    }
}
