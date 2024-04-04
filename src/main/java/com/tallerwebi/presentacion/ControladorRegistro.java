package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {


    ServicioLogin servicioLogin;

    @Autowired
    public ControladorRegistro(ServicioLogin servicioLogin){
        this.servicioLogin = servicioLogin;
    }

    public ModelAndView registrar(String email) {
        ModelMap modelo = new ModelMap();
        String nombreVista = "";
        if(email.isEmpty()){
            modelo.put("mensaje", "El registro fallo");
            nombreVista = "registro";
        }else{
            try{
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setEmail("flor@gmail.com");
                servicioLogin.registrar(nuevoUsuario);
                modelo.put("mensaje", "El registro fue exitoso");
                nombreVista="login";
            }
            catch(UsuarioExistente ex){
                modelo.put("mensaje", "El registro fallo");
                nombreVista = "registro";
            }


        }
        return new ModelAndView(nombreVista, modelo);
    }
}
