package com.wholesale.specialintegration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.wholesale.specialintegration.domain.ErrorMessage;
import com.wholesale.specialintegration.domain.Telefono;
import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.exception.WholeException;
import com.wholesale.specialintegration.service.repository.WholeRepository;

@RunWith(MockitoJUnitRunner.class)  
public class WholeServiceBDTest {
    @Mock
    private WholeRepository repository;
    @InjectMocks
    private WholeServiceBD wholeService;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(wholeService, "messageNotUserRegister", "messageNotUserRegister");        
        ReflectionTestUtils.setField(wholeService, "messageNotMailRegister", "messageNotMailRegister");
        ReflectionTestUtils.setField(wholeService, "messageMailRegister", "messageMailRegister");
        ReflectionTestUtils.setField(wholeService, "userDelete", "userDelete");
        ReflectionTestUtils.setField(wholeService, "userActive", "userActive");

    }

    @Test
    public void getWholeTest() throws WholeException {
        Usuario request = new Usuario();
        request.setCorreo("test@test.com");
        Usuario user = new Usuario();
        user.setId(1);
        user.setCorreo("test@test.com");
        user.setNombre("Test");
        when(repository.searchEmail(request.getCorreo())).thenReturn(user);
        when(repository.getById(user.getId())).thenReturn(user);
        Object response = wholeService.getWhole(request);
        assertNotNull(response);
    }

    @Test
    public void getWholeNotUserTest() throws WholeException {
        Usuario request = new Usuario();
        request.setCorreo("test@test.com");
        when(repository.searchEmail(request.getCorreo())).thenReturn(null);
        Object response = wholeService.getWhole(request);
        assertNotNull(response);
        assertTrue(response instanceof ErrorMessage);
        ErrorMessage error = (ErrorMessage) response;
    }


    @Test
    public void postWholeTest() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setCorreo("test@test.com");
        usuario.setNombre("Test");
        usuario.setContrasena("123456");
        usuario.setTelefonos(new ArrayList<>());

        when(repository.searchEmail(anyString())).thenReturn(null);
        when(repository.saveAndFlush(any(Usuario.class))).thenReturn(usuario);       
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Object response = wholeService.postWhole(usuario);

        assertNull(response);
    }

    @Test
    public void postWholeTestUserExists() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setCorreo("test@test.com");
        usuario.setNombre("Test");
        usuario.setContrasena("123456");
        usuario.setTelefonos(new ArrayList<>());

        when(repository.searchEmail(anyString())).thenReturn(usuario);

        Object response = wholeService.postWhole(usuario);

        assertNull(response);
    }

    @Test
    public void putWholeTest() throws WholeException {
        Usuario request = new Usuario();
        request.setCorreo("test@test.com");
        request.setContrasena("123456");
        request.setNombre("Test");
        request.setActivo("true");
        request.setTelefonos(new ArrayList<>());
        request.getTelefonos().add(new Telefono());
        request.setUltimoLogin(new Date().toString());
        request.setModificado(new Date().toString());

        Usuario user = new Usuario();
        user.setCorreo("test@test.com");
        user.setContrasena("123456");
        user.setNombre("Test");
        user.setActivo("true");
        user.setTelefonos(new ArrayList<>());
        user.getTelefonos().add(new Telefono());
        user.setUltimoLogin(new Date().toString());
        user.setModificado(new Date().toString());

        when(repository.searchEmail(request.getCorreo())).thenReturn(user);
        when(repository.save(user)).thenReturn(user);

        Object response = wholeService.putWhole(request);

        assertNotNull(response);
    }

    @Test
    public void putWholeTestUserNotFound() throws WholeException {
        Usuario request = new Usuario();
        request.setCorreo("test@test.com");
        request.setContrasena("123456");
        request.setNombre("Test");
        request.setActivo("true");
        request.setTelefonos(new ArrayList<>());
        request.getTelefonos().add(new Telefono());
        request.setUltimoLogin(new Date().toString());
        request.setModificado(new Date().toString());

        when(repository.searchEmail(request.getCorreo())).thenReturn(null);

        Object response = wholeService.putWhole(request);

        assertNotNull(response);
        assertTrue(response instanceof ErrorMessage);
        assertEquals("messageNotMailRegister", ((ErrorMessage) response).getMessage());
    }


    @Test
    public void deleteWholeTest() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setCorreo("correo@correo.com");
        usuario.setId(1);
        ErrorMessage error = new ErrorMessage();
        error.setMessage("messageNotUserRegister");
        when(repository.searchEmail(usuario.getCorreo())).thenReturn(usuario);
        assertEquals(error.getMessage(), ((ErrorMessage) wholeService.deleteWhole(usuario)).getMessage());
    }

    @Test
    public void deleteWholeTestNotFound() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setCorreo("correo@correo.com");
        ErrorMessage error = new ErrorMessage();
        error.setMessage("messageNotUserRegister");
        when(repository.searchEmail(usuario.getCorreo())).thenReturn(null);
        assertEquals(error.getMessage(), ((ErrorMessage) wholeService.deleteWhole(usuario)).getMessage());
    }
}
