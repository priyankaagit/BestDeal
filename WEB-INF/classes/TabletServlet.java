/*
 * Product.java
 *
 */
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TabletServlet extends HttpServlet {
   
  HashMap<String, Tablet> tab=null;
  HashMap<String, Tablet> apple=null;
  HashMap<String, Tablet> asus=null;
  HashMap<String, Tablet> lenovo=null;
  HashMap<String, Tablet> nexus=null;
  HashMap<String, Tablet> samsung=null;
  String maker = null;
  
  
 SaxParser4TabletXMLdataStore sx;
    public void init() {
           tab = new HashMap<String, Tablet>();
		   apple = new HashMap<String, Tablet>();
		   asus = new HashMap<String, Tablet>();
		   lenovo = new HashMap<String, Tablet>();
		   nexus = new HashMap<String, Tablet>();
		   samsung = new HashMap<String, Tablet>();
		   
           String filepath = "C:/apache-tomcat-7.0.34/webapps/bestdeal/WEB-INF/classes/Tablet.xml";
			sx = new SaxParser4TabletXMLdataStore(filepath);
        	tab = sx.prettyPrint();
			
			for(String key: tab.keySet()){
				Tablet l = tab.get(key);
				if(l.getName().equals("samsung"))
					samsung.put(key,l);
				else if(l.getName().equals("asus"))
					asus.put(key,l);
				else if(l.getName().equals("apple"))
					apple.put(key,l);
				else if(l.getName().equals("lenovo"))
					lenovo.put(key,l);
				else if (l.getName().equals("nexus"))
					nexus.put(key,l);
				
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
        
    }



protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	
		
                HttpSession session = request.getSession(true);
 		        response.setContentType("text/html");
                PrintWriter out = response.getWriter();
				
				HashMap<String, Tablet> temp = new HashMap<String, Tablet>(); 
				maker = request.getParameter("maker");
			    temp = sx.prettyPrint();
				String user = (String)session.getValue("userid");
			    String usertype = (String)session.getValue("usertype");
				if (maker!=null){
				if(maker.equals("asus"))
					tab=new HashMap<String,Tablet>(asus);
				else if(maker.equals("samsung"))
					tab = new HashMap<String,Tablet>(samsung);
				else if(maker.equals("apple"))
					tab = new HashMap<String,Tablet>(apple);
				else if (maker.equals("lenovo"))
					tab = new HashMap<String,Tablet>(lenovo);
				else if (maker.equals("nexus"))
					tab = new HashMap<String,Tablet>(nexus);
		        }
				maker = null;
				
				
		
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
                             "<h2>Shopping Online Made Easy</h2>\n");
							 if(user!=null){
								out.println("<h3 style=\"text-align:right\">");
								out.println("Welcome " + user);
								out.println("<a href=\"/bestdeal/TrackOrder\">Track Order</a>\n");
								out.println("</h3>");
								}
                             out.println("</header> \n" +
                             "<nav>\n" +
                             "<ul>\n");
							 
        	                   if(user==null) 
								out.println("<li class=\"start selected\"><a href=\"http://localhost/bestdeal/Home\">Home</a></li> \n");				 
						     else 
							 out.println( 
        	             "<li class=\"start selected\"><a href=\"\">Home</a></li> \n");
						out.println("<li class=\"\"><a href=\"http://localhost/bestdeal/LaptopServlet\">Laptop</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/TelevisionServlet\">TV</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/TabletServlet\">Tablet</a></li> \n" +
						"<li><a href=\"http://localhost/bestdeal/MobileServlet\">Smart Phone</a></li>\n"); 	             
	
                             out.println("<li class=\"text\">\n" +
                             "<form method=\"get\" class=\"searchform\" action=\"#\" >\n" +
                                "<p> \n" +
                                   " <input type=\"text\" name=\"search\" placeholder=\"Search\" size=\"30\" style=\"height:25px\" value=\"\" name=\"s\" class=\"s\" /> \n" +                                   
                               "</p> \n" +
                            " </form> \n" +								
			                 "</li> \n" );
							if(user==null)
					     	out.println("<li><a href=\"Login.html\">Login</a></li>\n" +	
					    	"<li><a href=\"Signup.html\">SignUp</a></li>	\n" );
                            else{
							out.println("<li><a href=\"AccountServlet\">Account</a></li>\n" );		
                            out.println("<li><a href=\"LogoutServlet\">Logout</a></li>\n" );	
							}
                             out.println("<li class=\"end\"><a>cart</li>\n" +
                             "</ul>\n" +
                             "</nav>\n");
							  out.println("<div id=\"body\">\n" +
							 "	<section id=\"content\"> \n");
							 String url = "/bestdeal/OrderPage";
							for (String tabletid: tab.keySet()) {
							Tablet tp = tab.get(tabletid);
							out.println("<FORM action=\""+url+"\">\n" +
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +tp.getId()+"\">");	
                            out.println( "<table style=\"width:30%\">\n" );
						    out.println("<tr>");
							out.println("<td>");
							out.println("<img src=" +request.getContextPath()+ "/imagestablet/"+ tp.getImage());
							out.println( "alt=\"\" width=\"250\" height=\"200\">");
							out.println("</td>");
							out.println( "<td> ");
							out.println( "Product\n" );
							out.println(tp.getName());
							out.println("</td> \n"); 
							out.println( "<td> ");
						//	out.println( "Condition\n" );
						//	out.println(tp.getCondition());
						//	out.println("</td> \n"); 
							out.println( "<td> ");
							out.println( "Price\n" );
							out.println(tp.getPrice());
							
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
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +tp.getId()+"\">");
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
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +tp.getId()+"\">");
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
							out.println("</FORM>");
							} 
	
                            out.println("</section>\n" );
	                        out.println ("<aside class=\"sidebar\"> \n" +
				
						"<ul>	\n" +
						   "<li> \n" +
								"<h4>Laptop</h4>\n" +
								"<ul>\n" +
								"<li id=\"first\"><a href=\"http://localhost/bestdeal/LaptopServlet?maker=samsung\">Samsung</a></li>   \n" +             
								"	<li><a href=\"http://localhost/bestdeal/LaptopServlet?maker=lenovo\">Lenovo</a></li> \n" +
								"	<li><a href=\"http://localhost/bestdeal/LaptopServlet?maker=dell\">Dell</a></li> \n" +
								"	<li><a href=\"http://localhost/bestdeal/LaptopServlet?maker=hp\">HP</a></li> \n" +
								"	<li><a href=\"http://localhost/bestdeal/LaptopServlet?maker=asus\">ASUS</a></li> \n" +
								"</ul> \n" +
							"</li> \n" +
							
							"<li> \n" +
								"<h4>Tablets</h4> \n" +
								"<ul> \n" +
									"<li><a href=\"http://localhost/bestdeal/TabletServlet?maker=samsung\">Samsung</a></li>\n" +
									"<li><a href=\"http://localhost/bestdeal/TabletServlet?maker=lenovo\">Lenovo</a></li>\n" +
									"<li><a href=\"http://localhost/bestdeal/TabletServlet?maker=apple\">Apple</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/TabletServlet?maker=nexus\">Nexus</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/TabletServlet?maker=asus\">ASUS</a></li>\n" +
								"</ul> \n" +
							"</li> \n" +
							
							"<li> \n" +
								"<h4>SmartPhone</h4>\n" +
								"<ul> \n" +
									"<li><a href=\"http://localhost/bestdeal/MobileServlet?maker=samsung\">Samsung</a></li>\n" +
									"<li><a href=\"http://localhost/bestdeal/MobileServlet?maker=apple\">Apple</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/MobileServlet?maker=htc\">HTC</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/MobileServlet?maker=acer\">Acer</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/MobileServlet?maker=sony\">Sony</a></li> \n" +
								"</ul> \n" +
							
							"<li> \n" +
								"<h4>TV</h4>\n" +
												  
								"<ul> \n" +
									"<li><a href=\"http://localhost/bestdeal/TelevisionServlet?maker=samsung\">Samsung</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/TelevisionServlet?maker=apple\">Apple</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/TelevisionServlet?maker=lg\">LG</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/TelevisionServlet?maker=philips\">Philips</a></li> \n" +
									"<li><a href=\"http://localhost/bestdeal/TelevisionServlet?maker=sony\">Sony</a></li> \n" +
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
				"</footer> \n" );
   			  out.println(  "</div>\n" +
	
			                "</body>\n" +
	
			                "</html>\n");
				
			
				tab = new HashMap<String,Tablet>(temp);
                 
		}
        		
}





