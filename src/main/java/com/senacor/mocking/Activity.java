/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

/**
 *
 * @author mfochler
 */
public interface Activity {
    
    void setBrick(Brick brick);
    
    /**
     * A function that calls a brick function. 
     */
    void execute(Object sdo);
    
    /**
     * A function that calls a brick void function.
     */
    void execute2(Object sdo);
    
    /**
     * A function that calls a brick void function three and execute2 two times.
     */
    void execute3(Object sdo);
    
    /**
     * A function that calls execute and execute2 on brick.
     */
    void execute4(Object sdo);
    
    @Override
    String toString();
    
    void finalize() throws Throwable;
}
