/*
 * Classe Human
 * 
 * @author	Mathis Dankou
 * @version	02/04/2019
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Human
{
	
	public Human(int bank, int color)
	{
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String getCom()
	{
		String com;
		try
		{
			if(reader.ready() == true)
				buffer = reader.readLine();
		}
		catch(IOException e){ e.printStackTrace(); }
	}
}
