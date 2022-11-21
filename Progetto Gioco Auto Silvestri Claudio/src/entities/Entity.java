package entities;

import java.awt.Graphics;
import java.io.Serializable;

/** 
 * Superclasse astratta per le entità del gioco, contiene informazioni riguardo la loro posizione (x, y) e la loro 
 * dimensione (width, height). Sono presenti anche due metodi astratti da implementare per gestire l'output a schermo
 * dell'entità e la sua eventuale nuova posizione.
 * 
 * @See Player
 * @See Obstacle
 * @Author Silvestri Claudio
 */

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 6072532978188461801L;

	protected float x,y;
	protected int width, height;
	
	public Entity(float x, float y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void newPosition();
	
}
