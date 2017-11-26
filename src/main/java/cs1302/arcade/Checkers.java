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


    CheckersTile[][] chBoard = new CheckersTile[WIDTH][HEIGHT];
    Group tileGroup = new Group();
    Group pieceGroup = new Group();

    public Checkers()
    {

    }

    StackPane createBoard()
    {
	StackPane root = new StackPane();
	root.setPrefSize(WIDTH * TILE_SIZE,HEIGHT * TILE_SIZE);
	root.getChildren().addAll(tileGroup,pieceGroup);
	Color col = null;

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
			    col = Color.RED;
			    piece = new CheckersPiece(col,r,c);
			}

		    if (c >= 5 && (r + c) % 2 != 0)
			{
			    col = Color.WHITE;
			    piece = new CheckersPiece(col,r,c);
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

    // CheckersTile[][] getBoard()
    // {
    // }

    //  boolean moveAvailable(int currX, int currY, int newX, int newY)
    // {
    //	if (newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7)
    //	    {
    //		if ((newX + newY) % 2 == 0 || chBoard[newX][newY].pieceOn() == true)
    //		    {
    //			if (pieceToJump(currX,currY) == true)
    //			    return true;
    //			else
    //			    return false;
    //		    }
    //		else if(chBoard[currX][currY].pieceOn() == true)
    //		    {
    //			if (chBoard[currX][currY].getChPiece().getFill().equals(Color.RED) && newX == (currX + 1) && (newY == currY - 1 || newY == currY + 1))
    //			    {
    //				return true;
    //			    }
    //			else if (chBoard[currX][currY].getChPiece().getFill().equals(Color.WHITE) && newX == (currX - 1) && (newY == currY - 1 || newY == currY + 1))
    ///			    return true;
    //			else
    //			    return false;
    //		    }
    //	    }
    //	else
    //	    return false;
    //}

    // boolean pieceToJump(int currX, int currY)
    // {
    //	if (chBoard[currX][currY].pieceOn() == true)
    //	    {
    //		if (chBoard[currX][currY].getChPiece().getFill().equals(Color.RED))
    //		    {
    //			if (currX + 2 <= 7 && currY + 2 <= 7 && currY - 2 >= 0)
    //			    {
    //				if (chBoard[currX + 1][currY + 1].pieceOn() == true && chBoard[currX + 1][currY + 1].getChPiece().getFill().equals(Color.WHITE) && chBoard[currX + 2][currY + 2].pieceOn() == false)
    //				    {
    //					return true;
    //				    }
    //				else if (chBoard[currX + 1][currY - 1].pieceOn() == true && chBoard[currX + 1][currY - 1].getChPiece().getFill().equals(Color.WHITE) && chBoard[currX + 2][currY - 2].pieceOn() == false)
    //				    {
    //					return true; 
    //				    }
    //			    }//if
    //			else
    //			    return false;
    //		    }//if
    //	if (chBoard[currX][currY].getChPiece().getFill().equals(Color.WHITE))
    //		    {
    //			if (currX - 2 >= 0 && currY + 2 <= 7 && currY - 2 >= 0)
    //			    { 
    //				if (chBoard[currX + 1][currY + 1].pieceOn() == true && chBoard[currX - 1][currY + 1].getChPiece().getFill().equals(Color.RED) && chBoard[currX - 2][currY + 2].pieceOn() == false)
    //				    {
    //					return true;
    //				    }
    //				else if (chBoard[currX + 1][currY - 1].pieceOn() == true && chBoard[currX - 1][currY - 1].getChPiece().getFill().equals(Color.RED) && chBoard[currX - 2][currY - 2].pieceOn() == false)
    //				    {
    //					return true; 
    //				    }
    //			    }//if
    //		    }//if
    //	    }//if
    //	else
    //	    return false;
    //}
}
                                
