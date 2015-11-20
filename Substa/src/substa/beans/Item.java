package substa.beans;

public class Item {

	private int itemId;
	private String itemName;
	private ItemType itemType;
	private int numCopies;
	private String deescription;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public int getNumCopies() {
		return numCopies;
	}
	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}
	public String getDeescription() {
		return deescription;
	}
	public void setDeescription(String deescription) {
		this.deescription = deescription;
	}
	
	
}
