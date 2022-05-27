package property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utility.AccessKey;

/**
 * A class that represents an access key property
 */
public class AccessKeyProperty
{
  private StringProperty accessKeyS;
  private StringProperty permissionKey;

  /**
   * A two arguments constructor with an access key as a parameter
   * @param accessKey
   */
  public AccessKeyProperty(AccessKey accessKey)
  {
    accessKeyS = new SimpleStringProperty(accessKey.getAccessKey());
    permissionKey = new SimpleStringProperty(accessKey.getPermissionKey());
  }

  /**
   * Overloaded constructor with two arguments
   * @param accessKeyS
   * @param permissionKey
   */
  public AccessKeyProperty(StringProperty accessKeyS, StringProperty permissionKey)
  {
    this.accessKeyS = accessKeyS;
    this.permissionKey = permissionKey;
  }

  public StringProperty accessKeyProperty()
  {
    return accessKeyS;
  }

  public StringProperty permissionKeyProperty()
  {
    return permissionKey;
  }

  public StringProperty getPermissionKey()
  {
    return permissionKey;
  }

  public AccessKey getAccessKey()
  {
    return new AccessKey(accessKeyS.get(), permissionKey.get());
  }
}
