package server.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import server.common.JDBC;
import server.entity.Users;

public class UserDao implements IUser {
	JDBC con = new JDBC();

	@Override
	public boolean add(Users user) throws SQLException 
	{
		Date birth = user.getBirthday();
		String sql="insert into users(u_name,u_pwd,u_gender,u_email,u_remarke,u_signature,u_head_Img,u_type,u_birthday) values (?,?,?,?,?,?,?,?,?)";
		String[] params={user.getName(),user.getPwd(),user.getGender(),user.getEmail(),user.getRemarke(),user.getSignature(),user.getHeadImg(),user.getType(),user.getBirthday().toString()};
		return con.update(sql,params)>0;
	}

	@Override
	public boolean delete(int usersid) throws SQLException
	{
		String sql="delete from users where U_ID =?";
		if(con.update(sql, new String[]{String.valueOf(usersid)})>0)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public boolean update(Users user)
	{
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(user.getBirthday().getTime());
		try
		{
			result = con.update("update users set u_name = ?, u_pwd = ?, u_gender = ?, " +
					"u_email = ?," +
					" u_remarke = ?, u_signature = ?, u_head_img = ?, u_type = ?," +
					" u_birthday = to_date('"+user.getBirthday()+"','yyyy-mm-dd') where u_id = ?", new String[]{user.getName(),
					user.getPwd(),user.getGender(),user.getEmail()
					,user.getRemarke(),
					user.getSignature(),user.getHeadImg(),user.getType(),
					String.valueOf(user.getId())});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result>0;
	}

	@Override
	public ArrayList<Users> queryAll()
	{
		Users user = null;
		ArrayList<Users> list = new ArrayList<Users>();
		ResultSet rs = con.query("select * from users order by u_id", null);
		try 
		{
			// 开始遍历获取数据
			while (rs.next())
			{
				user = new Users();// 如果找到数据，创建用户对象
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setPwd(rs.getString("u_pwd"));
				user.setGender(rs.getString("u_gender"));
				user.setEmail(rs.getString("u_email"));
				user.setLastLogin(rs.getString("u_last_login"));
				user.setLastExit(rs.getString("u_last_exit"));
				user.setRemarke(rs.getString("u_remarke"));
				user.setSignature(rs.getString("u_signature"));
				user.setHeadImg(rs.getString("u_head_Img"));
				user.setBirthday(rs.getDate("u_birthday"));
				user.setType(rs.getString("u_type"));
				user.setState(rs.getString("u_state"));
				user.setIp(rs.getString("u_ip"));
				list.add(user);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			//con.closeConnection();// 关闭所有数据库对象
		}
		return list;
	}


	public Users queryById(int usersid)
	{
		Users user = null;
		ResultSet rs = con.query("select * from users where u_id=? ",
				new String[] { String.valueOf(usersid) });
		try {
			// 开始遍历获取数据
			while (rs.next()) {
				user = new Users();// 如果找到数据，创建用户对象
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setPwd(rs.getString("u_pwd"));
				user.setGender(rs.getString("u_gender"));
				user.setEmail(rs.getString("u_email"));
				user.setLastLogin(rs.getString("u_last_login"));
				user.setLastExit(rs.getString("u_last_exit"));
				user.setRemarke(rs.getString("u_remarke"));
				user.setSignature(rs.getString("u_signature"));
				user.setHeadImg(rs.getString("u_head_Img"));
				user.setBirthday(rs.getDate("u_birthday"));
				user.setType(rs.getString("u_type"));
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			//con.closeConnection();// 关闭所有数据库对象
		}
		return user;
	}
	public Users queryByName(String userName)
	{
		Users user = null;
		ResultSet rs = con.query("select * from users where u_name=?",
				new String[]{userName});
		try 
		{
			// 开始遍历获取数据
			while (rs.next()) {
				user = new Users();// 如果找到数据，创建用户对象
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setPwd(rs.getString("u_pwd"));
				user.setGender(rs.getString("u_gender"));
				user.setEmail(rs.getString("u_email"));
				user.setLastLogin(rs.getString("u_last_login"));
				user.setLastExit(rs.getString("u_last_exit"));
				user.setRemarke(rs.getString("u_remarke"));
				user.setSignature(rs.getString("u_signature"));
				user.setHeadImg(rs.getString("u_head_Img"));
				user.setBirthday(rs.getDate("u_birthday"));
				user.setType(rs.getString("u_type"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//con.closeConnection();// 关闭所有数据库对象
		}
		return user;
	}

	/* 
	 * 检测用户是否已存在
	 */
	@Override
	public boolean checkUserIsExit(String name)
	{
		try
		{
			ResultSet rs = con.query("select * from users where u_name=? ",
					new String[] { name});
			if(rs.next())
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean checkNameAndPwd(String name, String password)
	{
		try
		{
			ResultSet rs = con.query("select * from users where u_name=? and u_pwd=? ",
					new String[] { name,password});
			if(rs.next())
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/* 
	 * 更新用户状态
	 */
	@Override
	public boolean updateUserState(String name, String state)
	{
		int result = 0;
		try
		{
			result = con.update("update users set u_state = ? where u_name = ?",new String[]{state,name});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result>0;
	}
	
	public void setLastLogin(String name)
	{
		String sql="update users set u_last_login=CURRENT_TIMESTAMP where u_name=?";
		try {
			con.update(sql, new String[]{name});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setLastExit(String name)
	{
		String sql="update users set u_last_exit=CURRENT_TIMESTAMP where u_name=?";
		try {
			con.update(sql, new String[]{name});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setUserState(String name,int i)
	{
		String sql="update users set u_state=? where u_name=?";
		try 
		{
			con.update(sql, new String[]{String.valueOf(i),name});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void setIP(String IP,String name)
	{
		String sql="update users set u_ip=? where u_name=?";
		try 
		{
			con.update(sql, new String[]{IP,name});
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
