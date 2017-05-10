package client.socket;

import java.text.DateFormat;
import java.util.Date;

import client.common.MyTextPane;
import client.control.Chat;

import com.MyTools;
import com.MyTools.Flag;
import com.socket.TCP;

/**
 * @author 刘显安
 *客户端与客户端之间的TCP连接
 */
public class CC_TCP extends TCP
{
	Chat chat=null;
	public CC_TCP(String serverIP, int serverPort,Chat chat) throws Exception
	{
		super(serverIP, serverPort);
		this.chat=chat;
	}

	@Override
	public void dealWithMessage(Flag flag, String message)
	{
		switch (flag)
		{
			case MESSAGE:showMessage(message);break;
			case SENDFILE:doStartSendFile(message);break;
			case FACE:doFace(message);break;
			default:break;
		}
	}

	@Override
	public void dealWithExit()
	{
	}
	public void showMessage(String message)
	{
		chat.setReceivePaneText(false, message);
	}
	public void doStartSendFile(String message)
	{
		chat.friendGetFilePort=Integer.parseInt(message);
		System.out.println("已成功获取好友的接收文件端口:"+message);
	}
	public void doFace(String message)
	{
		new MyTextPane(chat.jTextPane接收框).addIcon(MyTools.getFaceByIdx(Integer.parseInt(message)), chat.friendName);
	}
	
	

}
