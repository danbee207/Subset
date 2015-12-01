package substa.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import substa.beans.User;
import substa.model.DBManagers;

/**
 * Servlet implementation class EmployeeManagement
 */

@WebServlet(urlPatterns = { "/EmployeeManagement" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu/danpark"),
		@WebInitParam(name = "dbUser", value = "danpark"), @WebInitParam(name = "dbPass", value = "110142214") })
public class EmployeeManagement extends HttpServlet {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRuqest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRuqest(request, response);
	}

	protected void processRuqest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);

		response.setHeader("Cache-Control", "no-cache");

		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		int type = Integer.parseInt(request.getParameter("ShowDetail"));
		if(type ==-1){ //add
			User user = new User();
			user.setFirst(request.getParameter("FirstName"));
			user.setLast(request.getParameter("lastName"));
			user.setEmail(request.getParameter("email"));
			user.setPw(request.getParameter("password"));
			
			String address = request.getParameter("street")+","+request.getParameter("city")+","+request.getParameter("state");
			user.setAddress(address);
			user.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
			
			db.addUser(user);
			int level = Integer.parseInt(request.getParameter("level"));
			float money = Float.parseFloat(request.getParameter("hourR"));
			Timestamp start = Timestamp.valueOf(request.getParameter("startDate"));
			
			db.addEmployer(user, level, start, money);
			
			
		}else if(type==-2){  //delete
			
		}else{				//edit
			
		}

	}

}
