package substa.beans;

import java.sql.Timestamp;

public class AuctionDetailInfo {
	private String itemName;
	private String itemType;
	private String description;
	private String imgSrc;
	private int auctionId;
	private float bidInc;
	private float minBid;
	private int copy;
	private Timestamp endDate;
	private float price;
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemType() {
		return itemType;
	}
	
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public int getAuctionID() {
		return auctionId;
	}
	
	public void setAuctionID(int auctionId) {
		this.auctionId = auctionId;
	}
	
	public float getBidInc() {
		return bidInc;
	}
	
	public void setBidInc(float bidInc) {
		this.bidInc = bidInc;
	}
	
	public float getMinBid() {
		return minBid;
	}
	
	public void setMinBid(float minBid) {
		this.minBid = minBid;
	}
	
	public int getCopy() {
		return copy;
	}
	
	public void setCopy(int copy) {
		this.copy = copy;
	}
	
	public Timestamp getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
}
