package Event;

//重命名事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import Fileabout.FileList;
import Fileabout.FileNode;
import Fileabout.FileNodeOperation;
import Fileabout.FileTree;
import Fileabout.I_Node;

public class RenameEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	private I_Node node = null;
	private String operaType = null;
	
	public RenameEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;

		this.operaType = "FileSystemList";
	}

	public void execute() {
		if (operaType.equals("FileSystemList")) {
			node = (I_Node) fileSystemList.getSelectedValue();
			if (node != null) {
				String name = node.getFile().getName();
				String newName = JOptionPane.showInputDialog("请输入修改名称",
						name);
				if (newName != null) {
					String newPath = fileNodeOperation.rename(node, newName);//重命名后返回新的路径
					//普通模式下显示
					if (RightClickEvent.getViewFlg().equals(
							"Common Model View.")) {
						I_Node parent = (I_Node) node.getParent();
						fileSystemList.setList(parent);
					}
					//搜索模式下显示
					else if (RightClickEvent.getViewFlg().equals(
							"Search Model View.")) {
						int index = 0;
						for (I_Node tnode : FileNodeOperation
								.getSearchNodeList()) {
							//删掉重命名前的文件
							if (tnode.getPath().equals(node.getPath())) {
								FileNodeOperation.getSearchNodeList().remove(
										tnode);
								break;
							}
							index++;
						}
						FileNodeOperation.getSearchNodeList().add(index,
								new FileNode(new File(newPath)));   //将重命名后的文件添加到搜索列表
						I_Node searchResult = FileNodeOperation.getTempNode();
						searchResult.removeAllChildren();
						for (I_Node tempNode : FileNodeOperation
								.getSearchNodeList()) {
							searchResult.addChild(tempNode); 
						}
						fileSystemList.setList(searchResult);   //显示
					}
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		execute();
	}

}
