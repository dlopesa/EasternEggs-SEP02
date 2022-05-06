package utility;

public class EqualsTest
{
  public static void main(String[] args)
  {
    Item coffee = new Item(1, "Coffee","coffee",20,":)");
    Item coffee2 = new Item(1, "Coffee","coffee",20,":)");
    System.out.println(coffee.equals(coffee2));

    ItemList itemList = new ItemList();
    ItemList itemList2 = new ItemList();
    itemList.add(coffee);
    itemList2.add(coffee);

    System.out.println(itemList.equals(itemList2));

    Order order1 = new Order(false);

    order1.addItem(coffee);

    Order order2 = order1.copy();

    System.out.println(order1);
    System.out.println(order2);

    System.out.println(order1.equals(order2));
  }
}
