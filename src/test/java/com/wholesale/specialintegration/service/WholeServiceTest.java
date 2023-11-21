package com.wholesale.specialintegration.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.wholesale.specialintegration.domain.ErrorMessage;
import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.exception.WholeException;

@RunWith(MockitoJUnitRunner.class)
public class WholeServiceTest {
    @Mock
    private WholeServiceBD wholeServiceBD;
    @InjectMocks
    private WholeService wholeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(wholeService, "messageFormatMail", "messageFormatMail");
        ReflectionTestUtils.setField(wholeService, "validaMail", "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        ReflectionTestUtils.setField(wholeService, "validaPassword", "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{4,10}$");
        ReflectionTestUtils.setField(wholeService, "messagePassword", "messagePassword");
        ReflectionTestUtils.setField(wholeService, "messageRequest", "messagePassword");
    }

    @Test
    public void testGetWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeService.getWhole(new Usuario()));
        Assert.assertNotNull("NOT NULL", wholeService.getWhole(null));
    }

    @Test
    public void postWholeRequestNull() throws WholeException {
        ErrorMessage error = new ErrorMessage();
        error.setMessage("messagePassword");
        assertEquals(error, wholeService.postWhole(null));
    }

    @Test
    public void postWholeRequestOk() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setCorreo("juanperez@gmail.com");
        usuario.setContrasena("Qhunter2");
        when(wholeServiceBD.postWhole(usuario)).thenReturn(usuario);
        Assert.assertNotNull("NOT NULL", wholeService.postWhole(usuario));
    }

    @Test
    public void postWholeRequestError() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setCorreo("juan.perez@gmail.com");
        ErrorMessage error = new ErrorMessage();
        error.setMessage("messagePassword");
        when(wholeServiceBD.postWhole(usuario));
        assertEquals(error, wholeService.postWhole(usuario));
    }
    
    @Test
    public void postWholeRequestError2() throws WholeException {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setCorreo("juan.perezgmail.com");
        ErrorMessage error = new ErrorMessage();
        error.setMessage("messageFormatMail");
        when(wholeServiceBD.postWhole(usuario));
        assertEquals(error, wholeService.postWhole(usuario));
    }

    @Test
    public void testPutWhole() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        user.setContrasena("Qhunter2");
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(user));
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(null));
    }
    
    @Test
    public void testPutWholeError() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juanrodriguez.com");
        user.setContrasena("Qhunter2");
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(user));
    }

    @Test
    public void testDeleteWhole() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNull("NULL", wholeService.deleteWhole(user));
    }
    
    @Test
    public void testDeleteWholeNoOk() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.deleteWhole(null));
    }

}
