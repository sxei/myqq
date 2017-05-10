package client.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import client.frame.MyPanel;
import javax.swing.JLabel;
//import com.sun.awt.AWTUtilities;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	public JPasswordField pwd密码;
	private JLabel lblQQ2013;
	public JLabel lbl头像;
	public JCheckBox checkBox记住密码;
	public JCheckBox checkBox自动登录;
	public JLabel lbl登录;
	public JTextField textField用户名;
	public JLabel lbl注册账号;
	public JLabel lbl忘记密码;
	public JLabel lbl最小化;
	public JLabel lbl退出;
	public JLabel lbl多账号;
	public JLabel lbl设置;
	public JComboBox comboBox状态;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					//AWTUtilities.setWindowOpaque(frame, false);//设置窗体完全透明
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		setTitle("QQ2013");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/client/img/QQ_64.png")));
		setUndecorated(true);//设置窗体没有边框
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 272);
		
		contentPane = new MyPanel("../img/QQ2011_Login.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pwd密码 = new JPasswordField();
		pwd密码.setText("123");
		pwd密码.setEchoChar('●');
		pwd密码.setBounds(104, 163, 154, 26);
		contentPane.add(pwd密码);
		
		lblQQ2013 = new JLabel("QQ2013");
		lblQQ2013.setForeground(new Color(0, 0, 51));
		lblQQ2013.setFont(new Font("宋体", Font.BOLD, 16));
		lblQQ2013.setBounds(14, 6, 55, 18);
		contentPane.add(lblQQ2013);
		
		lbl头像 = new JLabel("");
		lbl头像.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/headImage/head_boy_01_64.jpg")));
		lbl头像.setBounds(18, 127, 64, 64);
		contentPane.add(lbl头像);
		
		checkBox记住密码 = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		checkBox记住密码.setBounds(156, 198, 76, 18);
		contentPane.add(checkBox记住密码);
		
		checkBox自动登录 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		checkBox自动登录.setBounds(237, 198, 76, 18);
		contentPane.add(checkBox自动登录);
		
		lbl登录 = new JLabel("");
		lbl登录.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/button_login_1.png")));
		lbl登录.setBounds(262, 237, 69, 22);
		contentPane.add(lbl登录);
		
		textField用户名 = new JTextField();
		textField用户名.setText("\u9A6C\u5316\u817E");
		textField用户名.setBounds(104, 128, 154, 26);
		contentPane.add(textField用户名);
		textField用户名.setColumns(10);
		
		lbl注册账号 = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		lbl注册账号.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lbl注册账号.setForeground(new Color(0, 51, 255));
		lbl注册账号.setBounds(288, 132, 55, 18);
		contentPane.add(lbl注册账号);
		
		lbl忘记密码 = new JLabel("\u5FD8\u8BB0\u5BC6\u7801");
		lbl忘记密码.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lbl忘记密码.setForeground(new Color(0, 51, 255));
		lbl忘记密码.setBounds(288, 167, 55, 18);
		contentPane.add(lbl忘记密码);
		
		lbl最小化 = new JLabel("");
		lbl最小化.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/login_minsize_1.png")));
		lbl最小化.setBounds(284, 0, 29, 19);
		contentPane.add(lbl最小化);
		
		lbl退出 = new JLabel("");
		lbl退出.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/login_exit_1.png")));
		lbl退出.setBounds(312, -1, 37, 20);
		contentPane.add(lbl退出);
		
		lbl多账号 = new JLabel("");
		lbl多账号.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/login_duozhanghao_1.png")));
		lbl多账号.setBounds(14, 237, 69, 21);
		contentPane.add(lbl多账号);
		
		lbl设置 = new JLabel("");
		lbl设置.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/login_setting_1.png")));
		lbl设置.setBounds(93, 237, 69, 21);
		contentPane.add(lbl设置);
		
		comboBox状态 = new JComboBox();
		comboBox状态.setBounds(104, 195, 40, 24);
		contentPane.add(comboBox状态);
	}
}
