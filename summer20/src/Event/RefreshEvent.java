package Event;

//刷新菜单事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import Fileabout.SelectedNode;

public class RefreshEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	public RefreshEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	public void execute() {
		I_Node selectedNode = (I_Node) fileSystemList.getSelectedValue();
		if (selectedNode != null) {
			I_Node parent = (I_Node) selectedNode.getParent();
			fileSystemList.setList(parent);
		}
		else {
			I_Node node = SelectedNode.getSelectedNode();
			fileSystemList.setList(node);
		}
	}
	public void actionPerformed(ActionEvent e) {
		execute();
	}
}
