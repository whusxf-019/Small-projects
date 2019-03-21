package Event;

//新建文件夹事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import Fileabout.SelectedNode;
import MainFrame.MainFrame;

public class NewFolderEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	
	public NewFolderEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	
	private void execute() { //执行函数
		I_Node selectedNode = (I_Node) fileSystemList.getSelectedValue();
		if (RightClickEvent.getViewFlg().equals("Search Model View.")){

			MainFrame.newFile.setEnabled(false);
			MainFrame.nMenu.setEnabled(false);
		}
		if (RightClickEvent.getViewFlg().equals(
				"Common Model View.")) {
		if (selectedNode != null) {
			I_Node parent = (I_Node) selectedNode.getParent();
			fileNodeOperation.setFileNode((parent));
			fileNodeOperation.createNewFolder();
			fileSystemList.setList(parent);
			fileNodeOperation.resetFileNode();

		}
		else {
			I_Node node = SelectedNode.getSelectedNode();
			if (node != null) {
				fileNodeOperation.setFileNode(node);
				fileNodeOperation.createNewFolder();
				fileSystemList.setList(node);
				fileNodeOperation.resetFileNode();
			}
		}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		execute();
	}
}
