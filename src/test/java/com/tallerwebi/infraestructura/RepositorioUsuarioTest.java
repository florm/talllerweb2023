package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.RepositorioUsuario;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioUsuarioTest {

    /*
    *
    * 1. Se puede buscar usuario por mail
    * 2. Se puede buscar usuario por rol
    *
    * */


    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void puedoObtenerUsuariosporRol(){

        givenExisteUsuario("admin");
        givenExisteUsuario("admin");
        givenExisteUsuario("admin");
        givenExisteUsuario("operador");

        List<Usuario> usuariosBuscados = whenBuscoUsuariosPorRol("admin");
        thenObtengoUsuarios(usuariosBuscados);
    }

    private void thenObtengoUsuarios(List<Usuario> usuariosBuscados) {
        assertThat(usuariosBuscados.size(), equalTo(3));
    }

    private List<Usuario> whenBuscoUsuariosPorRol(String rol) {
        return repositorioUsuario.buscarPorRol(rol);
    }

    private void givenExisteUsuario(String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        sessionFactory.getCurrentSession().save(usuario);

    }



}
