/**
 * Classe représentant l'historique des coups
 *
 * @author	Franck Naze
 * @version	03/04/2019
 */
public class Event
{
	private int x1,y1,x2,y2;
	private Piece EatenPiece;
	private boolean check;
	
	/**
	* Constructeur de l'historique des coups avec 
	* la case de départ,d'arrivée,et la pièce mangée
	* @param	x1 la position x de départ de la pièce
	* @param	y1 la position y de départ de la pièce
	* @param	x2 la position x d'arrivé de la pièce
	* @param	y2 la position y d'arrivé de la pièce
	* @param	check si le roi était en échec
	* @param	EatenPiece la pièce mangée
	*/
	public Event(int x1,int y1,int x2,int y2,Piece EatenPiece,boolean check)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.EatenPiece=EatenPiece;
		this.check = check;
	}
	
	/**
	 * Cette méthode retourne l'abscisse de la case de départ
	 * @return	this.x1 l'abscisse de la case de départ
	 */
	public int getStartingx()
	{
		return this.x1;
	}
	
	/**
	 * Cette méthode retourne l'ordonnée de la case de départ
	 * @return	this.y1 l'ordonnée de la case de départ
	 */
	public int getStartingy()
	{
		return this.y1;
	}
	
	/**
	 * Cette méthode retourne l'abscisse de la case d'arrivée
	 * @return	this.x2 l'abscisse de la case d'arrivée
	 */
	public int getFinalx()
	{
		return this.x2;
	}
	
	/**
	 * Cette méthode retourne l'ordonnée de la case d'arrivée
	 * @return	this.y2 l'ordonnée de la case d'arrivée
	 */
	public int getFinaly()
	{
		return this.y2;
	}
	
	/**
	* Permet de récupérer le type de Piece mangée
	* @return le type de pièce mangée 
	*/
	Piece getEatenPiece()
	{
		return this.EatenPiece;
	}
	
	/**
	* Permet de récupérer la valeur d'échec
	* @return le type de pièce mangée 
	*/
	boolean getCheck()
	{
		return this.check;
	}
}
