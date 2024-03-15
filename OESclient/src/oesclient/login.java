package oesclient;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class login
{
  String ID;
  String password;

  public login()
  {
    ID="";
    password="";
  }
  public login(String id,String pass)
  {
    ID=id;
    password=pass;
  }
  public boolean verifyUser(Connection con)
  {
    try
    {
      String query = "select UserID,Password from Users where UserID=" + ID +
          "and Password=" + password;
      Statement s = con.createStatement();
      ResultSet r = s.executeQuery(query);
      boolean check=true;
      while(r.next())
      {
        if(r.getString(1)!= null && r.getString(2) != null)
        {
          check=true;
        }

      }
      if(check==true)
      {
        return true;
      }
      else
        return false;

    }catch(Exception e){}
    return false;

  }




}
