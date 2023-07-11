package com.lifegame.app.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class gameSessionModel implements Runnable {
    private final int stepDelay;
    private boolean isStopped;
    private final Canvas canvas;
    private mapModel map;

    public gameSessionModel(int stepDelay, mapModel map, Canvas canvas) {
        this.stepDelay = stepDelay;
        this.isStopped = false;
        this.canvas = canvas;
        this.map = map;
    }

    public void run() {
        System.out.println(this.map);
        int mapH = this.map.getMap().length, mapW = map.getMap()[0].length;
        while (!this.isStopped) {
            try {
                Thread.sleep(this.stepDelay);

                cellModel[][] cells = this.map.getMap();
                mapModel newMap = new mapModel(mapH, mapW);
                cellModel[][] newCells = newMap.getMap();


                this.update(    //Левый верхний угол
                        newCells[0][0], cells[0][0].getValue(),
                        cells[0][1].getValue() +
                                cells[1][0].getValue() +
                                cells[1][1].getValue());

                this.update(    //Правый верхний угол
                        newCells[0][mapW - 1], cells[0][mapW - 1].getValue(),
                        cells[0][mapW - 2].getValue() +
                                cells[1][mapW - 2].getValue() +
                                cells[1][mapW - 2].getValue());

                this.update(    //Правый нижний угол
                        newCells[mapH - 1][mapW - 1], cells[mapH - 1][mapW - 1].getValue(),
                        cells[mapH - 2][mapW - 1].getValue() +
                                cells[mapH - 2][mapW - 2].getValue() +
                                cells[mapH - 1][mapW - 2].getValue());

                this.update(    //Левый нижний угол
                        newCells[mapH - 1][0], cells[mapH - 1][0].getValue(),
                        cells[mapH - 1][1].getValue() +
                                cells[mapH - 2][1].getValue() +
                                cells[mapH - 2][0].getValue());


                for (int j = 1; j < mapW - 2; j++) {    //Верхняя грань
                    this.update(newCells[0][j], cells[0][j].getValue(),
                            cells[0][j - 1].getValue() +
                                    cells[1][j - 1].getValue() +
                                    cells[1][j].getValue() +
                                    cells[1][j + 1].getValue() +
                                    cells[0][j + 1].getValue());
                }
                for (int i = 1; i < mapH - 2; i++) {    //Правая грань
                    this.update(newCells[i][mapW - 1], cells[i][mapW - 1].getValue(),
                            cells[i - 1][mapW - 1].getValue() +
                                    cells[i - 1][mapW - 2].getValue() +
                                    cells[i][mapW - 2].getValue() +
                                    cells[i + 1][mapW - 2].getValue() +
                                    cells[i + 1][mapW - 1].getValue());
                }
                for (int j = mapW - 2; j > 1; j--) {    //Нижняя грань
                    this.update(newCells[mapH - 1][j], cells[0][j].getValue(),
                            cells[mapH - 1][j - 1].getValue() +
                                    cells[mapH - 2][j - 1].getValue() +
                                    cells[mapH - 2][j].getValue() +
                                    cells[mapH - 2][j + 1].getValue() +
                                    cells[mapH - 1][j + 1].getValue());
                }
                for (int i = mapH - 2; i > 1; i--) {    //Левая грань
                    this.update(newCells[i][0], cells[i][mapW - 1].getValue(),
                            cells[i - 1][0].getValue() +
                                    cells[i - 1][1].getValue() +
                                    cells[i][1].getValue() +
                                    cells[i + 1][1].getValue() +
                                    cells[i + 1][0].getValue());
                }


                for (int i = 1; i < mapH - 2; i++)  //Все остальные клетки
                    for (int j = 1; j < mapW - 2; j++)
                        this.update(newCells[i][j], cells[i][j].getValue(),
                                cells[i + 1][j + 1].getValue() +
                                        cells[i + 1][j].getValue() +
                                        cells[i + 1][j - 1].getValue() +
                                        cells[i][j - 1].getValue() +
                                        cells[i - 1][j - 1].getValue() +
                                        cells[i - 1][j].getValue() +
                                        cells[i - 1][j + 1].getValue() +
                                        cells[i][j + 1].getValue());


                GraphicsContext gc = this.canvas.getGraphicsContext2D();
                gc.setStroke(Color.rgb(230, 230, 230));
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
                gc.setFill(Color.BLACK);
                for (int i = 0; i < mapH - 1; i++)
                    for (int j = 0; j < mapW - 1; j++) {
                        if (newCells[i][j].getValue() == 1) {
                            cells[i][j].setValue(1);
                            gc.fillRect(j * this.map.getCellW(), i * this.map.getCellH(), this.map.getCellW(), this.map.getCellH());
                        } else cells[i][j].setValue(0);
                        //gc.strokeRect(j * this.map.getCellW(), i * this.map.getCellH(), this.map.getCellW(), this.map.getCellH());
                    }
                this.map = newMap;
                System.out.print(":");
            } catch (InterruptedException e) {
                this.isStopped = true;
            }
        }
        System.out.println("Сессия завершена");
    }

    private void update(cellModel newCell, int prevValue, int neighbors) {
        if (neighbors > 1)
            if (neighbors < 4)
                if (neighbors == 3) newCell.setValue(1);
                else if (prevValue == 1) newCell.setValue(1);
    }
}
