package gamestates;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JFrame;

import main.Game;
import ui.Button;

/** 
 * Super classe che gestisce gli stati del gioco
 * 
 * @See Load
 * @See Menu
 * @See Playing
 * @Author Silvestri Claudio
 */

public class State implements Serializable {
	
	private static final long serialVersionUID = 3057801654112771540L;
	
	protected Game game;
	protected JFrame jframe;
	
	public State(Game game, JFrame jframe) {
		
		this.game = game;
		this.jframe = jframe;
		
	}
	
	public boolean isIn(MouseEvent e, Button b) {
		
		return b.getBounds().contains(e.getX(), e.getY());
		
	}	

}
