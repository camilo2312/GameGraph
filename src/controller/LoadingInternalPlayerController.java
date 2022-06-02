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
		default:
			break;
		}

		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				Random random = new Random();
				int node = lstNodes.get(random.nextInt(lstNodes.size()));

				switch (round) {
				case PRIMERA_RONDA:
					addTrafficNode(player, node);
					break;
				case SEGUNDA_RONDA:
					takeMisionPlayer(player, node);
					break;
				default:
					break;
				}
			}
		}, 4000);
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
	}
}
