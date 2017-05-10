package server.frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import client.control.Main;

import com.MyTools;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	UserMana userMana;
	MsgMana msgMana;
	ServerMana serverMana;
	
	/**
	 * 主方法
	 */
	public static void main(String[] args) 
	{
		MyTools.changeSkin();
		new MainWindow().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() 
	{
		setTitle("MyQQ\u670D\u52A1\u5668\u540E\u53F0\u7BA1\u7406");
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		
		setBounds((size.width-753)/2, (size.height-500)/2, 753, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		serverMana=new ServerMana();
		tabbedPane.addTab("服务管理", new ImageIcon(MainWindow.class.getResource("/client/img/manager_server.png")), serverMana, null);
		
		userMana=new UserMana();
		tabbedPane.addTab("用户管理", new ImageIcon(MainWindow.class.getResource("/client/img/manager_users.png")), userMana, null);
		
		msgMana=new MsgMana();
		tabbedPane.addTab("消息管理", new ImageIcon(MainWindow.class.getResource("/client/img/manager_message.png")), msgMana, null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
              int n=JOptionPane.showConfirmDialog(null,"确认退出吗?","确认对话框",
                                       JOptionPane.YES_NO_OPTION );
              if(n==JOptionPane.YES_OPTION)  
                 System.exit(0);
            }});
	    validate();  
	}
}
