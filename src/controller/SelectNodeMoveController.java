package controller;

import java.util.ArrayList;
import java.util.Collections;

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

public class SelectNodeMoveController
{
	private Main main;
	private ObservableList<Integer> lstNodesData = FXCollections.observableArrayList();
	private int currentValue;
	private Player currentPlayer;

	@FXML
	private Label lblTitle;

	@FXML
    private Label lblOriginNode;

	@FXML
	private Label lblDestinyNode;

    @FXML
    private Label lblCostMove;

	@FXML
    private Button btnAccept;

	@FXML
    private ComboBox<Integer> comboBoxNodes;

    @FXML
    void acceptAction(ActionEvent event)
    {
    	accept();
    }

    @FXML
    void changeNodeAction(ActionEvent event)
    {
    	changeNode();
    }

	/**
     * Método que permite ejecutar el evento de aceptar
     * para realizar el moviemiento del nodo
     */
    private void accept()
    {
    	if (lblCostMove.getText() != null && !lblCostMove.getText().trim().equals(""))
    	{
    		int cost = Integer.parseInt(lblCostMove.getText());
    		int nodeDestiny = Integer.parseInt(lblDestinyNode.getText());

    		if (this.currentValue != 0 && cost <= this.currentValue)
    		{
    			this.currentValue = this.currentValue - cost;
    			this.main.movePlayer(this.currentPlayer, nodeDestiny, this.currentValue);
    			this.close();
    		}
    	}
    }

    /**
     * Método que detecta el cambio en el combobox
     * y actualiza los labels
     */
    private void changeNode()
    {
		if (comboBoxNodes.getValue() != null)
		{
			int nodeDestiny = comboBoxNodes.getValue();
    		int costMove = this.main.getWeightNodes(this.currentPlayer.getCurrentNode(), nodeDestiny);
			this.lblOriginNode.setText(this.currentPlayer.getCurrentNode() + "");
			this.lblDestinyNode.setText(nodeDestiny + "");
			this.lblCostMove.setText(costMove + "");

		}
	}

    /**
     * Método que permite cerrar la ventana actual
     */
    private void close()
    {
    	Stage stage = (Stage) this.btnAccept.getScene().getWindow();
    	stage.close();
    }

    /**
     * Método que permite asignar la aplicación principal
     * al controlador
     * @param main
     */
    public void setMain(Main main, Player player, int currentValue)
    {
    	this.main = main;
    	lblTitle.setText(player.getUserName() + " debes ir a la ciudad " + player.getMision().getNode());
    	this.comboBoxNodes.setItems(loadComboBox(player.getCurrentNode()));
    	this.currentValue = currentValue;
    	this.currentPlayer = player;
    }

    /**
     *
     *
     * Método que permite inicializar el combobox de los nodos
     * @return Oberva
     */
	private ObservableList<Integer> loadComboBox(int currentNode)
	{
		ArrayList<Integer> keys = this.main.getNodesIncidents(currentNode);
    	Collections.sort(keys);

    	this.lstNodesData.addAll(keys);

    	return lstNodesData;
	}
}
