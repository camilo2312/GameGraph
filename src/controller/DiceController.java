package controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Player;

public class DiceController implements Initializable
{
	 private Main main;
	 private Player currentPlayer;

	 @FXML
	 private Label lblTitle;

	 @FXML
	 private ImageView imgDice;

	 @FXML
	 private ImageView imgDice2;

	 @FXML
	 private TextField txtResult;

	 @FXML
	 private TextField txtCurrentResult;

	 @FXML
	 private Button btnDice;

	 @FXML
	 private Button btnDiscard;

	 @FXML
	 private Button btnMove;

	 @FXML
	 void rollDiceAction(ActionEvent event)
	 {
		 setNumberRandom();
	 }

	 @FXML
	 void discardAction(ActionEvent event)
	 {

	 }

     @FXML
     void moveAction(ActionEvent event)
     {
    	 move();
     }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.btnDiscard.setDisable(true);
		this.btnMove.setDisable(true);

	}

	/**
	 * Método que permite obtener un número entre 1 y 6 al azar
	 */
	private void setNumberRandom()
	{
		Image img = null;
		Random random = new Random();
		int number =  random.nextInt(6) + 1;
		int number2 = random.nextInt(6) + 1;

		switch (number)
		{
			case 1:
				img = new Image(getClass().getResourceAsStream("../images/uno.png"));
				break;
			case 2:
				img = new Image(getClass().getResourceAsStream("../images/dos.png"));
				break;
			case 3:
				img = new Image(getClass().getResourceAsStream("../images/tres.png"));
				break;
			case 4:
				img = new Image(getClass().getResourceAsStream("../images/cuatro.png"));
				break;
			case 5:
				img = new Image(getClass().getResourceAsStream("../images/cinco.png"));
				break;
			case 6:
				img = new Image(getClass().getResourceAsStream("../images/seis.png"));
				break;
			default:
				break;
		}

		imgDice.setImage(img);

		switch (number2)
		{
			case 1:
				img = new Image(getClass().getResourceAsStream("../images/uno.png"));
				break;
			case 2:
				img = new Image(getClass().getResourceAsStream("../images/dos.png"));
				break;
			case 3:
				img = new Image(getClass().getResourceAsStream("../images/tres.png"));
				break;
			case 4:
				img = new Image(getClass().getResourceAsStream("../images/cuatro.png"));
				break;
			case 5:
				img = new Image(getClass().getResourceAsStream("../images/cinco.png"));
				break;
			case 6:
				img = new Image(getClass().getResourceAsStream("../images/seis.png"));
				break;
			default:
				break;
		}

		imgDice2.setImage(img);

		int result = number + number2;

		txtResult.setText("" + result);
		txtCurrentResult.setText("" + result);

		this.btnDice.setDisable(true);
		this.btnDiscard.setDisable(false);
		this.btnMove.setDisable(false);
	}

	/**
	 * Método que permite mover el jugador de su posición
	 */
	private void move()
	{
		int currentValue = Integer.parseInt(txtCurrentResult.getText());
		this.main.startThreadSelectNodeMove(currentPlayer, currentValue);
	}

	/**
	 * Método que permite cerrar la ventana actual
	 */
	private void close()
	{
		Stage stage = (Stage) this.btnDice.getScene().getWindow();
		stage.close();
	}

	/**
	 * Método que permite asignar la clase aplicación en el controlador
	 * @param main
	 */
	public void setMain(Main main, Player player)
	{
		this.main = main;
		this.currentPlayer = player;
		lblTitle.setText("Tira los dados " + player.getUserName());
	}

	/**
	 * Método que permite actualizar el valor y el jugador del controlador
	 * de dados
	 * @param currentPlayer
	 * @param currentValue
	 */
	public void updateCurrentValuePlayer(Player currentPlayer, int currentValue)
	{
		if (currentValue > 0)
		{
			this.currentPlayer = currentPlayer;
			this.txtCurrentResult.setText(currentValue + "");

			this.showMessage("Éxito", "Movimiento", "Tú movimiento fue exitoso, pero aún te restan valores por mover, intentalo una vez más", AlertType.INFORMATION);
		}
		else
		{
			this.currentPlayer = this.currentPlayer.getNextPlayer();
			this.main.startThreadDices(this.currentPlayer);
			close();
		}
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
}
