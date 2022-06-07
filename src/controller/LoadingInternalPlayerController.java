package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import enums.Rounds;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.NodeCoordinate;
import model.Player;
import threads.ThreadInternalPlayer;

public class LoadingInternalPlayerController implements Initializable
{
	private Main main;
	private ArrayList<Integer> lstNodes;
	private ThreadInternalPlayer threadInternalPlayer;

    @FXML
    private Label lblPlayerTurn;

    @FXML
    private Label lblDescription;

    @FXML
    private ImageView imageViewGif;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		RotateTransition transition = new RotateTransition();
		transition.setNode(imageViewGif);
		transition.setDuration(Duration.millis(1000));
		transition.setCycleCount(TranslateTransition.INDEFINITE);
		transition.setInterpolator(Interpolator.LINEAR);
		transition.setByAngle(360);
		transition.play();
	}

	/**
	 * Método que permite asignar la clase principal en el
	 * controller
	 * @param main aplicación principal
	 */
	public void setMain(Main main, Player player, Rounds round)
	{
		this.main = main;
		lblPlayerTurn.setText("¡Es turno de " + player.getUserName() + "!");
		lstNodes = this.main.getNodesKey();

		switch (round) {
		case PRIMERA_RONDA:
			lblDescription.setText("Eligiendo ciudad al azar");
			break;
		case SEGUNDA_RONDA:
			lblDescription.setText("Eligiendo una misión al azar");
			break;
		case TERCERA_RONDA:
			lblDescription.setText("Tirando dados \n y \n reocorriendo el tablero");
			break;
		default:
			break;
		}

		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				int node = getRandomNode();

				switch (round) {
				case PRIMERA_RONDA:
					addTrafficNode(player, node);
					break;
				case SEGUNDA_RONDA:
					takeMisionPlayer(player, node);
					break;
				case TERCERA_RONDA:
					movePlayer(player);
					break;
				default:
					break;
				}
			}
		}, 4000);
	}

	/**
	 * Método que permite obtener un nodo random
	 * @return
	 */
	private int getRandomNode()
	{
		Random random = new Random();
		int node = 0;
		do
		{
			node = lstNodes.get(random.nextInt(lstNodes.size()));

		} while(diferentPosition(node) == false && notTraffict(node) == false);

		return node;
	}

	/**
	 * Método que permite validar si el nodo ya tiene un semáforo o no
	 * @param node
	 * @return
	 */
	private boolean notTraffict(int node)
	{
		return main.notTraffictNode(node);
	}

	/**
	 * Método que permite validar si el nodo
	 * es deferente al de la posición de los jugadores
	 * @param node
	 * @return
	 */
	private boolean diferentPosition(int node)
	{
		return main.diferentPosition(node);
	}

	/**
	 * Método que permite asignar un semáforo a un nodo
	 * @param player
	 */
	private void addTrafficNode(Player player, int node)
	{
		this.main.addTrafficNode(node);

		player = player.getNextPlayer();

		this.threadInternalPlayer = new ThreadInternalPlayer("Thread internal player", this.main);
		Platform.runLater(threadInternalPlayer);
    	if (player != this.main.getFirstPlayer())
    	{
    		this.main.startThreadGame(player);
    	}
    	else
    	{
    		this.main.calcShortRoute();
    		this.main.startThreadTakeMision(player);
    	}
	}

	/**
	 * Método que permite asignar una misión al jugador
	 * @param player
	 */
	private void takeMisionPlayer(Player player, int idNode)
	{
		NodeCoordinate node = this.main.getNodeByKey(idNode);

		this.main.assignmentMisionToPlayer(node, player.getId());

		player = player.getNextPlayer();

		this.threadInternalPlayer = new ThreadInternalPlayer("Thread internal player", this.main);
		Platform.runLater(threadInternalPlayer);
    	if (player != this.main.getFirstPlayer())
    	{
    		this.main.startThreadTakeMision(player);
    	}
    	else
    	{
    		this.main.startThreadDices(player);
    	}
	}

	/**
	 * Método que permite correr el jugador interno del juego
	 * Aplicar disjktra
	 * @param player
	 */
	private void movePlayer(Player player)
	{
		Random random = new Random();
		int number1 = random.nextInt(6) + 1;
		int number2 = random.nextInt(6) + 1;
		int result = number1 + number2;
		int costMove = 0;
		int i = 0;

		try
		{
			String[] nodes = this.main.getShortRoute(player.getCurrentNode(), player.getMision().getNode()).split(",");
			while (result != 0 && i < nodes.length)
			{
				costMove = this.main.getWeightNodes(player.getCurrentNode(), Integer.parseInt(nodes[i + 1]));
				result = result - costMove;
				if (result > 0)
				{
					this.main.movePlayer(player, Integer.parseInt(nodes[i + 1]), result);
				}
				i = i + 1;
				Thread.sleep(1000);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		if (player.getCurrentNode() == player.getMision().getNode())
		{
			this.main.startThreadWinnerPlayer(player.getUserName());
		}
		else
		{
			player = player.getNextPlayer();
			this.threadInternalPlayer = new ThreadInternalPlayer("Thread internal player", this.main);
			Platform.runLater(threadInternalPlayer);

			this.main.startThreadDices(player);
		}

	}
}
