package model;

public class NodeCoordinate
{
	private double posX;
	private double posY;
	private String zone;
	private int node;
	private int reward;
	private NodeCoordinate nextNode;
	private boolean isTrafficLight;

	public NodeCoordinate() {}

	public double getPosX()
	{
		return posX;
	}

	public void setPosX(double posX)
	{
		this.posX = posX;
	}

	public double getPosY()
	{
		return posY;
	}

	public void setPosY(double posY)
	{
		this.posY = posY;
	}

	public String getZone()
	{
		return zone;
	}

	public void setZone(String zone)
	{
		this.zone = zone;
	}

	public int getNode()
	{
		return node;
	}

	public void setNode(int node)
	{
		this.node = node;
	}

	public int getReward()
	{
		return reward;
	}

	public void setReward(int reward)
	{
		this.reward = reward;
	}

	public NodeCoordinate getNextNode()
	{
		return nextNode;
	}

	public void setNextNode(NodeCoordinate nextNode)
	{
		this.nextNode = nextNode;
	}

	public boolean isTrafficLight() {
		return isTrafficLight;
	}

	public void setTrafficLight(boolean isTrafficLight) {
		this.isTrafficLight = isTrafficLight;
	}


}
