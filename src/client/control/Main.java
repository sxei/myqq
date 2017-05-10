package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import com.MyTools;
import com.MyTools.Flag;
import com.socket.TCPServer;

import client.common.*;
import client.control.MyTree;
import client.frame.MainFrame;
import client.socket.CC_TCP;
import client.socket.CS_TCP;
import client.socket.S_TCP;

public class Main extends MainFrame
{
	public CS_TCP cs_TCP=null;
	public S_TCP s_TCP=null;
	private int serverPort=5000;//端口从5000开始分配
	final int width=350;//窗体宽度
	final int height=630;//窗体高度
	public QunChat qunChat;//群聊天室
	public Main() 
	{     
		init();
	}
	
	public Main(CS_TCP cs_TCP)
	{
		this.cs_TCP=cs_TCP;
		init();
	}
	
	/**
	 * 初始化主窗体
	 */
	public void init()
	{
		new MyLabel(lbl设置,"../img/button/QQ_settings","png").addEvent();//给最底下的设置按钮添加事件
		MyTools.setWindowsMiddleShow(this,width,height);//设置窗体居中显示
		initUserStatus();//初始化用户是否在线等状态
		s_TCP=new S_TCP(serverPort,this);
	}
	
	/**
	 * 当登录成功后，将登录窗体的CS_TCP传给主窗体
	 * @param cs_TCP
	 */
	public void setCS_TCP(CS_TCP cs_TCP)
	{
		this.cs_TCP=cs_TCP;
	}
	public int getServerPort()
	{
		return s_TCP.getServerPort();
	}
	/**
	 * 初始化用户是否在线等状态
	 */
	public void initUserStatus()
	{
		comboBox状态.removeAllItems();
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_online.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_qme.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_leave.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_busy.png"));
		comboBox状态.addItem(MyTools.getIcon("../img/status/status_invisible.png"));
	}
	/**
	 * 初始化好友列表
	 */
	public void initjTree(String[] groupNames,ArrayList<String[]> friendNames)
	{
		new MyTree(tree, groupNames, friendNames);
	}

	/* 
	 * 开始给对方好友聊天
	 */
	@Override
	public void startChat(ActionEvent e)
	{
		if(tree.getSelectionPath().getPathCount()==3)
		{
			String str=tree.getSelectionPath().getLastPathComponent().toString();
			String friendName=str.substring(0,str.indexOf("("));
			String friendIP=str.substring(str.indexOf("(")+1,str.indexOf(":"));
			int friendPort=Integer.parseInt(str.substring(str.indexOf(":")+1,str.indexOf(")")));
			if(!friendIP.equals("下线或隐身"))
			{	
				Chat chat=new Chat(friendIP,friendPort,friendName,this.lbl用户名.getText());
				chat.setVisible(true);
			}
			else
			{
				Chat chat=new Chat(cs_TCP, friendName);
				chat.setVisible(true);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "对不起，您未选中任何好友！");
		}
	}
	/* 
	 * 请求获取好友资料
	 */
	@Override
	public void getFriendInfo(ActionEvent e)
	{
		String str=tree.getSelectionPath().getLastPathComponent().toString();
		String userName=str.substring(0,str.indexOf("("));
		cs_TCP.sendMessage(Flag.GET_FRIEND_INFO+MyTools.FLAGEND+userName);
	}
	/**
	 * 发送文件
	 * @param e
	 */
	@Override
	public void sendFile(ActionEvent e)
	{
	}
	/**
	 * 删除好友
	 * @param e
	 */
	public void deleteFriend(ActionEvent e)
	{
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		node.removeFromParent();
		JOptionPane.showMessageDialog(null, "删除好友成功！请关闭并打开当前分组以刷新好友列表！");
	}
	/**
	 * 进入聊天室
	 */
	@Override
	public void gotoChatRoom()
	{
		qunChat=new QunChat(this);
	}
	/**
	 * 新建聊天室
	 */
	@Override
	public void buildNewChatRoom()
	{
		JOptionPane.showMessageDialog(null, "新建聊天室属于会员专属功能，您还不是会员，是否想要加入会员，更多功能等你玩转，仅需10元每月哦！","提示",JOptionPane.YES_NO_CANCEL_OPTION);
	}
}
