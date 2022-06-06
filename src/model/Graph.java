package model;

import java.util.ArrayList;

public class Graph
{
	private int[][] matrix;
	private int[][] matrixAux;
	private int[][] roadMatrix;
	private int[][] adjacencyMatix;
	private final int CANT_NODES = 42;

	public Graph()
	{
		matrix = new int[CANT_NODES][CANT_NODES];
		matrixAux = new int[CANT_NODES][CANT_NODES];
		roadMatrix = new int[CANT_NODES][CANT_NODES];
		adjacencyMatix = new int[CANT_NODES][CANT_NODES];
		addItemMatrixs();


	}

	public int[][] getMatrix()
	{
		return matrix;
	}

	public int[][] getAdjacencyMatix()
	{
		return adjacencyMatix;
	}


	private void addItemMatrixs()
	{
		// Pesos entre los nodos de los poligonos

		this.matrix[0][1] = 3;
		this.matrix[1][2] = 3;
		this.matrix[2][3] = 3;
		this.matrix[3][4] = 3;
		this.matrix[4][5] = 3;
		this.matrix[5][0] = 3;

		this.matrix[6][7]   = 2;
		this.matrix[7][8]   = 2;
		this.matrix[8][9]   = 2;
		this.matrix[9][10]  = 2;
		this.matrix[10][11] = 2;
		this.matrix[11][6]  = 2;

		this.matrix[12][13] = 2;
		this.matrix[13][14] = 2;
		this.matrix[14][15] = 2;
		this.matrix[15][16] = 2;
		this.matrix[16][17] = 2;
		this.matrix[17][12] = 2;

		this.matrix[18][19] = 2;
		this.matrix[19][20] = 2;
		this.matrix[20][21] = 2;
		this.matrix[21][22] = 2;
		this.matrix[22][23] = 2;
		this.matrix[23][18] = 2;

		this.matrix[24][25] = 2;
		this.matrix[25][26] = 2;
		this.matrix[26][27] = 2;
		this.matrix[27][28] = 2;
		this.matrix[28][29] = 2;
		this.matrix[29][24] = 2;

		this.matrix[30][31] = 2;
		this.matrix[31][32] = 2;
		this.matrix[32][33] = 2;
		this.matrix[33][34] = 2;
		this.matrix[34][35] = 2;
		this.matrix[35][30] = 2;

		this.matrix[36][37] = 2;
		this.matrix[37][38] = 2;
		this.matrix[38][39] = 2;
		this.matrix[39][40] = 2;
		this.matrix[40][41] = 2;
		this.matrix[41][36] = 2;


		// Pesos entre los nodos de cada poligono

		this.matrix[0][9] = 1;
		this.matrix[9][0] = 1;

		this.matrix[5][14] = 1;
		this.matrix[14][5] = 1;

		this.matrix[4][19] = 1;
		this.matrix[19][4] = 1;

		this.matrix[3][24] = 1;
		this.matrix[24][3] = 1;

		this.matrix[2][35] = 1;
		this.matrix[35][2] = 1;

		this.matrix[1][40] = 1;
		this.matrix[40][1] = 1;

		this.matrix[41][8] = 1;
		this.matrix[8][41] = 1;

		this.matrix[10][13] = 1;
		this.matrix[13][10] = 1;

		this.matrix[15][18] = 1;
		this.matrix[18][15] = 1;

		this.matrix[20][29] = 1;
		this.matrix[29][20] = 1;

		this.matrix[25][34] = 1;
		this.matrix[34][25] = 1;

		this.matrix[30][39] = 1;
		this.matrix[39][30] = 1;


		addItemsAdjacencyMatrix();
	}

	private void addItemsAdjacencyMatrix()
	{
		// Matriz de adyacencias del grafo

		this.adjacencyMatix[0][1] = 1;
		this.adjacencyMatix[1][2] = 1;
		this.adjacencyMatix[2][3] = 1;
		this.adjacencyMatix[3][4] = 1;
		this.adjacencyMatix[4][5] = 1;
		this.adjacencyMatix[5][0] = 1;

		this.adjacencyMatix[6][7]   = 1;
		this.adjacencyMatix[7][8]   = 1;
		this.adjacencyMatix[8][9]   = 1;
		this.adjacencyMatix[9][10]  = 1;
		this.adjacencyMatix[10][11] = 1;
		this.adjacencyMatix[11][6] = 1;

		this.adjacencyMatix[12][13] = 1;
		this.adjacencyMatix[13][14] = 1;
		this.adjacencyMatix[14][15] = 1;
		this.adjacencyMatix[15][16] = 1;
		this.adjacencyMatix[16][17] = 1;
		this.adjacencyMatix[17][12] = 1;

		this.adjacencyMatix[18][19] = 1;
		this.adjacencyMatix[19][20] = 1;
		this.adjacencyMatix[20][21] = 1;
		this.adjacencyMatix[21][22] = 1;
		this.adjacencyMatix[22][23] = 1;
		this.adjacencyMatix[23][18] = 1;

		this.adjacencyMatix[24][25] = 1;
		this.adjacencyMatix[25][26] = 1;
		this.adjacencyMatix[26][27] = 1;
		this.adjacencyMatix[27][28] = 1;
		this.adjacencyMatix[28][29] = 1;
		this.adjacencyMatix[29][24] = 1;

		this.adjacencyMatix[30][31] = 1;
		this.adjacencyMatix[31][32] = 1;
		this.adjacencyMatix[32][33] = 1;
		this.adjacencyMatix[33][34] = 1;
		this.adjacencyMatix[34][35] = 1;
		this.adjacencyMatix[35][30] = 1;

		this.adjacencyMatix[36][37] = 1;
		this.adjacencyMatix[37][38] = 1;
		this.adjacencyMatix[38][39] = 1;
		this.adjacencyMatix[39][40] = 1;
		this.adjacencyMatix[40][41] = 1;
		this.adjacencyMatix[41][36] = 1;

		// Adyacencias bidireccionales

		this.adjacencyMatix[0][9] = 1;
		this.adjacencyMatix[9][0] = 1;

		this.adjacencyMatix[5][14] = 1;
		this.adjacencyMatix[14][5] = 1;

		this.adjacencyMatix[4][19] = 1;
		this.adjacencyMatix[19][4] = 1;

		this.adjacencyMatix[3][24] = 1;
		this.adjacencyMatix[24][3] = 1;

		this.adjacencyMatix[2][35] = 1;
		this.adjacencyMatix[35][2] = 1;

		this.adjacencyMatix[1][40] = 1;
		this.adjacencyMatix[40][1] = 1;

		this.adjacencyMatix[41][8] = 1;
		this.adjacencyMatix[8][41] = 1;

		this.adjacencyMatix[10][13] = 1;
		this.adjacencyMatix[13][10] = 1;

		this.adjacencyMatix[15][18] = 1;
		this.adjacencyMatix[18][15] = 1;

		this.adjacencyMatix[4][19] = 1;
		this.adjacencyMatix[19][4] = 1;

		this.adjacencyMatix[20][24] = 1;
		this.adjacencyMatix[24][20] = 1;

		this.adjacencyMatix[25][34] = 1;
		this.adjacencyMatix[34][25] = 1;

		this.adjacencyMatix[30][39] = 1;
		this.adjacencyMatix[39][30] = 1;
	}

	/**
	 * Método que permite asignar peso de +2 a los arcos incidentes
	 * @param node indice del nodo
	 */
	public void addWeightNode(int node)
	{
		int currentValue = 0;
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				if (j == node && matrix[i][j] != 0)
				{
					currentValue = matrix[i][j];
					matrix[i][j] = currentValue + 2;
				}
			}
		}
	}

	/**
	 * Método que permite obtener los nodos incidentes sobre el nodo mandado
	 * @param node identificador del nodo a encontrar los nodos incidentes
	 * @return lstNodes
	 */
	public ArrayList<Integer> getNodesIncidents(int node)
	{
		ArrayList<Integer> lstNodes = new ArrayList<>();
		for (int i = 0; i < adjacencyMatix.length; i++)
		{
			for (int j = 0; j < adjacencyMatix[i].length; j++)
			{
				if (i == node && adjacencyMatix[i][j] != 0)
				{
					lstNodes.add(j);
				}
			}
		}

		return lstNodes;

	}

	/**
	 * Método que permite obtener el peso entre 2 nodos
	 * @param currentNode
	 * @param nodeDestiny
	 * @return weight
	 */
	public int getWeightNodes(int currentNode, int nodeDestiny)
	{
		return matrix[currentNode][nodeDestiny];
	}

	/**
	 * Método que permite calcular la ruta más corta entre los nodos
	 */
	public void calcShortRoute() {

		this.matrixAux = this.matrix;

		for (int i = 0; i < matrixAux.length; i++) {
			for (int j = 0; j < matrixAux[i].length; j++) {
				if (matrixAux[i][j] == 0)
				{
					matrixAux[i][j] = 10000;
				}
			}
		}

		int i, j, k;
		for (i = 0; i < this.CANT_NODES; i++)
		{
			for (j = 0; j < this.CANT_NODES; j++)
			{
				for (k = 0; k < this.CANT_NODES; k++)
				{
					if (this.matrixAux[i][k] + this.matrixAux[k][j] < this.matrixAux[i][j])
					{
						this.matrixAux[i][j] = this.matrixAux[i][k] + this.matrixAux[k][j];
						this.roadMatrix[i][j] = k;
					}
				}
			}
		}
	}

	/**
	 * Método que permite obtener el peso minimo del camino
	 * @param origin nodo origen
	 * @param destiny nodo destino
	 * @return weight
	 */
	public int getMinWeight(int origin, int destiny)
	{
		return this.matrixAux[origin][destiny];
	}

	/**
	 * Método que permite encontrar el camino con los nodos que se deben
	 * recorrer para llegar al destino
	 * @param origin nodo origen
	 * @param destiny nodo destino
	 * @return route
	 */
	public String shortRoad(int origin, int destiny) {
		String route;
		if (origin == destiny)
		{
			route = "" + origin;
		}
		else
		{
			route = shortRoad(origin, this.roadMatrix[origin][destiny]) + ","
					+ destiny;
		}
		return route;
	}
}
