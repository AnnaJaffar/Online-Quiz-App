
import javax.swing.*;
import java.awt.*;
//import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class queryServer extends Thread
{
	Socket Rsocket;
	Socket Ssocket;
	Connection con;
	static ArrayList inPPL=new ArrayList();

	public queryServer(Socket s,int Sport)
	{
		try
		{
			Rsocket=s;
			Ssocket=new Socket(InetAddress.getLocalHost(),Sport);
			try
			{
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			}catch(ClassNotFoundException e){System.out.println("no jdbc drivers installed. ");}

			String filename = "D:\\my documents\\BI061004\\acp lab works\\Final Project OES\\OES.mdb";

			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database+= filename.trim() + ";DriverID=22;READONLY=true}";

			con=DriverManager.getConnection(database,"","");
		}catch(Exception y){}
	}
	public void run()
	{
		try{

			boolean check=true;
			int count=1;
			boolean verified=false;
			String User="";
			do
			{
				try{
					System.out.println("\n\nWaiting for message\n");

						Socket client;
						client = Rsocket;
						InputStream instr = client.getInputStream();
						InputStreamReader inread = new InputStreamReader(instr);
						BufferedReader inp = new BufferedReader(inread);
						String input=inp.readLine();
						//System.out.println(input);
						StringTokenizer token=new StringTokenizer(input,"#");
						String firstCommand=token.nextToken();
						System.out.println("\n\n "+firstCommand);
						if(firstCommand.compareToIgnoreCase("login")==0)
						{
							String a=token.nextToken();
							String b=token.nextToken();
							PrintStream out = new PrintStream(Ssocket.getOutputStream());
							if(!(inPPL.contains(a)))
							{

								//System.out.println(a+","+b);

								login user=new login(a,b);

								if(user.verifyUser(con))
								{
									 System.out.println(" user verified");
									 out.println("yes");
									 //System.out.println("sent");
									 verified=true;
									 User=user.ID;
									 inPPL.add(a);

								}
								else
								{
									System.out.println("Not verified");
									out.println("no");
									//check=false;
								}
							}
							else
							{
								System.out.println("Already signed in");
									out.println("alreadyIn");
							}
						}
						if(verified==true && firstCommand.compareToIgnoreCase("tests")==0)
						{
							int a=Integer.parseInt(token.nextToken());
							//System.out.println(a+"");
							if(a==1)
							{
								gKtest one=new gKtest(Rsocket,Ssocket,con,User);
								one.sendQuestions();
								String results=inp.readLine();
								StringTokenizer oke=new StringTokenizer(results,"#");
								storeRoom room=new storeRoom();
								room.store(con,Integer.parseInt(oke.nextToken()),oke.nextToken(),a+"");
							}
						}
						if(verified==true && firstCommand.compareToIgnoreCase("View")==0)
						{
							//System.out.println("entered in iF");
							storeRoom room=new storeRoom();
							room.sendView(con,Ssocket,User);
							//System.out.println("Views Sent");

						}
						if(verified==true && firstCommand.compareToIgnoreCase("Exit")==0)
						{
							System.out.println("Exiting..");
							int OutUser=inPPL.indexOf(User);
							inPPL.remove(OutUser);
							check=false;

						}
						if(firstCommand.compareToIgnoreCase("SignUp")==0)
						{

							Statement s=con.createStatement();

							String u="select UserID from Users";
							ResultSet r=s.executeQuery(u);
							ArrayList ids=new ArrayList();
							while(r.next())
							{
								ids.add(r.getString(1));

							}
							ObjectOutputStream oos = new ObjectOutputStream(Ssocket.getOutputStream());
							oos.writeObject(ids);
							oos.flush();
							String rply=inp.readLine();
							StringTokenizer oke=new StringTokenizer(rply,"#");
							String command=oke.nextToken();
							if(command.equals("info"))
							{
								String newID=oke.nextToken();
								String Pass=oke.nextToken();
								String query="insert into Users(UserID,Password) values('"+newID+"','"+Pass+"')";
								Statement ss=con.createStatement();
								int rr=ss.executeUpdate(query);
								if(rr>0)
								{
									System.out.println("New User Added");
									check=false;
								}
							}
							else if(command.equals("back"))
							{
								check=false;

							}

						}



					}catch(Exception u){ u.printStackTrace();}
			}while(check);

			Rsocket.close();
			Ssocket.close();
		}catch(Exception ii){}


	}
}



