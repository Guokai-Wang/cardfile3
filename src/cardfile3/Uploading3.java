package cardfile3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Uploading3
 */
@WebServlet(description = "Uploading3 servlet", urlPatterns = { "/Uploading3" })
public class Uploading3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploading3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException 
	{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File file;
		Hopper hopper = new Hopper();
		int maxFileSize = 46000 * 1024;
		   // int maxMemSize = 5000 * 1024;
		int maxMemSize = 46000 * 1024;
		   /*
		   ServletContext context = pageContext.getServletContext();
		   String filePath = context.getInitParameter("file-upload");
		*/
		PrintWriter out = response.getWriter();
		// String filePath = "c:\\users\\Guokai.Wang\\";
		String filePath = hopper.uFilePath();

		// Verify the content type
		   // String contentType = request.getContentType();
		   
		   // System.out.println("upload_servlet:" + contentType);
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      
		      // Location to save data that is larger than maxMemSize.
		      // factory.setRepository(new File("c:\\temp"));

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		System.out.println("uploadserv:" + getServletContext().getContextPath());
		System.out.println("uploadserv:" + File.separator);
		      
		try
		{
			List fileItems = upload.parseRequest(request);

		         // Process the uploaded file items
	        Iterator i = fileItems.iterator();

	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>JSP File upload</title>");  
	        out.println("</head>");
	        out.println("<body>");
		         
	        while(i.hasNext())
	        {
	        	FileItem fi = (FileItem) i.next();
		        if (!fi.isFormField()) 
		        {
		        	   // Get the uploaded file parameters
		            // String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            // boolean isInMemory = fi.isInMemory();
		            // long sizeInBytes = fi.getSize();
		            
		               // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ) 
		            {
		            	file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }
		            else
		            {
		            	file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		            hopper.convertCloud(fileName);
		        }
	        }
	        out.println("<a href='index.jsp'>Start Over</a>");
		    out.println("</body>");
		    out.println("</html>");
		}
		catch(Exception ex) 
		{
		         // System.out.println(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
