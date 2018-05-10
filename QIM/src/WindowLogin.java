import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class WindowLogin extends JFrame {
    int xOld=0;
    int yOld=0;
    public WindowLogin(){}
    public WindowLogin(int x, int y, int width, int height){
        init();
        GridLayout grid = new GridLayout(2, 1);
        setLayout(grid); //设置布局
        setLocation(x, y);  //设置初始位置
        setSize(width, height); //设置界面大小
        setUndecorated(true); //去掉JFrame边框
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //关闭操作
        validate();

        //以下代码控制JFrame去掉边框后窗体的移动----------------------------------
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();//记录鼠标按下时的坐标
                yOld = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                WindowLogin.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });
        //---------------------------------------------------------------------------

    }

    void init() { }
}
