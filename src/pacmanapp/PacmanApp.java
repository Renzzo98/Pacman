/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.O;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author csc190
 */
public class PacmanApp extends Application implements API {

    protected GraphicsContext gc;
    protected GameEngine ge;

    class MyThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20);
                    ge.oneRound();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        //1. set up canvas
        primaryStage.setTitle("Pacman");
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    ge.handleKey(GameEngine.KEY.UP); break;
                    case DOWN:  ge.handleKey(GameEngine.KEY.DOWN); break;
                    case LEFT:  ge.handleKey(GameEngine.KEY.LEFT); break;
                    case RIGHT: ge.handleKey(GameEngine.KEY.RIGHT); break;
                }
            }
        });
        
        Canvas canvas = new Canvas(1000,1000);
        this.gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();

        //2. Create GameEngine
        this.ge = new GameEngine(this);
        this.ge.loadMap();
       

        //3. IMPORTANT!! Start a thread or use Timeline/Keyframe!!!
        MyThread mt = new MyThread();
        mt.start(); // is a function provided by the standard Java to run the thread's function
    }

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    protected Hashtable<String, Image> map = new Hashtable<String, Image>();

    @Override
    public void drawImg(String filename, int x, int y, int w, int h) {
        FileInputStream fis = null;
        try {
            Image ig = map.get(filename);
            if (ig == null) {
                String path = "images/" + filename;
                fis = new FileInputStream(path);
                ig = new Image(fis);
                map.put(filename, ig);
            }
            this.gc.drawImage(ig, x, y, w, h);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void clear() {
        this.gc.clearRect(0,0,1000,1000);
    }

}
