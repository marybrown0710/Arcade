package cs1302.arcade;

import javafx.scene.layout.*;
import javafx.util.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.animation.*;

/**
 * Handles all the actions and operations of Tetris game. I AM FINISHED!!
 *
 *@author Mary Brown
 */

public class Tetris extends Application {

    private static final int HEIGHT = 680, WIDTH = 640, BLOCK_SIZE = 32;
    private static final int X_OFFSET = 20, Y_OFFSET = 20, NEXTPIECEX = 360, NEXTPIECEY = 40;
    private static Piece currentPiece;
    private static Piece nextPiece;
    private static Board board;
    private static int level = 1;
    private static double score;
    private static int lines;
    private final static Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.YELLOW, Color.RED};
    private static boolean gameOver = false;
    private Group root = new Group();
    private Canvas canvi = new Canvas();
    private StackPane pane = new StackPane();
    private Scene theScene;
    private Stage theStage;
    Timeline timeline;
    
    Canvas canvas = new Canvas(WIDTH, HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    /**
     * Creates a new Tetris game with pieces and board.
     */
    public Tetris() {
	 currentPiece = new Piece();
	 nextPiece = new Piece();
	 board = new Board();
    }// Tetris
    
    /**
     * Restarts the game
     */
    public void restart() {
	 currentPiece = new Piece();
	 nextPiece = new Piece();
	 board = new Board();
	 root.getChildren().clear();
	 paint(gc);
	 root.getChildren().add(canvas);
    }// restart
	
    /**
     * Handles majority of the Tetris operations.
     *
     * @param primaryStage  the Stage that the Tetris scene will be on.
     */
    @Override
    public void start(Stage primaryStage) {
	theStage = primaryStage;
	theStage.setTitle("Tetris");
    	
	paint(gc);
	
	root.getChildren().add(canvas);

	Scene sc = new Scene(root);

	theStage.setScene(sc);
	theStage.sizeToScene();
	theStage.show();
	
	sc.setOnKeyReleased(
			    new EventHandler<KeyEvent>()
			    {
				@Override
				    public void handle(KeyEvent e){
				    KeyCode daKey = e.getCode();
				    switch(daKey){
				    case UP:
					if(board.canMove(currentPiece, 0, -1)) currentPiece.rotate();
					break;
				    case DOWN:
					if(board.canMove(currentPiece, 0, 1)) {
					    currentPiece.moveDown();
					    score += 10 + level;
					}else updateBoard();
					break;
				    case LEFT: 
					if(board.canMove(currentPiece, -1, 0)) currentPiece.moveLeft();
					break;
				    case RIGHT:
					if(board.canMove(currentPiece, 1, 0)) currentPiece.moveRight();
					break;
				    case SPACE:
					while(board.canMove(currentPiece, 0, 1)) {
					    currentPiece.moveDown();
					    score += 10 + level;
					}
					updateBoard();
					break;
				    }
				    drawBoard(gc);
				    drawPiece(gc);
				    drawNextPiece(gc);
				    if(gameOver) {
					timeline.pause();
					gc.setFill(Color.GRAY);
					gc.fillRect(X_OFFSET + 2*BLOCK_SIZE, Y_OFFSET + 8*BLOCK_SIZE, 6*BLOCK_SIZE, 4*BLOCK_SIZE);
					gc.setFill(Color.WHITE);
					gc.fillText("GAME OVER.", X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 10*BLOCK_SIZE - 10);
					gc.fillText("Score: "+(int)score, X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 11*BLOCK_SIZE - 10);
					
					//System.exit(0);
				    }					  
				}
			    });
	
	EventHandler<ActionEvent> handler = event -> update();
	timeline = new Timeline(new KeyFrame(
					     Duration.millis(2500),
					     handler));
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.play();
	theStage.show();
    }// start
    
    
    /**
     * Creates the board for the user
     */
    public Group createContent() {

	Group root = new Group(); 
	canvi = new Canvas(WIDTH*BLOCK_SIZE, HEIGHT*BLOCK_SIZE);
	GraphicsContext g = canvi.getGraphicsContext2D();
	root.prefWidth(WIDTH*BLOCK_SIZE);
	root.prefHeight(HEIGHT*BLOCK_SIZE);
	root.getChildren().add(canvi);

	return root; 
    }// createContent

    /**
     * Creates the initial display for the game
     *
     * @param g2d  a GraphicsContext that handles all the initializes of the Tetris Board
     */
    //@Override
    public void paint(GraphicsContext g2d) {
	    
	g2d.setFont(new Font("Comic Sans MS", 20));
	g2d.setFill(Color.GRAY);
	g2d.fillRect(0, 0, WIDTH, HEIGHT);
	g2d.setFill(Color.WHITE);
	g2d.fillText("Next Piece:", NEXTPIECEX, NEXTPIECEY-10);
	g2d.fillText("Score: "+(int)score, NEXTPIECEX, 390);
	g2d.fillText("Lines: "+lines, NEXTPIECEX, 430);
	g2d.fillText("Level: "+level, NEXTPIECEX, 470);
	drawBoard(g2d);
	drawPiece(g2d);
	drawNextPiece(g2d);
	System.out.println("painted!");
	if(gameOver) {
	    timeline.pause();
	    g2d.setFill(Color.GRAY);
	    g2d.fillRect(X_OFFSET + 2*BLOCK_SIZE, Y_OFFSET + 8*BLOCK_SIZE, 6*BLOCK_SIZE, 4*BLOCK_SIZE);
	    g2d.setFill(Color.WHITE);
	    g2d.fillText("GAME OVER", X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 10*BLOCK_SIZE - 10);
	    g2d.fillText("Score: "+(int)score, X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 11*BLOCK_SIZE - 10);
	}
    }// paint
    
    /**
     * Drwas the piece in the Tetris grid.
     *
     * @param g2d   a GraphicsContext that handles all the initializes of the Tetris Board
     */
    private void drawPiece(GraphicsContext g2d) {
	int x = currentPiece.getxPos();
	int y = currentPiece.getyPos();
	for(int j=0; j<Piece.PIECE_SIZE; j++)
	    for(int i=0; i<Piece.PIECE_SIZE; i++) {
		if(currentPiece.getBlock(j, i) !=0) {
		    g2d.setFill(colors[currentPiece.getBlock(j, i)]);
		    g2d.fillRect(X_OFFSET+(x+i)*BLOCK_SIZE, Y_OFFSET+(y+j)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
		}
	    }
    }// drawPiece

    /**
     * Draws the piece in the view window.
     * 
     * @param g2d  a GraphicsContext that handles all the initializes of the Tetris Board
     */
    private void drawNextPiece(GraphicsContext g2d) {
	for(int j=0; j<Piece.PIECE_SIZE; j++)
	    for(int i=0; i<Piece.PIECE_SIZE; i++) {
		g2d.setFill(colors[nextPiece.getBlock(j, i)]);
		g2d.fillRect(NEXTPIECEX+i*BLOCK_SIZE, NEXTPIECEY+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	    }
    }// drawNextPiece
    
    /**
     * Draws the board within the Tetris grid.
     *
     * @param g2d    a GraphicsContext that handles all the initializes of the Tetris Board
     */
    private void drawBoard(GraphicsContext g2d) {
	for(int j=0; j<Board.HEIGHT; j++)
	    for(int i=0; i<Board.WIDTH; i++) {

		//System.out.println(board.getBlock(j, i));
		//System.out.println(colors[board.getBlock(j, i)]);
		//System.out.println("made it.");

		g2d.setFill(colors[board.getBlock(j, i)]);
		g2d.fillRect(X_OFFSET+i*BLOCK_SIZE, Y_OFFSET+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	    }
    }// drawBoard
    
    /**
     * Moves piece and updates board.
     */
    public void update() {
	if(board.canMove(currentPiece, 0, 1)){
	    currentPiece.moveDown();
	    drawBoard(gc);
	    drawPiece(gc);
	}
	else {
	    updateBoard();
	}
    }// update
    
    /**
     * Updates the board 
     */
    private void updateBoard() {
	board.store(currentPiece);
	currentPiece = nextPiece;
	nextPiece = new Piece();
	int num = board.deletePossibleLines();
	lines += num;
	score += ( (num-1)/10.0*num + num ) * (Board.WIDTH*10) + level*10;
	level = (int) (score/10000) + 1;
	if(!board.canMove(currentPiece, 0, 1)) {
	    gameOver = true;
	}
    }// updateBoard
    
    public static void main(String[] args) {
	Application.launch(args);
    }// main
    
}// Tetris