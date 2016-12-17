/*
 * SignupServlet.java
 *
 */
 

import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {
   
    HashMap<String,User> users = null;
	MySqlDataStoreUtilities s = null;
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
              users = new HashMap<String,User>();
              s  = new MySqlDataStoreUtilities();
             // users = s.selectUser();

    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String emailid = request.getParameter("emailid");
		String phoneno = request.getParameter("phoneno");
	    String userid = request.getParameter("userid");
        String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		boolean status = false; 
        User user = users.get(userid);
		out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Result</title>");  
        out.println("</head>");
		out.println("<body>");
           //     if(user!=null){

		   //         out.println("Name already taken,choose a different userid");

			//	}
			if (usertype == null)
			out.println("Please choose Type of user");	
		    else {
			status = s.checkStatus(userid);
				if(status){
					out.println("Name already taken,choose a different userid");
				}
				
				else {
					 user = new User(firstname,lastname,phoneno,emailid,password,userid,usertype);
				//	 users.put(userid,user);
					 s.insertUser(user);
					 out.println("Account created successfully, proceed to login "+user.getfirstName());
				     out.println("<form method=\"get\" action=\"Login.html\"> \n"+
                     "<button type=\"submit\">Continue</button> \n" +
                     " </form>");
					 
				}
				
		    }		
             //   else {
				//  user = new User(firstname,lastname,phoneno,emailid,password,userid,usertype);
				//  s.writeUserDataStore(user);
	
				//  out.println("Account created successfully, proceed to login "+user.getfirstName());
				//  out.println("<form method=\"get\" action=\"Login.html\"> \n"+
              //      "<button type=\"submit\">Continue</button> \n" +
               //  " </form>");
              // }
/*
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    } 
   */
}
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
}
