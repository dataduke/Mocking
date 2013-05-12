/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author mfochler
 */
public class Runde1Mockito {
    
    // The class we test
    @InjectMocks
    private Activity instance;
    
    // The class we want to mock
    @Mock
    private Brick brickmock;
    
    @Before
    public void createMocks(){
        // *** create test instance ***
        instance = new ActivityImpl();
        
        // *** create mocks ***
        // manually created Mock
        brickmock = Mockito.mock(Brick.class);
        // or by Annotation
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFunction(){
        
    }
}
