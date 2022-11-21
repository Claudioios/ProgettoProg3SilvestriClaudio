package entities;

import static utilz.Constants.ObstacleConstants.*;
import static utilz.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/** 
 * Obstacle eredita dalla classe "Entity" i suoi campi e i suoi metodi, rappresenta un ostacolo presente all'interno del gioco
 * e lo gestisce mediante l'implementazione della funzione "draw", che permette l'output a schermo dell'ostacolo, e della funzione
 * "newPosition" che viene chiamata ad ogni turno del gioco e muove l'ostacolo di una o due posizioni casuali
 * 
 * @See Entity
 * @Author Silvestri Claudio
 */

public class Obstacle extends Entity {
	
	private static final long serialVersionUID = 7109767603685732474L;
	
	private int stringIDWidth;
	private int stringIDHwight;
	private int obstacleID;
		
	public Obstacle(int obstacleID, float x, float y) {
		super(x, y, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
		
		this.obstacleID = obstacleID;

	}
	
	/**
	* Disegna a schermo gli ostacoli, imposta il loro colore e "scrive" al loro interno il numero identificativo dell'ostacolo (Identificativo
	* inserito solo per semplicità di riconoscimento degli ostacoli all'interno del gioco) 
	*/
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.cyan);
		g.fillRect((int)x,(int)y, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
		g.setColor(Color.black);
		stringIDWidth = (int) g.getFontMetrics().getStringBounds(String.valueOf(obstacleID), g).getWidth();
		stringIDHwight = (int) g.getFontMetrics().getStringBounds(String.valueOf(obstacleID), g).getHeight();
		g.drawString(String.valueOf(obstacleID), (int)x + (OBSTACLE_WIDTH  - stringIDWidth) / 2, (int)y + ((OBSTACLE_HEIGHT - stringIDHwight) / 2) + g.getFontMetrics().getAscent());

	
	}

	/**
	* funzione richiamata ad ogni turno del gioco, cambia la posizione dell'ostacolo di una o due posizioni casuali 
	*/
	
	@Override
	public void newPosition() {
		
		Random randomNumNewPosition = new Random(); //Randomicamente scelgo se l'ostacolo si muoverà di 1 o 2 posizioni
		Random randomNewPosition = new Random(); //Randomicamente decido in quale posizione l'ostacolo si sposterà
		
		if(randomNumNewPosition.nextInt(2) == 0) {
			
			do {
				
				switch(randomNewPosition.nextInt(3)) {
				case 0:
					x = x + TILES_SIZE; //muovo l'ostacolo a destra
					break;
				case 1:
					x = x - TILES_SIZE; //muovo l'ostacolo a sinistra
		   			break;
				case 2:
					y = y + TILES_SIZE; //muovo l'ostacolo in avanti (verso il player)
					break;
				default:
					System.exit(0);
					break;
				}
				
			} while (x < 0 || x > ((TILES_IN_WIDTH - 1) * TILES_SIZE)); //controllo se la nuova posizione dell'ostacolo è fuori dalla schermata di gioco



		} else {
			
			do {

				switch(randomNewPosition.nextInt(5)) {
				case 0:
					x = x + (TILES_SIZE * 2); //muovo l'ostacolo di due posizioni a destra
					break;
				case 1:
					x = x - (TILES_SIZE * 2); //muovo l'ostacolo di due posizioni a sinistra
	   				break;
				case 2:
					x = x + TILES_SIZE; //muovo l'ostacolo di una posizione a destra e una in avanti (verso il player)
					y = y + TILES_SIZE;
					break;
				case 3:
					x = x - TILES_SIZE; //muovo l'ostacolo di una posizione a sinistra e una in avanti (verso il player)
					y = y + TILES_SIZE;
					break;
				case 4:
					y = y + (TILES_SIZE * 2); //muovo l'ostacolo di due posizioni in avanti (verso il player)
					break;
				default:
					System.exit(0);
					break;
				}
			
			
			} while (x < 0 || x > ((TILES_IN_WIDTH - 1) * TILES_SIZE)); //controllo se la nuova posizione dell'ostacolo è fuori dalla schermata di gioco

		
		}
	}
	
	
}

