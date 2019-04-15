/*
 * Classe de l'IA
 * 
 * @author	Adrien Soursou
 * @version	27/03/2019
 */

public class AI extends Player
{
	public AI(int bank, int color)
	{
		super(bank, color);
	}
	
	public String getCom()
	{
		char x1, x2, y1, y2;
		x1 = (char)(Math.random() * 8 + 97);
		x2 = (char)(Math.random() * 8 + 97);
		y1 = (char)(Math.random() * 8 + 49);
		y2 = (char)(Math.random() * 8 + 49);
		return "" + x1 + y1 + x2 + y2;
	}
	
	public String getPromotion()
	{
		int n = (int)(Math.random() * 4);
		if (n == 0) return "rook";
		else if (n == 1) return "knight";
		else if (n == 2) return "bishop";
		return "queen";
	}
}
