/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

/**
 *
 * @author mfochler
 */
public interface Brick {
    
    Object execute(Object sdo);
    
    void execute2(Object sdo);
    
    @Override
    String toString();
    
    void finalize() throws Throwable;
}
