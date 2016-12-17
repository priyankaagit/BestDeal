import java.util.*;
import java.io.*;
public class Cart{


// The known platforms

	public HashMap<String,ArrayList> cartdetail =null; 
	
	

// Polulate the data for Microsoft into HashMap

public void populateUser(){
	
	// Testing
	//cartdetail = new HashMap("priyanka",new ArrayList());

}




public void  writeCart(String userid, ArrayList cartdetail){
	HashMap<String, ArrayList> newCarts = new HashMap<String,ArrayList>();
ArrayList cart = readCart(userid);
 if (cart!=null)
 {
	 newCarts.put(userid,cartdetail);
 }	else{
	 newCarts = new HashMap<String, ArrayList>();
 } 

    try{
		 
	newCarts.put(userid,cartdetail);
    File userDataStore=new File("C:/apache-tomcat-7.0.34/webapps/bestdeal/WEB-INF/classes/CartDataStore");
    FileOutputStream fos=new FileOutputStream(userDataStore);
    ObjectOutputStream oos=new ObjectOutputStream(fos);

	
        oos.writeObject(newCarts);
        oos.flush();
        oos.close();
        fos.close();
		//userDataStore.close();
    }catch(Exception e){
		System.out.println("Could NOT Write to CartDataStore");
	}

}


// Read the HashMaps from the File GameSpeedDataStore

public  ArrayList readCart(String userid) {
HashMap<String,ArrayList> cart = null;
 ArrayList usercart = new ArrayList();

   
    try{
		cart = new HashMap<String,ArrayList>();
	InputStream userDataStore = SerializedDatastore4User.class.getResourceAsStream("CartDataStore");
      
        ObjectInputStream ois =new ObjectInputStream(userDataStore);

        cart =(HashMap<String,ArrayList>)ois.readObject();

        ois.close();
		usercart = cart.get("userid");
       // fis.close();
     /*   
        for(Map.Entry<String,User> m :cart.entrySet()){
            	System.out.println(m.getKey());
		User u = m.getValue();
    //    System.out.println("\t Name : "+u.getloginid());
	//	System.out.println("\t Name : "+u.getfirstName());
		
	//	System.out.println("\t Phone : "+u.getphone());
	//	System.out.println("\t Accessories : ");
		
		

        }
		
		*/
    }catch(Exception e){}
	
	
return usercart ;
}

public static void main(String args[]){
	
	
Cart s = new Cart();
ArrayList o = s.readCart("priyanka");
s.populateUser();

//s.readUserDataStore();

}
}


