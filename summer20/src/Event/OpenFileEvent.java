package Event;

//打开文件按钮事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import Fileabout.FileList;
import Fileabout.I_Node;
import Fileabout.SelectedNode;
import MainFrame.*;

public class OpenFileEvent implements ActionListener {
	private FileList fileSystemList = null;
	
	public OpenFileEvent(FileList fileSystemList) {
		this.fileSystemList = fileSystemList;
	}

	public void execute(){ //执行函数
		I_Node node = (I_Node) fileSystemList.getSelectedValue();  //选中的节点
		File file = new File(node.getPath());
		if(file.isDirectory()){
		if(SelectedNode.recordSize<SelectedNode.getRecordNodesSize()-1)
			    SelectedNode.resetRecordNodes();
			MainFrame.returnButton.setEnabled(true);
			//MainFrame.forwardButton.setEnabled(false);
			}
		if (!node.getFile().isFile()||!node.getFile().isDirectory()) {
			MainFrame.openFile.setEnabled(false);
			MainFrame.oItem.setEnabled(false);
		}
		SelectedNode.setSelectedNode(node);    //设为临时节点
		//如果是文件就直接打开
		if(file.isFile()) {
			SelectedNode.resetRecordNodes();
			Runtime run = Runtime.getRuntime();
			try {
				Process process = run.exec("cmd /c call " + "\""
						+ file.getPath() + "\"");//调用系统命令执行文件
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
		}
		else {
			
			if(node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
				MainFrame.tfdAddress.setText("库");
				MainFrame.newFile.setEnabled(false);
				MainFrame.nMenu.setEnabled(false);
			}
			if (((I_Node)(node.getParent())).getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
				MainFrame.tfdAddress.setText("库\\"+node.getFile().getName());
			}
			else if(node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")){
				MainFrame.tfdAddress.setText("计算机");
				MainFrame.newFile.setEnabled(false);
				MainFrame.nMenu.setEnabled(false);
			}
			else if(node.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")){
				MainFrame.tfdAddress.setText("网络");
				MainFrame.newFile.setEnabled(false);
				MainFrame.nMenu.setEnabled(false);
				MainFrame.openFile.setEnabled(false);
				MainFrame.oItem.setEnabled(false);
			}
			else {
				MainFrame.tfdAddress.setText(node.getPath());
			}
			fileSystemList.setList(node);  //显示该节点下的文件节点
		}
		
	}
	public void  actionPerformed(ActionEvent e) {
			execute();
	}
	

}
