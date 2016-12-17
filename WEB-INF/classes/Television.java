import java.util.ArrayList;
import java.util.List;


public class Television {
    String retailer;
    String name;
    String id;
    String image;
    String condition;
    int price;
    List<String> accessories;

    public Television(){
        accessories=new ArrayList<String>();
    }

void setId(String id) {
	this.id = id;
}


void setRetailer(String retailer) {
	this.retailer = retailer;
}


void setImage(String image) {
	this.image = image;
}

void setCondition(String condition) {
	this.condition = condition;
}

void setPrice(int price) {
	this.price = price;
}




void setName(String name) {
	this.name = name;
}

String getId(){return id;}
String getName(){return name;}
String getImage() {
	return image;
}
String getCondition() {
	return this.condition;
}
int getPrice(){
return this.price;}



List getAccessories() {
	return accessories;
}

}
   