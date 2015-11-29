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
import substa.beans.SalesRecord;
import substa.beans.SoldItemAuctionInfo;
import substa.beans.User;

public class DBManager {

	private String dbURL = "";
	private String dbuser = "";
	private String dbpass = "";

	public DBManager(){
		super();
	}
	
	public DBManager(String url, String user, String pw) {
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
/*
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

	/**
	 * Insert a new tuple into the person table.
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean insertIntoPerson(int ssn, String lastname, String firstname, String address, int zipcode,
			long telephone, String email, String password) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Person(SSN, LastName, FirstName, Address, ZipCode, Telephone, Email, pw)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ssn);
				ps.setString(2, lastname);
				ps.setString(3, firstname);
				ps.setString(4, address);
				ps.setInt(5, zipcode);
				ps.setLong(6, telephone);
				ps.setString(7, email);
				ps.setString(8, password);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * update a person's name with SSN = ssn
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean updatePersonName(int ssn, String lastname, String firstname) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "UPDATE Person " + "SET LastName = ?, FirstName = ?" + "WHERE SSN = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(3, ssn);
				ps.setString(1, lastname);
				ps.setString(2, firstname);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * update a person's address and phone number with SSN = ssn.
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean updateAddressPhone(int ssn, String address, int zipcode, long telephone) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "UPDATE Person " + "SET Address = ?, ZipCode = ?, Telephone = ? " + "WHERE SSN = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(4, ssn);
				ps.setString(1, address);
				ps.setInt(2, zipcode);
				ps.setLong(3, telephone);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * update a person's email and password with SSN = ssn.
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean updateAccountInfo(int ssn, String email, String password) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "UPDATE Person " + "SET Email = ?, pw = ? " + "WHERE SSN = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(3, ssn);
				ps.setString(1, email);
				ps.setString(2, password);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * delete an employee from the database completely
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean deleteEmpFromPerson(int ssn) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "DELETE FROM Person WHERE SSN = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ssn);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Insert a new tuple into the Employee table.
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean insertIntoEmployee(int id, int level, Timestamp date, BigDecimal rate) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Employee (EmployeeID, Level, StartDate, HourlyRate) " + "VALUES (?, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setInt(2, level);
				ps.setTimestamp(3, date);
				ps.setBigDecimal(4, rate);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Update the level of an employee with EmployeeID = id
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean UpdateEmployeeLevel(int id, int level) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "UPDATE Employee " + "SET Level = ? " + "WHERE EmployeeID = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(2, id);
				ps.setInt(1, level);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Update the hourly rate of an employee with EmployeeID = id
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean UpdateEmployeeRate(int id, BigDecimal rate) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "UPDATE Employee " + "SET HourlyRate  = ? " + "WHERE EmployeeID = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(2, id);
				ps.setBigDecimal(1, rate);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Insert a new tuple into the Customer table.
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean insertIntoCustomer(int id, int rating, long cc) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Customer (CustomerID, Rating, CreditCardNum)" + " VALUES (?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setInt(2, rating);
				ps.setLong(3, cc);
				ps.executeUpdate();
				success = true;
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
		return success;
	}
	
	/**
	 * update a customer's info
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean updateCustomer(int id, int rating, long cc) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "UPDATE Customer SET Rating = ?, CreditCardNum = ?"
						+ " WHERE CustomerID = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(3, id);
				ps.setInt(1, rating);
				ps.setLong(2, cc);
				ps.executeUpdate();
				success = true;
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
		return success;
	}
	
	/**
	 * delete an customer from the database completely
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean deleteCustFromPerson(int ssn) {
		return deleteEmpFromPerson(ssn);
	}
	
	
	

	/**
	 * Insert a new tuple into the Item table.
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean insertIntoItem(String name, String type, int num, String descr) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Item(ItemName,ItemType, NumCopies, Description)" + " VALUES (?, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, type);
				ps.setInt(3, num);
				ps.setString(4, descr);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Insert a new tuple into the Post table.
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean insertIntoPost(int cus_id, int auc_id, Timestamp exp_date, Timestamp post_date,
			BigDecimal res_price) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Post(CustomerID,AuctionID,ExpireDate," + " PostDate,ReservedPrice)"
					+ " VALUES (?, ?, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cus_id);
				ps.setInt(2, auc_id);
				ps.setTimestamp(3, exp_date);
				ps.setTimestamp(4, post_date);
				ps.setBigDecimal(5, res_price);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Insert a new tuple into the Auction table.
	 * 
	 * @Return true if successful, false otherwise.
	 */
	public boolean insertIntoAuction(BigDecimal bid_incre, BigDecimal min_bid, int copy, int monitor, int item_id) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Auction(BidIncrement,MinimumBid,Copies_Sold,Monitor,ItemID)"
					+ " VALUES (?, ?, ?, ?,?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setBigDecimal(1, bid_incre);
				ps.setBigDecimal(2, min_bid);
				ps.setInt(3, copy);
				ps.setInt(4, monitor);
				ps.setInt(5, item_id);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/**
	 * Insert a new tuple into the Bid table.
	 * 
	 * @Returns true if successful, false otherwise.
	 */
	public boolean insertIntoBid(int cus_id, int auc_id, int item_id, Timestamp bid_time, BigDecimal max_bid,
			BigDecimal bid_price) {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Bid(CustomerID,AuctionID,ItemID,BidTime,MaximumBid,"
					+ "BidPrice) VALUES (?, ?, ?, ?,?,?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cus_id);
				ps.setInt(2, auc_id);
				ps.setInt(3, item_id);
				ps.setTimestamp(4, bid_time);
				ps.setBigDecimal(5, max_bid);
				ps.setBigDecimal(6, bid_price);
				ps.executeUpdate();
				success = true;
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
		return success;
	}

	/*
	 * a listing of all the sales for a particular month, 
	 * with month and year in integer as parameter.
	 * 
	 * @Returns a list of Sales object; Null if query unsuccessful.
	 *  Note: The list might be empty
	 */
	public List<SalesRecord> getSalesReport(int year, int month) {
		SalesRecord sr = null;
		List<SalesRecord> salesList = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * "
								+ " FROM Sales S"
								+ " WHERE YEAR(S.Date) = ? AND MONTH(S.Date) = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, year);
				ps.setInt(2, month);
				rs = ps.executeQuery();
				salesList = new ArrayList<SalesRecord>();
				while (rs.next()) {
					sr = new SalesRecord();
					sr.setBuyerID(rs.getInt("BuyerID"));
					sr.setSellerID(rs.getInt("SellerID"));
					sr.setPrice(rs.getBigDecimal("Price"));
					sr.setDate(rs.getTimestamp("Date"));
					sr.setAuctionID(rs.getInt("AuctionID"));
					salesList.add(sr);
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
		return salesList;
	}
	
	/*
	 * Produce a list of sales by item name
	 * 
	 * @Returns a list of Sales object; Null if query unsuccessful.
	 *  Note: The list might be empty
	 */
	public List<SalesRecord> getSalesByItemName(String item_name) {
		SalesRecord sr = null;
		List<SalesRecord> salesList = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT S.SellerID, S.BuyerID, S.Price, S.Date, A.AuctionID"
								+ " FROM Sales S,Auction A,Item I"
								+ " WHERE S.AuctionID = A.AuctionID AND A.ItemID= I.ItemID"
										+ " AND  I.ItemName = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, item_name);
				rs = ps.executeQuery();
				salesList = new ArrayList<SalesRecord>();
				while (rs.next()) {
					sr = new SalesRecord();
					sr.setBuyerID(rs.getInt("BuyerID"));
					sr.setSellerID(rs.getInt("SellerID"));
					sr.setPrice(rs.getBigDecimal("Price"));
					sr.setDate(rs.getTimestamp("Date"));
					sr.setAuctionID(rs.getInt("AuctionID"));
					salesList.add(sr);
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
		return salesList;
	}
	
	/*
	 * Produce a list of sales by seller name
	 * 
	 * @Returns a list of Sales object; Null if query unsuccessful.
	 *  Note: The list might be empty
	 */
	public List<SalesRecord> getSalesBySellerName(String fname, String lname) {
		SalesRecord sr = null;
		List<SalesRecord> salesList = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT A.SellerID, A.BuyerID, A.Price, A.Date, A.AuctionID"
								+ " FROM Sales A, Person P"
								+ " WHERE A.SellerID = P.SSN AND P.FirstName = ?"
										+ " AND P.LastName = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, fname);
				ps.setString(2, lname);
				rs = ps.executeQuery();
				salesList = new ArrayList<SalesRecord>();
				while (rs.next()) {
					sr = new SalesRecord();
					sr.setBuyerID(rs.getInt("BuyerID"));
					sr.setSellerID(rs.getInt("SellerID"));
					sr.setPrice(rs.getBigDecimal("Price"));
					sr.setDate(rs.getTimestamp("Date"));
					sr.setAuctionID(rs.getInt("AuctionID"));
					salesList.add(sr);
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
		return salesList;
	}
	
	
	/*
	 * Produce a list of sales by BUYER name
	 * 
	 * @Returns a list of Sales object; Null if query unsuccessful.
	 *  Note: The list might be empty
	 */
	public List<SalesRecord> getSalesByBuyerName(String fname, String lname) {
		SalesRecord sr = null;
		List<SalesRecord> salesList = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT A.SellerID, A.BuyerID, A.Price, A.Date, A.AuctionID"
								+ " FROM Sales A, Person P"
								+ " WHERE A.BuyerID = P.SSN AND P.FirstName = ?"
										+ " AND P.LastName = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, fname);
				ps.setString(2, lname);
				rs = ps.executeQuery();
				salesList = new ArrayList<SalesRecord>();
				while (rs.next()) {
					sr = new SalesRecord();
					sr.setBuyerID(rs.getInt("BuyerID"));
					sr.setSellerID(rs.getInt("SellerID"));
					sr.setPrice(rs.getBigDecimal("Price"));
					sr.setDate(rs.getTimestamp("Date"));
					sr.setAuctionID(rs.getInt("AuctionID"));
					salesList.add(sr);
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
		return salesList;
	}
	
	
	/*
	 * Produce a list of sales by a customer name, either as a buyer or a seller
	 * 
	 * @Returns a list of Sales object; Null if query unsuccessful.
	 *  Note: The list might be empty
	 */
	public List<SalesRecord> getSalesByCustomerName(String fname, String lname) {
		SalesRecord sr = null;
		List<SalesRecord> salesList = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT A.SellerID, A.BuyerID, A.Price, A.Date, A.AuctionID"
								+ " FROM Sales A, Person P"
								+ " WHERE (A.BuyerID = P.SSN  OR A.SellerID = P.SSN)"
										+ " AND P.FirstName = ? AND P.LastName = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, fname);
				ps.setString(2, lname);
				rs = ps.executeQuery();
				salesList = new ArrayList<SalesRecord>();
				while (rs.next()) {
					sr = new SalesRecord();
					sr.setBuyerID(rs.getInt("BuyerID"));
					sr.setSellerID(rs.getInt("SellerID"));
					sr.setPrice(rs.getBigDecimal("Price"));
					sr.setDate(rs.getTimestamp("Date"));
					sr.setAuctionID(rs.getInt("AuctionID"));
					salesList.add(sr);
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
		return salesList;
	}
	
	/* gets the revenue generated and number of sales in a particular month
	 * with month and year in integer as parameter.
	 * 
	 * @Returns a Pair of Sales object <revenue, number of sales>; Null if query unsuccessful.
	 *  Note: Pair might contain no elements
	 *//*
	public Pair getSalesSummary(int year, int month) {
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT MONTH(S.Date), COUNT(*) AS numberOfSales, "
													+ "SUM(Price) AS totalRevenue "
								+ "FROM Sales S "
								+ "WHERE YEAR(S.Date) = ? AND MONTH(S.Date) = ?"
								+ "GROUP BY MONTH(S.Date)";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, year);
				ps.setInt(2, month);
				rs = ps.executeQuery();
				p = new Pair();
				while (rs.next()) {
					p.setFirst(rs.getBigDecimal("totalRevenue"));
					p.setSecond(rs.getInt("numberOfSales"));
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
		return p;
	}
	
	/* Produce a summary listing of revenue generated by a particular item.
	 * 
	 * @Returns a Pair of Sales object <revenue, number of sales>; Null if query unsuccessful.
	 *  Note: Pair might contain no elements
	 */
	/*
	public Pair getSalesSummaryByItemID(int itemid) {
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT SUM(S.Price) AS totRev, COUNT(*) AS numSal"
								+ "FROM Sales S, Auction A "
								+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID = ? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, itemid);
				rs = ps.executeQuery();
				p = new Pair();
				while (rs.next()) {
					p.setFirst(rs.getBigDecimal("totRev"));
					p.setSecond(rs.getInt("numSal"));
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
		return p;
	}
	
	/* Produce a summary listing of revenue generated by a particular item type.
	 * 
	 * @Returns a Pair of Sales object <revenue, number of sales>; Null if query unsuccessful.
	 *  Note: Pair might contain no elements
	 *//*
	public Pair getSalesSummaryByItemType(String itemtype) {
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = " SELECT SUM(S.Price) AS totRev, COUNT(*) AS numSal"
								+ " FROM Sales S, Auction A"
								+ " WHERE S.AuctionID = A.AuctionID AND A.ItemID IN "
										+ "(  SELECT I.ItemID "
											+ "FROM Item I "
											+ "WHERE I.ItemType = ?)";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, itemtype);
				rs = ps.executeQuery();
				p = new Pair();
				while (rs.next()) {
					p.setFirst(rs.getBigDecimal("totRev"));
					p.setSecond(rs.getInt("numSal"));
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
		return p;
	}
	
	
	/* Produce a summary listing of revenue generated by a particular seller.
	 * 
	 * @Returns a Pair of Sales object <revenue, number of sales>; Null if query unsuccessful.
	 *  Note: Pair might contain no elements
	 *//*
	public Pair getSalesSummaryBySellerID(int seller_id) {
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = " SELECT SUM(S.Price) AS totRev, COUNT(*) AS numSal"
								+ " FROM Sales S"
								+ " WHERE S.SellerID = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, seller_id);
				rs = ps.executeQuery();
				p = new Pair();
				while (rs.next()) {
					p.setFirst(rs.getBigDecimal("totRev"));
					p.setSecond(rs.getInt("numSal"));
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
		return p;
	}
	
	
	/* Produce a summary listing of revenue generated by a particular buyer.
	 * 
	 * @Returns a Pair of Sales object <revenue, number of sales>; Null if query unsuccessful.
	 *  Note: Pair might contain no elements
	 *//*
	public Pair getSalesSummaryByBuyerID(int buyer_id) {
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery =  " SELECT SUM(S.Price) AS totRev, COUNT(*) AS numSal"
								+ " FROM Sales S"
								+ " WHERE S.BuyerID = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, buyer_id);
				rs = ps.executeQuery();
				p = new Pair();
				while (rs.next()) {
					p.setFirst(rs.getBigDecimal("totRev"));
					p.setSecond(rs.getInt("numSal"));
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
		return p;
	}

	/* Produce a comprehensive listing of all items
	 * 
	 * @Returns a list of Item object; Null if query unsuccessful.
	 *  Note: the list might be empty
	 *  Note 2: the amountSold field of all Item objects returned is null
	 */
	public List<Item> getAllItems() {
		List<Item> itemList = null;
		Item i = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Item";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				itemList = new ArrayList<Item>();
				while (rs.next()) {
					i.setItemID(rs.getInt("ItemID"));
					i.setItemName(rs.getString("ItemName"));
					i.setItemType(rs.getString("ItemType"));
					i.setNumCopies(rs.getInt("NumCopies"));
					i.setDescription(rs.getString("Description"));
					itemList.add(i);
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
		return itemList;
	}
	
	
	/* Determine which customer representative generated most total revenue
	 * 
	 * @Returns a list of Pair object <employeeID, revenue> ; Null if query unsuccessful.
	 *  Note: the list might be empty
	 *//*
	public List<Pair> getHighestRevEmpl() {
		List<Pair> list = null;
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "CREATE VIEW RepTotalRev(EmployeeID, TotalRev) AS"
							+ " SELECT A.Monitor, SUM(S.Price)"
							+ " FROM Sales S, Auction A"
							+ " WHERE A.AuctionID = S.AuctionID"
							+ " GROUP BY A.Monitor;"
							+ " SELECT R.EmployeeID, R.TotalRev"
							+ " FROM RepTotalRev R"
							+ " WHERE R.TotalRev = (SELECT MAX(R2.TotalRev)"
												+ " FROM RepTotalRev R2 )";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				list = new ArrayList<Pair>();
				while (rs.next()) {
					p.setFirst(rs.getInt("EmployeeID"));
					p.setSecond(rs.getBigDecimal("TotalRev"));
					list.add(p);
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
		return list;
	}
	
	
	/* Determine which customer generated most total revenue
	 * @Returns a list of Pair object <customerID, revenue> ; Null if query unsuccessful.
	 *  Note: the list might be empty
	 *//*
	public List<Pair> getHighestRevCust() {
		List<Pair> list = null;
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "CREATE VIEW SellerTotalRev(SellerID, TotalRev) AS"
							+ " SELECT S.SellerID, SUM(S.Price)"
							+ " FROM Sales S"
							+ " GROUP BY S.SellerID;"
							+ "	SELECT S.SellerID, S.TotalRev"
							+ " FROM SellerTotalRev S"
							+ " WHERE S.TotalRev = (SELECT MAX(S2.TotalRev)"
												+ " FROM SellerTotalRev S2)";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				list = new ArrayList<Pair>();
				while (rs.next()) {
					p = new Pair();
					p.setFirst(rs.getInt("SellerID"));
					p.setSecond(rs.getBigDecimal("TotalRev"));
					list.add(p);
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
		return list;
	}
	
	
	/* Produce a Best-Sellers list of items
	 * @Returns a list of Item objects, up to 50 items; Null if query unsuccessful.
	 *  Note: the list might be empty
	 *//*
	public List<Item> getBestSellingItems() {
		List<Item> list = null;
		Item i = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT A.ItemID, I.ItemName, I.ItemType,"
									+ " I.NumCopies, I.Description, COUNT(*) AS amountSold"
						+ " FROM Sales S, Auction A, Item I"
						+ " WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID"
						+ " GROUP BY A.ItemID"
						+ " ORDER BY COUNT(*) DESC"
						+ " LIMIT 50";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				list = new ArrayList<Item>();
				while (rs.next()) {
					i.setItemID(rs.getInt("ItemID"));
					i.setItemName(rs.getString("ItemName"));
					i.setItemType(rs.getString("ItemType"));
					i.setNumCopies(rs.getInt("NumCopies"));
					i.setDescription(rs.getString("Description"));
					i.setAmountSold(rs.getInt("amountSold"));
					
					list.add(i);
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
		return list;
	}
	
	
	
	/* Insert all the auction tuples that has ended but not yet recorded in the sales table
	 * @Returns a list of Item objects, up to 50 items; Null if query unsuccessful.
	 *  Note: the list might be empty
	 */
	public Boolean insertIntoSales() {
		Boolean success = false;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "INSERT INTO Sales(BuyerID, SellerID, Price, Date, AuctionID)"
								+ " SELECT B.CustomerID, P.CustomerID, "
											+ "MAX(B.BidPrice), P.ExpireDate, A.AuctionID"
								+ " FROM Post P, Bid B, Auction A "
								+ " WHERE P.ExpireDate < NOW() AND P.AuctionID = B.AuctionID AND"
								+ " P.AuctionID = A.AuctionID AND B.BidPrice >= P.ReservedPrice "
									+ "AND P.AuctionID NOT IN (SELECT AuctionID FROM Sales)"
								+ "GROUP BY B.AuctionID";
				ps = conn.prepareStatement(sqlQuery);
				ps.executeUpdate();
				success = true;
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
		return success;
	}
	
	
	/* Produce customer mailing lists
	 * 
	 * @Returns a list of MailingListHelper object; Null if query unsuccessful.
	 *  Note: the list might be empty
	 *//*
	public List<MailingListHelper> getMallingList() {
		List<MailingListHelper> list = null;
		MailingListHelper m = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT P.FirstName, P.LastName, P.Email"
						+ " FROM Person P"
						+ " WHERE EXISTS ("
								+ "SELECT CustomerID  "
								+ "FROM Customer C"
								+ " WHERE C.CustomerID = P.SSN)";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				list = new ArrayList<MailingListHelper>();
				while (rs.next()) {
					m.setFirstName(rs.getString("FirstName"));
					m.setLastName(rs.getString("LastName"));
					m.setEmail(rs.getString("Email"));
					list.add(m);
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
		return list;
	}
	
	*/
	/* Produce a list of item suggestions for a given customer 
	 * (based on that customer's past purchases)
	 * 
	 * @Returns a list of Item object; Null if query unsuccessful.
	 *  Note: the list might be empty
	 *  Note 2: the amountSold field of all Item objects returned is null
	 */
	
	public List<Item> getSuggestions(int id) {
		List<Item> itemList = null;
		Item i = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "CREATE VIEW CustomersWhoBoughtSimilarItems(CustomerID) AS"
						+ " SELECT S.BuyerID "
						+ "FROM Sales S, Auction A "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID IN ("
						+ " SELECT A.ItemID"
						+ " FROM Auction A, Sales S "
						+ "WHERE S.AuctionID = A.AuctionID AND S.BuyerID = ?);"
						+ "SELECT A.ItemID, I.ItemName, I.ItemType, "
							+ "I.Description, I.NumCopies"
						+ " FROM Sales S, Auction A, Item I "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID"
						+ " AND S.BuyerID IN ( Select * FROM CustomersWhoBoughtSimilarItems)"
						+ " GROUP BY A.ItemID"
						+ " HAVING I.NumCopies > 0"
						+ " ORDER BY COUNT(A.ItemID)"
						+ " LIMIT 20";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				itemList = new ArrayList<Item>();
				while (rs.next()) {
					i.setItemID(rs.getInt("ItemID"));
					i.setItemName(rs.getString("ItemName"));
					i.setItemType(rs.getString("ItemType"));
					i.setNumCopies(rs.getInt("NumCopies"));
					i.setDescription(rs.getString("Description"));
					itemList.add(i);
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
		return itemList;
	}
	
	
	/* A bid history for each auction

	 * 
	 * @Returns a list of BidHistory object; Null if query unsuccessful.
	 *  Note: the list might be empty
	 * 
	 * */
	/*
	public List<BidHistory> getAllBidHistory() {
		List<BidHistory> list = null;
		BidHistory b = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT A.AuctionID, B.CustomerID, B.BidPrice, B.BidTime"
						+ " FROM Bid B, Auction A"
						+ " WHERE B.AuctionID = A.AuctionID"
						+ " ORDER BY A.AuctionID ASC, B.BidPrice ASC";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				list = new ArrayList<BidHistory>();
				while (rs.next()) {
					b.setAuctionID(rs.getInt("AuctionID"));
					b.setCustomerID(rs.getInt("CustomerID"));
					b.setBidPrice(rs.getBigDecimal("BidPrice"));
					b.setBidTime(rs.getTimestamp("BidTime"));
					list.add(b);
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
		return list;
	}*/
	
	
	/* A history of all current and past auctions a customer has taken part in
	 * 
	 * @Returns a list of auction ids; Null if query unsuccessful.
	 *  Note: the list might be empty
	 * 
	 * */
	/*
	public List<Integer> getAuctionHistory(int id) {
		List<Integer> list = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT B.AuctionID"
						+ " FROM Bid B WHERE B.CustomerID = ?"
						+ " GROUP BY B.AuctionID ORDER BY AuctionID DESC";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				list = new ArrayList<Integer>();
				while (rs.next()) {
					list.add(rs.getInt("AuctionID"));
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
		return list;
	}
	*/
	
	/* Items sold by a given seller and corresponding auction info
	 * 
	 * @Returns a list of auction ids; Null if query unsuccessful.
	 *  Note: the list might be empty
	 * 
	 * */
	/*
	public List<SoldItemAuctionInfo> getSoldItemAuctionInfo(int id) {
		List<SoldItemAuctionInfo> list = null;
		SoldItemAuctionInfo i = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT I.ItemID, I.ItemName, S.AuctionID,"
						+ " P.PostDate, P.ExpireDate"
						+ " FROM Item I, Sales S, Post P, Auction A"
						+ " WHERE P.CustomerID = ? AND P.AuctionID = S.AuctionID"
						+ " AND A.AuctionID = P.auctionID AND A.ItemID = I.ItemID";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				list = new ArrayList<SoldItemAuctionInfo>();
				while (rs.next()) {
					i = new SoldItemAuctionInfo();
					i.setAuctionID(rs.getInt("AuctionID"));
					i.setExpireDate(rs.getTimestamp("ExpireDate"));
					i.setItemID(rs.getInt("ItemID"));
					i.setItemName(rs.getString("ItemName"));
					i.setPostDate(rs.getTimestamp("PostDate"));
					list.add(i);
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
		return list;
	}
	*/
	
	
	/* Items available of a particular type and corresponding auction info
	 * @Returns a list of Pair object <ItemName, auctionID> ; Null if query unsuccessful.
	 *  Note: the list might be empty
	 */
	/*
	public List<Pair> getAuctionByItemType(String type) {
		List<Pair> list = null;
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT I.ItemName, A.AuctionID"
						+ " FROM Item I, Auction A, Post P"
						+ " WHERE P.ExpireDate > NOW() AND P.AuctionID = A.AuctionID AND "
						+ " I.ItemType = ? AND I.ItemID = A.ItemID";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, type);
				rs = ps.executeQuery();
				list = new ArrayList<Pair>();
				while (rs.next()) {
					p = new Pair();
					p.setFirst(rs.getInt("ItemName"));
					p.setSecond(rs.getBigDecimal("AuctionID"));
					list.add(p);
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
		return list;
	}*/
	
	/* Items available with a particular keyword or set of keywords in the item name,
	 *  and corresponding auction info
	 * @Returns a list of Pair object <ItemName, auctionID> ; Null if query unsuccessful.
	 *  Note: the list might be empty
	 */
	/*
	public List<Pair> getItemsByKeyword(String keyword) {
		List<Pair> list = null;
		Pair p = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT I.ItemName, A.AuctionID"
						+ " FROM Item I, Auction A, Post P"
						+ " WHERE P.ExpireDate > NOW() AND P.AuctionID = A.AuctionID"
						+ " AND I.ItemName LIKE �%?%� AND I.ItemID = A.ItemID";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, keyword);
				rs = ps.executeQuery();
				list = new ArrayList<Pair>();
				while (rs.next()) {
					p = new Pair();
					p.setFirst(rs.getInt("ItemName"));
					p.setSecond(rs.getBigDecimal("AuctionID"));
					list.add(p);
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
		return list;
	}
	
	
	

	
*/	

}
