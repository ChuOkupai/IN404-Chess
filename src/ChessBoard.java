/*
 * Classe représentant le plateau du jeu
 *
 * @author	Adrien Soursou
 * @version	17/03/2019
 */

public class ChessBoard
{
	private Piece[][] board;
	
	// Couleurs pour le rendu
	private static final String reset = "\033[0m"; // Reset text color
	private static final String bbrown = "\033[48;2;135;84;45m"; // Brown background
	private static final String bblack = "\033[48;2;0;0;0m"; // Black background
	private static final String bwhite = "\033[48;2;190;190;190m"; // White background
	private static final String fblack = "\033[38;2;0;0;0m"; // Black foreground
	private static final String fwhite = "\033[38;2;190;190;190m"; // White foreground
	
	/**
	 * Constructeur du plateau de jeu
	 */
	public	ChessBoard()
	{
		int x, y1, y2;
		
		board = new Piece[8][8];
		for (x = 0, y1 = 1, y2 = 6; x < 8; x++)
		{
			board[y1][x] = new Pawn(1);
			board[y2][x] = new Pawn(0);
		}
		board[0][1] = new Knight(1);
		board[0][6] = new Knight(1);
		board[7][1] = new Knight(0);
		board[7][6] = new Knight(0);
		board[0][4] = new King(1);
		board[7][4] = new King(0);
	}
	
	/**
	 * Vérification des coordonnées
	 * @param x coordonnée horizontale
	 * @param y coordonnée verticale
	 * @return vrai si les coordonnées sont dans les limites du plateau
	 */
	public boolean isOnBoard(int x, int y)
	{
		return (x < 0 || x > 7 || y < 0 || y > 7) ? false : true;
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
	 * Permet de récupérer une pièce sur le plateau
	 * @param x coordonnée horizontale
	 * @param y coordonnée verticale
	 * @return la pièce si elle existe, null sinon
	 */
	public Piece	getPiece(int x, int y)
	{
		return board[y][x];
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
	 * Affiche le plateau de jeu en utilisant les couleurs
	 */
	public void	render()
	{
		// Sprites			♖ ♘ ♗ ♕ ♔ ♙
		// Sprites (fill)	♜ ♞ ♝ ♛ ♚ ♟
		String	buf;
		int		color = 1, x, y;
		
		System.out.println(bbrown + "    a b c d e f g h   " + reset);
		for (y = 7; y >= 0; y--)
		{
			System.out.print(bbrown + " " + (y + 1) + " " + reset);
			for (x = 0; x < 8; x++)
			{
				System.out.print((color == 0) ? bblack : bwhite);
				if (board[y][x] == null)
					System.out.print("  ");
				else
				{
					if (color == board[y][x].getColor())
						buf = board[y][x].getSprite();
					else
						buf = board[y][x].getSpriteFill();
					System.out.print((color == 0) ? fwhite : fblack);
					System.out.print(buf + " ");
				}
				color = 1 - color;
			}
			System.out.println(reset + bbrown + " " + (y + 1) + " " + reset);
			color = 1 - color;
		}
		System.out.println(bbrown + "    a b c d e f g h   " + reset);
		
		// Test (Debug)
		System.out.println("movePossible(King):");
		System.out.println("BLACK_KING(e8->d8) = " + board[7][4].movePossible(this, 4, 7, 3, 7));
		System.out.println("WHITE_KING(e1->e2) = " + board[0][4].movePossible(this, 4, 0, 4, 1));
	}
}