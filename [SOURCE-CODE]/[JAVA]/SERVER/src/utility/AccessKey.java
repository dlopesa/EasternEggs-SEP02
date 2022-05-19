package utility;

import java.io.Serializable;

public class AccessKey implements Serializable
{
  private String accessKey;
  private String permissionKey;

  public AccessKey(String accessKey, String permissionKey)
  {
    this.accessKey = accessKey;
    this.permissionKey = permissionKey;
  }

  public String getAccessKey()
  {
    return accessKey;
  }

  public void setAccessKey(String accessKey)
  {
    this.accessKey = accessKey;
  }

  public String getPermissionKey()
  {
    return permissionKey;
  }

  public void setPermission(String permissionKey)
  {
    this.permissionKey = permissionKey;
  }

  @Override public String toString()
  {
    return "AccessKey{" + "accessKey='" + accessKey + '\'' + ", permissionKey='"
        + permissionKey + '\'' + '}';
  }

}
