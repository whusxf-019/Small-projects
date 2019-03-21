package Event;

//删除事件

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.FileTree;
import Fileabout.I_Node;

public class DeleteEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	
	public DeleteEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}

	public void execute() {
			I_Node node = (I_Node) fileSystemList.getSelectedValue();   //单个文件被选中
			Object[] selectedNodes = fileSystemList.getSelectedValues();  //多个文件被选中
			//删除多个文件
			if (selectedNodes.length > 1) {//选中一个文件其实相当于是选中一个文件节点
				Object[] options = { "确定",
						"取消" };
				int confirmValue = JOptionPane.showOptionDialog(null,//当需要删除文件并点击删除按钮的时候 那么便弹出一个提示性的对话框
						"您确定要删除这些文件吗?",
						"确认文件删除", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				//普通模式下删除操作
				if (RightClickEvent.getViewFlg().equals(//普通模式下的删除即为 用编辑选项里面的删除操作
						"Common Model View.")) {
					I_Node parentNode = (I_Node) ((I_Node) selectedNodes[0])
							.getParent();
					if (confirmValue == JOptionPane.YES_OPTION) {
						for (int i = 0; i < selectedNodes.length; i++) {
							fileNodeOperation
									.setFileNode((I_Node) selectedNodes[i]);
							fileNodeOperation.delete((I_Node) selectedNodes[i]);
							parentNode.deleteChild((I_Node) selectedNodes[i]); //从父节点中删除该子节点
						}
						fileNodeOperation.resetFileNode();
					}
					fileSystemList.setList(parentNode);
					fileNodeOperation.removeAllFileNode();
				}
				//搜索模式下的删除操作
				else if (RightClickEvent.getViewFlg().equals(
						"Search Model View.")) {
					FileNodeOperation.getTempNode().removeAllChildren(); //清空临时节点的所有孩子
					Vector<I_Node> searchNodeListClone = (Vector) FileNodeOperation.getSearchNodeList().clone();
					if (confirmValue == JOptionPane.YES_OPTION) {
						//在搜索结果中删掉选中的节点
						for (int index = 0; index < selectedNodes.length; index++) {
							for (I_Node searchNode : searchNodeListClone) {
								if (((I_Node) selectedNodes[index]).getPath()
										.equals(searchNode.getPath())) {
									FileNodeOperation.getSearchNodeList()
											.remove(searchNode);    //从搜索节点列表中删除
									fileNodeOperation
											.delete((I_Node) selectedNodes[index]);//删除该节点文件
								}
							}
						}
						for (I_Node tnode : FileNodeOperation
								.getSearchNodeList()) {
							FileNodeOperation.getTempNode().addChild(tnode);//将更新后的搜索节点列表加到临时节点的孩子中
						}
						System.out.println("temp node: "+FileNodeOperation.getTempNode().getPath());
						fileSystemList.setList(FileNodeOperation.getTempNode());//显示临时节点的子节点
						fileNodeOperation.removeAllFileNode();
					}
				}
			}
			//删除单个文件
			else {
				Object[] options = {"确认","取消" };
				int confirmValue = JOptionPane.showOptionDialog(null, node
						.getFile().getName()+" 您确定要删除这个文件吗?", "确认文件删除",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null, options,
						options[0]);
				if (confirmValue == JOptionPane.YES_OPTION) {//点击确定的时候
					if (RightClickEvent.getViewFlg().equals(
							"Common Model View.")) {
						I_Node parentNode = (I_Node) ((I_Node) selectedNodes[0])
								.getParent();
						fileNodeOperation
								.setFileNode((I_Node) selectedNodes[0]);
						fileNodeOperation.delete((I_Node) selectedNodes[0]);//当删除一个文件的是时候 那么便删除相关节点
						parentNode.deleteChild((I_Node) selectedNodes[0]);					
						fileSystemList.setList(parentNode);
						fileNodeOperation.resetFileNode();//需要重置文件节点
						
					}
					else if (RightClickEvent.getViewFlg().equals(
							"Search Model View.")) {
						FileNodeOperation.getTempNode().removeAllChildren();
						for (I_Node searchNode : FileNodeOperation
								.getSearchNodeList()) {
							if (searchNode.getPath().equals(
									((I_Node) selectedNodes[0]).getPath())) {
								FileNodeOperation.getSearchNodeList().remove(
										searchNode);
								fileNodeOperation
										.delete((I_Node) selectedNodes[0]);
								break;
							}
						}
						for (I_Node tnode : FileNodeOperation
								.getSearchNodeList()) {
							FileNodeOperation.getTempNode().addChild(tnode);
						}
						System.out.println("temp node: "+FileNodeOperation.getTempNode().getPath());
						fileSystemList.setList(FileNodeOperation.getTempNode());
						fileNodeOperation.resetFileNode();
					}
				}
			}
	}

	//响应函数
	public void actionPerformed(ActionEvent e) {
      fileSystemList
			.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		execute();
		fileSystemList.setCursor(Cursor
				.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
