package threads;

import application.Main;
import enums.Rounds;
import model.Player;

public class ThreadTakeMisionPlayer extends Thread
{
	private Main main;
	private Player player;

	public ThreadTakeMisionPlayer(String name, Main main, Player player)
	{
		super(name);
		this.main = main;
		this.player = player;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(1500);
			if (this.player.getIsHuman())
			{
				this.main.viewWindowCards(player);
			}
			else
			{
				this.main.viewWindowLoading(player, Rounds.SEGUNDA_RONDA);
			}

			this.interrupt();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
