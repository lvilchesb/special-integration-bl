package com.wholesale.specialintegration.service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wholesale.specialintegration.domain.ErrorMessage;
import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.exception.WholeException;
import com.wholesale.specialintegration.service.repository.WholeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WholeService {
    @Value("${spring.format.mail}")
    private String messageFormatMail;

    @Value("${spring.valida.mail}")
    private String validaMail;

    @Value("${spring.valida.password}")
    private String validaPassword;

    @Value("${spring.message.password}")
    private String messagePassword;

    @Value("${spring.not.user.register}")
    private String messageNotUserRegister;

    @Value("${spring.not.mail.register}")
    private String messageNotMailRegister;

    @Value("${spring.mail.register}")
    private String messageMailRegister;

    @Value("${spring.user.delete}")
    private String userDelete;

    @Value("${spring.user.active}")
    private String userActive;

    private Usuario user;

    private final WholeRepository repository;

    public Object getWhole(Usuario request) throws WholeException {
        if(Objects.nonNull(request)) {
            user = new Usuario();
            ErrorMessage errorMail = errorMail(request);
            if(Objects.nonNull(errorMail)) {
                return errorMail;
            }
            user = repository.searchEmail(request.getCorreo());
            if (Objects.isNull(user)) {
                ErrorMessage error = new ErrorMessage();
                error.setMessage(messageNotUserRegister);
                return error;
            }
        }
        return  repository.getById(user.getId());
    }

    public Object postWhole(Usuario request) throws WholeException {
        if(Objects.nonNull(request)) {
            user = new Usuario();
            ErrorMessage errorValidate = errorValidate(request);
            if(Objects.nonNull(errorValidate)) {
                return errorValidate;
            }
            user = repository.searchEmail(request.getCorreo());
            if (Objects.isNull(user)) {
                user = repository.saveAndFlush(request);
                user.getTelefonos().forEach(phone -> phone.setUserId(user));
                user = repository.save(request);
            } else {
                ErrorMessage error = new ErrorMessage();
                error.setMessage(messageMailRegister);
                return error;
            }
        }
        return user;
        
    }

    public Object putWhole(Usuario request) throws WholeException {
        if(Objects.nonNull(request)) {
            user = new Usuario();
            user = repository.searchEmail(request.getCorreo());
            if (Objects.nonNull(user)) {
                ErrorMessage errorValidate = errorValidate(request);
                if(Objects.nonNull(errorValidate)) {
                    return errorValidate;
                }
                user.setContrasena(request.getContrasena());
                user.setCorreo(request.getCorreo());
                user.setNombre(request.getNombre());
                user.setActivo(request.getActivo());
                user.setTelefonos(new ArrayList<>());
                user.getTelefonos().addAll(request.getTelefonos());
                user.getTelefonos().forEach(phone -> phone.setUserId(user));  
                user.setUltimoLogin(request.getUltimoLogin());
                user.setModificado(request.getModificado());
                user = repository.save(user);
            } else {
                ErrorMessage error = new ErrorMessage();
                error.setMessage(messageNotMailRegister);
                return error;
            }
        }
        return user;
    }

    public Object deleteWhole(Usuario request) throws WholeException {
        ErrorMessage error = null;
        if(Objects.nonNull(request)) {
            user = new Usuario();
            ErrorMessage errorMail = errorMail(request);
            if(Objects.nonNull(errorMail)) {
                return errorMail;
            }
            user = repository.searchEmail(request.getCorreo());
            if (Objects.isNull(user)) {
                error = new ErrorMessage();
                error.setMessage(messageNotUserRegister);
                return error;
            }
            repository.deleteById(user.getId());
            error = new ErrorMessage();
            error.setMessage(userDelete);
            }
            return error;
    }

    private boolean validaEmail(String email) {
        Pattern pattern = Pattern.compile(validaMail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean password(String password) {
        Pattern pattern = Pattern.compile(validaPassword);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    private ErrorMessage errorValidate(Usuario request) {
        if ((Objects.isNull(request.getCorreo()) || request.getCorreo().isEmpty()) || !validaEmail(request.getCorreo()) ) {
            ErrorMessage error = new ErrorMessage();
            error.setMessage(messageFormatMail);
            return error;
        
        }
        if (Objects.isNull(request.getContrasena()) || request.getContrasena().isEmpty() || !password(request.getContrasena())) {
                ErrorMessage error = new ErrorMessage();
                error.setMessage(messagePassword);
                return error;
            
        }
      
        return null;
    }
    
    private ErrorMessage errorMail(Usuario request) {
        if (Objects.isNull(request.getCorreo()) || request.getCorreo().isEmpty() ||  !validaEmail(request.getCorreo()) ) {
                ErrorMessage error = new ErrorMessage();
                error.setMessage(messageFormatMail);
                return error;
            
        }
        return null;
    }

}
