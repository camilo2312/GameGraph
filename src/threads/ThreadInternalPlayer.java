package threads;

import application.Main;

public class ThreadInternalPlayer extends Thread
{
	private Main main;

	public ThreadInternalPlayer(String name, Main main)
	{
		super(name);
		this.main = main;
	}

	@Override
	public void run()
	{
		try
		{
			this.main.closeWindowInternalPlayer();
			this.interrupt();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
