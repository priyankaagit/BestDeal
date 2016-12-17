
import java.util.*;
import java.io.*;
import java.sql.*;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.*;



public class MongoDBDataStoreUtilities{
	
    	static DBCollection myReviews;
	    public static void getConnection(){
		MongoClient mongo;
		mongo = new MongoClient("localhost",27017);
		
		DB db = mongo.getDB("CustomerReviews");
		myReviews = db.getCollection("myReviews");
	}
	
	
	public void insertReview(Review review){
		    String itemID = review.getitemID();
			System.out.println(itemID);
			String ProductModelName = review.getProductModelName();			
			String ProductCategory = review.getProductCategory();
			String ProductPrice = review.getProductPrice();
			String RetailerName = review.getRetailerName();
			String RetailerZip = review.getRetailerZip();
			String RetailerCity = review.getRetailerCity();
			String RetailerState = review.getRetailerState();
			String ProductOnSale = review.getProductOnSale();
			String ManufacturerName = review.getManufacturerName();
			String ManufacturerRebate = review.getManufacturerRebate();
			String UserId = review.getUserId();
			String UserAge = review.getUserAge();
			String UserGender = review.getUserGender();
			String UserOccupation = review.getUserOccupation();
			Integer ReviewRating = review.getReviewRating(); 
			String ReviewDate = review.getReviewDate();
			String ReviewText = review.getReviewText();				
			
           try{			
			getConnection();
			
			
			BasicDBObject doc  = new BasicDBObject("title","myReviews");
			
			doc.append("itemID",itemID);
			doc.append("ProductModelName",ProductModelName);
			doc.append("ProductCategory",ProductCategory);
			doc.append("ProductPrice",ProductPrice);
			doc.append("RetailerName",RetailerName);
			doc.append("RetailerZip",RetailerZip);
			doc.append("RetailerCity",RetailerCity);
			doc.append("RetailerState",RetailerState);
			doc.append("ProductOnSale",ProductOnSale);
			doc.append("ManufacturerName",ManufacturerName);
			doc.append("ManufacturerRebate",ManufacturerRebate);
			doc.append("UserId",UserId);
			doc.append("UserAge",UserAge);
			doc.append("UserGender",UserGender);
			doc.append("UserOccupation",UserOccupation);
			doc.append("ReviewRating",ReviewRating);
			doc.append("ReviewDate",ReviewDate);
			doc.append("ReviewText",ReviewText);				
			myReviews.insert(doc);
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
		
	}
	
	
	
	public HashMap<String,ArrayList<Review>> selectReview(){
	   HashMap<String,ArrayList<Review>> review = new HashMap<String,ArrayList<Review>>();
	   ArrayList<Review> prodReviews = null;
	   try{
		   getConnection();
		 
            DBCursor cursor = myReviews.find();
				  
		  
		   while(cursor.hasNext()){
			   BasicDBObject obj = (BasicDBObject)cursor.next();
			   String itemID = obj.getString("itemID");
			   String ProductModelName = obj.getString("ProductModelName");
			   String ProductCategory = obj.getString("ProductCategory");
			   String ProductPrice = obj.getString("ProductPrice");
			   String RetailerName = obj.getString("RetailerName");
			   String RetailerZip = obj.getString("RetailerZip");
			   String RetailerCity = obj.getString("RetailerCity");
			   String RetailerState = obj.getString("RetailerState");
			   String ProductOnSale = obj.getString("ProductOnSale");
			   String ManufacturerName = obj.getString("ManufacturerName");
			   String ManufacturerRebate = obj.getString("ManufacturerRebate");
			   String UserId = obj.getString("UserId");
			   String UserAge = obj.getString("UserAge");
			   String UserGender = obj.getString("UserGender");
			   String UserOccupation = obj.getString("UserOccupation");
			   Integer ReviewRating = Integer.parseInt(obj.getString("ReviewRating"));
			   String ReviewDate = obj.getString("ReviewDate");
			   String ReviewText = obj.getString("ReviewText");
			  // System.out.println("count");
			   
			   
			   Review u = new Review(itemID,ProductModelName,ProductCategory,ProductPrice,RetailerName,RetailerZip,
			                         RetailerCity,RetailerState,ProductOnSale,ManufacturerName,
									 ManufacturerRebate,UserId,UserAge,UserGender,UserOccupation,ReviewRating,ReviewDate,ReviewText);
			   
			    if(review.containsKey(itemID)){
					prodReviews = review.get(itemID);
					prodReviews.add(u);
				//	review.put(itemID,prodReviews);
				}else{
					prodReviews = new ArrayList<Review>();
					prodReviews.add(u);
					//review.put(itemID,prodReviews);
				}
			   
			   review.put(itemID,prodReviews);
		   }
		   
	     	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   
		
	   return review;
   }
   
   public AggregationOutput topfivezip(){
	   AggregationOutput aggregate = null;
	   try{
		   getConnection();
        DBObject groupFields = new BasicDBObject();
	    groupFields.put("_id", "$RetailerZip"); 
		groupFields.put("count",new BasicDBObject("$sum",1)); 										
		DBObject group = new BasicDBObject("$group", groupFields); 										
		DBObject projectFields = new BasicDBObject("_id", 0); 
		projectFields.put("RetailerZip", "$_id"); 
		projectFields.put("ReviewCount","$count"); 
		DBObject project = new BasicDBObject("$project", projectFields); 
		DBObject sort = new BasicDBObject(); 
		sort.put("ReviewCount",-1); 
		DBObject orderby=new BasicDBObject("$sort",sort); 
		DBObject limit=new BasicDBObject("$limit",5); 
		aggregate = myReviews.aggregate(group,project,orderby,limit); 
			   }
       catch(Exception e){
		   e.printStackTrace();
	   }
	   
	    for (DBObject result : aggregate.results()) { 
			BasicDBObject bobj = (BasicDBObject) result; 
			System.out.println(bobj.getString("RetailerZip")+bobj.getString("ReviewCount")); 

		}	
	   
     return aggregate;	   
   }
   
public AggregationOutput topfiveproduct(){
	   AggregationOutput aggregate1 = null;
	   try{
		getConnection();
        DBObject groupFields1 = new BasicDBObject();      
		groupFields1.put("_id", "$ProductModelName"); 
		groupFields1.put("count",new BasicDBObject("$sum",1)); 										
		DBObject group1 = new BasicDBObject("$group", groupFields1); 										
		DBObject projectFields1 = new BasicDBObject("_id", 0); 
		projectFields1.put("ProductModelName", "$_id"); 
		projectFields1.put("ReviewCount","$count"); 
		DBObject project1 = new BasicDBObject("$project", projectFields1); 
		DBObject sort1 = new BasicDBObject(); 
		sort1.put("ReviewCount",-1); 
		DBObject orderby1 =new BasicDBObject("$sort",sort1); 
		DBObject limit1 =new BasicDBObject("$limit",5); 
		aggregate1 = myReviews.aggregate(group1,project1,orderby1,limit1);  
			   }
       catch(Exception e){
		   e.printStackTrace();
	   }   
	   	   
     return aggregate1;	   
   }
  

  
public AggregationOutput topfiveproductliked(){
	   AggregationOutput aggregate2 = null;
	   try{
		getConnection();
        DBObject groupFields2 = new BasicDBObject();
		groupFields2.put("_id", "$ProductModelName"); 
		groupFields2.put("AvgRating",new BasicDBObject("$avg","$ReviewRating")); 										
		DBObject group2 = new BasicDBObject("$group", groupFields2); 										
		DBObject projectFields2 = new BasicDBObject("_id", 0); 
		projectFields2.put("ProductModelName", "$_id"); 
		projectFields2.put("ReviewRating","$AvgRating"); 
		DBObject project2 = new BasicDBObject("$project", projectFields2);										
		DBObject sort2 = new BasicDBObject(); 
		sort2.put("ReviewRating",-1); 
		DBObject orderby2 =new BasicDBObject("$sort",sort2); 
		DBObject limit2 =new BasicDBObject("$limit",5); 																				
		aggregate2 = myReviews.aggregate(group2,project2,orderby2,limit2);  
			   }
       catch(Exception e){
		   e.printStackTrace();
	   }   
	   	   
     return aggregate2;	   
   }
     
  
public static void main(String args[]){
	HashMap<String,ArrayList<Review>> user = new HashMap<String,ArrayList<Review>>();
	//Review u = new Review("001","priyanka","Singh","12345","priyanka@iit.com","mysql","Priyankajsr","customer","","","","","","","","","","");
	MongoDBDataStoreUtilities mysqlUtility = new MongoDBDataStoreUtilities();
	mysqlUtility.topfivezip();
	/*user = mysqlUtility.selectReview();
		 //for(Map.Entry<String,Review> m :user.entrySet()){
          //  	System.out.println(m.getKey());
		//Review u2 = m.getValue();
		for(Review u1: user.get("019")){
        System.out.println("\t ProdName : "+u1.getProductModelName());
		System.out.println("\t ProdCat : "+u1.getProductCategory());
		System.out.println("\t ReviewRating : "+u1.getReviewRating());
		}
		
	
//	mysqlUtility.insertReview(u);
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