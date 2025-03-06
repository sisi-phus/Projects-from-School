package sc.player2022.logic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sc.api.plugins.IGameState;
import sc.player.IGameHandler;
import sc.plugin2022.Coordinates;
import sc.plugin2022.GameState;
import sc.plugin2022.Move;
import sc.plugin2022.Piece;
import sc.plugin2022.PieceType;
import sc.shared.GameResult;

import java.util.List;

/**
 * Das Herz des Clients:
 * Eine sehr simple Logik, die ihre Zuege zufaellig waehlt,
 * aber gueltige Zuege macht.
 * <p>
 * Ausserdem werden zum Spielverlauf Konsolenausgaben gemacht.
 */
public class Logic implements IGameHandler {
  private static final Logger log = LoggerFactory.getLogger(Logic.class);

  /** Aktueller Spielstatus. */
  private GameState gameState;
  public Move myLastMove = null;

  @Override
  public Move calculateMove() {
	  	long startTime = System.currentTimeMillis();
	    log.info("Es wurde ein Zug von {} angefordert.", gameState.getCurrentTeam());
	    int Bewertung_currentMove;
	    int Bewertung_bestMove;
	    
	    
	    List<Move> possibleMoves = gameState.getPossibleMoves(); // oder ggf. gameStateClone.getPossibleMoves() ...
	    Bewertung_bestMove = 0;
	    Move bestMove = null;
	    for (int i=0; i<possibleMoves.size(); i++) { // Schleife für jeden möglichen Spielzug
	        Move currentMove = possibleMoves.get(i);
	        Bewertung_currentMove = 100; // Standardbewertung: 100
	        Coordinates woher = currentMove.getFrom();
	        Coordinates wohin = currentMove.getTo();
		    System.out.println("----------");
	        System.out.println("Zu prüfender Zug: " + gameState.getBoard().get(woher.getX(), woher.getY()));
	        Piece currentPiece = gameState.getBoard().get(woher.getX(), woher.getY());
	        
	        GameState gameStateClone = gameState.clone();  // Kopie des GameState um darauf auszuprobieren... mit "perform"...
		    gameStateClone.performMove(currentMove);
		    List<Move> possible_opposite_Moves = gameStateClone.getPossibleMoves();
		    for (int j=0;j<possible_opposite_Moves.size();j++) {
		    	Move current_opposite_Moves = possible_opposite_Moves.get(j);
		    	if( wohin.equals(current_opposite_Moves.getTo())) {
		    		Bewertung_currentMove -=150;
		    		
		    		
		    		
		    	     if (gameStateClone.getBoard().get(wohin.getX(), wohin.getY()) != null )  { 
		 	        	System.out.print("gegnerisches Feld gefunden: "+wohin.getX()+wohin.getY());
		 	        	Bewertung_currentMove += 50; // kann man den Gegner schlagen, ist es besser?
		 	        	if(currentPiece.component1()== PieceType.Robbe) {
		 	        			System.out.println("Robbe gefunden! | Bewertung: -20");
		 	        			Bewertung_currentMove -= 20;
		 	        	}
		 	        	else if(currentPiece.component1()== PieceType.Seestern) {
		 						System.out.println("Seestern gefunden! | Bewertung: -20");
		 						Bewertung_currentMove -= 20; 
		 	        	}    
		 	        	else if(currentPiece.component1()== PieceType.Moewe) {
		 						System.out.println("Seestern gefunden! | Bewertung: -20");
		 						Bewertung_currentMove -= 20; 
		 	        	}  
		 	        	else if(currentPiece.component1()== PieceType.Herzmuschel) {
		 						System.out.println("Seestern gefunden! | Bewertung: -20");
		 						Bewertung_currentMove -= 20;  
		 	        	}  
		 	         
		 				if(currentPiece.component1()== PieceType.Robbe) {
		 				       System.out.println("Robbe gefunden! | Bewertung: +20");
		 				       Bewertung_currentMove += 20;
		 				}
		 				else if(currentPiece.component1()== PieceType.Seestern) {
		 						System.out.println("Seestern gefunden! | Bewertung: +20");
		 						Bewertung_currentMove += 20; 
		 				}    
		 				else if(currentPiece.component1()== PieceType.Moewe) {
		 						System.out.println("Seestern gefunden! | Bewertung: +20");
		 						Bewertung_currentMove += 20; 
		 				}  
		 				else if(currentPiece.component1()== PieceType.Herzmuschel) {
		 						System.out.println("Seestern gefunden! | Bewertung: +20");
		 						Bewertung_currentMove += 20;  
		 				} 
		 	        
		 	        }
		 	        if (gameStateClone.getBoard().get(wohin.getX(), wohin.getY()) !=null) {
		 	        	System.out.print("gegnerisches Feld gefunden:"+wohin.getX()+wohin.getY());
		 	        	Bewertung_currentMove +=100;
		 	        	if(gameStateClone.getBoard().get(wohin.getX(),wohin.getY()).component3()>1) {
		 	        		System.out.println("generischen Turm gefunden bei:"+wohin);
		 	        		Bewertung_currentMove += 200;
		 	        	}
		 	        }
		 	        	
		 	     
		 		        	
		 	        	
		    	
		    	}
		    		
		   
		    }
	        	
		    
       	 if (gameState.getBoard().get(wohin.getX(), wohin.getY()) !=null) {
	        	System.out.print("gegnerisches Feld gefunden:"+wohin.getX()+wohin.getY());
	        	Bewertung_currentMove +=150;
	        	if(gameState.getBoard().get(wohin.getX(),wohin.getY()).component3()>1) {
	        		System.out.println("generischen Turm gefunden bei:"+wohin);
	        		Bewertung_currentMove += 200;
         
     
      
			if(currentPiece.component1()== PieceType.Robbe) {
			       System.out.println("Robbe gefunden! | Bewertung: +20");
			       Bewertung_currentMove -=20;
			}
			else if(currentPiece.component1()== PieceType.Seestern) {
					System.out.println("Seestern gefunden! | Bewertung: +20");
					Bewertung_currentMove -= 20; 
			}    
			else if(currentPiece.component1()== PieceType.Moewe) {
					System.out.println("Seestern gefunden! | Bewertung: +20");
					Bewertung_currentMove -= 20; 
			}  
			else if(currentPiece.component1()== PieceType.Herzmuschel) {
					System.out.println("Seestern gefunden! | Bewertung: +20");
					Bewertung_currentMove -= 20;  
			} 
     
	       }

     	 }
		    
   	     if (gameState.getBoard().get(wohin.getX(), wohin.getY()) != null )  { 
	        	System.out.print("gegnerisches Feld gefunden: "+wohin.getX()+wohin.getY());
	        	Bewertung_currentMove += 100; // kann man den Gegner schlagen, ist es besser?
	        	if(currentPiece.component1()== PieceType.Robbe) {
	        			System.out.println("Robbe gefunden! | Bewertung: -20");
	        			Bewertung_currentMove += 20;
	        	}
	        	else if(currentPiece.component1()== PieceType.Seestern) {
						System.out.println("Seestern gefunden! | Bewertung: -20");
						Bewertung_currentMove += 20; 
	        	}    
	        	else if(currentPiece.component1()== PieceType.Moewe) {
						System.out.println("Seestern gefunden! | Bewertung: -20");
						Bewertung_currentMove += 20; 
	        	}  
	        	else if(currentPiece.component1()== PieceType.Herzmuschel) {
						System.out.println("Seestern gefunden! | Bewertung: -20");
						Bewertung_currentMove += 20;  
	        	}  
	        
	        	
	        	if (myLastMove != null) {
		        	if (currentMove.getFrom().getX() == myLastMove.getFrom().getX() && currentMove.getFrom().getY() == myLastMove.getFrom().getY()/* && currentPiece.component1() == myLastMove.component1()*/) {
		        		System.out.print("Der Zug macht den alten Rückgängig!: " + currentPiece);
		        		Bewertung_currentMove -= 50;  
		        	}
		        	
		        	System.out.println("mein letzter Zug war: "+ myLastMove);
		            if (myLastMove != null)  {
		            	if(wohin.getX() == myLastMove.getFrom().getX() &&  wohin.getY() == myLastMove.getFrom().getY())
		            	{ Bewertung_currentMove -=90;
		            			System.out.println("<<<<<<<<<<<<<ï¿½bereinstimmung, Bewertung"+Bewertung_currentMove);
		            	}
		            	System.out.println("## currentMove wohin ist "+ wohin);
		            	System.out.println("## myLastMove woher ist " + myLastMove.getFrom());
		            }
		        	
	        	}
   	     }
	        
	        System.out.println("aktuelle Bewertung: " + Bewertung_currentMove);
	        System.out.println("possible Moves: "+ possibleMoves.get(i));
	        System.out.println("possible Moves: "+ possibleMoves.get(i).component1());
	        //SASAGEO SASAGEO OAJHWIUHFCLIAWJHFLIKJW
	        
	        // SNADDY UWU
	        
	        // LEVIROSAA
	        
	        // Bestmove überprüfen
	        if (Bewertung_currentMove > Bewertung_bestMove) {
	    	    Bewertung_bestMove=Bewertung_currentMove;
	    	    bestMove=currentMove;
	    	    
	    	    
	    	     if (Bewertung_bestMove > Bewertung_currentMove) {
	    	    	 Move move = bestMove;
	    	 	    myLastMove = move;
	    	    	 return move;
	    	    }
	        }
	    }

	    Move move = bestMove;
	    myLastMove = move;
	    log.info("Sende {} nach {}ms.", move, System.currentTimeMillis() - startTime);
	    return move;
	    
  } 

  @Override
  public void onUpdate(IGameState gameState) {
    this.gameState = (GameState) gameState;
    log.info("Zug: {} Dran: {}", gameState.getTurn(), gameState.getCurrentTeam());
  }
  
  public void onGameOver(GameResult data) {
	    log.info("Das Spiel ist beendet, Ergebnis: {}", data);
  }

  @Override
  public void onError(String error) {
    log.warn("Fehler: {}", error);
  }
}
