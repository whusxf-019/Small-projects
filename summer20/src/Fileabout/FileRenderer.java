package Fileabout;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
//文件列表元素渲染器
class FileRenderer extends JLabel implements ListCellRenderer {//它的作用就是对文件的背景进行操作与表示
	public FileRenderer() {
		setOpaque(true);
	}
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		I_Node node = (I_Node) value;
		setIcon(node.getIcon());	
		setText(value.toString());
		setBackground(isSelected ? Color.blue : Color.WHITE);//选中时背景为蓝色，未选中为白色
		setForeground(isSelected ? Color.WHITE : Color.BLACK);//选中时文件名字为白色，未选中为黑色
		return this;
	}
}
