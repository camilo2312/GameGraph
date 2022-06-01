package model;

public class StackCardsNode
{
	private NodeCoordinate headCard;

	public StackCardsNode(){}

	public NodeCoordinate getHeadCard()
	{
		return headCard;
	}

	public void setHeadCard(NodeCoordinate headCard)
	{
		this.headCard = headCard;
	}

	/**
	 * Método que permite insertar un nodo o carta en la pila
	 * @param node
	 */
	public void insertCard(NodeCoordinate node)
	{
		if (headCard == null)
		{
            headCard = node;
        }
        else
        {
            NodeCoordinate currentNode = headCard;

            while(currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(node);
        }
	}

	public NodeCoordinate searchNode(int node)
	{
		NodeCoordinate currentNode = headCard;

        while(currentNode.getNextNode() != null) {
        	if (currentNode.getNode() == node)
        	{
        		return currentNode;
        	}
            currentNode = currentNode.getNextNode();
        }

        return null;
	}

	public void asignmentTraffictNode(int node)
	{
		searchNode(node).setTrafficLight(true);
	}


}
