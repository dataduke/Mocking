/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mfochler
 */
public class Runde1EasyMock {
    
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
        // Mock with 0, null or false returns for non mocked functions
        brickmock = EasyMock.createNiceMock(Brick.class);
    }
    
    @Test
    public void testFunction(){
        
    }
}
