package main;

import java.awt.Graphics;
import java.io.Serializable;

import gamestates.Gamestate;
import gamestates.Load;
import gamestates.Menu;
import gamestates.Playing;

/** 
 * La classe Game gestisce il gameloop e inizializza tutto il gioco, creando i vari stati e il jframe dove verrà visualizzato
 * 
 * @See GamePanel
 * @See GameWindow
 * @See Playing
 * @See Menu
 * @See Load
 * @Author Silvestri Claudio
 */

public class Game implements Runnable, Serializable {
	
	//utilizzo il serialVersionUID per "proteggere" la serializzazioni da eventuali cambiamenti della classe, posso usare in alternativa
	//@SuppressWarnings per ignorare il suggerimento dell'IDE
	private static final long serialVersionUID = 8249981377147180005L;
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
	private transient Thread gameThread; //transient è usato perchè Thread non è serializable
	
	private final int FPS_SET = 120;
	
	private Playing playing;
	private Menu menu;
	private Load load;
	
	private boolean initDone = false;
	
	public Game() {
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		initClasses();  
		
		GameManager.getInstance();
		
		startGameLoop(); 
		
	}

	/** 
	 * initClasses inizializza il menu principale, il menu di load e il gioco creando i rispettivi oggetti
	 */
	private void initClasses() {
		
		menu = new Menu(this, gameWindow.getJFrame());
		playing = new Playing(this, gameWindow.getJFrame());
		load = new Load(this, gameWindow.getJFrame());
		
		initDone = true; //metto a true quando l'inizializzazione è terminata
				
	}

	/** 
	 * startGameLoop crea un thread che si occuperà della gestione del game loop
	 */
	private void startGameLoop() {
		
		gameThread = new Thread(this); //creo un thread
		gameThread.start(); //avvio il thread, start() avvierà la funzione run() implementata dall'interfaccia "runnable"
		
	}
	
	/** 
	 * render si occuperà di avviare le funzione di "draw" dei vari elementi in base allo stato di gioco in cui l'utente si trova
	 */
	public void render(Graphics g) {
		 
		//le funzioni di draw verranno avviate solo se initDone è "true" e quindi le funzioni di inizializzazione sono terminate
		if(initDone) {
			switch(Gamestate.state) {
			case MENU:
				menu.draw(g);
				break;
			case PLAYING:
				playing.draw(g);
   				break;
			case LOAD:
				load.draw(g);
				break;
			default:
				System.exit(0);
				break;
		}
		
		}
		 
	}
	 
	
	/** 
	 * run si occupa di gestire il game loop eseguendo il "repaint" del gioco 120 volte al secondo (120 FPS)
	 */
	
	@Override
	public void run() {
		
		double timePerFrame = 1000000000 / FPS_SET;
		
		long previusTime = System.nanoTime();
		long lastCheck = System.currentTimeMillis();
		
		double deltaF = 0;
		
		while(true) {
			
			long currentTime = System.nanoTime();
			
			deltaF += (currentTime - previusTime) / timePerFrame;

			previusTime = currentTime;
			
			if(deltaF >= 1) {
				
				gamePanel.repaint(); //eseguo il repaint (applico eventuali aggiornamenti degli elementi grafici del gioco)
				deltaF--;

			}
			
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				
				lastCheck = System.currentTimeMillis();

			}
			
		}
		
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}
	
	public Load getLoad() {
		return load;
	}
	
}
