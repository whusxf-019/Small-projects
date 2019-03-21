//•	截屏程序——做一个可以截屏的工具，复杂一点可以增加一个转发邮件的功能。

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * 放置全屏截图的面板
 * @author Administrator
 *
 */
public class ImageFrame extends JFrame {

    /**
     * 鼠标的横坐标
     */
    int x;
    /**
     * 鼠标的纵坐标
     */
    int y;
    /**
     * 记录鼠标绘制出矩形的宽度
     */
    int width;
    /**
     * 记录鼠标绘制出的矩形的高度
     */
    int height;

    /**
     * 控制“按下”，“释放”，“点击”三个动作分别执行与否的标志位
     */
    boolean flag;

    /**
     * 用来加载整个屏幕截图的缓存
     */
    BufferedImage image;
    public ImageFrame(BufferedImage buf){
        image = buf;
        //界面工具包
        Toolkit tool = Toolkit.getDefaultToolkit();
        int w = tool.getScreenSize().width;
        int h = tool.getScreenSize().height;
        setSize(w,h);
        //去掉边框
        setUndecorated(true);

        setVisible(true);
        //一旦显示整个面板，就绘制图片
        repaint();

        //给整个面板注册鼠标监听器
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if(flag == false){
                    //鼠标释放时获得矩形框的宽和高
                    width = e.getX() - x;
                    height = e.getY() - y;
                    //绘制矩形框
                    repaint();
                    //拖出矩形框之后，设置标志为为true
                    flag = true;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(flag == false){
                    //鼠标按下时获得原点坐标
                    x = e.getX();
                    y = e.getY();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            /**
             * 双击保存截图
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                //判断是否双击
                if(flag == true && e.getClickCount() == 2){
                    //保存矩形框内的截图  (x,y,width,height)
                    try {
                        Robot robot = new Robot();
                        Toolkit tool = Toolkit.getDefaultToolkit();
                        //截图
                        BufferedImage image = robot.createScreenCapture(
                                new Rectangle(
                                        x,y,width,height));
                        //文件选择器选择自定义路径保存图片
                        JFileChooser chooser = new JFileChooser();
                        int cnt = chooser.showDialog(null,"保存");
                        if(cnt == 0){
                            File file = chooser.getSelectedFile();
                            ImageIO.write(image, "jpg", file);
                        }
                        System.exit(0);
                    } catch (AWTException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 重写父类的重绘方法
     */
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image, 0, 0, null);
        //画出矩形
        g.drawRect(x, y, width, height);
    }
}