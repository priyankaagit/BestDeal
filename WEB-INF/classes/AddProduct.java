 
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class AddProduct extends HttpServlet {


MySqlDataStoreUtilities s = null;
SaxParser4ProductXMLdataStore sx = null;

public void init() {
				
				s  = new MySqlDataStoreUtilities();
				sx = new SaxParser4ProductXMLdataStore();
							 
			}

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		
		//ShoppingCart scart;
		//String user = (String)session.getValue("userid");
		//String usertype = request.getParameter("usertype");
		String user = (String)session.getValue("userid");
		String usertype = (String)session.getValue("usertype");
		String password = request.getParameter("password");
		String filepath = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Laptop.xml";
		 String filepath1 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Mobile.xml";
		 String filepath2 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Tablet.xml";
		 String filepath3 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Television.xml";
		 String prodType = null;
							 HashMap<String, Product> laptop = new HashMap<String, Product>();
							 HashMap<String, Product> mobile = new HashMap<String, Product>();
							 HashMap<String, Product> tablet = new HashMap<String, Product>();
							 HashMap<String, Product> television = new HashMap<String, Product>();
									
         				     HashMap<String,Product> products = new HashMap<String,Product>();
							 
							 
							 
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
				 
				
					//String productid = request.getParameter("productid");
					String retailer = request.getParameter("retailer");
					String productprice1 = request.getParameter("productprice");
					Integer productprice = Integer.parseInt(productprice1);
					String productname = request.getParameter("productname");
					String producttype = request.getParameter("producttype");
					String image = request.getParameter("image");
					boolean status = false; 
					//Product product = products.get(productid);
				    Integer prodCount1 = s.productCount()+1;	
                    String prodCount = 	prodCount1.toString();				
					Product prod = new Product();
					prod.setId(prodCount);
					prod.setRetailer(retailer);
					prod.setPrice(productprice);
					prod.setName(productname);
					prod.setImage(image);
				    
					

		     
		
		
		
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
							 
							 
		                    if (producttype.equals("Laptop")){
							  lapSession.put(prodCount,prod);	
							  s.insertProduct(prod,producttype)	;
							} else if (producttype.equals("Mobile")){
								mobSession.put(prodCount,prod);
								s.insertProduct(prod,producttype)	;
							} else if (producttype.equals("Tablet")){
								tabSession.put(prodCount,prod);
								s.insertProduct(prod,producttype)	;
							} else if (producttype.equals("Television")){
								tvSession.put(prodCount,prod);
								s.insertProduct(prod,producttype)	;
							}
			               
                            							
						    out.println("<p>Product added" + prodCount + "</p>");
                            out.println("</section>\n" );
	                        out.println (
					
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
