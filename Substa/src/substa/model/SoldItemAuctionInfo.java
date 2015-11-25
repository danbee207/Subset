package substa.model;

import java.sql.Timestamp;

public class SoldItemAuctionInfo {
	private int itemID;
	private String itemName;
	private int auctionID;
	private Timestamp PostDate;
	private Timestamp ExpireDate;

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

	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	public Timestamp getPostDate() {
		return PostDate;
	}

	public void setPostDate(Timestamp postDate) {
		PostDate = postDate;
	}

	public Timestamp getExpireDate() {
		return ExpireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		ExpireDate = expireDate;
	}

}
