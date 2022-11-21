package main;

import java.io.Serializable;

/** 
 * Il GameManager gestisce le informazioni di gioco utili per il comportamento del player e la visualizzazione del punteggio a schermo. 
 * l'implementazione è stata effettuata mediante un "Creational Pattern", il "Singleton", per poter permettere una singola implementazione
 * della classe e un punto d'accesso comune alle informazioni del gioco
 * 
 * @See Player
 * @Author Silvestri Claudio
 */

public class GameManager implements Serializable {

    private static final long serialVersionUID = 6244652352798836767L;
    
    private boolean hasNeighborhood = false; //Mi dice se un ostacolo è presente o no nell'intorno del player
    private int nRightObstacles = 0; //Numero degli ostacoli presenti alla destra del player
    private int nLeftObstacles = 0; //Numero degli ostacoli nemici presenti alla sinistra del player
    private int playerLife = 3; //Vita del player
    private int score = 0; //Punteggio della partita
    
	private static GameManager instance = null;
    
    private GameManager() {
    	
    }
  
    /** 
     * getInstance crea il gameManager se instance è "null" o ritorna, a chi ha chiamato la funzione, l'istanza già precedentemente creata
     */
    public static GameManager getInstance()
    {
        if (instance == null)
        	instance = new GameManager();
  
        return instance;
    }

    /** 
     * resetta le variabili del gameManager preparandole ad una nuova partita
     */
	public void reset() {

	    setHasNeighborhood(false);
	    setnRightObstacles(0);
	    setnLeftObstacles(0);
	    setPlayerLife(3);
	    setScore(0);
		
	}

	//getter e setter per ottenere le variabili del gameManager:
	
	public boolean getHasNeighborhood() {
		return hasNeighborhood;
	}

	public void setHasNeighborhood(boolean hasNeighborhood) {
		this.hasNeighborhood = hasNeighborhood;
	}

	public int getnRightObstacles() {
		return nRightObstacles;
	}

	public void setnRightObstacles(int nRightObstacles) {
		this.nRightObstacles = nRightObstacles;
	}

	public int getnLeftObstacles() {
		return nLeftObstacles;
	}

	public void setnLeftObstacles(int nLeftObstacles) {
		this.nLeftObstacles = nLeftObstacles;
	}

	public int getPlayerLife() {
		return playerLife;
	}

	public void setPlayerLife(int playerLife) {
		this.playerLife = playerLife;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
         
}