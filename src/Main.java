/*
 * Main de l'application
 *
 * @author	Adrien Soursou
 * @version	27/03/2019
 */

enum Main
{
	ENVIRONNEMENT;
	
	/**
	 * Méthode principale du programme.
	 * @param args les arguments de ligne de commande
	 */
	public void run(String[] args)
	{
		Game game = new Game();
		
		game.run();
	}
	// A décommenter pour tester des déplacements (ne pas oublier de commenter l'autre fonction run)
	/*public void run(String[] args)
	{
		ChessBoard board = new ChessBoard();
		
		board.render();
		// Test de mouvements
		System.out.print("King: ");
		board.checkMove(4, 7, 4, 6);
	}*/
	
	/**
	 * Point d'entrée du programme.
	 * @param args les arguments de ligne de commande
	 */
	public static void main(String[] args)
	{
		ENVIRONNEMENT.run(args);
	}
}