package client.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class QunChatFrame extends JFrame
{

	private JPanel contentPane;
	public JTree tree;
	public JButton btn发送;
	public JButton btn关闭;
	public JTextPane textPane发送;
	public JTextPane textPane接收框;
	public JTextArea textArea群公告;
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
					QunChatFrame frame = new QunChatFrame();
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
	public QunChatFrame()
	{
		setTitle("MyQQ\u5B98\u65B9\u804A\u5929\u5BA4");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 712, 576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel右边 = new JPanel();
		
		JPanel panel左边 = new JPanel();
		panel左边.setBackground(new Color(204, 255, 204));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel左边, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel右边, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel右边, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
				.addComponent(panel左边, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
		);
		
		JPanel panel顶部 = new JPanel();
		panel顶部.setBackground(new Color(0, 153, 255));
		
		JPanel panel中间 = new JPanel();
		
		JPanel panel底部 = new JPanel();
		panel底部.setBackground(new Color(204, 255, 255));
		GroupLayout gl_panel左边 = new GroupLayout(panel左边);
		gl_panel左边.setHorizontalGroup(
			gl_panel左边.createParallelGroup(Alignment.LEADING)
				.addComponent(panel底部, GroupLayout.PREFERRED_SIZE, 476, Short.MAX_VALUE)
				.addComponent(panel顶部, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
				.addComponent(panel中间, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
		);
		gl_panel左边.setVerticalGroup(
			gl_panel左边.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel左边.createSequentialGroup()
					.addComponent(panel顶部, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel中间, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel底部, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
		);
		panel顶部.setLayout(null);
		
		JLabel lbl头像 = new JLabel("");
		lbl头像.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/QQ_64.png")));
		lbl头像.setBounds(6, 6, 64, 64);
		panel顶部.add(lbl头像);
		
		JLabel lbl视频 = new JLabel("");
		lbl视频.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_video_54.png")));
		lbl视频.setBounds(82, 16, 54, 54);
		panel顶部.add(lbl视频);
		
		JLabel lbl语音 = new JLabel("");
		lbl语音.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_voice_54.png")));
		lbl语音.setBounds(138, 16, 54, 54);
		panel顶部.add(lbl语音);
		
		JLabel lbl发送文件 = new JLabel("");
		lbl发送文件.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_sendfile_54.png")));
		lbl发送文件.setBounds(193, 16, 54, 54);
		panel顶部.add(lbl发送文件);
		panel中间.setLayout(new BorderLayout(0, 0));
		
		textPane接收框 = new JTextPane();
		panel中间.add(textPane接收框);
		
		textPane发送 = new JTextPane();
		
		btn发送 = new JButton("\u53D1\u9001");
		btn发送.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		
		btn关闭 = new JButton("\u5173\u95ED");
		btn关闭.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		
		JLabel lbl文字 = new JLabel("");
		lbl文字.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_font_32.png")));
		
		JLabel lbl表情 = new JLabel("");
		lbl表情.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_face_32.png")));
		
		JLabel lbl音乐 = new JLabel("");
		lbl音乐.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_music_32.png")));
		
		JLabel lbl图片 = new JLabel("");
		lbl图片.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_picture_32.png")));
		
		JLabel lbl截图 = new JLabel("");
		lbl截图.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_snap_32.png")));
		GroupLayout gl_panel底部 = new GroupLayout(panel底部);
		gl_panel底部.setHorizontalGroup(
			gl_panel底部.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel底部.createSequentialGroup()
					.addContainerGap(285, Short.MAX_VALUE)
					.addComponent(btn关闭, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn发送, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
				.addComponent(textPane发送, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel底部.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl文字)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl表情)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl音乐)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl图片)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl截图)
					.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_panel底部.setVerticalGroup(
			gl_panel底部.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel底部.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_panel底部.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl文字)
						.addComponent(lbl表情)
						.addComponent(lbl音乐)
						.addComponent(lbl图片)
						.addComponent(lbl截图))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane发送, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel底部.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btn发送, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn关闭, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel底部.setLayout(gl_panel底部);
		panel左边.setLayout(gl_panel左边);
		
		JPanel panel公告 = new JPanel();
		panel公告.setBackground(new Color(0, 153, 255));
		panel公告.setBorder(new TitledBorder(null, "\u7FA4\u516C\u544A", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.RED));
		
		JScrollPane scrollPane = new JScrollPane();
		panel公告.setLayout(new BorderLayout(0, 0));
		
		textArea群公告 = new JTextArea();
		textArea群公告.setLineWrap(true);
		textArea群公告.setForeground(new Color(255, 0, 0));
		textArea群公告.setText("\u6682\u65E0\u4EFB\u4F55\u5B98\u65B9\u516C\u544A\uFF01");
		textArea群公告.setFont(new Font("微软雅黑", Font.BOLD, 18));
		textArea群公告.setBackground(new Color(255, 204, 0));
		textArea群公告.setEnabled(false);
		panel公告.add(textArea群公告, BorderLayout.CENTER);
		GroupLayout gl_panel右边 = new GroupLayout(panel右边);
		gl_panel右边.setHorizontalGroup(
			gl_panel右边.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel右边.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_panel右边.createParallelGroup(Alignment.LEADING)
						.addComponent(panel公告, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_panel右边.setVerticalGroup(
			gl_panel右边.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel右边.createSequentialGroup()
					.addComponent(panel公告, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
		);
		
		tree = new JTree();
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
		panel右边.setLayout(gl_panel右边);
		contentPane.setLayout(gl_contentPane);
	}
}
