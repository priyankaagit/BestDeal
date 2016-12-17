/*
 * Product.java
 *
 */
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MobileServlet extends HttpServlet {
   
  HashMap<String, Mobile> mb=null;
  HashMap<String, Mobile> acer =null;
  HashMap<String, Mobile> apple =null;
  HashMap<String, Mobile> htc =null;
  HashMap<String, Mobile> samsung =null;
  HashMap<String, Mobile> sony =null;
   String maker = null;
  
  
 SaxParser4MobileXMLdataStore sx;
    public void init() {
           mb = new HashMap<String, Mobile>();
		   acer = new HashMap<String, Mobile>();
		   apple = new HashMap<String, Mobile>();
		   htc = new HashMap<String, Mobile>();
		   samsung = new HashMap<String, Mobile>();
		   sony = new HashMap<String, Mobile>();
		   
           String filepath = "C:/apache-tomcat-7.0.34/webapps/bestdeal/WEB-INF/classes/Mobile.xml";
			sx = new SaxParser4MobileXMLdataStore(filepath);
        	mb = sx.prettyPrint();
			
			for(String key: mb.keySet()){
				Mobile l = mb.get(key);
				if(l.getName().equals("samsung"))
					samsung.put(key,l);
				else if(l.getName().equals("acer"))
					acer.put(key,l);
				else if(l.getName().equals("apple"))
					apple.put(key,l);
				else if(l.getName().equals("htc"))
					htc.put(key,l);
				else if (l.getName().equals("sony"))
					sony.put(key,l);
				
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
				HashMap<String, Mobile> temp = new HashMap<String, Mobile>(); ;
				maker = request.getParameter("maker");
			    temp = sx.prettyPrint();
			    String user = (String)session.getValue("userid");
			    String usertype = (String)session.getValue("usertype");
				
				if (maker!=null){
				if(maker.equals("acer"))
					mb =new HashMap<String,Mobile>(acer);
				else if(maker.equals("samsung"))
					mb = new HashMap<String,Mobile>(samsung);
				else if(maker.equals("apple"))
					mb = new HashMap<String,Mobile>(apple);
				else if (maker.equals("htc"))
					mb = new HashMap<String,Mobile>(htc);
				else if (maker.equals("sony"))
					mb = new HashMap<String,Mobile>(sony);
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
                             "<h2>Shopping Online Made Easy</h2>\n" );
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
							}
						    //out.println("<li style=\"float:right\" class=\"end\"><a>Cart</li>\n"); 
						   
                             
                         out.println("<li class=\"end\"><a>cart</li>\n" +
                             "</ul>\n" +
                             "</nav>\n");
							 out.println("<div id=\"body\">\n" +
							 "	<section id=\"content\"> \n");
							 String url = "/bestdeal/OrderPage";
							for (String mobileid: mb.keySet()) {
							Mobile m = mb.get(mobileid);
							
                            out.println("<FORM action=\""+url+"\">\n" +
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +m.getId()+"\">");
							
                            out.println( "<table style=\"width:30%\">\n" );
						    out.println("<tr>");
							out.println("<td>");
							out.println("<img src=" +request.getContextPath()+ "/imagesphone/"+ m.getImage());
							out.println( "alt=\"\" width=\"250\" height=\"200\">");
							out.println("</td>");
							out.println( "<td> ");
							out.println( "Product\n" );
							out.println(m.getName());
							out.println("</td> \n"); 
							//out.println( "<td> ");
							//out.println( "Condition\n" );
							//out.println(m.getCondition());
							out.println("</td> \n"); 
							out.println( "<td> ");
							out.println( "Price\n" );
							out.println(m.getPrice());
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
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +m.getId()+"\">");
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
							"<input type=\"HIDDEN\" NAME=\"itemID\""+ "value=\"" +m.getId()+"\">");
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
							//out.println("</FORM>");
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
				
			 mb = new HashMap<String,Mobile>(temp);
				
                 
		}
        		
}





