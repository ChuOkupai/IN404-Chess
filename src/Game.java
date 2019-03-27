import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Classe au coeur de la logique du jeu
 *
 * @author	Mathis Dankou, Adrien Soursou
 * @version	15/03/2019
 */

public class Game
{
	private BufferedReader reader;
	private ChessBoard chessb;
	private int turn, max;
	
	/**
	 * Constructeur d'objets de classe Game
	 */
	public Game()
	{
		this.chessb = new ChessBoard();
		this.reader = new BufferedReader(new InputStreamReader(System.in), 4);
		this.turn = 1;
		this.max = 30;
	}
	
	/**
	 * Interprète les commande envoyé par un des joueurs  
	 **/
	private void parseCom(String s)
	{
		
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
		String	buffer = "";
		
		while (turn <= 4) // debug test de 4 tours, à remplacer par les conditions d'échecs
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
				if (t == dt)
				{
					if (t > 0)
					{
						System.out.print("\033[s"); // sauvegarde la position du curseur
						System.out.print("\033[4A\r"); // déplace le curseur de 4 lignes
					}
					System.out.println("  " + ((turn % 2 == 0) ? "Black" : "White") + "'s turn");
					System.out.println("  Turn: " + turn);
					System.out.println("  Time left: " + (max - t) + "s         ");
					if (t == 0)
						System.out.print("\n  > ");
					else
						System.out.print("\033[u"); // restore la position du curseur
					
					// Préparation de la seconde suivante
					t++;
				}
				try
				{
					if (reader.ready() == true) // si le fichier est à lire
					{
						buffer = reader.readLine();
						// Check du buffer (pas encore implémenté)
						//if (isValid(buffer) == true)
							dt = max; // break de la boucle intérieure
					}
					else
						dt = (System.currentTimeMillis() - t0) / 1000;
				}
				catch (IOException e)
				{
					e.printStackTrace(); // gestion d'erreurs
				}
			}	while (dt < max);
			turn++;
		}
	}
}