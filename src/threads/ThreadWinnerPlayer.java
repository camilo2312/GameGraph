package threads;

import application.Main;

public class ThreadWinnerPlayer extends Thread
{
	private Main main;
	private String userName;

	public ThreadWinnerPlayer(String name, Main main, String userName)
	{
		super(name);
		this.userName = userName;
		this.main = main;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(400);
			this.main.closeWindows();
			this.main.viewWindowWinnerPlayer(userName);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
