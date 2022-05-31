package model;

public class Card
{
	private String zone;
	private String node;
	private int reward;
	private Card nextCard;

	public Card(String zone, String node, int reward)
	{
		this.zone = zone;
		this.node = node;
		this.reward = reward;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public Card getNextCard() {
		return nextCard;
	}

	public void setNextCard(Card nextCard) {
		this.nextCard = nextCard;
	}



}
