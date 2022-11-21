package entities;

import static utilz.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

import main.GameManager;

/** 
 * la classe Player gestisce l'auto del giocatore, implementa i campi e i metodi della classe "Entity", e gestisce il suo comportamento
 * mediante il "Behavioral Pattern" "State". Il movimento del player è gestito mediante delle strategie che vengono utilizzate in base
 * alla posizione degli ostacoli e dal comportamento del player che varia in base alle vite rimanenti
 * 
 * @See Entity
 * @See PlayerContext
 * @Author Silvestri Claudio
 */

public class Player extends Entity implements Serializable{

	private static final long serialVersionUID = 6021170555148119647L;
	
	private GameManager gameManager = GameManager.getInstance(); //riferimento al gameManager
	
	private Random randStrategy = new Random(); 
	private int randomStrategy;
	
	PlayerContext playerContext = new PlayerContext();
	PlayerState playerRedState = new PlayerRedState(this);
	PlayerState playerOrangeState = new PlayerOrangeState(this);
	PlayerState playerPurpleState = new PlayerPurpleState(this);
	
	private Color color = Color.red; //imposto il colore del player
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		
		playerContext.setState(playerRedState);	
		
	}
	
	/** 
	 * Disegno l'auto del player all'interno del gioco, assegnandogli un colore, una posizione iniziale e una dimensione
	 */
	@Override
	public void draw(Graphics g) {
		
		g.setColor(color); 
		g.fillRect((int)x,(int)y, width, height);
		
	}
	
	/** 
	 * Imposto la nuova posizione del player in base al suo comportamento
	 */
	@Override
	public void newPosition() {	
			
		playerContext.execute(); 
			
	}
	
	/** 
	 * Strategia 1, il player si muove in una posizione casuale se ha un ostacolo nel suo intorno
	 */
	public void strategy1() {
		
		Random xRandom = new Random();
		x = xRandom.nextInt(5) * TILES_SIZE;
		
	}
	
	/** 
	 * Strategia 2, il player si muove verso sinistra (se possibile) se la maggioranza degli ostacoli si trova alla sua destra
	 */
	public void strategy2() {
		
		if (x > 0) {
			
			x =  x - TILES_SIZE;
			
		}
		
	}
	
	/** 
	 * Strategia 3, il player si muove verso destra (se possibile) se la maggior parte degli ostacoli si trova a sinistra
	 */
	public void strategy3() {
		
		if (x < ((TILES_IN_WIDTH - 1) * TILES_SIZE)){
			
			x = x + TILES_SIZE;
			
		}
	}

	/** 
	 * La funzione gestisce il caso in cui il player perde una vita. Verra assegnato al player il rispettivo nuovo colore e il nuovo
	 * comportamento
	 */
	public void loseLife() {
		
		//il numero di vite del player è contenuto all'interno del gameManager
		switch(gameManager.getPlayerLife()){
		case 2:
			color = Color.orange;
			randomStrategy = randStrategy.nextInt(2); //scelgo randomicamente la strategia, tra la 2 e la 3, da utilizzare in questo comportamento del player
			playerContext.setState(playerOrangeState);	
			break;
		case 1:
			color = new Color(143, 0, 255); //Imposto, tramite RGB, il colore viola al player
			playerContext.setState(playerPurpleState);	
			break;
		case 0:
			color = Color.black; //il plpayer ha perso la partita, non ha più vite
			break;
		default:
			break;
		
		}
		
	
	}

	/** 
	 * Resetto il player e lo preparo ad una nuova partita
	 */
	public void reset() {

		x = (GAME_WIDTH - TILES_SIZE) / 2;
		y = (GAME_HEIGHT - TILES_SIZE);
		color = Color.red;
		gameManager.setPlayerLife(3);
		
	}

	/** 
	 * Imposto il getter per ottenere la strategia che verrà utilizzata nel comportamento 2 del player (strategia scelta casualmente)
	 */
	public int getRandomStrategy() {
		
		return randomStrategy;
	}
	
}
