package Event;

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

//解密事件
public class PasswordOperationDecryptEvent implements ActionListener {
	private String fileUrl = null;
	private String tempUrl;
	private String key = "yuanshengtao";
	private FileList fileSystemList = null;
	private FileNodeOperation fileNodeOperation = null;
	public PasswordOperationDecryptEvent(FileNodeOperation fileNodeOperation,
			FileList fileSystemList) {
		// TODO Auto-generated constructor stub
		this.fileNodeOperation = fileNodeOperation;
		this.fileSystemList = fileSystemList;
	}
	public void execute() throws Exception{
		I_Node node = (I_Node) fileSystemList.getSelectedValue();
		int a = node.getPath().length();
		int b = node.toString().length();
		tempUrl = node.getPath().substring(0, a-b)+"decry.txt";
		decrypt(node.getPath(),tempUrl,key.length());
		
	}
	public static String decrypt(String fileUrl, String tempUrl, int keyLength) throws Exception{  
        File file = new File(fileUrl);  
        if (!file.exists()) {  
            return null;  
        }  
        File dest = new File(tempUrl);  
        if (!dest.getParentFile().exists()) {  
            dest.getParentFile().mkdirs();  
        }  
    
        InputStream is = new FileInputStream(fileUrl);  
        OutputStream out = new FileOutputStream(tempUrl);  
    
        byte[] buffer = new byte[1024];  
        byte[] buffer2=new byte[1024];  
        byte bMax=(byte)255;  
        long size = file.length() - keyLength;  
        int mod = (int) (size%1024);  
        int div = (int) (size>>10);  
        int count = mod==0?div:(div+1);  
        int k = 1, r;  
        while ((k <= count && ( r = is.read(buffer)) > 0)) {  
            if(mod != 0 && k==count) {  
                r =  mod;  
            }  
    
            for(int i = 0;i < r;i++)  
            {  
                byte b=buffer[i];  
                buffer2[i]=b==0?bMax:--b;  
            }  
            out.write(buffer2, 0, r);  
            k++;  
        }  
        out.close();  
        is.close();  
        return tempUrl;  
    }
	//判断文件是否加密
	public static String readFileLastByte(String fileName, int keyLength) {  
        File file = new File(fileName);  
        if(!file.exists())return null;  
        StringBuffer str = new StringBuffer();  
        try {  
            // 打开一个随机访问文件流，按读写方式  
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "r");  
            // 文件长度，字节数  
            long fileLength = randomFile.length();  
            //将写文件指针移到文件尾。  
            for(int i = keyLength ; i>=1 ; i--){  
                randomFile.seek(fileLength-i);  
                str.append((char)randomFile.read());  
            }  
            randomFile.close();  
            return str.toString();  
        } catch (IOException e) {  
            e.printStackTrace();    
        }    
        return null;  
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
