package Fileabout;

//文件树模型各类操作函数
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {
	private I_Node node = null;
	protected EventListenerList listenerList = new EventListenerList();
	private final String FILEKIND = "folder";
	public FileTreeModel(I_Node node) {
		this.node = node;
	}
	public void addTreeModelListener(TreeModelListener l) {

	}
	public Object getChild(Object parent, int index) {
		return ((I_Node) parent).getChild(FILEKIND, index);
	}
	public int getChildCount(Object parent) {
		return ((I_Node) parent).getChildCount(FILEKIND);
	}
	public int getIndexOfChild(Object parent, Object child) {
		return 0;
	}
	public Object getRoot() {
		return this.node;
	}
	public boolean isLeaf(Object node) {
		return ((I_Node) node).isLeaf();
	}
	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	public void valueForPathChanged(TreePath path, Object newValue) {

	}

	public String toString() {
		return this.node.toString();
	}
}
