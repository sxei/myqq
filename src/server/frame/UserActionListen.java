package server.frame;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import server.common.PageService;
import server.dao.UserDao;
import server.entity.Users;

public class UserActionListen implements ActionListener
{
	static PageService pageService;
	UserDao userDao;
	UserMana userMana;

	public UserActionListen(UserMana userMana)
	{
		userDao = new UserDao();
		List<Users> list = userDao.queryAll();
		pageService = new PageService(list);
		this.userMana = userMana;
	}

	public void actionPerformed(ActionEvent e)
	{
		String strBtn = e.getActionCommand();
		if("删除".equals(strBtn))
		{
			int row=userMana.table.getSelectedRow();
			if(row!=-1)
			{
				int userId=Integer.parseInt(userMana.table.getValueAt(row, 0).toString());
				try {
					if(userDao.delete(userId))
					{
						JOptionPane.showMessageDialog(null, "删除成功！");
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "删除失败！");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//userMana.refresh();
				pageService = new PageService(userMana.refresh());
			}
		}
		else if("刷新".equals(strBtn))
		{
			//userMana.refresh();
			pageService = new PageService(userMana.refresh());
		}
		else if("查找一".equals(strBtn))
		{
			userMana.model.removeTableModelListener(userMana.userTableCahnged);
			Users user=null;
			int rowCount = userMana.model.getRowCount();
			UserDao userDao=new UserDao();
			if(userMana.txtId.equals(null)||userMana.txtId.equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入用户ID");
			}
			else 
			{
				int userId=Integer.parseInt(userMana.txtId.getText());
				user=userDao.queryById(userId);
				if(user==null)
				{
					JOptionPane.showMessageDialog(null, "用户不存在！");
					return;
				}
				for (int i = 0; i <rowCount; i++)
				{
					userMana.model.removeRow(0);
				}
				Object[] rowData =
					{ user.getId(), user.getName(), user.getPwd(),user.getIp(),user.getState(),user.getGender(), user.getEmail(),
							user.getLastLogin(),user.getLastExit(), user.getBirthday()};
				userMana.model.addRow(rowData);
			}
			userMana.model.addTableModelListener(userMana.userTableCahnged);
		}
		else if("查找二".equals(strBtn))
		{
			userMana.model.removeTableModelListener(userMana.userTableCahnged);
			Users user=null;
			int rowCount = userMana.model.getRowCount();
			UserDao userDao=new UserDao();
			if(userMana.txtName.equals(null)||userMana.txtName.equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入用户名");
			}
			else 
			{
				String userName=userMana.txtName.getText();
				try {
					user=userDao.queryByName(userName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(user==null)
				{
					JOptionPane.showMessageDialog(null, "用户不存在！");
					return;
				}
				for (int i = 0; i <rowCount; i++)
				{
					userMana.model.removeRow(0);
				}
				Object[] rowData =
					{ user.getId(), user.getName(), user.getPwd(),user.getIp(),user.getState(),user.getGender(), user.getEmail(),
							user.getLastLogin(),user.getLastExit(), user.getBirthday()};
				userMana.model.addRow(rowData);
			}
			userMana.model.addTableModelListener(userMana.userTableCahnged);
		}
		else
		{
			userMana.model.removeTableModelListener(userMana.userTableCahnged);
			List<Users> listPerPage = null;
			if ("首页".equals(strBtn))
			{
				listPerPage = pageService.gotoFirst();
			}
			else if ("下一页".equals(strBtn))
			{
				listPerPage = pageService.gotoNext();
			}
			else if ("上一页".equals(strBtn))
			{
				listPerPage = pageService.gotoPre();
			}
			else if ("尾页".equals(strBtn))
			{
				listPerPage = pageService.gotoLast();
			}
	
			int rowCount = userMana.model.getRowCount();// 获取表格中共有几行
	
			for (int i = 0; i < rowCount; i++)
			{
				userMana.model.removeRow(0);
				
			}
			userMana.lblCurrent.setText("当前在第" + (pageService.getCurrentPage()+1) + "页");
			for (int i = 0; i < listPerPage.size(); i++)
			{
				Users user = (Users) listPerPage.get(i);
				Object[] rowData =
				{ user.getId(), user.getName(), user.getPwd(),user.getIp(),user.getState(),user.getGender(), user.getEmail(),
						user.getLastLogin(),user.getLastExit(), user.getBirthday()};
				userMana.model.addRow(rowData);
			}
			userMana.model.addTableModelListener(userMana.userTableCahnged);
		}
	}

}
