/*
 * Classe de la Reine
 *
 * @author	Paul-Victor Chabé, Mathis Dankou
 * @version	22/04/2019
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
		if(b.getPieceColor(x2, y2) == this.getColor()) return false;
		
		int dx = x2 - x1; 
		int dy = y2 - y1;
		
		if(!((Math.abs(x2 - x1) == 0 && Math.abs(y2 - y1) !=0) || (Math.abs(x2 - x1) != 0 && dy ==0) || (Math.abs(x2 - x1) == Math.abs(y2 - y1))))
			return false;
		if(dx < 0) dx = -1;
		else if (dx == 0) dx = 0;
		else dx = 1;
		if(dy < 0) dy = -1;
		else if (dy == 0) dy = 0;
		else dy = 1;
		
		while(x1 != (x2-dx) || y1 != (y2-dy))
		{
			x1+=dx;
			y1+=dy;
			if(b.isEmpty(x1,y1) == false) return false;
		}
		return true;
	}
}
