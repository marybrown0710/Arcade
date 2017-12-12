package cs1302.arcade;

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
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Checkers{

    public static final int TILE_SIZE = 60;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    //actual array
    CheckersTile[][] chBoard = new CheckersTile[WIDTH][HEIGHT];

    Group tileGroup = new Group();
    Group pieceGroup = new Group();
    int redCount = 12;
    int whiteCount = 12;

    public Checkers()
    {

    }

    Group createBoard()
    {
	Group root = new Group();
	root.prefWidth(WIDTH * TILE_SIZE);
	root.prefHeight(HEIGHT * TILE_SIZE);
	root.getChildren().addAll(tileGroup,pieceGroup);

	for (int r = 0; r < HEIGHT; r++)
	   {
	    for (int c = 0; c < WIDTH; c++)
		{
		    CheckersTile tile = new CheckersTile(((r+c)%2==0), r, c);
		    chBoard[r][c] = tile; 
		    tileGroup.getChildren().add(tile);

		    CheckersPiece piece = null;
		    if (c <= 2 && (r + c) % 2 != 0)
			{
			    piece = makePiece(PieceType.RED,r,c);
			    Game(piece);
			}

		    if (c >= 5 && (r + c) % 2 != 0)
			{
			    piece = makePiece(PieceType.WHITE,r,c);
			    Game(piece);
			}

		    if (piece != null)
			{
			    tile.setPiece(piece);
			    pieceGroup.getChildren().add(piece);
			}
		}
	   }
	return root;
    } 

    private int toBoard(double pixel)
    {
	return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

   CheckersPiece makePiece(PieceType type, int x, int y) { 
         CheckersPiece piece = new CheckersPiece(type, x, y); 
 
	 return piece;
   }

    public void Game(CheckersPiece piece)
    {
         piece.setOnMouseReleased(e -> { 
             int newX = toBoard(piece.getLayoutX()); 
             int newY = toBoard(piece.getLayoutY()); 
	     System.out.println(newX);
	     System.out.println(newY);
 
             MoveResult result; 
	     int x0 = toBoard(piece.getOldX());
	     int y0 = toBoard(piece.getOldY());
 
             if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT || chBoard[newX][newY].pieceOn() == true || (newX + newY) % 2 == 0) { 
                 result = new MoveResult(MoveType.NONE); 
		 piece.abortMove();
             } 
	     else 
		 {                  	
		     if(Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().movDir)
		     {
			 result = new MoveResult(MoveType.NORMAL);
			 piece.move(newX,newY);
			 chBoard[x0][y0].setPiece(null);
			 chBoard[newX][newY].setPiece(piece);
		     }
		     else if(Math.abs(newX - x0) == 1 && newY - y0 != piece.getType().movDir)
		     {
			 piece.abortMove();
		     }
		     else if(Math.abs(newX - x0) == 2 && newY - y0 != piece.getType().movDir * 2)
		     {
			 piece.abortMove();
		     }
		     else if(Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().movDir * 2)
		     {
			 int x1 = x0 + (newX - x0) / 2;
			 int y1 = y0 + (newY - y0) / 2;
			 if (chBoard[x1][y1].pieceOn() && chBoard[x1][y1].getChPiece().getType() != piece.getType())
			     {
				 result = new MoveResult(MoveType.KILL, chBoard[x1][y1].getChPiece());
				 piece.move(newX,newY);
				 chBoard[x0][y0].setPiece(null);
				 chBoard[newX][newY].setPiece(piece);
				 CheckersPiece otherP = result.getPiece();
				 pieceGroup.getChildren().remove(otherP);
				 if (otherP.getType() == PieceType.RED)
				     {
					 redCount--;
				     }
				 else
				     {
					 whiteCount--;
				     }
			     }
			 else
			     {
				 piece.abortMove();
			     }
		     } //elseif
		 }//else
	     System.out.println("RED PLAYER SCORE: " + (12 - whiteCount));
	     System.out.println("WHITE PLAYER SCORE: " + (12 - redCount));
	     if (12 - whiteCount == 0)
		 {
		     System.out.println("Game Over. RED Wins!!");
		 }
	     else if (12 - redCount == 0)
		 {
		     System.out.println("Game Over. WHITE Wins!!");
		 }
	     });
    }//Game

    //   public void PlayGame()
    // {
    //	int player1Turns, player2Turns = 0;
    //	createBoard();
    //	System.out.println("Click the space where you want to move")
    // setOnMouseClicked()( e -> {
    // int desiredX = toBoard(getX())
    // int desiredY = toBoard(getY())
    //});
    //if clickCount == 1
    //{
    //}

    //  public int updatePieceCount(PieceType type)
    // { 
    //	
    //	if (type == PieceType.RED)
    //	    {
    //		return redCount;
    //	    }
    //	else
    //	    {
    //		return whiteCount;
    //	    }
    //}

    public boolean outOfMoves(PieceType type)
    {
	int numPieces = 0;
	int addFactor = type.movDir;
	int killAddFactor = type.movDir * 2;
	for (int i = 0; i < 8; i++)
	    {for (int v = 0; v < 8; v++)
		    {if (chBoard[i][v].pieceOn() == true)
			    {
				if (chBoard[i][v].getChPiece().getType() == type)
				    {
					numPieces++;
					if ((i + addFactor <= 7) && (i + addFactor >= 0) && (v + addFactor >= 0) && (v + addFactor <= 7) && chBoard[i + addFactor][v + addFactor].pieceOn() == false)
					    return false;
					else if ((i + killAddFactor <= 7) && (i + killAddFactor >= 0) && (v + killAddFactor >= 0) && (v + killAddFactor <= 7) && chBoard[i + addFactor][v + addFactor].pieceOn() == true)
					    return false;
				    }
			    }
		    }
	    }
	return true; 
    }

}



                                
