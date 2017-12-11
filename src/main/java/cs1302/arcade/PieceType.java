package cs1302.arcade;

public enum PieceType {

    RED(1),WHITE(-1);

    final int movDir;

    PieceType(int movDir)
	{
	    this.movDir = movDir;
	}

}