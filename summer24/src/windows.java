//•	SQL查询分析器——该工具可以让用户输入一条查询命令，让其运行于本地数据库中。尽量让它运行得更高效。

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class windows extends JFrame {
    private JMenuBar jMenuBar1;
    private JMenu jMenu1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem5;
    private JMenu jMenu3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem3;
    private JMenu jMenu2;
    private JMenuItem jMenuItem2;
    final String date = null;
    final String ipaddrtext = null;
    final String username = null;
    final String password = null;
    private JLabel jLabel1;
    final String port = null;
    public windows(final String ipaddrtext,final String port,final String username,final String password,final String date){
        if(port==null){
            new login();
            return;
        }
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        this.setLayout(null);
        this.setSize(358, 305);
        this.setVisible(true);


        this.setTitle("MSSQL\u67e5\u8be2\u5206\u6790\u5668 v1.1");
        {
            jLabel1 = new JLabel();
            getContentPane().add(jLabel1);
            jLabel1.setBounds(0, 0, 350, 240);
            jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("mm/4.jpg")));
        }
        {
            jMenuBar1 = new JMenuBar();
            setJMenuBar(jMenuBar1);
            {
                jMenu1 = new JMenu();
                jMenuBar1.add(jMenu1);
                jMenu1.setText("服务设置");
                jMenu1.setPreferredSize(new java.awt.Dimension(70, 29));
                {
                    jMenuItem1 = new JMenuItem();
                    jMenu1.add(jMenuItem1);
                    jMenuItem1.setText("新建连接");
                    jMenuItem1.setPreferredSize(new java.awt.Dimension(67,29));
                    jMenuItem1.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    new login();
                                }
                            }
                    );

                }
                {
                    jMenuItem2 = new JMenuItem();
                    jMenu1.add(jMenuItem2);
                    jMenuItem2.setText("退    出");
                    jMenuItem2.setPreferredSize(new java.awt.Dimension(67,29));
                    jMenuItem2.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    dispose();
                                }
                            }
                    );
                }
            }
            {
                jMenu2 = new JMenu();
                jMenuBar1.add(jMenu2);
                jMenu2.setText("文件管理");
                jMenu2.setPreferredSize(new java.awt.Dimension(70, 29));
                {
                    jMenuItem3 = new JMenuItem();
                    jMenu2.add(jMenuItem3);
                    jMenuItem3.setText("CMD命令");
                    jMenuItem3.setPreferredSize(new java.awt.Dimension(67, 29));
                    jMenuItem3.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    new cmdwindows(ipaddrtext,port,username,password,date);
                                }
                            }
                    );
                }
                {
                    jMenuItem4 = new JMenuItem();
                    jMenu2.add(jMenuItem4);
                    jMenuItem4.setText("SQL查询");
                    jMenuItem4.setPreferredSize(new java.awt.Dimension(67, 29));
                    jMenuItem4.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    new sqlwindows(ipaddrtext,port,username,password,date);
                                }
                            }
                    );
                }
            }

        }
    }
    public static void main(String[] args){



        final String date = null;
        final String ipaddrtext = null;
        final String username = null;
        final String password = null;
        final String port = null;
        new windows(ipaddrtext,port,username,password,date);

    }


}
