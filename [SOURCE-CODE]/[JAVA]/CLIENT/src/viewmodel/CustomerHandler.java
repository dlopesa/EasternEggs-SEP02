package viewmodel;

import property.ItemProperty;

public class CustomerHandler
{
  private ItemProperty item;


  public void setItem(ItemProperty item)
  {
    this.item = item;
  }

  public ItemProperty getItem()
  {
    return item;
  }
}
