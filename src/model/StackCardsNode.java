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

            while(currentNode.getNextNode() != null)
            {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(node);
        }
	}

	/**
	 * Método que permite buscar un nodo en especifico
	 * @param node indice del nodo
	 * @return currentNode
	 */
	public NodeCoordinate searchNode(int node)
	{
		NodeCoordinate currentNode = headCard;

        while(currentNode.getNextNode() != null)
        {
        	if (currentNode.getNode() == node)
        	{
        		return currentNode;
        	}
            currentNode = currentNode.getNextNode();
        }

        return null;
	}

	/**
	 * Método que permite obtener una carta
	 * @return node
	 */
//	public NodeCoordinate getCard()
//	{
//        NodeCoordinate previous = null;
//        NodeCoordinate current = headCard;
//
//        if (headCard != null && headCard.getNextNode() == null)
//        {
//        	headCard = null;
//            return current;
//        }
//
//        while(current.getNextNode() != null)
//        {
//        	previous = current;
//        	current = current.getNextNode();
//        }
//        previous.setNextNode(null);
//
//        return current;
//    }

}
