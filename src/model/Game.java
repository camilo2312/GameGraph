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
	 * Método que permite obtener los nodos con semáforo
	 * @return lstNodesKey
	 */
	public ArrayList<Integer> getNodesTraffic()
	{
		ArrayList<Integer> lstNodesKey = new ArrayList<>();
		NodeCoordinate node = this.stackCardsNode.getHeadCard();

		while(node != null)
		{
			if (node.isTrafficLight())
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
	 * @return isDiferent
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
			this.graph.addOrDeleteWeightNode(node, true);
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
		NodeCoordinate node = this.stackCardsNode.searchNode(currentNode.getNode());

		if (player != null && node != null)
		{
			node.setItsMision(true);
			player.setMision(node);
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

	/**
	 * Método que permite obtener los nodos incidentes sobre otro
	 * @param currentNode
	 * @return lstNodes
	 */
	public ArrayList<Integer> getNodesIncidents(int currentNode)
	{
		return graph.getNodesIncidents(currentNode);
	}

	/**
	 * Método que permite obtener el peso entre 2 nodos
	 * @param currentNode
	 * @param nodeDestiny
	 * @return weight
	 */
	public int getWeightNodes(int currentNode, int nodeDestiny)
	{
		return this.graph.getWeightNodes(currentNode, nodeDestiny);
	}

	/**
	 * Método que permite actualizar el nodo al que se mueve
	 * el jugador
	 * @param idPlayer identificador del jugador
	 * @param nodeDestiny nodo destino
	 */
	public Player updateCurrentNodePlayer(int idPlayer, int nodeDestiny)
	{
		Player player = this.lstPlayers.searchPlayer(idPlayer);
		player.setCurrentNode(nodeDestiny);

		return player;
	}

	public String getShortRoute(int currentNode, int node)
	{
		return this.graph.shortRoad(currentNode, node);
	}

	/**
	 * Método que permite calcular las rutas más cortas
	 */
	public void calcShortRoute()
	{
		this.graph.calcShortRoute();
	}

	/**
	 * Método que permite eliminar un nodo con semáforo
	 * @param idNode
	 */
	public void deleteTrafficNode(int idNode)
	{
		NodeCoordinate node = this.stackCardsNode.searchNode(idNode);
		if (node != null)
		{
			node.setTrafficLight(false);
			this.graph.addOrDeleteWeightNode(idNode, false);
		}
	}

}
