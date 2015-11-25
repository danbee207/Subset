package substa.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BidHistory {
	private int auctionID;
	private int CustomerID;
	private BigDecimal BidPrice;
	private Timestamp BidTime;

	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public BigDecimal getBidPrice() {
		return BidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		BidPrice = bidPrice;
	}

	public Timestamp getBidTime() {
		return BidTime;
	}

	public void setBidTime(Timestamp bidTime) {
		BidTime = bidTime;
	}

}
