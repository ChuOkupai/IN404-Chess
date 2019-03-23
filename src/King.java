/*
 * Classe du Roi
 *
 * @author	Adrien Soursou
 * @version	17/03/2019
 */

public class King extends Piece
{
	/**
	 * Constructeur du Roi
	 */
	public King(int color)
	{
		// Affectation des sprites
		super(color,"♔","♚");
	}
	
	/**
	 * Cette méthode permet à la pièce de se déplacer
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 * @return	un boolean indiquant si le mouvement est possible ou non
	 */
	@Override
	public boolean movePossible(ChessBoard b, int x1, int y1, int x2, int y2)
	{
		// Propriétés du Roi
		if (Math.abs(x2 - x1) > 1 || Math.abs(y2 - y1) > 1)
			return false; // déplacement supérieur à une case
		else if (b.isEmpty(x2, y2) == false && b.getPiece(x2, y2).getColor() == this.getColor())
			return false; // case non vide et même couleur
		return true; // case vide ou couleur différente
	}
}