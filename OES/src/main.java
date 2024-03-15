
import javax.swing.*;
import java.awt.*;
//import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class main extends Thread
{
	public Socket s;
	public static ServerSocket server;

	public main()
	{
	}

    public static void main(String[] args)throws Exception
    {
    	server=new ServerSocket(9987);
    	main thread=new main();
    	thread.start();
	}

	public void run()
	{
		while(true)
		{
			try
			{
				s=server.accept();
				int sendingPort=read_From_File();
				Write_to_File(sendingPort+1);
				new queryServer(s,sendingPort).start();
				System.out.println("New User or Sign Up");

			}catch(Exception e){}
		}
	}

  	public static void Write_to_File(int port)throws IOException
	{
		File F=new File("D:\\my documents\\BI061004\\acp lab works\\Final Project OES\\Port.txt");
		FileWriter out=new FileWriter(F);
		out.write(""+port);
		out.close();
	}

  	public static int read_From_File()throws IOException
  	{
  		File F=new File("D:\\my documents\\BI061004\\acp lab works\\Final Project OES\\Port.txt");
  		int port=0;
  		if(F.isFile())
  		{
  			BufferedReader in=new BufferedReader(new FileReader(F));
  			String line=null;
  			while((line=in.readLine()) != null)
  			{
  				port=Integer.parseInt(line);
  			}
  		}
  		return port;
	}

}
