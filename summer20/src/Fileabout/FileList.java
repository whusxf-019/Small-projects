package Fileabout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import Event.DoubleClickEvent;
import Event.RightClickEvent;
import MainFrame.MainFrame;
//文件列表组件
public class FileList extends JList {
	private FileListModel model = null;   //文件列表模型
	private DoubleClickEvent mouseDoubleEvent = null;
	private FileNodeOperation fileNodeOperation=null; //文件节点操作
	//构造函数
	public FileList(FileNodeOperation fileNodeOperation) {
		model = new FileListModel();  
		this.setModel(model);   //添加文件列表模型
		this.fileNodeOperation=fileNodeOperation;
		this.setCellRenderer(new FileRenderer());  //添加元素渲染器
		mouseDoubleEvent = new DoubleClickEvent(this.fileNodeOperation,this);
		this.addMouseListener(mouseDoubleEvent);//添加双击事件
	}
	//在列表中显示该节点下的内容
	public void setList(I_Node node) {
		this.removeMouseListener(mouseDoubleEvent);//撤销双击事件
		model.setNode(node);
		this.setModel(model);
		this.updateUI();
		MainFrame.openFile.setEnabled(false);  //主界面打开按钮失效
		RightClickEvent.deleteFile.setEnabled(false);//右击菜单中删除菜单失效
		RightClickEvent.copyFile.setEnabled(false);//右击菜单中复制菜单失效
		RightClickEvent.cutFile.setEnabled(false);//右击菜单中剪切菜单失效
		RightClickEvent.zipFile.setEnabled(false);
		RightClickEvent.JzipFile.setEnabled(false);
		RightClickEvent.passwordFile.setEnabled(false);
		RightClickEvent.JpasswordFile.setEnabled(false);
		MainFrame.oItem.setEnabled(false);//主界面文件菜单栏打开菜单无效
		MainFrame.dItem.setEnabled(false);//主界面编辑菜单栏删除菜单无效  
		MainFrame.mItem.setEnabled(false);//主界面编辑菜单栏重命名菜单无效  
		MainFrame.cItem.setEnabled(false);//主界面编辑菜单栏复制菜单无效  
		MainFrame.xItem.setEnabled(false);//主界面编辑菜单栏打剪切菜单无效 
		/*MainFrame.zipItem.setEnabled(false);
		MainFrame.JzipItem.setEnabled(false);
		MainFrame.passwordItem.setEnabled(false);
		MainFrame.JpasswordItem.setEnabled(false);*/
		this.addMouseListener(mouseDoubleEvent);
	}
}
