package substa.beans;

import java.sql.Date;

public class Employer extends User{

	private int level;
	private Date startDate;
	private float hourlyRate;
	
	public Employer(){
		super();
	}
	public Employer(User user){
		this.setSsn(user.getSsn());
		this.setFirst(user.getFirst());
		this.setLast(user.getLast());
		this.setEmail(user.getEmail());
		this.setPw(user.getPw());
		this.setAddress(user.getAddress());
		this.setPw(user.getPw());
		this.setZipcode(user.getZipcode());
	}
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public float getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	
}
