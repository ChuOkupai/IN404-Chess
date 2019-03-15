/**
 * Classe du Pion(Exemple)
 *
 * @author	Mathis Dankou
 * @version	15/03/2019
 */

public class Pawn extends Piece
{
	/**
	 * Cette méthode permet à la piece de se déplacer (que j' @Override)
	 * @param	x1 la position x de départ de la pièce
	 * @param	y1 la position y de départ de la pièce
	 * @param	x2 la position x de d'arrivé de la pièce
	 * @param	y2 la position y de d'arrivé de la pièce
	 * @return	un boolean indiquant si le mouvement est possible ou non
	 */
	@Override
	public boolean movePossible(int x1, int y1, int x2, int y2)
	{
		int dx = x2-x1;
		int dy = y2-y1;
		
		if(this.color == 0)
		{
			
		}
		else
		{
			if(dx > 1 || dx < 0) return false
			if(dy < 0) return false
			if(dx == 0)
			{
				if(y1 == 1)
				{
					if(dy > 2) return false;
					//A compléter ;)
				}
			}
		}
		
	}
}