/**
 * Main de l'application.
 *
 * @author	Adrien Soursou
 * @version	15/03/2019
 */
enum Main
{
	ENVIRONNEMENT;
	
	/*
	 * Méthode principale du programme.
	 * @param args les arguments de ligne de commande
	 */
	public void run(String[] args)
	{
		ChessBoard board = new ChessBoard();
		
		board.render();
	}
	
	/*
	 * Point d'entrée du programme.
	 * @param args les arguments de ligne de commande
	 */
	public static void main(String[] args)
	{
		ENVIRONNEMENT.run(args);
	}
}