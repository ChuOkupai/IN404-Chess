/*
 * Classe du Fou
 *
 * @author	Franck Naze
 * @version	16/03/2019
 */

public class Bishop extends Piece
{
	/**
	 * Constructeur du fou
	 */
	public Bishop(int color)
	{
		// Affectation des sprites
		super(color,"♗","♝");
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
		if(x1==x2||y1==y2) return false;
		if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0)
			return false; 
		else if (x1 > 7 || x2 > 7 || y1 > 7 || y2 > 7)
			return false; 
		if((Math.abs(x2 - x1)== Math.abs(y2 - y1)))
		{
			while(x1 != x2 && y1 != y2)
			{
				if(x1<x2)
				{
					if(y1<y2)// en haut à droite
					{
						x1+=1; y1+=1;
						if((x1==x2)&&(y1==y2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPiece(x2, y2).getColor() == this.getColor()) return false;
						else return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
						
					}
					else// en bas à droite
					{
						
						x1+=1; y1-=1;
						if((x1==x2)&&(y1==y2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPiece(x2, y2).getColor() == this.getColor()) return false;
						else return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
				}
				else
				{
					if(y1<y2)//en haut à gauche
					{
						x1-=1; y1+=1;
						if((x1==x2)&&(y1==y2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPiece(x2, y2).getColor() == this.getColor()) return false;
						else return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
					else//en bas à gauche
					{
						x1-=1; y1-=1;
						if((x1==x2)&&(y1==y2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPiece(x2, y2).getColor() == this.getColor()) return false;
						else return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
				}	
			}
		}
		return false;
	}
}
