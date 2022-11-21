package gamestates;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UIConstants.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import main.Game;
import ui.Button;

/** 
 * La classe Menu eredita i campi e i metodi della classe "State", gestisce la schermata principale del gioco permettendo all'utente di
 * andare tramite il bottono "New Game" all'avvio di una nuova partita o, tramite il bottone "Load Game", andare alla schermata di
 * caricamento di una vecchia partita salvata
 * 
 * @See State
 * @See Load
 * @See Playing
 * @Author Silvestri Claudio
 */

public class Menu extends State implements Statemethods {

	private static final long serialVersionUID = -8474735552485522925L;
	
	private Button[] buttons = new Button[2];
	
	public Menu(Game game, JFrame jframe) {
		super(game, jframe);
		loadButtons();
 	}

	/** 
	 * Creo i bottoni presenti nel menu di "Menu"
	 */
	@Override
	public void loadButtons() {
		buttons[0] = new Button("New Game", TOTAL_WIDTH / 2, TOTAL_HEIGHT / 3 - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT, Gamestate.PLAYING);
		buttons[1] = new Button("Load Game", TOTAL_WIDTH / 2, TOTAL_HEIGHT - (TOTAL_HEIGHT / 3) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT, Gamestate.LOAD);
	}

	
	/** 
	 * Eseguo, per ogni bottone, la rispettiva funzione "draw"
	 */
	@Override
	public void draw(Graphics g) {
		for (Button b : buttons){
			b.draw(g);
		}
	}

	/** 
	 * Controllo dove si sono verificati gli eventuali click dell'utente, e se si sono verificati all'interno dei bottoni eseguo il
	 * rispettivo funzionamento
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		for (Button b : buttons){
			if(isIn(e,b)) {
				if (b.getButtonName() == "Load Game") {
					
					game.getLoad().showTextArea();
					
				}

				b.applyGamestate();
				
				break;
			}
			
		}

	}
	

}
