package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ControladorRegistroTest {
    /*
    * 1.Necesito un email y una contraseña para que el usuario se pueda registrar
    *
    * */

    ServicioLogin servicioLogin = mock(ServicioLogin.class);
    ControladorRegistro controladorRegistro = new ControladorRegistro(servicioLogin);
    @Test
    public void siIngresoEmailYClaveSeRegistraCorrectamente(){

        //1. given o dado que-> preparacion
        //2. when o cuando -> ejecucion
        ModelAndView mav = whenRegistroUsuario("flor@gmail.com");
        //3 then -> validacion
        thenElRegistroEsExitoso(mav);
    }


    @Test
    public void siElEmailEstaVacioElRegistroFalla(){

        //1. given o dado que-> preparacion
        //2. when o cuando -> ejecucion
        ModelAndView mav = whenRegistroUsuario("");
        //3 then -> validacion
        thenElRegistroFalla(mav);
    }

    private void thenElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getModel().get("mensaje").toString(), equalToIgnoringCase("El registro fallo"));
        assertThat(mav.getViewName(), equalToIgnoringCase("registro"));
    }

    private ModelAndView whenRegistroUsuario(String email) {
        ModelAndView mav = controladorRegistro.registrar(email);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
     //mensaje El registro fue exitoso
        //voy a vista login
        assertThat(mav.getModel().get("mensaje").toString(), equalToIgnoringCase("El registro fue exitoso"));
        assertThat(mav.getViewName(), equalToIgnoringCase("login"));
    }

    @Test
    public void siElUsuarioExisteElRegistroFalla() throws UsuarioExistente {

        doThrow(UsuarioExistente.class)
                .when(servicioLogin).registrar(any());

        ModelAndView mav = whenRegistroUsuario("flor@gmail.com");

        thenElRegistroFalla(mav);
    }

}
