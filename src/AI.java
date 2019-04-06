/*
 * Classe de l'IA
 * 
 * @author	Adrien Soursou
 * @version	27/03/2019
 */

public class AI extends Player
{
	public AI()
	{
		super(0, 0);
	}

	public AI(int bank, int color)
	{
		super(bank, color);
	}
	
	public String getCom()
	{
		char x1, x2, y1, y2;
		x1 = (char)(Math.random() * 8 + 97);
		while (x1 == (x2 = (char)(Math.random() * 8 + 97)));
		y1 = (char)(Math.random() * 8 + 49);
		while (y1 == (y2 = (char)(Math.random() * 8 + 49)));
		System.out.println();
		return "" + x1 + y1 + x2 + y2;
	}
}
