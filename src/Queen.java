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
		
		dx=Math.abs(x2 - x1);
		dy=Math.abs(y2 - y1);
		
		while(x1 != x2 || y1 != y2)
		{
			if((Math.abs(x2 - x1)== Math.abs(y2 - y1)))
			{
				while(x1 != x2 && y1 != y2)
				{
					if(x1<x2)
					{
						if(y1<y2)//Déplacement en Haut/Droite
						{
							x1+=1; y1+=1;
							if((x1==x2)&&(y1==y2))
							{
							if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
							else return true;
							}
							if(b.isEmpty(x1,y1)==false)return false;			
						}
						else//Déplacement en Bas/Droite
						{
						
							x1+=1; y1-=1;
							if((x1==x2)&&(y1==y2))
							{
							if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
							else return true;
							}
							if(b.isEmpty(x1,y1)==false)return false;
						}
					}
					else
					{
						if(y1<y2)//Déplacement en Haut/Gauche
						{
							x1-=1; y1+=1;
							if((x1==x2)&&(y1==y2))
							{
							if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
							else return true;
							}
							if(b.isEmpty(x1,y1)==false)return false;
						}
						else//Déplacement en Bas/Gauche
						{
							x1-=1; y1-=1;
							if((x1==x2)&&(y1==y2))
							{
							if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
							else return true;
							}
							if(b.isEmpty(x1,y1)==false)return false;
						}
					}	
				}
			}
			
			if(dx!=0)
			{
				while(x1 != x2)
				{
				    if(x1<x2)//Vers la droite
				    {
						x1+=1;
						if((x1==x2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
						else
						return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
					if(x1>x2)//Vers la gauche
					{
						x1-=1;
						if(x1==x2)
						{
						if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
						else
						return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
				  }
			}
			if(dy!=0)
			{
				while(y1 != y2)
				{
					if(y1<y2)//Vers le haut
					{
					 y1+=1;
						if((y1==y2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
						else
						return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
					else//Vers le Bas
						y1-=1;
						if((y1==y2))
						{
						if (b.isEmpty(x2, y2) == false && b.getPieceColor(x2, y2) == this.getColor()) return false;
						else
					    return true;
					    }
					    if(b.isEmpty(x1,y1)==false)return false;
					}
				}
			}
			
		return false;
	}
}	
			
		
	
