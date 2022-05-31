package controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

    private void addTrafficNode()
    {
    	if (comboBoxNodes.getValue() != null)
    	{
    		close();
        	int node = comboBoxNodes.getValue();
        	this.main.addTrafficNode(node);
        	this.currentPlayer = this.main.nextPlayer(this.currentPlayer);
        	this.main.startThreadGame(this.currentPlayer);
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

}
