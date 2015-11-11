package substa.beans;

public class Customer extends User{

	private float rating;
	private long creditCardNum;
	
	public Customer(){
		super();
	}
	public Customer(User user){
		this.setSsn(user.getSsn());
		this.setFirst(user.getFirst());
		this.setLast(user.getLast());
		this.setEmail(user.getEmail());
		this.setPw(user.getPw());
		this.setAddress(user.getAddress());
		this.setPw(user.getPw());
		this.setZipcode(user.getZipcode());
	}
	
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
