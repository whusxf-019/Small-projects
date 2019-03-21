package Event;
//前进按钮事件


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fileabout.FileList;
import Fileabout.I_Node;
import Fileabout.SelectedNode;
import MainFrame.MainFrame;

public class ForwardEvent implements ActionListener {
	private FileList fileSystemList = null;
	public ForwardEvent(FileList fileSystemList) {
		this.fileSystemList = fileSystemList;
	}

	private void execute() {//执行函数，判断当前处于库、计算机、网络还是其他
		MainFrame.returnButton.setEnabled(true);
		if(SelectedNode.recordSize<SelectedNode.getRecordNodesSize()){
		SelectedNode.recordSize++;
		fileSystemList.setList(SelectedNode.getRecordNode().
				elementAt(SelectedNode.recordSize));
		I_Node node=SelectedNode.getRecordNode().
				      elementAt(SelectedNode.recordSize);
		if (node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
			MainFrame.tfdAddress.setText("库\\"+node.getFile().getName());
		}
		else if(node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")){
			MainFrame.tfdAddress.setText("计算机");
			MainFrame.newFile.setEnabled(false);
			MainFrame.nMenu.setEnabled(false);
			//MainFrame.pItem.setEnabled(false);
			MainFrame.zItem.setEnabled(false);
		}
		else if(node.getFile().getName().equals("::{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}")){
			MainFrame.tfdAddress.setText("网络");
			MainFrame.newFile.setEnabled(false);
			MainFrame.nMenu.setEnabled(false);
			//MainFrame.pItem.setEnabled(false);
			MainFrame.zItem.setEnabled(false);
		}
		else {
			MainFrame.tfdAddress.setText(node.getPath());
			
		}
		System.out.println("recordesize:"+SelectedNode.recordSize);
		if (SelectedNode.recordSize==SelectedNode.getRecordNodesSize()-1) {
			MainFrame.forwardButton.setEnabled(false);
		 }
		}
	}

	public void actionPerformed(ActionEvent e) {
		execute();
	}
}
