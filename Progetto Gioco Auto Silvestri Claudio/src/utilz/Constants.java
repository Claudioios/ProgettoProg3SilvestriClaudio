package utilz;

/** 
 * La classe Constants contiene tutte le costanti utilizzate all'interno delle varie classi
 * 
 * @Author Silvestri Claudio
 */

public class Constants {
	
	public static class GameConstants {
		
		//Costanti per la dimensione del gioco
		public final static int TILES_DEFAULT_SIZE = 32; //Dimensione di default del player e degli ostacoli
		public final static float SCALE = 2.0f;  //Scale applicato alle dimensioni di Default
		public final static int TILES_IN_WIDTH = 5; //Numero di "tiles" in colonna del gioco 
		public final static int TILES_IN_HEIGHT = 6; //Numero di "tiles" in riga del gioco
		public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE); //Calcola la dimensione dei "tiles"
		public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH; //Calcola la larghezza del gioco
		public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT; //Calcola la lunghezza del gioco
		
		//Costanti per la barra laterale in playing
		public final static int GAME_HUD_WIDTH = (int) (TILES_SIZE * 1.5); //Calcola la larghezza della barra laterale in playing
		public final static int GAME_HUD_HEIGHT = TILES_SIZE; //Calcola la lunghezza della barra laterale in playing

		public final static int TOTAL_WIDTH = GAME_WIDTH + GAME_HUD_WIDTH; //Calcola la larghezza totale della schermata di gioco
		public final static int TOTAL_HEIGHT = GAME_HEIGHT; //Calcola la lunghezza totale della schermata di gioco
		
	}

	public static class ObstacleConstants{
		
		//Costanti per le dimensioni degli ostacoli del gioco
		
		public static final int OBSTACLE_WIDTH_DEFAULT = 32; //Larghezza di default
		public static final int OBSTACLE_HEIGHT_DEFAULT = 32; //Lunghezza di default
		
		public static final int OBSTACLE_WIDTH = (int)(OBSTACLE_WIDTH_DEFAULT * GameConstants.SCALE); //Larghezza effettiva in base allo SCALE
		public static final int OBSTACLE_HEIGHT = (int)(OBSTACLE_HEIGHT_DEFAULT * GameConstants.SCALE); //Lunghezza effettiva in base allo SCALE
		
	}
	
	public static class UIConstants{
		
		//Costanti per i bottoni del Menu
		public static final int BUTTON_WIDTH_DEFAULT = 50; //Larghezza di default
		public static final int BUTTON_HEIGHT_DEFAULT = 20; //Larghezza di default
		public static final int BUTTON_WIDTH = (int)(BUTTON_WIDTH_DEFAULT * GameConstants.SCALE);  //Larghezza effettiva in base allo SCALE
		public static final int BUTTON_HEIGHT = (int)(BUTTON_HEIGHT_DEFAULT * GameConstants.SCALE); //Lunghezza effettiva in base allo SCALE
			
		//Costanti per i bottoni all'interno del gioco (playing)
		public static final int PLAYING_BUTTON_WIDTH = GameConstants.TILES_SIZE; //Larghezza dei bottoni
		public static final int PLAYING_BUTTON_HEIGHT = GameConstants.TILES_SIZE; //Larghezza dei bottoni
			
	
	}

}
