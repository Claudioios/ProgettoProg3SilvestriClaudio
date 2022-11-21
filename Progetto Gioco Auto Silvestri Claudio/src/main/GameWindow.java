package main;

import java.io.Serializable;

import javax.swing.JFrame;

/** 
 * Il GameWindow gestisce la dimensione del jframe e lo collega al GamePanel
 * 
 * @See GamePanel
 * @Author Silvestri Claudio
 */

public class GameWindow implements Serializable {

	private static final long serialVersionUID = 2869786601110227298L;
	
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		
		jframe = new JFrame(); //creo il jframe che conterrà l'intera schermata di gioco
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //chiudo il gioco quando clicco sulla "x" di chiusura dell'applicazione
		jframe.add(gamePanel); //aggiungo il jframe al pannello di gioco
		jframe.setResizable(false); //rendo impossibile modificare le dimensioni della schermata di gioco
		jframe.pack(); 
		jframe.setLocationRelativeTo(null); //mostro la finestra di gioco al centro dello schermo al suo avvio
		jframe.setVisible(true); //imposto il jrame visibile

	}
	
	public JFrame getJFrame() {
		
		return jframe;
		
	}
	
}
