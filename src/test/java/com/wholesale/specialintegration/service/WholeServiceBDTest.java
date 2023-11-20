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
    public void testGetWhole() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.getWhole(user));
    }

    @Test
    public void testPostWhole() throws WholeException {

        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNull("NULL", wholeService.postWhole(user));        
    }

    @Test
    public void testPutWhole() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        user.setContrasena("Qhunter2");
        Assert.assertNotNull("NOT NULL", wholeService.putWhole(user));
    }

    @Test
    public void testDeleteWhole() throws WholeException {
        Usuario user = new Usuario();
        user.setCorreo("juan@rodriguez.com");
        Assert.assertNotNull("NOT NULL", wholeService.deleteWhole(user));
    }

}
