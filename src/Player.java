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
		this.bank = bank;
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
	 * Cette méthode retourne la commande tapée par l'utilisateur
	 * @return 
	 */
	public abstract String getCom();
}
