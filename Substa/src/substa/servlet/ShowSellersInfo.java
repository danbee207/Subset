package substa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import substa.beans.AuctionDetailInfo;
import substa.beans.Customer;
import substa.model.DBManagers;

/**
 * Servlet implementation class ShowSellersInfo
 */

@WebServlet(
		urlPatterns = { "/ShowSellersInfo" }, 
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark"), 
				@WebInitParam(name = "dbUser", value = "danpark"), 
				@WebInitParam(name = "dbPass", value = "110142214")
		})
public class ShowSellersInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DBManagers db;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException{
		super.init();
		
		db= new DBManagers();
		db.setDbURL(config.getInitParameter("dbUrl"));
		db.setDbuser(config.getInitParameter("dbUser"));
		db.setDbpass(config.getInitParameter("dbPass"));
		
		try{
			Class.forName(config.getInitParameter("jdbcDriver"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		progressRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		progressRequest(request,response);
	}

	private void progressRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		response.setHeader("Cache-Control", "no-cache");
		
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		int bestSellerNum = Integer.parseInt(request.getParameter("cusId"));
		
		Customer cus = db.getCustomer(bestSellerNum);
		session.setAttribute("bestSellerInfo",cus );
		ArrayList<AuctionDetailInfo> AucListfromBestSeller = db.getAuctionsBySellerID(bestSellerNum);
		session.setAttribute("AuctListofBestSeller", AucListfromBestSeller);
		
		gotoSellerInfo(response);
	}
	
	protected void gotoSellerInfo(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'>");
		out.println("location.href='SellersInfoDetail.jsp';");
		out.println("</script>");
	}

	
}
