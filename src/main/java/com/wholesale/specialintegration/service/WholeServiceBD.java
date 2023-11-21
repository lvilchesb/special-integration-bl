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
public class WholeServiceBD {

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
        user = new Usuario();
        user = repository.searchEmail(request.getCorreo());
        if (Objects.isNull(user)) {
            ErrorMessage error = new ErrorMessage();
            error.setMessage(messageNotUserRegister);
            return error;
        }
        return repository.getById(user.getId());
    }

    public Object postWhole(Usuario request) throws WholeException {
        user = new Usuario();
        user = repository.searchEmail(request.getCorreo());
        if (Objects.isNull(user)) {
            user = repository.saveAndFlush(request);
            savePhones();
        } else {
            ErrorMessage error = new ErrorMessage();
            error.setMessage(messageMailRegister);
            return error;
        }
        return user;
    }

    private void savePhones() {
        if (Objects.nonNull(user)) {
            user.getTelefonos().forEach(phone -> phone.setUserId(user));
            user = repository.save(user);
        }
    }

    public Object putWhole(Usuario request) throws WholeException {

        user = new Usuario();
        user = repository.searchEmail(request.getCorreo());
        if (Objects.nonNull(user)) {
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
        return user;
    }

    public Object deleteWhole(Usuario request) throws WholeException {
        ErrorMessage error = null;
        user = new Usuario();
        user = repository.searchEmail(request.getCorreo());
        if (Objects.isNull(user)) {
            error = new ErrorMessage();
            error.setMessage(messageNotUserRegister);
            return error;
        }
        repository.deleteById(user.getId());
        return user;
    }
}
