package application;

import java.io.IOException;
import java.util.ArrayList;
import controller.AddPlayersController;
import controller.DiceController;
import controller.PlayTableController;
import controller.ShowGraphController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import model.PlayersList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application
{
	private Stage primaryStage;
	private Game game = new Game("Simulador Vial");

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

			PlayTableController playTableController = loader.getController();
			playTableController.setMain(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setX(60);
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
	public void viewWindowDice()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/DiceView.fxml"));
			Parent rootLayout = (AnchorPane) loader.load();

			DiceController diceController = loader.getController();
			diceController.setMain(this);

			Scene scene = new Scene(rootLayout);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setX(820);
			stage.setY(30);
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
		viewWindowDice();
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
	public void addPlayer(String playerName, int idPlayer)
	{
		game.addPlayer(playerName, idPlayer);
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
}
