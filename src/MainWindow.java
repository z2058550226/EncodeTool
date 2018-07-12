

import util.CodeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

    private JFrame mMainFrame;
    private JPanel mPanel;
    private JLabel mKeyLabel = new JLabel("加密秘钥");
    private JLabel mDataLabel = new JLabel("加密数据");
    private JTextField mTfEncodeKey = new JTextField("7C0jQ4M8");
    private JTextArea mTaOriData = new JTextArea("");
    private JButton mBtnEncrypt = new JButton("加密");
    private JTextArea mTaResult = new JTextArea("");

    @Override
    public void show() {
        mMainFrame = new JFrame("加密工具");
        // Setting the width and height of frame
        mMainFrame.setSize(600, 450);
        mMainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mMainFrame.setResizable(false);//固定窗体大小

        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width / 2; // 获取屏幕的宽
        int screenHeight = screenSize.height / 2; // 获取屏幕的高
        int height = mMainFrame.getHeight(); //获取窗口高度
        int width = mMainFrame.getWidth(); //获取窗口宽度
        mMainFrame.setLocation(screenWidth - width / 2, screenHeight - height / 2);//将窗口设置到屏幕的中部
        //窗体居中，c是Component类的父窗口
        //mainframe.setLocationRelativeTo(c);
        Image myimage = kit.getImage("res/test.jpg"); //由tool获取图像
        mMainFrame.setIconImage(myimage);
        initPanel();//初始化面板
        mMainFrame.add(mPanel);
        mMainFrame.setVisible(true);
    }

    /**
     * 创建面板，这个类似于 HTML 的 div 标签
     * 我们可以创建多个面板并在 JFrame 中指定位置
     * 面板中我们可以添加文本字段，按钮及其他组件。
     */
    private void initPanel() {
        mPanel = new JPanel();
        mPanel.setLayout(null);
        //this.panel = new JPanel(new GridLayout(3,2)); //创建3行3列的容器
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        mKeyLabel.setBounds(20, 10, 60, 30);
        mTfEncodeKey.setBounds(90, 10, 190, 30);
        mDataLabel.setBounds(20, 50, 60, 50);

        Rectangle rtOriData = new Rectangle(90, 50, 190, 50);
//        mTaOriData.setBounds(rtOriData);
        JScrollPane spOriData = new JScrollPane(mTaOriData);
        spOriData.setBounds(rtOriData);
        mBtnEncrypt.setBounds(20, 110, 260, 30);
        Rectangle rtResult = new Rectangle(0, 150, 600, 300);
//        mTaResult.setBounds(0, 150, 600, 300);
        JScrollPane spResult = new JScrollPane(mTaResult);
        spResult.setBounds(rtResult);
        mTaResult.setLineWrap(true);        //激活自动换行功能
        mTaResult.setWrapStyleWord(true);            // 激活断行不断字功能
        mTaOriData.setLineWrap(true);
        mTaResult.setWrapStyleWord(true);

        mPanel.add(mDataLabel);
        mPanel.add(mKeyLabel);
        mPanel.add(mTfEncodeKey);

        mPanel.add(spOriData);
        mPanel.add(mBtnEncrypt);
        mPanel.add(spResult);

        //增加动作监听
        mBtnEncrypt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println(event.getActionCommand());
        if (event.getSource() == mBtnEncrypt) {
            if ("".equals(mTfEncodeKey.getText())) {
                JOptionPane.showMessageDialog(null, "加密秘钥不能为空", "提示", JOptionPane.WARNING_MESSAGE);//弹出提示对话框，warning
                return;
            } else {
                CodeUtil.KEY = mTfEncodeKey.getText();
            }
            System.out.println("aaa");
            try {
                String data = mTaOriData.getText().trim();
                String encode = CodeUtil.Encode(data);
                mTaResult.setText(encode);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "加密秘钥不合法", "提示", JOptionPane.WARNING_MESSAGE);
            }

        }
    }


}
