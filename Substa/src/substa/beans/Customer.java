package substa.beans;

public class Customer extends User{

	private float rating;
	private long creditCardNum;
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public long getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(long creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	
}
