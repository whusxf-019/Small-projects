import java.awt.AWTException;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;


public class MainFrame extends JFrame{

    /**
     * 截图按钮
     */
    JButton capture;

    public MainFrame(){
        capture = new JButton("截图");
        Container con = getContentPane();
        con.add(capture);
        setSize(100,60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //保持在最前端
        setAlwaysOnTop(true);

        /**
         * 按钮注册到监听器上
         */
        capture.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //使自己消失
                setVisible(false);

                //截图
                try {
                    //机器人，控制鼠标、控制键盘、屏幕截图
                    Robot robot = new Robot();
                    //工具包
                    Toolkit tool = Toolkit.getDefaultToolkit();
                    //获得屏幕的宽和高
                    int w = tool.getScreenSize().width;
                    int h = tool.getScreenSize().height;
                    //截图 并缓存在程序的内存中
                    BufferedImage image = robot.createScreenCapture(new Rectangle(w, h));
                    //在新的面板上打开全屏截图
                    ImageFrame imageFrame = new ImageFrame(image);
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }

                //自己消失
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }
}