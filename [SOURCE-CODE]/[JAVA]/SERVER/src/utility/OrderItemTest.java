package utility;

public class OrderItemTest
{
  public static void main(String[] args)
  {
    Order order = new Order(false);
    Item coffee = new Item(1,"Coffee","coffee",20,":)");
    Item tea = new Item(2, "Tea", "tea", 7, "lovely herbal tea");
    order.addItem(coffee);
    order.addItem(tea);
    System.out.println(order);
    order.removeItem(coffee);
    System.out.println(order);
  }


}
