/*
 * Classe abstraite des joueurs
 * 
 * @author	Adrien Soursou
 * @version	27/03/2019
 */

public abstract class Player
{
	private int color;
	
	/**
	 * Constructeur de la classe abstraite Player
	 */
	Player(int color)
	{
		this.color = color;	
	}

	/**
	 * Cette méthode retourne la couleur du joueur
	 * @returnla 0 si noir, 1 si blanc
	 */
	public int getColor()
	{
		return this.color;
	}
}
