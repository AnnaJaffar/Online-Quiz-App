package oesclient;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class gk1
{
  public ArrayList qs;
  public ArrayList ans;
  public ArrayList ops;
  public Socket socket;
  int [] randomNumbers;
  String user;
  Socket Rsocket;
  JFrame prev=new JFrame();

  public gk1(ArrayList q,ArrayList a,ArrayList o,Socket s,String user,Socket r,JFrame f)
  {
    qs=q;
    ans=a;
    ops=o;
    socket=s;
    prev=f;
    this.user=user;
    randomNumbers=new int[15];
    Arrays.fill(randomNumbers,50);
    int keys[]=new int[100];
    Rsocket=r;
    Random numbers=new Random();
    int i=0;
    while(i<100)
    {
      System.out.println("while");
      int aa=numbers.nextInt(731);
      keys[i] = aa;
      i++;
    }
    i=0;
    int m=17;
    int ii=0;
    while(i<15)
    {
      if(ii==100)
      {
        ii=0;
      }
      int y=keys[ii]%m;
      if(y!=0)
      {
        randomNumbers[i] = y;
        i++;
      }
      ii++;
    }
    removeRepetition(randomNumbers);
    for(int u=0;u<15;u++)
    {
      System.out.println(randomNumbers[u]+"");
    }

    //gk1.main(null);
  }

  public void showFrame()
  {
    gkFrame start=new gkFrame(qs,ans,ops,socket,randomNumbers,user,Rsocket,prev);
    start.setVisible(true);
  }
  public void removeRepetition(int[] array)
  {
    int i=0;
    int j=1;
    int index=18;
     while(i<array.length)
     {
       while(j<array.length)
       {
         if(array[i]==array[j])
         {
           array[j]=index;
           index++;
           if(index==24)
           {
             index=1;
           }
         }
         j++;
       }
       i++;
       j=i+1;
     }
  }

}
