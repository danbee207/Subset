package substa.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
	//셀러id / 옥션id / expire date / start date / reserve price
	private int cusId;
	private int aucId;
	private Timestamp endDate;
	private Timestamp startDate;
	private float price;
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getAucId() {
		return aucId;
	}
	public void setAucId(int aucId) {
		this.aucId = aucId;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
	
}
