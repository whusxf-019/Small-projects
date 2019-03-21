package Event;
//右击事件
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.FileTree;
import Fileabout.I_Node;
import Fileabout.SelectedNode;
import MainFrame.MainFrame;

public class RightClickEvent extends MouseAdapter {
	private FileList fileSystemList = null;
	private JPopupMenu listPopMenu = null;//菜单栏
	private FileNodeOperation fileNodeOperation = null;
	private static String viewFlg = "";  //
	private static String nullFlg = "";
	private static String copyType=null; //复制类型，复制或剪切
	public static JMenuItem cutFile=null;
	public static JMenuItem copyFile = null;
	public static JMenuItem deleteFile = null;
	public static JMenuItem pasteFile = null;
	public static JMenuItem zipFile = null;
	public static JMenuItem JzipFile = null;
	public static JMenuItem passwordFile = null;
	public static JMenuItem JpasswordFile = null;
	public static JMenu createObj = null;
	public static JMenuItem openFile=null;
	public static JMenuItem rename=null;
	//public static JMenuItem properties=null;
	public static void setViewFlg(String value) {
		viewFlg = value;
	}
	
	public static String getViewFlg() {
		return viewFlg;
	}
	
	public static void setNullFlg(String value) {
		nullFlg = value;
	}
	public static String getNullFlg() {
		return nullFlg;
	}
	//文件列表上的右击事件
	public RightClickEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileSystemList = fileSystemList;
		listPopMenu = new JPopupMenu();
		this.fileNodeOperation = fileNodeOperation;
		//打开菜单键
		 openFile=new JMenuItem("打开");
		 openFile.setEnabled(false);
		if (fileSystemList.getSelectedValues().length>1) {
			openFile.setEnabled(false);
		}else{
		openFile.addActionListener(new OpenFileEvent(fileSystemList));
		//openFile.setMnemonic(KeyEvent.VK_O);//添加快捷键助记符
		//openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				//KeyEvent.CTRL_MASK));  //添加快捷键Ctrl+c
		}
		//剪切菜单键
	    cutFile=new JMenuItem("剪切");
	    cutFile.setEnabled(false);
		cutFile.addActionListener(new CopyEvent(fileNodeOperation,
				fileSystemList,"cut"));
		//cutFile.setMnemonic(KeyEvent.VK_X);//添加快捷键助记符
		//cutFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				//KeyEvent.CTRL_MASK));  //添加快捷键Ctrl+c
		
		zipFile = new JMenuItem("压缩");
		zipFile.setEnabled(false);
		//添加事件响应
		zipFile.addActionListener(new ZipFilesEvent(fileNodeOperation, fileSystemList));
		
		JzipFile = new JMenuItem("解压");
		JzipFile.setEnabled(false);
		//添加事件响应
		JzipFile.addActionListener(new JZipFilesEvent(fileNodeOperation, fileSystemList));
		
		
		passwordFile = new JMenuItem("加密");
		passwordFile.setEnabled(false);
		//添加时间响应
		passwordFile.addActionListener(new PasswordOperationEncryptEvent(fileNodeOperation, fileSystemList));
		
		JpasswordFile = new JMenuItem("解密");
		JpasswordFile.setEnabled(false);
		//添加时间响应
		JpasswordFile.addActionListener(new PasswordOperationDecryptEvent(fileNodeOperation, fileSystemList));
		
		//复制菜单键
	    copyFile = new JMenuItem("复制");
	    copyFile.setEnabled(false);
		copyFile.addActionListener(new CopyEvent(fileNodeOperation,
				fileSystemList));
		//copyFile.setMnemonic(KeyEvent.VK_C);//添加快捷键助记符
		//copyFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				//KeyEvent.CTRL_MASK));  //添加快捷键Ctrl+c
		//粘贴菜单键
		pasteFile = new JMenuItem("粘贴");
		if (RightClickEvent.getViewFlg()
				.equals("Search Model View.")) {
			pasteFile.setEnabled(false);
		} else {
			pasteFile.addActionListener(new PasteEvent(fileNodeOperation,
					fileSystemList));
			//pasteFile.setMnemonic(KeyEvent.VK_V);
			//pasteFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					//KeyEvent.CTRL_MASK));   //添加快捷键Ctrl+v
		}
		//刷新菜单键
		JMenuItem refresh = new JMenuItem("刷新");
		refresh.addActionListener(new RefreshEvent(fileNodeOperation,
				fileSystemList));
		//refresh.setMnemonic(KeyEvent.VK_E);
		//refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				//KeyEvent.CTRL_MASK));   //添加快捷键Ctrl+S
		//重命名菜单键
	    rename = new JMenuItem("重命名");
	    rename.setEnabled(false);
		rename.addActionListener(new RenameEvent(fileNodeOperation,
				fileSystemList));
		//rename.setMnemonic(KeyEvent.VK_R);
		//rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				//KeyEvent.CTRL_MASK));  //添加快捷键Ctrl+R
		//删除菜单键
	    deleteFile = new JMenuItem("删除");
	    deleteFile.setEnabled(false);
		deleteFile.addActionListener(new DeleteEvent(fileNodeOperation,
				fileSystemList));
		//deleteFile.setMnemonic(KeyEvent.VK_D);
		//deleteFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				//KeyEvent.CTRL_MASK, true)); //添加快捷键Ctrl+D
		//新建菜单键
		createObj = new JMenu("新建");
		if (RightClickEvent.getViewFlg()
				.equals("Search Model View.")) {
			createObj.setEnabled(false);
		}
		else {
			//新建文件夹
			JMenuItem newFolder = new JMenuItem("文件夹");
			newFolder.addActionListener(new NewFolderEvent(
					fileNodeOperation, fileSystemList));
			//新建文本文档
			JMenuItem newTextDocument = new JMenuItem(
					 "新建 文本文档");
			newTextDocument.addActionListener(new NewFileEvent(
					fileNodeOperation, fileSystemList,
					"文本文档"));
			//新建Word文档
			JMenuItem newWordDocument = new JMenuItem(
					"新建 Word文档");
			newWordDocument.addActionListener(new NewFileEvent(
					fileNodeOperation, fileSystemList,
					"Word文档"));
			//新建Excel文档
			JMenuItem newExcelDocument = new JMenuItem(
					"新建 Excel文档");
			newExcelDocument.addActionListener(new NewFileEvent(
					fileNodeOperation, fileSystemList,
					"Excel文档"));
			createObj.add(newFolder);
			createObj.addSeparator();
			createObj.add(newTextDocument);
			createObj.add(newWordDocument);
			createObj.add(newExcelDocument);
		}
     //属性菜单键
		//properties = new JMenuItem("属性");
		//properties.addActionListener(new PropertyEvent(fileNodeOperation,
			//	fileSystemList));
		listPopMenu.add(openFile);
		listPopMenu.add(cutFile);
		listPopMenu.add(copyFile);
		listPopMenu.add(pasteFile);
		listPopMenu.add(zipFile);
		listPopMenu.add(JzipFile);
		listPopMenu.add(passwordFile);
		listPopMenu.add(JpasswordFile);
		listPopMenu.addSeparator();
		listPopMenu.add(refresh);
		listPopMenu.add(rename);
		listPopMenu.add(deleteFile);
		listPopMenu.addSeparator();
		listPopMenu.add(createObj);
		listPopMenu.addSeparator();
		//listPopMenu.add(properties);
	}

	public void mouseClicked(MouseEvent me) {
		if (SwingUtilities.isRightMouseButton(me)) {
			
			//搜索模式
			if (RightClickEvent.getViewFlg().equals(
					"Search Model View.")) {
				pasteFile.setEnabled(false);
				createObj.setEnabled(false);
			}
			//普通模式
			else if (RightClickEvent.getViewFlg().equals(
					"Common Model View.")) {
				//选择多个文件
				if (fileSystemList.getSelectedValues().length>1) {
					openFile.setEnabled(false);
					rename.setEnabled(false);
					zipFile.setEnabled(true);
					JzipFile.setEnabled(true);
					passwordFile.setEnabled(true);
					JpasswordFile.setEnabled(true);
				}else{
					openFile.setEnabled(true);
					rename.setEnabled(true);
					zipFile.setEnabled(true);
					JzipFile.setEnabled(true);
					passwordFile.setEnabled(true);
					JpasswordFile.setEnabled(true);
					
				}
				//没有复制文件就设粘贴菜单为无效
				if (fileNodeOperation.isClipboardEmpty()) {
					pasteFile.setEnabled(false);
				}
				else {
					pasteFile.setEnabled(true);
				}

				if (createObj != null) {
					createObj.setEnabled(true);
				}
				if (fileSystemList.getSelectedValue()!=null) {
				I_Node node = (I_Node) fileSystemList.getSelectedValue();  //选中的节点
				I_Node parent=(I_Node)node.getParent();
				if(!node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")&&
						!node.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")&&
						!node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")&&
						!node.getFile().getName().equals("C:\\Users\\jone")&&
						!parent.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")&&
						!parent.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
					RightClickEvent.copyFile.setEnabled(true);
					RightClickEvent.cutFile.setEnabled(true);
					RightClickEvent.rename.setEnabled(true);
					RightClickEvent.deleteFile.setEnabled(true);
					RightClickEvent.createObj.setEnabled(true);		
					RightClickEvent.zipFile.setEnabled(true);
					RightClickEvent.JzipFile.setEnabled(true);
					RightClickEvent.passwordFile.setEnabled(true);
					RightClickEvent.JpasswordFile.setEnabled(true);
				}
				else if (parent.getFile().getName().equals("C:\\Users\\jone")) {
					
					RightClickEvent.createObj.setEnabled(false);
				}
				else {
					MainFrame.newFile.setEnabled(false);
					MainFrame.nMenu.setEnabled(false);
					RightClickEvent.copyFile.setEnabled(false);
					RightClickEvent.cutFile.setEnabled(false);
					RightClickEvent.rename.setEnabled(false);
					RightClickEvent.deleteFile.setEnabled(false);
					RightClickEvent.createObj.setEnabled(false);
					//RightClickEvent.properties.setEnabled(false);
					RightClickEvent.zipFile.setEnabled(false);
					RightClickEvent.JzipFile.setEnabled(false);
					RightClickEvent.passwordFile.setEnabled(false);
					RightClickEvent.JpasswordFile.setEnabled(false);
				}
				}else {
					I_Node node = SelectedNode.getSelectedNode();
					if(node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")||
							node.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")||
							node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")||
							node.getFile().getName().equals("C:\\Users\\jone")){
						//RightClickEvent.properties.setEnabled(false);
						RightClickEvent.createObj.setEnabled(false);
					}
					RightClickEvent.openFile.setEnabled(false);
					RightClickEvent.rename.setEnabled(false);
					
				}
			
			}
			if (listPopMenu != null) {
				listPopMenu.show(fileSystemList, me.getX(), me.getY());
			} 
		}
 }
}
