package Fileabout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import sun.print.resources.serviceui;

//节点的系列操作 比如是保存节点还是删除节点还是其它操作等等  因为其实对文件的操作本质上就是对节点的操作
public class FileNodeOperation {
	private I_Node node = null;
	private Vector<Vector<I_Node>> copiedList = new Vector<Vector<I_Node>>();//存储复制的文件节点
	private Vector<I_Node> copyFile = null;
	private Vector<I_Node> multiSelectedNodeList = new Vector<I_Node>();//存储选中的节点
	private Vector<String> pastedFilePath = new Vector<String>();   //存储复制文件的路径
	private static Vector<I_Node> searchNodeList = new Vector<I_Node>();//存储搜索结果的节点
	int index = 0;        //标记是否有
	private int pos = 0;    //标记文件名在路径中的位置
	private int newNodeIndex = 0;
    private static I_Node tempNode = new FileNode();

	public static I_Node getTempNode() {
		return tempNode;
	}

	public FileNodeOperation() {
        
	}
   //获取复制文件列表
	private void setCopyList(I_Node copiedNode) {
		if (index++ == 0) {
			copyFile = new Vector<I_Node>();
			copyFile.add(copiedNode);
			copiedList.add(copyFile);
			pos = copiedNode.getPath().lastIndexOf(File.separator);//获取文件节点名开始的索引
		}
		File[] copieFiles = copiedNode.getFile().listFiles();
		if (copieFiles != null) {
			for (File file : copieFiles) {
				if (file.isDirectory()) {
					copyFile = new Vector<I_Node>();
					I_Node Tnode = new FileNode(file);
					copyFile.add(Tnode);
					copiedList.add(copyFile);
					setCopyList(Tnode);  //递归
				} else {
					copyFile = new Vector<I_Node>();
					I_Node Tnode = new FileNode(file);
					copyFile.add(Tnode);
					copiedList.add(copyFile);
				}
			}
		}
	}
	//压缩文件
	public int zipfiles(String zipfilenode1,String zipfilenode2){
		try{
			//String zippath = zipfilenode1.getPath();
			//String zippath1 = zipfilenode2.getPath();
			File file = new File(zipfilenode1);
			File zipfiles = new File(zipfilenode2);
			InputStream input = null;
	        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipfiles));
	        if(file.isDirectory()){
	            File[] files = file.listFiles();
	            for(int i = 0; i < files.length; ++i){
	                input = new FileInputStream(files[i]);
	                zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
	                int temp = 0;
	                while((temp = input.read()) != -1){
	                    zipOut.write(temp);
	                }
	                input.close();
	            }
	        }
	        zipOut.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
    //解压
	public int jzipfiles(String jzipfile1,String jzipfile2){
		try {
            File file = new File(jzipfile1);
            File outFile = null;
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            while((entry = zipInput.getNextEntry()) != null){
                System.out.println("解压缩" + entry.getName() + "文件");
                outFile = new File(jzipfile2 + File.separator + entry.getName());
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdir();
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;
                while((temp = input.read()) != -1){
                    output.write(temp);
                }
                input.close();
                output.close();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	//复制文件节点
	public int copy(I_Node copiedFileNode, I_Node pastedFileNode) {
		try {
			setCopyList(copiedFileNode);   //获取复制节点中的所有文件
			for (Vector<I_Node> copyFile : copiedList) {
				String copyPath = copyFile.get(0).getPath();
				String pastePath = pastedFileNode.getPath()
						+ copyPath.substring(pos);
				File file=new File(pastePath);
				if(file.exists()){
					JOptionPane.showMessageDialog(null,
							 file.getName()+"已存在", null,
							JOptionPane.ERROR_MESSAGE, null);
				
					return 0;
				}
				else{
				pastedFilePath.add(copyPath.substring(pos));
				if (copyFile.get(0).getFile().isFile()) {
					copyFile(copyPath, pastePath);
				} else if (copyFile.get(0).getFile().isDirectory()) {
					new File(pastePath).mkdir();
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
   //复制文件
	private void copyFile(String copiedPath, String pastedPath) {
		try {
			File file = new File(copiedPath);
			FileInputStream inputStream = new FileInputStream(file);
			File pastedFile = new File(pastedPath);
			FileOutputStream outputStream = new FileOutputStream(pastedFile);
			byte[] buffer = new byte[10240];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //清空复制文件节点列表
	public void resetCopiedList() {
		copiedList.removeAllElements();
	}
    //清空粘贴地址
	public void resetPastedFilePath() {
		pastedFilePath.removeAllElements();
	}
    //获取粘贴节点
	public Vector<I_Node> getPastedNode(I_Node pastedFileNode) {
		Vector<I_Node> pastedNodeList = new Vector<I_Node>();

		if (pastedNodeList.size() != 0) {
			pastedNodeList.removeAllElements();
		}
		for (String path : pastedFilePath) {
			String pastedNodePath = pastedFileNode.getPath() + path;
			if (path.lastIndexOf(File.separator) == 0) {
				I_Node pastedNode = new FileNode(new File(pastedNodePath));
				pastedNodeList.add(pastedNode);
			}
		}
		return pastedNodeList;
	}
    //设置文件节点
	public void setFileNode(I_Node node) {
		resetIndex();
		if (copiedList.size() != 0) {
			copiedList.removeAllElements();
		}

		this.node = node;
	}
//重设文件节点为空
	public void resetFileNode() {
		this.node = null;
	}
    //添加复制节点
	public void setFileNodeList(I_Node node) {
		multiSelectedNodeList.add(node);
	}
    //判断剪切板是否为空
	public boolean isClipboardEmpty() {
		return (this.node == null && multiSelectedNodeList.size() == 0);
	}
    //添加搜索节点
	public static void setSearchNodeList(I_Node node) {
		searchNodeList.add(node);
	}
    //获取搜索节点列表
	public static Vector<I_Node> getSearchNodeList() {
		return searchNodeList;
	}
    //清空选择节点列表
	public void removeAllFileNode() {
		if (multiSelectedNodeList.size() != 0) {
			multiSelectedNodeList.removeAllElements();
		}
	}
    //重设索引为0
	public void resetIndex() {
		index = 0;
	}
    //获取选中复制节点列表
	public Vector<I_Node> getFileNodeList() {
		return this.multiSelectedNodeList;
	}
    //获取当前节点
	public I_Node getFileNode() {
		return node;
	}
    //删除节点文件
	public void delete(I_Node node) {
		File file = node.getFile();
		deleteFile(file);
		file.delete();
	}
   //重命名节点文件并返回新的路径
	public String rename(I_Node node, String newName) {
		if (node != null) {
			File file = node.getFile();
			String path = ((I_Node) node.getParent()).getPath()
					+ File.separator + newName;
			file.renameTo(new File(path));
			return path;
		} else {
			return "";
		}
	}

	private void deleteFile(File deletedFile) {
		if (deletedFile.isDirectory() && deletedFile.listFiles() != null) {
			File[] fileList = deletedFile.listFiles();

			for (File file : fileList) {
				if (file.isDirectory()) {
					deleteFile(new File(file.getPath()));
				} else {
					file.delete();
				}

				file.delete();
			}
		} else {
			deletedFile.delete();
		}
	}
    //新建文件夹
	public void createNewFolder() {
		String name = node.getPath() + File.separator + "新建文件夹";
		System.out.println(name);
		int index = getNewFolderIndex(name);

		if (index != 0) {
			name = name + "(" + (index + 1) + ")";
		}
		File folder = new File(name);
		folder.mkdir();
		I_Node newFolder = new FileNode(folder);
		node.addChild(newFolder);
		newNodeIndex = 0;
	}
   //获取已新建文件夹最大索引
	private int getNewFolderIndex(String folder) {
		File file = new File(folder);

		if (file.exists()) {
			newNodeIndex++;
			if (file.getName().indexOf("(") > 0) {  //判断文件名中是否有括号，即是不是第一个
				int postion = file.getPath().lastIndexOf("(");
				String path = file.getPath().substring(0, postion) + "("
						+ (newNodeIndex + 1) + ")";
				getNewFolderIndex(path);//
			} else {
				String path = file.getPath() + "(" + (newNodeIndex + 1) + ")";
				getNewFolderIndex(path);//递归判断加1后的索引存在不
			}
		}
		return newNodeIndex;
	}
    //新建文件
	public void createNewFile(String type) {
		String name = null;
		if (type.equals("文本文档")) {
			name = node.getPath() + File.separator + "新建文本文档"
					+ ".txt";
		} else if (type.equals("Word文档")) {
			name = node.getPath() + File.separator + "新建 Word文档"
					+ ".doc";
		} else if (type.equals("Excel文档")) {
			name = node.getPath() + File.separator
					+ "新建 Excel文档" + ".xls";
		}

		int index = getNewFileIndex(name);
		if (index != 0) {
			if (type.equals("文本文档")) {
				name = name.split(".txt")[0] + "(" + (index + 1) + ")" + ".txt";
			} else if (type.equals("Word文档")) {
				name = name.split(".doc")[0] + "(" + (index + 1) + ")" + ".doc";
			} else if (type.equals("Excel文档")) {
				name = name.split(".xls")[0] + "(" + (index + 1) + ")" + ".xls";
			}
		}

		File file = new File(name);
		try {
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		I_Node newFile = new FileNode(file);
		node.addChild(newFile);
		newNodeIndex = 0;
	}
    //获取已新建同类型文件的最大索引
	private int getNewFileIndex(String filePath) {
		File file = new File(filePath);

		if (file.exists()) {
			newNodeIndex++;
			if (file.getName().indexOf("(") > 0) {
				int postion = file.getPath().lastIndexOf("(");
				String path = file.getPath().substring(0, postion) + "("
						+ (newNodeIndex + 1) + ")"
						+ filePath.substring(filePath.lastIndexOf("."));
				getNewFileIndex(path);
			} else {
				String path = file.getPath().substring(0,
						file.getPath().lastIndexOf("."));
				path = path
						+ "("
						+ (newNodeIndex + 1)
						+ ")"
						+ file.getPath().substring(
								file.getPath().lastIndexOf("."));
				getNewFileIndex(path);
			}
		}
		return newNodeIndex;
	}
    //获取图标
	public Icon getIcon(String iconName) {
		String currdir = null;
		try {
			currdir = new File(".").getCanonicalPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ImageIcon(this.getClass().getResource("/icon/" + iconName));//新建icon包中的图标
	}
}
