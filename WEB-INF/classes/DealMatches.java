import java.io.*;
import java.util.*;
import java.sql.*;


public class DealMatches {
	
	 MySqlDataStoreUtilities mysqlutil = new MySqlDataStoreUtilities();
	ArrayList lines = new ArrayList();
	public ArrayList getLines(){
		return lines;
	}
	
	public HashMap<String,Product> selectProductDeal(){
		
		//HashMap<String, Product> product = new HashMap<String, Product>();
		HashMap<String,Product> productDeal = new HashMap<String, Product>();
		productDeal = mysqlutil.selectProduct();
		String file = "C:/apache-tomcat-7.0.34/webapps/bestdeal/DealMatches.txt";
		String line;
		HashMap<String, Product> products = new HashMap<String, Product>();
		try{
		for (Map.Entry<String, Product>entry: productDeal.entrySet()){
			
			if (products.size()<2 && !products.containsKey(entry.getKey())){
				BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
				line = reader.readLine();
				if(line == null)
					break;
				do{
					if(line.contains(entry.getValue().getName())){
						products.put(entry.getKey(),entry.getValue());
						lines.add(line);
						break;
					}
				} while ((line = reader.readLine())!=null);
			
		}
		
	}
		}catch(Exception e){}
	return products;
	
	
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   
	
}