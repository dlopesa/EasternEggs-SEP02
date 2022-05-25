package utility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that represents an extra
 */
public class Extra implements Serializable
{
  private String name;
  private ArrayList<String> availableFor;

  /**
   * A two arguments constructor that takes the name of the extra as well as what items it's available for.
   * @param name
   * @param availableFor
   */
  public Extra(String name, ArrayList<String> availableFor)
  {
    this.name = name;
    this.availableFor = availableFor;
  }

  /**
   * Overloaded constructor taking only the name as an argument
   * @param name
   */
  public Extra(String name)
  {
    this.name = name;
    this.availableFor = new ArrayList<>();
  }

  /**
   *
   * @returns an String's Arraylist with the extras available for a specific type
   */
  public ArrayList<String> getAvailableTypes()
  {
    return availableFor;
  }

  /**
   * Adds a new type of item
   * @param type
   */
  public void addAvailableType(String type)
  {
    availableFor.add(type);
  }

  public void removeAvailableType(String type)
  {
    availableFor.remove(type);
  }

  /**
   *
   * @return a name of an extra
   */
  public String getName()
  {
    return name;
  }

  /**
   * Ses a name for an extra
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   *
   * @returns A string with all the infromation of an extra
   */
  @Override public String toString()
  {
    return "Extra{" + "name='" + name + '\'' + '}';
  }
}

