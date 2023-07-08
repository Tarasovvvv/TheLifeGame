package com.lifegame.app.model;

public class mapModel {
    private final cellModel[][] map;
    private final double cellW;
    private final double cellH;

    public mapModel(int w, int h) {
        this.cellW = 1900.0 / w;
        this.cellH = 1060.0 / h;
        this.map = new cellModel[w][h];
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                this.map[i][j] = new cellModel();
    }

    public cellModel[][] getMap() {
        return this.map;
    }

    public double getCellH() {
        return this.cellH;
    }

    public double getCellW() {
        return this.cellW;
    }
}
