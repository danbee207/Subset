package substa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import substa.beans.Customer;
import substa.beans.Employer;
import substa.beans.User;

public class DBManager {
/**
 * @author Danbee Park
 * 
 */

		
	private String dbURL ="";
	private String dbuser = "";
	private String dbpass="";
	public String getDbURL() {
		return dbURL;
	}
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	public String getDbpass() {
		return dbpass;
	}
	public void setDbpass(String dbpass) {
		this.dbpass = dbpass;
	}
		

	/*
	 * Func to open connection to the db
	 * @author Danbee Park
	 */
	
	public Connection getConnection(){
		
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection(getDbURL(), getDbuser(), getDbpass());
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return conn;
		
	}
	/*
	 * Func to close open connection to db
	 * @author Danbee Park
	 */
	public void closeConnection(Connection conn){
			
		if(conn!=null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			
		}
		
	}
	
	/*
	 * Fuct to get User info from db
	 * @author Danbee Park
	 * 
	 */
	public User getUser(String email,String pw){
		
		User user = null;
		System.out.println(this.getDbURL() + " " + this.getDbuser());
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps =null;
			ResultSet rs =null;
			
			try{
				
				String sqlQuery = "SELECT * FROM Person WHERE Email=? AND pw=? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, email);
				ps.setString(2, pw);
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					user = new User();
					user.setFirst(rs.getString("FirstName"));
					user.setLast(rs.getString("LastName"));
					user.setAddress(rs.getString("Address"));
					user.setZipcode(rs.getInt("ZipCode"));
					user.setTelephone(rs.getLong("Telephone"));
					user.setEmail(rs.getString("Email"));
					user.setPw(rs.getString("pw"));
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					rs.close();
					ps.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return user;
		
	}
	
	public Customer getCustomer(User user){
		
		Customer customer = null;
		
		Connection conn = getConnection();
		if(conn!=null){
			
			PreparedStatement ps =null;
			ResultSet rs =null;
			
			try{
				
				String sqlQuery = "SELECT * FROM Customer WHERE CustomerID=?";
				ps=conn.prepareStatement(sqlQuery);
				ps.setInt(1, user.getSsn());
				
				rs = ps.executeQuery();
				while(rs.next()){
					customer = (Customer) user;
					customer.setCreditCardNum(rs.getLong("CreditCardNum"));
					customer.setRating(rs.getFloat("Rating"));
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					rs.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
			
		}
		
		return customer;
		
	}
	
	public Employer getEmployer(User user){
		Employer employee = null;
		
		Connection conn = getConnection();
		if(conn!=null){
			
			PreparedStatement ps =null;
			ResultSet rs =null;
			
			try{
				
				String sqlQuery = "SELECT * FROM Employee WHERE EmployeeID=?";
				ps=conn.prepareStatement(sqlQuery);
				ps.setInt(1, user.getSsn());
				
				rs = ps.executeQuery();
				while(rs.next()){
					employee = (Employer) user;
					employee.setLevel(rs.getInt("Level"));
					employee.setStartDate(rs.getDate("StartDate"));
					employee.setHourlyRate(rs.getFloat("HourlyRate"));
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					rs.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
			
		}
		
		return employee;
	}

}
