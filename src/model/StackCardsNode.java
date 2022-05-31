package model;

public class StackCardsNode
{
	private NodeCoordinate headCard;

	public StackCardsNode(){}

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

	public NodeCoordinate getHeadCard()
	{
		return headCard;
	}

	public void setHeadCard(NodeCoordinate headCard)
	{
		this.headCard = headCard;
	}

}
