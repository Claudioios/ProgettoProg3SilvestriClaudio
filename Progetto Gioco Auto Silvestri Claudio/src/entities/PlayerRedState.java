package entities;

import java.io.Serializable;

import main.GameManager;

/** 
 * Comportamento base del player, possono essere utilizzate tutte le strategie disponibili
 * 
 * @see Player
 * @see PlayerContext
 * @Author Silvestri Claudio
 */

public class PlayerRedState implements PlayerState, Serializable  {

	private static final long serialVersionUID = -6543364183183586728L;
	private Player player;
	private GameManager gameManager = GameManager.getInstance();

	public PlayerRedState(Player player) {
		
		this.player = player; 

	}

	/** 
	 * In questo stato il player ha il suo comportamento standard, può quindi utilizzare tutte le strategie
	 */
	@Override
	public void execute() {
		
		if (gameManager.getHasNeighborhood() == true){
			
			player.strategy1(); 
			
		} else {
			
			if (gameManager.getnRightObstacles() > gameManager.getnLeftObstacles()) {
				
				player.strategy2();
				
			} else {
				
				player.strategy3();
			}
			
		}
		
	}

}
