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
public class Runde3Mockito {

    // The class we test
    private Activity instance;
    // The class we want to mock
    @Mock
    private Brick brickmock;

    @Before
    public void createMocks() {
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
    public void testExecute() {
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";

        // mock simple function: input -> output
        Mockito.when(brickmock.execute(sdo))
                .thenReturn(sdo2);

        // mock dynamic input -> fix output
        Mockito.when(brickmock.execute(Mockito.any(sdo.getClass())))
                .thenReturn(sdo2);

        // mock of real object can call real methods
        Mockito.when(brickmock.execute(sdo))
                .thenCallRealMethod();

        // call the function to test
        instance.execute(sdo);
    }

    @Test
    public void testExecute_again() {
        // create test objects
        Object sdo = "test";

        // mock simple function: input -> output
        Mockito.when(brickmock.execute(sdo))
                .thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation)
                    throws Throwable {
                Object input = invocation.getArguments()[0];
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
