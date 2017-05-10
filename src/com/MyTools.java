package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

import client.control.Main;

/**
 * @author 
 * 一些经常调用的方法和枚举，都集中放在这里
 */
public class MyTools
{
	//下面三句话定义一些标志，示例如下：
	//login#马化腾;1234567
	//friends_info#我的好友,柳夏南,王林,吴志强;陌生人,胡锦涛,陈水扁
	public static final String FLAGEND = "#";//定义最当头标志结束的字符
	public static final String SPLIT1 = ";";//定义一级分割字符
	public static final String SPLIT2 = ",";//定义二级分割字符
	public static final String SPLIT3 = "&";//定义三级分割字符
	
	public static final int QQServerPort = 6776; // QQ服务器端口号
	public static final String QQServerIP = "127.0.0.1"; // 服务器IP地址
	// 数据库地址和密码
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/myqq?useUnicode=true&characterEncoding=utf-8";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PWD = "root";
	// public static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	// public static final String JDBC_USER = "test";
	// public static final String JDBC_PWD = "test";

	
	/**
	 * @author LXA
	 *定义一个标志的枚举
	 */
	public enum Flag
	{
		LOGIN,	//“登录”的标志
		SUCCESS,	//“成功”的标志
		FAILED,		//“失败”的标志
		USERINFO,	//“用户信息、好友列表”的标志
		FRIENDS_LIST,	//好友列表
		PORT,	//端口号的标志
		START_CHAT,	//聊天的标志
		MESSAGE,	//聊天内容的标志
		GET_FRIEND_INFO, //获取好友资料的标志
		REGISTER,  //注册
		SENDFILE, //发送文件的标志
		GETFILE_OK,
		QUN_CHAT,   //群聊
		PUBLIC_MESSAGE,  //公告
		SHOW_WINDOW,  //弹窗
		UNDERLINE_MESSAGE,//离线消息
		SENDIMG,//发送图片
		FACE//表情
	};
	
	/**
	 * 将窗体居中显示
	 * @param frame 需要居中显示的窗体
	 */
	public static void setWindowsMiddleShow(JFrame frame)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2, frame.getWidth(), frame.getHeight());
	}
	/**
	 * 将窗体居中显示
	 * @param frame 需要居中显示的窗体
	 * @param width 窗体的宽度
	 * @param height 窗体的高度
	 */
	public static void setWindowsMiddleShow(JFrame frame,int width,int height)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
	}
	
	/**
	 * 更换皮肤
	 */
	public static void changeSkin()
	{
		try
		{
			//UIManager.put("nimbusBase", Color.red);
			//UIManager.put("nimbusBlueGrey", Color.red);//按钮的颜色
			UIManager.put("control", new Color(215, 255, 255));//控件背景色
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文件路径获取图片
	 * @param path 路径
	 * @return 返回获取的图片
	 */
	public static ImageIcon getIcon(String path)
	{
		try
		{
			//System.out.println(Main.class.getResource(path));
			ImageIcon icon=new ImageIcon(ImageIO.read(Main.class.getResource(path)));
			
			return icon;
		}
		catch (IOException e)
		{
			System.out.println("图片："+path+"不存在！");
			return null;
		}
	}
	
	/**
	 * 将字符串转成Flag类型的枚举
	 * @param str 需要转的字符串
	 * @return 返回转换后的Flag枚举
	 */
	public static Flag stringToFlagEnum(String str)
	{
		return Enum.valueOf(Flag.class, str);
	}
	public static String getFaceByIdx(int idx)
	{
		String fileName="";
		if(idx<10)
    	{
    		fileName= "../img/face/f00"+idx+".png";//修改图片路径 
    	}
    	else
    	{
    		fileName= "../img/face/f0"+idx+".png";
		}
		return fileName;
	}
	
	/**
	 * 默认会报如下错误：
	 * 访问限制：由于对必需的库 D:\GreenSoft\Java\jdk1.6.0_10\jre\lib\rt.jar 具有一定限制，因此无法访问类型 AudioStream
	 * 需要作如下修改：右键项目-->属性-->Java编译器-->错误/警告-->勾选“启用特定于项目的设置”-->建议不要使用和限制使用的API-->将“禁止的引用”改为警告
	 */
	public static void playMsgSound()
	{
		try
		{
			sun.audio.AudioPlayer.player.start(new sun.audio.AudioStream(new FileInputStream(new File("msg.wav"))));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void playSystemSound()
	{
		try
		{
			sun.audio.AudioPlayer.player.start(new sun.audio.AudioStream(new FileInputStream(new File("system.wav"))));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
