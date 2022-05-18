package utility;

import java.io.Serializable;

public class Extra implements Serializable
{
  private String name;
  private String type;

  public Extra(String name, String type)
  {
    this.name = name;
    this.type = type;
  }

  public String getName()
  {
    return name;
  }

  public String getType()
  {
    return type;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Extra copy()
  {
    return new Extra(name, type);
  }

  @Override public String toString()
  {
    return "Extra{" + "name='" + name + '\'' + ", type='" + type + '\'' + '}';
  }

}


