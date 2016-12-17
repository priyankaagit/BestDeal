 

import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class ReviewServlet extends HttpServlet {



 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String user1 = (String)session.getValue("userid");
		//String usertype = request.getParameter("usertype");
		String user = (String)session.getValue("userid");
		String usertype = (String)session.getValue("usertype");
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
		                    out.println( "<table align=\"center\" style=\"margin: 0px auto;\" cellpadding='2' cellspacing='1'>\n"+
										"<tr>\n"+
									    "<td><center>"+  "</center></td>\n");
							out.println(" <form method=\"post\" action=\"/bestdeal/WriteReview\">\n" +
                                   "<h4>Review</h4>\n" +
									 " <div align=\"center\"> \n" +
									  "<table align=\"center\" style=\"margin: 0px auto;\" cellpadding='2' cellspacing='1'>\n"+
										"<tr>\n"+
										    "<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +itemID+"\">" +
											"<td><center>ProductModelName</center></td>\n"+
											"<td><input type=\"TEXT\" size= \"15\" name=\"ProductModelName\"></input></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>ProductCategory</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"ProductCategory\"/></td>\n"+
										"</tr>\n"+
                                         "<tr>\n"+
											"<td><center>ProductPrice</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"ProductPrice\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>RetailerName</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"RetailerName\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>RetailerZip</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"RetailerZip\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>RetailerCity</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"RetailerCity\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>RetailerState</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"RetailerState\"/></td>\n"+
										"</tr>\n"+
										
										"<tr>\n"+
											"<td><center>ProductOnSale</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"ProductOnSale\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>ManufacturerName</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"ManufacturerName\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>ManufacturerRebate</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"ManufacturerRebate\"/></td>\n"+
										"</tr>\n"+
										
										"<tr>\n"+
											"<td><center>UserId</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"UserId\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>UserAge</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"UserAge\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>UserGender</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"UserGender\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>UserOccupation</center></td>\n"+
											"<td><input type=\"TEXT\" size=\"15\" name=\"UserOccupation\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>ReviewRating</center></td>\n"+
											"<td><select name=\"ReviewRating\">\n" + 
											 " <option value=\"1\">1</option> \n" +
											 " <option value=\"2\">2</option> \n" +
											 " <option value=\"3\">3</option> \n" +
											 " <option value=\"4\">4</option> \n" +
											 " <option value=\"5\">5</option> \n" +
											"</select> </td>\n"+
										"</tr>\n"+  									 
											
										"<tr>\n"+
											"<td><center>ReviewDate</center></td>\n"+
											//"<td><input required=\"\" type=\"text\" class=\"form-control\" placeholder=\"Date\" onfocus=\"(this.type='date')\"/></td>\n" +
											"<td><input type=\"DATE\" name=\"ReviewDate\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td><center>ReviewText</center></td>\n"+
											"<td><textarea name=\"ReviewText\" rows=\"2\" cols=\"40\"></textarea></td>\n" +
											//"<td><input type=\"TEXT\" size=\"15\" name=\"ReviewText\"/></td>\n"+
										"</tr>\n"+
										   
										
										
											
											"<td colspan='2'>\n"+
												"<center><input type=\"submit\" value=\"Submit\" /></center>\n"+
											"</td>\n"+
										"</tr>\n"+
									 "</table>\n"+
									 "</div>\n"+
									 "</form>\n"); 
							//out.println( "<table style=\"width:50%\">\n" );
							
							
					/*	    out.println("<tr>\n");
                            out.println( "<td>\n ");
							out.println( "User\n" );
						//	out.println(user);
							out.println("</td> \n"); 
							out.println( "<td>\n ");
							out.println( "UserType\n" );
						//	out.println(usertype);
							out.println("</td> \n");						
							out.println( "</tr> \n");
							out.println("<tr>\n");
                            out.println( "<td>\n ");
						//	out.println( "User\n" );
							out.println(user);
							out.println("</td> \n"); 
							out.println( "<td>\n ");
						//	out.println( "UserType\n" );
						    out.println(usertype);
							out.println("</td> \n");						
							out.println( "</tr> \n");
							*/
							//out.println("</table>\n");
							
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
