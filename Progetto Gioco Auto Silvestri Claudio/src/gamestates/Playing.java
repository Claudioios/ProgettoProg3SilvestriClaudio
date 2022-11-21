package gamestates;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UIConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entities.ObstacleManager;
import entities.Player;
import main.Game;
import main.GameManager;
import overlay.GameOverOverlay;
import overlay.PauseOverlay;
import ui.Button;
import ui.PlayScore;

/** 
 * La classe Playing eredita i campi e i metodi della classe "State", e gestisce la schermata di gioco. Qui l'utente può vedere a schermo
 * l'auto (Player) e gli ostacoli. Il gioco procede a turni dopo aver cliccato sul tasto "next" presente sulla destra dello schermo,
 * il player si muove in base al suo comportamento e successivamente avviene anche il movimento di tutti gli ostacoli. L'utente ha la
 * possibilità di mettere in pausa il gioco mediante il tasto "pause" e accedere al menu di pausa dove può riprendere il gioco, tornare
 * al menu principale o salvare la partita per poi continuarla successivamente
 * 
 * @See State
 * @See Load
 * @See Playing
 * @Author Silvestri Claudio
 */

public class Playing extends State implements Statemethods, Serializable {
	
	private static final long serialVersionUID = 6050458419898860103L;
	
	private Player player;

	private int xPlayerPosition = (GAME_WIDTH - TILES_SIZE) / 2; //Posiziono il player al centro del gioco
	private int yPlayerPosition = (GAME_HEIGHT - TILES_SIZE); //Posiziono il player al centro del gioco
	
	private GameManager gameManager = GameManager.getInstance();
	
	private ObstacleManager obstacleManager;
	
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;
	
	private boolean paused = false;
	private boolean noLife = false;
	
	private Button[] buttons = new Button[2];
	private PlayScore playScore;

	public Playing(Game game, JFrame jframe) {
		super(game, jframe); 
		initClasses();
		loadButtons();
		loadScore();
	}
	
	/** 
	 * Inizializzo il gioco creando l'obstacleManager che gestirà gli ostacoli, il player e i menu di overlay
	 */
	private void initClasses() {
		
		player = new Player(xPlayerPosition,yPlayerPosition, TILES_SIZE, TILES_SIZE);
		obstacleManager = new ObstacleManager(player);
		pauseOverlay = new PauseOverlay(this,jframe);
		gameOverOverlay = new GameOverOverlay(this);
		
	}
	
	/** 
	 * Creo i bottoni della schermata di gioco
	 */
	@Override
	public void loadButtons() {

		buttons[0] = new Button("Pause", TOTAL_WIDTH - (GAME_HUD_WIDTH / 2), TOTAL_HEIGHT / 3 - GAME_HUD_HEIGHT, PLAYING_BUTTON_WIDTH, PLAYING_BUTTON_HEIGHT, null);
		buttons[1] = new Button("Next", TOTAL_WIDTH - (GAME_HUD_WIDTH / 2), TOTAL_HEIGHT - (TOTAL_HEIGHT / 3), PLAYING_BUTTON_WIDTH, PLAYING_BUTTON_HEIGHT, null);
	}
	
	/** 
	 * Creo lo score della partita
	 */
	private void loadScore() {

		playScore = new PlayScore(TOTAL_WIDTH - (GAME_HUD_WIDTH / 2), TOTAL_HEIGHT / 2);

	}

	/** 
	 * Eseguo, per ogni elemento del gioco la rispettiva funzione "draw"
	 */
	@Override
	public void draw(Graphics g) {

		g.setColor(Color.black);
		drawGrid(g); //Disegno la griglia di gioco
		player.draw(g);
		
		obstacleManager.draw(g);
		
		for (Button b : buttons){
			b.draw(g);
		}
		
		playScore.draw(g);
		
		if(paused) {
			
			pauseOverlay.draw(g); //eseguo la funzione solo se mi trovo nel menu di pausa

		} else {
			
			if (noLife) {
			
				gameOverOverlay.draw(g); //eseguo la funzione solo se mi trovo nel menu di game over
				
			}
				
		}
		 
	}
	
	/** 
	 * Tolgo dalla pausa il gioco
	 */
	public void unpauseGame() {
		
		paused = false;
		
	}
	
	/** 
	 * Metto in pausa il gioco
	 */
	public void pauseGame() {
		
		paused = true;
		
	}
	
	/** 
	 * Metto il gioco nella fase di game over
	 */
	public void gameOver() { 
		
		noLife = true;
		
	}
	
	/** 
	 * resetto la partita impostando tutti gli elementi al valore iniziale
	 */
	public void retry() {
		
		player.reset();
		obstacleManager.reset();
		gameManager.reset();
		noLife = false;
		
	}
	
	/** 
	 * Eeguo la funzione nextMove ad ogni turno della partita, modificando posizione di ostacoli e player e controllando lo stato del gioco
	 */
	public void nextMove() {
		

		obstacleManager.checkNeighborhood(); //controllo se un ostacolo è nell'intorno del player
		obstacleManager.countObstacle(); //conto gli ostacoli alla sinistra e destra del player
		player.newPosition(); //cambio posizione al player
		obstacleManager.newPosition(); //Muovo gli ostacoli già esistenti
		obstacleManager.newObstacle(); //Creo un nuovo ostacolo da aggiungere al gioco
		if (obstacleManager.checkPlayerPosition()) { //controllo se un ostacolo colpisce il player
			
			gameManager.setPlayerLife(gameManager.getPlayerLife() - 1); //diminuisco la vita del player
			player.loseLife(); //cambio il comportamento del player
			
			if(gameManager.getPlayerLife() == 0) {
				
				gameOver(); //avvio la schermata di gameover se il player non ha più vita
				
			}
			
		} else {
			
			gameManager.setScore(gameManager.getScore() + 1); //incremento il punteggio per ogni turno
				
		}
		
	}

	/** 
	 * Controllo i click dell'utente e in base alla loro posizione eseguo i vari bottoni con le rispettive funzioni
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(paused) {
			pauseOverlay.mousePressed(e); //controllo gli eventuali click avvenuti nel menu di pausa
		} else {
		
			if(noLife){
				
					gameOverOverlay.mousePressed(e); //controllo gli evenutali click avvenuti nel menu di game over
				
				} else {
					
					for (Button b : buttons){
						if(isIn(e,b)) {
//							pb.Action();
							if(b.getButtonName() == "Pause") {
								pauseGame(); //metto in pausa il gioco
							}
							else {
								nextMove(); //avvio il nuovo turno
							}
							break;
						}
						
					}
				}
			}
	}	
	
	/** 
	 * Funzione che salva la partita in un file avente nome e cognome inserito
	 */
    public void save(String nomeFile){
    	
    	File f = new File(nomeFile);
    	ObjectOutputStream oss;
    	
            try{
            	
            	//Se il file esiste già (quindi esiste già un salvataggio) mostro un messaggio di conferma per sovrascrivere il file
            	if(f.exists()) {
            		
                	int result = JOptionPane.showConfirmDialog(jframe,"Save already exists, Do you want to overwrite it?","Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION){
                    	
                    	//salvo sul file avente nome e cognome inserito lo stato della partita
                        oss = new ObjectOutputStream(new FileOutputStream(f));
                        oss.writeObject(player);
                        oss.writeObject(obstacleManager);
                        oss.writeObject(gameManager);
                        oss.close();
                		pauseOverlay.setSavingError(false); //salvataggio avvenuto correttamente
                                                
                     }else if (result == JOptionPane.NO_OPTION){
                    	 
                 		pauseOverlay.setSavingError(false); //salvataggio non avvenuto, ma senza errori
                    	 
                     }
    
            		
            	} else {
            		
                	//creo un file avente nome e cognome inserito e salvo lo stato della partita
                    oss = new ObjectOutputStream(new FileOutputStream(f));
                    oss.writeObject(player);
                    oss.writeObject(obstacleManager);
                    oss.writeObject(gameManager);
                    oss.close();
            		pauseOverlay.setSavingError(false); //salvataggio avvenuto correttamente
            		
            	}

            }
            
            catch(FileNotFoundException e){
                //messaggio di errore
        		System.out.println(e);
        		JOptionPane.showMessageDialog(jframe,"Error during save, please insert a valid Name or Surname!");
        		pauseOverlay.setSavingError(true); //errore durante il salvataggio
        		

            }
            
            catch(Exception e){
                //messaggio di errore
        		System.out.println(e);
        		JOptionPane.showMessageDialog(jframe,"Error during save, please try again!");
        		pauseOverlay.setSavingError(true); //errore durante il salvataggio
        		

            }
            
    }
    
	/** 
	 * Funzione che carica la partita in base al nome e cognome inserito
	 */
    public void load(String nomeFile){
    	
        ObjectInputStream ois;
    	File f = new File(nomeFile);

        
        try{
        	//controllo se il salvataggio esiste
        	if (f.exists()) {
        		
        		//apro il file corrispondente al nome e cognome inserito, e carico lo stato della partita salvata
                ois = new ObjectInputStream(new FileInputStream(f));
                player = (Player) ois.readObject();
                obstacleManager = (ObstacleManager) ois.readObject();
                gameManager = (GameManager) ois.readObject();
        		game.getLoad().setLoadingError(false);

                
        	} else {
        		
        		//visualizzo un messaggio che mi dice che il salvataggio non esiste, rimango nella schermata di Load
        		Gamestate.state = Gamestate.LOAD;
        		game.getLoad().setLoadingError(true);
        		JOptionPane.showMessageDialog(jframe,"Save doesn't exists!");
        		
        	}   
        }
        catch(Exception e){
        	
                //Eventuale messaggio di errore quando il salvataggio non è avvenuto con successo
        		System.out.println(e);
        		Gamestate.state = Gamestate.LOAD;
        		game.getLoad().setLoadingError(true);
        		JOptionPane.showMessageDialog(jframe,"Error during load, the save file is corrupted!");


        }
    }
    
	/** 
	 * Disegno le linee di gioco dove si muoveranno gli ostacoli e il player
	 */
    public void drawGrid(Graphics g) {
    	
		for(int i = 0; i < TILES_IN_WIDTH + 1; i++) {
			
			g.drawLine(TILES_SIZE * i, 0, TILES_SIZE * i, GAME_HEIGHT); //disegno le righe verticali
			
		}
		
		for(int i = 0; i < TILES_IN_HEIGHT; i++) {
			
			g.drawLine(0, TILES_SIZE * i, GAME_WIDTH, TILES_SIZE * i); //disegno le righe orizzonatli
			
		}
    	
    }
     
}
