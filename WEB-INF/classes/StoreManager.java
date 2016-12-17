import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StoreManager extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{

     // RequestDispatcher view = request.getRequestDispatcher("header.html");
      //         view.forward(request, response); 
            HttpSession session = request.getSession(true);	  
	        response.setContentType("text/html");
            PrintWriter out = response.getWriter();
			String user = request.getParameter("userid");
			String usertype = (String)session.getValue("usertype");
            out.println("<!doctype html> \n" +            
			"<html>\n" +
			"<head>\n" +
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n" +
			"<title>spigot - Free CSS Template by ZyPOP</title> " +
 			"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /> \n" +
			"<!--[if lt IE 9]> \n" +
			"<script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>\n" +
			"<![endif]-->\n" +
//			<!--
//			spigot, a free CSS web template by ZyPOP (zypopwebtemplates.com/)

//			Download: http://zypopwebtemplates.com/

//			License: Creative Commons Attribution
			//-->
			"</head> \n" +
			"<body> \n" +
			"<div id=\"container\"> \n" +
				"<header> \n" +
				"	<h1><a href=\"/\">Best<span>Deal</span></a></h1> \n" +
				"	<h2>Shopping Online Made Easy</h2> \n");
				if(user!=null){
				out.println("<h3 style=\"text-align:right\">");
				out.println("Welcome " + user);
                out.println("</h3>");
				}
				out.println(
				"</header>\n" +
				"<nav>\n" +
					"<ul>\n");
					if(user==null) 
								out.println("<li class=\"start selected\"><a href=\"http://localhost/bestdeal/Home\">Home</a></li> \n");				 
						     else 
							 out.println( 
        	             "<li class=\"start selected\"><a href=\"\">Home</a></li> \n");
					    out.println("<li class=\"\"><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Laptop\">Laptop</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Television\">TV</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Tablet\">Tablet</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/ProductServlet?prodType=Mobile\">Smart Phone</a></li>\n"); 
						
						
													
                        
					
					
					out.println("<li class=\"text\"> \n" +
										"<form method=\"get\" class=\"searchform\" action=\"#\" > \n" +
											"<p>\n" +
												"<input type=\"text\" name=\"search\" placeholder=\"Search\" size=\"30\" style=\"height:25px\" value=\"\" name=\"s\" class=\"s\" /> \n" +
												
											"</p>\n" +
										"</form>\n" +	
										
						"</li>\n");
						
						if(user==null)
						out.println("<li><a href=\"Login.html\">Login</a></li>\n" +	
						"<li><a href=\"Signup.html\">SignUp</a></li>	\n" );
                        else {
						out.println("<li><a href=\"AccountServlet\">Account</a></li>\n" );	
                        out.println("<li><a href=\"LogoutServlet\">Logout</a></li>\n" );	
						//out.println("<li style=\"float:right\" class=\"end\"><a>Cart</li>\n"); 
						
						}
						out.println("<li class=\"end\"><a>Cart</li>\n");
				out.println("</ul>\n" +
				"</nav> \n" +
                "<div id=\"body\">\n"+
			    "<section id=\"content\"> \n"+
				"<br>\n" +
				 "<form method=\"post\" action=\"/bestdeal/AddProduct\">\n" +
                                    
									"<div align=\"center\">\n" +
									 "<TABLE BORDER=1 ALIGN=\"CENTER\" style = \"width:50%\">\n"+
                                    "<TR BGCOLOR=\"#FFAD00\">\n" +
						
									    
									    "<tr>\n"+
											"<td>Image Name \n" +
											"<input type=\"TEXT\" size=\"15\" name=\"image\"/></td>\n" +
										"</tr>\n" +
										"<tr> \n" +
											"<td>Retailer \n" +
											"<input type=\"TEXT\" size=\"15\" name=\"retailer\"/></td>\n" +
										"</tr> \n" +
										"<tr> \n" +
											"<td>Product Price \n" +
											"<input type=\"TEXT\" size=\"15\" name=\"productprice\"/></td>\n" +
										"</tr>	\n"+
										"<tr> \n" +
											"<td>Product Name\n" +
											"<input type=\"TEXT\" size=\"15\" name=\"productname\"/></td>\n" +
										"</tr>\n" +
										"<tr> \n" +
											"<td>Product Type \n" +
											"<input type=\"TEXT\" size=\"15\" name=\"producttype\"></input></td> \n" +
										"</tr>	\n" +																	    
										"<td > \n" +
												"<center><input type=\"submit\" value=\"Add Product\" ></input></center> \n" +
										"</td>\n" +	
										"</table> \n" +
									 "</div> \n" +
					"</form> \n" +
				"</tr> \n" +
                "<br> \n" +
               "<form method=\"post\" action=\"/bestdeal/UpdateProduct\"> \n" +
                                   
									 "<div align=\"center\">\n"+
									 "<TABLE BORDER=1 ALIGN=\"CENTER\" style = \"width:50%\">\n" +
                                    "<TR BGCOLOR=\"#FFAD00\">\n" +
						
									
									    "<tr>\n"+
											"<td>Product Id\n"+
											"<input type=\"TEXT\" size=\"15\" name=\"productid\"/></td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td>Retailer\n"+
											"<input type=\"TEXT\" size=\"15\" name=\"retailer\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td>Product Price\n"+
											"<input type=\"TEXT\" size=\"15\" name=\"productprice\"/></td>\n"+
										"</tr>	\n"+
										"<tr>\n"+
											"<td>Product Name\n"+
											"<input type=\"TEXT\" size=\"15\" name=\"productname\"/></td>\n"+
										"</tr>\n"+
										"<tr>\n"+
											"<td>Product Type\n"+
											"<input type=\"TEXT\" size=\"15\" name=\"producttype\"></input></td>\n" +
										"</tr>\n"+
                                        "<tr>\n"+
											"<td>Image\n"+
											"<input type=\"TEXT\" size=\"15\" name=\"image\"/></td>\n"+
										"</tr>	\n"+									
										"<td > \n"+
												"<center><input type=\"submit\" value=\"Update Product\" ></input></center>\n"+
										"</td>	\n"+
										"</table>\n"+
									 "</div>\n"+
									 "</form>\n"+
				
				"<br> \n"+
				
							 
				 "<form method=\"get\" action=\"/bestdeal/DeleteProduct\">\n"+
				 "<div align=\"center\"> \n"+
                             "<TABLE BORDER=1 ALIGN=\"CENTER\"style = \"width:50%\">\n"+
                            "<TR BGCOLOR=\"#FFAD00\">\n"+
									  
								
										"<tr>\n"+
										   
											"<td><center>Productid\n"+
											"<input type=\"TEXT\" size= \"15\" name=\"productid\"/> \n"+									
																	
												
												"<input type=\"submit\" value=\"Delete Product\"></center> \n"+
											"</td> \n"+
										"</tr> \n"+
									
									"</table>\n"+
									"<div>\n"+
									 "</form>\n");
									 
		out.println(			"</section>\n" +
					
					"<aside class=\"sidebar\"> \n" +
				
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