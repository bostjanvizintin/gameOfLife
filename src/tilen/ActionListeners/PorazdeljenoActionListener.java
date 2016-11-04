package tilen.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tilen.GameOfLife;

public class PorazdeljenoActionListener implements ActionListener{
		
	GameOfLife game;
	
	public PorazdeljenoActionListener(GameOfLife game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		game.porazdeljeno();
		
	}

}
