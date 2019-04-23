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
		int d = 2, b = 0, s = 0, t = 0;
		boolean p1 = true, p2 = false, err = false, help = false;
		
		for (int i = 0; i < args.length && err == false && help == false; i++)
		{
			if (args[i].equals("--help"))
				help = true;
			else if (args[i].charAt(0) == '-' && i + 1 < args.length)
			{
				if (args[i].equals("-d") || args[i].equals("--delay"))
				{
					if (args[i + 1].equals("instant"))
						d = 0;
					else if (args[i + 1].equals("slow"))
						d = 1;
					else if (args[i + 1].equals("normal"))
						d = 2;
					else if (args[i + 1].equals("fast"))
						d = 3;
					else err = true;
				}
				else if (args[i].equals("-b") || args[i].equals("--bank"))
					try { b = Integer.parseInt(args[i + 1]); }
					catch (NumberFormatException e) { err = true; }
				else if (args[i].equals("-s") || args[i].equals("--seconds"))
					try { s = Integer.parseInt(args[i + 1]); }
					catch (NumberFormatException e) { err = true; }
				else if (args[i].equals("-t") || args[i].equals("--turns"))
					try { t = Integer.parseInt(args[i + 1]); }
					catch (NumberFormatException e) { err = true; }
				else if (args[i].equals("-p1") || args[i].equals("--player1"))
				{
					if (args[i + 1].equals("ai"))
						p1 = false;
					else if (args[i + 1].equals("human"))
						p1 = true;
					else err = true;
				}
				else if (args[i].equals("-p2") || args[i].equals("--player2"))
				{
					if (args[i + 1].equals("ai"))
						p2 = false;
					else if (args[i + 1].equals("human"))
						p2 = true;
					else err = true;
				}
				else err = true;
				i++;
			}
			else err = true;
		}
		if (help == true)
		{
			System.out.println("Usage:\n\n      -d,    --delay  wait an amout of time after ai played (instant, slow, normal or fast)");
			System.out.println("      -b,     --bank  set a time bank for each player (0 is infinite)");
			System.out.println("      -s,  --seconds  set a time limit for each turn (0 is infinite)");
			System.out.println("      -t,    --turns  set a limit of turns (0 is infinite)");
			System.out.println("      -p1, --player1  choose if the player is controlled by an ai or not (ai or human)\n");
			System.out.println("      -p2, --player2  same as player 1\n");
			System.out.println("Default is: -d normal -b 0 -s 0 -t 0 -p1 human -p2 ai");
			return;
		}
		else if (err == true)
		{
			System.out.println("error: Invalid option\nTry './chess --help' for more information.");
			return;
		}
		Game game = new Game(d, b, s, t, p1, p2);
		game.run();
	}
}