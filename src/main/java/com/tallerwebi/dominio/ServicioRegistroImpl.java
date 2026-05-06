package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    @Override
    public Usuario registrar(String email, String password) {
        if(password.length()<5){
            throw new PasswordLongitudIncorrectaException();
        }
        return new Usuario();
    }

    //public Usuario registrar(String email, String password) {
        //return new Usuario();
    //}

//    List<Usuario> usuarios = new ArrayList<>();
//    private Usuario buscarUsuarioPorEmail(DatosRegistro datosRegistro){
//        Usuario usuarioBuscado = usuarios
//                .stream()
//                .filter(u -> u.getEmail().equals(datosRegistro.getMail())).findFirst().orElse(null);
//        return usuarioBuscado;
//    }

}
