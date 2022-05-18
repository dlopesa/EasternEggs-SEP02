package database;

import java.sql.SQLException;

public interface ExtraDAO
{
  public void getExtrasByType(String type) throws SQLException;
}
