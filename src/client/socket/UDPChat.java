package client.socket;

import client.control.Chat;

import com.socket.UDP;


public class UDPChat extends UDP
{
	Chat chat = null;// 声明一个聊天的类

	public UDPChat(String friendIP, Chat parent)
	{
		super(friendIP);
		this.chat = parent;
		newThreadGetMessage();
	}

	/**
	 * 开辟新线程后台获取消息
	 */
	public void newThreadGetMessage()
	{
		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					String message = getMessage();// 调用父类UDP的获取消息方法
					chat.setReceivePaneText(false, message);// 将接收的消息显示在聊天窗体中的聊天框中
					try
					{
						Thread.sleep(30);// 线程休息100毫秒
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(runnable).start();
	}

}
