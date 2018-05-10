import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    public static void main(String[] args) {
        WindowLogin win = new WindowLogin(500, 300, 444, 335);

        //设置上下两个面板界面-----------------------------------------------------------------------------------
        MyPanel uppanel = new MyPanel(); //上面部分
        uppanel.setLayout(null);
             //添加关闭按钮
        JButton close = new JButton();
        close.setBackground(Color.CYAN); //按钮背景色
        close.setIcon(new ImageIcon("..\\QIM\\Image\\close.png")); //关闭按钮图标
        uppanel.add(close);
        close.setBounds(410,5,25,25); //关闭按钮坐标位置
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); //给关闭按钮注册监听器


        //分界线---------------------------------------------------------------------------------------------
        JPanel downpanel = new JPanel(); // 下面部分
        downpanel.setBackground(Color.white); //设置下面部分背景色为白色
        downpanel.setLayout(null); //下面使用空布局

        JButton Blogin = new JButton("登     陆"); //登陆按钮
        Blogin.setBackground(Color.lightGray); //登陆按钮设置为浅灰色
        JTextField account = new JTextField(); //账号输入框
        JPasswordField password = new JPasswordField(); //密码框
        JCheckBox check1 = new JCheckBox("记住密码"); //复选框1
        JCheckBox check2 = new JCheckBox("自动登录"); //复选框2
        JLabel label1 = new JLabel("注册账号");
        JLabel label2 = new JLabel("找回密码");
        downpanel.add(account); //将account添加到downpanel中
        downpanel.add(password); //将password添加到downpanel中
        downpanel.add(Blogin); //将Blogin添加到downpanel中
        downpanel.add(check1);
        downpanel.add(check2);
        downpanel.add(label1);
        downpanel.add(label2);
        Blogin.setBounds(120,95,200,40); //设置Blogin在面板中的位置
        password.setBounds(120,45,200,25); //设置password在面板中的位置
        account.setBounds(120,20,200,25); //设置account在面板中的位置
        check1.setBounds(116,70,80,16);
        check1.setBackground(Color.white);
        check2.setBounds(246,70,80,16);
        check2.setBackground(Color.white);
        label1.setBounds(330,20,60,25);
        label2.setBounds(330,45,60,25);



        //设置上下两个面板结束-----------------------------------------------------------------------------------
        win.add(uppanel); //将uppanel添加到窗体中
        win.add(downpanel); //将downpanel添加到窗体中
        win.setVisible(true);   //将界面设为可见
    }
}
