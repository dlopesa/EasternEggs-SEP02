package database;

import utility.Extra;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExtraDAO
{
  ArrayList<Extra> getExtrasByType(String type) throws SQLException;

}
