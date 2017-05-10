package server.frame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import server.QQServer;

import client.frame.MyPanel;

import com.MyTools;

import java.util.Calendar;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;


public class ServerMana extends JPanel implements Runnable
{
	QQServer qqServer=null;//QQ服务器
	
	
	JButton btnStart;
	JLabel lblTime;
	Calendar calendar=Calendar.getInstance();
	private JLabel currentTime;
	private JLabel label_1;
	private JLabel lblRun;
	private JLabel lblStateShow;
	public JLabel lblState;
	public int allTime=0;
	public long startTime=0;
	public long endTime=0;
	public boolean isRun=false;
	public JTextArea textArea公告;
	private JPanel panel;
	private JTextArea textArea弹窗;
	private JButton btn弹窗;
	
	public ServerMana() 
	{
		setLayout(null);
		
		btnStart = new JButton("\u542F\u52A8MyQQ\u670D\u52A1\u5668");
		btnStart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				startOrCloseServer();
			}
		});
		btnStart.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnStart.setBounds(29, 30, 301, 76);
		add(btnStart);
		
		JLabel label = new JLabel("\u5F53\u524D\u670D\u52A1\u5668\u65F6\u95F4\uFF1A");
		label.setBounds(29, 169, 106, 15);
		add(label);
		
		currentTime = new JLabel("");
		currentTime.setBounds(153, 169, 192, 15);
		add(currentTime);
		
		label_1 = new JLabel("\u670D\u52A1\u5668\u8FD0\u884C\u65F6\u95F4\uFF1A");
		label_1.setBounds(29, 203, 106, 15);
		add(label_1);
		
		lblRun = new JLabel("");
		lblRun.setBounds(153, 203, 135, 15);
		add(lblRun);
		
		lblStateShow = new JLabel("\u5F53\u524D\u670D\u52A1\u5668\u72B6\u6001\uFF1A");
		lblStateShow.setBounds(28, 132, 107, 15);
		add(lblStateShow);
		
		lblState = new JLabel("服务器未运行");
		lblState.setBounds(153, 132, 135, 15);
		add(lblState);
		
		JPanel panel公告 = new JPanel();
		panel公告.setBorder(new TitledBorder(null, "\u53D1\u5E03\u804A\u5929\u5BA4\u516C\u544A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel公告.setBounds(29, 231, 289, 240);
		add(panel公告);
		
		textArea公告 = new JTextArea();
		
		JButton btn发布公告 = new JButton("\u53D1\u5E03\u804A\u5929\u5BA4\u516C\u544A");
		btn发布公告.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sendPublicMessage();
			}
		});
		GroupLayout gl_panel公告 = new GroupLayout(panel公告);
		gl_panel公告.setHorizontalGroup(
			gl_panel公告.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea公告, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel公告.createSequentialGroup()
					.addContainerGap(63, Short.MAX_VALUE)
					.addComponent(btn发布公告, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		gl_panel公告.setVerticalGroup(
			gl_panel公告.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel公告.createSequentialGroup()
					.addComponent(textArea公告, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn发布公告, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		panel公告.setLayout(gl_panel公告);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u53D1\u5E03\u5F39\u7A97\u516C\u544A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(330, 231, 256, 240);
		add(panel);
		panel.setLayout(null);
		
		textArea弹窗 = new JTextArea();
		textArea弹窗.setBounds(16, 33, 222, 139);
		panel.add(textArea弹窗);
		
		btn弹窗 = new JButton("\u53D1\u5E03\u5F39\u7A97\u516C\u544A");
		btn弹窗.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showWindow();
			}
		});
		btn弹窗.setBounds(71, 177, 127, 46);
		panel.add(btn弹窗);
		Thread thread=new Thread(this);
		thread.start();
		
	}
	//设置窗体的背景图片
	/*@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);//这句话有时候必须写，有时候可以不写
		g.drawImage(MyTools.getIcon("../img/registerBGimg2.jpg").getImage(), 0, 0, null);
	}*/


	public void run()
	{
		while(true)
		{
			showTime();
			if(isRun)
			{
				endTime=System.currentTimeMillis();
				showRunTime();
			}
			else 
			{
				showRunTime();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void showTime()
	{
		Calendar calendar=Calendar.getInstance();
		String time=calendar.get(Calendar.YEAR)+"年"
				+(calendar.get(Calendar.MONTH)+1)+"月"
				+calendar.get(Calendar.DATE)+"日"
				+" "
				+calendar.get(Calendar.HOUR_OF_DAY)+":"
				+calendar.get(Calendar.MINUTE)+":"
				+calendar.get(Calendar.SECOND);
		currentTime.setText(time);
	}
	public void showRunTime()
	{
		allTime=(int)(endTime-startTime)/1000;
		lblRun.setText(String.valueOf(allTime));
	}
	public void startOrCloseServer()
	{
		if(btnStart.getText().equals("启动MyQQ服务器"))
		{
			qqServer=new QQServer(MyTools.QQServerPort);
			allTime=0;
			startTime=System.currentTimeMillis();
			isRun=true;
			lblState.setText("服务器正在运行中...");
			btnStart.setText("停止MyQQ服务器");
		}
		else if(btnStart.getText().equals("停止MyQQ服务器"))
		{
			if(qqServer!=null)
			{
				qqServer.closeServer();
				isRun=false;
				lblState.setText("服务器已关闭。");
				JOptionPane.showMessageDialog(null,"本次服务器总共运行"+allTime+"秒");
				btnStart.setText("启动MyQQ服务器");
			}
		}
	}
	/**
	 * 发布聊天室公告
	 */
	public void sendPublicMessage()
	{
		if(btnStart.getText().equals("停止MyQQ服务器"))
		{
    		if(textArea公告.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "公告内容不能为空！");
    		else
    			qqServer.sendPublicMessage(textArea公告.getText());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "您还未启动MyQQ服务器，不能发布公告！");
		}
	}
	/**
	 * 弹窗
	 */
	public void showWindow()
	{
		if(btnStart.getText().equals("停止MyQQ服务器"))
		{
    		if(textArea弹窗.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "弹窗内容不能为空！");
    		else
    			qqServer.sendShowWindow(textArea弹窗.getText());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "您还未启动MyQQ服务器，不能发布弹窗公告！");
		}
	}
}
