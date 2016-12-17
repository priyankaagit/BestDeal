
import java.util.*;

/** A shopping cart data structure used to track orders.
 *  The OrderPage servlet associates one of these carts
 *  with each user session.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */
 
public class ShoppingCart {
   ArrayList itemsOrdered;
  SaxParser4ProductXMLdataStore sx;
  
HashMap<String,Product> map;
  /** Builds an empty shopping cart. */
 Product item;
  public ShoppingCart() {
    itemsOrdered = new ArrayList();
    map = new HashMap<String,Product>();
    //String filepath = "C:/apache-tomcat-7.0.34/webapps/bestdeal/WEB-INF/classes/Laptop.xml";
	String filepath1 = "C:/apache-tomcat-7.0.34/webapps/bestdealjsp/WEB-INF/classes/product/Product.xml";
    sx = new SaxParser4ProductXMLdataStore(filepath1);
    map = sx.prettyPrint();
	if(map.isEmpty()){
		System.out.println("map is empty");
	}

  }

  /** Returns List of ItemOrder entries giving
   *  Item and number ordered. Declared as List instead
   *  of ArrayList so that underlying implementation
   *  can be changed later.
   */
  
  public List getItemsOrdered() {
    return(itemsOrdered);
  }

  /** Looks through cart to see if it already contains
   *  an order entry corresponding to item ID. If it does,
   *  increments the number ordered. If not, looks up
   *  Item in catalog and adds an order entry for it.
   */
  
  public synchronized void addItem(String itemID) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemID().equals(itemID)) {
        order.incrementNumItems();
        return;
      }
    }

Product lap = map.get(itemID);
    ItemOrder newOrder = new ItemOrder(lap);
    itemsOrdered.add(newOrder);
  }

  /** Looks through cart to find order entry corresponding
   *  to item ID listed. If the designated number
   *  is positive, sets it. If designated number is 0
   *  (or, negative due to a user input error), deletes
   *  item from cart.
   */
  
  public synchronized void setNumOrdered(String itemID,
                                         int numOrdered) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemID().equals(itemID)) {
        if (numOrdered <= 0) {
          itemsOrdered.remove(i);
        } else {
          order.setNumItems(numOrdered);
        }
        return;
      }
    }
   Product lap = map.get(itemID);
    ItemOrder newOrder = new ItemOrder(lap);
    itemsOrdered.add(newOrder);
  }
}
    
