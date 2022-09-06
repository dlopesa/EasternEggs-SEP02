package database;

import utility.Extra;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExtraDAO
{
  ArrayList<Extra> getExtrasByType(String type) throws SQLException;
  void create(Extra extra) throws SQLException;
  void delete(Extra extra) throws SQLException;
  ArrayList<Extra> getAllExtras() throws SQLException;
}
