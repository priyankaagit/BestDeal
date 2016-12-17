/*
 * WriteReview.java
 *
 */
 

import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ViewReview extends HttpServlet {
   
   HashMap<String,ArrayList<Review>> review = null;
	MongoDBDataStoreUtilities s = null;
	
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
		   
            review = new HashMap<String,ArrayList<Review>>();
            s  = new MongoDBDataStoreUtilities();
            review = s.selectReview();

    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String user1 = (String)session.getValue("userid");
		//String usertype = request.getParameter("usertype");
		String user = (String)session.getValue("userid");
		String usertype = (String)session.getValue("usertype");
		String productName = request.getParameter("prodName");
		String itemID = request.getParameter("itemID");
		
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
                            
							/*
							 if (review!= null){
								 out.println("Review not null"+itemID);
								 
								 ArrayList<Review> item5review = review.get(itemID);
								 if(item5review==null)
									 out.println("But item Review is null"+itemID);
							 }
						*/
                           		
							
							
							 if (review!= null){ 
										
										ArrayList<Review> itemreview = review.get(itemID);
										
										if(itemreview != null){
										 
											for(Review lp: review.get(itemID)){											 									
												/* if (lp == null){
													
												 }	*/							
												if(lp !=null){
													out.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                                                                    "<TR BGCOLOR=\"#FFAD00\">\n");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ProductModelName\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getProductModelName());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ProductCategory\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getProductCategory());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ProductPrice\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getProductPrice());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "RetailerName\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getRetailerName());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "RetailerZip\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getRetailerZip());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "RetailerCity\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getRetailerCity());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "RetailerState\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getRetailerState());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ProductOnSale\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getProductOnSale());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ManufacturerName\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getManufacturerName());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ManufacturerRebate\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getManufacturerRebate());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "UserId\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getUserId());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "UserAge\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getUserAge());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "UserGender\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getUserGender());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "UserOccupation\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getUserOccupation());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");							
													out.println( "<td>\n ");
													out.println( "ReviewRating\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getReviewRating());
													out.println("</td> \n");
													out.println("</tr>");
													out.println("<tr>\n");
													out.println( "<td> ");
													out.println( "ReviewDate\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getReviewDate());
													out.println("</td> \n"); 
													out.println("<tr>\n");
													out.println( "<td>\n ");													
													out.println( "ReviewText\n" );
													out.println("</td> \n");
													out.println( "<td>\n ");
													out.println(lp.getReviewText());
													out.println("</td> \n"); 
													out.println("</tr>");
													out.println("</table>\n");
												} 
										   }
										   
										}else{
											out.println("No Review available");
										}  
				
							}
							 else {
								 out.println("No Review available");
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
