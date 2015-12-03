package substa.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

	/*
	 * protected void processRequest(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception {
	 * 
	 * HttpSession session = request.getSession(true);
	 * 
	 * response.setHeader("Cache-Control", "no-cache");
	 * response.setHeader("Pragma", "no-cache");
	 * response.setDateHeader("Expires", 0);
	 * 
	 * response.setContentType("text/html; charset=utf-8");
	 * 
	 * boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	 * 
	 * if(isMultipart){
	 * 
	 * DiskFileItemFactory factory = new DiskFileItemFactory();
	 * factory.setSizeThreshold(1*1024*1024);
	 * 
	 * ServletFileUpload upload = new ServletFileUpload(factory);
	 * upload.setSizeMax(10*1024*1024);
	 * 
	 * List<FileItem> items = upload.parseRequest(request); Iterator iter =
	 * items.iterator();
	 * 
	 * while(iter.hasNext()){ FileItem fileItem = (FileItem)iter.next();
	 * 
	 * if(fileItem.isFormField()){ Item item = new Item();
	 * item.setItemName(request.getParameter("name"));
	 * item.setDescription(request.getParameter("desc"));
	 * item.setItemType(request.getParameter("sType"));
	 * item.setNumCopies(Integer.parseInt(request.getParameter("num")));
	 * 
	 * 
	 * item.setImgsrc(file); db.addItem(item); int itemId =
	 * db.getLatestItemID(); //db Auction auction = new Auction();
	 * auction.setItemId(itemId); auction.setMornitor(db.getManagerID());
	 * auction.setCopy(1);
	 * auction.setMinBid(Float.parseFloat(request.getParameter("minBid")));
	 * 
	 * db.addAuction(auction); //db Post post = new Post(); Customer customer =
	 * (Customer)session.getAttribute("customerInfo");
	 * post.setCusId(customer.getSsn());
	 * post.setAucId(db.getLatesetAuctionID()); post.setStartDate(new
	 * Timestamp(System.currentTimeMillis()));
	 * 
	 * String endDate = request.getParameter("endDate");
	 * System.out.println("getDate"+endDate); SimpleDateFormat bfomratter = new
	 * SimpleDateFormat("yyyy-m-d h:m:s"); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); Date d =
	 * bfomratter.parse(endDate);
	 * 
	 * System.out.println(d.toString()); String endDateFixed =
	 * formatter.format(d); System.out.println(endDateFixed);
	 * post.setEndDate(Timestamp.valueOf(endDateFixed));
	 * post.setPrice(Float.parseFloat(request.getParameter("reserveBid")));
	 * db.addPost(post); //db }else{ if(fileItem.getSize()>0){ String fieldName
	 * = fileItem.getFieldName(); String fileName = fileItem.getName(); String
	 * contentType = fileItem.getContentType();
	 * 
	 * boolean isInMemory = fileItem.isInMemory(); long sizeBytes =
	 * fileItem.getSize();
	 * 
	 * try{ File uploadedFile = new File(realDir,fileName);
	 * fileItem.write(uploadedFile);
	 * 
	 * fileItem.delete(); }catch(IOException e){ e.printStackTrace(); } } } }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String realFolder = "";
		String saveFolder = "/fileupload";

		int fileSize = 5 * 1024 * 1024;

		realFolder = request.getServletContext().getRealPath(saveFolder);
		try {
			File fileCheck = new File(realFolder);
			if (!fileCheck.exists()) {
				fileCheck.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			MultipartRequest multi = null;

			multi = new MultipartRequest(request, realFolder, fileSize, "euc-kr", new DefaultFileRenamePolicy());

			Item item = new Item();
			item.setItemName(multi.getParameter("name"));
			item.setDescription(multi.getParameter("desc"));
			item.setItemType(multi.getParameter("sType"));
			item.setNumCopies(Integer.parseInt(multi.getParameter("num")));

			String file = multi.getFilesystemName((String) multi.getFileNames().nextElement());
			item.setImgsrc(file);
			db.addItem(item);
			int itemId = db.getLatestItemID();
			// db
			Auction auction = new Auction();
			auction.setItemId(itemId);
			auction.setMornitor(db.getManagerID());
			auction.setCopy(1);
			auction.setMinBid(Float.parseFloat(multi.getParameter("minBid")));

			db.addAuction(auction);
			// db
			Post post = new Post();
			Customer customer = (Customer) session.getAttribute("customerInfo");
			post.setCusId(customer.getSsn());
			post.setAucId(db.getLatesetAuctionID());
			post.setStartDate(new Timestamp(System.currentTimeMillis()));

			String endDate = multi.getParameter("endDate");
			System.out.println("getDate" + endDate);
			SimpleDateFormat bfomratter = new SimpleDateFormat("yyyy-m-d h:m:s");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			Date d = bfomratter.parse(endDate);

			System.out.println(d.toString());
			String endDateFixed = formatter.format(d);
			System.out.println(endDateFixed);
			post.setEndDate(Timestamp.valueOf(endDateFixed));
			post.setPrice(Float.parseFloat(multi.getParameter("reserveBid")));
			db.addPost(post);
			// db

			goIndex(response);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	private void goIndex(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'>");
		out.println("location.href='main.jsp';");
		out.println("</script>");
	}
}
