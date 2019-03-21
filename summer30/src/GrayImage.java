//•	导入图片并存成灰度图——该工具将图片上的彩色除尽并保存。可以增加对比度调整、色化等额外功能以增加复杂度。

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GrayImage extends JFrame {
    private JPanel contentPane;
    private BorderLayout borderLayout1 = new BorderLayout();
    // 添加的组件
    private JPanel jpanel = new JPanel();
    private JButton jb1 = new JButton();
    private JButton jb2 = new JButton();
    private GridLayout gridLayout = new GridLayout();
    DisplayPanel dp = new DisplayPanel();
    public void initial() {
        // 设置各组件的属性
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(580, 440));
        this.setTitle("图像的色彩处理演示");
        jb1.setFont(new Font("Dialog", Font.BOLD, 13));
        jb1.setText("Gray Image");
        jb1.addActionListener(new ActionListener() {// 添加事件监听器
            public void actionPerformed(ActionEvent e) {
                jb1_actionPerformed(e);
            }
        });
        jb2.setFont(new Font("Dialog", Font.BOLD, 13));
        jb2.setText("Reset");
        jb2.addActionListener(new ActionListener() {// 添加事件监听器
            public void actionPerformed(ActionEvent e) {
                jb2_actionPerformed(e);
            }
        });
        jpanel.setLayout(gridLayout);
        jpanel.add(jb1, null);
        jpanel.add(jb2, null);
        contentPane.add(jpanel, BorderLayout.SOUTH);
        // 在窗口中部添加DisplayPanel面板
        contentPane.add(dp, BorderLayout.CENTER);
        this.setVisible(true);
    }
    public void jb1_actionPerformed(ActionEvent e) {
        dp.toChange();// 灰化图像
        dp.repaint();// 绘制灰化后的图像
    }
    public void jb2_actionPerformed(ActionEvent e) {
        dp.reset();// 缓冲区图像设为源图像
        dp.repaint();// 绘制源图像
    }
    public static void main(String[] args) {
        new GrayImage().initial();
    }
}
class DisplayPanel extends JPanel {
    private BorderLayout borderLayout = new BorderLayout();
    // 定义新属性
    Image image;// 源图像
    BufferedImage bi;// 缓冲区图像
    Graphics2D g2d;// 图像上下文
    public DisplayPanel() {
        this.setLayout(borderLayout);
        loadImage();// 加载图片
        createBufferedImage();// 创建缓冲区图像
        this
                .setSize(new Dimension(image.getWidth(this), image
                        .getHeight(this)));// 设置面板大小
    }
    public void loadImage() {
        // 加载图片
        image = this.getToolkit().getImage(
                ClassLoader.getSystemResource("hua.jpg"));// 创建Image对象，这里的图片读者可以自定义
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 1);
        try {
            mt.waitForAll();
        } catch (Exception e) {
            e.printStackTrace();// 表示加载失败
        }
        if (image.getWidth(this) == -1) {
            System.out.println("不能创建图象");// 如果不存在退出程序
            System.exit(-1);
        }
    }
    public void createBufferedImage() {
        // 根据image对象创建一个缓冲区图像
        bi = new BufferedImage(image.getWidth(this), image.getHeight(this),
                BufferedImage.TYPE_INT_ARGB);
        // 向缓冲区图像中输入原image对象的图象数据
        g2d = bi.createGraphics();
        g2d.drawImage(this.image, 0, 0, this);// 绘制缓冲区图像
    }
    public void reset() {
        g2d.setColor(Color.black);
        g2d.clearRect(0, 0, image.getWidth(this), image.getHeight(this));
        g2d.drawImage(this.image, 0, 0, this);// 绘制缓冲区图像
    }
    public void update(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        paintComponent(g);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bi, 0, 0, this);
    }
    public void toChange() {
        // 创建灰化颜色转换器
        ColorConvertOp cco = new ColorConvertOp(ColorSpace
                .getInstance(ColorSpace.CS_GRAY), null);
        cco.filter(bi, bi);// 将原缓冲区图像的像素灰化后，重新存入缓冲区图像中
    }
}
