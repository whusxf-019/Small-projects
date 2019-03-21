//•	水印——你是否想保护你图片的版权？在图片上加上标志或者文字，这样别人就不能轻易地从你网站上盗图了。做一个程序来给你的图片加上水印吧。

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterMark {
    /**
     * 给图片添加水印
     *
     * @param filePath
     *             需要添加水印的图片的路径
     * @param markContent
     *             水印的文字
     * @param markContentColor
     *             水印文字的颜色
     * @param qualNum
     *             图片质量
     * @return
     */
    public void createMark(String filePath, String markContent,
                           Color markContentColor, float qualNum) {

        ImageIcon imgIcon = new ImageIcon(filePath);
        Image theImg = imgIcon.getImage();
        int width = theImg.getWidth(null);
        int height = theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bimage.createGraphics();
        g.setColor(markContentColor);
        g.setBackground(Color.white);
        g.drawImage(theImg, 0, 0, null);
        g.drawString(markContent, width / 3, height / 2); // 添加水印的文字和设置水印文字出现的内容
        g.dispose();

        try {
            FileOutputStream out = new FileOutputStream(filePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
            param.setQuality(qualNum, true);
            encoder.encode(bimage, param);
            out.close();

        } catch (Exception e) {
            return ;
        }
    }

    public static void main(String[] args) {
        WaterMark wm = new WaterMark();

        wm.createMark("d://map.jpg","http://www.90che.com",Color.WHITE,70f);
    }
}