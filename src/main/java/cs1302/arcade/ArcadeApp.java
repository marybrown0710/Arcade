package cs1302.arcade;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import java.awt.Graphics;
import java.lang.*;
import javafx.stage.*;
import java.awt.Graphics;

public class ArcadeApp extends Application {

    HBox hbox = new HBox();
    Scene scene;
    VBox vBox = new VBox();
    VBox vBox2 = new VBox();
    Random rng = new Random();
    MenuBar menubar2 = new MenuBar();
    MenuBar menubar = new MenuBar();
    Menu fileMenu = new Menu("File");
    Menu optionMenu = new Menu("Options");
    Menu helpMenu = new Menu("Help");
    Menu gamesButt = new Menu("Games");
    MenuItem exitGame = new MenuItem("Exit Game");
    MenuItem exitButt = new MenuItem("Exit");
    MenuItem changeTheme = new MenuItem("Change Theme");
    MenuItem checkers = new MenuItem("Checkers");
    MenuItem tetris = new MenuItem("Tetris");
    Menu gameFile = new Menu("File");
    Menu gameHelp = new Menu("Help");
    Checkers ch = new Checkers();
    // Pane pane1 = new Pane();
    // Group chG = ch.createBoard();

    VBox vBox3 = new VBox();
    Tetris tet = new Tetris();
    StackPane tPane = new StackPane();

    @Override
    public void start(Stage stage) {

	/* You are allowed to rewrite this start method, add other methods, 
	 * files, classes, etc., as needed. This currently contains some 
	 * simple sample code for mouse and keyboard interactions with a node
	 * (rectangle) in a group. 
	 */
	menubar.getMenus().addAll(fileMenu,optionMenu,helpMenu);
	fileMenu.getItems().addAll(gamesButt,exitButt);
	optionMenu.getItems().add(changeTheme);
	gamesButt.getItems().addAll(checkers,tetris);
	menubar.prefWidthProperty().bind(stage.widthProperty());
	menubar2.getMenus().addAll(gameFile,gameHelp);
	gameFile.getItems().add(exitGame);
	menubar2.prefWidthProperty().bind(vBox2.widthProperty());
    
	Image bg2 = new Image("https://s3-us-west-2.amazonaws.com/s.cdpn.io/476907/upsidedown.png");
	Image bg1 = new Image("https://newevolutiondesigns.com/images/freebies/retro-wallpaper-50.jpg");
	ImageView iv = new ImageView();

	//set background to first image
	iv.setImage(bg1);
	iv.fitWidthProperty().bind(stage.widthProperty());
	vBox.getChildren().addAll(menubar,iv);
	Scene scene = new Scene(vBox,1000,650);
	
	//opens checker game
	checkers.setOnAction(actionEvent -> {
	 	StackPane sPane = new StackPane();
		Group newB = ch.createBoard();
		CheckersTile[][] chTile = ch.getChBoardArray();

		//	for (int i = 0; i < 8; i++)
		//  { for (int v = 0; v < 8; v++)
		//	    {
		//		if (chBoard[i][v]
		//		chBoard[i][v].getPiece()
		vBox2.getChildren().clear();
		vBox2.getChildren().addAll(menubar2,newB);
	 	sPane.getChildren().addAll(vBox,vBox2);
	 	stage.setScene(new Scene(sPane,1000,650));
  });

	//opens tetris game
	tetris.setOnAction(actionEvent -> {
		tet.start(new Stage());
		System.out.println("It worked!!");
	   });


	//exits game without exiting application 
	exitGame.setOnAction(actionEvent -> {
		VBox v1 = new VBox();
		v1.getChildren().addAll(menubar,iv);
		stage.setScene(new Scene(v1,1000,650));
	    });

	//changes background when pressed
	changeTheme.setOnAction(actionEvent -> {
		if (iv.getImage().equals(bg1))
		    {
			iv.setImage(bg2);
		    }
		else
		    {
			iv.setImage(bg1);
		    }
		VBox v1 = new VBox();       		
		v1.getChildren().addAll(menubar,iv);
		stage.setScene(new Scene(v1,1000,650));
	    });

	//exits application
	exitButt.setOnAction(actionEvent -> {
		Platform.exit();
	    });

        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();
    } // start

    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

    
		    

} // ArcadeApp

