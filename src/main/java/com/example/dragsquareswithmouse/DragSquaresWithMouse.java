package com.example.dragsquareswithmouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program demonstraste the uses of events listners
 * the program allow the user to drag  either of squares
 * accross the canvas or beyond the scene.
 * in the case user want to try star back they press ESCAPE key
 */

public class DragSquaresWithMouse extends Application {

    final double squareSize = 80; //the size of the square

    private GraphicsContext g; //for drawing on the canvas

    private Canvas canvas;// where everything is drawn

    private boolean dragging; // this is true while the user is dragging the square

    private boolean isRedSquare; //holding the value of the current square being dragged


    /**
     * the function initialize the program
     * set the canvas where everything is drawn
     * drow two square a blue and red
     * set mouse events
     * set Escape key events
     */
    @Override
    public void start(Stage stage) {
        canvas = new Canvas(600, 400);
        initDraw();

        canvas.setOnMouseDragged(e -> mouseDragged(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMousePressed(e -> mousePressed(e));

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Drag squares with mouse");

        scene.setOnKeyPressed(this::keyTyped);

        stage.show();
    }

    public static void main (String[] args){
        launch();
    }

    //------------ Draw function ---------

    /*
    The method initialize the drawing and
    it is called each time the escape key is pressed
     */
    public void initDraw(){
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        redSquare(150, 150);
        blueSquare(280, 150);
    }

    /**
     * It redraws either red or blue square based on the user
     * clicks on the canvas
     * @param x type double, used to set x point of the square
     * @param y type double, used to set y point of the square
     */
    public void draw(double x, double y){
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        if(isRedSquare)redSquare(x, y);
        else blueSquare(x, y);
    }

    /**
     * draws a red square
     * @param x type double, used to x point of the square
     * @param y type double, used to set y point of the square
     */
    public void redSquare(double x, double y){
        g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.setFill(Color.RED);
        g.fillRect(x, y, squareSize, squareSize);

    }

    /**
     * draws a blue square
     * @param x type double, used to x point of the square
     * @param y type double, used to set y point of the square
     */
    public void blueSquare(double x, double y){
        g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.setFill(Color.BLUE);
        g.fillRect(x, y, squareSize, squareSize);

    }

    //-------- Mouse event listener ----------
    public void mouseDragged(MouseEvent evt){
        if(!dragging) return; //Nothing to do because the user isn't drawing

        double x = evt.getX();
        double y = evt.getY();

        draw(x, y);

    }

    public void mousePressed(MouseEvent evt){
        dragging = true;

        double x = evt.getX();

        if(x > 150 && x < 250) {
            isRedSquare = true;
        }else if(x > 280 && x < 360){
            isRedSquare = false;
        }
    }

    public void mouseReleased(MouseEvent evt){
        dragging = false;
    }

    //---------key event handler --------
    public void keyTyped(KeyEvent evt){
        var key = evt.getCode();
        if(key == KeyCode.ESCAPE){
           initDraw();
        }
    }
}//end of the class
