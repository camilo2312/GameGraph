package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Player;
import threads.ThreadInternalPlayer;

public class TraffictLightsInternalPlayerController implements Initializable
{
	private Main main;
	private ArrayList<Integer> lstNodes;
	private ThreadInternalPlayer threadInternalPlayer;

    @FXML
    private Label lblPlayerTurn;

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
	public void setMain(Main main, Player player)
	{
		this.main = main;
		lblPlayerTurn.setText("¡Es turno de " + player.getUserName() + "!");
		lstNodes = this.main.getNodesKey();

		new Timer().schedule(new TimerTask() {
			@Override
			public void run()
			{
				addTrafficNode(player);
			}
		}, 4000);
	}

	private void addTrafficNode(Player player)
	{
		Random random = new Random();
		int node = lstNodes.get(random.nextInt(lstNodes.size()));

		this.main.addTrafficNode(node);

		player = player.getNextPlayer();

		this.threadInternalPlayer = new ThreadInternalPlayer("Thread internal player", this.main);
		Platform.runLater(threadInternalPlayer);
    	if (player != this.main.getFirstPlayer())
    	{
    		this.main.startThreadGame(player);
    	}
	}
}
