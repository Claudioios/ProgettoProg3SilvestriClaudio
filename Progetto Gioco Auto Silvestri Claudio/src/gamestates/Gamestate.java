package gamestates;

/** 
 * Enumerazione che gestisce i vari stati del "game"
 * 
 * @See Game
 * @Author Silvestri Claudio
 */
public enum Gamestate {
	
	PLAYING, MENU, LOAD; 
	
	public static Gamestate state = MENU;
}
