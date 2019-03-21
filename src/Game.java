/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou
 * @version	15/03/2019
 */

public class Game
{
	private ChessBoard chessb;
	private int turn;
	
	/**
	 * Constructeur d'objets de classe Game
	 */
	public Game()
	{
		this.chessb = new ChessBoard();
		this.turn = 0;
	}
	
	/**
	 *Vérifie si une coordonnée est dans les limites du plateau
	 */
	private boolean isOnBoard()
	{
		if(/*Coor audelà des limites*/)return false;
		return true;
	}
	
	/**
	 *Interprète les commande envoyé par un des joueurs  
	 **/
	private void parseCom(String )
	{
		
	}
	 
	/**
	 * S'occupe de la logique de jeu
	 */
	public void run()
	{
		
		while(!isCheckmate())
		{
			/*
			 * - 1.Attente Entrée joueur n
			 * - 2.Vérif des entrées
			 * - 3.Affichage 
			 * - 4.changement de tour
			 * */
		}
	}

}
