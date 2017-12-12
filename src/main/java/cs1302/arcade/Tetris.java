package cs1302.arcade;

//import java.applet.Applet;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.Timer;
import javafx.scene.layout.*;
import java.util.Timer;
<<<<<<< HEAD
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.animation.*;



public class Tetris extends Group {

    private static final int HEIGHT = 680, WIDTH = 640, BLOCK_SIZE = 32;
    private static final int X_OFFSET = 20, Y_OFFSET = 20, NEXTPIECEX = 360, NEXTPIECEY = 40;
    private static Piece currentPiece;
    private static Piece nextPiece;
    private static Board board;
    private static int level = 1;
    private static double score;
    private static int lines;
    private final static Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.YELLOW, Color.RED};
    private static TetrisTimer timerTask;
    private static Timer timer;
    private static boolean gameOver = false;
    private Group root = new Group(); 
    //private GraphicsContext g;
    private Canvas canvi = new Canvas(WIDTH*BLOCK_SIZE, HEIGHT*BLOCK_SIZE);
    private StackPane pane = new StackPane();
    private Scene theScene;
    
    private KeyEvent k;
  
    /**
     * Creates a new Tetris game with pieces and board.
     */
    public Tetris() {
	
    	currentPiece = new Piece();
    	nextPiece = new Piece();
    	board = new Board();
    	//timer = new Timer();
    	//timerTask = new TetrisTimer(this);
    	//timer.scheduleAtFixedRate(timerTask, 1000, 1000);

    // 	//setFocusable(true);
    // 	//this.requestFocusInWindow();
    // 	//this.addKeyListener(this);
    // 	//repaint();
	
    // 	//return pane;
    }// Tetris

    /**
     * Handles majority of the Tetris operations.
     *
     * @param primaryStage  the Stage that the Tetris scene will be on.
     */
    public void start(Stage primaryStage) {

    	root = createContent();
	
	//scene.setOnKeyPressed(e -> keyReleased(e));
	theScene = new Scene(root, WIDTH*BLOCK_SIZE, HEIGHT*BLOCK_SIZE, Color.WHITE);
	//set BorderPane
	//BorderPane tPane = new BorderPane();
	//tPane.prefHeightProperty().bind(theScene.heightProperty());
	//tPane.prefWidthProperty().bind(theScene.widthProperty());

	//borderPane.setTop(menuBar);
	//root.getChildren().add(tPane);

	theScene.setOnKeyReleased(   //??
				  new EventHandler<KeyEvent>()
				  {
				      @Override
					  public void handle(KeyEvent e){
					  
					  if (e.getCode() == KeyCode.UP){
					      if(board.canMove(currentPiece, 0, -1)) currentPiece.rotate();
					  } else if (e.getCode() == KeyCode.DOWN){
					      if(board.canMove(currentPiece, 0, 1)) {
						  currentPiece.moveDown();
						  score += 10 + level;
					      }else updateBoard();
					  } else if (e.getCode() == KeyCode.LEFT){
					      if(board.canMove(currentPiece, -1, 0)) currentPiece.moveLeft();
					  } else if (e.getCode() == KeyCode.RIGHT){
					      if(board.canMove(currentPiece, 1, 0)) currentPiece.moveRight();
					  } else if (e.getCode() == KeyCode.SPACE){
					      while(board.canMove(currentPiece, 0, 1)) {
						  currentPiece.moveDown();
						  score += 10 + level;
					      }
					      updateBoard();
					  }
				   
				      }
				  });
=======
import javafx.scene.layout.StackPane;

public class Tetris extends Applet implements KeyListener{

	private static final int HEIGHT = 680, WIDTH = 640, BLOCK_SIZE = 32;
	private static final int X_OFFSET = 20, Y_OFFSET = 20, NEXTPIECEX = 360, NEXTPIECEY = 40;
	private static Piece currentPiece, nextPiece;
	private static Board board;
	private static int level = 1;
	private static double score;
	private static int lines;
	private final static Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.YELLOW, Color.RED};
	private static TetrisTimer timerTask;
	private static Timer timer;
	private static boolean gameOver = false;
	

    // @Override
    //	public StackPane init() {
    //	    StackPane pane = new StackPane();
    //	}

	@Override
	public void init() {
	

		setSize(WIDTH, HEIGHT);
		currentPiece = new Piece();
		nextPiece = new Piece();
		board = new Board();
		timer = new Timer();
		timerTask = new TetrisTimer(this);
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);
		setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		repaint();

		//return pane;
	}
>>>>>>> 553cbb9a2e6b5615db17daa3809a371ede3e064d
	
	AnimationTimer timer = new AnimationTimer() {
		@Override
		    public void handle(long now) {
		    update();
		}
	    };
	timer.start();
    }// start

    /**
     * Gets the current scene.
     *
     * @return the current scene
     */
    public Scene getTheScene() {
	return theScene;
    }

    /**
     * Gets the root node of the game.
     *
     * @return the root of the game
     */
    public Group getRoot() {
	return root;
    }
	
	//scene.setOnKeyPressed(e -> keyReleased(e));
	
	
	//primaryStage.setScene(scene);
	//primaryStage.show();
    //}// start

    // public static void main(String[] args) {
    // 	try {
    // 	    Application.launch(args);
    // 	} catch (UnsupportedOperationException e) {
    // 	    System.out.println(e);
    // 	    System.err.println("If this is a DISPLAY problem, then your X server connection");
    // 	    System.err.println("has likely timed out. This can generally be fixed by logging");
    // 	    System.err.println("out and logging back in.");
    // 	    System.exit(1);
    // 	} // try
    // }// main

    /**
     * Creates the board for the user
     */
    public Group createContent() {

	//Group root = new Group(); 
	//timerTask = new TetrisTimer(this);
	//timer.scheduleAtFixedRate(timerTask, 1000, 1000);
	
	//pane.setPrefSize(WIDTH*BLOCK_SIZE, HEIGHT*BLOCK_SIZE);
	GraphicsContext g = canvi.getGraphicsContext2D();
	paint(g);
	//pane.getChildren().addAll(canvi);
	//root.getChildren().add(canvi);
	
	
	//pane.getChildren().addAll(canvi);
	root.getChildren().add(canvi);
	
	//theScene = new Scene(root);
	//timerTask.run();
	
	//pane.getChildren().add(canvi);

	return root; //??
	//return canvi; 
    }// createContent

    /**
     * Creates the initial display for the game
     *
     * @param g2d  a GraphicsContext that handles all the initializes of the Tetris Board
     */
    //@Override
    public void paint(GraphicsContext g2d) {
	    
	    
	    //Graphics2D g2d = (Graphics2D)g;   //??!!??
	    g2d.setFont(new Font("Comic Sans MS", 20));
	    g2d.setFill(Color.GRAY);
	    g2d.fillRect(0, 0, WIDTH, HEIGHT);
	    g2d.setFill(Color.WHITE);
	    g2d.fillText("Next Piece:", NEXTPIECEX, NEXTPIECEY-10);
	    //Text t1 = new Text((double)NEXTPIECEX, (double)NEXTPIECEY-10, "Next Piece:");
	    g2d.fillText("Score: "+(int)score, NEXTPIECEX, 390);
	    //Text t2 = new Text((double)NEXTPIECEX, 390.0, "Score: "+(int)score);
	    g2d.fillText("Lines: "+lines, NEXTPIECEX, 430);
	    //Text t3 = new Text((double)NEXTPIECEX, 470, "Lines: "+lines);
	    g2d.fillText("Level: "+level, NEXTPIECEX, 470);
	    //Text t4 = new Text((double)NEXTPIECEX, 470, "Level: "+level);
	    // g2d.drawString("Game developed by:", NEXTPIECEX, 550);
	    // g2d.drawString("Chinmay Pednekar", NEXTPIECEX+64, 590);
	    // g2d.drawString("ChiP™", NEXTPIECEX+64, 630);
	    drawBoard(g2d);
	    drawPiece(g2d);
	    drawNextPiece(g2d);
	    System.out.println("painted!");
	    if(gameOver) {
		timerTask.cancel();
		g2d.setFill(Color.GRAY);
		g2d.fillRect(X_OFFSET + 2*BLOCK_SIZE, Y_OFFSET + 8*BLOCK_SIZE, 6*BLOCK_SIZE, 4*BLOCK_SIZE);
		g2d.setFill(Color.WHITE);
		g2d.fillText("GAME OVER", X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 10*BLOCK_SIZE - 10);
		//Text t5 = new Text(X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 10*BLOCK_SIZE - 10, "GAME OVER");
		g2d.fillText("Score: "+(int)score, X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 11*BLOCK_SIZE - 10);
		//Text t6 = new Text(X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 11*BLOCK_SIZE - 10, "Score: "+(int)score); 
		//this.removeKeyListener(this.getKeyListeners()[0]);
		//pane.getChildren().addAll(t5, t6);
	    }
	    //pane.getChildren().add(canvi);
	    //pane.getChildren().addAll(t1, t2, t3, t4);
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
    
        
    // public void keyReleased(Scene scene) {
    // 	scene.setOnKeyReleased(   //??
    // 			   new EventHandler<KeyEvent>()
    // 			   {
    // 			       @Override
    // 			       public void handle(KeyEvent e){
				   
    // 				   //KeyCode userKey = e.getCode();
				   
    // 				   //switch(userKey) {
    // 				   if (e.getCode() == KeyCode.UP){
    // 				       if(board.canMove(currentPiece, 0, -1)) currentPiece.rotate();
    // 				   } else if (e.getCode() == KeyCode.DOWN){
    // 				       if(board.canMove(currentPiece, 0, 1)) {
    // 					   currentPiece.moveDown();
    // 					   score += 10 + level;
    // 				       }else updateBoard();
    // 				   } else if (e.getCode() == KeyCode.LEFT){
    // 				       if(board.canMove(currentPiece, -1, 0)) currentPiece.moveLeft();
    // 				   } else if (e.getCode() == KeyCode.RIGHT){
    // 				       if(board.canMove(currentPiece, 1, 0)) currentPiece.moveRight();
    // 				   } else if (e.getCode() == KeyCode.SPACE){
    // 				       while(board.canMove(currentPiece, 0, 1)) {
    // 					   currentPiece.moveDown();
    // 					   score += 10 + level;
    // 				       }
    // 				       updateBoard();
    // 				   }
				   
    // 			       }
    // 			   });
    // }
    
    
   
    public void update() {
	if(board.canMove(currentPiece, 0, 1))
	    currentPiece.moveDown();
	else {
	    updateBoard();
	}
	//repaint();
    }
    
   
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
	//pane.getChildren().add(board);
    }
    
       
    

}// TetrisTimer