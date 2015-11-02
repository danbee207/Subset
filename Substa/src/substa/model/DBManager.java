package substa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}
