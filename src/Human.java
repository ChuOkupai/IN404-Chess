/*
 * Classe Human
 * 
 * @author	Mathis Dankou
 * @version	02/04/2019
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
 
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
		String com = "";
		try
		{
			if(reader.ready() == true)
			{
				com = reader.readLine();
				if(com.length​() >= 4) com = com.subchain(0,5);
			}
		}
		catch(IOException e){ e.printStackTrace(); }
		
		return com;
	}
}
