package utility;

import java.io.Serializable;

/**
 * A class that represents an access key.
 */
public class AccessKey implements Serializable
{
  private String accessKey;
  private String permissionKey;

  /**
   * Two arguments constructor.
   * @param accessKey - String that actually  is the key to login.
   * @param permissionKey - Strig that defines the type of permission.
   */
  public AccessKey(String accessKey, String permissionKey)
  {
    this.accessKey = accessKey;
    this.permissionKey = permissionKey;
  }

  /**
   *
   * @returns a String with an access key.
   */
  public String getAccessKey()
  {
    return accessKey;
  }

  /**
   * Setting the access key.
   * @param accessKey
   */
  public void setAccessKey(String accessKey)
  {
    this.accessKey = accessKey;
  }

  /**
   *
   * @return the type of permission in a String.
   */
  public String getPermissionKey()
  {
    return permissionKey;
  }

  public void setPermission(String permissionKey)
  {
    this.permissionKey = permissionKey;
  }

  /**
   *
   * @return a Strings that has all the information about an access key
   */
  @Override public String toString()
  {
    return "AccessKey{" + "accessKey='" + accessKey + '\'' + ", permissionKey='"
        + permissionKey + '\'' + '}';
  }
}
