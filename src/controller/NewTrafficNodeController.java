package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class NewTrafficNodeController
{
	private Main main;

	@FXML
    private Label lblUserName;

    @FXML
    private Label lblPlayerTurn1;

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

    public void setMain(Main main)
    {
    	this.main = main;
    }

	private void assigmentNode()
	{
		if (comboBoxNodesTraffict.getValue() != null && comboBoxNodesNoTraffict.getValue() != null)
		{
			int nodeTraffic = comboBoxNodesTraffict.getValue();
			int nodeNoTraffic = comboBoxNodesNoTraffict.getValue();
		}
	}
}
