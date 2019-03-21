//•	文件复制工具——该工具可以批量处理文件复制和备份操作
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Test {
    public Test(String string) {
        getfiel(new File(string));
    }

    File filename = new File("F:\\ideaProject\\summer21\\file2");
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    private byte bb[];
    private int jk = 1024 * 1024 * 5;

    /**
     * 目的：把一个文件复制到另一个文件夹避免发生覆盖，需要重命名文件
     *
     * 只需要把一个file对象传进来，并且设置好目标目录就可以了 目标对象：filename
     */
    private void getfiel(File file) {
        if (!file.isFile()) {
            return;
        }
        /**
         * String s[]=file.getName().split("\\."); 根据正则表达式分割成数组
         */
        String s[] = file.getName().split("\\.");
        /**
         * String pathstr=ilename.getPath()+File.separatorChar+file.getName();
         * 文件命名方式，目标目录加上需要复制的文件名
         */
        String pathstr = filename.getPath() + File.separatorChar
                + file.getName();

        /**
         * 如果 s.length<2 表示没有后缀 可以自己加一个 否则就可以得到后缀了，数组的最后一个就是它的后缀
         */
        if (s.length < 2) {
            pathstr = pathstr + ".jpg";
        }
        File file2 = new File(pathstr);
        try {
            /**
             * 下面的if（）判断文件是否存在，不存在的话就不怕文件名重复了
             */
            if (file2.exists()) {
                String path = file2.getPath();
                /** 下面的while（）判断文件不存在退出 */
                while (file2.exists()) {
                    String si[] = path.split("\\.");
                    path = "";
                    /**
                     * si[si.length-2]=si[si.length-2]+"_1." *
                     * 给重复增加文件名，因为文件切割时不保存“ . ”，所以还要加上
                     */
                    si[si.length - 2] = si[si.length - 2] + "_1.";
                    for (int i = 0; i < si.length; i++) {
                        path = path + si[i];
                    }
                    /** file2=new File(path); * 重新创建对象 */
                    file2 = new File(path);
                }
            }
            file2.createNewFile();
            int bytesum = 0;
            int byteread = 0;
            bb = new byte[jk];
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(file2);
            while ((byteread = fileInputStream.read(bb)) != -1) {
                bytesum += byteread;
                /** 字节数 文件大小 */
                fileOutputStream.write(bb, 0, byteread);
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test("F:\\ideaProject\\summer21\\file1\\file.txt");
    }
}
