package com.lifegame.app.controller;

import com.lifegame.app.model.mapModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    }

    GraphicsContext gc;
    int mapW = 190, mapH = 106;
    double cellW, cellH, canvasW, canvasH;

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'mainView.fxml'.";
        gc = canvas.getGraphicsContext2D();
        mapModel map = new mapModel(mapW, mapH);
        cellH = map.getCellH();
        cellW = map.getCellW();
        canvasW = canvas.getWidth()-20;
        canvasH = canvas.getHeight()-20;
        gc.setStroke(Color.rgb(200, 200, 200));
        for (int i = 0; i < canvasW; i += cellW)
            for (int j = 0; j < canvasH; j += cellH)
                gc.strokeRect(i, j, cellW, cellH);
    }
}
