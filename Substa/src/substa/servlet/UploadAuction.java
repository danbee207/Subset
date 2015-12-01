package substa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import substa.beans.Auction;
import substa.beans.Customer;
import substa.beans.Item;
import substa.beans.Post;
import substa.model.DBManager;
import substa.model.DBManagers;

/**
 * Servlet implementation class UploadAuction
 */
@WebServlet(urlPatterns = { "/UploadAuction" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu/danpark"),
		@WebInitParam(name = "dbUser", value = "danpark"), @WebInitParam(name = "dbPass", value = "110142214") })
public class UploadAuction extends HttpServlet {
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

		HttpSession session = request.getSession(true);
		
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
			db.addItem(item);
			int itemId = db.getLatestItemID();
			//db
			Auction auction = new Auction();
			auction.setItemId(itemId);
			auction.setMornitor(db.getManagerID());
			auction.setCopy(1);
			auction.setMinBid(Float.parseFloat(request.getParameter("minBid")));
			
			db.addAuction(auction);
			//db
			Post post = new Post();
			Customer customer = (Customer)session.getAttribute("customerInfo");
			post.setCusId(customer.getSsn());
			post.setAucId(db.getLatesetAuctionID());
			post.setStartDate(new Timestamp(System.currentTimeMillis()));
			post.setEndDate(Timestamp.valueOf(request.getParameter("endDate")));
			post.setPrice(Float.parseFloat(request.getParameter("reserveBid")));
			db.addPost(post);
			//db
			
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
