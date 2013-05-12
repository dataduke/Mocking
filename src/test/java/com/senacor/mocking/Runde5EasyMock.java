/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

import java.lang.reflect.Field;
import java.util.List;
import junit.framework.Assert;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mfochler
 */
public class Runde5EasyMock {

    // The class we test
    private Activity instance;
    // The class we want to mock
    private Brick brickmock;

    @Before
    public void createMocks() {
        // *** create test instance ***
        instance = new ActivityImpl();

        // *** create mocks ***
        // Mock with AssertionError for non mocked functions
        brickmock = EasyMock.createMock(Brick.class);
        // Mock with 0, null or false returns for non mocked functions
        brickmock = EasyMock.createNiceMock(Brick.class);

        // *** add mocks to instance ***
        // by setter
        instance.setBrick(brickmock);
        // or by reflection
        Class testClazz = instance.getClass();
        Class mockClass = brickmock.getClass();
        for (Field field : testClazz.getFields()) {
            if (field.getClass().isAssignableFrom(mockClass)) {
                try {
                    field.set(instance, brickmock);
                } catch (Throwable e) {
                    // exception pain if field is not accessable
                }
                break;
            }
        }
    }

    @Test
    public void testExecute3() {
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

        // verify is done in a oneliner
        // cause the behauvior is done in function mocks
        EasyMock.verify(brickmock);
    }

    @Test
    public void testExecute3_again() {
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";

        // mock void function
        // call the function on the mock
        brickmock.execute2(sdo);
        // if you call it twice it have to be called twice as well
        brickmock.execute2(sdo);
        // or you can stub void methods
        EasyMock.expectLastCall().times(2);
        // or you can stub functions
        EasyMock.expect(brickmock.execute(sdo))
                .andReturn(sdo2).times(2);
        // replay mock
        EasyMock.replay(brickmock);

        // call the function to test
        instance.execute3(sdo);

        // verify is done in a oneliner
        // cause the behauvior is done in function mocks
        EasyMock.verify(brickmock);
    }

    @Test
    public void testExecute3_again_inOrder() {
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";

        // switch to order sensitiv mock
        EasyMock.checkOrder(brickmock, true);
        // or create a order sensitive mock at the beginning
        // brickmock = EasyMock.createStrictMock(Brick.class);

        // mock void function
        // call the function one on the mock
        EasyMock.expect(brickmock.execute(sdo)).andReturn(sdo2);
        // and only after that function two should be called
        brickmock.execute2(sdo);
        EasyMock.expectLastCall();
        // replay mock
        EasyMock.replay(brickmock);

        // call the function to test
        instance.execute4(sdo);

        // verify will also check for the order of calls
        EasyMock.verify(brickmock);
    }

    @Test
    public void testExecute3_again_withCapture() {
        // create test objects
        Object sdo = "test";
        Object sdo2 = "test";

        // create an argument capture
        Capture<Object> capture = new Capture<Object>();

        // mock void function
        // call the function on the mock with the capture
        EasyMock.expect(brickmock.execute(EasyMock.capture(capture)))
                .andReturn(sdo2);
        // replay mock
        EasyMock.replay(brickmock);

        // call the function to test
        instance.execute(sdo);

        // get all captured values
        List<Object> capturedInputs = capture.getValues();
        Object lastInput = capture.getValue();
        // verify the captured values
        Assert.assertTrue("Inputs doesn't contain the predicted one", 
                capturedInputs.contains(sdo));
        Assert.assertEquals("Input is not the predicted one", sdo, lastInput);
    }
}
