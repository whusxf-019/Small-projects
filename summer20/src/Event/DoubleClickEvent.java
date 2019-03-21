package Event;
//双击事件

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import Fileabout.SelectedNode;
import MainFrame.*;

public class DoubleClickEvent extends MouseAdapter {
	private long clickTime = 0;
	private FileNodeOperation fileNodeOperation=null;
	private FileList fileSystemList = null;
	//当产生双击事件的时候   要对文件节点进行操作 还要罗列出文件列表 
	public DoubleClickEvent(FileNodeOperation fileNodeOperation ,FileList fileSystemList) {
		this.fileNodeOperation=fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}

	//响应函数
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (isDoubleClick()) {
				RightClickEvent.setViewFlg("Common Model View.");  //设为普通模式
				I_Node node = (I_Node) fileSystemList.getSelectedValue();  //选中的节点      getSelectedValue返回所选的第一个值，如果选择为空，则返回 null。
				File file = new File(node.getPath());
				if(file.isDirectory()){
				if(SelectedNode.recordSize<SelectedNode.getRecordNodesSize()-1)
				   SelectedNode.resetRecordNodes();
			    	MainFrame.returnButton.setEnabled(true);
			    	//MainFrame.forwardButton.setEnabled(false);				
				}
				SelectedNode.setSelectedNode(node);//设为临时节点	
		    	//如果是文件就直接打开
				if (file.isFile()) {
					SelectedNode.resetRecordNodes();
					Runtime run = Runtime.getRuntime();//每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
					//可以通过 getRuntime 方法获取当前运行时。 
					try {//在单独的进程中执行指定的字符串命令。 
						//这是一个很有用的方法。对于 exec(command) 形式的调用而言，其行为与调用 exec(command, null, null) 完全相同
						Process process = run.exec("cmd /c call " + "\""
								+ file.getPath() + "\"");//调用系统命令执行文件
					} catch (IOException e1) {
						e1.printStackTrace();
						System.exit(0);
					}
				}
				//其他就显示该文件夹中的文件
				else {			//判断是否是库
					if(node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
						MainFrame.tfdAddress.setText("库");
						MainFrame.newFile.setEnabled(false);
						MainFrame.nMenu.setEnabled(false);
						//MainFrame.pItem.setEnabled(false);
						MainFrame.zItem.setEnabled(false);	
					}
					if (((I_Node)(node.getParent())).getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
						MainFrame.tfdAddress.setText("库\\"+node.getFile().getName());
					}//判断是否是计算机
					else if(node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")){
						MainFrame.tfdAddress.setText("计算机");
						MainFrame.newFile.setEnabled(false);
						MainFrame.nMenu.setEnabled(false);
						//MainFrame.pItem.setEnabled(false);
						MainFrame.zItem.setEnabled(false);
					}//判断是否是网络
					else if(node.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")){
						MainFrame.tfdAddress.setText("网络");
						MainFrame.newFile.setEnabled(false);
						MainFrame.nMenu.setEnabled(false);
						//MainFrame.pItem.setEnabled(false);
						MainFrame.zItem.setEnabled(false);
					}
					else {
						if (fileNodeOperation.isClipboardEmpty()) {
							MainFrame.zItem.setEnabled(false);
						}
						else {
							MainFrame.zItem.setEnabled(true);
						}
						MainFrame.tfdAddress.setText(node.getPath());
						
					}
					fileSystemList.setList(node);  //显示该节点下的文件节点
				    
				}
			}else {
				if (fileNodeOperation.isClipboardEmpty()) {
					MainFrame.zItem.setEnabled(false);
				}
				else {
					MainFrame.zItem.setEnabled(true);
				}
				MainFrame.newFile.setEnabled(true);
				MainFrame.nMenu.setEnabled(true);
				MainFrame.openFile.setEnabled(true);	
				MainFrame.oItem.setEnabled(true);
				MainFrame.dItem.setEnabled(true);
				MainFrame.mItem.setEnabled(true);
				//MainFrame.pItem.setEnabled(true);
				MainFrame.cItem.setEnabled(true);
				MainFrame.xItem.setEnabled(true);
				I_Node node1 = (I_Node) fileSystemList.getSelectedValue();  //选中的节点
				I_Node parent=(I_Node)node1.getParent();
				if (node1.getFile().isDirectory()) {
					int num=node1.getChildCount("files");
					long longTime = node1.getFile().lastModified();
					Date date = new Date(longTime);
					SimpleDateFormat simpleFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String updateTime = simpleFormat.format(date);
					MainFrame.pLabel.setText("文件类型:文件夹  "+num+"个对象 "+"修改日期:"+updateTime);
				}
				else if (node1.getFile().isFile()) {
					long longTime = node1.getFile().lastModified();
					Date date = new Date(longTime);
					SimpleDateFormat simpleFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String updateTime = simpleFormat.format(date);
					int pos = node1.getFile().getName().lastIndexOf(
							'.');
					String kind = node1.getFile().getName().substring(
							pos + 1);
					MainFrame.pLabel.setText(node1.getFile().getName()+" "+kind +"文件   修改日期:"+updateTime);
				}
				else {
					int num=node1.getChildCount("files");
					MainFrame.pLabel.setText(num+"个对象");
				}
				if(node1.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")||
						node1.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")||
						node1.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")||
						node1.getFile().getName().equals("C:\\Users\\jone")||
						parent.getFile().getName().equals("C:\\Users\\jone")||
						parent.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")||
						parent.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){		
					MainFrame.newFile.setEnabled(false);
					MainFrame.nMenu.setEnabled(false);
					MainFrame.oItem.setEnabled(false);
					MainFrame.dItem.setEnabled(false);
					MainFrame.mItem.setEnabled(false);
					//MainFrame.pItem.setEnabled(false);
					MainFrame.cItem.setEnabled(false);
					MainFrame.xItem.setEnabled(false);
					MainFrame.zItem.setEnabled(false);
				}
				
			}
		}
		
	}
//判断是否为双击事件
	private boolean isDoubleClick() {
		long nowTime = new Date().getTime();

		if (nowTime - clickTime < 300) {
			clickTime = 0;
			return true;
		} else {
			clickTime = nowTime;
			return false;
		}
	}
}
