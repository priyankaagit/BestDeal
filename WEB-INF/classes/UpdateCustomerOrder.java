import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class UpdateCustomerOrder extends HttpServlet {

MySqlDataStoreUtilities s = null;
HashMap<String,CustomerOrder> co = null;
ArrayList <OrderItem> or = null;

public void init() {
				
				s  = new MySqlDataStoreUtilities();
			    co = new HashMap<String,CustomerOrder>();
				or = new ArrayList<OrderItem>();
			}
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String user1 = (String)session.getValue("userid");
		//String usertype = request.getParameter("usertype");
		String user = (String)session.getValue("userid");
		String usertype = (String)session.getValue("usertype");
		String orderid = request.getParameter("orderid");
        
		
		out.println("<!doctype html>\n"+
                             "<html>\n" +
			     "<head>\n" +
			    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" + 
                             "<title>spigot - Free CSS Template by ZyPOP</title>\n" +
                             "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\"\n" + 
			     "</head>\n" +
			     "<body>\n" +
			     "<div id=\"container\">\n" + 
 			     "<header> \n" +
    		             "<h1><a href=\"/\">Best<span>Deal</span></a></h1>\n" +
                             "<h2>Shopping Online Made Easy</h2>\n" );
							 if(user!=null){
				out.println("<h3 style=\"text-align:right\">");
				out.println("Welcome " + user);
				out.println("<a href=\"/bestdeal/TrackOrder\">Track Order</a>\n");
                out.println("</h3>");
				}
                 out.println("</header> \n" +
                             "<nav>\n" +
                             "<ul>\n" );
							if(user==null) 
								out.println("<li class=\"start selected\"><a href=\"http://localhost/bestdeal/Home\">Home</a></li> \n");				 
						     else 
							 out.println( 
        	             "<li class=\"start selected\"><a href=\"\">Home</a></li> \n");
					    out.println("<li class=\"\"><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop\">Laptop</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television\">TV</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet\">Tablet</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile\">Smart Phone</a></li>\n"); 
							 
                 //out.println("<li><a href=\"Login.html\">Login</a></li>\n" +	
					//		 "<li><a href=\"Signup.html\">SignUp</a></li>\n");
						
				out.println("<li class=\"text\">\n" +
                             "<form method=\"get\" class=\"searchform\" action=\"#\" >\n" +
                                "<p> \n" +
                                   " <input type=\"text\" name=\"search\" placeholder=\"Search\" size=\"30\" style=\"height:25px\" value=\"\" name=\"s\" class=\"s\" /> \n" +                                   
                               "</p> \n" +
                            " </form> \n" +								
			                 "</li> \n");	
							if(user==null)
					     	out.println("<li><a href=\"Login.html\">Login</a></li>\n" +	
					    	"<li><a href=\"Signup.html\">SignUp</a></li>	\n" );
                            else{
							out.println("<li><a href=\"\">Account</a></li>\n" );		
                            out.println("<li><a href=\"LogoutServlet\">Logout</a></li>\n" );	
						    //out.println("<li style=\"float:right\" class=\"end\"><a>Cart</li>\n"); 
							}
                             
                         out.println("<li class=\"end\"><a>cart</li>\n" +
                             "</ul>\n" +
                             "</nav>\n");
							 out.println("<div id=\"body\">\n" +
							 "	<section id=\"content\"> \n");
		                    
							co = s.selecttrackOrder(orderid);
							 for (String orderid1: co.keySet()) {
								 
										CustomerOrder c = co.get(orderid1);	
                                        out.println(" <form method=\"post\" action=\"/bestdeal/UpdateOrder\">\n" +
                                  
									 " <div align=\"center\"> \n");										
										out.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
													"<TR BGCOLOR=\"#FFAD00\">\n");
										out.println("<tr>\n");							
										out.println( "<td>\n ");
										out.println( "OrderId\n" );
										out.println("</td> \n");
										out.println( "<td>\n ");
										out.println(  "<INPUT TYPE=\"TEXT\" NAME=\"OrderId\"\n" + " SIZE=10 VALUE=\"" + c.getOrderId() + "\">\n" +"<SMALL>\n");
										out.println("</td> \n");
										out.println("</tr>");
										out.println("<tr>\n");							
										out.println( "<td>\n ");
										out.println( "OrderDate\n" );
										out.println("</td> \n");
										out.println( "<td>\n ");
										out.println(  "<INPUT TYPE=\"TEXT\" NAME=\"OrderDate\"\n" + " SIZE=10 VALUE=\"" + c.getOrderDate() + "\">\n" +"<SMALL>\n");
										out.println("</td> \n");
										out.println("</tr>");				 
										out.println("<tr>\n");							
										out.println( "<td>\n ");
										out.println( "DeliveryDate\n" );
										out.println("</td> \n");
										out.println( "<td>\n ");
										out.println(  "<INPUT TYPE=\"TEXT\" NAME=\"DeliveryDate\"\n" + " SIZE=10 VALUE=\"" + c.getDeliveryDate() + "\">\n" +"<SMALL>\n");
										out.println("</td> \n");
										out.println("</tr>"); 
										out.println("<tr>\n");							
										out.println( "<td>\n ");
										out.println( "TotalPrice\n" );
										out.println("</td> \n");
										out.println( "<td>\n ");
										out.println(  "<INPUT TYPE=\"TEXT\" NAME=\"TotalPrice\"\n" + " SIZE=10 VALUE=\"" + c.getTotalPrice() + "\">\n" +"<SMALL>\n");
										out.println("</td> \n");
										out.println("</tr>"); 
										or = c.getOrderItem();												    							   							    							    							   																																							
										for(OrderItem i: or){
											out.println(" <form method=\"post\" action=\"/bestdeal/UpdateOrder\">\n" +
                                    
									        " <div align=\"center\"> \n");
											out.println("<tr>\n");							
										    out.println( "<td>\n ");
										    out.println( "ProductName\n" );
										    out.println("</td> \n");
										    out.println( "<td>\n ");
										    out.println(  "<INPUT TYPE=\"TEXT\" NAME=\"ProductName\"\n" + " SIZE=10 VALUE=\"" + i.getProductName() + "\">\n" +"<SMALL>\n");
										    out.println("</td> \n");
										    out.println("</tr>");
											out.println("<tr>\n");							
										    out.println( "<td>\n ");
										    out.println( "ProductPrice\n" );
										    out.println("</td> \n");
										    out.println( "<td>\n ");
										    out.println(  "<INPUT TYPE=\"TEXT\" NAME=\"ProductPrice\"\n" + " SIZE=10 VALUE=\"" + i.getProductPrice() + "\">\n" +"<SMALL>\n");
										    out.println("</td> \n");
										    out.println("</tr>");
											out.println("<tr>\n");							
										    out.println( "<td>\n ");
                                            out.println( "<center><input type=\"submit\" value=\"UpdateOrder\" /></center>\n");
										    out.println("</td> \n");
										    out.println("</tr>");	
                                            out.println("</form>\n");											
										} 
							 
							
							
									  
									 
									
							
							
							out.println("</table>");
							out.println( "</div>\n"+
									 "</form>\n");
							
							 }	
							
                            out.println("</section>\n");
	                        out.println ("<aside class=\"sidebar\"> \n" +
				
						"<ul>	\n" +
						   "<li> \n" +
								"<h4>Laptop</h4>\n" +
								"<ul>\n" +
								"<li id=\"first\"><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop&maker=Samsung\">Samsung</a></li>   \n" +             
								"	<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop&maker=Lenovo\">Lenovo</a></li> \n" +
								"	<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop&maker=dell\">Dell</a></li> \n" +
								"	<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop&maker=HP\">HP</a></li> \n" +
								"	<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop&maker=asus\">ASUS</a></li> \n" +
								"</ul> \n" +
							"</li> \n" +
							
							"<li> \n" +
								"<h4>Tablets</h4> \n" +
								"<ul> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet&maker=samsung\">Samsung</a></li>\n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet&maker=lenovo\">Lenovo</a></li>\n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet&maker=apple\">Apple</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet&maker=nexus\">Nexus</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet&maker=asus\">ASUS</a></li>\n" +
								"</ul> \n" +
							"</li> \n" +
							
							"<li> \n" +
								"<h4>SmartPhone</h4>\n" +
								"<ul> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile&maker=samsung\">Samsung</a></li>\n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile&maker=apple\">Apple</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile&maker=htc\">HTC</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile&maker=acer\">Acer</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile&maker=sony\">Sony</a></li> \n" +
								"</ul> \n" +
							
							"<li> \n" +
								"<h4>TV</h4>\n" +
												  
								"<ul> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television&maker=samsung\">Samsung</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television&maker=apple\">Apple</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television&maker=lg\">LG</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television&maker=philips\">Philips</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television&maker=sony\">Sony</a></li> \n" +
								"</ul> \n" +
							"<li> \n" +
								//"<h4>DataAnalytics</h4>\n" +
												  
								"<ul> \n");
                                if (user != null){
										if (usertype.equals("StoreManager")){
											out.println("<li><a href=\"http://localhost/bestdeal/TelevisionServlet?maker=samsung\">Data Analytics</a></li> \n" );
											out.println("<li><a href=\"/bestdeal/Trending\">Trending</a></li> \n" +
														"</ul> \n" );
										}					
										 else	{
											out.println("<li><a href=\"/bestdeal/Trending\">Trending</a></li> \n" +
										"</ul> \n" );
										 }
								}
								 else {
									 out.println("<li><a href=\"/bestdeal/Trending\">Trending</a></li> \n" +
										"</ul> \n" ); 
								 }
					out.println("</li> \n" +
					"</aside> \n" +
					"<div class=\"clear\"></div> \n" +
				
				"<footer>\n" +
			"<div class=\"footer-content\"> \n" +
						"<ul> \n" +
							"<li><h4>About us</h4></li> \n" +
							"<li><a href=\"#\">About Best Deal</a></li> \n" +

						"</ul> \n" +
						
						"<ul> \n" +
							"<li><h4>Accounts</h4></li> \n" +
							"<li><a href=\"#\">your account</a></li> \n" +
							"<li><a href=\"#\">your orders</a></li> \n" +

						 "</ul> \n" +
						
						"<ul class=\"endfooter\"> \n" +
							"<li><h4>Contact Us</h4></li> \n" +
							"<li><h4> 1-800-100-009<h4></li> \n" +
						"</ul> \n" +
						
						"<div class=\"clear\"></div>\n" +
					"</div> \n" +
					"<div class=\"footer-bottom\"> \n" +
						"<p>&copy; Copyright © BestDeal.com   \n" +
					 "</div> \n" +
				"</footer> \n" +
			"</div>	\n" +
				
				"</body>\n" +
				"</html>");
	
         //session.invalidate();
	//	 RequestDispatcher view = request.getRequestDispatcher("/Home");
      //   view.forward(request, response);  
 }


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



}
