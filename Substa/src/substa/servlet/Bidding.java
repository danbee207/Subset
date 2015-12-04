package substa.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import substa.beans.AuctionDetailInfo;
import substa.beans.BidHistory;
import substa.beans.Customer;
import substa.model.DBManagers;

/**
 * Servlet implementation class Bidding
 */
@WebServlet(
		urlPatterns = { "/Bidding" }, 
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark"), 
				@WebInitParam(name = "dbUser", value = "danpark"), 
				@WebInitParam(name = "dbPass", value = "110142214")
		})
public class Bidding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DBManagers db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();

		db = new DBManagers();
		db.setDbURL(config.getInitParameter("dbUrl"));
		db.setDbuser(config.getInitParameter("dbUser"));
		db.setDbpass(config.getInitParameter("dbPass"));

		try {
			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);

		response.setHeader("Cache-Control", "no-cache");

		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		float newMaxbid = Float.parseFloat(request.getParameter("futureBidding"));
		
		Customer customerInfo = (Customer)session.getAttribute("customerInfo");
		AuctionDetailInfo itemDetail = (AuctionDetailInfo)session.getAttribute("itemDetail");
		BidHistory aucHistory = db.getWinnersBid(itemDetail.getAuctionID());
		/*
		 * private int auctionID;
			private long CustomerID;
		private int itemID;
		private String itemName;
		private Float BidPrice;
		private Timestamp BidTime;
		private Float maxBid;
		private long winnerID;
		 * 
		 */

		float currentPrice = aucHistory.getBidPrice();

		BidHistory temp = new BidHistory();
		temp.setAuctionID(itemDetail.getAuctionID());
		temp.setCustomerID(customerInfo.getSsn());
		temp.setItemName(itemDetail.getItemName());
		if(currentPrice>0 && currentPrice<100){
			float newPrice = (float) (aucHistory.getBidPrice()+3.00);
			temp.setBidPrice(newPrice);
		}else if(currentPrice>=100 && currentPrice<500){
			float newPrice = (float) (aucHistory.getBidPrice()+5.00);
			temp.setBidPrice(newPrice);
		}else if(currentPrice>=500){
			float newPrice = (float) (aucHistory.getBidPrice()+10.00);
			temp.setBidPrice(newPrice);
		}
		
		temp.setWinnerID(customerInfo.getSsn());
		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();
		 
		// 2) get a java.util.Date from the calendar instance.
//		    this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();
		 
		// 3) a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		temp.setBidTime(currentTimestamp );
		
		if(newMaxbid==-1){				//not new maxbid
			float ownMax = db.getOwnBidMax(itemDetail.getAuctionID(), customerInfo.getSsn());
			temp.setMaxBid(ownMax);
				
				
			
		}else{							//new maxbid
			temp.setMaxBid(newMaxbid);
		}
		
		db.addBid(temp);
	}
	

}
