package substa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import substa.beans.AuctionDetailInfo;
import substa.beans.Customer;
import substa.model.DBManagers;

/**
 * Servlet implementation class checkBidMax
 */

@WebServlet(
		urlPatterns = { "/checkBidMax" }, 
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark"), 
				@WebInitParam(name = "dbUser", value = "danpark"), 
				@WebInitParam(name = "dbPass", value = "110142214")
		})
public class checkBidMax extends HttpServlet {
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
		processReqeust(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processReqeust(request,response);
	}

	private void processReqeust(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub6
		
		HttpSession session = request.getSession(true);

		response.setHeader("Cache-Control", "no-cache");

		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		Customer customer = (Customer)session.getAttribute("customerInfo");
		AuctionDetailInfo itemDetail = (AuctionDetailInfo)session.getAttribute("itemDetail");
		
		float value = -1;
		value = db.getOwnBidMax(itemDetail.getAuctionID(), customer.getSsn());
		
		JSONObject json = new JSONObject();
		
		
		float valueMax = db.getbid;
		float currentValue; 
		
		json.put("RevenueValue", value);
		json.put("valueMax", valueMax);
		json.put("currentValue", currentValue);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
