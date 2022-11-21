package main;

import static utilz.Constants.GameConstants.*;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.MouseInputs;

/** 
 * Il GamePanel gestisce la visualizzazione a schermo del gioco permettendo anche l'input tramite mouse
 * 
 * @See Game
 * @See MouseInputs
 * @Author Silvestri Claudio
 */

public class GamePanel extends JPanel{   
	
	private static final long serialVersionUID = 1287500740620226534L;
	
	private MouseInputs mouseInputs;
	private Game game;
	
	public GamePanel(Game game) {
		
		mouseInputs = new MouseInputs(this);  
  		this.game  = game; 
		setPanelSize();
		addMouseListener(mouseInputs);
		
	}
	
	/** 
	 * setPanelSize imposta le dimensioni della schermata dell'applicazione
	 */
	private void setPanelSize() {
		
		Dimension size = new Dimension(TOTAL_WIDTH,TOTAL_HEIGHT);
		setPreferredSize(size);
		
		
	}
	
	/** 
	 * Creo il componente che andrà a gestire la creazione degli elementi grafici presenti a schermo durante il gioco
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		game.render(g);

	
	}
	
	public Game getGame() {
		return game;
	}



}
