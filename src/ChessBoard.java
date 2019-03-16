/*
 * Classe représentant le plateau du jeu
 *
 * @author	Adrien Soursou
 * @version	16/03/2019
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
	 * Détection d'une case vide
	 * @param x coordonnée horizontale
	 * @param y coordonnée verticale
	 * @return vrai si la case est vide
	 */
	public boolean	isEmpty(int x, int y)
	{
		return (board[y][x] == null) ? true : false;
	}
	
	/**
	 * Vérifie les situations d'échecs
	 * @return vrai si il y a une situation d'échec
	 */
	public boolean	isCheck()
	{
		return false;
	}
	
	/**
	 * Vérifie les situations d'échecs et mat
	 * @return vrai si il y a une situation d'échec et mat
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
		int color = 1, x, y;
		
		for (y = 8; y > 0; y--)
		{
			System.out.print(" " + y + " ");
			for (x = 0; x < 8; x++)
			{
				System.out.print((color == 0) ? Color.BACKGROUND_BLACK : Color.BACKGROUND_WHITE);
				System.out.print("   ");
				color = 1 - color;
			}
			System.out.println(Color.RESET);
			color = 1 - color;
		}
		// Sprites			♖ ♘ ♗ ♕ ♔ ♙
		// Sprites (fill)	♜ ♞ ♝ ♛ ♚ ♟
		System.out.println("    a  b  c  d  e  f  g  h");
	}
}