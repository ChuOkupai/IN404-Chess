/*
 * Main de l'application
 *
 * @author	Adrien Soursou
 * @version	27/03/2019
 */

public class Main
{
	/**
	 * Méthode principale du programme.
	 * Attention: il est impossible de faire jouer 2 IAs en même temps
	 * Les arguments 
	 * @param args les arguments de ligne de commande
	 */
	public static void main(String[] args)
	{
		int b = 0, s = 0, t = 0;
		boolean j1 = true, j2 = false, err = false, help = false;
		
		for (int i = 0; i < args.length && err == false && help == false; i++)
		{
			if (args[i].equals("--help"))
				help = true;
			else if (args[i].charAt(0) == '-' && i + 1 < args.length)
			{
				if (args[i].equals("-b"))
					b = Integer.parseInt(args[i + 1]);
				else if (args[i].equals("-s"))
					s = Integer.parseInt(args[i + 1]);
				else if (args[i].equals("-t"))
					t = Integer.parseInt(args[i + 1]);
				else if (args[i].equals("-j1"))
				{
					if (args[i + 1].equals("ai"))
						j1 = false;
					else if (args[i + 1].equals("human"))
						j1 = true;
					else err = true;
				}
				else if (args[i].equals("-j2"))
				{
					if (args[i + 1].equals("ai"))
						j2 = false;
					else if (args[i + 1].equals("human"))
						j2 = true;
					else err = true;
				}
				else err = true;
				i++;
			}
			else err = true;
		}
		if (help == true)
		{
			System.out.println("Usage:\n\n      -b (integer), set a time bank for each player");
			System.out.println("      -s (integer), set a time limit for each turn");
			System.out.println("      -t (integer), set a limit of turns");
			System.out.println("      -[j1|j2] (ai|human), choose if a player is controlled by an artificial intelligence\n");
			System.out.println("Default is: -b 0 -s 0 -t 0 -j1 human -j2 ai");
			return;
		}
		else if (err == true)
		{
			System.out.println("error: Invalid option\nTry './chess --help' for more information.");
			return;
		}
		Game game = new Game(b, s, t, j1, j2);
		game.run();
	}
}