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
	 * Renvoie vrai la case est vide
	 */
	public boolean	isEmpty(int x, int y)
	{
		return (board[y][x] == null) ? true : false;
	}
	
	/**
	 * Renvoie vrai en cas de situation d'échec
	 */
	public boolean	isCheck()
	{
		return false;
	}
	
	/**
	 * Renvoie vrai en cas de situation d'échec et mat
	 */
	public boolean	isCheckmate()
	{
		return false;
	}
	
	/**
	 * Affiche le plateau de jeu
	 */
	public void	render()
	{
		// Enlever bordures + ajout couleur plateau
		System.out.println("   ┌───┬───┬───┬───┬───┬───┬───┬───┐");
		System.out.println(" 8 │ ♜ │ ♞ │ ♝ │ ♛ │ ♚ │ ♝ │ ♞ │ ♜ │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 7 │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 6 │   │   │   │   │   │   │   │   │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 5 │   │   │   │   │   │   │   │   │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 4 │   │   │   │   │   │   │   │   │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 3 │   │   │   │   │   │   │   │   │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 2 │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │");
		System.out.println("   ├───┼───┼───┼───┼───┼───┼───┼───┤");
		System.out.println(" 1 │ ♖ │ ♘ │ ♗ │ ♕ │ ♔ │ ♗ │ ♘ │ ♖ │");
		System.out.println("   └───┴───┴───┴───┴───┴───┴───┴───┘");
		System.out.println("     A   B   C   D   E   F   G   H");
	}
}