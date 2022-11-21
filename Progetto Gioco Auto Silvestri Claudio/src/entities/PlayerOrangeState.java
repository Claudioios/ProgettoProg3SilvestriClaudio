package entities;

import java.io.Serializable;

import main.GameManager;

/** 
 * Comportamento del player dopo aver perso la prima vita, possono essere utilizzate solo la strategia 1 e la 2 o la 3
 * 
 * @see Player
 * @see PlayerContext
 * @Author Silvestri Claudio
 */

public class PlayerOrangeState implements PlayerState, Serializable  {

	private static final long serialVersionUID = -1110150479377122032L;
	
	private Player player;
	private GameManager gameManager = GameManager.getInstance();
	
	public PlayerOrangeState(Player player) {
		
		this.player = player;
		
	}

	/** 
	 * In questo stato il comportamento del player consiste nell'utilizzare la strategia 1 se è presente un ostacolo nel suo intorno oppure,
	 * deciso randomicamente durante la creazione del player, o solo la strategia 2 o solo la 3
	 */
	@Override
	public void execute() {
		
		if (gameManager.getHasNeighborhood() == true){
			
			player.strategy1(); //utilizzo la strategia 1
			
		} else {
			
			if (gameManager.getnRightObstacles() > gameManager.getnLeftObstacles()) {
				
				//Controllo quale tra la strategia 2 e 3 posso utilizzare con il secondo comportamento
				if(player.getRandomStrategy() == 0) {
					
					player.strategy2(); //utilizzo la strategia 2

				}
				
				
			} else {
				
				//Controllo quale tra la strategia 2 e 3 posso utilizzare con il secondo comportamento
				if(player.getRandomStrategy() == 1) {
					
					player.strategy3(); //utilizzo la strategia 3
					
				}
			}
			
		}
		
	}

}
