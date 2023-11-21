package com.wholesale.specialintegration.service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wholesale.specialintegration.domain.ErrorMessage;
import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.exception.WholeException;

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

    @Value("${spring.validate.request}")
    private String messageRequest;

    private final WholeServiceBD wholeServiceBD;
    
    private ErrorMessage error;

    public Object getWhole(Usuario request) throws WholeException {
        error = new ErrorMessage();
        if (Objects.isNull(request)) {
            error.setMessage(messageRequest);
            return error;
        } else {
            error = errorMail(request);
            if (Objects.nonNull(error)) {
                return error;
            }
        }
        return wholeServiceBD.getWhole(request);
    }

    public Object postWhole(Usuario request) throws WholeException {
        error = new ErrorMessage();
        if (Objects.isNull(request)) {
            error.setMessage(messageRequest);
            return error;
        } else { 
            error = errorMail(request);
            if (Objects.nonNull(error)) {
                return error;
            }
            error = errorpass(request);
            if (Objects.nonNull(error)) {
                return error;
            }
        }
        return wholeServiceBD.postWhole(request);
    }

    public Object putWhole(Usuario request) throws WholeException {
        error = new ErrorMessage();
        if (Objects.isNull(request)) {
            error.setMessage(messageRequest);
            return error;
        } else { 
            error = errorMail(request);
            if (Objects.nonNull(error)) {
                return error;
            }
            error = errorpass(request);
            if (Objects.nonNull(error)) {
                return error;
            }
        }
        return wholeServiceBD.putWhole(request);
    }

    public Object deleteWhole(Usuario request) throws WholeException {
        error = new ErrorMessage();
        if (Objects.isNull(request)) {
            error.setMessage(messageRequest);
            return error;
        } else {
            error = errorMail(request);
            if (Objects.nonNull(error)) {
                return error;
            }
        }
        return wholeServiceBD.deleteWhole(request);
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

    private ErrorMessage errorpass(Usuario request) {
    
        if (Objects.isNull(request.getContrasena()) || request.getContrasena().isEmpty()
                || !password(request.getContrasena())) {
            error = new ErrorMessage();
            error.setMessage(messagePassword);
            return error;
        }
        return null;
    }

    private ErrorMessage errorMail(Usuario request) {
        if (Objects.isNull(request.getCorreo()) || request.getCorreo().isEmpty() || !validaEmail(request.getCorreo())) {
            error = new ErrorMessage();
            error.setMessage(messageFormatMail);
            return error;
        }
        return null;
    }
}
