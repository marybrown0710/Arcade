package cs1302.arcade;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.*;
import javafx.util.Duration;



/**
 * Handles all operations of the timer for the Tetris game
 *
 * @author Mary Brown
 */


public class TetrisTimer {
    final Timeline timeline = new Timeline();

    private Tetris game;
    
    /**
     * Instantiates the <code>game</code> for future use.	
     *
     * @param game  a Tetris game
     */
    public TetrisTimer(Tetris game) {
	this.game = game;

	EventHandler<ActionEvent> handler = event -> game.update();
	KeyFrame keyFrame = new KeyFrame(new Duration(1000 / 60), handler);
	//TimeLine timeline = new Timeline();
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	//timeline.play(); 
    }// TetrisTimer

    /**
     * Overrides the original run method. Starts the timer for the
     * Tetris game.
     */
    public void run() {
	timeline.play();
        }// run

    public void cancel() {
	timeline.pause();
    }
}// TetrisTimer