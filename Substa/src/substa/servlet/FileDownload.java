package substa.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet(urlPatterns = { "/FileDownload" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu/danpark"),
		@WebInitParam(name = "dbUser", value = "danpark"), @WebInitParam(name = "dbPass", value = "110142214") })
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		filedown(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		filedown(request,response);
	}

	private void filedown(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		response.setHeader("Cache-Control", "no-cache"); // no cache for HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String fileName = request.getParameter("file_name");
		
		String saveFolder = "/fileupload";
		String realFolder = request.getServletContext().getRealPath(saveFolder);
		String realFolder2 = request.getServletContext().getRealPath(saveFolder+"/att");
		
		String filePath = realFolder +"/"+fileName;
		String filePath2 = realFolder2 + "/"+fileName;
		
		String s = getFilePath(filePath,filePath2);
		System.out.println(s);
		if(s==null || s.equals("")|| s.equals("null")){
			//noimge
			return;
		}
		
		try{
			File file = new File(s);
			byte b[] = new byte[4096];
			
			response.reset();
			response.setContentType("application/octet-stream");
			
			String Encoding = new String(fileName.getBytes("UTF-8"),"8859_1");
			response.setHeader("Content-Disposition", "attatchement; filename ="+Encoding );
			response.setHeader("Content-Length", String.valueOf((int)file.length()));
			
			System.out.println(filePath);
			
			FileInputStream is = new FileInputStream(filePath);
			ServletOutputStream sos = response.getOutputStream();
			
			int numRead;
			while((numRead = is.read(b,0,b.length))!=-1){
				sos.write(b,0,numRead);
				
			}
			
			sos.flush();
			sos.close();
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getFilePath(String... filePath) {
		String file = "";	
	
		for(String s : filePath) {
			File f = new File(s);
			
			if(f.exists()) {
				file = s;
				break;
			}
		}
		return file;
	}

}
