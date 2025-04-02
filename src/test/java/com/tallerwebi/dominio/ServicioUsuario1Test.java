package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioUsuario1Impl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioUsuario1Test {


    RepositorioUsuario1 repositorioUsuario1 = mock(RepositorioUsuario1Impl.class);
    ServicioUsuario1 servicioUsuario = new ServicioUsuario1Impl(repositorioUsuario1);

    @Test
    public void siExiteEmailYPasswordElRegistroEsExitoso(){

        //given

        //when
        Usuario usuarioCreado = whenRegistroUsuario("flor@gmail.com", "12345");

        //then
        thenElRegistroEsExitoso(usuarioCreado);
    }


    private Usuario whenRegistroUsuario(String email, String password) {
        Usuario usuarioCreado = servicioUsuario.registrar(email, password);
        return usuarioCreado;
    }

    private void thenElRegistroEsExitoso(Usuario usuarioCreado) {
        assertThat(usuarioCreado, notNullValue());
        verify(repositorioUsuario1, times(1)).guardar(usuarioCreado);
    }

    @Test
    public void siLaPasswordTieneMenosDeCintoCaracteresElRegistroFalla(){
        //given

        //when
//        Usuario usuarioCreado = whenRegistroUsuario("flor@gmail.com", "123");
        assertThrows(PasswordLongitudIncorrecta.class, ()-> whenRegistroUsuario("flor@gmail.com", "123"));
         //then
//        thenElRegistroFalla(usuarioCreado);
    }

    private void thenElRegistroFalla(Usuario usuarioCreado) {
        assertThat(usuarioCreado, nullValue());
    }

    @Test
    public void siYaExisteUsuarioConMismoMailElRegistroFalla(){

        ///given
        givenExisteUsuario("flor@gmail.com", "12345");
        when(repositorioUsuario1.buscar("flor@gmail.com")).thenReturn(new Usuario());

        //when
        Usuario usuarioCreado = whenRegistroUsuario("flor@gmail.com", "12345");

        //then
        thenElRegistroFalla(usuarioCreado);
    }

    private void givenExisteUsuario(String email, String password) {
        whenRegistroUsuario(email, password);
    }
}
