package gamestates;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UIConstants.*;

import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import main.Game;
import ui.Button;

/** 
 * La classe Load eredita i campi e i metodi della classe "State", gestisce il gioco quando l'utente si trova nella schermata di 
 * caricamento di una partita precedentemente salvata, permette all'utente di tornare, mediante il tasto "back", al menu principale o caricare
 * una partita mediante il tast "load game" dopo aver inserito nome e cognome di una partita salvata correttamente
 * 
 * @See State
 * @See Menu
 * @See Playing
 * @Author Silvestri Claudio
 */

public class Load extends State implements Statemethods {

	private static final long serialVersionUID = 8049278771022018312L;
	
	private TextField nameTextField; 
	private TextField surnameTextField;
	
	private boolean loadingError = false;
	
	private Button[] buttons = new Button[2];
	
	public Load(Game game, JFrame jframe) {
		super(game, jframe);
		loadButtons(); //creo i bottoni
		loadTextArea(); //creo le aree di testo

		
 	}
	
	/** 
	 * Creo i bottoni presenti nel menu di "Load"
	 */
	@Override
	public void loadButtons() {
		
		buttons[0] = new Button("Back", TOTAL_WIDTH / 3, (int)(100 * SCALE), BUTTON_WIDTH, BUTTON_HEIGHT, Gamestate.MENU);
		buttons[1] = new Button("Load game", TOTAL_WIDTH - (TOTAL_WIDTH / 3), (int)(100 * SCALE), BUTTON_WIDTH, BUTTON_HEIGHT, Gamestate.PLAYING);
		
		
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
	
	private void loadTextArea() {
		
		nameTextField = new TextField("Name", 1);
		nameTextField.setBounds(GAME_WIDTH / 4, GAME_HEIGHT / 4,100,25);
		nameTextField.setVisible(false);
		jframe.add(nameTextField);
		surnameTextField = new TextField("Surname", 1);
		surnameTextField.setBounds(GAME_WIDTH - (GAME_WIDTH / 4), GAME_HEIGHT / 4,100,25);
		surnameTextField.setVisible(false);
		jframe.add(surnameTextField);
		
	}
	
	/** 
	 * Controllo dove si sono verificati gli eventuali click dell'utente, e se si sono verificati all'interno dei bottoni eseguo il
	 * rispettivo funzionamento
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		for (Button b : buttons){
			if(isIn(e,b)) {
				
				b.applyGamestate();

				if(b.getButtonName() == "Load game") {
					
					game.getPlaying().load(nameTextField.getText() + surnameTextField.getText());
					if(!loadingError) {
						
						nameTextField.setVisible(false);
						surnameTextField.setVisible(false);
						
					}
				} else {
					
					nameTextField.setVisible(false);
					surnameTextField.setVisible(false);
					
				}
				
				break;
				
			}
			
		}

	}
	
	/**
	 * rendo visibili le aree di testo del menu di "Load"
	 */
	public void showTextArea() {
		
		nameTextField.setVisible(true);
		surnameTextField.setVisible(true);
		
	}
	
	/** 
	 * imposto un eventuale errore durante il caricamento di una vecchia partita
	 */
	public void setLoadingError(boolean loadingError) {
		
		this.loadingError = loadingError;
		
	}

	
	

}