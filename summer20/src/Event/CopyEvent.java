package Event;
//复制和剪切事件

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import MainFrame.MainFrame;

public class CopyEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;  //文件操作
	public static String copyType=null;  //复制类型，分为复制和剪切
	private String temp=null;
	//复制文件构造函数
	public CopyEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	//剪切文件构造函数
	public CopyEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList,String str) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	    this.temp=str;//由这个变量来决定是复制文件还是剪切文件
	}

	public void execute() {
		    this.copyType=temp;
			Object[] nodeList = fileSystemList.getSelectedValues(); //获取选中文件节点
		   //复制多个文件
			if (nodeList.length > 1) {
				fileNodeOperation.removeAllFileNode();  //清空存储 把所有的文件节点删除
				fileNodeOperation.resetPastedFilePath();//清空复制文件路径向量
				for (int i = 0; i < nodeList.length; i++) {
					fileNodeOperation.setFileNodeList((I_Node) nodeList[i]);//将节点存储起来 便于复制操作
				}
			}
			//复制单个文件
			else {
				fileNodeOperation.removeAllFileNode();
				fileNodeOperation.resetPastedFilePath();
				fileNodeOperation.setFileNode((I_Node) nodeList[0]);
			}
				MainFrame.zItem.setEnabled(true);//因为存在复制 那么就需要把所复制的文件给粘贴到某个地方
	}

	//响应函数
	public void actionPerformed(ActionEvent e) {
		execute();
	}
}
