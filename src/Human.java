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
	
	public Human(int bank, int color)
	{
		super(bank, color);
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
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
				System.out.print("\033[12;5H\033[J"); // Reset du reader
			}
		}
		catch(IOException e){ e.printStackTrace(); }
		return com;
	}
	
	public String getPromotion()
	{
		return getCom();
	}
}
