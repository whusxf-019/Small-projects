package Event;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;

//压缩事件
public class ZipFilesEvent implements ActionListener{
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	public ZipFilesEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	public void execute(){
		//Object[] nodeList = fileSystemList.getSelectedValues();
		I_Node node = (I_Node) fileSystemList.getSelectedValue();
		fileNodeOperation.zipfiles(node.getPath(), node.getPath()+".zip");
		System.out.println(node.getPath());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
	execute();
	}
}
