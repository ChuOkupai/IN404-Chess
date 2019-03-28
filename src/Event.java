
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
	
	public int getStartingx()
	{
		return this.x1;
	}
	public int getStartingy()
	{
		return this.y1;
	}
	public int getFinalx()
	{
		return this.x2;
	}
	public int getFinaly()
	{
		return this.y2;
	}
	Piece getEatenPiece()
	{
		return this.EatenPiece;
	}
}
