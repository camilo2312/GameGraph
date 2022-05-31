package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.NodeCoordinate;

public class GameCardsController implements Initializable
{
	private Main main;
	private NodeCoordinate currentNode;

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
    void nextCardAction(ActionEvent event)
    {
    	nextCardNode();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.lblTitleCard.setText("Misión");;
		this.anchorPaneFourStar.setVisible(false);
	}

	/**
	 * Método que permite obtener el nodo cabeza
	 */
	private void getHeadCardNode()
	{
		currentNode = main.getHeadCardNode();

		setValuesLabel();

	}

	/**
	 * Método que permite obtener el nodo siguiente
	 */
	private void nextCardNode()
	{
		currentNode = currentNode.getNextNode();
		if (currentNode == null)
		{
			currentNode = this.main.getHeadCardNode();
		}

		setValuesLabel();
	}

	/**
	 * Método que permite visualizar las estrellas
	 */
	private void setValuesLabel()
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

		this.anchorPaneFourStar.setVisible(currentNode.getReward() == 4);
	}

	/**
	 * Método que permite asignar la clase principal al controller
	 * @param main
	 */
	public void setMain(Main main)
	{
		this.main = main;
		this.getHeadCardNode();
	}
}
