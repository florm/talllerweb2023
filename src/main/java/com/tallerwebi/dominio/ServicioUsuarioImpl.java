package com.tallerwebi.dominio;


import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {


    RepositorioUsuario repositorioUsuario;
    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario registrar(String email, String pass) {
        if(pass.length() < 6){
            throw new PasswordLongitudIncorrecta();
        }
        if(repositorioUsuario.buscar(email) != null){
            throw new UsuarioExistente();
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(pass);
        repositorioUsuario.guardar(usuario);
        return usuario;
    }
}
