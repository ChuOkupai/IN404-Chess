import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Classe Human
 * 
 * @author	Mathis Dankou
 * @version	02/04/2019
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
				if (com.length​() < 4)
					com = "    ";
				else if(com.length​() > 4) com = com.substring(0,5);
			}
		}
		catch(IOException e){ e.printStackTrace(); }
		return com;
	}
}
