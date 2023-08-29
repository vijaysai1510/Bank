import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

public class signin extends Frame {

    String DB_URL = "jdbc:mysql://localhost:3306/bank";
    String USER = "root";
    String PASS = "root";
    public int ac;
    private JLabel name, acc, pin;
    private JTextField tf1, tf2;

    signin() {
        JLabel l1 = new JLabel("Code Clause Bank");
        l1.setFont(new Font("Serif", Font.PLAIN, 35));
        l1.setBounds(280, 50, 400, 80);

        JLabel l2 = new JLabel("Sign In");
        l2.setFont(new Font("Serif", Font.PLAIN, 35));
        l2.setBounds(370, 130, 400, 80);

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

        JButton Submit = new JButton("Submit");
        Submit.setBounds(320, 410, 200, 50);

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac = Integer.parseInt(tf2.getText());
                char[] passwordChars = tf3.getPassword();
                String password = new String(passwordChars);
                int p = Integer.parseInt(password);

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = conn.createStatement();

                    String sql = "select  pin from userinfo where userinfo.acc_num=" + "" + ac + "";
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    int pin = rs.getInt("pin");
                    if (p == pin) {
                        new Interface();
                    } else {
                        JOptionPane.showMessageDialog(Submit, "Invalid Pin");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Submit, "Invalid Account Number");
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
        new signin();
    }
}
