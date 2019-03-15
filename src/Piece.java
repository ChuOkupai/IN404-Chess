/**
 * Classe Abstraite des différentes pièces
 *
 * @author	Mathis Dankou
 * @version	15/03/2019
 */

public abstract class Piece
{
	private int color;
	
	/**
	 * Cette méthode retourne la couleur de la piece
	 * @return	this.color la couleur de la piece ( 0: noir 1: blanc )
	 */
	public int getColor(){return this.color;}
	
	/**
	 * Cette méthode permet à la piece de se déplacer
	 */
	public abstract boolean movePossible();
	
}