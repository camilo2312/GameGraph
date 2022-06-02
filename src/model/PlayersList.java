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

	/**
	 * Método que permite buscar un jugador
	 * @param idPlayer
	 * @return player
	 */
	public Player searchPlayer(int idPlayer)
	{
		if (lastPlayer != null)
		{
			Player playerAux = lastPlayer;
			while(playerAux != null)
			{
				if (playerAux.getId() == idPlayer)
				{
					return playerAux;
				}

				playerAux = playerAux.getNextPlayer();
			}
		}

		return null;
	}
}
