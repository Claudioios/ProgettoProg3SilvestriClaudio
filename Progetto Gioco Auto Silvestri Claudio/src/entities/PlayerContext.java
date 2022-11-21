package entities;

import java.io.Serializable;

/** 
 * PlayerContext gestisce il comportamento del player, cambiando il suo stato in base alle vite o esegendo il comportamento del player
 * quando è richiesto un nuovo cambio di posizione
 * 
 * @See PlayerState
 * @See PlayerRedState
 * @See PlayerOrangeState
 * @See PlayerPurpleState
 * @see Player
 * @Author Silvestri Claudio
 */
public class PlayerContext implements PlayerState, Serializable  {

	private static final long serialVersionUID = -3366376130544465088L;
	
	private PlayerState playerState;
	
	/** 
	 * Imposto lo stato del player
	 */
	public void setState(PlayerState playerState) {
		   
	   this.playerState = playerState;
	      
	}
	
	/** 
	 * Ottengo lo stato del player
	 */
	public PlayerState getState() {
	   return this.playerState;
	}
	
	/** 
	 * Eseguo lo stato del player
	 */
	@Override
	public void execute() {
		
		this.playerState.execute();
		
	}

}
