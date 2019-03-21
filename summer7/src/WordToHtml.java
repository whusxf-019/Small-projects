//•	HTML生成器——将 TEXT 文档转换成HTML文件，对制作网页HTML文档很有用
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;
public class WordToHtml {
    public static void main(String[] args) {
        try {
            wordToHtml("d:\\12.docx", "d:\\", "123.html");
            wordToHtml("d:\\2.doc", "d:\\", "12.html");
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void wordToHtml(String wordPath,String htmlPath,String newFilename) throws TransformerException, IOException, ParserConfigurationException {
        convert2Html(wordPath, htmlPath, newFilename);
    }

    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){

            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }


    public static void convert2Html(String fileName, String outPutFilePath,String newFileName)
            throws TransformerException, IOException,
            ParserConfigurationException {
        String substring = fileName.substring(fileName.lastIndexOf(".")+1);
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        if("docx".equals(substring)){
            InputStream inputStream = new FileInputStream(new File(fileName));
            XWPFDocument document = new XWPFDocument(inputStream);

            final String imageUrl = "";
            XHTMLOptions options = XHTMLOptions.create();
            options.setExtractor(new FileImageExtractor(new File(outPutFilePath + imageUrl)));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
            options.URIResolver(new IURIResolver() {
                public String resolve(String uri) {
                    return imageUrl + uri;
                }
            });

            XHTMLConverter.getInstance().convert(document, out, options);
        }else{
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .newDocument());
            wordToHtmlConverter.setPicturesManager( new PicturesManager()
            {
                public String savePicture( byte[] content,
                                           PictureType pictureType, String suggestedName,
                                           float widthInches, float heightInches )
                {
                    return suggestedName;
                }
            } );
            wordToHtmlConverter.processDocument(wordDocument);
            List pics=wordDocument.getPicturesTable().getAllPictures();
            if(pics!=null){
                for(int i=0;i<pics.size();i++){
                    Picture pic = (Picture)pics.get(i);
                    System.out.println();
                    try {
                        pic.writeImageContent(new FileOutputStream(outPutFilePath
                                + pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            Document htmlDocument = wordToHtmlConverter.getDocument();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(out);

            TransformerFactory tf = TransformerFactory.newInstance();    //这个应该是转换成xml的
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        }

        out.close();
        writeFile(new String(out.toByteArray()), outPutFilePath+newFileName);
    }
}
