/*
 * Classe de la Reine
 *
 * @author	Paul-Victor Chabé
 * @version	21/03/2019
 */
public class Queen extends Piece
{
	/**
	 * Constructeur de la reine
	 */
	public Queen (int color)
	{
		super(color,"♕","♛");
	}
	
	/**
	 * Cette méthode permet à la pièce de se déplacer
	 * @param	b le plateau de jeu
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 * @return	un boolean indiquant si le mouvement est possible ou non
	 */
	@Override
	public boolean movePossible(ChessBoard b, int x1, int y1, int x2, int y2)
	{
		
		return false;
	}
}