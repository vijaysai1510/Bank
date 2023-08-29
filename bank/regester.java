import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;

public class regester extends Frame {
    private JLabel name, acc, pin, cpin;
    private JTextField tf1, tf2;

    String DB_URL = "jdbc:mysql://localhost:3306/bank";
    String USER = "root";
    String PASS = "root";

    regester() {
        JLabel l1 = new JLabel("Code Clause Bank");
        l1.setFont(new Font("Serif", Font.PLAIN, 35));
        l1.setBounds(280, 50, 400, 80);

        JLabel l2 = new JLabel("Regester");
        l2.setFont(new Font("Serif", Font.PLAIN, 35));
        l2.setBounds(350, 130, 400, 80);

        name = new JLabel("Name: ");
        name.setFont(new Font("Serif", Font.PLAIN, 20));
        name.setBounds(180, 200, 400, 80);
        tf1 = new JTextField();
        tf1.setBounds(240, 228, 400, 30);

        acc = new JLabel("Account Number: ");
        acc.setFont(new Font("Serif", Font.PLAIN, 20));
        acc.setBounds(90, 250, 400, 80);
        tf2 = new JTextField();
        tf2.setBounds(240, 278, 400, 30);

        pin = new JLabel("Pin: ");
        pin.setFont(new Font("Serif", Font.PLAIN, 20));
        pin.setBounds(195, 300, 400, 80);
        JPasswordField tf3 = new JPasswordField(20);
        tf3.setEchoChar('*');
        tf3.setBounds(240, 328, 400, 30);

        cpin = new JLabel("Confirm Pin: ");
        cpin.setFont(new Font("Serif", Font.PLAIN, 20));
        cpin.setBounds(123, 350, 400, 80);
        JPasswordField tf4 = new JPasswordField(20);
        tf4.setEchoChar('*');
        tf4.setBounds(240, 378, 400, 30);

        JButton Submit = new JButton("Submit");
        Submit.setBounds(320, 450, 200, 50);

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = tf1.getText();
                int ac = Integer.parseInt(tf2.getText());
                char[] passwordChars = tf3.getPassword();
                String password = new String(passwordChars);
                int p = Integer.parseInt(password);
                char[] cpasswordChars = tf4.getPassword();
                String cpassword = new String(cpasswordChars);
                int cp = Integer.parseInt(cpassword);
                if (p == cp) {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();

                        String sql = "Insert into userinfo(name,acc_num,pin)" + "values('" + n + "'," + ac + "," + p
                                + ")";
                        stmt.executeUpdate(sql);
                        String sql2 = "Insert into transactions(acc_num,money)" + "values(" + ac + "," + 0 + ")";
                        stmt.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(Submit, "Regestered Successfully");
                        new signin();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Submit, "Invalid Account Number");
                    }
                } else {
                    JOptionPane.showMessageDialog(Submit, "Pins does not match");
                }

            }
        });

        add(tf1);
        add(l1);
        add(l2);
        add(name);
        add(acc);
        add(tf2);
        add(pin);
        add(tf3);
        add(cpin);
        add(tf4);
        add(Submit);

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
        new regester();
    }

}
