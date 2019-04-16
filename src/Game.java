/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	14/04/2019
 */

public class Game
{
	private ChessBoard chessb;
	private int bank, color, turn, maxSeconds, maxTurns;
	private boolean j1, j2;
	private Player[] player;
	private String mode;
	
	/**
	 * Constructeur d'objets de classe Game
	 * @param bank le nombre maximum de secondes par joueur, infini si <= 0
	 * @param maxSeconds le nombre maximum de secondes par tour, infini si <= 0
	 * @param maxTurns le nombre maximum de tours, infini si <= 0
	 * @param le joueur 1, true indique un joueur humain et false une IA
	 * @param le joueur 2
	 */
	public Game(int bank, int maxSeconds, int maxTurns, boolean j1, boolean j2)
	{
		chessb = new ChessBoard();
		color = 1;
		this.bank = (bank < 0) ? 0 : bank;
		this.turn = 1;
		this.maxSeconds = (maxSeconds < 0) ? 0 : maxSeconds;
		this.maxTurns = (maxTurns < 0) ? 0 : maxTurns;
		player = new Player[2];
		player[0] = (j2) ? new Human(this.bank, 0) : new AI(this.bank, 0);
		player[1] = (j1) ? new Human(this.bank, 1) : new AI(this.bank, 1);
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
		int m = s / 60;
		s %= 60;
		if (m > 0)
		{
			System.out.format("%dm %02ds\n", m, s);
			return;
		}
		if (s < 6) System.out.print("\033[38;2;255;55;55m"); // rouge
		else if (s < 11) System.out.print("\033[38;2;255;128;55m"); // orange
		else if (s < 16) System.out.print("\033[38;2;255;255;30m"); // jaune
		System.out.println(s + "s\033[0m");
	}
	
	/**
	 * Rendu de l'affichage qui contient les informations de la partie
	 * @param turn le tour en cours
	 * @param frame le numéro de la frame à rendre (une par seconde)
	 */
	private void render(int turn, int frame)
	{
		System.out.print("\033[s");
		if (turn == 1 && frame % 5 == 0) // coordonnées random
			System.out.print("\033[4;33H" + randomCoor());
		System.out.print("\033[6H");
		if (frame == 0) // Initialisation lors de la première frame
		{
			if (mode != null) System.out.println("\033[27CMode: " + mode);
			System.out.println("\033[27C" + ((color % 2 == 0) ? "Black" : "White") + "'s turn");
			System.out.print("\033[27CTurn: " + turn);
			if (maxTurns > 0) System.out.print(" of " + maxTurns);
			System.out.println();
		}
		else System.out.print("\033[" + ((mode != null) ? 3 : 2) + "B");
		if (bank > 0)
		{
			System.out.print((frame == 0) ? "\033[27CBank: " : "\033[33C\033[K");
			renderSeconds(player[color].getBank());
		}
		if (maxSeconds > 0)
		{
			System.out.print((frame == 0) ? "\033[27CTime left: " : "\033[38C\033[K");
			renderSeconds(maxSeconds - frame);
		}
		System.out.print((turn == 1 && frame == 0 && color == 1) ? "\033[13H    > " : "\033[u");
	}
	
	/**
	 * Attend que le joueur choisisse une pièce puis la remplace
	 **/
	private void promote(int x, int y)
	{
		String buf = null;
		System.out.print("\033[s\033[1;1H");
		chessb.render();
		System.out.print("\033[15H  Choose a promotion: rook, knight, bishop or queen\033[u");
		while (buf == null)
		{
			buf = player[color].getCom();
			if (buf == null)
				continue;
			if (buf.equals("rook")) chessb.promote(new Rook(color), x, y);
			else if (buf.equals("knight")) chessb.promote(new Knight(color), x, y);
			else if (buf.equals("bishop")) chessb.promote(new Bishop(color), x, y);
			else if (buf.equals("queen")) chessb.promote(new Queen(color), x, y);
			else
				buf = null;
		}
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
			if (chessb.isPawn(x2, y2) == true && ((color == 0 && y2 == 0) || (color == 1 && y2 == 7)))
				promote(x2, y2);
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
		int		frame, ret;
		
		ret = 0;
		System.out.print("\033c"); // efface l'écran
		while (ret != 1)
		{
			t0 = System.currentTimeMillis();
			dt = 0;
			frame = 0;
			System.out.print("\033[s\033[1;1H"); // sauvegarde et déplace le curseur en haut de l'écran
			chessb.render();	
			if (turn == 1) // Information pour le premier tour
				System.out.print("\033[3;27HInput format: [a-h][1-8][a-h][1-8]\n\033[26Cex: > ");
			System.out.print("\033[u"); // restaure la position du curseur
			if (maxTurns > 0 && turn > maxTurns)
			{
				System.out.println("\033[13;7H\033[JNo more turns left!");
				break;
			}
			do
			{
				if (frame == dt) // si une nouvelle frame doit être rendue
				{
					// Met à jour le rendu et l'entrée utilisateur une fois par seconde
					render(turn, frame++); // Affiche et prépare de la seconde suivante
					if (dt >= maxSeconds)
					{
						if ((bank == 0 && maxSeconds > 0) || (bank != 0 && player[color].getBank() == 0))
						{
							System.out.println("\033[13;7H\033[JNo more time left!");
							ret = 1;
							break;
						}
						player[color].decreaseBank();
					}
				}
				ret = parseCom(player[color].getCom());
				if (ret == 1)
				{
					System.out.print((color == 0) ? "Black" : "White");
					System.out.println("'s have conceded");
				}
				else if (ret == 3)
				{
					turn -= 2;
					color = 1 - color;
				}
				dt = (System.currentTimeMillis() - t0) / 1000;
			}	while (ret < 1);
			if (color == 0) turn++; // Si c'était au tour du joueur 2
			color = 1 - color;
		}
		System.out.println();
	}
}