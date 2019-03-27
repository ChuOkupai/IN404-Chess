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
		int dx = x2 - x1; 
		int dy = y2 - y1;
		
		if(x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7)//si l'utilisateur est un utilisateur
			return false;
		if(x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) //si c'est en dehors
			return false;
		if(x1 == x2 && y1==y2) // au cas où
			return false;	
			
		if(this.getColor() == 0){dx = -dx; dy = -dy;} //les noirs en haut
		return true;
	}
}