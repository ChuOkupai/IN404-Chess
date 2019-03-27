/*
 * Classe du Pion(Exemple)
 *
 * @author	Mathis Dankou
 * @version	15/03/2019
 */

public class Pawn extends Piece
{
	/**
	 * Constructeur du pion
	 */
	public Pawn(int color)
	{
		super(color,"♙","♟");
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
		int dx = x2 - x1; 
		int dy = y2 - y1;
		
		if(x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7)//si l'utilisateur est un utilisateur ;))))))))))))
			return false;
		if(x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) //si c'est en dehors
			return false;
		if(x1 == x2 && y1==y2) // au cas où
			return false;
		
		if(this.getColor() == 0){dx = -dx; dy = -dy;} //les noirs en haut
		
		if(dy < 0 ) return false; //pas de retour en arrière
		if(dy > 2) return false; //ne peut pas aller au dela de dy = 2 
		if(dy == 2)
		{
			if(dx == 0 && (y1 ==  1 || y1 == 6)) //ou verif de la pos de base
				if(b.isEmpty(x2, y2))
					return true;
		}
		if(dy == 1)
		{
			if(dx == -1 || dx == 1)
			{
				if(b.isEmpty(x2, y2))
					return true;
				if(!b.isEmpty(x2, y2) && b.getPiece(x2, y2).getColor() != this.getColor())
					return true;
			}
		}
		
		return false;
	}
}
