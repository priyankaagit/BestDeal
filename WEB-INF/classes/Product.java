

import java.util.ArrayList;
import java.util.List;


public class Product {
    public String retailer;
    public String name;
    public String id;
    public String image;
    public String condition;
    public int price;
    public List<String> accessories;

    public Product(){
        accessories=new ArrayList<String>();
    }

public void setId(String id) {
	this.id = id;
}
public String getId(){
	return id;
	}

public void setRetailer(String retailer) {
	this.retailer = retailer;
}

public String getRetailer(){
	return retailer;
}

public void setImage(String image) {
	this.image = image;
}

public String getImage() {
	return image;
}
public void setCondition(String condition) {
	this.condition = condition;
}

public String getCondition() {
	return this.condition;
}

public void setPrice(int price) {
	this.price = price;
}

public int getPrice(){
return this.price;
}

public void setName(String name) {
	this.name = name;
}
public String getName(){
	return name;
}

public List getAccessories() {
	return accessories;
}

}
   