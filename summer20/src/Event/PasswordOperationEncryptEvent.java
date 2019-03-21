package Event;
//加密事件
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import Fileabout.FileList;
import Fileabout.FileNodeOperation;
import Fileabout.I_Node;

public class PasswordOperationEncryptEvent implements ActionListener {
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	private String key = "yuanshengtao";
	public PasswordOperationEncryptEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		// TODO Auto-generated constructor stub
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	public void execute() throws Exception{
		I_Node node = (I_Node) fileSystemList.getSelectedValue();
		
		encrypt(node.getPath(),key);
	}
	public static void encrypt(String fileUrl, String key) throws Exception {  
        File file = new File(fileUrl);  
        String path = file.getPath();  
        if(!file.exists()){  
            return;  
        }  
        int index = path.lastIndexOf("\\");  
        String destFile = path.substring(0, index)+"\\"+"abc";  
        File dest = new File(destFile);  
        InputStream in = new FileInputStream(fileUrl);  
        OutputStream out = new FileOutputStream(destFile);  
        byte[] buffer = new byte[1024];  
        int r;  
        byte[] buffer2=new byte[1024];  
        while (( r= in.read(buffer)) > 0) {  
            for(int i=0;i<r;i++)  
            {  
                byte b=buffer[i];  
                buffer2[i]=b==255?0:++b;  
            }  
            out.write(buffer2, 0, r);  
            out.flush();  
        }  
        in.close();  
        out.close();  
        file.delete();  
        dest.renameTo(new File(fileUrl));  
        appendMethodA(fileUrl, key);  
        System.out.println("加密成功");  
    }
	
	public static void appendMethodA(String fileName, String content) {  
        try {  
            // 打开一个随机访问文件流，按读写方式  
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");  
            // 文件长度，字节数  
            long fileLength = randomFile.length();  
            //将写文件指针移到文件尾。  
            randomFile.seek(fileLength);  
            randomFile.writeBytes(content);  
            randomFile.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			execute();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
