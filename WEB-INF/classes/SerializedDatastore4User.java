import java.util.*;
import java.io.*;
public class SerializedDatastore4User{


// The known platforms

	public HashMap<String,User> userdetail =null; 
	SerializedDatastore4User(){
		userdetail  = new HashMap<String,User>();
	}

// Polulate the data for Microsoft into HashMap





public void  writeUserDataStore(User u){
	
HashMap<String, User> users = readUserDataStore();
    try{
		 
	userdetail.put(u.getloginid(),u);
    File userDataStore=new File("C:/apache-tomcat-7.0.34/webapps/bestdeal/WEB-INF/classes/UserDataStore");
    FileOutputStream fos=new FileOutputStream(userDataStore);
    ObjectOutputStream oos=new ObjectOutputStream(fos);

	
        oos.writeObject(userdetail);
        oos.flush();
        oos.close();
        fos.close();
		//userDataStore.close();
    }catch(Exception e){
		System.out.println("Could NOT Write to UserDataStore");
	}

}


// Read the HashMaps from the File GameSpeedDataStore

public  HashMap<String,User> readUserDataStore() {
HashMap<String,User> mapInFile = new HashMap<String,User>();
   
    try{
	InputStream userDataStore = SerializedDatastore4User.class.getResourceAsStream("UserDataStore");
      
        ObjectInputStream ois =new ObjectInputStream(userDataStore);

        mapInFile=(HashMap<String,User>)ois.readObject();

        ois.close();
       // fis.close();
        
        for(Map.Entry<String,User> m :mapInFile.entrySet()){
            	System.out.println(m.getKey());
		User u = m.getValue();
        System.out.println("\t Name : "+u.getloginid());
		System.out.println("\t Name : "+u.getfirstName());
		System.out.println("\t Phone : "+u.getphone());
		System.out.println("\t Accessories : ");
		
		

        }
    }catch(Exception e){}
return mapInFile ;
}

public static void main(String args[]){

}


}