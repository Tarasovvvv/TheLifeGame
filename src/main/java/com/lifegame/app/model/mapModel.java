package com.lifegame.app.model;

public class mapModel {
    private cellModel[][] map;
    private double cellW, cellH;

    public mapModel(int w, int h) {
        this.cellW = 1900 / w;
        this.cellH = 1060 / h;
        this.map = new cellModel[w][h];
    }

    public double getCellH() {
        return this.cellH;
    }

    public double getCellW() {
        return this.cellW;
    }
}
