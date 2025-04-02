package com.tallerwebi;

import com.tallerwebi.dominio.CajaFuerte;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class CajaFuerteTest {

    /*
    * 1. Cuando se crea la caja fuerte debe estar abierta
    * 2. No puedo cerrar la caja sin un codigo
    * 3. Para abrir la caja fuerte tengo que ingresar el codigo con el que la cerre
    * */


    @Test
    public void cuandoCreoUnaCajaFuerteTieneQueEstarAbierta(){

        //GIVEN -> preparacion
        givenNoExisteUnaCajaFuerte();
        //WHEN -> ejecucion
        CajaFuerte caja = whenCreoLaCajaFuerte();
        //THEN -> comprobacion
        thenLaCajaFuerteEstaAbierta(caja);

    }

    private void givenNoExisteUnaCajaFuerte() {
    }
    private CajaFuerte whenCreoLaCajaFuerte(){
        CajaFuerte caja = new CajaFuerte();
        return caja;
    }
    private void thenLaCajaFuerteEstaAbierta(CajaFuerte caja) {

        assertThat(caja.estaAbierta(), equalTo(true));
    }

    @Test
    public void siCierroLaCajaConCodigoDeberiaEstarCerrada(){
        CajaFuerte caja = givenTengoUnaCajaFuerte();
        whenCierroLaCajaFuerte(caja, "1234");
        thenLaCajaFuerteEstaCerrada(caja);
    }

    private CajaFuerte givenTengoUnaCajaFuerte() {
        CajaFuerte caja = new CajaFuerte();
        return caja;
    }

    private void whenCierroLaCajaFuerte(CajaFuerte caja, String codigo) {
        caja.cerrar(codigo);
    }

    private void thenLaCajaFuerteEstaCerrada(CajaFuerte caja) {
        assertThat(caja.estaAbierta(), equalTo(false));
    }

    @Test
    public void siCierroLaCajaSinCodigoDeberiaEstarAbierta(){
        CajaFuerte caja = givenTengoUnaCajaFuerte();
        whenCierroLaCajaFuerte(caja, "");
        thenLaCajaFuerteEstaAbierta(caja);
    }

    @Test
    public void puedoAbrirLaCajaFuerteConElCodigoConElQueLaCerre(){
        CajaFuerte caja = givenTengoUnaCajaFuerte();
        String codigo = givenTengoUnCodigo();
        givenLaCajaEstaCerrada(caja, codigo);
        whenAbroLaCajaFuerte(caja, codigo);
        thenLaCajaFuerteEstaAbierta(caja);
    }

    private void givenLaCajaEstaCerrada(CajaFuerte cajaFuerte, String codigo) {
        cajaFuerte.cerrar(codigo);
    }

    private String givenTengoUnCodigo() {
        return "1234";
    }

    private void whenAbroLaCajaFuerte(CajaFuerte caja, String codigo) {
        caja.abrir(codigo);
    }

}
