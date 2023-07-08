package com.lifegame.app.model;

public class mapModel {
    private cellModel[][] map;
    private double cellW, cellH;

    public mapModel(int w, int h) throws Exception {
        if (w > 1900) throw new Exception("w должно быть меньше 1900");
        if (h > 1060) throw new Exception("h должно быть меньше 1060");
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
