package Event;

//返回按钮事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MainFrame.MainFrame;
import Fileabout.FileList;
import Fileabout.I_Node;
import Fileabout.SelectedNode;

public class ReturnEvent implements ActionListener {
	private FileList fileSystemList = null;
	
	public ReturnEvent(FileList fileSystemList) {
		this.fileSystemList = fileSystemList;
	}

	private void execute() {
		RightClickEvent.setViewFlg("Common Model View.");  //设为普通模式
		if(SelectedNode.recordSize>0){
		SelectedNode.recordSize--;
		fileSystemList.setList(SelectedNode.getRecordNode().
				elementAt(SelectedNode.recordSize));
		I_Node node=SelectedNode.getRecordNode().
			      elementAt(SelectedNode.recordSize);
		//判断是否是库
	if (node.getFile().getName().equals("::{031E4825-7B94-4DC3-B131-E946B44C8DD5}")){
		MainFrame.tfdAddress.setText("库\\"+node.getFile().getName());
	}
	//判断是否是计算机
	else if(node.getFile().getName().equals("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")){
		MainFrame.tfdAddress.setText("计算机");
		MainFrame.newFile.setEnabled(false);
		MainFrame.nMenu.setEnabled(false);
		//MainFrame.pItem.setEnabled(false);
		MainFrame.zItem.setEnabled(false);
	}
	//判断是否是网络
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
		//MainFrame.forwardButton.setEnabled(true);
		if (SelectedNode.recordSize==0) {
			MainFrame.returnButton.setEnabled(false);
		}
		}
	}

	public void actionPerformed(ActionEvent e) {
		execute();
	}
}
