package entities;

import java.io.Serializable;

import main.GameManager;

/** 
 * Comportamento del player dopo aver perso due vite, può essere utilizzata solo la strategia 1
 * 
 * @see Player
 * @see PlayerContext
 * @Author Silvestri Claudio
 */

public class PlayerPurpleState implements PlayerState, Serializable {

	private static final long serialVersionUID = 2835376740769883047L;
	private Player player;
	private GameManager gameManager = GameManager.getInstance();
	
	public PlayerPurpleState(Player player) {
		
		this.player = player;

	}

	/** 
	 * In questo stato il comportamento del player consiste nel poter usare escusivamente solo la strategia 1
	 */
	
	@Override
	public void execute() {
		
	if (gameManager.getHasNeighborhood() == true){
				
				player.strategy1(); 
				
			}
			
	}

}
