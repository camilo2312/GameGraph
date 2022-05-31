package controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiceController implements Initializable
{
	 @SuppressWarnings("unused")
	private Main main;

	 @FXML
     private ImageView imgDice;

     @FXML
     private ImageView imgDice2;

	 @FXML
	 private TextField txtResult;

	 @FXML
	 private Button btnDice;

	 @FXML
	 void rollDiceAction(ActionEvent event)
	 {
		 setNumberRandom();
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		setNumberRandom();
	}

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
	}

	public void setMain(Main main)
	{
		this.main = main;
	}
}
