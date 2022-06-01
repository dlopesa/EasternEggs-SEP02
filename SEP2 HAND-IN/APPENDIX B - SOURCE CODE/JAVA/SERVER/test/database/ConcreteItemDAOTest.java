package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Item;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteItemDAOTest
{

  @BeforeEach void setUp()
  {
  }

  @Test void createItem()
  {
    Item item = new Item("Black tea", "coffee", 15, "Earl gray made in China.");
    assertDoesNotThrow(() -> ConcreteItemDAO.getInstance().createItem(item));
  }
}