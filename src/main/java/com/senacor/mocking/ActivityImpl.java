/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senacor.mocking;

/**
 *
 * @author mfochler
 */
public class ActivityImpl implements Activity {
    
    private Brick brick;

    public void setBrick(Brick brick) {
        this.brick = brick;
    }

    public void execute(Object sdo) {
        this.brick.execute(sdo);
    }

    public void execute2(Object sdo) {
        this.brick.execute2(sdo);
    }

    public void execute3(Object sdo) {
        this.brick.execute2(sdo);
        this.brick.execute2(sdo);
        this.brick.execute2(sdo);
        this.brick.execute(sdo);
        this.brick.execute(sdo);
        this.brick.execute(sdo);
    }

    public void execute4(Object sdo) {
        this.brick.execute(sdo);
        this.brick.execute2(sdo);
    }

    @Override
    public String toString() {
        return this.brick.toString();
    }

    @Override
    public void finalize() throws Throwable {
        this.brick.finalize();
    }
}
