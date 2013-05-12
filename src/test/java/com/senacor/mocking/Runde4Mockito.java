/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author mfochler
 */
public class Runde4Mockito {
    
    // The class we test
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
        MockitoAnnotations.initMocks(instance);
        
        // *** add mocks to instance ***
        // by setter
        instance.setBrick(brickmock);
        // or by reflection by the Annotation
        // see line 36
    }
    
    @Test
    public void testExecute2(){
        // create test objects
        Object sdo = "test";
        
        // mock void function
        // define it vise versa
        Mockito.doAnswer(new Answer() {
            // this is what happens in the void
            public Object answer(InvocationOnMock invocation) 
                    throws Throwable {
                Object sdo = invocation.getArguments()[0];
                // do something with the sdo in the mock
                // ...
                // cause it is void we return null
                return null;
            }
            
        }).when(brickmock).execute2(sdo);
        
        // call the function to test
        instance.execute(sdo);
    }
}
