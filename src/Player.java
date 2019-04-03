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
	 * @return la valeur de bank
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
	 * @return la commande joueur
	 */
	public abstract String getCom();
}
