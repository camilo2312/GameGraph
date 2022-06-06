package threads;

import application.Main;
import enums.Rounds;
import model.Player;

public class ThreadDices extends Thread
{
	private Main main;
	private Player currentPlayer;

	public ThreadDices(String name, Main main, Player player)
	{
		super(name);
		this.main = main;
		this.currentPlayer = player;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(2000);
			if (currentPlayer.getIsHuman())
			{
				this.main.viewWindowDice(this.currentPlayer);
			}
			else
			{
				this.main.viewWindowLoading(this.currentPlayer, Rounds.TERCERA_RONDA);
			}

			this.interrupt();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}
