/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

/**
 *
 * @author mfochler
 */
public class BrickImpl implements Brick {

    public Object execute(Object sdo) {
        System.out.println("real execute(Object)");
        return sdo;
    }

    public void execute2(Object sdo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "real toString()";
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("real finalize()");
    }
}
