package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Farmacia;
import com.tallerwebi.dominio.Localidad;
import com.tallerwebi.dominio.RepositorioFarmacia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioFarmaciaImpl implements RepositorioFarmacia {


    @Autowired
    SessionFactory sessionFactory;

    /*
    * SELECT * FROM Farmacia WHERE nombre like 'farma%'
    *
    * */


    @Override
    public List<Farmacia> buscarFarmaciasPorNombre(String nombre) {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Farmacia.class)
                .add(Restrictions.like("nombre",nombre + "%"))
                .list();
    }

    /*
    *
    * SELECT * FROM Farmacia f
    * join Direccion d ON f.idDireccion = d.id
    * WHERE d.calle = "rivadavia"
    *
    * */


    @Override
    public List<Farmacia> buscarFarmaciasPorCalle(String calle) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Farmacia.class)
                .createAlias("direccion", "d")
                .add(Restrictions.eq("d.calle",calle))
                .list();
    }


    /*
    *
    * select * from Farmacia f
    * join Direccion d on f.idDireccion = d.id
    * join Localidad l on d.idLocalidad = l.id
    * where l.nombre = 'san justo'
    *
    * */
    @Override
    public List<Farmacia> buscarFarmaciasPorNombreLocalidad(String nombre) {
        var session = sessionFactory.getCurrentSession();
        List<Farmacia> farmacias = session.createCriteria(Farmacia.class)
                .createAlias("direccion", "d")
                .createAlias("d.localidad", "l")
                .add(Restrictions.eq("l.nombre", nombre))
                .list();
        return farmacias;

    }

    @Override
    public List<Farmacia> buscarFarmaciasPorLocalidad(Localidad localidad) {
        var session = sessionFactory.getCurrentSession();
        return session.createCriteria(Farmacia.class)
                .createAlias("direccion", "d")
                .add(Restrictions.eq("d.localidad",localidad ))
                .list();


    }
}
