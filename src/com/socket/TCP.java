package com.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import client.common.MyTextPane;
import client.control.Chat;

import com.MyTools;
import com.MyTools.Flag;

public class TCP
{
	private Socket socket = null;
	private String clientName="";//用户名
	private int clientServerPort=0;//客户端端口号
	private int userState=0;//0表示在线
	
	/**
	 * 客户端TCP初始化
	 * @param serverIP 服务端IP地址
	 * @param serverPort  服务端端口号
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TCP(String serverIP, int serverPort) throws Exception
	{
		socket = new Socket(serverIP, serverPort);
		System.out.println("客户端TCP成功启动！");
		getMessageNewThread();//对于客户端的TCP，给它启动一个新线程不停的接收消息
	}
	
	/**
	 * TCP的另一种构造方法
	 * @param server 传过来的Server端TCP连接
	 */
	public TCP(ServerSocket server)
	{
		try
		{
			socket = server.accept();
			System.out.println("新增一台客户端与服务端连接！");
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
	}
	
	/**
	 * 关闭Socket连接
	 */
	public void closeSocket()
	{
		try
		{
			if(socket!=null)
				socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 开辟新线程后台接收消息并处理
	 * @param tcp 传过来的TCP连接
	 */
	public void getMessageNewThread()
	{
		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				boolean exit=false;
				while(!exit)
				{
					try
					{
						//将获取的消息按#分割，msg[0]的表示标志，msg[1]表示内容
						String[] temp=getMessage().split(MyTools.FLAGEND);
						String flagHead=temp[0];//获取消息的标志
						String message=temp[1];//获取消息的真实内容
						Flag flag=MyTools.stringToFlagEnum(flagHead);//获取标志
						dealWithMessage(flag,message);
					}
					catch (Exception e)
					{
						dealWithExit();
						exit=true;
					}
				}
			}
		};
		new Thread(runnable).start();
	}
	
	public void dealWithMessage(Flag flag,String message)
	{
		
	}
	public void dealWithExit()
	{
		
	}
	/**
	 * 发送消息
	 * @param text
	 *            要发送的文本
	 */
	public void sendMessage(String text)
	{
		try
		{
			socket.getOutputStream().write(text.getBytes());
			System.out.println("TCP成功发送：" + text);
			Thread.sleep(100);//每发送完一个消息线程休息一下，防止连续发送消息重叠在一起
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获取消息
	 * 
	 * @return 获取的文本
	 */
	public String getMessage() throws Exception
	{
    	byte[] b = new byte[1024];
    	int len = socket.getInputStream().read(b);
    	String str = new String(b, 0, len);
    	System.out.println("TCP接收到：" + str);
    	return str;
	}
	public void sendFile()
	{
		
	}
	/**
	 * @param imgPath
	 *            根据图片路径发送图片
	 * 
	 */
	public void sendImg(String imgPath)
	{
		try
		{
			File imgFile = new File(imgPath);
			FileInputStream imgFileIn = new FileInputStream(imgFile);
			// 获取文件名
			String imgName = imgFile.getName();
			byte[] buff = new byte[1024];
			int len = 0;

			// 首先发送文件名
			sendMessage(Flag.SENDIMG+MyTools.FLAGEND+imgName);
			OutputStream out = socket.getOutputStream();
//			out.write(imgName.getBytes());
			// 开始发送图片
			System.out.println("开始发送图片……");
			if(socket.isClosed())
				System.out.println("已关闭");
			else {
				System.out.println("未关闭");
			}
			while ((len = imgFileIn.read(buff)) != -1)
			{
				System.out.println("111");
				out.write(buff, 0, len);
				System.out.println("222");
			}
			out.write("#文件发送完毕#".getBytes());
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("图片已发送完成");

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 得到图片
	 */
	public void getImg(Chat chat ,String imgName)
	{
		File imgFile = null;
		try
		{
			InputStream in = socket.getInputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			// 存放接收文件对象
			imgFile = new File("./" + System.currentTimeMillis() + imgName);
			FileOutputStream imgOut = new FileOutputStream(imgFile);

			System.out.println("12342");
			len = 1024;//in.read(buff,0,buff.length);
			//imgOut.write(buff, 0, len);
			while (len ==1024)
			{
				System.out.println("len="+len);
				
				System.out.println("图片正在接收....");
				len = in.read(buff,0,buff.length);
				String str=new String(buff,0,len);
				if(str.equals("#文件发送完毕#"))
					break;
				imgOut.write(buff, 0, len);
			}
			System.out.println("图片接收完成");
			in.close();
			imgOut.close();
			
			new MyTextPane(chat.jTextPane接收框).addIcon(ImageIO
					.read(new FileInputStream(imgFile)), chat.friendName);
			//chat.jTextPane接收框.insertIcon(new ImageIcon()));
			System.out.println("图片插入完成");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * 获取客户端的IP地址
	 * @return
	 */
	public String getClientIP()
	{
		return socket.getInetAddress().getHostAddress().replaceAll("/", "");
	}
	
	/**
	 *  获取本机IP地址
	 * @return 返回字符串形式的IP地址
	 */
	public static String getLocalHostIP()
	{
		try
		{
			return InetAddress.getLocalHost().getHostAddress().replaceAll("/", "");
		}
		catch (UnknownHostException e)
		{
			return null;
		}
	}
	/**
	 * 获取客户端的用户名
	 * @return
	 */
	public String getClientName()
	{
		return clientName;
	}
	/**
	 * 设置客户端的用户名
	 * @param name
	 */
	public void setClientName(String name)
	{
		this.clientName=name;
	}

	/**
	 * 获取客户端的TCP服务端端口号
	 * @return
	 */
	public int getClientServerPort()
	{
		return clientServerPort;
	}

	/**
	 * 设置客户端的TCP服务端端口号
	 * @param clientPort
	 */
	public void setClientServerPort(int clientServerPort)
	{
		this.clientServerPort=clientServerPort;
	}
	
	/**
	 * 获取服务端的端口号
	 */
	public int getServerPort()
	{
		return socket.getLocalPort();//返回自己的端口，如5000
	}
	public int getClientPort()
	{
		return socket.getPort();//返回与别人建立连接的端口，如65544
	}
	/**
	 * 设置当前登录用户状态
	 * @param userState
	 */
	public void setUserState(int userState)
	{
		this.userState=userState;
	}
	/**
	 * 获取当前登录用户状态
	 * @return
	 */
	public int getUserState()
	{
		return userState;
	}
}
