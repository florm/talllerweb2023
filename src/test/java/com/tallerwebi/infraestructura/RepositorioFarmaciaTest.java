package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Direccion;
import com.tallerwebi.dominio.Farmacia;
import com.tallerwebi.dominio.Localidad;
import com.tallerwebi.dominio.RepositorioFarmacia;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioFarmaciaTest {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    RepositorioFarmacia repo;
    
    @Test
    @Transactional
    @Rollback
    public void puedoObtenerUnaListaDeFarmaciasConLikeNombre() {
//        givenTengoUnaListaDeFarmacias();
        Localidad l1 = givenTengoUnaLocalidad("san justo");

        givenTengoUnaFarmacia("farmacity", "rivadavia", 123, l1);
        givenTengoUnaFarmacia("farmaonline", "rivadavia", 1232,l1);
        givenTengoUnaFarmacia("doctor ahorro", "viedma", 33, l1);
        givenTengoUnaFarmacia("natal", "ramon falcon", 123, l1);
        List<Farmacia> farmacias = whenBuscoFarmaciaPorNombre("farma");
        thenEncuentroFarmacias(farmacias, 2);

    }

    private Localidad givenTengoUnaLocalidad(String nombreLocalidad) {
        Localidad localidad = new Localidad();
        localidad.setNombre(nombreLocalidad);
        sessionFactory.getCurrentSession().save(localidad);
        return localidad;
    }

    private void givenTengoUnaFarmacia(String nombreFarmacia, String calle, Integer numero, Localidad localidad ){
        Direccion direccion = new Direccion(calle, numero, localidad);
        Farmacia farmacia1 = new Farmacia(nombreFarmacia, direccion);
        sessionFactory.getCurrentSession().save(farmacia1);
    }

    private List<Farmacia> whenBuscoFarmaciaPorNombre(String nombre) {
        List<Farmacia> farmacias =  repo.buscarFarmaciasPorNombre(nombre);
        return farmacias;
    }

    private void thenEncuentroFarmacias(List<Farmacia> farmacias, Integer cantidadEsperada) {
        assertThat(farmacias.size(), equalTo(cantidadEsperada));
    }


    @Test
    @Transactional
    @Rollback
    public void puedoBuscarFarmaciasPorCalle() {
        Localidad l1 = givenTengoUnaLocalidad("san justo");
        givenTengoUnaFarmacia("farmacity", "rivadavia", 123, l1);
        givenTengoUnaFarmacia("farmaonline", "rivadavia", 1232, l1);
        givenTengoUnaFarmacia("doctor ahorro", "viedma", 33, l1 );
        givenTengoUnaFarmacia("natal", "rivadavia", 123, l1);
        List<Farmacia> farmacias = whenBuscoFarmaciaPorCalle("rivadavia");
        thenEncuentroFarmacias(farmacias, 3);

    }

    private List<Farmacia> whenBuscoFarmaciaPorCalle(String calle) {
        List<Farmacia> farmacias = repo.buscarFarmaciasPorCalle(calle);
        return farmacias;
    }
    
    @Test
    @Transactional
    @Rollback
    public void puedoBuscarFarmaciasPorNombreDeLocalidad() {
        Localidad sanJusto = givenTengoUnaLocalidad("san justo");
        Localidad haedo = givenTengoUnaLocalidad("haedo");

        givenTengoUnaFarmacia("farmacity", "rivadavia", 123, sanJusto);
        givenTengoUnaFarmacia("farmaonline", "rivadavia", 1232, haedo);
        givenTengoUnaFarmacia("doctor ahorro", "viedma", 33, haedo);
        givenTengoUnaFarmacia("natal", "rivadavia", 123, haedo);

        List<Farmacia> farmacias = whenBuscoFarmaciaPorNombreLocalidad(sanJusto.getNombre());
        thenEncuentroFarmacias(farmacias, 1);
    }

    private List<Farmacia> whenBuscoFarmaciaPorNombreLocalidad(String nombre) {
        return repo.buscarFarmaciasPorNombreLocalidad(nombre);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarFarmaciasPorLocalidad() {
        Localidad sanJusto = givenTengoUnaLocalidad("san justo");
        Localidad haedo = givenTengoUnaLocalidad("haedo");

        givenTengoUnaFarmacia("farmacity", "rivadavia", 123, sanJusto);
        givenTengoUnaFarmacia("farmaonline", "rivadavia", 1232, haedo);
        givenTengoUnaFarmacia("doctor ahorro", "viedma", 33, haedo);
        givenTengoUnaFarmacia("natal", "rivadavia", 123, haedo);

        List<Farmacia> farmacias = whenBuscoFarmaciaPorLocalidad(haedo);
        thenEncuentroFarmacias(farmacias, 3);
    }

    private List<Farmacia> whenBuscoFarmaciaPorLocalidad(Localidad localidad) {
        return repo.buscarFarmaciasPorLocalidad(localidad);
    }



}
