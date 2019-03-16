/**
 * Classe Abstraite des différentes pièces
 *
 * @author	Mathis Dankou
 * @version	15/03/2019
 */

public abstract class Piece
{
	private int color;
	private String sprite;
	private String spriteFill;
	
	/**
	 * Constructeur de la classe abstraite piece
	 */
	public Piece(int color, String sprite, String spriteFill)
	{
		this.color = color;
		this.sprite = sprite;
		this.spriteFill = spriteFill;
	}
	
	/**
	 * Cette méthode retourne la couleur de la piece
	 * @return	this.color la couleur de la piece ( 0: noir 1: blanc )
	 */
	public int getColor(){return this.color;}
	
	/**
	 * Cette méthode retourne le sprite de la piece
	 * @return	this.sprite le sprite de la piece
	 */
	public String getSprite(){return this.sprite;}
	
	/**
	 * Cette méthode retourne le sprite en mode remplie de la piece
	 * @return	this.spriteFill le sprite remplie de la piece
	 */
	public String getSpriteFill(){return this.spriteFill;}
	
	/**
	 * Cette méthode indique si la piece peut se déplacer
	 * @return	true ou false la booolean indiquant si le déplacment est possible
	 */
	public abstract boolean movePossible(int x1, int y1, int x2, int y2);
	
}