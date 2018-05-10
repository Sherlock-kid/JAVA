import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    Image bg;
    public MyPanel()
    {
        setOpaque(false);//设置透明色,这个不能少，不然也会看不到效果
        bg=new ImageIcon("..\\QIM\\Image\\Tim.jpg").getImage();
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(bg,0,0,bg.getWidth(null),bg.getHeight(null),null);
        super.paint(g);
    }
}
