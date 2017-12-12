package cs1302.arcade;

import java.util.Random;

/**
 * Hhandles the creation and detailing of all Tetris pieces.
 *
 * @author Mary Brown
 */

public class Piece {

    private int xPos, yPos;
    private int type, rotation;
    final static int PIECE_SIZE = 4;
    private final static int[][][][] PIECES = {
	//I type
	{
	    {
		{0,1,0,0},
		{0,1,0,0},
		{0,1,0,0},
		{0,1,0,0}				
	    },
	    {
		{0,0,0,0},
		{1,1,1,1},
		{0,0,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,1,0,0},
		{0,1,0,0},
		{0,1,0,0},
		{0,1,0,0}				
	    },
	    {
		{0,0,0,0},
		{1,1,1,1},
		{0,0,0,0},
		{0,0,0,0}				
	    }
	},
	//Z type
	{
	    {
		{0,0,0,0},
		{2,2,0,0},
		{0,2,2,0},
		{0,0,0,0}				
	    },
	    {
		{0,2,0,0},
		{2,2,0,0},
		{2,0,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{2,2,0,0},
		{0,2,2,0},
		{0,0,0,0}				
	    },
	    {
		{0,2,0,0},
		{2,2,0,0},
		{2,0,0,0},
		{0,0,0,0}				
	    }
	},
	//Z reversed
	{
	    {
		{0,0,0,0},
		{0,3,3,0},
		{3,3,0,0},
		{0,0,0,0}				
	    },
	    {
		{3,0,0,0},
		{3,3,0,0},
		{0,3,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{0,3,3,0},
		{3,3,0,0},
		{0,0,0,0}				
	    },
	    {
		{3,0,0,0},
		{3,3,0,0},
		{0,3,0,0},
		{0,0,0,0}				
	    }
	},
	//T type
	{
	    {
		{0,4,0,0},
		{4,4,4,0},
		{0,0,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,4,0,0},
		{0,4,4,0},
		{0,4,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{4,4,4,0},
		{0,4,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,4,0,0},
		{4,4,0,0},
		{0,4,0,0},
		{0,0,0,0}				
	    }
	},
	//L type
	{
	    {
		{0,5,0,0},
		{0,5,0,0},
		{0,5,5,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{5,5,5,0},
		{5,0,0,0},
		{0,0,0,0}				
	    },
	    {
		{5,5,0,0},
		{0,5,0,0},
		{0,5,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,5,0},
		{5,5,5,0},
		{0,0,0,0},
		{0,0,0,0}				
	    }
	},
	//L reversed
	{
	    {
		{0,6,0,0},
		{0,6,0,0},
		{6,6,0,0},
		{0,0,0,0}				
	    },
	    {
		{6,0,0,0},
		{6,6,6,0},
		{0,0,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,6,6,0},
		{0,6,0,0},
		{0,6,0,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{6,6,6,0},
		{0,0,6,0},
		{0,0,0,0}				
	    }
	},
	//Box type
	{
	    {
		{0,0,0,0},
		{0,7,7,0},
		{0,7,7,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{0,7,7,0},
		{0,7,7,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{0,7,7,0},
		{0,7,7,0},
		{0,0,0,0}				
	    },
	    {
		{0,0,0,0},
		{0,7,7,0},
		{0,7,7,0},
		{0,0,0,0}				
	    }
	}
	
    };
    private static int[][][] startPos = {
	//I type
	{
	    {3,0},
	    {3,-1},
	    {3,0},
	    {3,-1}
	},
	//Z type
	{
	    {3,-1},
	    {3,0},
	    {3,-1},
	    {3,0}
	},
	//Z reversed
	{
	    {3,-1},
	    {3,0},
	    {3,-1},
	    {3,0}
	},
	//T type
	{
	    {3,0},
	    {3,0},
	    {3,-1},
	    {3,0}
	},
	//L type
	{
	    {3,0},
	    {3,-1},
	    {4,0},
	    {4,0}
	},
	//L reversed
	{
	    {4,0},
	    {3,0},
	    {3,0},
	    {3,-1},
	},
	//Box type
	{
	    {3,-1},
	    {3,-1},
	    {3,-1},
	    {3,-1}
	}
    };

    /**
     * Manifests a random tetris piece to be utilised by the player.
     */
    public Piece () {
	Random r = new Random();
	type = r.nextInt(7);
	rotation = r.nextInt(4);
	yPos = startPos[type][rotation][1];
	xPos = startPos[type][rotation][0];
    }//Piecex

    /**
     * Manifests a specific tetris piece to be utilised by the player. Sets the position, type and
     * current rotation of the current piece.
     * 
     * @param p   a specific tetris piece
     */
    public Piece(Piece p) {
	xPos = p.getxPos();
	yPos = p.getyPos();
	type = p.getType();
	rotation = p.getRotation();
    }// Piece
    
    /**
     * Gets the horizontal position of the piece within the grid.
     *
     * @return the x position of piece.
     */
    public int getxPos() {
	return xPos;
    }// getxPos
    
    /**
     * Gets the vertical position of the piece within the grid.
     *
     * @return the y position of piece.
     */
    public int getyPos() {
	return yPos;
    }// getyPos
    
    /**
     * Gets the type of the current piece.
     *
     * @return the type of piece.
     */
    public int getType() {
	return type;
    }// getType
    
    /**
     * Gets the current rotation position of the current piece.
     *
     * @return the rotation of piece.
     */
    public int getRotation() {
	return rotation;
    }// getRotation
    
    /**
     * Gets the size of the current piece.
     *
     * @return the size of piece.
     */
    public static int getPieceSize() {
	return PIECE_SIZE;
    }// getPieceSize
    
    /**
     * Gets the piece (whose shape is represented in binary)
     *
     * @return the piece (in binary)
     */
    public int[][] getPiece() {
	return PIECES[type][rotation];
    }// getPieces

    /**
     * Gets the block at that certain coordinare on the grid. Returns a <code>0</code>
     * if the block at the coordinate is empty, and a <code>1</code> if occupied.
     *
     * @return the occupancy of the square within the grid.
     */
    public int getBlock(int x, int y) {
	return PIECES[type][rotation][x][y];
    }// getBlock
    
    /**
     * Moves the piece one unit to the left.
     */
    public void moveLeft() {
	xPos--;
    }// moveLeft
    
    /**
     * Moves the piece one unit to the right.
     */
    public void moveRight() {
	xPos++;
    }// moveRight
    
    /**
     * Moves the piece one unit down.
     */
    public void moveDown() {
	yPos++;
    }// moveDown
    
    /**
     * Rotates the piece.
     */
    public void rotate() {
	rotation = (rotation+1)%4;
    }// rotate
    
    /**
     * Manifests a specific tetris piece then rotates it.
     *
     * @return the new and rotates piece
     */
	public Piece rotatedPiece() {
		Piece q = new Piece(this);
		q.rotate();
		return q;
	}
}// Piece
