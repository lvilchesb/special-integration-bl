
package com.wholesale.specialintegration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wholesale.specialintegration.domain.ErrorMessage;
import com.wholesale.specialintegration.domain.Telefono;
import com.wholesale.specialintegration.domain.TelefonoDTO;
import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.domain.UsuarioDTO;
import com.wholesale.specialintegration.service.WholeService;
import com.wholesale.specialintegration.service.repository.WholeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WholeImpl implements WholeService {

    private final WholeRepository repository;

    @Autowired
    public WholeImpl( WholeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object getWhole(UsuarioDTO request) {
        Usuario user = null;
        if(!validaEmail(request.getCorreo())) {
            ErrorMessage error = new ErrorMessage();
            error.setMessage("El correo no tiene un formato valido");
            return error; 
        }
         user = repository.searchEmail(request.getCorreo());
         if(user==null) {
             ErrorMessage error = new ErrorMessage();
             error.setMessage("El Usuario no se encuentra registrado");
             return error;
         }
         user = repository.getById(user.getId());
        return user;
    }

    @Override
    public Object postWhole(UsuarioDTO request) {
        Usuario user = null;
       if(!password(request.getContrasena())) {
           ErrorMessage error = new ErrorMessage();
           error.setMessage("Se requiere que la contraseña tenga de 4 a 8 caracteres y debe contener números, letras minúsculas y mayúsculas");
           return error;
       }
       if(!validaEmail(request.getCorreo())) {
           ErrorMessage error = new ErrorMessage();
           error.setMessage("El correo no tiene un formato valido");
           return error; 
       }
        user = repository.searchEmail(request.getCorreo());
        if(user==null) {
            user = new Usuario();
            user.setNombre(request.getNombre());
            user.setActivo("true");
            user.setContrasena(request.getContrasena());
            user.setCreado(new Date().toString());
            user.setUltimoLogin(new Date().toString());
            user.setCorreo(request.getCorreo());
            user= repository.saveAndFlush(user);
            
            user.setTelefonos(new ArrayList<>());
            for(TelefonoDTO phone: request.getTelefonos()){
                Telefono tele= new Telefono();
                tele.setCodigoCiudad(phone.getCodigoCiudad());
                tele.setCodigoPais(phone.getCodigoPais());
                tele.setNumero(phone.getNumero());
                tele.setUserId(user);
                user.getTelefonos().add(tele);
            }
            user= repository.saveAndFlush(user); 
        }else {
            ErrorMessage error = new ErrorMessage();
            error.setMessage("El correo ya está registrado");
            return error;
        }
        return user;
    }
    
    @Override
    public Object putWhole(UsuarioDTO request) {
     
        Usuario user = null;
         user = repository.searchEmail(request.getCorreo());
         if(user!=null) {             
             if(request.getContrasena()!=null && !request.getContrasena().isEmpty()) {  
                 if(!password(request.getContrasena())) {
                     ErrorMessage error = new ErrorMessage();
                     error.setMessage("Se requiere que la contraseña tenga de 4 a 8 caracteres y debe contener números, letras minúsculas y mayúsculas");
                     return error;
                 }
                 user.setContrasena(request.getContrasena());
             }
             if(request.getCorreo()!=null && !request.getCorreo().isEmpty()) {  
                 if(!validaEmail(request.getCorreo())) {
                     ErrorMessage error = new ErrorMessage();
                     error.setMessage("El correo no tiene un formato valido");
                     return error; 
                 }
                 user.setCorreo(request.getCorreo()); 
             }
             user.setNombre(request.getNombre());
             user.setActivo(request.getActivo());
                        
             setUpadatePhones(request, user);
             user.setUltimoLogin(new Date().toString());
             user.setModificado(new Date().toString());
             user= repository.saveAndFlush(user);  
         }else {
             ErrorMessage error = new ErrorMessage();
             error.setMessage("El correo no está registrado");
             return error;
         }
         return user;
    }

    private void setUpadatePhones(UsuarioDTO request, Usuario user) {
        for(TelefonoDTO phone: request.getTelefonos()){
             for(Telefono telefo: user.getTelefonos()) {
                 if(telefo.getPhoneId().intValue() == phone.getPhoneId().intValue()) {                      
                     telefo.setCodigoCiudad(phone.getCodigoCiudad());
                     telefo.setCodigoPais(phone.getCodigoPais());
                     telefo.setNumero(phone.getNumero());
                 }
             }
         }
    }

    @Override
    public Object deleteWhole(UsuarioDTO request) {
        Usuario user = null;
        if(!validaEmail(request.getCorreo())) {
            ErrorMessage error = new ErrorMessage();
            error.setMessage("El correo no tiene un formato valido");
            return error; 
        }
         user = repository.searchEmail(request.getCorreo());
         if(user==null) {
             ErrorMessage error = new ErrorMessage();
             error.setMessage("El Usuario no se encuentra registrado");
             return error;
         }

         repository.deleteById(user.getId());
         
         ErrorMessage error = new ErrorMessage();
         error.setMessage("El Usuario borro con exito");
         return error;
    }
    
    private boolean validaEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    private boolean password (String password) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,10}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
