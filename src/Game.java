/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	22/04/2019
 */

public class Game
{
	private ChessBoard chessb;
	private int bank, color, turn, maxSeconds, maxTurns;
	private boolean j1, j2, check, checkmate;
	private Player[] player;
	private String mode;
	
	/**
	 * Constructeur d'objets de classe Game
	 * @param bank le nombre maximum de secondes par joueur, infini si <= 0
	 * @param maxSeconds le nombre maximum de secondes par tour, infini si <= 0
	 * @param maxTurns le nombre maximum de tours, infini si <= 0
	 * @param j1 le joueur 1, true indique un joueur humain et false une IA
	 * @param j2 le joueur 2
	 */
	public Game(int bank, int maxSeconds, int maxTurns, boolean j1, boolean j2)
	{
		chessb = new ChessBoard();
		player = new Player[2];
		player[0] = (j2) ? new Human(this.bank, 0) : new AI(this.bank, 0);
		player[1] = (j1) ? new Human(this.bank, 1) : new AI(this.bank, 1);
		this.j1 = j1;
		this.j2 = j2;
		this.maxTurns = (maxTurns < 0) ? 0 : maxTurns;
		if (! (j1 || j2))
		{
			mode = "\033[38;2;84;194;221mAI battle\033[0m";
			this.bank = 0;
			this.maxSeconds = 0;
			return;
		}
		this.bank = (bank < 0) ? 0 : bank;
		this.maxSeconds = (maxSeconds < 0) ? 0 : maxSeconds;
		mode = (bank > 0 || maxSeconds > 0) ? "Blitz" : "Normal";
	}
	
	/**
	 * Crée des coordonnées aléatoires sous forme de chaîne de charactères
	 * 
	 * @return la chaîne de charactères contenant le déplacement
	 */
	private String randomCoor()
	{
		char x1, x2, y1, y2;
		x1 = (char)(Math.random() * 8 + 97);
		while (x1 == (x2 = (char)(Math.random() * 8 + 97)));
		y1 = (char)(Math.random() * 8 + 49);
		while (y1 == (y2 = (char)(Math.random() * 8 + 49)));
		return "" + x1 + y1 + x2 + y2;
	}
	
	/**
	 * Rendu des secondes en couleur
	 * @param s la valeur en secondes à afficher
	 */
	private void renderSeconds(int s)
	{
		int m = s / 60, h = m / 60;
		m %= 60; s %= 60;
		if (h + m != 0)
		{
			if (h > 0)
				System.out.format("%02dh ", h);
			System.out.format("%02dm %02ds\n", m, s);
			return;
		}
		if (s < 6) System.out.print("\033[38;2;255;55;55m"); // rouge
		else if (s < 11) System.out.print("\033[38;2;255;128;55m"); // orange
		else if (s < 16) System.out.print("\033[38;2;255;255;30m"); // jaune
		System.out.println(s + "s\033[0m");
	}
	
	/**
	 * Affiche un ou deux messages sur l'écran
	 * @return int de valeur 1 pour casser une boucle dans la méthode run
	 **/
	private int printInfo(String s1, String s2)
	{
		System.out.print("\033[3;27H\033[K");
		if (s1 != null)
			System.out.print(s1);
		System.out.print("\033[4;27H\033[K");
		if (s2 != null)
			System.out.print(s2);
		return 1;
	}
	
	/**
	 * Rendu de l'affichage qui contient les informations de la partie
	 * @param frame le numéro de la frame à rendre (une par seconde)
	 */
	private void render(int frame)
	{
		System.out.print("\033[s");
		if (frame == 0)
		{
			if (turn == 1)
			{
				if (color == 1)
				{
					System.out.print("\033[6;28HMode: " + mode);
					System.out.print("\n\033[32C's turn\n\033[27CTurn: ");
					if (bank > 0)
						System.out.print("\n\033[27CBank: ");
					if (maxSeconds > 0)
						System.out.print("\n\033[27CTime left: ");
					System.out.print("\033[13H    > \033[s");
				}
				// Information pour le premier tour
				printInfo("Input format: [a-h][1-8][a-h][1-8]", "ex: > ");
			}
			System.out.println("\033[7;28H" + ((color % 2 == 0) ? "Black" : "White"));
			System.out.print("\033[33C" + turn);
			if (maxTurns > 0) System.out.print(" of " + maxTurns);
			System.out.println();
		}
		else System.out.print("\033[9H");
		if (bank > 0)
		{
			System.out.print("\033[33C\033[K");
			renderSeconds(player[color].getBank());
		}
		if (maxSeconds > 0)
		{
			System.out.print("\033[38C\033[K");
			renderSeconds(maxSeconds - frame);
		}
		if (turn == 1 && frame % 5 == 0) // coordonnées aléatoires toutes les 5s
			System.out.print("\033[4;33H" + randomCoor());
		System.out.print("\033[u");
	}
	
	/**
	 * Attend que le joueur choisisse une pièce puis la remplace
	 **/
	private void promote(int x, int y)
	{
		String buf;
		System.out.print("\033[s\033[1;1H");
		chessb.render();
		printInfo("Choose a promotion:", "rook, knight, bishop or queen\033[u");
		do
		{
			while ((buf = player[color].getPromotion()) == null);
			if (buf.equals("rook")) chessb.promote(new Rook(color), x, y);
			else if (buf.equals("knight")) chessb.promote(new Knight(color), x, y);
			else if (buf.equals("bishop")) chessb.promote(new Bishop(color), x, y);
			else if (buf.equals("queen")) chessb.promote(new Queen(color), x, y);
			else buf = null;
		}	while (buf == null);
		printInfo(null, "\033[u");
	}
	
	/**
	 * Simule un tour de l'IA avec un faux délai
	 **/
	private void aiThinking()
	{
		long t0;
		double dt, tmax;
		t0 = System.currentTimeMillis();
		dt = 0;
		tmax = 0.2 + Math.random() * 0.2;
		while (dt < tmax)
			dt = (System.currentTimeMillis() - t0) / 1000.0;
	}
	
	/**
	 * Interprète les commandes envoyées par un des joueurs
	 * @return int -1 si invalide, 0 si le buffer n'est pas prêt, 1 si "exit", 2 si instruction de jeu seulement, 3 si undo
	 **/
	private int parseCom(String com)
	{
		if (com == null)
			return 0;
		int x1,x2,y1,y2;
		
		if (com.equals("undo") == true)
		{
			if (turn < 2)
				return -1;
			chessb.undo();
			chessb.undo();
			return 3;
		}
		else if (com.equals("exit") == true) return 1;
		
		x1 = com.charAt(0) - 'a';
		y1 = com.charAt(1) - '0' - 1;
		x2 = com.charAt(2) - 'a';
		y2 = com.charAt(3) - '0' - 1;
		
		if (chessb.doMove(color,x1,y1,x2,y2) == true)
		{
			if (! (j1 || j2)) aiThinking(); // délai si 2 IAs jouent
			if (chessb.isPawn(x2, y2) == true && ((color == 0 && y2 == 0) || (color == 1 && y2 == 7)))
			{
				if (! (j1 || j2)) aiThinking(); // délai
				promote(x2, y2);
			}
			return 2;
		}
		return -1;
	}
	
	/**
	 * S'occupe de la logique du jeu, en faisant jouer les adversaires tour à tour
	 */
	public void run()
	{
		long	t0, dt;
		int		frame, ret = 0;
		color = 1; turn = 1;
		System.out.print("\033c"); // efface l'écran
		while (ret != 1)
		{
			t0 = System.currentTimeMillis();
			dt = 0;
			frame = 0;
			ret = 0;
			check = chessb.isCheck(color);
			checkmate = chessb.isCheckmate(color);
			System.out.print("\033[s\033[1;1H"); // sauvegarde et déplace le curseur en haut de l'écran
			chessb.render();
			if (turn > 1) printInfo(null, null);
			if (maxTurns > 0 && turn > maxTurns)
				ret = printInfo("No more turns left!", null);
			if (checkmate == true)
			{
				System.out.print("\033[3;27H\033[K" + ((color == 0) ? "Black" : "White") + "'s king can not escape check");
				ret = 1;
			}
			else if (check == true)
				printInfo("\033[38;2;255;55;55m⚠  CHECK\033[0m", "Your king is in danger!");
			System.out.print("\033[u"); // restaure la position du curseur
			while (ret < 1)
			{
				if (frame == dt) // si une nouvelle frame doit être rendue
				{
					// Met à jour le rendu et l'entrée utilisateur une fois par seconde
					render(frame++); // Affiche et prépare de la seconde suivante
					if (dt >= maxSeconds)
					{
						if ((bank == 0 && maxSeconds > 0) || (bank != 0 && player[color].getBank() == 0))
						{
							ret = printInfo("No more time left!", null);
							break;
						}
						player[color].decreaseBank();
					}
				}
				ret = parseCom(player[color].getCom());
				if (ret == 1)
					printInfo(((color == 0) ? "Black" : "White") + "'s have conceded!", null);
				else if (ret == 3)
					turn--;
				dt = (System.currentTimeMillis() - t0) / 1000;
			}
			if (ret != 3)
			{
				if (color == 0) turn++; // Si c'était au tour du joueur 2
				color = 1 - color;
			}
		}
		System.out.println("\033[15H");
	}
}