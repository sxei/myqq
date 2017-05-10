package client.socket;

import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.xml.transform.Templates;

import client.common.MyTextPane;
import client.control.Chat;
import client.control.Main;
import client.control.RecieveThread;
import client.frame.PublicMessageFrame;
import client.frame.SendFileFrame;

import com.MyTools;
import com.MyTools.Flag;
import com.socket.TCP;
import com.socket.TCPServer;

public class S_TCP extends TCPServer
{
	ArrayList<Chat> chats=new ArrayList<Chat>();//存放所有的打开的聊天窗体集合
	Main main=null;//传过来的主窗体
	public S_TCP(int serverPort,Main main)
	{
		super(serverPort);
		this.main=main;
	}
	/**
	 * 处理客户端发来的各种信息
	 * @param flag 信息标志
	 * @param message 消息内容
	 */
	@Override
	public void dealWithMessage(Flag flag,String message,TCP tcp)
	{
		switch (flag)
		{
			case START_CHAT:doStartChat(message,tcp);break;
			case MESSAGE:showMessage(message,tcp);break;
			case SENDFILE:doSendFile(message,tcp);break;
			case SENDIMG: dogetImg(message ,tcp);break;
			case FACE:doFace(message,tcp);break;//如果是表情
			default:break;
		}
	}
	
	/**
	 * 接收图片
	 * @param message
	 * @param tcp
	 */
	private void dogetImg(String message,TCP tcp)
	{
		Chat currentChatWindoew =
		null;
		for(Chat chat:chats)
		{
			if(chat.friendName.equals(tcp.getClientName()))
				currentChatWindoew = chat;
		}
	  tcp.getImg(currentChatWindoew ,message);
	}
	
	
	/**
	 * 处理客户端退出的相关事件
	 * @param tcp TCP连接
	 */
	@Override
	public void dealWithExit(TCP tcp)
	{
		
	}
	/**
	 * 服务端启动后要做的事情，把这部分单独提取出来的目的是为了方便子类继承时重写
	 */
	@Override
	public void afterServerStart()
	{
	}
	public void doStartChat(String message,TCP tcp)
	{
		tcp.setClientName(message);//设置对方的名字
		if(!message.equals(main.lbl用户名.getText()))
		{
			Chat chat=new Chat(tcp,tcp.getClientName(),main.lbl用户名.getText());
    		chat.setTitle("与" + tcp.getClientName()+"("+tcp.getClientIP()+")聊天中");// 设置窗体标题
    		chats.add(chat);
		}
		else
		{
			JOptionPane.showMessageDialog(null,	"您正在与自己聊天，MyQQ将只会打开一个聊天框！");
		}
	}
	public void showMessage(String message,TCP tcp)
	{
		for(Chat chat:chats)
		{
			if(chat.friendName.equals(tcp.getClientName()))
			{
				if(!chat.isVisible())
				{
					new PublicMessageFrame(tcp.getClientName()+"给您发来消息",message,chat);
					//对方未打开聊天窗体时就播放声音
					MyTools.playMsgSound();
				}
				chat.setReceivePaneText(false, message);
			}
		}
	}
	/**
	 * 处理接收文件的相关事件
	 */
	public void doSendFile(String message,TCP tcp)
	{
		for(Chat chat:chats)
		{
			if(chat.friendName.equals(tcp.getClientName()))
			{
				chat.friendGetFilePort=Integer.parseInt(message);
				System.out.println("已成功获取好友的接收文件端口:"+message);
			}
		}
	}
	/**
	 * 处理表情
	 * @param message
	 * @param tcp
	 */
	public void doFace(String message,TCP tcp)
	{
		for(Chat chat:chats)
		{
			if(chat.friendName.equals(tcp.getClientName()))
			{
				new MyTextPane(chat.jTextPane接收框).addIcon(MyTools.getFaceByIdx(Integer.parseInt(message)), chat.friendName);
			}
		}
	}
}
