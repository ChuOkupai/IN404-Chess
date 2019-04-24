/*
 * Classe de l'IA
 * 
 * @author	Adrien Soursou
 * @version	27/03/2019
 */

public class AI extends Player
{
	/**
	 * Constructeur de l'IA
	 * @param bank la valeur de la banque du joueur
	 * @param color la couleur du joueur
	 */
	public AI(int bank, int color)
	{
		super(bank, color);
	}
	
	/**
	 * Cette méthode retourne la commande tapée par le joueur
	 * Si c'est un humain, renvoie l'entrée utilisateur
	 * Si c'est une IA, renvoie une entrée aléatoire
	 * @return la commande du joueur
	 */
	public String getCom()
	{
		char x1, x2, y1, y2;
		x1 = (char)(Math.random() * 8 + 97);
		x2 = (char)(Math.random() * 8 + 97);
		y1 = (char)(Math.random() * 8 + 49);
		y2 = (char)(Math.random() * 8 + 49);
		return "" + x1 + y1 + x2 + y2;
	}
	
	/**
	 * S'occupe de la promotion d'un pion
	 * @return la chaîne de la nouvelle pièce
	 */
	public String getPromotion()
	{
		int n = (int)(Math.random() * 4);
		if (n == 0) return "rook";
		else if (n == 1) return "knight";
		else if (n == 2) return "bishop";
		return "queen";
	}
}
