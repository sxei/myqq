package client.control;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import com.MyTools;
import com.MyTools.Flag;

import client.common.MyLabel;
import client.frame.LoginFrame;
import client.frame.RegisterFrame;
import client.socket.CS_TCP;
import com.sun.awt.AWTUtilities;//这个导入很重要，不导入报错，导入但又警告，所以不要理警告
/**
 * @author LXA
 * 登录窗体，继承自LoginFrame
 */
public class Login extends LoginFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5055331246882705423L;
	int width=350;
	int height=266;
	private Point lastPoint = null;//存放按下鼠标时的坐标点
	public CS_TCP cs_TCP=null;//声明一个客户端TCP
	public Main main; //声明一个主窗体
	public RegisterFrame registerFrame=null;
	/**
	 * 程序的主入口
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyTools.changeSkin();//更换皮肤
		new Login();
	}
	
	
	/**
	 * 登录窗体的构造方法
	 */
	public Login()
	{
		init();
	}

	/**
	 * 初始化
	 */
	public void init()
	{
		MyTools.setWindowsMiddleShow(this,width,height);//设置窗体居中显示
		new MyLabel(lbl登录, "../img/button/button_login", "png").addEvent();
		new MyLabel(lbl最小化, "../img/button/login_minsize", "png").addEvent();
		new MyLabel(lbl退出, "../img/button/login_exit", "png").addEvent();
		new MyLabel(lbl多账号, "../img/button/login_duozhanghao", "png").addEvent();
		new MyLabel(lbl设置, "../img/button/login_setting", "png").addEvent();
		new MyLabel(lbl注册账号).addEvent();
		initUserStatus();
		AWTUtilities.setWindowOpaque(this, false);//设置窗体完全透明
		addEvent();
		this.setVisible(true);
		main=new Main();
	}
	/**
	 * 初始化用户是否在线等状态
	 */
	public void initUserStatus()
	{
		comboBox状态.removeAllItems();
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_online_12.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_qme_12.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_leave_12.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_busy_12.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_invisible_12.png"));
	}
	

	/**
	 * 登录到服务器
	 */
	public void login()
	{
		String name=textField用户名.getText();
		String password=new String(pwd密码.getPassword());
		if(name.equals("")||password.equals(""))
			JOptionPane.showMessageDialog(this, "用户名和密码不能为空！","错误",JOptionPane.ERROR_MESSAGE);
		else 
		{
			try
    		{
    			if(cs_TCP==null)
    				cs_TCP=new CS_TCP(MyTools.QQServerIP, MyTools.QQServerPort,this,main);
    			System.out.println("开始检测用户名和密码……");
    			
    			cs_TCP.sendMessage(Flag.LOGIN+MyTools.FLAGEND+name+MyTools.SPLIT1+password+MyTools.SPLIT1+main.getServerPort()+MyTools.SPLIT1+comboBox状态.getSelectedIndex());
    		}
    		catch (Exception e)
    		{
    			JOptionPane.showMessageDialog(null, "连接服务器失败！请检查网络连接或确保QQ服务器已启动！");
    		}
		}
	}
	
	/**
	 * 处理窗体的鼠标按下事件
	 * @param e
	 */
	public void mousePress(MouseEvent e)
	{
		lastPoint = e.getLocationOnScreen();
	}
	/**
	 * 处理窗体的拖拽事件
	 * @param e
	 */
	public void mouseDrag(MouseEvent e)
	{
		Point point = e.getLocationOnScreen();
		int offsetX = point.x - lastPoint.x;
		int offsetY = point.y - lastPoint.y;
		Rectangle bounds = this.getBounds();
		bounds.x += offsetX;
		bounds.y += offsetY;
		this.setBounds(bounds);
		lastPoint = point;
	}
	
	/**
	 * 给窗体添加事件
	 */
	public void addEvent()
	{
		lbl登录.addMouseListener(new MouseAdapter()
		{
			//登录按钮的单击事件
			@Override
			public void mouseClicked(MouseEvent e)
			{
				login();
			}
		});
		this.addMouseListener(new MouseAdapter()
		{
			//窗体的鼠标按下事件
			@Override
			public void mousePressed(MouseEvent e)
			{
				mousePress(e);
			}
		});
		this.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				mouseDrag(e);
			};
		});
		lbl退出.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		lbl最小化.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "功能暂未实现！敬请期待！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lbl多账号.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "功能暂未实现！敬请期待！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lbl设置.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "功能暂未实现！敬请期待！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lbl注册账号.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				new Register();
			}
		});
	}
}
