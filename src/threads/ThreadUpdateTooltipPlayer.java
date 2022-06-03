package threads;

import application.Main;

public class ThreadUpdateTooltipPlayer extends Thread
{
	private Main main;
	private int node;
	private int player;

	public ThreadUpdateTooltipPlayer(Main main, int node, int player)
	{
		this.main = main;
		this.node = node;
		this.player = player;
	}

	@Override
	public void run()
	{
		this.main.updateTextPlayer(node, player);
		this.interrupt();
	}
}
