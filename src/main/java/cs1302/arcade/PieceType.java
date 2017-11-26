package cs1302.arcade;

public class PieceType {

    final int dir;

    public PieceType(int dir)
    {
	this.dir = dir;
    }

    boolean pieceRed(int x, int y)
    {
	if ((x+y) <= 9)
	    {
		return true;
	    }
	else
	    {
		return false;
	    }
    }

}