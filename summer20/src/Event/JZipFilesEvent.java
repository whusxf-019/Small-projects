package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import MainFrame.MainFrame;

public class JZipFilesEvent implements ActionListener{
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	public JZipFilesEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	public void execute(){
		//Object[] nodeList = fileSystemList.getSelectedValues();
		I_Node node = (I_Node) fileSystemList.getSelectedValue();
		int a = node.getPath().length();
		int b = node.toString().length();
		//String D = node.getPath().substring(0, a-b);
		fileNodeOperation.jzipfiles(node.getPath(),node.getPath().substring(0, a-b));
		//System.out.println(node.getParent().toString());
		System.out.println(node.getPath());
		System.out.println(node.getPath().substring(0, a-b));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		execute();
	}

}
