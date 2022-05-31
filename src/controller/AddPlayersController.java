package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPlayersController implements Initializable
{
	private Main main;
	private int totalPlayers = 0;
	private int idPlayer = 2;

	@FXML
    private Label lblPlayers;

    @FXML
    private TextField txtPlayerName;

    @FXML
    private Button btnAddPlayer;

    @FXML
    private Button btnStartGame;

    @FXML
    void addPlayerAction(ActionEvent event)
    {
    	addPlayer();
    }

    @FXML
    void startGameAction(ActionEvent event)
    {
    	startGame();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		btnStartGame.setVisible(false);
		lblPlayers.setText("Ingresados: " + totalPlayers);
	}

	/**
	 * Método que permite agregar jugadores
	 */
    private void addPlayer()
    {
    	String playerName = txtPlayerName.getText();

    	if (!playerName.trim().equals("") && !main.existPlayer(playerName))
    	{
    		if (totalPlayers != 5)
    		{
    			main.addPlayer(playerName, idPlayer);
        		lblPlayers.setText("Ingresados: " + (totalPlayers = totalPlayers + 1));
        		txtPlayerName.setText("");
        		idPlayer = idPlayer + 1;

        		if (totalPlayers >= 1)
        		{
        			this.btnStartGame.setVisible(true);
        		}
    		}
    		else
    		{
    			this.showMessage("Error", "Agregar jugador", "No es posible agregar más jugadores", AlertType.WARNING);
    		}
    	}
    	else
    	{
    		this.showMessage("Error", "Agregar jugador", "El jugador ingresado ya existe", AlertType.WARNING);
    	}

    }

    /**
     * Método que permite inicializar el juego
     */
    private void startGame()
    {
    	main.viewWindowsGame();
    }

    /**
	 * Método que permite mostrar un mensaje en pantalla
	 *
	 * @param titulo
	 * @param encabezado
	 * @param mensaje
	 * @param alertType
	 */
	private void showMessage(String title, String header, String message, AlertType alertType)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}

    public void setMain(Main main)
    {
    	this.main = main;
    }
}
