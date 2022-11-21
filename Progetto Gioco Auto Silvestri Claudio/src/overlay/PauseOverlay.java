 package overlay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JFrame;

import gamestates.Gamestate;
import gamestates.Playing;
import ui.OverlayButton;

import static utilz.Constants.UIConstants.*;

/** 
 * PauseOverlay eredita i campi e gli attributi della classe "Overlay". gestisce il menu di pausa permettendo all'utente di riprendere il gioco,
 * tornare al menu principale o salvare la partita per poi riprenderla successivamente. Durante il salvataggip sarà necessario inserire un
 * nome e cognome per salvare la partita correttamente e riprenderla successivamente.
 * 
 * @See GameOverOverlay
 * @See Overlay
 * @See Playing
 * @Author Silvestri Claudio
 */

public class PauseOverlay extends Overlay implements Serializable{

	private static final long serialVersionUID = 1361568676563090849L;
		
	private JFrame jframe;
	
	private OverlayButton resumeButton;
	private OverlayButton saveMenuButton;
	private OverlayButton mainMenuButton;
	private OverlayButton backButton;
	private OverlayButton saveGameButton;
	
	private TextField nameTextField;
	private TextField surnameTextField;
	
	private boolean savingError = false;
	private boolean saveMenuIsOpen = false;
	
	public PauseOverlay(Playing playing, JFrame jframe) {
		super(playing);
		this.jframe = jframe;
		loadBackground(); //carico il background di overlay
		createPauseButtons(); //creo i bottoni del menu di pausa
		saveTextArea(); //creo le aree di testo per l'inserimento di nome e cognome per il salvataggio della partita
		
	}
	
	//creo i bottoni del menu di pausa
	private void createPauseButtons() {

		//Creo i vari bottoni, ognuno con il suo nome (che verrà visualizzato come stringa), posizione e dimensione)
		resumeButton = new OverlayButton("Resume", bgX + (bgW / 2) - (BUTTON_WIDTH / 2), bgY + (bgH / 4) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);
		saveMenuButton = new OverlayButton("Save game", bgX + (bgW / 2) - (BUTTON_WIDTH / 2), bgY + (bgH / 2) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);	
		mainMenuButton = new OverlayButton("Main menu", bgX + (bgW / 2) - (BUTTON_WIDTH / 2), bgY + (bgH - (bgH / 4)) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);	
		
		backButton = new OverlayButton("Back", bgX + (bgW / 3) - (BUTTON_WIDTH / 2), bgY + (bgH - (bgH / 3)) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);	
		saveGameButton = new OverlayButton("Save game", bgX + (bgW - (bgW / 3)) - (BUTTON_WIDTH / 2), bgY + (bgH - (bgH / 3)) - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT);	

		
	}
	
	/** 
	 * funzione che mostra a schermo i bottoni e il background del menu di pausa
	 */
	public void draw(Graphics g) {

		//Disegna il background del menu di pausa
		g.setColor(new Color(0,0,0,100));
		g.fillRect(bgX,bgY,bgW,bgH);

		//Disegna i bottoni del menu di pausa se non è aperto il menu di salvataggio
		if (!saveMenuIsOpen) {
			resumeButton.draw(g);
			saveMenuButton.draw(g);
			mainMenuButton.draw(g);
		} else //Disegno i bottoni del menu di salvataggio
		{
			backButton.draw(g);
			saveGameButton.draw(g);
		}
	
	}
	
	/** 
	 * gestisco i vari click dell'utente all'interno della schermata di pausa
	 */
	public void mousePressed(MouseEvent e) {
		
		//gestisco, in base a quale bottono clicco, il comportamento che il gioco deve avere
		if(!saveMenuIsOpen) {
			if(isIn(e,resumeButton)) {
				playing.unpauseGame();
				
			} else {
				
				if(isIn(e,mainMenuButton)) {
					
					playing.unpauseGame();
					playing.retry();
					Gamestate.state = Gamestate.MENU;
					
				} else {
					
					if(isIn(e,saveMenuButton)) {
						
						saveMenuIsOpen = true;
						nameTextField.setVisible(true);
						surnameTextField.setVisible(true);
						
					}
					
				}
			}
		} else {
			
			if (isIn(e,backButton)) {
				
				saveMenuIsOpen = false;
				nameTextField.setVisible(false);
				surnameTextField.setVisible(false);
			
				
			} else {
				
				if (isIn(e,saveGameButton)) {
					
					//Salva la partita
					playing.save(nameTextField.getText() + surnameTextField.getText());
					if (!savingError) {
						
						saveMenuIsOpen = false;
						nameTextField.setVisible(false);
						surnameTextField.setVisible(false);
						
					}
				}
				
			}
		}
 
		
	}
	
	private void saveTextArea() {
		
		//Creo i campi di testo del menu di salvataggio, li imposto "invisibili", verranno visualizzati solo quando l'utente è
		//all'interno del menu di salvataggio
		nameTextField = new TextField("Name", 1);
		nameTextField.setBounds(bgX + (bgW / 3) - (BUTTON_WIDTH / 2),bgY + (bgH / 3) - (BUTTON_HEIGHT / 2),100,25);
		nameTextField.setVisible(false);
		jframe.add(nameTextField); //aggiungo l'elemento al jframe del gioco
		surnameTextField = new TextField("Surname", 1);
		surnameTextField.setBounds(bgX + (bgW - (bgW / 3)) - (BUTTON_WIDTH / 2),bgY + (bgH / 3) - (BUTTON_HEIGHT / 2),100,25);
		surnameTextField.setVisible(false);
		jframe.add(surnameTextField); //aggiungo l'elemento al jframe del gioco
		
	}

	//Setter per gestire eventuali errori durante la fase di salvataggio
	public void setSavingError(boolean savingError) {
		
		this.savingError = savingError;
		
	}
		
}

