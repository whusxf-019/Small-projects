//FTP工具——与远程网络服务器交互文件
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

public class ftp {
    FtpClient ftpClient;

    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp
        FtpClient ftp = null;
        try {
            //创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接
            ftp = FtpClient.create();
            ftp.connect(addr);
            //登陆
            ftp.login(username, password.toCharArray());
            ftp.setBinaryType();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    public static List<String> download(String ftpFile, FtpClient ftp) {
        List<String> list = new ArrayList<String>();
        String str = "";
        InputStream is = null;
        BufferedReader br = null;
        try {
            // 获取ftp上的文件
            is = ftp.getFileStream(ftpFile);
            //转为字节流
            br = new BufferedReader(new InputStreamReader(is));
            while((str=br.readLine())!=null){
                list.add(str);
            }
            br.close();
        }catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FtpClient ftp = connectFTP("192.168.8.55",21,"test","test");
        List<String> list = download("1.txt",ftp);
        for(int i=0;i<list.size();i++){
            System.out.println("list "+ i + " :"+list.get(i));
        }
        try {
            ftp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
