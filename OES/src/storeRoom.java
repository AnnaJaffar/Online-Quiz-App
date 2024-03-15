import javax.swing.*;
import java.awt.*;
//import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;
public class storeRoom
{

	public void store(Connection c,int score,String user,String i)throws Exception
	{
		Statement s = c.createStatement();
		String q="select TestID from Scores where UserID='"+user+"'";
		ResultSet ru=s.executeQuery(q);
		ArrayList array=new ArrayList();
		while(ru.next())
		{
			String h=(String)ru.getString(1);
			System.out.println(h);
			array.add(h);

		}
		ru.close();
		Statement tt = c.createStatement();
		if(!(array.contains("test"+i)))
		{

			String query="insert into Scores values('"+user+"',"+"'test"+i+"','"+score+"')";
			int isDone=tt.executeUpdate(query);
			if(isDone>0)
			{
				System.out.println("\n\nScores Updated\n\n");
			}
		}
		else if(array.contains("test"+i))
		{
			String query="Update Scores set Score='"+score+"' where UserID='"+user+"' and TestID='test"+i+"'";
			int isDone=s.executeUpdate(query);
			if(isDone>0)
			{
				System.out.println("\n\n**update done on already taken test**\n\n");
			}
		}


	}

	public void sendView(Connection c,Socket socket,String user)
	{
		try
		{
			//System.out.println("In sendView Function");
			ArrayList Tests=new ArrayList();
			ArrayList Score=new ArrayList();
			Statement s = c.createStatement();
			String query="select UserID,TestID,Score from Scores where UserID='"+user+"'";
			ResultSet r=s.executeQuery(query);
			System.out.println("Query Successfully executed");
			while(r.next())
			{
				Tests.add(r.getString(2));
				Score.add(r.getString(3));
			}
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(Tests);
			oos.writeObject(Score);
			oos.flush();
		}catch(Exception y){}



	}
}