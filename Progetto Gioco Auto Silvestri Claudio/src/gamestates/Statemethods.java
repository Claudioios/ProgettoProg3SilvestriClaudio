package gamestates;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
/** 
 * Interfaccia che contiene i metodi da implementare comuni a tutti gli stati del gioco
 * 
 * @See State
 * @Author Silvestri Claudio
 */
public interface Statemethods {
	
	public void draw(Graphics g);
		
	public void mousePressed(MouseEvent e);

	public void loadButtons();
}
