 package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import gamestates.Gamestate;
import main.GamePanel;

/** 
 * MouseInputs è la classe che gestisce gli input da mouse dell'utente
 * 
 * @See GamePanel
 * @Author Silvestri Claudio
 */
public class MouseInputs implements MouseListener, Serializable {

	private static final long serialVersionUID = 1244087813397223742L;
	
	
	private GamePanel gamePanel;
	
	public MouseInputs(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		
	}

	/** 
	 * funzione che gestisce i click dell'utente in base allo stato di gioco in cui si trova
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousePressed(e); //gestisco i click che avvengono nel menu principale
			break;
		case LOAD:
			gamePanel.getGame().getLoad().mousePressed(e); //gestisco i click che avvengono nel menu di load
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousePressed(e); //gestisco i click che avvengono durante il gioco
			break;
		default:
			break;
		
		}
		   
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
