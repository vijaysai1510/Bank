import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class change extends Frame {
    private JLabel pin, acc, pin2;
    private JTextField tf2;
    private JButton submit;

    String DB_URL = "jdbc:mysql://localhost:3306/bank";
    String USER = "root";
    String PASS = "root";

    change() {
        JLabel l1 = new JLabel("Code Clause Bank");
        l1.setFont(new Font("Serif", Font.PLAIN, 35));
        l1.setBounds(280, 50, 400, 80);

        JLabel l2 = new JLabel("CHANGE PIN");
        l2.setFont(new Font("Serif", Font.PLAIN, 35));
        l2.setBounds(300, 130, 400, 80);

        acc = new JLabel("Account Number: ");
        acc.setFont(new Font("Serif", Font.PLAIN, 20));
        acc.setBounds(110, 200, 150, 80);
        tf2 = new JTextField();
        tf2.setBounds(260, 228, 300, 30);

        pin = new JLabel("Existing Pin: ");
        pin.setFont(new Font("Serif", Font.PLAIN, 20));
        pin.setBounds(148, 250, 150, 80);
        JPasswordField tf3 = new JPasswordField(20);
        tf3.setEchoChar('*');
        tf3.setBounds(260, 278, 300, 30);

        pin2 = new JLabel("New Pin: ");
        pin2.setFont(new Font("Serif", Font.PLAIN, 20));
        pin2.setBounds(174, 300, 120, 80);
        JPasswordField tf4 = new JPasswordField(20);
        tf4.setEchoChar('*');
        tf4.setBounds(260, 328, 300, 30);

        submit = new JButton("SUBMIT");
        submit.setBounds(300, 400, 200, 50);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordChars = tf3.getPassword();
                String password = new String(passwordChars);
                int p = Integer.parseInt(password);
                int a = Integer.parseInt(tf2.getText());
                char[] passwordChars2 = tf4.getPassword();
                String password2 = new String(passwordChars2);
                int p2 = Integer.parseInt(password2);

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "select  pin from userinfo where userinfo.acc_num=" + "" + a + "";
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    int pin = rs.getInt("pin");
                    if (p == pin) {
                        String sql2 = "UPDATE userinfo SET pin=" + p2 + " WHERE userinfo.acc_num=" + a + "";
                        stmt.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(submit, "Pin Changed Successfully");
                        new Interface();
                    } else {
                        JOptionPane.showMessageDialog(submit, "Invalid Existing Pin");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(submit, "Invalid Account Number");
                }
            }
        });

        JLabel l = new JLabel("Welcome To Code Clause Bank");
        l.setFont(new Font("Serif", Font.PLAIN, 35));
        l.setBounds(180, 530, 900, 80);

        add(l1);
        add(pin);
        add(tf3);
        add(tf4);
        add(submit);
        add(l);
        add(acc);
        add(tf2);
        add(l2);
        add(pin2);

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
        new change();
    }

}
