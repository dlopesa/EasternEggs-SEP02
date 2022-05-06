package utility;

import java.io.Serializable;

public class Extra implements Serializable
{
  private String name;

  public Extra(String name)
  {
    this.name = name;
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

