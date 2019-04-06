/*
 * Classe représentant le plateau du jeu
 *
 * @author	Adrien Soursou
 * @version	06/04/2019
 */

public class ChessBoard
{
	private Piece[][] board;
	int[] kingX, kingY;
	
	// Couleurs pour le rendu
	private static final String reset = "\033[0m"; // Reset text color
	private static final String bbrown = "\033[48;2;135;84;45m"; // Brown background
	private static final String bblack = "\033[48;2;0;0;0m"; // Black background
	private static final String bwhite = "\033[48;2;200;200;200m"; // White background
	private static final String fblack = "\033[38;2;0;0;0m"; // Black foreground
	private static final String fwhite = "\033[38;2;200;200;200m"; // White foreground
	
	/**
	 * Constructeur du plateau de jeu
	 */
	public	ChessBoard()
	{
		int x, y1, y2;
		
		board = new Piece[8][8];
		for (x = 0, y1 = 1, y2 = 6; x < 8; x++)
		{
			board[y2][x] = new Pawn(0);
			board[y1][x] = new Pawn(1);
		}
		// y = ligne, x = colonne
		board[7][1] = new Knight(0);
		board[7][6] = new Knight(0);
		board[0][1] = new Knight(1);
		board[0][6] = new Knight(1);
		board[7][2] = new Bishop(0);
		board[7][5] = new Bishop(0);
		board[0][2] = new Bishop(1);
		board[0][5] = new Bishop(1);
		board[7][0] = new Rook(0);
		board[7][7] = new Rook(0);
		board[0][0] = new Rook(1);
		board[0][7] = new Rook(1);
		board[7][3] = new Queen(0);
		board[0][3] = new Queen(1);
		board[7][4] = new King(0);
		board[0][4] = new King(1);
		int[] kingX = new int[2];
		int[] kingY = new int[2];
		kingY[0] = 7; kingX[0] = 4;
		kingY[1] = 0; kingX[1] = 4;
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
	 * Vérifie si une pièce est un pion
	 * @param x coordonnée horizontale
	 * @param y coordonnée verticale
	 * @return vrai si la pièce est un pion, faux sinon
	 */
	public boolean	isPawn(int x, int y)
	{
		return (board[y][x] != null && board[y][x].getSprite().equals("♙") == true) ? true : false;
	}
	
	/**
	 * Permet de récupérer la couleur d'une pièce sur le plateau
	 * @param x coordonnée horizontale
	 * @param y coordonnée verticale
	 * @return 0 si noir, 1 si blanc et -1 si la pièce n'existe pas
	 */
	public int	getPieceColor(int x, int y)
	{
		return (isEmpty(x, y) == true) ? -1 : board[y][x].getColor();
	}
	
	/**
	 * Déplacement d'une pièce sur le plateau
	 * @param color la couleur du joueur
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 * @return	vrai si la pièce a été déplacée, faux sinon
	 */
	public boolean	doMove(int color, int x1, int y1, int x2, int y2)
	{
		if (isOnBoard(x1, y1) == false || isOnBoard(x2, y2) == false || isEmpty(x1, y1) == true)
			return false;
		else if (x1 == x2 && y1 == y2)
			return false;
		else if (board[y1][x1].getColor() != color)
			return false;
		else if (board[y1][x1].movePossible(this, x1, y1, x2, y2) == false)
			return false;
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
		if (board[y2][x2].getSprite().equals("♔"))
		{
			kingX[color] = x2;
			kingY[color] = y2;
			// VERIF ECHEC AVEC PILE
		}
		return true;
	}
	
	/**
	 * Vérifie les situations d'échecs
	 * @param color la couleur du joueur
	 * @return vrai si il y a une situation d'échec
	 */
	private boolean	isCheck(int color)
	{
		int x, y = 0;
		while (y < 8)
		{
			x = 0;
			while (x < 8)
				if (board[y][x] != null && board[y][x].getColor() != color)
					if (board[y][x].movePossible(this, x, y, kingX[color], kingY[color]) == true)
						return true;
		}
		return false;
	}
	
	/**
	 * Vérifie les situations d'échecs et mat
	 * @param color la couleur du joueur
	 * @return 1 si en échec, 2 si échec et mat, 0 sinon
	 */
	public int	isCheckmate(int color)
	{
		if (isCheck(color) == false)
			return 0;
		int x = kingX[color] - 1, y = kingY[color] - 1, i = 0;
		return 0;
	}
	
	/**
	 * Affiche le plateau de jeu
	 */
	public void	render()
	{
		String	buf;
		int		color = 1, x, y;
		
		System.out.println(bbrown + "    a b c d e f g h   " + reset);
		for (y = 7; y >= 0; y--)
		{
			System.out.print(bbrown + " " + (y + 1) + " " + reset);
			for (x = 0; x < 8; x++)
			{
				System.out.print((color == 0) ? bblack : bwhite);
				if (board[y][x] == null) // case vide
					System.out.print("  ");
				else
				{
					if (color == board[y][x].getColor()) // pièce de même couleur que la case
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
	}
	
	/**
	 * Gère la promotion d'une pièce
	 **/
	private void promote(int x, int y)
	{
		// c'est la fête
	}
}