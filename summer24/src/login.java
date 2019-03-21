
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;


public class login extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel4;
    private JTextField datetext;
    private JPasswordField passtext;
    private JTextField porttext;
    private JLabel jLabel5;
    private JButton cancelbutton;
    private JButton linkbutton;
    private JTextField usertext;
    private JTextField ipaddr;
    private JLabel jLabel3;
    public login(){
        setLocationRelativeTo(null);

        this.setDefaultCloseOperation(2);
        this.setLayout(null);
        {
            jLabel1 = new JLabel();
            getContentPane().add(jLabel1);
            jLabel1.setText("IP\u5730\u5740");
            jLabel1.setBounds(18, 25, 48, 15);
        }
        {
            jLabel2 = new JLabel();
            getContentPane().add(jLabel2);
            jLabel2.setText("\u7528\u6237\u540d");
            jLabel2.setBounds(18, 79, 48, 15);
        }
        {
            jLabel3 = new JLabel();
            getContentPane().add(jLabel3);
            jLabel3.setText("\u5bc6  \u7801");
            jLabel3.setBounds(18, 107, 48, 15);
        }
        {
            jLabel4 = new JLabel();
            getContentPane().add(jLabel4);
            jLabel4.setText("\u6570\u636e\u5e93");
            jLabel4.setBounds(18, 134, 48, 15);
        }
        {
            ipaddr = new JTextField();
            getContentPane().add(ipaddr);
            ipaddr.setBounds(78, 21, 144, 22);
            ipaddr.setText("127.0.0.1");
        }
        {
            usertext = new JTextField();
            getContentPane().add(usertext);
            usertext.setText("sa");
            usertext.setBounds(78, 75, 144, 22);
        }
        {
            datetext = new JTextField();
            getContentPane().add(datetext);
            datetext.setText("master");
            datetext.setBounds(78, 130, 144, 22);
        }
        {
            linkbutton = new JButton();
            getContentPane().add(linkbutton);
            linkbutton.setText("\u8fde\u63a5");
            linkbutton.setBounds(78, 157, 60, 22);
            linkbutton.setBackground(new java.awt.Color(192,192,192));
            linkbutton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String ipaddrtext=ipaddr.getText().trim();
                            String port=porttext.getText().trim();
                            String username=usertext.getText().trim();
                            String password=passtext.getText().trim();
                            String date=datetext.getText().trim();
                            String url="jdbc:sqlserver://"+ipaddrtext+":"+port+";DatabaseName="+date+"";
                            try {
                                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                                Connection con=DriverManager.getConnection(url,username,password);
                                JOptionPane.showMessageDialog(null, "连接成功!");

                                new windows(ipaddrtext,port,username,password,date);
                                dispose();
                            } catch (ClassNotFoundException e1) {
                                JOptionPane.showMessageDialog(null, "加载驱动失败!");

                                e1.printStackTrace();
                            } catch (SQLException e1) {
                                JOptionPane.showMessageDialog(null, "连接失败!");
                                e1.printStackTrace();
                            }

                        }
                    }
            );

        }
        {
            cancelbutton = new JButton();
            getContentPane().add(cancelbutton);
            cancelbutton.setText("\u53d6\u6d88");
            cancelbutton.setBounds(162, 157, 60, 22);
            cancelbutton.setBackground(new java.awt.Color(192,192,192));
            cancelbutton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            dispose();
                        }
                    }
            );

        }
        {
            jLabel5 = new JLabel();
            getContentPane().add(jLabel5);
            jLabel5.setText("\u7aef  \u53e3");
            jLabel5.setBounds(18, 52, 42, 15);
        }
        {
            porttext = new JTextField();
            getContentPane().add(porttext);
            porttext.setText("1433");
            porttext.setBounds(78, 48, 144, 22);
        }
        {
            passtext = new JPasswordField();
            getContentPane().add(passtext);
            passtext.setBounds(78, 103, 144, 22);
        }
        this.setSize(289, 243);
        this.setVisible(true);
        this.setTitle("MSSQL\u67e5\u8be2\u5206\u6790\u5668 v1.1");
    }

    public static void main(String[] args){


        new login();


    }
}






