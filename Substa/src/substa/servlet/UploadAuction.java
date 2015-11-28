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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import substa.beans.Item;
import substa.model.DBManager;

/**
 * Servlet implementation class UploadAuction
 */
@WebServlet(urlPatterns = { "/UploadAuction" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu/danpark"),
		@WebInitParam(name = "dbUser", value = "danpark"), @WebInitParam(name = "dbPass", value = "110142214") })
public class UploadAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String realFolder = "";
		String saveFolder = "/img";
		
		int fileSize = 5*1024*1024;
		realFolder = request.getRealPath(saveFolder);
		try{
			MultipartRequest multi = null;
			
			multi = new MultipartRequest(request, realFolder, fileSize,"utf-8",new DefaultFileRenamePolicy());
			
			Item item = new Item();
			item.setItemName(multi.getParameter("name"));
			item.setDescription(multi.getParameter("desc"));
			item.setItemType(multi.getParameter("sType"));
			item.setNumCopies(Integer.parseInt(multi.getParameter("num")));
			
			String file = multi.getFilesystemName((String)multi.getFileNames().nextElement());
			item.setImgsrc(file);
			
			goIndex(response);
			
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		
	}

	private void goIndex(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'>");
		out.println("location.href='index.jsp';");
		out.println("</script>");
	}
}
