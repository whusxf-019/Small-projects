import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit; public class Ball {
    int x;//小球的X坐标
    int y;//小球的Y坐标
    int d;//小球的直径
    int xStep;//小球的移动步长
    int yStep;//小球的移动步长
    Color color;//小球颜色
    Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕分辨率
    Color[] c = { Color.BLUE, Color.CYAN, Color.YELLOW, Color.GREEN,
            Color.GRAY, Color.RED, Color.ORANGE };
    public void move() {
        x += xStep;
        y += yStep;
        if (x > scrSize.width - 5 - d || x < 0) {
            xStep *= -1;
            this.color=c[(int) (Math.random() * 7)];
        }
        if (y > scrSize.height - 25 - d || y < 0) {
            yStep *= -1;
            this.color=c[(int) (Math.random() * 7)];
        }
    }
}