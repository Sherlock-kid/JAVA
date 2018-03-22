/*
* 大致思路是用一个面板放文本框，一个面板放按钮群，最后将两个面板放到一个大面板中
* */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WindowGridLayout extends JFrame implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String label = e.getActionCommand();//获得点击按钮字符串

        //将特殊的列出来，剩下的为操作符
        if("0123456789.".indexOf(label) >= 0){//如果label出现在字符串中，则返回出现的位置，否则返回-1
            number(label);
        }
        else if(label.equals("C")){//点击C触发
            handleC();
        }
        else if(label.equals("CE")){//点击CE触发
            text.setText("0");
        }
        else if(label.equals("BackSpace")){
            handleBS();
        }
        else{
            handleop(label);
        }
    }
    // 标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    private boolean firstDigit = true;
    //当前运算的运算符
    private String operator = "=";
    //操作是否合法
    private boolean operatorValidFlag =true;
    //计算的中间结果
    private double resultNum = 0.0;
    //计算机按钮名称
    private final String[] keys = {"CE", "C", "BackSpace", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "#", "0", ".", "="};
    //计算器上的按钮
    private JButton Keys[] = new JButton[keys.length];
    // 计算结果文本框
    private JTextField text = new JTextField("0");
    public WindowGridLayout(){
        init(); //初始化
        this.setResizable(false); //不允许修改计算器的大小
        this.setTitle("计算器");//设置窗口名称
        setVisible(true);//窗口可见
        this.pack(); //使计算器各组件大小合适
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭动作
    }
    private void init(){
        text.setHorizontalAlignment(JTextField.RIGHT); //文本框内容采用右对齐方式
        text.setEditable(false); //不允许修改文本框结果
        text.setBackground(Color.WHITE); //文本框背景颜色设置为白色
        JPanel pal1 = new JPanel(); //设置一个放置按钮的面板
        pal1.setLayout(new GridLayout(5,4,3,4));// 用网格布局管理器，5行，4列的网格，网格之间的水平方向间隔为3个象素，垂直方向间隔为4个象素
        for(int i=0; i < keys.length; i++){ //创建按钮并添加到面板
            Keys[i] = new JButton(keys[i]);
            pal1.add(Keys[i]);
            Keys[i].setForeground(Color.blue);//键用蓝色标示
        }

        // 新建一个大面板
        JPanel PAN = new JPanel();
        PAN.setLayout(new BorderLayout(3,3));// 画板采用边界布局管理器，画板里组件之间的水平和垂直方向上间隔都为3象素
        PAN.add("Center", pal1); //将pal1添加到此面板中部
        //建立一个面板放文本框
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());//采用边界布局管理器
        top.add("Center", text);//将文本框添加到面板中部

        //整体布局
        getContentPane().setLayout(new BorderLayout(3, 5));// 画板采用边界布局管理器，画板里组件之间的水平3像素和垂直方向上间隔3象素
        getContentPane().add("North", top);//将top面板添加到顶部
        getContentPane().add("Center", PAN);//将PAN面板添加到中部

        //为按钮添加事件监听器
        for(int i = 0; i < keys.length; i++) {
            Keys[i].addActionListener(this);
        }
    }

        ////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        //事件处理
        private void number(String key) {
            if (firstDigit){
                text.setText(key);
            }
            else if((key.equals("."))&&(text.getText().indexOf(".")<0)){
                // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
                text.setText(text.getText() + ".");
            }
            else if(!key.equals(".")){
                // 如果输入的不是小数点，则将数字附在结果文本框的后面
                text.setText(text.getText()+ key);
            }
            // 以后输入的肯定不会是第一个数字
            firstDigit = false;
        }
        private void handleC() {
            // 初始化计算器的各种值
            text.setText("0");
            firstDigit = true;
            operator = "=";
        }
        private void handleBS(){
            String text1 = text.getText();
            int i = text1.length();
            if (i > 0){
                //退格，将文本最后一个字符去掉
                text1 = text1.substring(0, i - 1);
                if(text1.length()==0){
                    text.setText("0");
                    firstDigit = true;
                    operator = "=";
                }
                else{
                    //显示新文本
                    text.setText(text1);
                }
            }
        }
        private void handleop(String key){
            if(operator.equals("/")){
                //除法运算
                // 如果当前结果文本框中的值等于0
                if(getNumberFromText() == 0.0){
                    //操作不合法
                    operatorValidFlag = false;
                    text.setText("除数不能为0");
                }
                else{
                    resultNum /= getNumberFromText();
                }
            }
            else if(operator.equals("+")){
                //加法运算
                resultNum += getNumberFromText();
            }
            else if(operator.equals("-")){
                //加法运算
                resultNum -= getNumberFromText();
            }
            else if(operator.equals("*")){
                //加法运算
                resultNum *= getNumberFromText();
            }
            else if(operator.equals("/")){
                //加法运算
                resultNum /= getNumberFromText();
            }
            else if (operator.equals("=")) {
                // 赋值运算
                resultNum = getNumberFromText();
            }

            if(operatorValidFlag){
                //双精度浮点数的运算
                long t1;
                double t2;
                t1 = (long) resultNum;
                t2 = resultNum - t1;
                if (t2 == 0) {
                    text.setText(String.valueOf(t1));//将string转换成int
                } else {
                    text.setText(String.valueOf(resultNum));
                }
            }
            // 运算符等于用户按的按钮
            operator = key;
            firstDigit = true;
            operatorValidFlag = true;
        }

        private double getNumberFromText(){
            double result = 0;
            try{
                result = Double.valueOf(text.getText()).doubleValue();
            }
            catch (NumberFormatException e){
            }
            return result;
        }
}