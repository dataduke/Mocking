/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import java.lang.reflect.Field;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mfochler
 */
public class Runde4EasyMock {
    
    // The class we test
    private Activity instance;
    
    // The class we want to mock
    private Brick brickmock;
    
    @Before
    public void createMocks(){
        // *** create test instance ***
        instance = new ActivityImpl();

        // *** create mocks ***
        // Mock with AssertionError for non mocked functions
        brickmock = EasyMock.createMock(Brick.class);

        // *** add mocks to instance ***
        // by setter
        instance.setBrick(brickmock);
    }
    
    @Test
    public void testExecute2(){
        // create test objects
        Object sdo = "test";
        
        // mock void function
        // call the function on the mock
        brickmock.execute2(sdo);
        // say easymock that this is a valid call
        EasyMock.expectLastCall();
        // replay mock
        EasyMock.replay(brickmock);
        
        // call the function to test
        instance.execute2(sdo);
    }
}
