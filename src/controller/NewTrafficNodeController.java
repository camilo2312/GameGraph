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

public class NewTrafficNodeController
{
	private Main main;
	private ObservableList<Integer> lstNodesNoTrafficData = FXCollections.observableArrayList();
	private ObservableList<Integer> lstNodesTrafficData = FXCollections.observableArrayList();
	@FXML
    private Label lblUserName;

    @FXML
    private ComboBox<Integer> comboBoxNodesTraffict;

    @FXML
    private ComboBox<Integer> comboBoxNodesNoTraffict;

    @FXML
    private Button btnAssignmentNode;

    @FXML
    void assigmentNodeAction(ActionEvent event)
    {
    	assigmentNode();
    }

    /**
     * Método que permite asignar un nuevo semáforo cambiandolo
     * por uno anterior
     */
	private void assigmentNode()
	{
		if (comboBoxNodesTraffict.getValue() != null && comboBoxNodesNoTraffict.getValue() != null)
		{
			int nodeTraffic = comboBoxNodesTraffict.getValue();
			int nodeNoTraffic = comboBoxNodesNoTraffict.getValue();

			this.main.addTrafficNode(nodeNoTraffic);
			this.main.deleteTrafficNode(nodeTraffic);

			this.close();
		}
	}

    /**
     * Método que permite asignar la clase principal al controlador
     * @param main
     */
    public void setMain(Main main, String userName)
    {
    	this.main = main;
    	this.comboBoxNodesNoTraffict.setItems(loadComboBoxNoTraffic());
    	this.comboBoxNodesTraffict.setItems(loadComboBoxTraffic());
    	lblUserName.setText(userName);
    }

    /**
     * Método que permite cargar el combobox de los nodos que no tienen un semáforo
     * @return lstNodesNoTrafficData lista de nodos sin semáforo
     */
	private ObservableList<Integer> loadComboBoxNoTraffic()
	{
		ArrayList<Integer> keys = this.main.getNodesKey();
    	Collections.sort(keys);

    	this.lstNodesNoTrafficData.addAll(keys);

    	return lstNodesNoTrafficData;
	}

	/**
	 * Método que permite obtener los nodos con semáforo
	 * @return
	 */
	private ObservableList<Integer> loadComboBoxTraffic()
	{
		ArrayList<Integer> keys = this.main.getNodesTraffic();
    	Collections.sort(keys);

    	this.lstNodesTrafficData.addAll(keys);

    	return lstNodesTrafficData;
	}

	/**
	 * Método que permite cerrar la ventana actual
	 */
	private void close()
	{
		Stage stage = (Stage) this.btnAssignmentNode.getScene().getWindow();
		stage.close();
	}
}
