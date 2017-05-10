package server.common;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.MyTools;

public class JDBC
{
	public Connection con = null;
	public PreparedStatement preSta = null;
	public ResultSet result = null;
	
	/**
	 * 无参的构造方法
	 */
	public JDBC()
	{
		getConnection();
	}
	
	/**
	 * 有参的构造方法
	 * @param url
	 * @param user 用户名
	 * @param password 
	 */
	public JDBC(String url,String user,String password)
	{
		getConnection(url,user,password);
	}
	
	/**
	 * 获取连接
	 * @param url 连接数据库的URL
	 * @param user 用户名
	 * @param password 密码
	 * @return
	 */
	private Connection getConnection(String url,String user,String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("开始尝试连接数据库！");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("连接成功！");
		}
		catch (Exception e)
		{
			System.out.println("执行数据库操作报错！");
			 e.printStackTrace();
		}
		return con;
	}
	/**
	 * 使用默认的参数获取数据库的连接
	 * @return
	 */
	private Connection getConnection()
	{
		return getConnection(MyTools.JDBC_URL, MyTools.JDBC_USER, MyTools.JDBC_PWD);
	}
	/**
	 * 关闭数据库的连接
	 */
	public void closeConnection()
	{
		try
		{
			if(result!=null)
				result.close();
			if(preSta!=null)
				preSta.close();
			if (con != null)
				con.close();
			System.out.println("数据库连接已关闭！");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 执行数据库的更新操作
	 * @param sql 需要执行的预编译语句
	 * @param params 预编译语句的参数列表
	 * @return 受影响的行数
	 * @throws SQLException 
	 */
	public int update(String sql,String[] params) throws SQLException
	{
		int count=0;
		preSta=con.prepareStatement(sql);
		if(params!=null)
		{
			for(int i=0;i<params.length;i++)
				preSta.setString(i+1, params[i]);
		}
		count=preSta.executeUpdate();
		return count;
	}
	/**
	 * 执行数据库的查询操作
	 * @param sql 需要执行的预编译语句
	 * @param params 预编译语句的参数列表
	 * @return 返回查询的结果集，类型为ResultSet
	 * @throws SQLException 
	 */
	public ResultSet query(String sql,String[] params)
	{
		try
		{
			preSta=con.prepareStatement(sql);
			if(params!=null)
			{
				for(int i=0;i<params.length;i++)
					preSta.setString(i+1, params[i]);
			}
			result=preSta.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 写入配置文件
	 * @param url jdbc连接域名
	 * @param user 用户名
	 * @param password 密码
	 */
	public static void writeProperties(String url,String user,String password)
	{
		Properties pro=new Properties();
		FileOutputStream fileOut=null;
		try
		{
			fileOut=new FileOutputStream("Config.ini");
			pro.put("url", url);
			pro.put("user", user);
			pro.put("password", password);
			pro.store(fileOut, "My Config");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				if(fileOut!=null)
					fileOut.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * 读取配置文件
	 * @return 返回list
	 */
	public static List readProperties()
	{
		List list=new List();
		Properties pro=new Properties();
		FileInputStream fileIn=null;
		try
		{
			fileIn=new FileInputStream("Config.ini");
			pro.load(fileIn);
			list.add(pro.getProperty("url"));
			list.add(pro.getProperty("user"));
			list.add(pro.getProperty("password"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				if(fileIn!=null)
					fileIn.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
}
