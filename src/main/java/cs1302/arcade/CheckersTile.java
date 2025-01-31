package cs1302.arcade;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CheckersTile extends Rectangle{

    private CheckersPiece piece;

    public CheckersPiece getChPiece()
    {
	return piece;
    }

    public boolean pieceOn()
    {
	if (getChPiece() == null)
	    {
		return false;
	    }
	else
	    {
		return true;
	    }
    }

    public void setPiece(CheckersPiece piece)
    {
	this.piece = piece;
    }

    public CheckersTile(boolean red, int x, int y)
    {
	setHeight(Checkers.TILE_SIZE);
	setWidth(Checkers.TILE_SIZE);

	relocate(x * Checkers.TILE_SIZE, y * Checkers.TILE_SIZE);

	if (red == true)
	    {
		setFill(Color.RED);
	    }
	else
	    {
		setFill(Color.BLACK);
	    }
    }

    boolean red(int x, int y)
    {
	if ((x + y) % 2 == 0)
	    {
		return true;
	    }
	else
	    {
		return false;
	    }
    }
}