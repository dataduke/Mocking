/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author mfochler
 */
public class Runde5Mockito {
    
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
    public void testExecute3(){
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";
        
        // mock void function
        Mockito.doNothing().when(brickmock).execute2(sdo);
        
        // call the function to test
        instance.execute2(sdo);
        
        // verify the mocked function
        // if its void or not
        // by saying exactly how the function was called
        Mockito.verify(brickmock).execute2(sdo);
    }
    
    @Test
    public void testExecute3_again(){
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";
        
        // mock void function
        Mockito.doNothing().when(brickmock).execute2(sdo);
        Mockito.when(brickmock.execute(sdo)).thenReturn(sdo2);
        
        // call the function to test
        instance.execute3(sdo);
        
        // verify the mocked function
        // even how often it was called
        Mockito.verify(brickmock, Mockito.times(3)).execute2(sdo);
        Mockito.verify(brickmock, Mockito.times(3)).execute(sdo);
    }
    
    @Test
    public void testExecute3_again_inOrder(){
        // create test objects
        Object sdo = "test";
        
        // create an inOrder object
        InOrder order = Mockito.inOrder(brickmock);
        
        // mock void function
        // call the function one on the mock
        brickmock.execute(sdo);
        // and only after that function two should be called
        brickmock.execute2(sdo);
        
        // call the function to test
        instance.execute(sdo);
        
        // verify the same way but call the order object
        order.verify(brickmock).execute(sdo);
        order.verify(brickmock).execute2(sdo);
    }
    
    @Test
    public void testExecute3_again_withCapture(){
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";
        
        // create an argument capture
        ArgumentCaptor<Object> capture = ArgumentCaptor.forClass(Object.class);
        
        // mock void function
        // call the function on the mock with the capture
        Mockito.when(brickmock.execute(Mockito.any())).thenReturn(sdo2);
        
        // call the function to test
        instance.execute(sdo);
        
        // verify the call
        Mockito.verify(brickmock).execute(capture.capture());
        // get all captured values
        List<Object> capturedInputs = capture.getAllValues();
        Object lastInput = capture.getValue();
        // verify the captured values
        Assert.assertTrue("Inputs doesn't contain the predicted one", capturedInputs.contains(sdo));
        Assert.assertEquals("Input is not the predicted one", sdo, lastInput);
    }
}
