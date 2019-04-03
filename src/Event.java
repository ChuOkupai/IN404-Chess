
public class Event
{
	private int x1,y1,x2,y2;
	private Piece EatenPiece;
	
	public Event(int x1,int y1,int x2,int y2,Piece EatenPiece)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.EatenPiece=EatenPiece;
	}
	
	public int getStartingX()
	{
		return x1;
	}
	
	public int getStartingY()
	{
		return y1;
	}
	
	public int getFinalX()
	{
		return x2;
	}
	
	public int getFinalY()
	{
		return y2;
	}
	
	Piece getEatenPiece()
	{
		return EatenPiece;
	}
}
