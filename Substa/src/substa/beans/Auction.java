package substa.beans;

public class Auction {

	private int auctionId;
	private float bidIncrement;
	private double minBid;
	private int NumSold;
	private int mornitor;
	private int itemId;
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public float getBidIncrement() {
		return bidIncrement;
	}
	public void setBidIncrement(float bidIncrement) {
		this.bidIncrement = bidIncrement;
	}
	public double getMinBid() {
		return minBid;
	}
	public void setMinBid(double minBid) {
		this.minBid = minBid;
	}
	public int getNumSold() {
		return NumSold;
	}
	public void setNumSold(int numSold) {
		NumSold = numSold;
	}
	public int getMornitor() {
		return mornitor;
	}
	public void setMornitor(int mornitor) {
		this.mornitor = mornitor;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
	
}
