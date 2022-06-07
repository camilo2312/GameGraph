package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinnerPlayerController
{
	@SuppressWarnings("unused")
	private Main main;

	@FXML
    private Label lblWinnerPlayer;

	public void setMain(Main main, String userName)
	{
		this.main = main;
		this.lblWinnerPlayer.setText("Felicitaciones " + userName);
	}
}
