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
    Pane pane1 = new Pane();
    Group chG = ch.createBoard();
    StackPane sPane = new StackPane();

    VBox vBox3 = new VBox();
    Tetris tet = new Tetris();
    // Graphics g = new Graphics();  //???
    // Pane pane3 = tet.init();  // ???
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
	vBox2.getChildren().addAll(menubar2,chG);
	//	vBox3.getChildren().addAll(menubar2,pane3);

	//		Group group = new Group();           // main container
	//	Rectangle r = new Rectangle(20, 20); // some rectangle
	//	r.setX(50);                          // 50px in the x direction (right)
	//	r.setY(50);                          // 50ps in the y direction (down)
		//   group.getChildren().add(vBox);          // add to main container


	// when the user presses left and right, move the rectangle
	//	group.setOnKeyPressed(event -> {
	//	System.out.println(event);
	//	if (event.getCode() == KeyCode.LEFT)  r.setX(r.getX() - 10.0);
	//		if (event.getCode() == KeyCode.RIGHT) r.setX(r.getX() + 10.0);
		// TODO bounds checking
	//  });

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
		sPane.getChildren().clear();
		sPane.getChildren().addAll(vBox,vBox2);
		stage.setScene(new Scene(sPane,1000,650));
        });

	// opens tetris game
	
	//	tetris.setOnAction(actionEvent -> {
		
	//	tPane.getChildren().addAll(vBox,vBox3);
	//	stage.setScene(new Scene(tPane, 1000, 650));
	//   });

	//exits game without exiting application 
	exitGame.setOnAction(actionEvent -> {
		//	VBox v1 = new VBox();
		vBox.getChildren().clear();
		vBox.getChildren().addAll(menubar,iv);
		stage.setScene(scene);
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

       	menubar.getMenus().clear();
	menubar.getMenus().addAll(fileMenu,optionMenu,helpMenu);
       	vBox.getChildren().clear();		
	vBox.getChildren().addAll(menubar,iv);
       	stage.setScene(scene);
	    });

	//exits application
	exitButt.setOnAction(actionEvent -> {
		Platform.exit();
	    });

        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();

	// the group must request input focus to receive key events
	// @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
	//	group.requestFocus();

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

