package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.NodeCoordinate;
import model.Player;
import model.PlayersList;
import model.StackCardsNode;

public class PlayTableController implements Initializable
{
	private Main main;
	private StackCardsNode stackCardsNode = new StackCardsNode();
	private PlayersList lstPlayers;
	private int keyNode = 0;

	@FXML
	private Button btnViewGraph;

	@FXML
    private Canvas canvas;
	private GraphicsContext gc;

	@FXML
    private Label lblPlayer1;

    @FXML
    private Label lblPlayer2;

    @FXML
    private Label lblPlayer3;

    @FXML
    private Label lblPlayer4;

	@FXML
    private Label lblPlayer5;

    @FXML
    private Label lblPlayer6;


    @FXML
    void viewGraphAction(ActionEvent event)
    {
    	viewGraph();
    }

    private void viewGraph()
    {
    	this.main.viewGraphWindow();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.gc = canvas.getGraphicsContext2D();
		drawTableGame();

	}

	/**
	 * Método que permite inicializar los nombres de los jugadores
	 */
	private void initializeNamePlayers()
	{
		lstPlayers = main.getPlayers();

		if (lstPlayers != null)
		{
			Player playerAux = lstPlayers.getLastPlayer().getNextPlayer();

			do
			{
				switch (playerAux.getId()) {
				case 1:
					lblPlayer1.setText(playerAux.getUserName());
					break;
				case 2:
					lblPlayer2.setText(playerAux.getUserName());
					break;
				case 3:
					lblPlayer3.setText(playerAux.getUserName());
					break;
				case 4:
					lblPlayer4.setText(playerAux.getUserName());
					break;
				case 5:
					lblPlayer5.setText(playerAux.getUserName());
					break;
				case 6:
					lblPlayer6.setText(playerAux.getUserName());
					break;
				default:
					break;
				}

				playerAux = playerAux.getNextPlayer();


			}while(playerAux != lstPlayers.getLastPlayer().getNextPlayer());
		}
	}

	/**
	 * Método que permite dibujar el tablero de juego
	 */
	public void drawTableGame()
	{
		int valueAdd = 0;
		double valuex = 0;
		double valuey = 0;

        double desplazamiento = Math.toRadians(90);

        drawPolygon(360, 280, 65, 6, 0, 0);
        this.gc.setFill(Color.WHITE);
        this.gc.fill();

        for (int i = 1; i <= 3; i++)
        {
        	for (int j = 0; j < 6; j++)
            {
        		if (i == 1)
        		{
        			valueAdd = 113;
        			valuex = Math.cos((j * 2 * Math.PI / 6) + desplazamiento);
        			valuey = Math.sin((j * 2 * Math.PI / 6)  + desplazamiento);
        		}
        		else
        		{
        			valueAdd = 195;
        			valuex = Math.sin((j * 2 * Math.PI / 6) + desplazamiento);
        			valuey = Math.cos((j * 2 * Math.PI / 6)  + desplazamiento);
        		}

                double posX = 360 + valueAdd * valuex;
                double posY = 280 + valueAdd * valuey;
                drawPolygon(posX, posY, 65, 6, i, j);
                setFillColor(i, j);
            }
		}

        this.drawNodes();
	}

	/**
	 * Método que permite dibujar el poligono correspondiente
	 * @param x posición en x del poligono
	 * @param y posición en y del poligono
	 * @param radio anchura de la línea
	 * @param sides número de lados
	 */
	public void drawPolygon(double x, double y, double radio, int sides, int position, int positionj)
	{
        this.gc.beginPath();
        String zone = "";
        int reward = 0;

        for (int i = 0; i <= sides; i++)
        {
            double posX = x + radio * Math.cos(i * 2 * Math.PI / sides);
            double posY = y + radio * Math.sin(i * 2 * Math.PI / sides);
            if (position == 0 || position == 2)
            {
            	if (position == 0)
            	{
            		zone = "Centro";
            		reward = 4;
            	}
            	else
            	{
            		reward = 3;
            		switch (positionj)
                	{
    	            	case 0:
    	    				zone = "Violeta";
    	    				break;
    	    			case 1:
    	    				zone = "Azul";
    	    				break;
    	    			case 2:
    	    				zone = "Verde";
    	    				break;
    	    			case 3:
    	    				zone = "Amarillo";
    	    				break;
    	    			case 4:
    	    				zone = "Naranja";
    	    				break;
    	    			case 5:
    	    				zone = "Rojo";
    	    				break;
    	    			default:
    	    				break;
    				}
            	}

            	addNode(posX, posY, zone, reward);
            }
            this.gc.lineTo(posX, posY);
        }
        this.gc.stroke();
	}

	/**
	 * Método que permite agregar un nodo a la lista de nodos
	 * @param posX posición en X del nodo
	 * @param posY posición en Y del nodo
	 */
	private void addNode(double posX, double posY, String zone, int reward)
	{
		double newPosX = posX - 5;
		double newPosY = posY - 5;
		boolean isNew = true;

		if (this.stackCardsNode.getHeadCard() != null)
		{
			NodeCoordinate nodeAux = this.stackCardsNode.getHeadCard();

			do
			{
				if ((int)nodeAux.getPosX() == (int)newPosX && (int)nodeAux.getPosY() == (int)newPosY)
				{
					isNew = false;
					break;
				}

				nodeAux = nodeAux.getNextNode();
            }
            while(nodeAux != null);

			if (isNew)
			{
				NodeCoordinate node = new NodeCoordinate();
				node.setPosX(newPosX);
				node.setPosY(newPosY);
				node.setNode(keyNode);
				node.setZone(zone);
				node.setReward(reward);
				this.stackCardsNode.insertCard(node);
		        keyNode++;
			}
		}
		else
		{
			NodeCoordinate node = new NodeCoordinate();
			node.setPosX(newPosX);
			node.setPosY(newPosY);
			node.setNode(keyNode);
			node.setZone(zone);
			node.setReward(reward);
			this.stackCardsNode.insertCard(node);
	        keyNode++;
		}
	}

	/**
	 * Método que permite dibujar los nodos del tablero de juego
	 */
	private void drawNodes()
	{
		NodeCoordinate node = this.stackCardsNode.getHeadCard();

		do
		{
			this.gc.setFill(Color.BLACK);
			this.gc.fillText("" + node.getNode(), node.getPosX(), node.getPosY() - 6);
			this.gc.setFill(Color.GRAY);
			this.gc.fillOval(node.getPosX(), node.getPosY(), 12, 12);
			this.gc.setLineWidth(3);
			this.gc.strokeOval(node.getPosX(), node.getPosY(), 12, 12);

			node = node.getNextNode();
        } while(node != null);

	}
	/**
	 * Método que permite asignar el color de relleno por poligono
	 * @param i
	 * @param j
	 */
	private void setFillColor(int i, int j)
	{
		if (i == 2)
		{
			switch (j) {
			case 0:
				this.gc.setFill(Color.VIOLET);
				this.gc.fill();
				break;
			case 1:
				this.gc.setFill(Color.BLUE);
				this.gc.fill();
				break;
			case 2:
				this.gc.setFill(Color.GREEN);
				this.gc.fill();
				break;
			case 3:
				this.gc.setFill(Color.YELLOW);
				this.gc.fill();
				break;
			case 4:
				this.gc.setFill(Color.ORANGERED);
				this.gc.fill();
				break;
			case 5:
				this.gc.setFill(Color.RED);
				this.gc.fill();
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Método que permite asignar la clase principal al controlador
	 * @param main
	 */
	public void setMain(Main main)
	{
		this.main = main;
		this.initializeNamePlayers();

		this.main.addCardNodesToGame(this.stackCardsNode);
		this.main.viewWindowCards();
	}

}
