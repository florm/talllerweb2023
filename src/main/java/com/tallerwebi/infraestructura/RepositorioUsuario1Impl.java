package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario1;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioUsuario1Impl implements RepositorioUsuario1 {


    SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuario1Impl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    /*
    *
    * SELECT * FROM Usuario WHERE email = "..."
    *
    * */


    @Override
    public Usuario buscar(String email) {

        Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();


    }

    @Override
    public void guardar(Usuario usuario) {

        Session session = sessionFactory.getCurrentSession();
        session.save(usuario);
    }


    public List<Usuario> buscarPorRol(String rol) {

        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Usuario.class)
                .add(Restrictions.eq("rol", rol))
                .list();


    }
}
