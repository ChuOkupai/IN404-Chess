import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Classe Human
 * 
 * @author	Mathis Dankou
 * @version	06/04/2019
 */
 
public class Human extends Player
{
	private BufferedReader reader;
	
	/**
	 * Constructeur du joueur humain
	 * @param bank la valeur de la banque du joueur
	 * @param color la couleur du joueur
	 */
	public Human(int bank, int color)
	{
		super(bank, color);
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Cette méthode retourne la commande tapée par le joueur
	 * Si c'est un humain, renvoie l'entrée utilisateur
	 * Si c'est une IA, renvoie une entrée aléatoire
	 * @return la commande du joueur
	 */
	public String getCom()
	{
		String com = null; // pour gérer le cas ou il n'y a rien
		try
		{
			if(reader.ready() == true)
			{
				com = reader.readLine();
				if (com.length() < 4)
					com = "    ";
				System.out.print("\033[13;7H\033[J"); // Reset du reader
			}
		}
		catch(IOException e){ e.printStackTrace(); }
		return com;
	}
	
	/**
	 * S'occupe de la promotion d'un pion
	 * @return la chaîne de la nouvelle pièce
	 */
	public String getPromotion()
	{
		return getCom();
	}
}
