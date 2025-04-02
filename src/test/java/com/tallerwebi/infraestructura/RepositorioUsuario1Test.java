package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.RepositorioUsuario1;
import com.tallerwebi.dominio.Usuario;
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
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.IsNull.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioUsuario1Test {


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioUsuario1 repositorioUsuario1;


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnUsuario(){

        Usuario usuario = new Usuario();
        usuario.setEmail("flor@gmail.com");
        usuario.setPassword("12345");

        //when
        repositorioUsuario1.guardar(usuario);

        //then
        assertThat(usuario.getId(), notNullValue());


    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarUsuarioPorEmail(){
        //given
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("flor1@gmail.com");
        usuario1.setPassword("12345");
        sessionFactory.getCurrentSession().save(usuario1);

        //given
        Usuario usuario2 = new Usuario();
        usuario2.setEmail("flor2@gmail.com");
        usuario2.setPassword("5678");
        sessionFactory.getCurrentSession().save(usuario2);

        //when
        Usuario buscado = repositorioUsuario1.buscar("flor2@gmail.com");

        //then
        assertThat(buscado, notNullValue());
        assertThat(buscado.getPassword(), equalToIgnoringCase("5678"));
    }
}
