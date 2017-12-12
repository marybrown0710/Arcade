package cs1302.arcade;

/**
 * This class handles all of the function and operations regarding the Tetris Board.
 *
 * @author Mary Brown
 */
public class Board {
	public final static int HEIGHT = 20;
	public final static int WIDTH = 10;
	private int[][] boardMatrix;

    /**
     *Creates a 2D array representing the tiles of the tetris board.
     */
    public Board() {
	boardMatrix = new int[HEIGHT][WIDTH];
	for (int i = 0; i< HEIGHT; i++){
	    for (int j = 0; j < WIDTH; j++){
		boardMatrix[i][j] = 0;
	    }
	}
    }// Board

    /**
     * Stores the piece, if applicable, into a matrix representing piece 
     * placement on the Board.
     *
     * @param p  a tetris piece
     * @throws ArrayIndexOutOfBoundsException  position cannot be stored
     * @return whether or not the piece has been stored
     */
    public boolean store(Piece p) {
	int x = p.getxPos();
	int y = p.getyPos();
	try {
	    for(int j = 0; j < Piece.PIECE_SIZE; j++)
		for(int i = 0; i < Piece.PIECE_SIZE; i++)
		    if(p.getBlock(j, i)!=0)
			boardMatrix[y+j][x+i] = p.getBlock(j, i);
	} catch(ArrayIndexOutOfBoundsException e) {
	    return false;
	}
	return true;
    }// store
    
    /**
     * Removes the completed line from view.
     *
     * @param line  a number representing the preferred line to check
     */
    private void deleteLine(int line) {
	for(int j = line; j > 0; j--)
	    boardMatrix[j] = boardMatrix[j-1];
	boardMatrix[0] = new int[WIDTH];
    }// deleteLine
    
    /**
     * Checks grid to see if any lines are completed entirely. If the line
     * has a block on the entirety of the line, it is then deleted.
     *
     * @return the number of lines successfully deleted
     */
    public int deletePossibleLines() {
	int deletedLines = 0;
	int j = HEIGHT-1;
	while(j>=0) {
	    boolean deletePossible = true;
	    for(int i = 0; i < WIDTH; i++)
		if(boardMatrix[j][i]==0) {
		    deletePossible = false;
		    break;
		}
	    if(deletePossible) {
		deleteLine(j);
		deletedLines++;
	    }
	    else
		j--;
	}
	return deletedLines;
    }// deletePossibleLines
    
    /**
     * Determines whether or not the Tetris piece can be into the desired position.
     *
     * @param p       a Tetris Piece
     * @param xDisp   the desired horizontal position of the piece
     * @param yDisp   the desired vertical position of the piece
     * @throws ArrayIndexOutOfBoundsException the piece cannot be stored in grid if moved to desired position
     * @return whether or not the piece can be moved 
     */
    public boolean canMove(Piece p, int xDisp, int yDisp) {
	if(yDisp < 0) {
	    p = p.rotatedPiece();
	    yDisp = 0;
	}
	int x = p.getxPos() + xDisp;
	int y = p.getyPos() + yDisp;
	try {
	    for(int j = 0; j < Piece.PIECE_SIZE; j++)
		for(int i = 0; i < Piece.PIECE_SIZE; i++)
		    if(p.getBlock(j, i)!=0)
			if(boardMatrix[y+j][x+i] !=0 )
			    return false;
	} catch(ArrayIndexOutOfBoundsException e) {
	    return false;
	}
	return true;
    }// canMove
    
    /**
     * Gets the occupancy of the block on the Tetris Board. Returns a <code>1</code>
     * if the gris is occupied with a block, and a <code>0</code> if it is non-occpied.
     *
     * @param x   an integer representing the x coordinate of the preferred block
     * @param y   an integer representing the y coordinate of the preferred block
     * @return the occupancy of the block
     */
    public int getBlock(int y, int x) {
	//System.out.println("board.getBlock[][] = " + boardMatrix[y][x]);
	return boardMatrix[y][x];
    }// getBlock

}// Board

