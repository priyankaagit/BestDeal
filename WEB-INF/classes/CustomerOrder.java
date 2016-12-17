import java.util.*;
public class CustomerOrder implements java.io.Serializable {

String OrderId;
String LoginId;
Integer TotalPrice;
java.util.Date OrderDate;
java.util.Date DeliveryDate;
ArrayList OrderItem;

CustomerOrder(String OrderId,String LoginId,Integer TotalPrice,java.util.Date OrderDate,java.util.Date DeliveryDate, ArrayList OrderItem){

	 
this.OrderId = OrderId;
this.LoginId = LoginId;
this.TotalPrice = TotalPrice;
this.OrderDate = OrderDate;
this.DeliveryDate = DeliveryDate;
this.OrderItem = OrderItem;
}

public void setOrderId(String OrderId){
       this.OrderId = OrderId;
}

public String getOrderId(){
       return OrderId;
}

public void setLoginId(String LoginId) {
       this.LoginId = LoginId;
}

public String getLoginId() {
        return LoginId;
}


public void setTotalPrice(Integer TotalPrice){
	this.TotalPrice = TotalPrice;
}
public Integer getTotalPrice(){
	return TotalPrice;
}

public void setOrderDate(java.util.Date OrderDate) {
       this.OrderDate = OrderDate;
}

public java.util.Date getOrderDate() {
       return OrderDate;
}

public void setDeliveryDate(java.util.Date DeliveryDate) {
       this.DeliveryDate = DeliveryDate;
}

public java.util.Date getDeliveryDate() {
    return DeliveryDate;
}

public void setOrderItem(ArrayList OrderItem){
	this.OrderItem = OrderItem;
}
public ArrayList getOrderItem(){
	return OrderItem;
}


}


