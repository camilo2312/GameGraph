package threads;

import application.Main;
import enums.Rounds;
import model.Player;

public class ThreadTafficNode extends Thread
{
	private Main main;
	private Player player;

	public ThreadTafficNode(String name, Main main, Player player)
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
			if (player.getIsHuman())
			{
				this.main.viewAddTrafficLights(player);
			}
			else
			{
				this.main.viewWindowLoading(player, Rounds.PRIMERA_RONDA);
			}
			this.interrupt();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
