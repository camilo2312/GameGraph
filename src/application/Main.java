package application;

import java.io.IOException;
import java.util.ArrayList;

import controller.AddPlayersController;
import controller.AddTrafficLightsController;
import controller.DiceController;
import controller.GameCardsController;
import controller.PlayTableController;
import controller.SelectNodeMoveController;
import controller.ShowGraphController;
import enums.Rounds;
import controller.LoadingInternalPlayerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Game;
import model.NodeCoordinate;
import model.Player;
import model.PlayersList;
import threads.ThreadUpdateTooltipPlayer;
import threads.ThreadDices;
import threads.ThreadSelectedNodeMove;
import threads.ThreadTafficNode;
import threads.ThreadTakeMisionPlayer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application
{
	private Stage primaryStage;
	private Stage secondStage;
	private Game game = new Game("Simulador Vial");
	private ThreadTafficNode threadGame;
	private ThreadTakeMisionPlayer threadTakeMision;
	private ThreadDices threadDices;
	private PlayTableController playTableController;
	private DiceController diceController;

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("¡Bienvenido al juego!");
		viewWindowAddPlayers();
	}

	public static void main(String[] args)
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}

	/**
	 * Método que permite abrir la ventana de carga
	 */
	public void viewWindowLoading(Player player, Rounds round)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/LoadingInternalPlayerView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			LoadingInternalPlayerController loadingInternalPlayerController = loader.getController();
			loadingInternalPlayerController.setMain(this, player, round);

			Scene scene = new Scene(rootLayout);
			secondStage = new Stage();
			secondStage.setResizable(false);
			secondStage.initStyle(StageStyle.UTILITY);
			secondStage.centerOnScreen();
			secondStage.setScene(scene);
			secondStage.show();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Método que permite mostrar la ventana de ingresar jugadores
	 */
	public void viewWindowAddPlayers()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/AddPlayers.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();

			AddPlayersController addPlayersController = loader.getController();
			addPlayersController.setMain(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setScene(scene);
			primaryStage.show();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite mostrar la ventana de juego
	 */
	public void viewWindowPlay()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PlayTableView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();

			playTableController = loader.getController();
			playTableController.setMain(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setX(275);
			primaryStage.setY(30);
			primaryStage.show();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite visualizar la ventada de los dados
	 */
	public void viewWindowDice(Player currentPlayer)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/DiceView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			diceController = loader.getController();
			diceController.setMain(this, currentPlayer);

			Scene scene = new Scene(rootLayout);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.initStyle(StageStyle.UTILITY);
			stage.setX(1030);
			stage.setY(30);
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite visualizar la ventada de las cartas o misiones
	 */
	public void viewWindowCards(Player player)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/GameCardsView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			GameCardsController gameCardsController = loader.getController();
			gameCardsController.setMain(this, player);

			Scene scene = new Scene(rootLayout);
			Stage stage = new Stage();
			stage.centerOnScreen();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Métdodo que permite mostrar la ventana para asignar un semaforo por jugador
	 * @param userName
	 */
	public void viewAddTrafficLights(Player player)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/AddTrafficLightsView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			AddTrafficLightsController addTrafficLightsController = loader.getController();
			addTrafficLightsController.setMain(this, player);

			Scene scene = new Scene(rootLayout);
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.setScene(scene);
			stage.show();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite mostrar las ventanas del juego
	 */
	public void viewWindowsGame()
	{
		viewWindowPlay();
		startThreadGame(getFirstPlayer());
	}

	/**
	 * Método que permite validar si ya existe  el jugador
	 * ingresado
	 * @param playerName nombre del jugador
	 * @return
	 */
	public boolean existPlayer(String playerName)
	{
		return game.existPlayer(playerName);
	}

	/**
	 * Método que permite agregar un jugador
	 * @param playerName
	 */
	public void addPlayer(String playerName, int idPlayer, boolean isHuman)
	{
		game.addPlayer(playerName, idPlayer, isHuman);
	}

	/**
	 * Método que permite obtener la lista de jugadores
	 * @return lstPlayers
	 */
	public PlayersList getPlayers()
	{
		return game.getPlayers();
	}

	public model.Graph getGraph()
	{
		return this.game.getGraph();
	}

	/**
	 * Método que permite visualizar la ventana del grafo
	 */
	public void viewGraphWindow()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ShowGraphView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			ShowGraphController showGraphController = loader.getController();
			showGraphController.setMain(this);

			Scene scene = new Scene(rootLayout);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.setScene(scene);
			stage.show();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite abrir la ventana para seleccionar un nodo
	 */
	public void viewWindowSelectNodeMove(Player currentPlayer, int currentValue)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/SelectNodeMoveView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			SelectNodeMoveController selectNodeMoveController = loader.getController();
			selectNodeMoveController.setMain(this, currentPlayer, currentValue);

			Scene scene = new Scene(rootLayout);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.setScene(scene);
			stage.show();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite asignar la lista de cartas nodo
	 * al juego
	 * @param stackCardsNode
	 */
	public void addCardNodesToGame(ArrayList<NodeCoordinate> lstNodes)
	{
		for (NodeCoordinate nodeCoordinate : lstNodes)
		{
			this.game.getStackCardsNode().insertCard(nodeCoordinate);
		}
	}

	/**
	 * Método que permite obtener la lista de llaves de los nodos
	 * @return
	 */
	public ArrayList<Integer> getNodesKey()
	{
		return this.game.getNodesKey();
	}

	/**
	 * Método que permite iniciar el hilo del juego
	 */
	public void startThreadGame(Player player)
	{
		this.threadGame = new ThreadTafficNode("ThreadGame", this, player);
		Platform.runLater(this.threadGame);
	}

	/**
	 * Método que permite obtener el jugador
	 * @return player
	 */
	public Player getFirstPlayer()
	{
		return this.game.getPlayers().getLastPlayer().getNextPlayer();
	}

	/**
	 * Método que permite asignar un semáforo
	 * @param node
	 */
	public void addTrafficNode(int node)
	{
		NodeCoordinate currentNode = this.game.addTrafficNode(node);
		 if (currentNode != null)
		 {
			 playTableController.drawTafficNode(currentNode);
		 }
	}

	/**
	 * Método que permite cerrar la ventana de los jugadores internos
	 */
	public void closeWindowInternalPlayer()
	{
		secondStage.close();
	}

	/**
	 * Método que permite iniciar el hilo para elegir una misión
	 * @param player
	 */
	public void startThreadTakeMision(Player player)
	{
		this.threadTakeMision = new ThreadTakeMisionPlayer("ThreadMision", this, player);
		Platform.runLater(this.threadTakeMision);
	}

	/**
	 * Método que permite inicial el hilo para tirar los dados
	 * @param currentPlayer
	 */
	public void startThreadDices(Player currentPlayer)
	{
		this.threadDices = new ThreadDices("ThreadDices", this, currentPlayer);
		Platform.runLater(this.threadDices);
	}

	/**
	 * Método que permite asignar una misión a un jugador
	 * @param currentNode nodo o carta elegida
	 * @param idPlayer id del jugador
	 * @return isAssignment
	 */
	public boolean assignmentMisionToPlayer(NodeCoordinate currentNode, int idPlayer)
	{
		boolean result = this.game.assignmentMisionToPlayer(currentNode, idPlayer);

		if (result)
		{
			ThreadUpdateTooltipPlayer thread = new ThreadUpdateTooltipPlayer(this, currentNode.getNode(), idPlayer);
			Platform.runLater(thread);

		}

		return result;
	}

	/**
	 * Método que permite elegir una carta al azar
	 * @param idNode indice del nodo
	 * @return nodo o carta elegida
	 */
	public NodeCoordinate getNodeByKey(int idNode)
	{
		return this.game.getNodeByKey(idNode);
	}

	/**
	 * Método que permite obtener la carta cabeza de la pila
	 * @return
	 */
	public NodeCoordinate getHeadCard()
	{
		return this.game.getHeadCard();
	}

	/**
	 * Método que valida si el nodo es una posición de
	 * los jugadores
	 * @param node
	 * @return
	 */
	public boolean diferentPosition(int node)
	{
		return this.game.diferentPosition(node);
	}

	/**
	 * Método que permite actualizar los tooltips de los jugadores
	 * @param node nodo destino
	 * @param player jugador
	 */
	public void updateTextPlayer(int node, int player)
	{
		this.playTableController.updateTextPlayer(node, player);

	}

	/**
	 * Método que permite seleccionar una carta de la baraja
	 * @return card
	 */
	public NodeCoordinate getCard()
	{
		return this.game.getStackCardsNode().getCard();
	}

	/**
	 * Método que permite obtener los nodos incidentes
	 * @param currentNode
	 * @return lstNodes
	 */
	public ArrayList<Integer> getNodesIncidents(int currentNode)
	{
		return this.game.getNodesIncidents(currentNode);
	}

	/**
	 * Método que permite obtener el peso entre 2 nodos
	 * @param currentNode
	 * @param nodeDestiny
	 * @return weight
	 */
	public int getWeightNodes(int currentNode, int nodeDestiny)
	{
		return this.game.getWeightNodes(currentNode, nodeDestiny);
	}

	public void movePlayer(Player currentPlayer, int nodeDestiny, int currentValue)
	{
		drawBlackNodePrevious(currentPlayer.getCurrentNode());

		currentPlayer = this.game.updateCurrentNodePlayer(currentPlayer.getId(), nodeDestiny);

		drawNewNodePlayer(currentPlayer.getId(), nodeDestiny);

		this.diceController.updateCurrentValuePlayer(currentPlayer, currentValue);
	}

	private void drawNewNodePlayer(int idPlayer, int nodDestiny)
	{
		NodeCoordinate node = this.game.getStackCardsNode().searchNode(nodDestiny);

		if (node != null)
		{
			this.playTableController.drawNewNodePlayer(idPlayer, node);
		}
	}

	private void drawBlackNodePrevious(int currentNode)
	{
		NodeCoordinate node = this.game.getStackCardsNode().searchNode(currentNode);

		if (node != null)
		{
			this.playTableController.drawBlackNodePrevious(node);
		}
	}

	public void startThreadSelectNodeMove(Player currentPlayer, int currentValue)
	{
		ThreadSelectedNodeMove threadSelectedNodeMove = new ThreadSelectedNodeMove("ThreadSelectNodeMove", this, currentValue, currentPlayer);
		Platform.runLater(threadSelectedNodeMove);
	}
}
