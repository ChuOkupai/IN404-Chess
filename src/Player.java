/*
 * Classe abstraite des joueurs
 * 
 * @author	Adrien Soursou, Mathis Dankou
 * @version	02/04/2019
 */

public abstract class Player
{
	private int bank, color;
	
	/**
	 * Constructeur de la classe abstraite Player
	 * @param bank la valeur de la banque du joueur
	 * @param color la couleur du joueur
	 */
	Player(int bank, int color)
	{
		this.bank = (bank < 0) ? 0 : bank;
		this.color = color;
	}

	/**
	 * Cette méthode retourne la couleur du joueur
	 * @return 0 si noir, 1 si blanc
	 */
	public int getColor()
	{
		return this.color;
	}
	
	/**
	 * Récupère la valeur de la banque de temps
	 * @return la valeur de la banque de temps
	 */
	public int getBank()
	{
		return bank;
	}
	
	/**
	 * Retire un à la banque de temps, ne fait rien si la valeur est déjà à 0
	 */
	public void decreaseBank()
	{
		if (bank > 0)
			bank--;
	}
	
	/**
	 * Cette méthode retourne la commande tapée par le joueur
	 * Si c'est un humain, renvoie l'entrée utilisateur
	 * Si c'est une IA, renvoie une entrée aléatoire
	 * @return la commande du joueur
	 */
	public abstract String getCom();
	
	/**
	 * S'occupe de la promotion d'un pion
	 * @return la chaîne de la nouvelle pièce
	 */
	public abstract String getPromotion();
}
