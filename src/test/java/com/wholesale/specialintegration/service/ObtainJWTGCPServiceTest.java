package com.wholesale.specialintegration.service;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.wholesale.specialintegration.exception.WholeException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ObtainJWTGCPServiceTest {
    
    @InjectMocks
    private static ObtainJWTGCPService objWS;
    
    @Before
    public void init() {
    
    MockitoAnnotations.initMocks(this);
  
    }
    
    @Test 
    public void testObtainJWTGCP() throws WholeException {
        ReflectionTestUtils.setField(objWS, "issuer", "test@test.gserviceaccount.com");
        ReflectionTestUtils.setField(objWS, "audience", "test@test.gserviceaccount.com");
        ReflectionTestUtils.setField(objWS, "time", "10");
        ReflectionTestUtils.setField(objWS, "email", "email");       
        Assert.assertNotNull("NOT NULL", objWS.obtainJWTGCP());
    }
}
