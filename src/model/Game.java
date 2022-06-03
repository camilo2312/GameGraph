package model;

import java.util.ArrayList;

public class Game
{
	private String gameName;
	private Graph graph;
	private PlayersList lstPlayers;
	private StackCardsNode stackCardsNode;

	public Game(String gameName)
	{
		this.gameName = gameName;
		this.graph = new Graph();
		lstPlayers = new PlayersList();
		setStackCardsNode(new StackCardsNode());
		addPlayer("Maquina", 1, false);
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
	public void addPlayer(String playerName, int idPlayer, boolean isHuman)
	{
		Player player = new Player();
		player.setUserName(playerName);
		player.setId(idPlayer);
		player.setPoints(0);
		player.setHuman(isHuman);

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

	/**
	 * Método que permite obtener los nodos de las cartas
	 * @return stackCardsNode
	 */
	public StackCardsNode getStackCardsNode()
	{
		return stackCardsNode;
	}

	/**
	 * Método que permite asignar el valor a uno de las cartas nodo
	 * @param stackCardsNode
	 */
	public void setStackCardsNode(StackCardsNode stackCardsNode)
	{
		this.stackCardsNode = stackCardsNode;
	}

	/**
	 * Método que permite obtener la lista de llaves de los nodos
	 * @return
	 */
	public ArrayList<Integer> getNodesKey()
	{
		ArrayList<Integer> lstNodesKey = new ArrayList<>();
		NodeCoordinate node = this.stackCardsNode.getHeadCard();

		while(node != null)
		{
			if (!node.isTrafficLight() && diferentPosition(node.getNode()))
			{
				lstNodesKey.add(node.getNode());
			}
			node = node.getNextNode();
		}

		return lstNodesKey;
	}

	/**
	 * Método que permite validar si la posición es diferente de los
	 * nodos de los jugadores
	 * @param node
	 * @return
	 */
	public boolean diferentPosition(int node)
	{
		boolean isDiferent = true;
		switch (node) {
		case 6:
		case 17:
		case 22:
		case 27:
		case 32:
		case 37:
			isDiferent = false;
			break;

		default:
			break;
		}

		return isDiferent;
	}

	/**
	 * Método que permite asignar un peso en el nodo
	 * @param node
	 */
	public void addWeightNode(int node)
	{
		this.graph.addWeightNode(node);
	}

	/**
	 * Método que permite asignar un semáforo al nodo
	 * @param node indice del nodo
	 * @return currentNode
	 */
	public NodeCoordinate addTrafficNode(int node)
	{
		NodeCoordinate currentNode = this.stackCardsNode.searchNode(node);

		if (currentNode != null)
		{
			currentNode.setTrafficLight(true);
			this.graph.addWeightNode(node);
		}

		return currentNode;
	}

	/**
	 * Método que permite asignar una misión a un jugador
	 * @param currentNode nodo o carta elegida
	 * @param idPlayer id del jugador
	 * @return isAssignment
	 */
	public boolean assignmentMisionToPlayer(NodeCoordinate currentNode, int idPlayer)
	{
		boolean isAssignment = false;

		Player player = this.lstPlayers.searchPlayer(idPlayer);

		if (player != null)
		{
			stackCardsNode.searchNode(currentNode.getNode()).setItsMision(true);
			player.setMision(currentNode);
			isAssignment = true;
		}

		return isAssignment;
	}

	/**
	 * Método que permite elegir un nodo al azar
	 * @return nodo o carta elegida
	 */
	public NodeCoordinate getNodeByKey(int idNode)
	{
		NodeCoordinate nodeAux = stackCardsNode.getHeadCard();

		while(nodeAux != null)
		{
			if (nodeAux.getNode() == idNode)
			{
				return nodeAux;
			}

			nodeAux = nodeAux.getNextNode();
		}

		return null;
	}

	/**
	 * Método que permite tomar la carta cabeza
	 * @return lastCard
	 */
	public NodeCoordinate getHeadCard()
	{
		return stackCardsNode.getHeadCard();
	}

}
