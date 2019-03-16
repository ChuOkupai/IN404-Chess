/*
* Classe pour manipuler les couleurs sur le terminal
 *
 * @author	Adrien Soursou
 * @version	16/03/2019
 */

public class Color
{
	// Reset
	public static final String RESET = "\033[0m"; // Text Reset
	
	// Regular Colors
	public static final String BLACK = "\033[0;30m"; // BLACK
	public static final String WHITE = "\033[0;37m"; // WHITE
	
	// Bold
	public static final String BOLD_BLACK = "\033[1;30m"; // BLACK
	public static final String BOLD_WHITE = "\033[1;37m"; // WHITE
	
	// Background
	public static final String BACKGROUND_BLACK = "\033[40m"; // BLACK
	public static final String BACKGROUND_WHITE = "\033[47m"; // WHITE
}