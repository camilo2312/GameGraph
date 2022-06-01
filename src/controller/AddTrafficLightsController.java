package controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Player;

public class AddTrafficLightsController
{
	private Main main;
	private Player currentPlayer;
	private ObservableList<Integer> lstNodesData = FXCollections.observableArrayList();

	@FXML
    private Label lblPlayerTurn;

	@FXML
    private ComboBox<Integer> comboBoxNodes;

    @FXML
    private Button btnAdd;

    @FXML
    void addAction(ActionEvent event)
    {
    	addTrafficNode();
    }

    /**
     * Método que permite asignar un semáforo
     */
    private void addTrafficNode()
    {
    	if (comboBoxNodes.getValue() != null)
    	{
    		close();
        	int node = comboBoxNodes.getValue();
        	this.main.addTrafficNode(node);
        	this.currentPlayer = this.currentPlayer.getNextPlayer();
        	if (this.currentPlayer != this.main.getFirstPlayer())
        	{
        		this.main.startThreadGame(this.currentPlayer);
        	}
        	else
        	{
        		this.currentPlayer = null;
        	}
    	}
    	else
    	{
    		showMessage("Error", "Valor nulo", "No se ha seleccionado una ciudad", AlertType.ERROR);
    	}
    }

    /**
     * Método que permite obtener la instancia de la clase
     * principal
     * @param main
     */
    public void setMain(Main main, Player player)
    {
    	this.main = main;
    	this.currentPlayer = player;
    	lblPlayerTurn.setText("¡Es turno de " + player.getUserName() + "!");
    	this.comboBoxNodes.setItems(loadComboBox());
    }

    /**
     * Método que permite inicializar el comobobox de nodos
     */
    public ObservableList<Integer> loadComboBox()
    {
    	this.lstNodesData.addAll(this.main.getNodesKey());

    	return lstNodesData;
    }

    /**
     * Método que permite cerrar la ventanas
     */
    private void close()
    {
    	Stage currentStage = (Stage) this.btnAdd.getScene().getWindow();
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

}
