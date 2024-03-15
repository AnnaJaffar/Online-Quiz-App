package oesclient;

import javax.swing.*;
import java.awt.*;
//import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import com.borland.jbcl.layout.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class takeTest
    extends JFrame {
  JButton takeTest = new JButton();
  Socket socket;
  Socket Rsocket;
  JButton takeTest1 = new JButton();
  JButton jButton1 = new JButton();
  XYLayout xYLayout1 = new XYLayout();

  public takeTest(Socket sock, Socket rSock) {
    try {
      socket = sock;
      Rsocket = rSock;
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    takeTest.setFont(new java.awt.Font("Garamond", 1, 18));
    takeTest.setText("Take a Test");
    takeTest.addActionListener(new takeTest_takeTest_actionAdapter(this));
    this.getContentPane().setLayout(xYLayout1);
    xYLayout1.setWidth(650);
    xYLayout1.setHeight(450);
    this.setSize(650, 400);
    takeTest1.addActionListener(new takeTest1_takeTest1_actionAdapter(this));
    takeTest1.setText("View My Tests");
    //takeTest1.addActionListener(new takeTest1_takeTest1_actionAdapter(this));
    takeTest1.setFont(new java.awt.Font("Garamond", 1, 18));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 14));
    jButton1.setText("Exit");
    jButton1.addActionListener(new takeTest_jButton1_actionAdapter(this));
    this.getContentPane().add(takeTest1, new XYConstraints(229, 229, 201, 36));
    this.getContentPane().add(takeTest, new XYConstraints(227, 87, 204, 38));
    this.getContentPane().add(jButton1, new XYConstraints(1, 1, 94, 38));
    this.setTitle("Online Quiz Fun Home");

  }

  void takeTest_actionPerformed(ActionEvent e) {
    tests frame = new tests(socket, Rsocket, this);
    this.setVisible(false);
    frame.show();

  }

  void takeTest1_actionPerformed(ActionEvent e) {
    try {
      PrintStream out = new PrintStream(socket.getOutputStream());
      out.println("View#1");
      ObjectInputStream ois = new ObjectInputStream(Rsocket.getInputStream());
      ArrayList tests = (ArrayList) ois.readObject();
      ArrayList scores = (ArrayList) ois.readObject();
      testView frame = new testView(tests, scores, this);
      this.setVisible(false);
      frame.show();
      frame.view();
    }
    catch (Exception i) {}

  }

  void jButton1_actionPerformed(ActionEvent e) {
//Exit Button
    try {
      PrintStream out = new PrintStream(socket.getOutputStream());
      out.println("Exit#1");
      socket.close();
      Rsocket.close();
      this.setVisible(false);
    }
    catch (Exception i) {}
  }
}

class takeTest_takeTest_actionAdapter
    implements java.awt.event.ActionListener {
  takeTest adaptee;

  takeTest_takeTest_actionAdapter(takeTest adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.takeTest_actionPerformed(e);
  }
}

class takeTest1_takeTest1_actionAdapter
    implements java.awt.event.ActionListener {
  takeTest adaptee;

  takeTest1_takeTest1_actionAdapter(takeTest adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.takeTest1_actionPerformed(e);
  }
}

class takeTest_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  takeTest adaptee;

  takeTest_jButton1_actionAdapter(takeTest adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}
