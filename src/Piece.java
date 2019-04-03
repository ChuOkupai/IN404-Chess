/*
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
	public int getColor()
	{
		return this.color;
	}
	
	/**
	 * Cette méthode retourne le sprite de la piece
	 * @return	this.sprite le sprite de la piece
	 */
	public String getSprite()
	{
		return this.sprite;
	}
	
	/**
	 * Cette méthode retourne le sprite en mode remplie de la piece
	 * @return	this.spriteFill le sprite remplie de la piece
	 */
	public String getSpriteFill()
	{
		return this.spriteFill;
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
	public abstract boolean movePossible(ChessBoard b, int x1, int y1, int x2, int y2);
}