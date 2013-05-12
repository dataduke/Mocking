/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import java.lang.reflect.Field;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mfochler
 */
public class Runde3EasyMock {
    
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
    public void testExecute() {
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";

        // mock simple function: input -> output
        EasyMock.expect(brickmock.execute(sdo))
                .andReturn(sdo2);

        // mock dynamic input -> fix output
        EasyMock.expect(brickmock.execute(EasyMock.anyObject(sdo.getClass())))
                .andReturn(sdo2);
        
        // andDelegateTo style for real object methods
        EasyMock.expect(brickmock.execute(sdo))
                .andDelegateTo(new BrickImpl());

        // call the function to test
        instance.execute(sdo);
    }

    @Test
    public void testExecute_again() {
        // create test objects
        Object sdo = "test";

        // mock simple function: input -> output
        EasyMock.expect(brickmock.execute(sdo))
                .andAnswer(new IAnswer<Object>() {
                    
            public Object answer()
                    throws Throwable {
                Object input = EasyMock.getCurrentArguments()[0];
                // manipulate input or create output
                Object output = "an other object";
                // return the function return
                return output;
            }
        });

        // call the function to test
        instance.execute(sdo);
    }
}
