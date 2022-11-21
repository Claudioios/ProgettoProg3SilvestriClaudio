package entities;

import static utilz.Constants.GameConstants.*;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import main.GameManager;

/** 
 * La classe ObstacleManager gestisce tutti gli ostacoli presenti all'interno di una partita. Ad ogni turno crea un nuovo ostacolo
 * e lo inserisce all'interno di un "ArrayList" per poi gestire le loro posizioni ad ogni turno (rimuovendo gli ostacoli se il player
 * viene superato o rimuovendo una vita al PLayer se entra in contatto con un ostacolo).
 * 
 * @See Obstacle
 * @see Player
 * @Author Silvestri Claudio
 */

public class ObstacleManager implements Serializable {

	private static final long serialVersionUID = -2802872960241153823L;
	
	private Player player; //riferimento al player
	private ArrayList<Obstacle> obstacles = new ArrayList<>(); //array list contenente tutti gli ostacoli presenti in gioco
	private Random xRandom = new Random(); //posizione randomica dell'ostacolo creato
	private GameManager gameManager = GameManager.getInstance(); //riferimento al gameManager
	private int obstacleID = 0; //identificativo dell'ostacolo


	public ObstacleManager(Player player) {
		
		this.player = player;
		
	}
	
	/** 
	 * Richiamo la funzione "draw" di ogni ostacolo creato
	 */
	public void draw(Graphics g) {
		for(Obstacle o : obstacles){
			o.draw(g);
		}
		
	}
	
	/** 
	 * Funzione che crea un nuovo ostacolo in una posizione casuale e lo aggiunge all'array list
	 */
	public void newObstacle() {
		
		obstacles.add(new Obstacle(obstacleID, xRandom.nextInt(5) * TILES_SIZE,0)); //Funzione da richiamare per creare un nuovo ostacolo (modifiche necessarie)
		obstacleID++;
	}
	
	/** 
	 * Richiamo la funzione "newPosition" di ogni ostacolo creato, rimuovendo l'ostacolo se supera il player
	 */
	public void newPosition() {
		for(Obstacle o : obstacles){
			o.newPosition();
			
		}

		obstacles.removeIf(obstacles -> (obstacles.y > player.y));  

	}

	/** 
	 * Conta gli ostacoli presenti in game e incrementa il rispettivo contatore in base a se si trovano sulla sinistra o destra del player
	 */
	public void countObstacle() {
		
		gameManager.setnRightObstacles(0); //numero di ostacoli presenti a destra del player
		gameManager.setnLeftObstacles(0); //numero di ostacoli presenti a sinistra del player
		
		for(Obstacle o : obstacles){
			if(o.x > player.x)
			{
				
				gameManager.setnRightObstacles(gameManager.getnRightObstacles() + 1);
				
			} else {
				if (o.x < player.x)
				{
					
					gameManager.setnLeftObstacles(gameManager.getnLeftObstacles() + 1);
					
				}
			}
		}
		
	}
	
	/** 
	 * Controllo se un ostacolo si trova all'interno dell'intorno del player (nelle due posizioni avanti rispetto alla posizione del player)
	 */
	public void checkNeighborhood() {
		
		gameManager.setHasNeighborhood(false);
		
		for(Obstacle o : obstacles) {
			if (o.x == player.x && (o.y == (player.y - TILES_SIZE) || o.y == (player.y - (TILES_SIZE * 2)))){
				
				gameManager.setHasNeighborhood(true);
				
			}
		}
	}
	
	/** 
	 * Controllo se un ostacolo si trova nella stessa posizione del player, in caso affermativo rimuovo l'ostacolo e decremento la vita del player
	 */
	public boolean checkPlayerPosition() {
				
		for(Obstacle o : obstacles) {
			
			if (o.x == player.x && o.y == player.y){
				
				obstacles.remove(o); //Elimino l'ostacolo che si trova nella stessa posizione del player
				
				return true;
				
			}
		}
				
		return false;
	}

	/** 
	 * Resetta l'obstacleManager preparandolo per una nuova partita
	 */
	public void reset() {

		obstacles.removeAll(obstacles);
		obstacleID = 0;
		
	}
	
	

}
