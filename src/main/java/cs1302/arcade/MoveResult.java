package cs1302.arcade;

public class MoveResult {

    private MoveType type;
    private CheckersPiece piece;

    public MoveType getMoveType()
    {
	return type;
    }

    public CheckersPiece getPiece()
    {
	return piece;
    }

    public MoveResult(MoveType type)
    {
	this(type, null);
    }

    public MoveResult(MoveType type, CheckersPiece piece)
    {
	this.piece = piece;
    }

}