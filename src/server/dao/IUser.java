package server.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.entity.Users;

/**
 * @author Administrator
 *用户类数据库操作接口
 */
public interface IUser {
	/**
	 * @param userid
	 * @return根据用户编号查询单条用户数据
	 */
	public Users queryById(int usersid) ;
	/**
	 * @return查询所有用户信息
	 */
	public List<Users> queryAll() ;
	
	/**
	 * @param user
	 * @return返回是否添加成功
	 */
	public boolean add(Users user)throws SQLException;
	/**
	 * @param user
	 * @return返回是否修改成功
	 */
	public boolean update(Users user)throws SQLException;
	/**
	 * @param userid
	 * @return返回是否删除成功
	 */
	public boolean delete(int usersid)throws SQLException;
	/**
	 * 检测用户名是否已存在
	 * @param name
	 * @return
	 */
	public boolean checkUserIsExit(String name);
	/**
	 * 检测用户名或密码是否正确
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean checkNameAndPwd(String name,String password);
	/**
	 * 更新用户状态
	 * @param name
	 * @param state
	 * @return
	 */
	public boolean updateUserState(String name,String state);
	/**
	 * @param userid
	 * @return根据用户编号查询单条用户数据
	 */
	public Users queryByName(String name);
}
