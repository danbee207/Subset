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

import substa.beans.Customer;
import substa.beans.User;
import substa.model.DBManagers;

/**
 * Servlet implementation class Mysetting
 */

@WebServlet(
		urlPatterns = {"/Mysetting"},
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark"), 
				@WebInitParam(name = "dbUser", value = "danpark"), 
				@WebInitParam(name = "dbPass", value = "110142214")
		})
public class Mysetting extends HttpServlet {
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
		processRequest(request,response);
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

		String type = request.getParameter("type");
		
		Customer customer = new Customer();
		customer.setFirst(request.getParameter("firstName"));
		customer.setLast(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("email"));
		customer.setPw(request.getParameter("password"));
		customer.setSsn(Long.parseLong(request.getParameter("ssn")));
		
		String address = request.getParameter("street")+","+request.getParameter("city")+","+request.getParameter("state");
		customer.setAddress(address);
		
		customer.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		customer.setTelephone(Long.parseLong(request.getParameter("tele")));
		
		long credit;
		if(request.getParameter("card")!="")
			credit = Long.parseLong(request.getParameter("card"));
		else
			credit =0 ;
		
		
		
		
		if(type.equals("0")){				//fix it
			//db
			db.changeCustomer(customer);
		}else{								//remove it
			db.deleteCustomer(customer.getSsn());
			//db 

			session.invalidate();
			response.sendRedirect("");
		}
		
		gotoIndex(response);
	
	}
	protected void gotoIndex(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'>");
		out.println("location.href='main.jsp';");
		out.println("</script>");
	}
}
