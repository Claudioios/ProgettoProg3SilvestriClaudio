package overlay;

import static utilz.Constants.GameConstants.TOTAL_HEIGHT;
import static utilz.Constants.GameConstants.TOTAL_WIDTH;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import gamestates.Playing;
import ui.OverlayButton;

/** 
 * Overlay è la superclasse che gestisce i menu di Overlay durante il gioco, contiene informazioni riguardo la dimensione e la posizione
 * del background dei men u di Overlay
 * 
 * @See GameOverOverlay
 * @See PauseOverlay
 * @See Playing
 * @Author Silvestri Claudio
 */

public class Overlay implements Serializable {

	private static final long serialVersionUID = -4218277768597465260L;
	
	protected int bgX, bgY, bgW, bgH;
	protected Playing playing;

	
	public Overlay(Playing playing) {
		
		this.playing = playing;
		
	}
	
	/** 
	 * creo le dimensioni del background dei menu di overlay
	 */
	public void loadBackground() {
		
		//Posizione e dimensione del background dei menu di overlay
		bgW = TOTAL_WIDTH;
		bgH = TOTAL_HEIGHT;
		bgX = (TOTAL_WIDTH / 2) - (bgW / 2);
		bgY = (TOTAL_HEIGHT / 2) - (bgH / 2);
		
	}
	
	public boolean isIn(MouseEvent e, OverlayButton b) {
		
		return (b.getBounds().contains(e.getX(), e.getY()));
			
	}
}
