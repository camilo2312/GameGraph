package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.javafx.FxGraphRenderer;

import application.Main;

import org.graphstream.graph.Node;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class ShowGraphController implements Initializable
{
	@FXML
	private VBox vBoxGraph;
	private Graph graph = new SingleGraph("Visualizar grafo");
	private model.Graph graph2;
	private Main main;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

	}

	private void generateGraph()
	{
		addNodes();

		graph.setAttribute("ui.antialias");


		FxViewer display = new FxViewer(graph,  FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

		display.enableAutoLayout();

		FxViewPanel graphPanel = (FxViewPanel)display.addDefaultView(false, new FxGraphRenderer());


		vBoxGraph.getChildren().add(graphPanel);
	}

	private void addNodes()
	{
		String origin = "";
		String to = "";
		for (int i = 0; i < graph2.getMatrix().length; i++)
		{
			Node node = graph.addNode(i + "");
			node.setAttribute("ui.label", i + "");
		}

		for (int i = 0; i < graph2.getMatrix().length; i++)
		{
			for (int j = 0; j < graph2.getMatrix()[i].length; j++)
			{
				if (graph2.getMatrix()[i][j] != 0)
				{
					origin = i + "";
					to = j + "";
					graph.addEdge(origin + to, origin, to, true);
				}
			}
		}


	}

	private void getGraph()
	{
		this.graph2 = this.main.getGraph();
		generateGraph();
	}

	public void setMain(Main main)
	{
		this.main = main;
		getGraph();
	}


}
