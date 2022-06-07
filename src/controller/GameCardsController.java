package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.NodeCoordinate;
import model.Player;

public class GameCardsController implements Initializable
{
	private Main main;
	private NodeCoordinate currentNode;
	private Player currentPlayer;
//	private int attemps = 0;

	@FXML
    private Label lblTitleCard;

	@FXML
    private Label lblReward;

    @FXML
    private Label lblZone;

    @FXML
    private Label lblNode;

    @FXML
    private AnchorPane anchorPaneFourStar;

    @FXML
    private Button btnNextCard;

    @FXML
    private Button btnTakeMision;

    @FXML
    void nextCardAction(ActionEvent event)
    {
    	nextCardNode();
    }

    @FXML
    void takeMisionAction(ActionEvent event)
    {
    	takeMision();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.anchorPaneFourStar.setVisible(false);
	}

	/**
	 * Método que permite obtener el nodo siguiente
	 */
	private void nextCardNode()
	{
		currentNode = this.main.getCard();
		setValuesLabel();
	}

	/**
	 * Método que permite visualizar las estrellas
	 */
	private void setValuesLabel()
	{
//		if (attemps != 2)
		{
			this.lblNode.setText(currentNode.getNode() + "");
			this.lblReward.setText(currentNode.getReward() + "");
			this.lblZone.setText(currentNode.getZone());

			switch (currentNode.getZone().toLowerCase())
			{
				case "centro":
					lblZone.setTextFill(Color.BLACK);
					break;
				case "azul":
					lblZone.setTextFill(Color.BLUE);
					break;
				case "amarillo":
					lblZone.setTextFill(Color.YELLOW);
					break;
				case "verde":
					lblZone.setTextFill(Color.GREEN);
					break;
				case "rojo":
					lblZone.setTextFill(Color.RED);
					break;
				case "naranja":
					lblZone.setTextFill(Color.ORANGERED);
					break;
				case "violeta":
					lblZone.setTextFill(Color.VIOLET);
					break;
				default:
					break;
			}

			if (currentNode.isItsMision() || !diferentPosition(currentNode.getNode()))
			{
				this.btnTakeMision.setDisable(true);
			}
			else
			{
				this.btnTakeMision.setDisable(false);
			}

			this.anchorPaneFourStar.setVisible(currentNode.getReward() == 4);
//			attemps++;
		}
//		else
//		{
//			this.showMessage("Error", "Seleccionar misión", "Solo puedes descartar 2 cartas, debes elegir la que tienes actualmente", AlertType.ERROR);
//		}
	}

	/**
	 * Método que permite escoger un nodo diferente al
	 * de los jugadores
	 * @param node
	 * @return
	 */
	private boolean diferentPosition(int node)
	{
		return this.main.diferentPosition(node);
	}

	/**
	 * Método que permite elegir una misión
	 */
	private void takeMision()
	{
		if (this.currentNode != null)
		{
			close();
			if (this.main.assignmentMisionToPlayer(this.currentNode, this.currentPlayer.getId()))
			{
				this.showMessage("Éxito", "Asignación de misión", "Misión asignada correctamente", AlertType.INFORMATION);
				this.currentPlayer = this.currentPlayer.getNextPlayer();
				if (this.currentPlayer != this.main.getFirstPlayer())
	        	{
	        		this.main.startThreadTakeMision(this.currentPlayer);
	        	}
				else
				{
					this.main.startThreadDices(this.currentPlayer);
				}
			}
			else
			{
				this.showMessage("Error", "Asignación de misión", "No se pudo asignar la misión", AlertType.ERROR);
			}
		}
	}

	 /**
     * Método que permite cerrar la ventanas
     */
    private void close()
    {
    	Stage currentStage = (Stage) this.btnTakeMision.getScene().getWindow();
		currentStage.close();
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

	/**
	 * Método que permite asignar la clase principal al controller
	 * @param main
	 */
	public void setMain(Main main, Player player)
	{
		this.main = main;
		this.currentPlayer = player;
		this.currentNode = this.main.getHeadCard();
		setValuesLabel();
		this.lblTitleCard.setText("¡Selecciona tú misión " + player.getUserName() + "!");
	}
}
