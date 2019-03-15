/*
 * Classe représentant le plateau du jeu
 *
 * @author	Adrien Soursou
 * @version	15/03/2019
 */

public class ChessBoard
{
	private Piece[][] board;
	
	/**
	 * Constructeur du plateau de jeu
	 */
	public	ChessBoard()
	{
		board = new Piece[8][8];
	}
	
	/**
	 * Affiche le plateau de jeu
	 */
	void	render()
	{
		System.out.println("wesh ma gueule");
	}
}