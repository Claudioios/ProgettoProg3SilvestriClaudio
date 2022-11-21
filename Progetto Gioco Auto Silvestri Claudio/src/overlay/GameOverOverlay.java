package overlay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import gamestates.Gamestate;
import gamestates.Playing;
import ui.OverlayButton;

import static utilz.Constants.UIConstants.*;

/** 
 * GameOverOverlay eredita i campi e gli attributi della classe "Overlay". gestisce il meu di GameOver, qui l'utente può ricominciare una
 * nuova partita o tornare al menu principale
 * 
 * @See PauseOverlay
 * @See Overlay
 * @See Playing
 * @Author Silvestri Claudio
 */

public class GameOverOverlay extends Overlay implements Serializable{

	private static final long serialVersionUID = -3587195885280761042L;
		
	private OverlayButton mainMenuButton;
	private OverlayButton retryButton;
	
	public GameOverOverlay(Playing playing) {
		
		super(playing);
		loadBackground(); //creo il background di game over
		createButtons(); //creo i bottoni del game over
		
	}
	
	//creo i bottoni del menu di game over
	private void createButtons() {

		mainMenuButton = new OverlayButton("Main menu", bgX + (bgW / 2) - (BUTTON_WIDTH / 2), bgY + (bgH / 3) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);
		retryButton = new OverlayButton("Retry", bgX + (bgW / 2) - (BUTTON_WIDTH / 2), bgY + (bgH - (bgH / 3)) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);

	}
	
	/** 
	 * funzione che mostra a schermo i bottoni e il background del menu di game over
	 */
	public void draw(Graphics g) {

		//Disegna il background del menu di GameOver
		g.setColor(new Color(0,0,0,100));
		g.fillRect(bgX,bgY,bgW,bgH);

		//Disegna il bottone "Resume" e "Main Menu"
		mainMenuButton.draw(g);
		retryButton.draw(g);
	}
	
	/** 
	 * gestisco i vari click dell'utente all'interno della schermata di game over
	 */
	public void mousePressed(MouseEvent e) {
		
		if(isIn(e,retryButton)) {
			
			playing.retry();
			
		} else {
			
			if(isIn(e,mainMenuButton)) {
				
				playing.retry();
				Gamestate.state = Gamestate.MENU;
				
			}
			
		}
		
	}
		
}
	
