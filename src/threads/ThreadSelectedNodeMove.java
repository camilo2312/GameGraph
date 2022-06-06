package threads;

import application.Main;
import model.Player;

public class ThreadSelectedNodeMove extends Thread
{
	private Main main;
	private int currentValue;
	private Player currentPlayer;

	public ThreadSelectedNodeMove(String name, Main main, int currentValue, Player currentPlayer)
	{
		super(name);
		this.main = main;
		this.currentValue = currentValue;
		this.currentPlayer = currentPlayer;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(2000);
			this.main.viewWindowSelectNodeMove(currentPlayer, currentValue);
			this.interrupt();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
