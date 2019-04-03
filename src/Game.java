import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*

java.awt.Robot
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	27/03/2019
 */

public class Game
{
	private BufferedReader reader;
	private ChessBoard chessb;
	private int maxSeconds, maxTurns;
	
	/**
	 * Constructeur d'objets de classe Game
	 * @param maxSeconds le nombre maximum de secondes par tour, infini si <= 0
	 * @param maxTurns le nombre maximum de tours, infini si <= 0
	 */
	public Game(int maxSeconds, int maxTurns)
	{
		this.chessb = new ChessBoard();
		this.reader = new BufferedReader(new InputStreamReader(System.in));
		this.maxSeconds = (maxSeconds < 0) ? 0 : maxSeconds;
		this.maxTurns = (maxTurns < 0) ? 0 : maxTurns;
	}
	
	/**
	 * Crée des coordonnées aléatoires sous forme de chaîne de charactères
	 * 
	 * @return la chaîne de charactères contenant le déplacement
	 */
	private String randomCoor()
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
	 * Rendu de l'affichage qui contient les informations de la partie
	 * @param color la couleur du joueur
	 * @param turn le tour en cours
	 * @param frame le numéro de la frame à rendre (une par seconde)
	 */
	private void render(int color, int turn, int frame)
	{
		if (frame == 0) // Initialisation lors de la première frame
		{
			System.out.println("  " + ((color % 2 == 0) ? "Black" : "White") + "'s turn");
			System.out.print("  Turn: " + turn);
			if (maxTurns > 0)
				System.out.print(" of " + maxTurns);
			System.out.print("\n  Time left: ");
		}
		else // sauvegarde la position du curseur puis le déplace de 2 lignes
			System.out.print("\033[s\033[2A\r\033[13C\033[K");
		if (maxSeconds == 0) // affichage en vert si temps illimité
			System.out.print("\033[38;2;55;255;55munlimited\033[0m");
		else
		{
			int left = maxSeconds - frame;
			if (left < 6) // affichage en rouge si inférieur à 5s
				System.out.print("\033[38;2;255;55;55m");
			System.out.print(left + "s");
			if (left < 6)
				System.out.print("\033[0m");
		}
		System.out.print((frame == 0) ? "\n\n  > " : "\033[u");
	}
	
	/**
	 * Interprète les commandes envoyées par un des joueurs
	 * @param color la couleur du joueur
	 * @return int -1 si invalide, 0 si le buffer n'est pas prêt, 1 si "exit", 2 si instruction de jeu
	 **/
	private int parseCom(int color)
	{
		String s = "";
		try
		{
			if (reader.ready() == true) // si le fichier est à lire
				s = reader.readLine();
			else
				return 0;
		}	catch (IOException e) { e.printStackTrace(); }
		int x1,x2,y1,y2;
		
		if(s.length() != 4) return -1;
		if(s.equals("exit") == true) return 1;
	
		x1 = s.charAt(0) - 'a';
		y1 = s.charAt(1) - '0' - 1;
		x2 = s.charAt(2) - 'a';
		y2 = s.charAt(3) - '0' - 1;
		return (chessb.doMove(color,x1,y1,x2,y2) == false) ? -1 : 2;
	}
	
	/**
	 * S'occupe de la logique de jeu
	 * Bug connu: si l'utilisateur dépasse la ligne en écrivant sur le buffer, l'affichage ne fonctionne plus correctement
	 */
	public void run()
	{
		long	t0, dt;
		int		color = 1, turn = 1, frame, ret;
		
		while (maxTurns == 0 || turn <= maxTurns)
		{
			t0 = System.currentTimeMillis();
			dt = 0;
			frame = 0;
			System.out.print("\033c"); // efface l'écran
			chessb.render();
			System.out.println();
			if (turn == 1) // Information pour le premier tour
				System.out.println(" Input format: [a-h][1-8][a-h][1-8] (ex " + this.randomCoor() + ")\n");
			do // Met à jour le rendu et l'entrée utilisateur une fois par seconde
			{
				if (frame == dt) // si une nouvelle frame doit être rendue
					render(color, turn, frame++); // Affiche et prépare de la seconde suivante
				ret = parseCom(color);
				if (ret == 0);
				else if (ret == 1)
					return;
				else if (ret == 2)
					break;
				else // (ret == -1)
					System.out.print("\033[1A\r\033[4C\033[K"); // Reset du reader
				dt = (System.currentTimeMillis() - t0) / 1000;
			}	while (maxSeconds == 0 || dt < maxSeconds);
			if (color == 0) // Si c'était au tour du joueur 2
				turn++;
			color = 1 - color;
		}
	}
}