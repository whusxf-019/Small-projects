//•	屏保——电脑空闲时会运行的屏保程序。简单版的可以使用一些标准图片，复杂版的可以做出能在屏幕上转来转去的3D物体。

import javax.swing.JFrame;

public class Test {
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setTitle("这里设置标题");//如果需要隐藏标题栏，可不写
        f.setUndecorated(true);//隐藏窗体的标题栏，如果不需要可以不写
        f.setSize(300,300);//这里设置窗口大小
        f.getGraphicsConfiguration().getDevice().setFullScreenWindow(f);//设置窗口全屏显示
        MyPanel mp=new MyPanel();
        f.add(mp);
        f.addKeyListener(mp);
        mp.addKeyListener(mp);
        Thread t=new Thread(mp);
        mp.addMouseListener(mp);
        mp.addMouseListener(mp);
        t.start();
        f.show();
    }
}