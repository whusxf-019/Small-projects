//•	图片浏览器——该程序可以查看电脑上各种格式的图片，譬如PNG、GIF、JPG、BMP、TIFF等等。

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
public class ImageFrame{
    public static void main(String args[]){
        Image1Frame ia1=new Image1Frame();
        ia1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ia1.setVisible(true);
    }
}
class Image1Frame extends JFrame{
    public static final int DEFAULT_WIDTH=400;
    public static final int DEFAULT_HEIGHT=300;
    private JFileChooser chooser;
    private JLabel label;
    public Image1Frame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu=new JMenu("File");
        menuBar.add(menu);
        JMenuItem openItem=new JMenuItem("Open");
        openItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                chooser.setCurrentDirectory(new File("."));
//show file chooser dialog
                int result=chooser.showOpenDialog(Image1Frame.this);
            }
        });
        menu.add(openItem);
        JMenuItem exitItem=new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        menu.add(exitItem);
//User a label to diaplay the images
        label=new JLabel();
        add(label);
//set up file chooser
        chooser=new JFileChooser();
//accept all image files ending with .jpg .jpeg .gif
        FileNameExtensionFilter filter=new FileNameExtensionFilter("images files","jpeg","gif","jpg");
        chooser.setFileFilter(filter);

        chooser.setAccessory(new ImagePreviewer(chooser));
// 设置 FileChooserUI 中的 ApproveButton 内使用的文本
        chooser.setApproveButtonText("Open");
//窗口标题
        chooser.setDialogTitle("DialogTitle");
        chooser.setFileView(new FileIconView(filter,new ImageIcon("palette.gif")));
    }
}
class ImagePreviewer extends JLabel{
    public ImagePreviewer(JFileChooser chooser){
        setPreferredSize(new Dimension(200,200));
        setBorder(BorderFactory.createEtchedBorder());

        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
//the user has selected a new file
                    File f=(File)evt.getNewValue();
                    if(f==null){
                        setIcon(null);
                        return;
                    }
//read the image into an icon
                    ImageIcon icon=new ImageIcon(f.getPath());
//if the icon is too large to fit,scale it
                    if(icon.getIconWidth()>getWidth()) icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
                    setIcon(icon);
                }
            }
        });
    }
}
class FileIconView extends FileView
{
    private FileFilter filter;
    private Icon icon;


    public FileIconView(FileFilter aFilter, Icon anIcon)
    {
        filter = aFilter;
        icon = anIcon;
    }

    public Icon getIcon(File f)
    {
        if (!f.isDirectory() && filter.accept(f)) return icon;
        else return null;
    }
}
