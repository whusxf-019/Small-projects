
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class sqlwindows extends JFrame {
    private JLabel jLabel1;
    private JTextField sqltext;
    private JButton runtext;
    private JComboBox jComboBox1;
    private DefaultTableModel model;
    private Object[] colname=null;
    private ResultSet rs=null;
    private ResultSetMetaData rsm=null;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    Object[][] data=null;
    String Table;
    String sql;
    public sqlwindows(final String ipaddrtext,final String port,final String username,final String password,final String date){
        if(port==null){
            new login();
            return;
        }

        this.setDefaultCloseOperation(2);
        this.setLayout(null);
        setLocationRelativeTo(null);

        {
            jLabel1 = new JLabel();
            getContentPane().add(jLabel1);
            jLabel1.setText("SQL\u8bed\u53e5");
            jLabel1.setBounds(12, 12, 51, 25);
        }
        {
            sqltext = new JTextField();
            getContentPane().add(sqltext);
            sqltext.setBounds(69, 13, 223, 22);
        }
        {
            ComboBoxModel jComboBox1Model =
                    new DefaultComboBoxModel(
                            new String[] { "查询", "修改" });
            jComboBox1 = new JComboBox();
            getContentPane().add(jComboBox1);
            jComboBox1.setModel(jComboBox1Model);
            jComboBox1.setBounds(298, 13, 60, 22);
            jComboBox1.setBackground(new java.awt.Color(192,192,192));
        }
        {
            runtext = new JButton();
            getContentPane().add(runtext);
            runtext.setText("\u6267\u884c");
            runtext.setBounds(364, 13, 60, 22);
            runtext.setBackground(new java.awt.Color(192,192,192));
            runtext.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String fangshi=(String) jComboBox1.getSelectedItem();
                            String sql=sqltext.getText().trim();
                            if(fangshi.equals("查询")){
                                rs=axecon(ipaddrtext,port,username,password,date,sql);
                                if(rs==null){
                                    JOptionPane.showMessageDialog(null, "无此对象");
                                }
                                Tableshow(rs);

                                rs=null;
                            }
                            else{
                                rs=(ResultSet) exec(ipaddrtext,port,username,password,date,sql);
                                Tableshow(rs);
                            }

                        }
                    }
            );

        }
        {
            jScrollPane1 = new JScrollPane();
            getContentPane().add(jScrollPane1);
            jScrollPane1.setBounds(0, 63, 429, 326);
            {

                jTable1 = new JTable();
                jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                jTable1.setPreferredScrollableViewportSize(new Dimension(500, 70));
                jScrollPane1.setViewportView(jTable1);

            }
        }
        this.setSize(437, 422);
        this.setVisible(true);
        this.setTitle("SQL\u67e5\u8be2");
    }
    public   Object exec(final String ipaddrtext,final String port,final String username,final String password,final String date,String sql){
        Statement stmt = null;
        Object rs=null;
        String url="jdbc:sqlserver://"+ipaddrtext+":"+port+";DatabaseName="+date+"";
        try{

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con=DriverManager.getConnection(url,username,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }catch(Exception e2){

            e2.printStackTrace();
        }

        try {
            rs = stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
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
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }catch(Exception e2){

            e2.printStackTrace();
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void Tableshow(ResultSet rs2) {

        try{
            //查询语句

            rsm = rs.getMetaData();

            //获取列标题
            colname = new String[rsm.getColumnCount()];

            for (int i = 0; i < rsm.getColumnCount(); i++)
            {  colname [i] = rsm.getColumnName(i + 1);
            }
            int row = 0;
            int colum = 0;
            int columCount = rsm.getColumnCount();
            //获取行数,没有直接的方法,这里先移动到纪录结尾,获取行号,即为行数,然后再移回来
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            //读取数据到数据项变量
            data = new Object[rowCount][columCount];
            while (rs.next())
            {         // times++;

                for (colum = 0; colum < rsm.getColumnCount(); colum++)
                {                data[row][colum] = rs.getObject(colum + 1);
                }
                row++;


            }

        } catch (SQLException ex)
        {
        }
        model = new DefaultTableModel(data, colname)
        {
            public Class getColumnClass(int c)
            {
                //这里要对空数据集进行检验
                if (dataVector.isEmpty() == false && getValueAt(0, c)!=null)
                {
                    return getValueAt(0, c).getClass();
                } else
                {
                    return Object.class;
                }
            }
        };
        jTable1.setModel(model);
    }

    public static void main(String[] args){

        final String date = null;
        final String ipaddrtext = null;
        final String username = null;
        final String password = null;
        final String port = null;
        new sqlwindows(ipaddrtext,port,username,password,date);

    }


}
