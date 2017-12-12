package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class CheckersPiece extends StackPane {

    PieceType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType()
    {
	return type;
    }

    public double getOldX()
    {
	return oldX;
    }

    public double getOldY()
    { 
	return oldY;
    }

    public CheckersPiece(PieceType type,int x, int y)
    {
	this.type = type;

	move(x,y);

	Ellipse ellipse = new Ellipse(Checkers.TILE_SIZE * .3125, Checkers.TILE_SIZE * .26);
	ellipse.setFill(type == PieceType.RED ? Color.RED : Color.WHITE);
	ellipse.setStroke(Color.BLUE);
	ellipse.setStrokeWidth(Checkers.TILE_SIZE * .03);
	ellipse.setTranslateX((Checkers.TILE_SIZE - Checkers.TILE_SIZE * .3125 * 2)/2);
	ellipse.setTranslateY((Checkers.TILE_SIZE - Checkers.TILE_SIZE * .26 * 2)/2);

	getChildren().addAll(ellipse);

		setOnMousePressed(e -> {
		mouseX = e.getSceneX();
		mouseY = e.getSceneY();
        });

       	setOnMouseDragged(e -> {
		relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
		System.out.println("Mouse Dragged");
	    });
    }

    public void move(int x, int y)
    {
	oldX = x * Checkers.TILE_SIZE;
	oldY = y * Checkers.TILE_SIZE;
	relocate(oldX, oldY);
    }

    public void movePiece(int x, int y)
    {
	relocate(x * Checkers.TILE_SIZE,y * Checkers.TILE_SIZE);
    }

    public void abortMove()
    { 
	relocate(oldX,oldY);
    }
}


