package oesclient;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class gkFrame2 extends JFrame {
  int score;
  Socket socket;
  String user;
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JButton jButton5 = new JButton();
   public Socket Rsocket;
   JFrame prev=new JFrame();


  public gkFrame2(int s,Socket sock,String user,Socket rs,JFrame f) {
    try {
      score=s;
      socket=sock;
      Rsocket=rs;
      prev=f;
      this.user=user;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel1.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel1.setText("Your Score :");
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 16));
   jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
   int y=score;
   jLabel2.setText(y+" / 15");
    this.getContentPane().setLayout(xYLayout1);
    xYLayout1.setWidth(650);
    xYLayout1.setHeight(408);
    jButton1.setText("Exit");
    jButton1.addActionListener(new gkFrame2_jButton1_actionAdapter(this));
    jButton5.setText(" Back to Home");
    //jButton5.addActionListener(new tests_jButton5_actionAdapter(this));
    this.getContentPane().add(jButton1,         new XYConstraints(0, 0, 99, 38));
    this.getContentPane().add(jLabel1,   new XYConstraints(216, 131, 134, 32));
    this.getContentPane().add(jLabel2, new XYConstraints(345, 127, 86, 38));
    this.getContentPane().add(jButton5,      new XYConstraints(24, 342, 146, 38));
    this.setTitle("Result");
    this.setSize(650,400);
  }


  public void store()
  {
    try{
      PrintStream out = new PrintStream(socket.getOutputStream());
      out.println(score+"#"+user);
      System.out.println("score sent to database");
    }catch(Exception i){}
  }

  void jButton1_actionPerformed(ActionEvent e) {
    try
        {
          PrintStream out = new PrintStream(socket.getOutputStream());
          out.println("Exit#1");
          socket.close();
          Rsocket.close();
          this.setVisible(false);
        }catch(Exception i){}
      }

  void jButton5_actionPerformed(ActionEvent e) {
//back to home
    this.setVisible(false);
    prev.setVisible(true);
  }

  }



class gkFrame2_jButton1_actionAdapter implements java.awt.event.ActionListener {
  gkFrame2 adaptee;

  gkFrame2_jButton1_actionAdapter(gkFrame2 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

/*class tests_jButton5_actionAdapter implements java.awt.event.ActionListener {
  gkFrame2 adaptee;

  tests_jButton5_actionAdapter(gkFrame2 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}*/
