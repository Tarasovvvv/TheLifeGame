package com.lifegame.app.model;

public class mapModel {
    private cellModel[][] map;
    private final double cellW;
    private final double cellH;
    private final double w;
    private final double h;

    public mapModel(int h, int w) {
        this.cellH = 1060.0 / h;
        this.cellW = 1690.0 / w;
        this.h = h;
        this.w = w;
        this.map = new cellModel[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                this.map[i][j] = new cellModel();
    }

    public void clear() {
        for (int i = 0; i < this.h; i++)
            for (int j = 0; j < this.w; j++)
                this.map[i][j].setValue(0);
    }

    public void setMap(cellModel[][] newMap) {
        this.map = newMap;
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
