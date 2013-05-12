/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.stubbing.Answer;

/**
 *
 * @author mfochler
 */
public class EasyMockLimitations {
    
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
    @Ignore("Throws IllgealStateException cause toString() can not be differed")
    public void testToString() {
        // mock function
        EasyMock.expect(brickmock.toString()).andAnswer(new IAnswer<String>() {

            public String answer() throws Throwable {
                // we call sysout to prove the call of the mock
                System.out.println("called toString()");
                return "mocked toString()";
            }
        });
        
        // call the function to test
        String actual = instance.toString();
        
        // verify the mocked function
        // Mockito can not differ
        // Mockito.verify(brickmock).toString();
        // but the function was mocked
        Assert.assertEquals("no mocked toString()", "mocked toString()", actual);
    }
    
    @Test
    @Ignore("Can not be implemented in EasyMock")
    public void testFinalize() throws Throwable {
        // not testable cause EasyMock can not mock void functions
    }
}
