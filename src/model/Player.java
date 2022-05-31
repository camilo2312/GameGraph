package model;

public class Player
{
	private int id;
	private String userName;
	private int points;
	private boolean isHuman;
	private Player nextPlayer;

	public Player()
	{
		this.nextPlayer = this;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public int getPoints()
	{
		return points;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Player getNextPlayer()
	{
		return nextPlayer;
	}

	public void setNextPlayer(Player nextPlayer)
	{
		this.nextPlayer = nextPlayer;
	}

	public boolean isHuman()
	{
		return isHuman;
	}

	public void setHuman(boolean isHuman)
	{
		this.isHuman = isHuman;
	}




}
