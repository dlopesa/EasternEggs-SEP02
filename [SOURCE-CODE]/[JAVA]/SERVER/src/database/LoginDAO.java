package database;

import utility.AccessKey;
import utility.Item;

import java.sql.SQLException;
import java.util.ArrayList;


public interface LoginDAO
{
  String getUserType(String pwd) throws SQLException;
  public ArrayList<AccessKey> getAllAccessKey() throws SQLException;
  public void removeAccessKey(AccessKey accessKey) throws SQLException;
  public void addAccessKey(AccessKey accessKey) throws SQLException;
}
