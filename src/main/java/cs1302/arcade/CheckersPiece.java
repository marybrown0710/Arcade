package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class CheckersPiece extends StackPane {

    PieceShape piece; 

    public CheckersPiece(Color col,int x, int y)
    {
	relocate(x * Checkers.TILE_SIZE, y * Checkers.TILE_SIZE);

	Ellipse gp = new Ellipse(Checkers.TILE_SIZE * .3125, Checkers.TILE_SIZE * .26);

	if (y <= 2 && (x + y) % 2 != 0)
	    {
		gp.setFill(Color.RED);
	    }
	else
	    {
		gp.setFill(Color.WHITE);
	    }

	gp.setStroke(Color.BLUE);
	gp.setStrokeWidth(Checkers.TILE_SIZE * .03);
	gp.setTranslateX((Checkers.TILE_SIZE - Checkers.TILE_SIZE * .3125 * 2)/2);
	gp.setTranslateY((Checkers.TILE_SIZE - Checkers.TILE_SIZE * .26 * 2)/2);

	getChildren().addAll(gp);
    }

}


