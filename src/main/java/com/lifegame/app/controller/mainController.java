package com.lifegame.app.controller;

import com.lifegame.app.mainApplication;
import com.lifegame.app.model.cellModel;
import com.lifegame.app.model.gameSessionModel;
import com.lifegame.app.model.mapModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class mainController extends mainApplication {

    @FXML
    private Pane pane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button start_pause_button;

    @FXML
    private Canvas canvas;

    @FXML
    private Button clearButton;

    @FXML
    void onCanvasKeyPressed(KeyEvent event) {

    }

    @FXML
    void onCanvasMouseClicked(MouseEvent event) throws IOException {
        this.onCanvasMouseDragged(event);
    }

    @FXML
    void onCanvasMouseDragged(MouseEvent event) throws IOException {
        if (anchorPaneH != anchorPane.getHeight()) {
            anchorPaneH = anchorPane.getHeight();
            anchorPaneW = anchorPane.getWidth();
            paneH = anchorPaneH - 20;
            paneW = anchorPaneW - 30 - 200;
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, paneW, paneH);
        }
        if (gameOnPause) {
            double x = event.getX(), y = event.getY();
            if (x >= 0 && y >= 0 && x <= canvasW && y <= canvasH) {
                x -= x % squareEdgeLength;
                y -= y % squareEdgeLength;
                switch (event.getButton()) {
                    case PRIMARY -> {
                        map.getMap()[(int) (y / squareEdgeLength)][(int) (x / squareEdgeLength)].setValue(1);
                        gc.setFill(Color.BLACK);
                    }
                    case SECONDARY -> {
                        map.getMap()[(int) (y / squareEdgeLength)][(int) (x / squareEdgeLength)].setValue(0);
                        gc.setFill(Color.WHITE);
                    }
                }
                gc.fillRect(x, y, squareEdgeLength, squareEdgeLength);
            }
        }
    }

    @FXML
    void clearMap(MouseEvent event) throws InterruptedException {
        gameOnPause = true;
        gameSession.stopT();
        start_pause_button.setText("Старт");
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, paneW, paneH);
        map.clear();
        gameSession.setMap(this.map);
    }


    @FXML
    void startPauseButtonClicked(MouseEvent event) throws InterruptedException {
        if (gameOnPause) {
            gameOnPause = false;
            //gameSessionModel.map = this.map;
            gameSession.startT();
            start_pause_button.setText("Стоп");
        } else {
            gameOnPause = true;
            gameSession.stopT();
            start_pause_button.setText("Старт");
        }
    }

    private boolean gameOnPause;
    private final int squareEdgeLength = 4;
    private double canvasH, canvasW, paneH, paneW, anchorPaneH, anchorPaneW;
    private mapModel map;
    private Thread session;
    private gameSessionModel gameSession;
    private GraphicsContext gc;

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'mainView.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'mainView.fxml'.";
        assert start_pause_button != null : "fx:id=\"start_pause_button\" was not injected: check your FXML file 'mainView.fxml'.";

        canvasH = canvas.getHeight();
        canvasW = canvas.getWidth();
        anchorPaneH = anchorPane.getPrefHeight();
        anchorPaneW = anchorPane.getPrefWidth();
        paneH = anchorPaneH - 20;
        paneW = anchorPaneW - 30 - 200;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, paneW, paneH);
        gameOnPause = true;
        map = new mapModel((int) canvasH / squareEdgeLength, (int) canvasW / squareEdgeLength);

        gameSession = new gameSessionModel(50, this.map, this.canvas, paneH, paneW);
        session = new Thread(gameSession);
        session.start();
        gameSession.stopT();
    }
}
