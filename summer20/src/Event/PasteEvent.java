package Event;

//粘贴文件按钮事件
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;
import Fileabout.SelectedNode;

public class PasteEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	//构造函数
	public PasteEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
  
	public void setFileSystemList(FileList fileSystemList) {
		this.fileSystemList = fileSystemList;
	}

	public void execute() {
		
		I_Node node = SelectedNode.getSelectedNode();
			int flag;
			//粘贴多个文件
			if (fileNodeOperation.getFileNodeList().size() > 0) {
				int listSize = fileNodeOperation.getFileNodeList().size();
				for (int i = 0; i < listSize; i++) {
					I_Node pastedNode = (I_Node) fileNodeOperation
							.getFileNodeList().get(i);   //获取复制的节点
					fileNodeOperation.resetIndex();   //清除复制文件标记
					fileNodeOperation.resetCopiedList();  //清空复制文件列表
					flag=fileNodeOperation.copy(pastedNode, node);   //复制文件
				}
				//将粘贴的文件添加到node的孩子列表中
				Vector<I_Node> pastedNodeList = fileNodeOperation
						.getPastedNode(node);
				for (int i = 0; i < pastedNodeList.size(); i++) {
					I_Node pastedNode = pastedNodeList.get(i);
					node.addChild(pastedNode);
				}
				fileSystemList.setList(node);//显示node节点下的文件
				//如果是剪切就删掉复制的源文件
				if(CopyEvent.copyType.equals("cut")){
					for (int i = 0; i < fileNodeOperation.getFileNodeList().size(); i++) {
						I_Node parentNode=(I_Node)fileNodeOperation.getFileNodeList().elementAt(i).getParent();//获取父节点
						fileNodeOperation.setFileNode(fileNodeOperation.getFileNodeList().elementAt(i));
						fileNodeOperation.delete(fileNodeOperation.getFileNodeList().elementAt(i));//删除文件
						parentNode.deleteChild(fileNodeOperation.getFileNodeList().elementAt(i)); //从父节点中删除该子节点
					}
					fileNodeOperation.resetFileNode();
				}
			
			}
			//粘贴单个文件
			else {
				I_Node pasteNode = fileNodeOperation.getFileNode();//获取复制的节点
				fileNodeOperation.resetIndex();   //清除复制文件标记
				fileNodeOperation.resetCopiedList();  //清空复制文件列表
				flag=fileNodeOperation.copy(pasteNode, node);   //复制文件
				if(flag!=0){
				Vector<I_Node> pastedNodeList = fileNodeOperation
						.getPastedNode(node);
				for (int i = 0; i < pastedNodeList.size(); i++) {
					I_Node pastedNode = pastedNodeList.get(i);
					node.addChild(pastedNode);
				}
				fileSystemList.setList(node);
				System.out.println("copytype: "+CopyEvent.copyType);
				//如果是剪切则删除复制的文件
				if(CopyEvent.copyType.equals("cut")){
					System.out.println("copytype: "+CopyEvent.copyType);
				I_Node parentNode = (I_Node)fileNodeOperation.getFileNode().getParent();
				fileNodeOperation.delete(fileNodeOperation.getFileNode());
				parentNode.deleteChild(fileNodeOperation.getFileNode());		
				fileNodeOperation.resetFileNode();
			}
				}
		}
	}

	public void actionPerformed(ActionEvent e) {
	   execute();
	}
}
