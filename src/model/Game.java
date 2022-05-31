package model;


public class Game
{
	private String gameName;
	private Graph graph;
	private PlayersList lstPlayers;

	public Game(String gameName)
	{
		this.gameName = gameName;
		this.graph = new Graph();
		lstPlayers = new PlayersList();
		addPlayer("Maquina", 1);
	}

	public String getGameName()
	{
		return gameName;
	}

	/**
	 * Método que permite validar si ya existe el jugador ingresado
	 * @param playerName
	 * @return verdadero si ya existe y falso si no
	 */
	public boolean existPlayer(String playerName)
	{

		Player playerAux = lstPlayers.getLastPlayer().getNextPlayer();

		if (playerAux != null)
		{
			do
			{
				if (playerAux.getUserName().equals(playerName))
				{
					return true;
				}

				playerAux = playerAux.getNextPlayer();

			}while(playerAux != lstPlayers.getLastPlayer().getNextPlayer());
		}

		return false;
	}

	/**
	 * Método que permite ingresar un nuevo jugador
	 * @param playerName nombre del jugador
	 */
	public void addPlayer(String playerName, int idPlayer)
	{
		Player player = new Player();
		player.setUserName(playerName);
		player.setId(idPlayer);
		player.setPoints(0);

		lstPlayers.insertPlayer(player);
	}

	/**
	 * Método que permite obtener la lista de jugadores
	 * @return
	 */
	public PlayersList getPlayers()
	{
		return lstPlayers;
	}

	/**
	 * Método que permite obtener el grafo
	 * @return graph
	 */
	public Graph getGraph()
	{
		return graph;
	}

}
