//•	PDF生成器——从txt、html或其它文件中读取数据生成PDF文件。可以做成一个网页服务，用户上传文件，返回一个pdf版本。
import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
public class JAVAtoPDF {
    //这个可以将目录下的所有txt->pdf
    static void generatePDF(File dir) {
        String[] contents = dir.list();//将文件目录中的文件提取出来
        for (int i=0; i < contents.length; i++) {
            String fileName=dir+"\\"+contents[i];
            if(fileName.substring(fileName.indexOf("."), fileName.length()).equals(".txt"))  {
                System.out.println("文件名:"+fileName);
                makePDF(fileName);
            }
        }
    }

    public static void makePDF(String fileName) {
        try {
            //首先创建一个字体
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            String line = null;
            Document document;
            document = new Document(PageSize.A4, 50, 50, 50, 50);
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            PdfWriter.getInstance(document, new FileOutputStream(fileName.substring(0,fileName.indexOf("."))+".pdf"));
            document.open();
            while ((line = in.readLine()) != null)
                document.add(new Paragraph(12, line, FontChinese));
            document.close();
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        String sInput="";
        try {
            //输入
            InputStreamReader fp = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(fp);
            sInput = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = sInput;
        if (args.length != 0) {
            path = args[0];
        }
        File dir = new File(path);
        System.out.print("path" + path );
        //判断是否是有此文件目录
        if (!dir.isDirectory()) {
            System.out.println("没有此目录");
            return;
        }
        generatePDF(dir);
    }
}
