package Fileabout;


import java.util.Vector;
//选中显示的节点
public class SelectedNode {
	private static I_Node selectedNode = null;
	public static int recordSize;  //选中进入节点向量的标记
	private static Vector<I_Node> recordNodes=new Vector<I_Node>();//存储选中进入的节点
	//设置为选中显示的节点
	public static void setSelectedNode(I_Node temp) {
		selectedNode = temp;
		recordNodes.add(selectedNode);
		System.out.println();
		recordSize=recordNodes.size()-1;
		System.out.println("recordesize:"+recordSize);
		System.out.println("tempnode set: "+selectedNode.getPath());
	}
	//获取选中显示的节点
	public static I_Node getSelectedNode() {
		return selectedNode;
	}
	//重设选中显示的节点
	public static void resetRecordNodes(){
		System.out.println("recordesize:"+recordSize);
		System.out.println("recordNodes.size1:"+recordNodes.size());
		int len=recordNodes.size()-recordSize-1;
		int index=recordSize+1;
		for (int i = 1; i <=len ; i++) {
			recordNodes.removeElementAt(index);
			System.out.println("i:"+i);			
		}
		recordSize=recordNodes.size()-1;
		System.out.println("recordnodesize2:"+recordNodes.size());
	}
	//获取选中显示节点的数量
	public static int getRecordNodesSize(){
		return recordNodes.size();
	}
	//获取选中节点向量
	public static Vector<I_Node> getRecordNode(){
		return recordNodes;
	}
}
