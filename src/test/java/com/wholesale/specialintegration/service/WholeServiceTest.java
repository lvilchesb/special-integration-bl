package com.wholesale.specialintegration.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.exception.WholeException;
import com.wholesale.specialintegration.service.repository.WholeRepository;

@RunWith(MockitoJUnitRunner.class)
public class WholeServiceTest {
    @Mock
    private WholeRepository repository;
    @InjectMocks
    private WholeService wholeService;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(wholeService, "messageFormatMail", "messageFormatMail");
        ReflectionTestUtils.setField(wholeService, "validaMail", "validaMail");
        ReflectionTestUtils.setField(wholeService, "validaPassword", "validaPassword");
        ReflectionTestUtils.setField(wholeService, "messagePassword", "messagePassword");
        ReflectionTestUtils.setField(wholeService, "messageNotUserRegister", "messageNotUserRegister");
        
        ReflectionTestUtils.setField(wholeService, "messageNotMailRegister", "messageNotMailRegister");
        ReflectionTestUtils.setField(wholeService, "messageMailRegister", "messageMailRegister");
        ReflectionTestUtils.setField(wholeService, "userDelete", "userDelete");
        ReflectionTestUtils.setField(wholeService, "userActive", "userActive");

    }

    @Test
    public void testGetWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeService.getWhole(new Usuario()));
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.getWhole(user));
        user.setCorreo("juanrodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.getWhole(user));
    }

    @Test
    public void testPostWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeService.postWhole(new Usuario()));
        
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.postWhole(user));
        
        user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        user.setContrasena("1");
        Assert.assertNotNull("NOT NULL", wholeService.postWhole(user));
        
    }

    @Test
    public void testPutWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(new Usuario()));
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        user.setContrasena("Qhunter2");
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(user));
        user = new Usuario();
        user.setCorreo("juanrodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(user));
    }

    @Test
    public void testDeleteWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeService.deleteWhole(new Usuario()));
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.deleteWhole(user));
        user.setCorreo("juanrodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.deleteWhole(user));
    }

}
