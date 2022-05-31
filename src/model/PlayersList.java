package model;

public class PlayersList
{
	private Player lastPlayer;

	public PlayersList()
	{
		lastPlayer = null;
	}

	public Player getLastPlayer()
	{
		return lastPlayer;
	}

	public void setLastPlayer(Player lastPlayer)
	{
		this.lastPlayer = lastPlayer;
	}

	/**
	 * Método que permite agregar un jugador a lista de turnos
	 * @param player jugador a insertar
	 */
	public void insertPlayer(Player player)
	{
	    if(lastPlayer != null)
	    {
	        player.setNextPlayer(lastPlayer.getNextPlayer());
	        lastPlayer.setNextPlayer(player);
	    }
	    lastPlayer = player;
	}
}
