/*
 * LoginServlet.java
 *
 */
 

import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
   
   HashMap<String,User> usersAccount = null;
   MySqlDataStoreUtilities sd = null;
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
	          sd = new MySqlDataStoreUtilities();
              usersAccount  = new HashMap<String,User>();
		      usersAccount   = sd.selectUser();
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HttpSession session = request.getSession(true);
		//boolean status = false;
	
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(userid != null &&
            password != null) {
		User user = usersAccount.get(userid);
		
				
		if(user==null)
			
		showPage(request,response, "user does not exist",false,usertype);
		if (usertype == null)
		showPage(request,response, "Please select type of user",false,usertype);
		//out.println("");
		
                String realpassword = user.getpassword();
				String realusertype = user.getusertype();
                if(realpassword != null &&
                    realpassword.equals(password)) {
						
                        if (realusertype != null && realusertype.equals(usertype)){					
							session.putValue( "userid",userid);
							session.putValue("usertype", usertype);
							showPage(request,response, "Login Success!",true,usertype);
						}
						 else{
							 showPage(request,response, "Login Failure!User Type is incorrect",false,usertype);
						 }
						
                } else {
                    showPage(request,response, "Login Failure! Username or password is incorrect",false,usertype);
					
                }
        }  else {
            showPage(request,response, "Login Failure!  You must supply a username and password",false,usertype);
        }
    } 
    
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletRequest request,HttpServletResponse response, String message,boolean status, String usertype)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
		
		if(status==false){
	    
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
		out.println("<table style=\"width:30%\">");
		out.println("<tr>");
        out.println("<td>" + message + "</td>");
		out.println("</tr>");
		out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        out.close();
		}
		else if(status == true){
			if (usertype.equals("Customer")){				
				RequestDispatcher view = request.getRequestDispatcher("/Home");
                view.forward(request, response);  
			}
			else if (usertype.equals("StoreManager")){
				RequestDispatcher view = request.getRequestDispatcher("/StoreManager");
                view.forward(request, response);  
			}
			else if (usertype.equals("SalesMan")){
				RequestDispatcher view = request.getRequestDispatcher("/SalesMan");
                view.forward(request, response);  
			}
     }
	}
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
	
/*
public static void main(String args[]){
	HashMap<String,User> user = new HashMap<String,User>();
	//User u = new User("priyanka","Singh","12345","priyanka@iit.com","mysql","Priyankajsr","customer");
	MySqlDataStoreUtilities mysqlUtility = new MySqlDataStoreUtilities();
	
	user = mysqlUtility.selectUser();
		    for(Map.Entry<String,User> m :user.entrySet()){
            	System.out.println(m.getKey());
		User u1 = m.getValue();
        System.out.println("\t Name : "+u1.getloginid());
		System.out.println("\t Name : "+u1.getfirstName());
		System.out.println("\t Phone : "+u1.getphone());
			}
	//mysqlUtility.insertUser(u);
	//boolean status = mysqlUtility.checkStatus("priyankajsr");
   // System.out.println(status);	
	/*
	System.out.println(u.getloginid());
	System.out.println(u.getpassword());
	System.out.println(u.getusertype());
	System.out.println(u.getfirstName());
	System.out.println(u.getlastname());
	System.out.println(u.getemail());
	System.out.println(u.getphone());
	
	

	
	
   


    }
	*/

}