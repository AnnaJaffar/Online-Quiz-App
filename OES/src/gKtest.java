import java.net.*;
import java.sql.*;
import java.util.*;
import java.io.*;
public class gKtest
{
	public Socket Rsocket;
	public Socket Ssocket;
	public Connection con;
	String user;

	gKtest(Socket a,Socket b,Connection c,String user)
	{
		Rsocket=a;
		Ssocket=b;
		con=c;
		this.user=user;

	}

	public void sendQuestions()
	{
		try{

			String q=queryFactory.gKtest();
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(q);
			ArrayList quests=new ArrayList();
			ArrayList ans2quests=new ArrayList();
			ArrayList options=new ArrayList();
			quests.add(new String("none"));
			ans2quests.add(new String("none"));
			options.add(new String("none"));
			while(r.next())
			{
				//System.out.println(r.getString(1)+" Ans: "+r.getString(2));
				quests.add(r.getString(1));
				ans2quests.add(r.getString(2));
				options.add(r.getString(3));
			}
			ObjectOutputStream oos = new ObjectOutputStream(Ssocket.getOutputStream());
			oos.writeObject(quests);
			oos.writeObject(ans2quests);
			oos.writeObject(options);
			oos.writeObject(user);
			oos.flush();




		}catch(Exception u){}

	}

}