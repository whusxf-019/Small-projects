//•	文件管理器——做一个文件管理器，要加些新特性，更佳的搜索功能、新图标、新外观。
package MainFrame;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileSystemView;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.FileTree;
//import Event.AboutAuthorEvent;
//import event.AboutExplorerEvent;
import Event.CopyEvent;
import Event.DeleteEvent;
import Event.DoubleClickEvent;
import Event.JZipFilesEvent;
//import Event.ForwardEvent;
//import Event.GoEvent;
import Event.NewFileEvent;
import Event.NewFolderEvent;
import Event.OpenFileEvent;
import Event.PasteEvent;
import Event.RefreshEvent;
import Event.RenameEvent;
//import Event.PropertyEvent;
//import Event.RefreshEvent;
//import Event.RenameEvent;
import Event.ReturnEvent;
import Event.RightClickEvent;
//import Event.SearchEvent;
//import Event.ShortcutKeyEvent;
//import Event.*;
import Event.ZipFilesEvent;



public class MainFrame {
	private JScrollPane treeScrollPane = null;
	private JScrollPane listScrollPane = null;
	private JFrame mainFrame = null;
	private JLabel lblAddress = null;
	public static JTextField tfdAddress = null;
	public static JButton returnButton=null;
	public static JButton forwardButton=null;
    public static JButton openFile=null;//选中之后用来打开的按钮
    public static JButton newFile=null;//新建文件夹按钮
    public static JButton refreshButton=null;
    public static JTextField searchField=null;
    public static JMenu nMenu;
    public static JMenuItem oItem;
    public static JMenuItem nItem;
    public static JMenuItem dItem;
    public static JMenuItem mItem;
    //public static JMenuItem pItem;
    public static JMenuItem xItem;
    public static JMenuItem cItem;
    public static JMenuItem zItem;
    public static JMenuItem zipItem;
    public static JMenuItem JzipItem;
    public static JMenuItem passwordItem;
    public static JMenuItem JpasswordItem;
    public static JLabel pLabel=null;
	private JToolBar toolBar = null;//JToolBar 提供了一个用来显示常用的 Action 或控件的组件
	private JSplitPane pnlMain = null;
	private FileSystemView fileSystemView = FileSystemView.getFileSystemView();//列举系统文件夹，获取系统图标
	public void show() {
		FileNodeOperation fileNodeOperation = new FileNodeOperation();
		//文件列表结构
		FileList fileSystemList = new FileList(fileNodeOperation);  
		Icon returnIcon = fileNodeOperation.getIcon("return.png");//返回按钮的添加
		returnButton=new JButton(returnIcon);
		returnButton.setToolTipText("返回");
		returnButton.setBounds(5, 0, 40, 30);
		returnButton.setEnabled(false);
		returnButton.addActionListener(new ReturnEvent(fileSystemList));// 添加一个返回事件的监听器
		Icon refreshIcon = fileNodeOperation.getIcon("refresh.png");//前进按钮的添加
		/*forwardButton=new JButton(refreshIcon);
		forwardButton.setToolTipText("前进");
		forwardButton.setBounds(45, 0, 40, 30);
		forwardButton.setEnabled(false);*/
		//forwardButton.addActionListener(new ForwardEvent(fileSystemList));
		//菜单栏
		JMenu fMenu=new JMenu("文件(F)"); //菜单栏中相关的文件栏菜单fMenu
		 //为这个新建文件设置热键 相当于按下 Alt-F按钮的时候便弹出打开文件选项
	    //fMenu.setMnemonic(KeyEvent.VK_F);//设置当前模型上的键盘助记符。助记符是某种键，它与外观的无鼠标修饰符（通常是 Alt）组合时（如果焦点被包含在此按钮祖先窗口中的某个地方）将激活此按钮
	    oItem=new JMenuItem("打开");
	    oItem.setEnabled(false);
	    oItem.addActionListener(new OpenFileEvent(fileSystemList));//添加打开文件的监听器
	    nMenu=new JMenu("新建");
	    JMenuItem nItem1=new JMenuItem("文件夹");//菜单中的项的实现。菜单项本质上是位于列表中的按钮。当用户选择“按钮”时，将执行与菜单项关联的操作。
	    nItem1.addActionListener(new NewFolderEvent(fileNodeOperation, fileSystemList));
	    JMenuItem nItem2=new JMenuItem("文本文档");
	    nItem2.addActionListener(new NewFileEvent(fileNodeOperation, fileSystemList, "文本文档"));
	    JMenuItem nItem3=new JMenuItem("Word文档");
	    nItem3.addActionListener(new NewFileEvent(fileNodeOperation, fileSystemList, "Word文档"));
	    JMenuItem nItem4=new JMenuItem("Excel文档");
	    nItem4.addActionListener(new NewFileEvent(fileNodeOperation, fileSystemList, "Excel文档"));
	    nMenu.add(nItem1);//新建文件的时候涉及到新建不同类型的文件 要把这些都添加到nMenu上去
	    nMenu.add(nItem2);
	    nMenu.add(nItem3);
	    nMenu.add(nItem4);
	    dItem=new JMenuItem("删除");
	    dItem.setEnabled(false);
	    dItem.addActionListener(new DeleteEvent(fileNodeOperation, fileSystemList));//添加删除文件的监听器
	    mItem=new JMenuItem("重命名");
	    mItem.setEnabled(false);
	    mItem.addActionListener(new RenameEvent(fileNodeOperation, fileSystemList));
	    //pItem=new JMenuItem("属性");
	    //pItem.setEnabled(false);
	    //pItem.addActionListener(new PropertyEvent(fileNodeOperation, fileSystemList));
	    fMenu.add(oItem);
	    fMenu.add(nMenu);
	   JMenuItem eItem=new JMenuItem("退出(X)"/*,KeyEvent.VK_X*/);//按下Alt-X快捷键的时候便退出当前运行
	   eItem.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);//点击退出按钮的时候 便退出整个程序
		}
	});
	   //eItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));//设置组合键，它能直接调用菜单项的操作侦听器而不必显示菜单的层次结构。 表示当我们输入Ctrl-X的时候便退出 相当于是一个快捷键
	   fMenu.add(dItem);
	   fMenu.add(mItem);
	   //fMenu.add(pItem);
	   fMenu.add(eItem);
	   JMenu eMenu=new JMenu("编辑(E)"); //另外一个菜单栏“编辑”
	    //fMenu.setMnemonic(KeyEvent.VK_E);//按下快捷键crtl-E的时候便弹出编辑选项
	    xItem=new JMenuItem("剪切");
	    xItem.setEnabled(false);
	    xItem.addActionListener(new CopyEvent(fileNodeOperation, fileSystemList,"cut"));
	    cItem=new JMenuItem("复制");
	    cItem.setEnabled(false);
	    cItem.addActionListener(new CopyEvent(fileNodeOperation, fileSystemList));
	    zItem=new JMenuItem("粘贴");
	    zItem.setEnabled(false);
	    zItem.addActionListener(new PasteEvent(fileNodeOperation, fileSystemList));
	    /*zipItem = new JMenuItem("压缩");
	    zipItem.setEnabled(false);
	    zipItem.addActionListener(new ZipFilesEvent(fileNodeOperation, fileSystemList));
	    //添加压缩实现模块
	    
	    JzipItem = new JMenuItem("解压");
	    JzipItem.setEnabled(false);
	    JzipItem.addActionListener(new JZipFilesEvent(fileNodeOperation, fileSystemList));
	    //添加解压缩实现模块
	    
	    passwordItem = new JMenuItem("加密");
	    passwordItem.setEnabled(false);
	    //添加加密实现模块
	    
	    JpasswordItem = new JMenuItem("解密");
	    JpasswordItem.setEnabled(false);*/
	    //添加解密实现模块
	    
	    
	    eMenu.add(xItem);
	    eMenu.add(cItem);
	    eMenu.add(zItem);
	    /*eMenu.add(JpasswordItem);
	    eMenu.add(passwordItem);
	    eMenu.add(JzipItem);
	    eMenu.add(zipItem);*/
	    
	    /*JMenu vMenu=new JMenu("查看(V)"); 
	    vMenu.setMnemonic(KeyEvent.VK_V);//按下快捷键Alt-V的时候便弹出查看选项
	    JMenuItem rItem=new JMenuItem("刷新");
	    rItem.addActionListener(new RefreshEvent(fileNodeOperation, fileSystemList));
	    vMenu.add(rItem);*/
	    
	    
	    /*JMenu hMenu=new JMenu("帮助(H)"); 
	    JMenuItem aItem1=new JMenuItem("关于");//在一个菜单中添加多个菜单项
	    //aItem1.addActionListener( new AboutExplorerEvent());
	    JMenuItem aItem2=new JMenuItem("关于作者");
	    //aItem2.addActionListener(new AboutAuthorEvent());
	    hMenu.setMnemonic(KeyEvent.VK_H);//按下快捷键Alt-H的时候便弹出帮助选项
	    hMenu.add(aItem1);
	    hMenu.add(aItem2);*/
	    
	    
	   JMenuBar menuBar=new JMenuBar();//主菜单选项把四个分菜单选项添加到一起
	   menuBar.add(fMenu);
	   menuBar.add(eMenu);
	   //menuBar.add(vMenu);
	   //menuBar.add(hMenu);
	   menuBar.setBounds(170, 30, 60, 25);
		//打开按钮
	    openFile=new JButton("打开");//打开按钮 下面的
		openFile.setToolTipText("打开选中文件");
		openFile.setBounds(5,0,60,25);
		openFile.setEnabled(false);
		
		
		//新建文件夹按钮
	    newFile=new JButton("新建文件夹");//添加新建文件夹事件
		newFile.setToolTipText("创建一个空的文件夹");
		newFile.setBounds(65, 0, 100, 25);	
		pLabel=new JLabel();
		pLabel.setToolTipText("选中文件属性");
		pLabel.setBounds(350, 0, 400, 25);	
		
		
		listScrollPane = new JScrollPane(fileSystemList);//设置滚动条
		listScrollPane.setPreferredSize(new Dimension(
				350, 300));
		fileNodeOperation = new FileNodeOperation();
		fileSystemList.addMouseListener(new RightClickEvent(
				fileNodeOperation, fileSystemList));//添加鼠标右击响应事件
		
	    refreshButton = new JButton(refreshIcon);//刷新按钮
		refreshButton.setToolTipText("刷新");
		refreshButton.setBounds(420,0,40,30);
		refreshButton.addActionListener(new RefreshEvent(fileNodeOperation, fileSystemList));
		//地址文本编辑框
		tfdAddress = new JTextField();
		tfdAddress.setBounds(90,0,330,30);
		
		
		openFile.addActionListener(new OpenFileEvent(fileSystemList));//添加打开文件响应事件
		newFile.addActionListener(new NewFolderEvent(fileNodeOperation, fileSystemList));//添加新建文件夹事件
		//文件树结构
		FileTree fileSystemTree = new FileTree(fileSystemList);
		treeScrollPane = new JScrollPane(fileSystemTree);
		treeScrollPane.setPreferredSize(new Dimension(
				150, 300));
		//添加到分隔面板
		//JSplitPane 用于分隔两个（只能两个）Component。两个 Component 图形化分隔以外观实现为基础，并且这两个 Component 可以由用户交互式调整大小
		JSplitPane explorerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				treeScrollPane, listScrollPane);
		explorerPane.setDividerLocation(180);//设置分隔条的位置。
		explorerPane.setDividerSize(8);//设置分隔条的大小。
		/*searchField=new JTextField();//搜索栏
		searchField.setBounds(480,0,260,30);
		searchField.setToolTipText("输入在左侧地址下搜索的完整文件名");
		//搜索按钮
		Icon searchIcon = fileNodeOperation
				.getIcon("Search.jpg");
		JButton btnSearch = new JButton(searchIcon);
		btnSearch.setBounds(740,0,40,30);
		btnSearch.setToolTipText("搜索");*/
		//btnSearch.addActionListener(new SearchEvent(fileSystemList));//添加搜索响应事件
		tfdAddress.setText(fileSystemView.getHomeDirectory().getPath());//fileSystemView 列举系统文件夹，获取系统图标
		tfdAddress.setPreferredSize(new Dimension(300, 25));//设置此组件的首选大小。如果 preferredSize 为 null，则要求 UI 提供首选大小

        JPanel p1=new JPanel();//添加一个JPanel 将这些按钮等都添加到一起
        p1.setPreferredSize(new Dimension(750,30));
        p1.setLayout(null);
        p1.add(returnButton);
        //p1.add(forwardButton);
        
        p1.add(tfdAddress);
        p1.add(refreshButton);
        //p1.add(searchField);
        //p1.add(btnSearch);
        JPanel p2=new JPanel();//把新建文件夹 、打开文件 和显示文件信息的标签添加到一起 
        p2.setPreferredSize(new Dimension(750,25));
        p2.setLayout(null);
        p2.add(openFile);
        p2.add(newFile);
        p2.add(pLabel);
		pnlMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p2);//将这两部分组件用分隔符分隔开
		pnlMain.setDividerLocation(30);
		pnlMain.setDividerSize(5);//然后再把合成的部分与刚才前面的浏览具体文件列表的部分合到一起
		JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				pnlMain, explorerPane);
		mainFrame = new JFrame();
		mainFrame.setTitle("文件管理器");//添加完标题 这样的话就算是把整个的资源管理器界面布局打理好了
		mainFrame.add(mainPane);
		mainFrame.setBounds(100, 50, 800, 600);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击右上角的按钮也可以退出
	}

	class JMenuDemo extends JMenuBar implements ActionListener{
		JMenuItem item1;
		public JMenuDemo(){
		   add(createJMenuone());		  
		}
		public JMenu createJMenuone(){
		   JMenu menu=new JMenu("文件(F)");
		   menu.setMnemonic(KeyEvent.VK_F);
		   JMenuItem item=new JMenuItem("新建(N)",KeyEvent.VK_N);
		   //表示键盘或等效输入设置上的键操作的 KeyStroke。KeyStroke 仅能对应于按下或释放某个特定的键
		   item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));//CTRL_MASK是Ctrl 修饰符
		   // getKeyStroke 返回 KeyStroke 的共享实例，前者表示指定字符的 KEY_TYPED 事件
		   //setAccelerator设置组合键，它能直接调用菜单项的操作侦听器而不必显示菜单的层次结构。
		   menu.add(item);
		   item1=new JMenuItem("退出(X)",KeyEvent.VK_X);
		   item1.addActionListener((ActionListener) this);
		   item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		   menu.add(item1);
		   return menu;
		}
		public void actionPerformed(ActionEvent e) {
		   //  自动生成方法存根
		   if(e.getSource()==item1){//得到最初发生 Event 的对象
		      System.exit(0);
		   }
		}
		}
	public static void main(String[] args)
	{
		MainFrame mf=new MainFrame();
		mf.show();
	}
}