package substa.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SalesRecord {
	private long buyerID;
	private long sellerID;
	private Float price;
	private Timestamp date;
	private int auctionID;

	public long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(long buyerID) {
		this.buyerID = buyerID;
	}

	public long getSellerID() {
		return sellerID;
	}

	public void setSellerID(long sellerID) {
		this.sellerID = sellerID;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

}
