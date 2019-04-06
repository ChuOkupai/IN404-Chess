/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	27/03/2019
 */

public class Game
{
	private ChessBoard chessb;
	private int bank, color, maxSeconds, maxTurns;
	private Player[] player;
	private AI ai;
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
		ai = new AI();
		mode = (bank > 0 && maxSeconds > 0) ? "Blitz" : null;
	}
	
	/**
	 * Rendu des secondes en couleur
	 * @param s la valeur en secondes à afficher
	 */
	private void renderSeconds(int s)
	{
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
			System.out.print("\033[3;31H" + ai.getCom());
		System.out.print("\033[5H");
		if (frame == 0) // Initialisation lors de la première frame
		{
			if (mode != null) System.out.println("\033[25CMode: " + mode);
			System.out.println("\033[25C" + ((color % 2 == 0) ? "Black" : "White") + "'s turn");
			System.out.print("\033[25CTurn: " + turn);
			if (maxTurns > 0) System.out.print(" of " + maxTurns);
			System.out.println();
		}
		else System.out.print("\033[" + ((mode != null) ? 3 : 2) + "B");
		if (bank > 0)
		{
			System.out.print((frame == 0) ? "\033[25CBank: " : "\033[31C\033[K");
			renderSeconds(player[color].getBank());
		}
		if (maxSeconds > 0)
		{
			System.out.print((frame == 0) ? "\033[25CTime left: " : "\033[36C\033[K");
			renderSeconds(maxSeconds - frame);
		}
		System.out.print((turn == 1 && frame == 0 && color == 1) ? "\033[12H  > " : "\033[u");
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
		// gère la promotion ma gueule si vrai
	}
	
	/**
	 * S'occupe de la logique du jeu, en faisant jouer les adversaires tour à tour
	 */
	public void run()//Rajouter des paramètres
	{ 
		long	t0, dt;
		int		turn = 1, frame, ret;
		
		System.out.print("\033c"); // efface l'écran
		while (maxTurns == 0 || turn <= maxTurns)
		{
			t0 = System.currentTimeMillis();
			dt = 0;
			frame = 0;
			System.out.print("\033[s\033[1;1H"); // sauvegarde et déplace le curseur en haut de l'écran
			chessb.render();
			if (turn == 1) // Information pour le premier tour
				System.out.print("\033[2;25HInput format: [a-h][1-8][a-h][1-8]\n\033[24Cex: > ");
			System.out.print("\033[u"); // restaure la position du curseur
			do
			{
				if (frame == dt) // si une nouvelle frame doit être rendue
				{
					// Met à jour le rendu et l'entrée utilisateur une fois par seconde
					render(turn, frame++); // Affiche et prépare de la seconde suivante
					if (dt >= maxSeconds)
					{
						if (bank != 0 && player[color].getBank() == 0)
						{
							System.out.println();
							return;
						}
						player[color].decreaseBank();
					}
				}
				ret = parseCom(player[color].getCom());
				if (ret == 1) return;
				else if (ret == 2 || ret == -1)
				{
					System.out.print("\r\033[K\033[1A\r\033[4C\033[K"); // Reset du reader
					if (ret == 2) break;
				}
				dt = (System.currentTimeMillis() - t0) / 1000;
			}	while ((maxSeconds == 0 || dt < maxSeconds) || bank != 0);
			if (color == 0) turn++;// Si c'était au tour du joueur 2
			color = 1 - color;
		}
	}
}