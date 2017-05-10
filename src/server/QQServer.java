package server;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import server.dao.MsgDao;
import server.dao.UserDao;
import server.entity.Msg;
import server.entity.Users;

import com.MyTools;
import com.MyTools.Flag;
import com.socket.TCP;
import com.socket.TCPServer;

/**
 * @author LXA 服务端
 */
public class QQServer extends TCPServer
{ 

	UserDao userDao;
	MsgDao msgDao;
	public QQServer(int serverPort)
	{
		super(serverPort);
		userDao=new UserDao();
		msgDao=new MsgDao();
	}
	/**
	 * 主函数
	 * @param args
	 */
//	public static void main(String[] args)
//	{
//		new QQServer(MyTools.QQServerPort);
//	}

	/**
	 * 处理客户端发来的各种信息
	 * @param flag 信息标志
	 * @param message 消息内容
	 * @param tcp TCP连接
	 */
	@Override
	public void dealWithMessage(Flag flag, String message,TCP tcp)
	{
		switch(flag)
		{
			case LOGIN:doLogin(message,tcp);break;//如果是登录
			case GET_FRIEND_INFO:doGetFriendInfo(message,tcp);break;//处理用户发来的请求好友资料的事件
			case REGISTER:doRegister(message,tcp);break;
			case QUN_CHAT:doQunChat(message,tcp);break;
			case UNDERLINE_MESSAGE:doUnderlineMessage(message,tcp);break;//处理用户发来的离线消息
			default:break;
		}
	}
	
	/**
	 * 处理客户端退出的相关事件
	 * @param tcp TCP连接
	 */
	@Override
	public void dealWithExit(TCP tcp)
	{
		refreshAllUserFriendList();
		userDao.updateUserState(tcp.getClientName(), -1+"");//将用户的状态更新到数据库中去
		userDao.setLastExit(tcp.getClientName());
		System.out.println("用户"+tcp.getClientName()+"已退出！");
		showOnlineNumber();
	}
	
	/* 
	 * 服务端启动后要做的事情
	 */
	@Override
	public void afterServerStart()
	{
		System.out.println("QQ服务器已启动！");
		showOnlineNumber();
	}

	/**
	 * 处理客户端登录
	 * @param message
	 */
	public void doLogin(String message,TCP tcp)
	{
		System.out.println("客户端"+tcp.getClientIP()+"尝试登录……");
		String[] temp=message.split(MyTools.SPLIT1);
		String name=temp[0];//用户名
		String password=temp[1];//用户密码
		int port=Integer.parseInt(temp[2]);//用户端口号
		int userState=Integer.parseInt(temp[3]);//用户状态
		System.out.println("nihao");
		if(checkNameAndPwd(name, password))//如果用户名和密码都正确
		{
			if(checkIsLoginAgain(name))//如果重复登录
				tcp.sendMessage(Flag.LOGIN+MyTools.FLAGEND+Flag.FAILED+MyTools.SPLIT1+"您不能重复登录！");
			else 
			{
				
				tcp.setClientName(name);//设置登录用户的名字到TCP中保存
				tcp.setClientServerPort(port);//保存当前登录用户的端口
				tcp.setUserState(userState);//保存当前登录用户的状态
				userDao.updateUserState(name, userState+"");//将用户的状态保存到数据库中去
				userDao.setLastLogin(name);
				userDao.setIP(tcp.getClientIP(), name);//将用户的IP存往数据库
				tcp.sendMessage(Flag.LOGIN+MyTools.FLAGEND+Flag.SUCCESS+MyTools.SPLIT1+getCurrentUserInfo(tcp));//发送一个消息给用户提示登录成功
				try
				{
					Thread.sleep(50);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				refreshAllUserFriendList();//刷新所有用户的好友列表，但不包括当前登录用户
				try
				{
					Thread.sleep(50);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				sendUnreadMessage(tcp);//发送用户未读消息
				showOnlineNumber();//显示当前在线人数
			}
		}
		else//如果登录失败
		{
			tcp.sendMessage(Flag.LOGIN+MyTools.FLAGEND+Flag.FAILED+MyTools.SPLIT1+"用户名或密码错误！");
		}
	}
	
	/**
	 * 处理用户发来的请求好友资料的事件
	 * @param message
	 * @param tcp
	 */
	public void doGetFriendInfo(String message,TCP tcp)
	{
		Users users = userDao.queryByName(tcp.getClientName());
		StringBuffer sb=new StringBuffer();
		sb.append("用户ID："+users.getId()+"\n");
		sb.append("用户名："+users.getName()+"\n");
		sb.append("性别："+users.getGender()+"\n");
		sb.append("电子邮件："+users.getEmail()+"\n");
		sb.append("最后一次登录："+users.getLastLogin()+"\n");
		sb.append("最后一次退出："+users.getLastExit()+"\n");
		sb.append("个性签名："+users.getSignature()+"\n");
		sb.append("生日："+users.getBirthday()+"\n");
		String userInfo=new String(sb);
		tcp.sendMessage(Flag.GET_FRIEND_INFO+MyTools.FLAGEND+userInfo);
	}
	
	/**
	 * 处理用户发来的注册请求
	 * @param message
	 * @param tcp
	 */
	public void doRegister(String message,TCP tcp)
	{
		String[] temp=message.split(MyTools.SPLIT1);
		String name=temp[0];//姓名
		String password=temp[1];//密码
		String sex=temp[2];//性别
		String email=temp[3];//电子邮件
		Date birthday;//生日
		String repost="恭喜你，注册成功！";//给用户的回复
		try
		{
			System.out.println("生日："+temp[4]);
			birthday=Date.valueOf(temp[4]);
			System.out.println(birthday);
		}
		catch (Exception e)
		{
			birthday=Date.valueOf("1992-07-28");
			repost+="\n但是您的生日日期格式不正确，系统已给您设置成默认的生日：1992-07-28!";
		}
		String signature=temp[5];//个性签名
		String headImageIdx=temp[6];//头像索引
		Users users=new Users(name, password, sex, email, signature, headImageIdx, birthday);
		if(userDao.checkUserIsExit(name))
			tcp.sendMessage(Flag.REGISTER+MyTools.FLAGEND+Flag.FAILED+MyTools.SPLIT1+"用户名已存在！");
		else 
		{
			try
    		{
    			userDao.add(users);
    			tcp.sendMessage(Flag.REGISTER+MyTools.FLAGEND+Flag.SUCCESS+MyTools.SPLIT1+repost);
    		}
    		catch (SQLException e)
    		{
    			e.printStackTrace();
    			tcp.sendMessage(Flag.REGISTER+MyTools.FLAGEND+Flag.FAILED+MyTools.SPLIT1+"由于某些原因，注册失败！");
    		}
		}
	}
	
	/**
	 * 检查登录用户的用户名和密码是否正确
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean checkNameAndPwd(String name,String password)
	{
		boolean success=false;
		if(userDao.checkNameAndPwd(name, password))
			success=true;
		return success;
	}
	/**
	 * 检查用户是否重复登录
	 * @param name 用户名
	 * @return 返回的真假值
	 */
	public boolean checkIsLoginAgain(String name)
	{
		boolean res=false;
		for(TCP tcp:tcpSockets)
			if(tcp.getClientName().equals(name))
			{
				res=true;
				break;
			}
		return res;
	}
	/**
	 * 初始化客户端信息
	 * @param tcp
	 */
	public void initClientInfo(TCP tcp)
	{
		tcp.sendMessage(Flag.USERINFO+MyTools.FLAGEND+getCurrentUserInfo(tcp));
	}

	/**
	 * 刷新所有在线用户的好友列表
	 */
	public void refreshAllUserFriendList()
	{
		for(TCP tcp:tcpSockets)
			tcp.sendMessage(Flag.FRIENDS_LIST+MyTools.FLAGEND+getAllUsersInfo());
	}
	
	/**
	 * 获取当前用户的信息
	 * @param t
	 * @return
	 */
	public String getCurrentUserInfo(TCP tcp)
	{
		//示例：用户名;状态;个性签名;头像
		StringBuffer sb=new StringBuffer();
		String userName=tcp.getClientName();
		Users users=userDao.queryByName(userName);//从数据库中查询用户信息
		sb.append(userName+MyTools.SPLIT2//用户名
				+tcp.getUserState()+MyTools.SPLIT2//状态
				+users.getSignature()+MyTools.SPLIT2//签名
				+users.getHeadImg());//头像
		String userInfo=new String(sb);//返回当前登录用户自己的信息
		return userInfo;
	}
	/**
	 * 将所有用户的信息转换成字符串
	 * @return
	 */
	public String getAllUsersInfo()
	{
		//示例：刘显安,192.168.1.1,8888;吴志强,192.168.1.2,6666
		StringBuffer sb=new StringBuffer();
		sb.append("所有在线用户"+MyTools.SPLIT2+"所有不在线用户"+MyTools.SPLIT2+"我的好友"+MyTools.SPLIT1);
		for(TCP tcp:tcpSockets)
		{
			if(tcp.getUserState()!=4)//因为4表示隐身
			{
				System.out.println("客户端名"+tcp.getClientName());
    			sb.append(tcp.getClientName()+MyTools.SPLIT3//用户名
    					+tcp.getClientIP()+MyTools.SPLIT3//IP
    					+tcp.getClientServerPort()+MyTools.SPLIT3//端口号
    					+userDao.queryByName(tcp.getClientName()).getHeadImg()+MyTools.SPLIT3//头像
    					+tcp.getUserState()+MyTools.SPLIT2);//用户状态
			}
		}
		String onlineUser=new String(sb);
		onlineUser=onlineUser.substring(0,onlineUser.length()-1);//去掉最后一个逗号
		onlineUser+=MyTools.SPLIT1;//加上一个分号
		
		
		sb=new StringBuffer();
		ArrayList<Users> userList=userDao.queryAll();
		for(Users users:userList)
		{
			if(users.getState().equals("-1")||users.getState().equals("4")||users.getState()==null)
			{
				sb.append(users.getName()+MyTools.SPLIT3
						+"下线或隐身"+MyTools.SPLIT3
						+"0"+MyTools.SPLIT3
						+users.getHeadImg()+MyTools.SPLIT3
						+"-1"+MyTools.SPLIT2);
			}
		}
		String underlineUser=new String(sb);
		if(!underlineUser.equals(""))
			underlineUser=underlineUser.substring(0,underlineUser.length()-1);//去掉最后一个逗号
		underlineUser+=MyTools.SPLIT1;//加上一个分号
		
		String myfriend="无";
		
		return onlineUser+underlineUser+myfriend;
	}
	
	/**
	 * 显示当前在线人数
	 */
	public void showOnlineNumber()
	{
		System.out.println("当前总在线人数："+tcpSockets.size());
	}
	
	public void doQunChat(String message,TCP tcp)
	{
		for(TCP t:tcpSockets)
			t.sendMessage(Flag.QUN_CHAT+MyTools.FLAGEND+message);
	}
	
	/**
	 * 处理用户发来的离线消息
	 * @param message
	 * @param tcp
	 */
	public void doUnderlineMessage(String message,TCP tcp)
	{
		int sendFrom=userDao.queryByName(tcp.getClientName()).getId();
		int sendTo=userDao.queryByName(message.split(MyTools.SPLIT1)[0]).getId();
		Msg msg=new Msg(message.split(MyTools.SPLIT1)[1],sendFrom,sendTo,"", "");
		try
		{
			msgDao.insertMsg(msg);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 给用户发送未读消息
	 */
	public void sendUnreadMessage(TCP tcp)
	{
		String userName=tcp.getClientName();
		int userID=userDao.queryByName(userName).getId();
		ArrayList<Msg> msgs=(ArrayList<Msg>) msgDao.selectMsgBySendTo(userID);
		StringBuffer sb=new StringBuffer();
		sb.append(Flag.UNDERLINE_MESSAGE+MyTools.FLAGEND);
		if(msgs.size()>0)
		{
			for(Msg msg:msgs)
			{
				sb.append(userDao.queryById(msg.getSendFrom()).getName()+"("
						+msg.getSendTime()+"):\n"
						+msg.getMsgContent()+"\n");
			}
			String unreadMessage=new String(sb);
			tcp.sendMessage(unreadMessage);
			System.out.println("给用户发送未读离线消息成功！");
			msgDao.deleteMsgBySendTO(userID);
			System.out.println("删除用户未读消息成功！");
		}
	}
	
	
	/**
	 * 发送聊天室公告
	 * @param message
	 */
	public void sendPublicMessage(String message)
	{
		for(TCP t:tcpSockets)
			t.sendMessage(Flag.PUBLIC_MESSAGE+MyTools.FLAGEND+message);
		JOptionPane.showMessageDialog(null, "公告发布成功！","恭喜",JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * 发送弹窗公告
	 * @param message
	 */
	public void sendShowWindow(String message)
	{
		for(TCP t:tcpSockets)
			t.sendMessage(Flag.SHOW_WINDOW+MyTools.FLAGEND+message);
		JOptionPane.showMessageDialog(null, "弹窗公告发布成功！","恭喜",JOptionPane.INFORMATION_MESSAGE);
	}
	
}
