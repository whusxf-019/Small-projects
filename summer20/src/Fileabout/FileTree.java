package Fileabout;


import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import Event.RightClickEvent;
import MainFrame.MainFrame;

//文件目录树结构组件 我们模拟操作系统的文件组织结构  有节点就有树形结构 需要用树形把这些表示文件的散乱的节点结构组织起来
public class FileTree extends JTree {
	private FileList fileSystemList = null;  //文件列表组件
	private FileTreeModel fileSystemTreeModel = null;  //
	
	public FileTree(FileList fileSystemList) {
		this.fileSystemList = fileSystemList;
		FileNode fileNode = new FileNode();   //构建主目录节点
		fileSystemTreeModel = new FileTreeModel(fileNode);   //构建树模型
		this.setModel(fileSystemTreeModel);
		this.setCellRenderer(new TreeRenderer()); //添加渲染器
		fileSystemList.setList(fileNode);   //显示主目录节点
		//添加选择事件
		this.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent tse) {
			}
		});
		this.setSelectionRow(0);
	}
	//获取树模型
	public FileTreeModel getFileSystemTreeModel() {
		return this.fileSystemTreeModel;
	}
	//树响应事件
	public void fireValueChanged(TreeSelectionEvent tse) {
		RightClickEvent.setViewFlg("Common Model View.");  //设为普通模式
		FileNodeOperation.getSearchNodeList().removeAllElements();
		TreePath path = tse.getNewLeadSelectionPath();
		I_Node node = (I_Node) path.getLastPathComponent();
		System.out.println("file name: "+node.getFile().getName());
		//如果选中的节点不为计算机、网络或库三个节点则新建操作有效
		if (!node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")&&
				!node.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")&&
						!node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")){
			MainFrame.newFile.setEnabled(true);
			MainFrame.nMenu.setEnabled(true);
			MainFrame.oItem.setEnabled(true);
		}
		else {
			MainFrame.newFile.setEnabled(false);
			MainFrame.nMenu.setEnabled(false);
			MainFrame.oItem.setEnabled(false);
		}
		String nodePath = node.getPath();
		//网络节点
		if (nodePath.equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")) {
			MainFrame.tfdAddress.setText("网络");
		}
		//计算机节点
		else if (nodePath.equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")) {
			MainFrame.tfdAddress.setText("计算机");
		}
		//库节点
		else if(nodePath.equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")) {
			MainFrame.tfdAddress.setText("库");
		}
		//其他节点
		else {
			MainFrame.tfdAddress.setText(nodePath);
		}
		//重新设置记录节点向量
		if(SelectedNode.recordSize<SelectedNode.getRecordNodesSize()-1)
		     SelectedNode.resetRecordNodes();
		
		SelectedNode.setSelectedNode(node);//设为临时节点
		fileSystemList.setList(node);//显示节点
		MainFrame.returnButton.setEnabled(true);  //返回按钮有效
		
	}
    //通知已注册对获得此事件类型通知感兴趣的所有侦听器
	public void fireTreeCollapsed(TreePath path) {
		super.fireTreeCollapsed(path);    
		TreePath curpath = getSelectionPath();    //返回选中的第一个节点的路径
		if (path.isDescendant(curpath)) {
			setSelectionPath(path);
		}
	}
}
