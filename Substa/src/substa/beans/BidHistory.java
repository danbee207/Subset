package substa.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BidHistory {
	private int auctionID;
	private long CustomerID;
	private int itemID;
	private String itemName;
	private Float BidPrice;
	private Timestamp BidTime;
	private Float maxBid;

	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	public long getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(long customerID) {
		CustomerID = customerID;
	}

	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Float getBidPrice() {
		return BidPrice;
	}

	public void setBidPrice(Float bidPrice) {
		BidPrice = bidPrice;
	}

	public Timestamp getBidTime() {
		return BidTime;
	}

	public void setBidTime(Timestamp bidTime) {
		BidTime = bidTime;
	}
	
	public Float getMaxBid() {
		return maxBid;
	}
	
	public void setMaxBid(Float maxBid) {
		this.maxBid = maxBid;
	}

}
