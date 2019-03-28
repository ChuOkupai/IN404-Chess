import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	27/03/2019
 */

public class Game
{
	private BufferedReader reader;
	private ChessBoard chessb;
	private int turn, maxSeconds, maxTurns;
	
	/**
	 * Constructeur d'objets de classe Game
	 * @param maxSeconds le nombre maximum de secondes par tour, infini si <= 0
	 * @param maxTurns le nombre maximum de tours, infini si <= 0
	 */
	public Game(int maxSeconds, int maxTurns)
	{
		this.chessb = new ChessBoard();
		this.reader = new BufferedReader(new InputStreamReader(System.in), 4);
		this.turn = 1;
		this.maxSeconds = (maxSeconds < 0) ? 0 : maxSeconds;
		this.maxTurns = (maxTurns < 0) ? 0 : maxTurns;
	}
	
	/**
	 * Interprète les commandes envoyées par un des joueurs
	 * @param s l'instruction venant du joueur
	 * @return int -1 si invalide, 0 si "exit", 1 si instruction de jeu
	 **/
	private int parseCom(String s)
	{
		int x1,x2,y1,y2;
		
		if(s.length() != 4) return -1;
		if(s.equals("exit") == true) return 0;
	
		x1 = s.charAt(0) - 'a';
		y1 = s.charAt(1) - '0' - 1;
		x2 = s.charAt(2) - 'a';
		y2 = s.charAt(3) - '0' - 1;
		return (chessb.doMove(x1,y1,x2,y2) == false) ? -1 : 1;
	}
	
	/**
	 * Cette méthode permet de récupérer des coordonnées aléatoires
	 * 
	 * @return la chaîne de charactères contenant le déplacement
	 */
	private String getRandomCoor()
	{
		char x1, x2, y1, y2;
		
		x1 = (char)(Math.random() * 8);
		while (x1 == (x2 = (char)(Math.random() * 8)));
		x1 += 97;
		x2 += 97;
		y1 = (char)(Math.random() * 8);
		while (y1 == (y2 = (char)(Math.random() * 8)));
		y1 += 49;
		y2 += 49;
		return (String)("" + x1 + y1 + x2 + y2);
	}
	
	/**
	 * S'occupe de la logique de jeu
	 * Bug connu: si l'utilisateur dépasse la ligne en écrivant sur le buffer, l'affichage ne fonctionne plus correctement
	 */
	public void run()
	{
		long	t0, t, dt;
		int		ret;
		String	buffer = "";
		
		while (maxTurns == 0 || turn <= maxTurns)
		{
			t0 = System.currentTimeMillis();
			dt = 0;
			t = 0;
			System.out.print("\033c"); // efface l'écran
			chessb.render();
			System.out.println();
			if (turn < 3) // Information pour les 2 premiers tours
				System.out.println(" Input format: [a-h][1-8][a-h][1-8] (ex " + this.getRandomCoor() + ")\n");
			do // Met à jour le rendu et l'entrée utilisateur une fois par seconde
			{
				if (t == dt || (t == dt && t == 0 && maxSeconds == 0))
				{
					if (t > 0)
					{
						System.out.print("\033[s"); // sauvegarde la position du curseur
						System.out.print("\033[4A\r"); // déplace le curseur de 4 lignes
					}
					System.out.println("  " + ((turn % 2 == 0) ? "Black" : "White") + "'s turn");
					System.out.println("  Turn: " + turn);
					System.out.print("  Time left: ");
					if (maxSeconds == 0)
						System.out.print("\033[38;2;55;255;55munlimited\033[0m");
					else
					{
						if (maxSeconds - t < 6) // Couleur rouge si inférieur à 5s
							System.out.print("\033[38;2;255;55;55m");
						System.out.print((maxSeconds - t) + "s         "); 
						if (maxSeconds - t < 6)
							System.out.print("\033[0m");
					}
					if (t == 0)
						System.out.print("\n\n  > ");
					else
						System.out.print("\033[u"); // restaure la position du curseur
					t++; // Préparation de la seconde suivante
				}
				try
				{
					if (reader.ready() == true) // si le fichier est à lire
					{
						buffer = reader.readLine();
						ret = parseCom(buffer);
						if (ret == 0)
							return;
						else if (ret == 1)
							break;
						else
							System.out.print("\033[1A\033[K  > ");
					}
					else
						dt = (System.currentTimeMillis() - t0) / 1000;
				}
				catch (IOException e)
				{
					e.printStackTrace(); // gestion d'erreurs
				}
			}	while (maxSeconds == 0 || dt < maxSeconds);
			turn++;
		}
	}
}