package ui;

import java.io.Serializable;

import main.GameManager;

import java.awt.Color;
import java.awt.Graphics;

/** 
 * PlayScore è la classe che gestisce lo score di una partita
 * 
 * @See GameOverOverlay
 * @Author Silvestri Claudio
 */

public class PlayScore implements Serializable {

	private static final long serialVersionUID = 446515456961551484L;

	private GameManager gameManager = GameManager.getInstance(); //Accedo al gameManager del gioco per ottenere informazioni sullo score
	private int xPos, yPos; //Posizione dello score
	private int scoreWidth, scoreHeight; //Dimensione dello score
	
	public PlayScore(int xPos, int yPos) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		
	}

	/** 
	 * mostro a schermo lo score della partita
	 */
	public void draw(Graphics g) {
		
		g.setColor(Color.black);		
		scoreWidth = (int) g.getFontMetrics().getStringBounds("Score: " + String.valueOf(gameManager.getScore()), g).getWidth();
		scoreHeight = (int) g.getFontMetrics().getStringBounds("Score: " + String.valueOf(gameManager.getScore()), g).getHeight();
		g.drawString("Score: " + String.valueOf(gameManager.getScore()), xPos - (scoreWidth / 2), yPos - (scoreHeight / 2) + g.getFontMetrics().getAscent());
		
	}
	
}
