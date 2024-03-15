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

public class gkFrame1 extends JFrame {
  public ArrayList qs;
  public ArrayList ans;
  public ArrayList ops;
  String user;
  int i;
  int score;

  public Socket socket;
  public Socket Rsocket;
  int[] num;
  int myQ[];
  String myAns[];
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JRadioButton jRadioButton3 = new JRadioButton();
  JRadioButton jRadioButton4 = new JRadioButton();
  JButton jButton1 = new JButton();
  JFrame prev=new JFrame();
  JButton jButton6 = new JButton();


  public gkFrame1(ArrayList a, ArrayList b, ArrayList o, Socket s, int[] n,String user,Socket rs,JFrame f) {
    try {
      this.user=user;
      qs = a;
      ans = b;
      ops = o;
      socket = s;
      Rsocket=rs;
       prev=f;
      num = n;
      myQ = new int[a.size()];
      myAns = new String[a.size()];
      i = 0;
      score=0;

      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Garamond", 0, 14));
    jLabel1.setText( (String) qs.get(num[i]));
    xYLayout1.setWidth(656);
    xYLayout1.setHeight(405);
    this.getContentPane().setLayout(xYLayout1);
    StringTokenizer token = new StringTokenizer( (String) ops.get(num[i]),
                                                "#");

    jRadioButton1.setFont(new java.awt.Font("Dialog", 0, 14));
    jRadioButton1.setText(token.nextToken());
    jRadioButton1.addActionListener(new gkFrame1_jRadioButton1_actionAdapter(this));
    jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 14));
    jRadioButton2.setText(token.nextToken());
    jRadioButton2.addActionListener(new gkFrame1_jRadioButton2_actionAdapter(this));
    jRadioButton3.setFont(new java.awt.Font("Dialog", 0, 14));
    jRadioButton3.setText(token.nextToken());
    jRadioButton3.addActionListener(new gkFrame1_jRadioButton3_actionAdapter(this));
    jRadioButton4.setFont(new java.awt.Font("Dialog", 0, 14));
    jRadioButton4.setText(token.nextToken());
    jRadioButton4.addActionListener(new gkFrame1_jRadioButton4_actionAdapter(this));

    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setText("Next");
    jButton1.addActionListener(new gkFrame1_jButton1_actionAdapter(this));
    jButton6.setText("Exit");
    //jButton6.addActionListener(new tests_jButton6_actionAdapter(this));
    //jButton6.addActionListener(new tests_jButton6_actionAdapter(this));
    this.getContentPane().add(jButton1,  new XYConstraints(283, 324, 83, 37));
    this.getContentPane().add(jRadioButton4, new XYConstraints(91, 257, -1, -1));
    this.getContentPane().add(jRadioButton1, new XYConstraints(90, 118, -1, -1));
    this.getContentPane().add(jRadioButton2, new XYConstraints(89, 166, -1, -1));
    this.getContentPane().add(jRadioButton3, new XYConstraints(90, 212, -1, -1));
    this.getContentPane().add(jLabel1,     new XYConstraints(97, 49, 537, 36));
    this.getContentPane().add(jButton6,    new XYConstraints(0, 1, 104, 38));
    this.setTitle("Question No. 1");
    this.setSize(650,400);
  }


  void jRadioButton1_actionPerformed(ActionEvent e) {
    //myAns[num[i]] = 1+"";
  }

  void jRadioButton2_actionPerformed(ActionEvent e) {
   // myAns[num[i]] = 2+"";
  }

  void jRadioButton3_actionPerformed(ActionEvent e) {
    //myAns[num[i]] = 3+"";
  }

  void jRadioButton4_actionPerformed(ActionEvent e) {
   // myAns[num[i]] = 4+"";
  }

  void jButton1_actionPerformed(ActionEvent e) {
    int countSelection=0;
//gkFrame2 frame=new gkFrame2(qs,ans,ops,socket,num,myAns);
    boolean selected=false;
    if (i < 15) {

      if(jRadioButton1.isSelected())
      {
        if(countSelection==0)
        {
          myAns[num[i]] = 1 + "";
        }
        jRadioButton1.doClick();
        selected=true;
        countSelection++;
      }
      if(jRadioButton2.isSelected())
      {
         if(countSelection==0)
         {
           myAns[num[i]] = 2 + "";
         }
        jRadioButton2.doClick();
        selected=true;
        countSelection++;
      }
      if(jRadioButton3.isSelected())
      {
        if(countSelection==0)
        {
          myAns[num[i]] = 3 + "";
        }
        jRadioButton3.doClick();
        selected=true;
        countSelection++;
      }
      if(jRadioButton4.isSelected())
      {
        if(countSelection==0)
        {
          myAns[num[i]] = 4 + "";
        }
        jRadioButton4.doClick();
        selected=true;
        countSelection++;
      }
    }

      if(selected)
      {
        if (countSelection == 1) {
          i++;
          if (i < 15) {
            StringTokenizer token = new StringTokenizer( (String) ops.get(num[i]),
                "#");
            this.setTitle("Question No. " + (i + 1));
            jLabel1.setText( (String) qs.get(num[i]));
            jRadioButton1.setText(token.nextToken());
            jRadioButton2.setText(token.nextToken());
            jRadioButton3.setText(token.nextToken());
            jRadioButton4.setText(token.nextToken());
          }

          else if (i == 15) {
            //jRadioButton1=null;
            //jRadioButton1.delete();
            jRadioButton1.setVisible(false);
            jRadioButton2.setVisible(false);
            jRadioButton3.setVisible(false);
            jRadioButton4.setVisible(false);
            jLabel1.setVisible(false);
            jButton1.setText("Results");
            i++;

          }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Ahan..No Mischief ! Choose only one answer","Many Options Selected",JOptionPane.ERROR_MESSAGE);
        }
      }
        else if(i>15) {
          int j = 0;
          while (j < num.length) {
            System.out.println("Question no.:" + num[j] + " Answer:" +
                               myAns[num[j]]);
            if (ans.get(num[j]).equals(myAns[num[j]])) {
              score++;
            }
            j++;
          }
          gkFrame2 frame = new gkFrame2(score, socket, user,Rsocket,prev);
          this.setVisible(false);
          frame.show();
          frame.store();
          System.out.println(score + " is score");

        }

    else if(!selected)
    {
      JOptionPane.showMessageDialog(null,"Oops!..You Missed an Answer","Option Missed",JOptionPane.ERROR_MESSAGE);
    }


  }

  void jButton6_actionPerformed(ActionEvent e) {
    try
           {
             PrintStream out = new PrintStream(socket.getOutputStream());
             out.println("Exit#1");
             socket.close();
             Rsocket.close();
             this.setVisible(false);
           }catch(Exception i){}
         }
}

class gkFrame1_jRadioButton1_actionAdapter implements java.awt.event.ActionListener {
  gkFrame1 adaptee;

  gkFrame1_jRadioButton1_actionAdapter(gkFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton1_actionPerformed(e);
  }
}

class gkFrame1_jRadioButton2_actionAdapter implements java.awt.event.ActionListener {
  gkFrame1 adaptee;

  gkFrame1_jRadioButton2_actionAdapter(gkFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton2_actionPerformed(e);
  }
}

class gkFrame1_jRadioButton3_actionAdapter implements java.awt.event.ActionListener {
  gkFrame1 adaptee;

  gkFrame1_jRadioButton3_actionAdapter(gkFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton3_actionPerformed(e);
  }
}

class gkFrame1_jRadioButton4_actionAdapter implements java.awt.event.ActionListener {
  gkFrame1 adaptee;

  gkFrame1_jRadioButton4_actionAdapter(gkFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton4_actionPerformed(e);
  }
}

class gkFrame1_jButton1_actionAdapter implements java.awt.event.ActionListener {
  gkFrame1 adaptee;

  gkFrame1_jButton1_actionAdapter(gkFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

/*class tests_jButton6_actionAdapter implements java.awt.event.ActionListener {
  gkFrame1 adaptee;

  tests_jButton6_actionAdapter(gkFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton6_actionPerformed(e);
  }
}*/
