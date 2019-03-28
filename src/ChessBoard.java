/*
 * Classe représentant le plateau du jeu
 *
 * @author	Adrien Soursou
 * @version	27/03/2019
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
	 * /!\ OBSOLETE /!\ Utiliser getPieceColor pour récupérer la couleur
	 * (suppression d'ici les prochaines versions)
	 * @param x coordonnée horizontale
	 * @param y coordonnée verticale
	 * @return la pièce si elle existe, null sinon
	 */
	public Piece	getPiece(int x, int y)
	{
		return board[y][x];
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
	 * Cette méthode permet de tester un déplacement (debug)
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 */
	public void	checkMove(int x1, int y1, int x2, int y2)
	{
		if (isOnBoard(x1, y1) == false || isOnBoard(x2, y2) == false || isEmpty(x1, y1) == true)
			System.out.println("error: invalid coordinates");
		else
		{
			System.out.print("Trying to move Piece from ");
			System.out.print((char)(x1 + 97) + "" + (y1 + 1) + " to " + (char)(x2 + 97) + "" + (y2 + 1));
			System.out.println(" > " + board[y1][x1].movePossible(this, x1, y1, x2, y2));
		}
	}

	/**
	 * Déplacement d'une pièce sur le plateau
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 * @return	vrai si la pièce a été déplacée, faux sinon
	 */
	public boolean	doMove(int x1, int y1, int x2, int y2)
	{
		if (isOnBoard(x1, y1) == false || isOnBoard(x2, y2) == false || isEmpty(x1, y1) == true)
			return false;
		else if (board[y1][x1].movePossible(this, x1, y1, x2, y2) == false)
			return false;
		board[y2][x2] = board[y1][x1];
		return true;	
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
	}
}