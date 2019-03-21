package Event;

//新建文件事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import Fileabout.SelectedNode;

public class NewFileEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	private String createType = null;
	public NewFileEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList, String createType) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
		this.createType = createType;
	}
  
	private void execute() {  //执行函数
		I_Node selectedNode = (I_Node) fileSystemList.getSelectedValue();//获取第一个被选中节点
		if (selectedNode != null) {
			I_Node parent = (I_Node) selectedNode.getParent();
			if (parent != null) {//判断是否存在父节点
				fileNodeOperation.setFileNode(parent);
				fileNodeOperation.createNewFile(createType);
				fileSystemList.setList(parent);
				fileNodeOperation.resetFileNode();
			}
		}
		else {
			I_Node node = SelectedNode.getSelectedNode();
			if (node != null) {
				fileNodeOperation.setFileNode(node);
				fileNodeOperation.createNewFile(createType);
				fileSystemList.setList(node);
				fileNodeOperation.resetFileNode();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		execute();
	}
}
