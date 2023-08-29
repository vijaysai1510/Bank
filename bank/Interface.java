import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Interface extends Frame {
  private JButton deposit, withdraw, bal, chpin,lout;

  Interface() {
    JLabel l1 = new JLabel("Code Clause Bank");
    l1.setFont(new Font("Serif", Font.PLAIN, 35));
    l1.setBounds(280, 50, 400, 80);

    JLabel l = new JLabel("Welcome To Code Clause Bank");
    l.setFont(new Font("Serif", Font.PLAIN, 35));
    l.setBounds(180, 530, 900, 80);

    // deposit button
    deposit = new JButton("DEPOSIT");
    deposit.setBounds(120, 220, 200, 50);

    deposit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new deposit();

      }
    });
    // withdraw button
    withdraw = new JButton("WITHDRAW");
    withdraw.setBounds(120, 340, 200, 50);

    withdraw.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new withdraw();

      }
    });
    // bal button
    bal = new JButton("CHECK BALANCE");
    bal.setBounds(450, 220, 200, 50);

    bal.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new balance();

      }
    });
    // chpin button
    chpin = new JButton("CHANGE PIN");
    chpin.setBounds(450, 340, 200, 50);

    chpin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new change();

      }
    });
    lout = new JButton("LOG OUT");
    lout.setBounds(290, 460, 200, 50);
    lout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new bank();

      }
    });

    add(l1);
    add(deposit);
    add(withdraw);
    add(bal);
    add(chpin);
    add(l);
    add(lout);

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
    setBackground(Color.PINK);
  }

  public static void main(String[] args) {
    new Interface();
  }
}
