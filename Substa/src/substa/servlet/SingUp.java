package substa.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import substa.beans.Customer;
import substa.beans.User;
import substa.model.DBManager;
import substa.model.DBManagers;

/**
 * Servlet implementation class SingUp
 */
@WebServlet(
		urlPatterns = { "/SingUp" }, 
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark"), 
				@WebInitParam(name = "dbUser", value = "danpark"), 
				@WebInitParam(name = "dbPass", value = "110142214")
		})
public class SingUp extends HttpServlet {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession(true);
		
		response.setHeader("Cache-Control", "no-cache");
		
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires

		
		User customer = new User();
		customer.setFirst(request.getParameter("firstName"));
		customer.setLast(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("email"));
		customer.setPw(request.getParameter("password"));
		customer.setSsn(Integer.parseInt(request.getParameter("ssn")));
		
		String address = request.getParameter("street")+","+request.getParameter("city")+","+request.getParameter("state");
		customer.setAddress(address);
		
		customer.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		customer.setTelephone(Long.parseLong(request.getParameter("tele")));
		
		long credit = Long.parseLong(request.getParameter("card"));
		
		if(db.addCustomer(customer, credit)){
			
		}
			
		
		
		
		
	}
}
