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
		Game game = new Game(10, 0);
	
		
		game.run();
	}
	// A décommenter pour tester des déplacements (ne pas oublier de commenter l'autre fonction run)
	/*public void run(String[] args)
	{
		ChessBoard board = new ChessBoard();
		
		board.render();
		// Test de mouvements
		System.out.print("Pion: ");
		//board.doMove(3,1,3,2);
		board.checkMove(3, 2, 3, 3);
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
