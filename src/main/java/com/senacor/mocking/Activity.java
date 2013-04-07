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
     * A function that calls a brick function 
     */
    void execute(Object sdo);
    
    /**
     * A function that calls a brick void function 
     */
    void execute2(Object sdo);
}
