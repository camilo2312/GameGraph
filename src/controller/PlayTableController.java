package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.NodeCoordinate;
import model.Player;
import model.PlayersList;

public class PlayTableController implements Initializable
{
	private Main main;
	private PlayersList lstPlayers;
	private ArrayList<NodeCoordinate> lstNodes = new ArrayList<>();
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
				switch (playerAux.getId())
				{
					case 1:
						lblPlayer1.setText(playerAux.getUserName());
						lstPlayers.searchPlayer(playerAux.getId()).setCurrentNode(22);
						break;
					case 2:
						lblPlayer2.setText(playerAux.getUserName());
						lstPlayers.searchPlayer(playerAux.getId()).setCurrentNode(27);
						break;
					case 3:
						lblPlayer3.setText(playerAux.getUserName());
						lstPlayers.searchPlayer(playerAux.getId()).setCurrentNode(32);
						break;
					case 4:
						lblPlayer4.setText(playerAux.getUserName());
						lstPlayers.searchPlayer(playerAux.getId()).setCurrentNode(37);
						break;
					case 5:
						lblPlayer5.setText(playerAux.getUserName());
						lstPlayers.searchPlayer(playerAux.getId()).setCurrentNode(6);
						break;
					case 6:
						lblPlayer6.setText(playerAux.getUserName());
						lstPlayers.searchPlayer(playerAux.getId()).setCurrentNode(17);
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
	private void drawTableGame()
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
	private void drawPolygon(double x, double y, double radio, int sides, int position, int positionj)
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

            	addNode(posX, posY, zone, reward, false);
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
	private void addNode(double posX, double posY, String zone, int reward, boolean isTrafficLight)
	{
		double newPosX = posX - 5;
		double newPosY = posY - 5;
		boolean isNew = true;

		for (NodeCoordinate nodeAux : lstNodes)
		{
			if ((int)nodeAux.getPosX() == (int)newPosX && (int)nodeAux.getPosY() == (int)newPosY)
			{
				isNew = false;
				break;
			}
		}

		if (isNew)
		{
			NodeCoordinate node = new NodeCoordinate();
			node.setPosX(newPosX);
			node.setPosY(newPosY);
			node.setNode(keyNode);
			node.setZone(zone);
			node.setReward(reward);
			node.setTrafficLight(isTrafficLight);
			lstNodes.add(node);
	        keyNode++;
		}
	}

	/**
	 * Método que permite dibujar los nodos del tablero de juego
	 */
	private void drawNodes()
	{
		for (NodeCoordinate node : lstNodes)
		{
			this.gc.setFill(Color.BLACK);
			this.gc.fillText("" + node.getNode(), node.getPosX(), node.getPosY() - 6);

			switch (node.getNode()) {
			case 6:
				this.gc.setFill(Color.VIOLET);
				break;
			case 17:
				this.gc.setFill(Color.BLUE);
				break;
			case 22:
				this.gc.setFill(Color.GREEN);
				break;
			case 27:
				this.gc.setFill(Color.YELLOW);
				break;
			case 32:
				this.gc.setFill(Color.ORANGE);
				break;
			case 37:
				this.gc.setFill(Color.rgb(136, 6, 6));
				break;
			default:
				break;
			}

			this.gc.fillOval(node.getPosX(), node.getPosY(), 12, 12);
			this.gc.setLineWidth(3);
			this.gc.strokeOval(node.getPosX(), node.getPosY(), 12, 12);
		}

		Collections.shuffle(lstNodes);
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
				this.gc.setFill(Color.ORANGE);
				this.gc.fill();
				break;
			case 5:
				this.gc.setFill(Color.rgb(131, 6, 6));
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

		this.main.addCardNodesToGame(this.lstNodes);
	}

	/**
	 * Método que permite asignar un semáforo
	 * @param currentNode
	 */
	public void drawTafficNode(NodeCoordinate currentNode)
	{
		this.gc.setFill(Color.RED);
		this.gc.fillOval(currentNode.getPosX(), currentNode.getPosY(), 12, 12);
		this.gc.setLineWidth(3);
		this.gc.strokeOval(currentNode.getPosX(), currentNode.getPosY(), 12, 12);
	}

	public void updateTextPlayer(int idNode, int idPlayer)
	{
		String message = "Debes ir a la ciudad número " + idNode + "";
		Tooltip tooltip = new Tooltip();
		Image img = new Image(getClass().getResourceAsStream("../images/star.png"));
		ImageView imgView = new ImageView(img);

		imgView.setFitWidth(14);
		imgView.setFitHeight(14);

		tooltip.setText(message);
		tooltip.setGraphic(imgView);
		switch (idPlayer)
		{
			case 1:
				lblPlayer1.setTooltip(tooltip);
				break;
			case 2:
				lblPlayer2.setTooltip(tooltip);
				break;
			case 3:
				lblPlayer3.setTooltip(tooltip);
				break;
			case 4:
				lblPlayer4.setTooltip(tooltip);
				break;
			case 5:
				lblPlayer5.setTooltip(tooltip);
				break;
			case 6:
				lblPlayer6.setTooltip(tooltip);
				break;
			default:
				break;
		}
	}

	/**
	 * Nodo que permite pintar de negro el nodo anterior
	 * del jugador
	 * @param node nodo anterior
	 */
	public void drawBlackNodePrevious(NodeCoordinate node)
	{
		this.gc.setFill(Color.BLACK);
		this.gc.fillOval(node.getPosX(), node.getPosY(), 12, 12);
	}

	/**
	 * Método que permite pintar el nodo al que se mueve el jugador
	 * @param idPlayer identificador del jugador
	 * @param node nuevo nodo
	 */
	public void drawNewNodePlayer(int idPlayer, NodeCoordinate node)
	{
		switch (idPlayer)
		{
			case 1:
				this.gc.setFill(Color.GREEN);
				break;
			case 2:
				this.gc.setFill(Color.YELLOW);
				break;
			case 3:
				this.gc.setFill(Color.ORANGE);
				break;
			case 4:
				this.gc.setFill(Color.rgb(131, 6, 6));
				break;
			case 5:
				this.gc.setFill(Color.VIOLET);
				break;
			case 6:
				this.gc.setFill(Color.BLUE);
				break;
			default:
				break;
		}

		this.gc.fillOval(node.getPosX(), node.getPosY(), 12, 12);
		this.gc.strokeOval(node.getPosX(), node.getPosY(), 12, 12);
	}

}
