/*
 * Classe de chevalier
 *
 * @author	Mathis Dankou
 * @version	20/03/2019
 */

public class Knight extends Piece
{
	/**
	 * Constructeur du cavalier
	 */
	public Knight(int color)
	{
		super(color,"♘","♞");
	}
	
	/**
	 * Cette méthode permet à la piece de se déplacer (que j' @Override)
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 * @return	un boolean indiquant si le mouvement est possible ou non
	 */
	@Override
	public boolean movePossible(ChessBoard b, int x1, int y1, int x2, int y2)
	{
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		
		if(x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7)//si l'utilisateur est un utilisateur ;))))))))))))
			return false;
		if(x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) //si c'est en dehors
			return false;
		if(x1 == x2 && y1==y2) // au cas où
			return false;
		
		if(dx > 2 || dy > 2) return false;
		if((dx == 1 && dy == 2) || (dx == 2 && dy == 1))
		{
			if(b.isEmpty(x2, y2))
				return true;
			if(!b.isEmpty(x2, y2) && b.getPiece(x2, y2).getColor() != this.getColor())
				return true;
		}
		return false;
	}
}
