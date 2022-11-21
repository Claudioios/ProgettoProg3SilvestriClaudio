package ui;

import gamestates.Gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

/** 
 * Button è la classe che gestisce i bottoni presenti all'interno dei menu di gioco, contiene informazioni sulla loro posizione e dimensione
 * 
 * @Author Silvestri Claudio
 */

public class Button implements Serializable {

	private static final long serialVersionUID = -7711967683507780007L;
	
	private int xPos, yPos;
	private int width, height;
	private int xOffsetCenter;
	private String buttonName;
	private Gamestate state;
	private Rectangle bounds;
	private int buttonNameWidth;
	private int buttonNameHeight;	
	
	public Button(String buttonName, int xPos, int yPos, int width, int height, Gamestate state) {
		
		this.buttonName = buttonName;
		this.xPos = xPos;
		this.yPos = yPos;
		this.state = state;
		this.width = width;
		this.height = height;
		xOffsetCenter = width / 2;
		initBounds();
		
	}
	
	//creo i bordi del bottone per poi controllare evenutali click al suo interno
	private void initBounds() {
		
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, width, height);
		
	}

	/** 
	 * mostro a schermo i bottoni
	 */
	public void draw(Graphics g) {
		
		g.setColor(Color.black);
		g.drawRect(xPos - xOffsetCenter, yPos, width, height);
		buttonNameWidth = (int) g.getFontMetrics().getStringBounds(buttonName, g).getWidth();
		buttonNameHeight = (int) g.getFontMetrics().getStringBounds(buttonName, g).getHeight();
		g.drawString(buttonName, xPos - xOffsetCenter + (width  - buttonNameWidth) / 2, yPos + ((height - buttonNameHeight) / 2) + g.getFontMetrics().getAscent());
		
	}
	
	/** 
	 * applico il cambiamento di stato assegnato al bottone
	 */
	public void applyGamestate() {

		Gamestate.state = state;
		
	}

	public Rectangle getBounds() {
		
		return bounds;
		
	}
	
	public String getButtonName() {
		
		return buttonName;
		
	}
}
