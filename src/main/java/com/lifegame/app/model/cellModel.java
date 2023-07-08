package com.lifegame.app.model;

public class cellModel {
    private boolean isAlive;

    public cellModel() {
        this.isAlive = false;
    }

    public void setAlive() {
        this.isAlive = true;
    }

    public void setDead() {
        this.isAlive = false;
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
