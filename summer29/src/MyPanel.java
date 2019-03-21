import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable,KeyListener,MouseListener{ int count=50; //设置小球的数量
    Ball[] balls=new Ball[count];
    Color[] color = { Color.BLUE, Color.CYAN, Color.YELLOW, Color.GREEN,
            Color.GRAY, Color.RED, Color.ORANGE };
    Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕分辨率
    public MyPanel(){
        for (int i = 0; i < balls.length; i++) {
            Ball ball = new Ball();
            ball.x = ((int)(Math.random() * (scrSize.width-150)))+20;
            ball.y = ((int)(Math.random() * (scrSize.height-150)))+20;
            ball.d = (int)(Math.random() * 80 + 50);
            ball.xStep = (int)(Math.random() * 5) - 3;
            int[] a={1,-1};
            if(ball.xStep==0){
                ball.xStep=a[(int)(Math.random()*2)];
            }
            ball.yStep = (int)(Math.random() * 5) - 3;
            if(ball.yStep==0){
                ball.yStep=a[(int)(Math.random()*2)];
            }
            ball.color = color[(int) (Math.random() * 7)];
            balls[i]=ball;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);//设置画笔颜色
        g.fillRect(0, 0, 3000, 2000);//填充背景
        for (int i = 0; i < balls.length; i++) {
            g.setColor(balls[i].color);
            g.fillOval(balls[i].x, balls[i].y, balls[i].d, balls[i].d);
        }
    } @Override
    public void run() {
// TODO Auto-generated method stub
        while (true) {
            for (int i = 0; i < balls.length; i++) {
                balls[i].move();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.setOpaque(false);
            repaint();
        }
    } @Override
    public void keyPressed(KeyEvent e) {
// TODO Auto-generated method stub
        if(e.getKeyCode()==e.VK_ENTER||e.getKeyCode()==e.VK_ESCAPE){//如果按下ENTER或ESC键则程序退出
            System.exit(0);
        }
    } @Override
    public void keyReleased(KeyEvent e) {
// TODO Auto-generated method stub

    } @Override
    public void keyTyped(KeyEvent e) {
// TODO Auto-generated method stub

    } @Override
    public void mouseClicked(MouseEvent e) {
// TODO Auto-generated method stub
        if(e.getClickCount()==2){//如果双击鼠标，程序退出
            System.exit(0);
        }
    } @Override
    public void mouseEntered(MouseEvent e) {
// TODO Auto-generated method stub

    } @Override
    public void mouseExited(MouseEvent e) {
// TODO Auto-generated method stub

    } @Override
    public void mousePressed(MouseEvent e) {
// TODO Auto-generated method stub

    } @Override
    public void mouseReleased(MouseEvent e) {
// TODO Auto-generated method stub

    }
}