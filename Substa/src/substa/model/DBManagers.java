package substa.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import substa.beans.Auction;
import substa.beans.Customer;
import substa.beans.Employer;
import substa.beans.User;
import substa.beans.Item;
import substa.beans.Post;

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
					employee.setStartDate((rs.getTimestamp("StartDate")));
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
		
		System.out.println("adUser : " + addUser(employer));
		
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
	
	public boolean addItem(Item item) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Item(ItemName, ItemType, NumCopies, Description, img)"
						+ " VALUES (?, ?, ?, ?, ?)";
			
				ps = conn.prepareStatement(sql);
				ps.setString(1, item.getItemName());
				ps.setString(2, item.getItemType());
				ps.setInt(3, item.getNumCopies());
				ps.setString(4, item.getDescription());
				ps.setString(5, item.getImgsrc());
				
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
	
	public int getLatestItemID() {
		
		int latestItemID = 0;
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql = "SELECT MAX(ItemID) AS ItemID"
						+ "FROM Item";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					latestItemID = rs.getInt("ItemID");
				}
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
		
		return latestItemID;
	}
	
	public int getManagerID() {
		
		int ManagerID = 0;
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql = "SELECT EmployeeID"
						+ "FROM Employee"
						+ "WHERE Level = 2";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					ManagerID = rs.getInt("EmployeeID");
				}
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
		
		return ManagerID;
	}
	
	public boolean addAuction(Auction auction) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Auction(BidIncrement, MinimumBid, Copies_Sold, Monitor, ItemId)"
						+ " VALUES (?, ?, ?, ?, ?)";
			
				ps = conn.prepareStatement(sql);
				ps.setFloat(1, auction.getBidInc());
				ps.setFloat(2, auction.getMinBid());
				ps.setInt(3, auction.getCopy());
				ps.setInt(4, auction.getMornitor());
				ps.setInt(5, auction.getItemId());
				
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
	
	public int getLatesetAuctionID() {
		
		int latestAuctionID = 0;
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql = "SELECT MAX(AuctionID) AS AuctionID"
						+ "FROM Auction";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					latestAuctionID = rs.getInt("AuctionID");
				}
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
		
		return latestAuctionID;
	}
	
	public boolean addPost(Post post) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Post(CustomerID, AuctionID, ExpireDate, PostDate, ReservedPrice)"
						+ " VALUES (?, ?, ?, ?, ?)";
			
				ps = conn.prepareStatement(sql);
				ps.setInt(1, post.getCusId());
				ps.setInt(2, post.getAucId());
				ps.setTimestamp(3, post.getEndDate());
				ps.setTimestamp(4, post.getStartDate());
				ps.setFloat(5, post.getPrice());
				
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

	public Item getItem(int itemID) {
		
		
		return null;
	}
	
	public ArrayList<Item> getBestSellers() {
		ArrayList<Item> bestSellers = new ArrayList<Item>();
		Item item = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT A.ItemID "
						+ "FROM Sales S, Auction A "
						+ "WHERE A.AuctionID = S.AuctionID"
						+ "GRUOP BY A.ItemID"
						+ "ORDER BY COUNT(*) DESC"
						+ "LIMIT 3";
				ps1 = conn.prepareStatement(sqlQuery1);
				rs1 = ps1.executeQuery();
				while(rs1.next()) {
					
					String sqlQuery2 = "SELECT * FROM Item WHERE ItemID = ?";
					ps2 = conn.prepareStatement(sqlQuery2);
					ps2.setInt(1, rs1.getInt("ItemID"));
					rs2 = ps2.executeQuery();
					
					item = new Item();
					item.setItemID(rs2.getInt("ItemID"));
					item.setItemName(rs2.getString("ItemName"));
					item.setItemType(rs2.getString("ItemType"));
					item.setNumCopies(rs2.getInt("NumCopies"));
					item.setDescription(rs2.getString("Description"));
					item.setImgsrc(rs2.getString("img"));
					
					bestSellers.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					rs1.close();
					ps2.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return bestSellers;
	}
	
}