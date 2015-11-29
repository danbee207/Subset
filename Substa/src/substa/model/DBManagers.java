package substa.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import substa.beans.BidHistory;
import substa.beans.Customer;
import substa.beans.Employer;
import substa.beans.Item;
import substa.beans.Pair;
import substa.beans.SalesRecord;
import substa.beans.SoldItemAuctionInfo;
import substa.beans.User;

public class DBManagers {

	private String dbURL = "";
	private String dbuser = "";
	private String dbpass = "";

	public DBManagers(){
		super();
	}
	
	public DBManagers(String url, String user, String pw) {
		dbURL = url;
		dbuser = user;
		dbpass = pw;
	}

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
	 * 
	 * @author Danbee Park
	 */
	public Connection getConnection() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(getDbURL(), getDbuser(), getDbpass());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * Func to close open connection to db
	 * 
	 * @author Danbee Park
	 */
	public void closeConnection(Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/*
	 * Fuct to get User info from db
	 * 
	 * @author Danbee Park
	 * 
	 */
	public User getUser(String email, String pw) {
		User user = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Person WHERE Email=? AND pw=? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, email);
				ps.setString(2, pw);
				rs = ps.executeQuery();
				while (rs.next()) {
					user = new User();
					user.setSsn(rs.getInt("SSN"));
					user.setFirst(rs.getString("FirstName"));
					user.setLast(rs.getString("LastName"));
					user.setAddress(rs.getString("Address"));
					user.setZipcode(rs.getInt("ZipCode"));
					user.setTelephone(rs.getLong("Telephone"));
					user.setEmail(rs.getString("Email"));
					user.setPw(rs.getString("pw"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return user;
	}

	public Customer getCustomer(User user) {
		Customer customer = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Customer WHERE CustomerID=?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, user.getSsn());
				rs = ps.executeQuery();
				while (rs.next()) {
					customer = new Customer(user);
					customer.setCreditCardNum(rs.getLong("CreditCardNum"));
					customer.setRating(rs.getFloat("Rating"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return customer;
	}

	public Employer getEmployer(User user) {
		Employer employee = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Employee WHERE EmployeeID=?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, user.getSsn());
				rs = ps.executeQuery();
				while (rs.next()) {
					employee = new Employer(user);
					employee.setLevel(rs.getInt("Level"));
					employee.setStartDate(rs.getDate("StartDate"));
					employee.setHourlyRate(rs.getFloat("HourlyRate"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return employee;
	}
	
	public boolean addUser(User user){
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Person(SSN, LastName, FirstName, Address, ZipCode, Telephone, Email, pw)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
					ps = conn.prepareStatement(sql);
					ps.setInt(1, user.getSsn());
					ps.setString(2, user.getLast());
					ps.setString(3, user.getFirst());
					ps.setString(4, user.getAddress());
					ps.setInt(5, user.getZipcode());
					ps.setLong(6, user.getTelephone());
					ps.setString(7, user.getEmail());
					ps.setString(8, user.getPw());
					return ps.execute();
					
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						ps.close();
						
					}catch(SQLException ex){
						ex.printStackTrace();
					}
					closeConnection(conn);
				}
			
		}
	
		return true;
	}
	
	public boolean addCustomer(User customer, long credit){
		
		addUser(customer);
		
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Customer (CustomerID, Rating, CreditCardNum)" + " VALUES (?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, customer.getSsn());
				ps.setInt(2, 0);
				ps.setLong(3, credit);
				
				return ps.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean addEmployer(User employer,int level, Timestamp start, float rate){
		
		addUser(employer);
		
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Employee (EmployeeID, Level, StartDate, HourlyRate) " + "VALUES (?, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, employer.getSsn());
				ps.setInt(2, level);
				ps.setTimestamp(3, start);
				ps.setFloat(4, rate);
				
				return ps.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return true;
	}

}