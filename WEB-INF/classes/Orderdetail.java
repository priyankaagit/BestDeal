 
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class Orderdetail extends HttpServlet {

Cart c;
MySqlDataStoreUtilities s = null;
ArrayList oldcart;

public void init() {
				c = new Cart();
				s  = new MySqlDataStoreUtilities();
			}

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		ArrayList<ItemOrder> order = new ArrayList<ItemOrder>();
		ShoppingCart scart;
		//String user = (String)session.getValue("userid");
		//String usertype = request.getParameter("usertype");
		String user = (String)session.getValue("userid");
		String usertype = (String)session.getValue("usertype");
		String password = request.getParameter("password");
		String cardno = request.getParameter("cardno");
		String address = request.getParameter("Address");
		String cardtype = request.getParameter("cardtype");	
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		
		Calendar calendar = Calendar.getInstance();
		//Date orderdate = new Date(dateFormat.format(orderdate));
		Date orderdate = new Date();
        int days = 15;    
        calendar.add(Calendar.DAY_OF_MONTH, days);
		//calendar.add(Calendar.DATE, 15);
		//calendar = dateFormat.format((calendar.getTime()));
		//Date deliverydate = new Date(dateFormat.format(calendar.getTime()));
		
		scart = (ShoppingCart)session.getAttribute("shoppingCart");
		
		oldcart = c.readCart(user);
		if (scart!=null){
		order = (ArrayList<ItemOrder>)scart.getItemsOrdered();
		//out.println(order.size());
	    
		//c.writeCart(user,order);
		     }
		Double productprice = 0.0;
		s.insertCustomerAccount(user,address,cardno,cardtype);
		
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
							out.println("<li><a href=\"AccountServlet\">Account</a></li>\n" );		
                            out.println("<li><a href=\"LogoutServlet\">Logout</a></li>\n" );							
						    //out.println("<li style=\"float:right\" class=\"end\"><a>Cart</li>\n"); 
							}
                             
                         out.println("<li class=\"end\"><a>cart</li>\n" +
                             "</ul>\n" +
                             "</nav>\n");
							 out.println("<div id=\"body\">\n" +
							 "	<section id=\"content\"> \n");
		                   for(ItemOrder i: order )
							{
								
									
						
							productprice = productprice + (int) i.getUnitCost();
							

							}
							
							
							Double totalprice = productprice;
							out.println("<table align=\"center\">");
							out.println("<tr>\n");
						    out.println( "<td>\n ");						
						    out.println("TotalPrice \n");
							out.println("</td> \n");
							out.println( "<td>\n ");
							out.println(productprice);
							out.println("</td> \n"); 
							out.println( "</tr> \n");
							int randomNum = (int)(Math.random() * ((1000 - 1) + 1));
							String ordNum = (Integer)randomNum + user;
							java.sql.Date neworderdate = new java.sql.Date(orderdate.getTime());
							java.sql.Date deliverydate = new java.sql.Date(calendar.getTime().getTime());
							
							s.insertCustomerOrder(ordNum,user,totalprice,neworderdate, deliverydate);
							s.insertOrderItem(ordNum,order);
                            							
						   out.println("<tr>\n");
						    out.println( "<td>\n ");	
							out.println(ordNum);
							out.println("</td> \n");
							out.println( "<td>\n ");
							out.println("Order Date" + dateFormat.format(orderdate));
							out.println("Delivery date" + dateFormat.format((calendar.getTime())));
							out.println("</table>\n")	;						
                            out.println("</section>\n" );
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
	   }
         //session.invalidate();
	//	 RequestDispatcher view = request.getRequestDispatcher("/Home");
      //   view.forward(request, response);  
		
	


   protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
        } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
          }



}
