package tilen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopActionListener implements ActionListener {
	
	GameOfLife game;
	
	public StopActionListener(GameOfLife game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		game.stop();

	}

}
