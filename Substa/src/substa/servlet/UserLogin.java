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
import substa.beans.Employer;
import substa.beans.User;
import substa.model.DBManager;

/**
 * Servlet implementation class UserLogin
 * @author Danbee Park
 */
@WebServlet(
		urlPatterns = { "/UserLogin" }, 
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark"), 
				@WebInitParam(name = "dbUser", value = "danpark"), 
				@WebInitParam(name = "dbPass", value = "110142214")
		})
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DBManager db;

    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException {
        super.init();
        // TODO Auto-generated constructor stub
        db = new DBManager();
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		signIn(request,response);
	}
protected void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		HttpSession session = request.getSession(true);

		response.setHeader("Cache-Control", "no-cache"); // no cache for HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		String email = request.getParameter("signInId");
		String pw = request.getParameter("signInPw");
		
		User user = db.getUser(email,pw);
		if(user!=null){
			session.setAttribute("LoginUser", user);
			Customer customer= db.getCustomer(user);
			
			if(customer==null){
				Employer employee = db.getEmployer(user);
				session.setAttribute("employeeInfo", employee);
				session.setAttribute("isCustomer", false);
			}else{
				session.setAttribute("customerInfo", customer);
				session.setAttribute("isCustomer", true);
			}
			
			String targetPage = "index.jsp";
			response.sendRedirect(targetPage);
		}else{										//error
			noUserInfoMsg(response);
		}
	}
	protected void noUserInfoMsg(HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('Invalid Email or Password');");
		out.println("history.back();");
		out.println("</script>");
	}

}
