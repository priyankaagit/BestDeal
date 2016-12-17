
import java.util.*;
import java.io.*;
import java.sql.*;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class MySqlDataStoreUtilities 
{ 
    Connection conn = null; 
   /* public void getConnection() 
       { try {
	          Class.forName("com.mysql.jdbc.Driver").newInstance(); 
              conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase" ,"root","root"); 
          } catch(Exception e) {} 

	   }   
     */         


public void insertUser(User user){
	String firstname = user.getfirstName();
	String lastname = user.getlastname();
	String phone = user.getphone();
	String email = user.getemail();
	String loginid = user.getloginid();
	String password = user.getpassword();
	String usertype = user.getusertype();
	
	
	try
	{ 
	 // getConnection();
	  Class.forName("com.mysql.jdbc.Driver").newInstance();
	  conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase" ,"root","root"); 
	  String insertIntoCustomerRegisterQuery= "INSERT INTO Registration(firstname,lastname,phone,email,password,loginid,usertype) " + "VALUES (?,?,?,?,?,?,?);";
	  PreparedStatement pst= conn.prepareStatement(insertIntoCustomerRegisterQuery);
	  //System.out.println(insertIntoCustomerRegisterQuery);
	  pst.setString(1,firstname); 
	  pst.setString(2,lastname); 
	  pst.setString(3,phone);
	  pst.setString(4,email);
	  pst.setString(5,password);
	  pst.setString(6,loginid);
	  pst.setString(7,usertype);
	  pst.execute();
	  pst.close();
    } 
    catch(Exception e){
		e.printStackTrace();
	}
}



   public boolean checkStatus(String user){
	   
	   boolean status = false;
	   try {
	   Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase" ,"root","root"); 
	   
	   
	   String checkCustomerQuery = "SELECT loginid from Registration where loginid =?;";   	   
	   PreparedStatement pst = conn.prepareStatement(checkCustomerQuery);
	   pst.setString(1,user);
	   ResultSet rs = pst.executeQuery();
	   while(rs.next()){
		  	String userid = (rs.getString("loginid"));

			if (user.equals(userid)){
			    status = true;			
			}
			else {
				status = false;				
			}
				 
	   }
		pst.close();
	   }
	   catch(Exception e){
		e.printStackTrace();
	   }
	   
	  return status; 
	   
	   
   }
   
   public HashMap<String,User> selectUser(){
	   HashMap<String,User> user = new HashMap<String,User>();
	   try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		  // User u = user.get(userid);
		   String selectCustomerQuery = "SELECT * from Registration ;";
		   PreparedStatement pst = conn.prepareStatement(selectCustomerQuery);
		  // pst.setString(1,userid);		  
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   String userid = rs.getString("loginid");
			   String firstname = rs.getString("firstname");
			   String lastname = rs.getString("lastname");
			   String phone = rs.getString("phone");
			   String email = rs.getString("email");
			   String password = rs.getString("password");
			   String usertype = rs.getString("usertype");
			   //System.out.println(userid);
			   
			   
			   User u = new User(firstname,lastname,phone,email,password,userid,usertype);
			   user.put(userid,u);
		   }
		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   
		
	   return user;
   }
   
  
public void insertCustomerOrder(String orderid,String userid,Double price, Date orderdate, Date deliverydate){
	
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		String insertOrderidQuery = "INSERT INTO customerorder(orderid, loginid, totalprice, orderdate,deliverydate)" + "VALUES(?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(insertOrderidQuery);
		//java.sql.Date neworderdate = new java.sql.Date(orderdate.getTime());
		pst.setString(1,orderid);
		pst.setString(2,userid);
		pst.setDouble(3,price);
		pst.setDate(4,orderdate);
		pst.setDate(5,deliverydate);
		
		pst.execute();
		pst.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
}
   
 public void insertCustomerAccount(String user, String add, String cardno, String cardtype) {
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		String insertOrderidQuery = "INSERT INTO customeraccount(address,creditnumber,loginid,cardtype)" + "VALUES(?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(insertOrderidQuery);
		pst.setString(1,add);
		pst.setString(2,cardno);
		pst.setString(3,user);
		pst.setString(4,cardtype);		
		pst.execute();
		pst.close();
	}
	catch(Exception e){
		e.printStackTrace();
	} 	 
	 
 } 
 public void insertOrderItem(String orderId, ArrayList<ItemOrder> order){
	 
	 
	 try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		 PreparedStatement pst = null;
		 for(int i = 0;i < order.size(); i++){	
            String name = order.get(i).getItemID();
            Double price = order.get(i).getUnitCost();			
		    
		 String insertOrderItemQuery = "INSERT INTO orderitem(orderid, productname, productprice)" + "VALUES(?,?,?)";
		 pst = conn.prepareStatement(insertOrderItemQuery);
		 pst.setString(1,orderId);
		 pst.setString(2,name);
		 pst.setDouble(3,price);
		 pst.execute();
		 
		 }
		 pst.close();
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 
 } 

 public void insertCustomerOrderItem(String orderId, ArrayList<OrderItem> order){
	 
	 
	 try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		 PreparedStatement pst = null;
		 for(int i = 0;i < order.size(); i++){	
            //String name = order.get(i).getOrderId();
			 String name = order.get(i).getProductName();
            Double price = order.get(i).getProductPrice();			
		    
		 String insertOrderItemQuery = "INSERT INTO orderitem(orderid, productname, productprice)" + "VALUES(?,?,?)";
		 pst = conn.prepareStatement(insertOrderItemQuery);
		 pst.setString(1,orderId);
		 pst.setString(2,name);
		 pst.setDouble(3,price);
		 pst.execute();
		 
		 }
		 pst.close();
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 
 }
 
 public HashMap<String,CustomerOrder> trackOrder(String user){
	     HashMap<String,CustomerOrder> order = new HashMap<String,CustomerOrder>();
	     ArrayList <OrderItem> orditem = null;
		 
	 try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		  // User u = user.get(userid);
		   String selectCustomerOrderQuery = "SELECT * from customerorder where loginid =?";
		   String selectOrderItemQuery = "SELECT * from orderitem where orderid =?";		   
		   PreparedStatement pst = conn.prepareStatement(selectCustomerOrderQuery);
		   pst.setString(1,user);	
           		   
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   String orderid = rs.getString("orderid");
			   String loginid = rs.getString("loginid");
			   Integer totalprice = rs.getInt("totalprice");
			   java.util.Date orderdate = rs.getDate("orderdate");
			   java.util.Date deliverydate = rs.getDate("deliverydate");
			   PreparedStatement ps = conn.prepareStatement(selectOrderItemQuery);
		       ps.setString(1,orderid);	           		   
		       ResultSet r = ps.executeQuery();  
			   orditem = new ArrayList<OrderItem>();
			   while(r.next()){
				   String productname = r.getString("productname");
				   Double productprice = r.getDouble("productprice");
				   OrderItem o = new OrderItem(orderid,productname,productprice);
				   orditem.add(o);
			   }
			    CustomerOrder c = new CustomerOrder(orderid,loginid,totalprice,orderdate,deliverydate,orditem);	  			    			  
			    order.put(orderid,c);
		   }
		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	 return order;
 }

public HashMap<String,CustomerOrder> selecttrackOrder(String orderid1){
	     HashMap<String,CustomerOrder> order = new HashMap<String,CustomerOrder>();
	     ArrayList <OrderItem> orditem = null;
		 
	 try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		  // User u = user.get(userid);
		   String selectCustomerOrderQuery = "SELECT * from customerorder where orderid =?";
		   String selectOrderItemQuery = "SELECT * from orderitem where orderid =?";		   
		   PreparedStatement pst = conn.prepareStatement(selectCustomerOrderQuery);
		   pst.setString(1,orderid1);	
           		   
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   String orderid = rs.getString("orderid");
			   String loginid = rs.getString("loginid");
			   Integer totalprice = rs.getInt("totalprice");
			   java.util.Date orderdate = rs.getDate("orderdate");
			   java.util.Date deliverydate = rs.getDate("deliverydate");
			   PreparedStatement ps = conn.prepareStatement(selectOrderItemQuery);
		       ps.setString(1,orderid);	           		   
		       ResultSet r = ps.executeQuery();  
			   orditem = new ArrayList<OrderItem>();
			   while(r.next()){
				   String productname = r.getString("productname");
				   Double productprice = r.getDouble("productprice");
				   OrderItem o = new OrderItem(orderid,productname,productprice);
				   orditem.add(o);
			   }
			    CustomerOrder c = new CustomerOrder(orderid,loginid,totalprice,orderdate,deliverydate,orditem);	  			    			  
			    order.put(orderid,c);
				
		   }
		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	 return order;
 }
 
public void cancelOrder(String orderid){
	     
		 try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");		
		   String deleteCustomerOrderQuery = "DELETE from customerorder where orderid =?";
		   String deleteOrderItemQuery = "DELETE from orderitem where orderid =?";		   
		   PreparedStatement pst = conn.prepareStatement(deleteCustomerOrderQuery);
		   pst.setString(1,orderid);           		   
		   pst.execute();
		   PreparedStatement ps = conn.prepareStatement(deleteOrderItemQuery);
		   ps.setString(1,orderid);	           		   
		   ps.execute(); 
           pst.close();		   
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	
}



public void updateOrderItem(String orderId, String prodname, Double price){
	 
	 
	 try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		 String updateOrderItemQuery = "UPDATE orderitem SET productprice = ? WHERE productname = ? and orderid = ?";
		 PreparedStatement pst = conn.prepareStatement(updateOrderItemQuery);
		 pst.setDouble(1,price);
		 pst.setString(2,prodname.trim());
		 pst.setString(3,orderId.trim());
		 pst.executeUpdate();
		 pst.close();
		 
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 
 } 


public void insertProduct(Product product, String prodType){
	String retailer = product.getRetailer();
	String name = product.getName();
	String id = product.getId();
	Integer price = product.getPrice();
	String image = product.getImage();
	String condition = product.getCondition();
	
	
	
	try
	{ 
	 // getConnection();
	  Class.forName("com.mysql.jdbc.Driver").newInstance();
	  conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase" ,"root","root"); 
	  String insertIntoPoductQuery= "INSERT INTO product(productid,retailer,productprice,productname,producttype,image) " + "VALUES (?,?,?,?,?,?);";
	  PreparedStatement pst= conn.prepareStatement(insertIntoPoductQuery);
	  //System.out.println(insertIntoCustomerRegisterQuery);
	  pst.setString(1,id); 
	  pst.setString(2,retailer);
      pst.setInt(3,price);	  	  
	  pst.setString(4,name);
	  pst.setString(5,prodType);
      pst.setString(6,image);	  
	  pst.execute();
	  pst.close();
    } 
    catch(Exception e){
		e.printStackTrace();
	}
}
 
 public Integer productCount(){
	   
	   
	   try {
	   Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase" ,"root","root"); 
	   
	   
	   String productCount = "SELECT MAX(productid) from product ";   	   
	   PreparedStatement pst = conn.prepareStatement(productCount);
	   //pst.setString(1,user);
	   ResultSet rs = pst.executeQuery();
	   while(rs.next()){
		  	return rs.getInt(1);					 
	   }
		pst.close();
	   }
	   catch(Exception e){
		e.printStackTrace();
	   }
	   
	 return 0;
	   
	   
   }
 
 
 public void updateProduct(Product product, String prodType){
	String retailer = product.getRetailer();
	String name = product.getName();
	String id = product.getId();
	Integer price = product.getPrice();
	String condition = product.getCondition();
	String image = product.getImage();
	 
	 try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		 String updateProduct = "UPDATE product SET productprice = ?, productname = ?,image = ?,retailer = ? WHERE productid = ? and producttype = ?";
		 PreparedStatement pst = conn.prepareStatement(updateProduct);
		 pst.setInt(1,price);
		 pst.setString(2,name);
		 pst.setString(3,image);
		 pst.setString(4,retailer);
		 pst.setString(5,id);
		 pst.setString(6,prodType);
		 pst.executeUpdate();
		 pst.close();
		 
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 
 } 
 
 public void deleteProduct(String productid){
	
		 try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");		
		   String deleteProduct = "DELETE from product where productid =?";		  	   
		   PreparedStatement pst = conn.prepareStatement(deleteProduct);
		   pst.setString(1,productid);           		   
		   pst.execute();	 		  
           pst.close();		   
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	
}

public String productType(String productid){
	   
	   String prodType = null;
	   try {
	   Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase" ,"root","root"); 
	   
	   
	   String productType = "SELECT producttype from product where productid = ?";   	   
	   PreparedStatement pst = conn.prepareStatement(productType);
	   pst.setString(1,productid);
	   ResultSet rs = pst.executeQuery();
	   while(rs.next()){
		  	prodType =  rs.getString("producttype");					 
	   }
		pst.close();
	   }
	   catch(Exception e){
		e.printStackTrace();
	   }
	   
	 return prodType ;
	   
	   
   }
public void truncateProduct(){
	
		 try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");		
		   String truncateProduct = "TRUNCATE product";		  	   
		   PreparedStatement pst = conn.prepareStatement(truncateProduct);
		   //pst.setString(1,productid);           		   
		   pst.execute();	 		  
           pst.close();		   
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	
} 

public HashMap<String,Product> selectProduct(){
	   HashMap<String,Product> product = new HashMap<String,Product>();
	   try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
		 
		   String selectProductQuery = "SELECT * from product ;";
		   PreparedStatement pst = conn.prepareStatement(selectProductQuery);
		  		  
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   Product prod = new Product();
			   prod.setId(rs.getString("productid"));
			   prod.setRetailer(rs.getString("retailer"));
			   prod.setPrice(rs.getInt("productprice"));
			   prod.setName(rs.getString("productname"));
			   //prod.rs.getString("producttype");
			   prod.setImage(rs.getString("image"));		    
			   			   			   
			   product.put(rs.getString("productid"),prod);
		   }
		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   
		
	   return product;
   }

 
 public static void main(String args[]){
	HashMap<String,User> user = new HashMap<String,User>();
	User u = new User("priyanka","Singh","12345","priyanka@iit.com","mysql","Priyankajsr","customer");
	MySqlDataStoreUtilities mysqlUtility = new MySqlDataStoreUtilities();
	//mysqlUtility.updateOrderItem("772priyanka","004", 4000.00);
	/*user = mysqlUtility.selectUser();
		    for(Map.Entry<String,User> m :user.entrySet()){
            	System.out.println(m.getKey());
		User u1 = m.getValue();
        System.out.println("\t Name : "+u1.getloginid());
		System.out.println("\t Name : "+u1.getfirstName());
		System.out.println("\t Phone : "+u1.getphone());
			}
	//mysqlUtility.insertUser(u);
	//boolean status = mysqlUtility.checkStatus("priyankajsr");
   // System.out.println(status);	
	/*
	System.out.println(u.getloginid());
	System.out.println(u.getpassword());
	System.out.println(u.getusertype());
	System.out.println(u.getfirstName());
	System.out.println(u.getlastname());
	System.out.println(u.getemail());
	System.out.println(u.getphone());
	
	*/

	
	
   


    }
}