package com.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.BreakIterator;


public class UDP
{
	public DatagramSocket socket = null;// 通过UDP建立的Socket
	private int myPort = 6000;// 我的端口，从6000开始查找可用的端口号
	public InetAddress friendIP = null;// 好友的IP地址
	public int friendPort = 0;// 好友的端口

	public UDP(String friendIP)
	{
		getMyUsefulPort();
		try
		{
			this.friendIP = InetAddress.getByName(friendIP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置好友的端口号
	 * @param friendPort
	 */
	public void setFriendPort(int friendPort)
	{
		this.friendPort=friendPort;
	}
	/**
	 * 获取我的端口号，只有当UDP类被实例化后才能调用此方法，
	 * 否则得到的端口号可能不是最终有效的端口号
	 * @return
	 */
	public int getMyPort()
	{
		return this.myPort;
	}
	/**
	 * 获取可用的端口号
	 */
	public void getMyUsefulPort()
	{
		while(true)
		{
    		try
    		{
    			// 实例化一个DatagramSocket
    			socket = new DatagramSocket(myPort);
    			break;
    		}
    		catch (SocketException e)
    		{
    			myPort++;
    		}
		}
	}
	/*
	 * 给好友发送消息
	 */
	public void sendMessage(String text)
	{
		byte[] data = text.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, friendIP,
				friendPort);
		try
		{
			socket.send(packet);// 开始发送消息
			System.out.println("使用UDP成功发送："+text);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 接收消息
	 */
	public String getMessage()
	{
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		try
		{
			socket.receive(dp);
			String message = new String(dp.getData(), 0, dp.getLength());
			System.out.println("使用UDP成功接收到："+message);
			return message;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 开辟新线程后台获取消息,这个方法姑且丢置在这里，暂时不会用到
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
					getMessage();// 调用UDP的获取消息方法
					try
					{
						Thread.sleep(100);// 线程休息100毫秒
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
