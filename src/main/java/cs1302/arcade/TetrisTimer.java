package cs1302.arcade;

import java.util.TimerTask;


public class TetrisTimer extends TimerTask {

	private Tetris game;
	public TetrisTimer(Tetris game) {
		this.game = game;
	}
	@Override
	public void run() {
		game.timer();
	}

}// TetrisTimer