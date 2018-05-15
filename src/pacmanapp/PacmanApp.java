/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

//import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.O;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author csc190
 */
public class PacmanApp extends Application implements API {

    protected GraphicsContext gc;
    protected GameEngine ge;
    

    protected Stage primaryStage;
    protected Group root;
    
    protected BorderPane border = new BorderPane();
    protected AnchorPane mainscreen = new AnchorPane();
    protected MyThread mt;
    protected Label HScore = new Label();
    
    protected enum CONDITION {
        WIN, LOSE, CONTINUE
    };

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
                
                                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HScore.setText("High Score: " + ge.HS);
                        
                    }
                });

                switch (ge.endCondition()) {
                    case 1:
                        ge.oneRound();
                        break;
                    case 2:
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
//                                score.setText("High Score: " + ge.score + " You Win!!!");

                                gameOverMenu(root, primaryStage, true);
                                mt.stop();
                            }
                        });
                        break;
                    case 3:
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
//                                score.setText("High Score: " + ge.score + " You LOST!!! LOSER!");
                                gameOverMenu(root, primaryStage, false);
                                mt.stop();
                            }
                        });
                        break;
                }

            }
        }
    }
    
    protected VBox mainMenu(Group root, Stage primaryStage)
    {
        Button newGame = new Button();
        newGame.setText("Start Game");
        newGame.setStyle("-fx-text-fill: black; -fx-border-color: blue;");
        newGame.setOnAction((ActionEvent e)-> {
            startingGame(root, primaryStage);
        });
        
        Button exitGame = new Button();
        exitGame.setText("Exit Game");
        exitGame.setStyle("-fx-text-fill: black; -fx-border-color: blue;");
        exitGame.setOnAction((ActionEvent e) -> {
            Stage n = (Stage) primaryStage.getScene().getWindow();
            n.close();
        });
        
        VBox vbox = new VBox();
        vbox.setPrefHeight(1000);
        vbox.setPrefWidth(1000);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        Label gameTitle = new Label();
        gameTitle.setText("Main Menu");
        gameTitle.setFont(Font.font(35));
        gameTitle.setStyle("-fx-text-fill: black;");
        vbox.getChildren().add(gameTitle);

        VBox.setMargin(newGame, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(newGame);

        VBox.setMargin(exitGame, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(exitGame);

        vbox.setAlignment(Pos.CENTER);

        return vbox;
        
    }
    
    protected VBox gameOverMenu(Group root, Stage primarystage, boolean condition)
    {
        Button exitGame = new Button();
        exitGame.setText("Exit Game");
        exitGame.setStyle("-fx-text-fill: black; -fx-border-color: blue;");
        exitGame.setOnAction((ActionEvent e) -> {
            Stage n = (Stage) primaryStage.getScene().getWindow();
            n.close();
        });
        
        VBox vbox = new VBox();
        vbox.setPrefHeight(1000);
        vbox.setPrefWidth(1000);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        Label gameTitle = new Label();
        gameTitle.setText("GAME OVER");
        gameTitle.setFont(Font.font(35));
        gameTitle.setStyle("-fx-text-fill: black;");
        vbox.getChildren().add(gameTitle);

        VBox.setMargin(exitGame, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(exitGame);

        vbox.setAlignment(Pos.CENTER);

        return vbox;
        
    }
    
    /*
    public void loadMenu(Group root, Stage primaryStage, String difficulty){
        this.root = new Group();
        border = new BorderPane();
        
        Button
    }
*/
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Pacman");
        
        this.root = new Group();
        
        StackPane TitlePane = new StackPane();
        Label gameTitle = new Label();
        gameTitle.setText("Pac-man");
        gameTitle.setFont(Font.font(75));
        gameTitle.setStyle("-fx-text-fill: black;");
        gameTitle.setTextAlignment(TextAlignment.CENTER);
        TitlePane.getChildren().addAll(gameTitle);
        TitlePane.setAlignment(gameTitle, Pos.CENTER);
        border.setTop(TitlePane);
        border.setCenter(mainMenu(root, primaryStage));
        
        root.getChildren().add(border);
        Scene scene = new Scene(root, 500, 500);
        border.prefWidthProperty().bind(scene.widthProperty());
        border.prefHeightProperty().bind(scene.heightProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }
        
    
    public void startingGame(Group root, Stage primarystage)
    {
        //Set up Canvas
        this.root = new Group();
        border = new BorderPane();
        Canvas canvas = new Canvas(1000, 1000);
        border.setPrefHeight(1000);
        border.setPrefWidth(1000);
        
        this.gc = canvas.getGraphicsContext2D();
        
        //Set up an instance of GameEngine
        this.ge = new GameEngine(this);
        this.ge.loadMap();
        
        BorderPane.setMargin(canvas, new Insets(0, 0, 0, 8));
        border.setCenter(canvas);

        BorderPane.setMargin(HScore, new Insets(0, 0, 0, 8));
        border.setTop(HScore);
        
        HScore.setTextFill(Color.WHITESMOKE);
        HScore.setStyle("-fx-text-fill: black; -fx-border-color: blue;");
        HScore.setFont(Font.font(20));
        
        // Start a thread or use Timeline/Keyframe!!!
        MyThread mt = new MyThread();
        mt.start(); // is a function provided by the standard Java to run the thread's function
    
        this.root.getChildren().add(border);
        Scene scene = new Scene(this.root, 1000, 1000);
        
        
        border.prefWidthProperty().bind(scene.widthProperty());
        border.prefHeightProperty().bind(scene.heightProperty());
        
        
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
                
        //root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primarystage.show();
        primarystage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                mt.stop();
            }
        });
  
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
