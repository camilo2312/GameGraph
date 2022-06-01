package threads;

import application.Main;
import model.Player;

public class ThreadGame extends Thread
{
	private Main main;
	private Player player;

	public ThreadGame(String name, Main main, Player player)
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
			Thread.sleep(2000);
			if (player.getIsHuman())
			{
				this.main.viewAddTrafficLights(player);
			}
			else
			{
				this.main.viewWindowLoading(player);
			}
			this.interrupt();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
