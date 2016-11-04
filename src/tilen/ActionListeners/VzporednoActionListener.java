package tilen.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tilen.GameOfLife;

public class VzporednoActionListener implements ActionListener{
		
	GameOfLife game;
	
	public VzporednoActionListener(GameOfLife game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		game.vzporedno();
		
	}

}
