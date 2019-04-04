/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	27/03/2019
 */

public class Game
{
	private ChessBoard chessb;
	private int bank, color, maxSeconds, maxTurns, lines;
	private Player[] player;
	private String mode;
	
	/**
	 * Constructeur d'objets de classe Game
	 * @param bank le nombre maximum de secondes par joueur, infini si <= 0
	 * @param maxSeconds le nombre maximum de secondes par tour, infini si <= 0
	 * @param maxTurns le nombre maximum de tours, infini si <= 0
	 */
	public Game(int bank, int maxSeconds, int maxTurns)
	{
		chessb = new ChessBoard();
		color = 1;
		this.bank = (bank < 0) ? 0 : bank;
		this.maxSeconds = (maxSeconds < 0) ? 0 : maxSeconds;
		this.maxTurns = (maxTurns < 0) ? 0 : maxTurns;
		player = new Player[2];
		player[0] = new Human(this.bank, 0);
		player[1] = new Human(this.bank, 1);
		mode = (bank > 0 && maxSeconds > 0) ? "Blitz" : null;
		lines = 1;
		if (this.bank > 0) lines++;
		if (this.maxSeconds > 0) lines++;
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
	 * Rendu des secondes en couleur
	 * @param s la valeur en secondes à afficher
	 */
	private void renderSeconds(int s)
	{
		if (s < 6) // affichage en rouge
			System.out.print("\033[38;2;255;55;55m");
		else if (s < 11) // affichage en orange
			System.out.print("\033[38;2;255;128;55m");
		else if (s < 16) // affichage en jaune
			System.out.print("\033[38;2;255;255;55m");
		System.out.print(s + "s");
		if (s < 16)
			System.out.print("\033[0m");
		System.out.println();
	}
	
	/**
	 * Rendu de l'affichage qui contient les informations de la partie
	 * @param color la couleur du joueur
	 * @param turn le tour en cours
	 * @param frame le numéro de la frame à rendre (une par seconde)
	 * @param lines le nombre de lines de décalage (pour déplacer le curseur)
	 */
	private void render(int turn, int frame, int lines)
	{
		if (frame == 0) // Initialisation lors de la première frame
		{
			if (mode != null)
				System.out.println("  Mode: " + mode);
			System.out.println("  " + ((color % 2 == 0) ? "Black" : "White") + "'s turn");
			System.out.print("  Turn: " + turn);
			if (maxTurns > 0)
				System.out.print(" of " + maxTurns);
			System.out.println();
		}
		else // sauvegarde la position du curseur puis le déplace en fonction de lines
			System.out.print("\033[s\033[" + lines + "A");
		if (bank > 0)
		{
			System.out.print((frame == 0) ? "  Bank: " : "\r\033[8C\033[K");
			renderSeconds(player[color].getBank());
		}
		if (maxSeconds > 0)
		{
			System.out.print((frame == 0) ? "  Time left: " : "\r\033[13C\033[K");
			renderSeconds(maxSeconds - frame);
		}
		System.out.print((frame == 0) ? "\n  > " : "\033[u");
	}
	
	/**
	 * Interprète les commandes envoyées par un des joueurs
	 * @return int -1 si invalide, 0 si le buffer n'est pas prêt, 1 si "exit", 2 si instruction de jeu
	 **/
	private int parseCom(String com)
	{
		if (com == null)
			return 0;
		int x1,x2,y1,y2;
		
		if(com.equals("exit") == true) return 1;
		
		x1 = com.charAt(0) - 'a';
		y1 = com.charAt(1) - '0' - 1;
		x2 = com.charAt(2) - 'a';
		y2 = com.charAt(3) - '0' - 1;
		return (chessb.doMove(color,x1,y1,x2,y2) == false) ? -1 : 2;
	}
	
	/**
	 * S'occupe de la logique de jeu
	 * Bug connu: si l'utilisateur dépasse la ligne en écrivant sur le buffer, l'affichage ne fonctionne plus correctement
	 */
	public void run()//Rajouter des paramètres
	{ 
		long	t0, dt;
		int		turn = 1, frame, ret;
		
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
				{
					render(turn, frame++, lines); // Affiche et prépare de la seconde suivante
					if (dt >= maxSeconds)
						player[color].decreaseBank();
				}
				ret = parseCom(player[color].getCom());
				if (ret == 0);
				else if (ret == 1)
					return;
				else if (ret == 2)
					break;
				else // (ret == -1)
					System.out.print("\r\033[K\033[1A\r\033[4C\033[K"); // Reset du reader
				dt = (System.currentTimeMillis() - t0) / 1000;
			}	while ((maxSeconds == 0 || dt < maxSeconds) || (bank != 0 && player[color].getBank() != 0));
			if (bank != 0 && player[color].getBank() == 0)
				return;
			if (color == 0) // Si c'était au tour du joueur 2
				turn++;
			color = 1 - color;
		}
	}
}