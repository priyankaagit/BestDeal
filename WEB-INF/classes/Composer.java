import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Composer extends HttpServlet {
	
	HashMap<String, Product> lap = null;
	HashMap<String, Product> laptop = null;
	HashMap<String, Product> mobile = null;
	HashMap<String, Product> tablet = null;
	HashMap<String, Product> television = null;
	SaxParser4ProductXMLdataStore sx = null;
	MySqlDataStoreUtilities s = null;
	String prodType = null;
	String folder = null;
	
	
	public void init() {
		
		    //String filepath = "C:/apache-tomcat-7.0.34/webapps/bestdeal/WEB-INF/classes/Laptop.xml";
			//sx = new SaxParser4ProductXMLdataStore(filepath);
        	     laptop = new HashMap<String, Product>();
				 mobile = new HashMap<String, Product>();
				 tablet = new HashMap<String, Product>();
				 television = new HashMap<String, Product>();
			     s = new MySqlDataStoreUtilities();
	}
	
	
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{

     // RequestDispatcher view = request.getRequestDispatcher("header.html");
      //         view.forward(request, response);  
	        HttpSession session = request.getSession(true);	 
	        response.setContentType("text/html");
            PrintWriter out = response.getWriter();
			//String user = request.getParameter("userid");
			String user = (String)session.getValue("userid");
			String usertype = (String)session.getValue("usertype");
            out.println("<!doctype html> \n" +
            
			"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
			"<head>\n" +
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n" +
			"<title>spigot - Free CSS Template by ZyPOP</title> " +
			"<script type=\"text/javascript\" src=\"javascript.js\"></script> " +
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
			"<body onload=\"init()\"> \n" +
			"<div id=\"container\"> \n" +
				"<header> \n" +
				"	<h1><a href=\"/\">Best<span>Deal</span></a></h1> \n" +
				"	<h2>Shopping Online Made Easy</h2> \n");
				if(user!=null){
				out.println("<h3 style=\"text-align:right\">");
				out.println("Welcome " + user);
				out.println("<a href=\"/bestdeal/TrackOrder\">Track Order</a>\n");
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
						
					out.println("<li class=\"text\">\n" +
                             "<form name=\"autofillform\" action=\"/AutoCompleteServlet\">"+
                                "<p> \n" +
																
											"<div name=\"autofillform\">\n" +
													"<input type=\"text\" id = \"searchId\" onkeyup=\"doCompletion()\" \n" +
													 "placeholder=\"Search\" size=\"37\" style=\"height:25px\"\"/> \n" +
														"<div id=\"auto-row\">\n"+
															"<table id=\"complete-table\" class=\"gridstyle\" style=\"position:absolute;width:315px;\"></table>\n" +
														"</div> \n" +
											"</div>\n" +
									
									 " </form> \n" +								
			                 "</li> \n");	
													
                        
					
					
					
						
						if(user==null)
						out.println("<li><a href=\"Login.html\">Login</a></li>\n" +	
						"<li><a href=\"Signup.html\">SignUp</a></li>	\n" );
                        else {
						out.println("<li><a href=\"AccountServlet\">Account</a></li>\n" );	
                        out.println("<li><a href=\"LogoutServlet\">Logout</a></li>\n" );	
						//out.println("<li style=\"float:right\" class=\"end\"><a>Cart</li>\n"); 
						
						}
						
						
						out.println("<li class=\"end\"><a>Cart</li>\n");
						
								
						/*out.println("<body onload=\"init()\"> \n" +
						"<form name=\"autofillform\" action=\"/AutoComplete\"> \n" +
									  "<table border=\"0\" cellpadding=\"5\"> \n" +
										"<tbody> \n" +
										 " <tr> \n" +
											"<td><strong>Composer Name:</strong></td> \n" +
														"<td> \n" +
															"<input type=\"text\" \n" +
													   "size=\"40\" \n" + 
													   "id=\"searchId\" \n" +
																  " onkeyup=\"doCompletion()\"> \n" +
														"</td>\n" +
										  "</tr> \n" +
										  "<tr> \n" +
											  "<td id=\"auto-row\" colspan=\"2\"> \n" +
												"<table id=\"complete-table\" class=\"popupBox\" /> \n" +
											" </td> \n" +
										  "</tr> \n" +
										"</tbody> \n" +
									 " </table> \n" +
									"</form>\n" + 
									"</body>\n");			
						*/
				out.println("</ul>\n" +
				"</nav> \n" +

				
			"<div id=\"body\">\n" +

					

				"	<section id=\"content\"> \n");
				            
				            String productid = request.getParameter("searchId");
							String producttype = s.productType(productid);
							
							String filepath = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Laptop.xml";
				String filepath1 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Mobile.xml";
				String filepath2 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Tablet.xml";
				String filepath3 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Television.xml";
				
				HashMap<String, Product> lapSession = (HashMap<String,Product>)session.getAttribute("laptopData");
				if(lapSession!=null){
					laptop = lapSession;
				}
				 if(laptop.isEmpty()){
				 sx  = new SaxParser4ProductXMLdataStore(filepath);
				 laptop = sx.prettyPrint();
				 session.setAttribute("laptopData",laptop);
				 for(String id: laptop.keySet()){
					 prodType = "Laptop";
					 Product product = laptop.get(id);
					 s.insertProduct(product,prodType);
				 }
				 
				 }
				 HashMap<String, Product> mobSession = (HashMap<String,Product>)session.getAttribute("mobileData");
				if(mobSession!=null){
					mobile = mobSession;
				}
				 
				 if(mobile.isEmpty()){
				 sx  = new SaxParser4ProductXMLdataStore(filepath1);
				 mobile = sx.prettyPrint();
				 session.setAttribute("mobileData",mobile);
				 for(String id: mobile.keySet()){
					 prodType = "Mobile";
					 Product product = mobile.get(id);
					 s.insertProduct(product,prodType);
				 }	 	 	
				 }

				HashMap<String, Product> tabSession = (HashMap<String,Product>)session.getAttribute("tabletData");
				if(tabSession!=null){
					tablet = tabSession;
				}

				 if(tablet.isEmpty()){
				 sx  = new SaxParser4ProductXMLdataStore(filepath2);
				 tablet = sx.prettyPrint();
				 session.setAttribute("tabletData",tablet);
				 for(String id: tablet.keySet()){
					 prodType = "Tablet";
					 Product product = tablet.get(id);
					 s.insertProduct(product,prodType);
				 }	 	 	
				 }
				 HashMap<String, Product> tvSession = (HashMap<String,Product>)session.getAttribute("televisionData");
				if(tvSession!=null){
					television = tvSession;
				}
				 
				 if(television.isEmpty()){
				 sx  = new SaxParser4ProductXMLdataStore(filepath3);
				 television = sx.prettyPrint();
				 session.setAttribute("televisionData",television);
				 for(String id: television.keySet()){
					 prodType = "Television";
					 Product product = television.get(id);
					 s.insertProduct(product,prodType);
				 }	 
				 }
							
				
				if (producttype!= null){
							   if(producttype.equals("Laptop")){
								 folder = "/images/"; 
                                 lap = laptop;								 
							   }
							   else if(producttype.equals("Mobile")){
								   folder = "/imagesphone/";
								   lap = mobile;								   
							   }
							   else if(producttype.equals("Tablet")){
								   folder = "/imagestablet/";
								   lap = tablet;
							   }
							   else if(producttype.equals("Television")){
								   folder = "/imagestv/";
								   lap = television;
							   }
						   }
							
							
				            Product lp = lap.get(productid);
                            String url = "/bestdeal/OrderPage";
							out.println("<FORM action=\""+url+"\">\n" +
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +lp.getId()+"\">");																
							out.println( "<table style=\"width:30%\">\n" );
						    out.println("<tr>\n");
							out.println("<td>\n");
							out.println("<img src=" +request.getContextPath()+ folder + lp.getImage());
							out.println( "alt=\"\" width=\"250\" height=\"200\">");
							out.println("</td>\n");
							out.println( "<td>\n ");
							out.println( "Product\n" );
							out.println(lp.getName());
							out.println("</td> \n"); 
							//out.println( "<td> ");
							//out.println( "Condition\n" );
							//out.println(lp.getCondition());
							//out.println("</td> \n"); 
							out.println( "<td>\n ");
							out.println( "Price\n" );
							out.println(lp.getPrice());
							out.println("</td> \n"); 
							
                            if (user!=null)	{						
							out.println("<td>");
							out.println("<input type=\"SUBMIT\""+ "value=\"Buy Now\">\n");
							
							out.println("</td>\n"); 
							}
							else {
								out.println("<td>");
							    out.println("<a href=\"Login.html\" class=\"button\">Buy Now</a>\n");
							    out.println("</td>\n"); 
							}
							out.println("</FORM>");
							out.println("<FORM action=\"/bestdeal/ReviewServlet\">\n" +
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +lp.getId()+"\">");
							if (user!=null)	{									
							out.println("<td>");
				           // out.println("<a href=\"/bestdeal/ReviewServlet\" class=\"button\">Review</a>\n");
							out.println("<input type=\"SUBMIT\""+ "value=\"Review\">\n");
							out.println("</td>\n");
							}
							else {
							out.println("<td>");
				            out.println("<a href=\"Login.html\" class=\"button\">Review</a>\n");
							out.println("</td>\n");
							}
							out.println("</FORM>");
							out.println("<FORM action=\"/bestdeal/ViewReview\">\n" +
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +lp.getId()+"\">");
							if (user!=null)	{									
							out.println("<td>");
				            out.println("<input type=\"SUBMIT\""+ "value=\"View Review\">\n");
							out.println("</td>\n");
							}
							else {
							out.println("<td>");
				            out.println("<a href=\"Login.html\" class=\"button\">View Review</a>\n");
							out.println("</td>\n");
							}
							out.println("</FORM>");
							out.println("</tr>\n");
							out.println( " </table>" );
				
				
				
				
					out.println(
					"</section>\n" +
					
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