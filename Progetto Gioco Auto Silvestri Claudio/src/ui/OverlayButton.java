package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

/** 
 * OverlayButton è la classe che gestisce i bottoni presenti all'interno dei menu di overlay del gioco (Menu di pausa e gameover), 
 * contiene informazioni sulla loro posizione e dimensione
 * 
 * @See GameOverOverlay
 * @Author Silvestri Claudio
 */

public class OverlayButton implements Serializable{

	private static final long serialVersionUID = 7386133345737840901L;
	
	private String buttonName; 
	private int x,y,width,height;
	private Rectangle bounds;
	private int xOffsetCenter;
	private int yOffsetCenter;
	private int buttonNameWidth;
	private int buttonNameHeight;
	
	public OverlayButton(String buttonName, int x, int y, int width, int height) {
		this.buttonName = buttonName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		xOffsetCenter = width / 2;
		yOffsetCenter = height / 2;
		initBounds();
	}

	//creo i bordi del bottone per poi controllare evenutali click al suo interno
	private void initBounds() {
		bounds = new Rectangle(x,y,width,height);
	}
	
	/** 
	 * mostro a schermo il bottone
	 */
	public void draw(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(x - 1, y - 1, width + 2, height + 2);
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		buttonNameWidth = (int) g.getFontMetrics().getStringBounds(buttonName, g).getWidth();
		buttonNameHeight = (int) g.getFontMetrics().getStringBounds(buttonName, g).getHeight();
		g.drawString(buttonName, x + xOffsetCenter - (buttonNameWidth / 2), y + yOffsetCenter - (buttonNameHeight / 2) + g.getFontMetrics().getAscent());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
}
