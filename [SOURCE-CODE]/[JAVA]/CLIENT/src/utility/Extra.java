package utility;

import java.io.Serializable;
import java.util.ArrayList;

public class Extra implements Serializable
{
  private String name;
  private ArrayList<String> availableFor;

  public Extra(String name, ArrayList<String> availableFor)
  {
    this.name = name;
    this.availableFor = availableFor;
  }

  public Extra(String name)
  {
    this.name = name;
    this.availableFor = new ArrayList<>();
  }

  public ArrayList<String> getAvailableTypes()
  {
    return availableFor;
  }

  public void addAvailableType(String type)
  {
    availableFor.add(type);
  }

  public void removeAvailableType(String type)
  {
    availableFor.remove(type);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Override public String toString()
  {
    return "Extra{" + "name='" + name + '\'' + '}';
  }
}

