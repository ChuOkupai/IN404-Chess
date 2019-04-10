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
		// bank, maxSeconds, maxTurns
		Game game = new Game(120, 0, 0);
		game.run();
	}
	
	/**
	 * Point d'entrée du programme.
	 * @param args les arguments de ligne de commande
	 */
	public static void main(String[] args)
	{
		ENVIRONNEMENT.run(args);
	}
}
