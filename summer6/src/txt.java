//•	文本编辑器——记事本类型的应用，可以打开、编辑、保存文本文档。可以增加单词高亮和其它的一些特性
import java.awt.*;
import java .awt.event.*;
import java.io.*;
 class win {
     Frame a;
     MenuBar menubar;
     Menu menu1;
     File file;
     MenuItem item1,item2,item3;
     FileDialog opendia,savedia;
     TextArea t;
     win(){
         a = new Frame("Notepad");
         Init();
     }
     public void Init(){
         menubar=new MenuBar();
         menu1=new Menu("File");
         item1=new MenuItem("Open");
         item2=new MenuItem("Preservation");
         item3=new MenuItem("Quit");
         menu1.add(item1);
         menu1.add(item2);
         menu1.add(item3);
         menubar.add(menu1);
         a.setMenuBar(menubar);

         t=new TextArea();
         a.add(t);
         opendia=new FileDialog(a,"Open",FileDialog.LOAD);
         savedia=new FileDialog(a,"Preservation",FileDialog.SAVE);
         a.setBounds(150,150,640,480);
         a.setVisible(true);
         myevent();
     }
     public void myevent(){
         a.addWindowListener(new WindowAdapter()
         {
             public void windowClosing(WindowEvent e)
             {
                 System.exit(0);
             }
         });
         item3.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 System.exit(0);
             }
         });
         item1.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 opendia.setVisible(true);
                 String dirPath=opendia.getDirectory();
                 String fileName=opendia.getFile();
                 if(dirPath==null||fileName==null)
                     return ;
                 t.setText("");
                 file=new File(dirPath,fileName);
                 try
                 {BufferedReader bfrd=new BufferedReader(new FileReader(file));
                     String line=null;
                     while((line=bfrd.readLine())!=null)
                     {t.append(line+"\r\n");
                     }bfrd.close();
                 }
                 catch(IOException ex)
                 {throw new RuntimeException("读取失败");
                 }
             }
         });
         item2.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 if(file==null)
                 {
                     savedia.setVisible(true);
                     String dirPath=savedia.getDirectory();
                     String fileName=savedia.getFile();
                     if(dirPath==null||fileName==null)
                         return ;
                     file=new File(dirPath,fileName);
                 }
                 try
                 {
                     BufferedWriter bfwt=new BufferedWriter(new FileWriter(file));
                     String text= t.getText();
                     bfwt.write(text);
                     bfwt.close();
                     t.setText("");
                 }
                 catch(IOException ex)
                 {
                     throw new RuntimeException();
                 }
             }
         });
     }
 }
 class txt{
     public static void main(String[] args){
         win a = new win();
     }
 }

