import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class withdraw extends Frame {
    private JLabel pin, l3, acc;
    private JTextField tf1, tf2;
    private JButton wit;

    String DB_URL = "jdbc:mysql://localhost:3306/bank";
    String USER = "root";
    String PASS = "root";

    withdraw() {

        JLabel l1 = new JLabel("Code Clause Bank");
        l1.setFont(new Font("Serif", Font.PLAIN, 35));
        l1.setBounds(280, 50, 400, 80);

        JLabel l2 = new JLabel("WITHDRAW");
        l2.setFont(new Font("Serif", Font.PLAIN, 35));
        l2.setBounds(300, 130, 400, 80);

        acc = new JLabel("Account Number: ");
        acc.setFont(new Font("Serif", Font.PLAIN, 20));
        acc.setBounds(110, 200, 150, 80);
        tf2 = new JTextField();
        tf2.setBounds(260, 228, 300, 30);

        pin = new JLabel("Pin: ");
        pin.setFont(new Font("Serif", Font.PLAIN, 20));
        pin.setBounds(215, 250, 40, 80);
        JPasswordField tf3 = new JPasswordField(20);
        tf3.setEchoChar('*');
        tf3.setBounds(260, 278, 300, 30);

        l3 = new JLabel("Amount: ");
        l3.setFont(new Font("Serif", Font.PLAIN, 20));
        l3.setBounds(180, 300, 80, 80);
        tf1 = new JTextField();
        tf1.setBounds(260, 328, 300, 30);

        wit = new JButton("WITHDRAW");
        wit.setBounds(300, 400, 200, 50);

        wit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordChars = tf3.getPassword();
                String password = new String(passwordChars);
                int p = Integer.parseInt(password);
                int am = Integer.parseInt(tf1.getText());
                int a = Integer.parseInt(tf2.getText());

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = conn.createStatement();

                    String sql = "select  pin from userinfo where userinfo.acc_num=" + "" + a + "";
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    int pin = rs.getInt("pin");
                    String sql1 = "select  money from transactions where transactions.acc_num=" + "" + a + "";
                    ResultSet r = stmt.executeQuery(sql1);
                    r.next();
                    int amount = r.getInt("money");
                    if (p == pin) {
                        int newm = amount - am;
                        String sql2 = "UPDATE transactions SET money=" + newm + " WHERE transactions.acc_num=" + a + "";
                        stmt.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(wit, "Amount Withdrawn Sucessfully");
                        new Interface();
                    } else {
                        JOptionPane.showMessageDialog(wit, "Invalid Pin");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(wit, "Invalid Account Number");

                }
            }
        });

        JLabel l = new JLabel("Welcome To Code Clause Bank");
        l.setFont(new Font("Serif", Font.PLAIN, 35));
        l.setBounds(180, 530, 900, 80);

        add(l1);
        add(pin);
        add(tf3);
        add(l3);
        add(tf1);
        add(wit);
        add(l);
        add(acc);
        add(tf2);
        add(l2);

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
        new withdraw();
    }
}
