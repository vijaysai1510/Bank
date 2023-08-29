import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class bank extends Frame {

  JLabel l1, l2, l3, l4;

  bank() {
    l1 = new JLabel("Code Clause Bank");
    l1.setFont(new Font("Serif", Font.PLAIN, 35));
    l1.setBounds(280, 50, 400, 80);

    l2 = new JLabel("New User?");
    l2.setFont(new Font("Serif", Font.PLAIN, 20));
    l2.setBounds(250, 200, 400, 80);

    JButton Regester = new JButton("Regester");
    Regester.setBounds(300, 280, 200, 50);

    l3 = new JLabel("Existing User?");
    l3.setFont(new Font("Serif", Font.PLAIN, 20));
    l3.setBounds(250, 330, 400, 80);

    JButton Signin = new JButton("Sign In");
    Signin.setBounds(300, 410, 200, 50);

    Regester.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new regester();
        
      }
    });

    Signin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new signin();
        
      }
    });

    add(l1);
    add(l2);
    add(Regester);
    add(l3);
    add(Signin);

    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(
              WindowEvent we) {
            System.exit(0);
          }
        });
    setSize(800, 800);
    setLayout(null);
    setVisible(true);
    setBackground(Color.pink);
  }

  public static void main(String[] args) {
    new bank();
  }
}