package Fileabout;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
//实现ListModel接口
//此接口定义方法组件（如 JList），这些组件用于获取列表中每个单元格的值以及列表的长度。在逻辑上，模型是一个向量，索引范围从 0 到 ListDataModel.getSize() - 1。
//对数据模型的内容和长度的任何更改必须报告给所有 ListDataListener
public class FileListModel implements ListModel {
	private I_Node node = null;
	private String FILEKIND = "files";
	public void setNode(I_Node node) {
		this.node = node;
	}
	public void addListDataListener(ListDataListener l) {//监听器
	}
	//获取节点指定子节点
	public Object getElementAt(int index) {
		if (node != null) {//getChild获取指定索引节点文件的子文件节点
			return this.node.getChild(FILEKIND, index);
		} else {
			return null;
		}
	}
    //获取节点子节点数
	public int getSize() {
		if (node != null) {//getChildCount获取子节点数目
			return this.node.getChildCount(FILEKIND);
		} else {
			return 0;
		}
	}
	public void removeListDataListener(ListDataListener l) {
	}
}
