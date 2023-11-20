package com.wholesale.specialintegration.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import com.wholesale.specialintegration.domain.UsuarioDTO;
import com.wholesale.specialintegration.exception.WholeException;
import com.wholesale.specialintegration.service.ObtainJWTGCPService;
import com.wholesale.specialintegration.service.WholeService;

@RunWith(MockitoJUnitRunner.class)
public class WholeControllerTest {
    @Mock
    private ObtainJWTGCPService jwt;

    @Mock
    private WholeService wholeService;
    @InjectMocks
    private WholeController wholeController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(wholeController, "messageErrorApikey", "messageErrorApikey");
        ReflectionTestUtils.setField(wholeController, "messageErrorToken", "messageErrorToken");
    }

    @Test
    public void testTokenWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.tokenWhole("toKenWhOlE"));
    }
    
    @Test
    public void testTokenWhole1() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.tokenWhole("APIkeyWhole"));
    }
       
    @Test
    public void testPostWhole1() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.postWhole(new UsuarioDTO(), "Bearer"+" toKenWhOlE"));
    }
   
    @Test
    public void testPostWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.postWhole(new UsuarioDTO(), "toKenWhOlE"));
    }

    @Test
    public void testGetWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.getWhole(new UsuarioDTO(), "toKenWhOlE"));
    }
    
    @Test
    public void testGetWhole1() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.getWhole(new UsuarioDTO(), "Bearer"+" toKenWhOlE"));
    }

    @Test
    public void testPutWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.putWhole(new UsuarioDTO(), "toKenWhOlE"));
    }
    
    @Test
    public void testPutWhole1() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.putWhole(new UsuarioDTO(), "Bearer"+" toKenWhOlE"));
    }

    @Test
    public void testDeleteWhole() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.deleteWhole(new UsuarioDTO(), "toKenWhOlE"));
    }
    
    @Test
    public void testDeleteWhole1() throws WholeException {
        Assert.assertNotNull("NOT NULL", wholeController.deleteWhole(new UsuarioDTO(), "Bearer"+" toKenWhOlE"));
    }

}
