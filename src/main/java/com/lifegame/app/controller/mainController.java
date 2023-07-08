package com.lifegame.app.controller;

import com.lifegame.app.model.mapModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class mainController {

    @FXML
    private Canvas canvas;

    @FXML
    void onCanvasKeyPressed(KeyEvent event) {

    }

    @FXML
    void onCanvasMouseClicked(MouseEvent event) {

    }

    @FXML
    void onCanvasMouseDragged(MouseEvent event) {
        if (drawAvailable) {
            double x = event.getX(), y = event.getY();
            x -= x % cellW;
            y -= y % cellH;
            switch (event.getButton()) {
                case PRIMARY -> {
                    map.getMap()[(int) (x / cellW)][(int) (y / cellH)].setAlive();
                    gc.setFill(Color.BLACK);
                }
                case SECONDARY -> {
                    map.getMap()[(int) (x / cellW)][(int) (y / cellH)].setDead();
                    gc.setFill(Color.WHITE);
                }
            }
            gc.fillRect(x, y, cellW, cellH);
            gc.strokeRect(x, y, cellW, cellH);
        }
    }

    private GraphicsContext gc;
    private final int mapW = 190;
    private final int mapH = 106;
    private double cellW, cellH, canvasW, canvasH;
    private mapModel map;
    private boolean drawAvailable;

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'mainView.fxml'.";
        drawAvailable = true;
        gc = canvas.getGraphicsContext2D();
        map = new mapModel(mapW, mapH);
        cellH = map.getCellH();
        cellW = map.getCellW();
        canvasW = canvas.getWidth() - 20;
        canvasH = canvas.getHeight() - 20;
        gc.setStroke(Color.rgb(230, 230, 230));
        for (int i = 0; i < canvasW; i += cellW)
            for (int j = 0; j < canvasH; j += cellH)
                gc.strokeRect(i, j, cellW, cellH);
    }
}
