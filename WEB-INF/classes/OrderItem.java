public class OrderItem implements java.io.Serializable {

String OrderId;
String ProductName;
Double ProductPrice;


OrderItem(String OrderId,String ProductName,Double ProductPrice){
	 
this.OrderId = OrderId;
this.ProductName = ProductName;
this.ProductPrice = ProductPrice;

}

public void setOrderId(String OrderId){
       this.OrderId = OrderId;
}

public String getOrderId(){
       return OrderId;
}

public void setProductName(String ProductName) {
       this.ProductName = ProductName;
}

public String getProductName() {
        return ProductName;
}

public void setProductPrice(Double ProductPrice) {
       this.ProductPrice = ProductPrice;
}

public Double getProductPrice() {
       return ProductPrice;
}

}


