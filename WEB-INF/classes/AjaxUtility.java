import java.util.*;
import java.io.*;
import java.sql.*;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class AjaxUtility{
	Connection conn = null; 
	
	
	
	public HashMap<String,Product> selectProduct(){
	   HashMap<String,Product> product = new HashMap<String,Product>();
	   try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		 
		   String selectCustomerQuery = "SELECT * from product ;";
		   PreparedStatement pst = conn.prepareStatement(selectCustomerQuery);
		  	  
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   String name = rs.getString("productname");
			   String id = rs.getString("productid");
			   String retailer = rs.getString("retailer");
			   Integer price = rs.getInt("productprice");
			   String image = rs.getString("image");
			   
			        Product prod = new Product();
					prod.setId(id);
					prod.setRetailer(retailer);
					prod.setPrice(price);
					prod.setName(name);
					prod.setImage(image);
			  
			   product.put(id,prod);
		   }
		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   
		
	   return product;
   }
	
	/*
	
	public StringBuffer readProduct(String searchId){
		StringBuffer sb = new StringBuffer();
		HashMap<String,Product> product = selectProduct();
		Iterator it = product.keySet().iterator();
		 while (it.hasNext()) {
			 //Map.Entry pi = (Map.Entry)it.next();
			 
			  String id = (String) it.next();
			  Product p = (Product)product.get(id);
              //Composer composer = (Composer) composers.get(id);
			 
			 
			 if(p.getName().toLowerCase().startsWith(searchId)){
				
						sb.append("<products>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getName() + "</productName>");
                        //sb.append("<lastName>" + composer.getLastName() + "</lastName>");
                        sb.append("</products>");				
				 
			 }
		
		
		
	}
   
	return sb;
}
*/

//public static void main(String args[]){
	// AjaxUtility a = new AjaxUtility();
	// HashMap<String,Product> product = a.selectProduct();
	// StringBuffer sb = new StringBuffer();
	// sb = a.readProduct("sa");
	 /*for(Map.Entry<String,Product> m :product.entrySet()){
            	System.out.println(m.getKey());
		Product u1 = m.getValue();
        System.out.println("\t id : "+u1.getId());
		System.out.println("\t Name : "+u1.getName());
	
			}
    */
	//System.out.println(sb);



}
