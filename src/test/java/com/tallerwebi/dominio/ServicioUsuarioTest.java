package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioUsuarioTest {

    /*
    * si el usuario tiene email y password el registro es exitoso
    * si la password tiene menos de 6 caracteres el registro falla
    * si ya existe el email el registro falla
    *
    * */
    RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario);

    @Test
    public void siElUsuarioTieneEmailYPasswordElRegistroEsExitoso(){
        givenNoExisteUsuario();
        Usuario usuario = whenRegistroUsuario("flor@gmail.com", "12345678");
        thenElRegistroEsExitoso(usuario);

    }

    private void thenElRegistroEsExitoso(Usuario usuario) {
        assertThat(usuario, notNullValue() );
        verify(repositorioUsuario, times(1)).guardar(usuario);
    }

    private Usuario whenRegistroUsuario(String email, String pass) {
        return servicioUsuario.registrar(email, pass);
    }

    private void givenNoExisteUsuario() {
    }

    @Test
    public void siLaPasswordTieneMenosDeSeisCaracteresElRegistroFalla(){
        givenNoExisteUsuario();
//        Usuario usuario = whenRegistroUsuario("flor@gmail.com", "123");

        assertThrows(PasswordLongitudIncorrecta.class,
                ()-> whenRegistroUsuario("flor@gmail.com", "123"));

//        thenElRegistroFalla(usuario);
    }

    private void thenElRegistroFalla(Usuario usuario) {
        assertThat(usuario, nullValue() );
    }

    @Test
    public void siElUsuarioExisteElRegistroFalla(){
        givenExisteUsuario("flor@gmail.com", "12345678888");
        when(repositorioUsuario.buscar("flor@gmail.com")).thenReturn(new Usuario());
//        Usuario usuario = whenRegistroUsuario("flor@gmail.com", "12345678888");

        assertThrows(UsuarioExistente.class, ()-> whenRegistroUsuario("flor@gmail.com", "12345678888"));

//        thenElRegistroFalla(usuario);
    }

    private void givenExisteUsuario(String email, String pass) {
        servicioUsuario.registrar(email, pass);
    }

}
