package oesclient;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
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

public class gkFrame extends JFrame {
  public ArrayList qs;
  public ArrayList ans;
   public ArrayList ops;
   public Socket socket;
    public Socket Rsocket;
   String user;
  int[] randomNums;
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
JFrame prev=new JFrame();
  public gkFrame(ArrayList a,ArrayList b,ArrayList o,Socket s,int[] n,String user,Socket rs,JFrame f) {
    try {
      qs=a;
      ans=b;
      ops=o;
      this.user=user;
      socket=s;
      prev=f;
      randomNums=n;
      Rsocket=rs;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("General knowledge Test");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 16));
    jLabel2.setText("Total Questions: 15");
    jButton1.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton1.setText("Start");
    jButton1.addActionListener(new gkFrame_jButton1_actionAdapter(this));
    jButton2.addActionListener(new gkFrame_jButton2_actionAdapter(this));
    jButton2.setText("Cancel");
    jButton2.addActionListener(new gkFrame_jButton2_actionAdapter(this));
    jButton2.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton3.addActionListener(new takeTest_jButton3_actionAdapter(this));
    jButton3.setText("Exit");
    jButton3.addActionListener(new takeTest_jButton3_actionAdapter(this));
    jButton3.setFont(new java.awt.Font("Dialog", 0, 14));
    this.getContentPane().add(jLabel1,  new XYConstraints(194, 82, 258, 37));
    this.getContentPane().add(jLabel2,  new XYConstraints(257, 156, 228, 25));
    this.getContentPane().add(jButton1,  new XYConstraints(279, 235, 95, 32));
    this.getContentPane().add(jButton2,    new XYConstraints(279, 300, 95, 32));
    this.getContentPane().add(jButton3,    new XYConstraints(4, 6, 94, 38));
    this.setSize(650,400);
    this.setTitle("Start Test");
  }


  void jButton1_actionPerformed(ActionEvent e)
  {
    gkFrame1 frame=new gkFrame1(qs,ans,ops,socket,randomNums,user, Rsocket,prev);
    this.setVisible(false);
    frame.show();
  }

  void jButton3_actionPerformed(ActionEvent e) {
    try
        {
          PrintStream out = new PrintStream(socket.getOutputStream());
          out.println("Exit#1");
          socket.close();
          Rsocket.close();
          this.setVisible(false);
        }catch(Exception i){}
      }

  void jButton2_actionPerformed(ActionEvent e) {
this.setVisible(false);
    prev.setVisible(true);
  }

  }


class gkFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  gkFrame adaptee;

  gkFrame_jButton1_actionAdapter(gkFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class takeTest_jButton3_actionAdapter implements java.awt.event.ActionListener {
  gkFrame adaptee;

  takeTest_jButton3_actionAdapter(gkFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class gkFrame_jButton2_actionAdapter implements java.awt.event.ActionListener {
  gkFrame adaptee;

  gkFrame_jButton2_actionAdapter(gkFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

