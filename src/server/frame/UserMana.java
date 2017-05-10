package server.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import server.common.PageService;
import server.dao.UserDao;

import server.entity.Users;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserMana extends JPanel {
	JScrollPane scrollpane = new JScrollPane();
	Vector<String> columnNames = new Vector<String>();
	public DefaultTableModel model;
	public JTable table;
	public JLabel lblNum;
	public JLabel lblCurrent;
	
	public UserTableCahnged userTableCahnged;

	JButton btnFirst = new JButton("首页");
	JButton btnNext = new JButton("下一页");
	JButton btnPre = new JButton("上一页");
	JButton btnLast = new JButton("尾页");
	JButton btnDelete = new JButton("\u5220\u9664");
	private final JButton btnUpdate = new JButton("\u5237\u65B0");
	public JTextField txtId;
	public JTextField txtName;
	public UserMana()
	{
		columnNames.add("用户ID");
		columnNames.add("用户名");
		columnNames.add("密码");
		columnNames.add("IP");
		columnNames.add("状态");
		columnNames.add("性别");
		columnNames.add("Email");
		columnNames.add("最近上线时间");
		columnNames.add("最后下线时间");
		columnNames.add("生日");
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model)
		{
			boolean[] canEdit = new boolean[] { false, true, true, false, true,
					true, true, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}

		};
		table.getAutoscrolls();
		scrollpane.setViewportView(table);
		
		UserActionListen userActionListen=new UserActionListen(this);
		userTableCahnged=new UserTableCahnged(table);
		
		model.addTableModelListener(userTableCahnged);
		btnFirst.addActionListener(userActionListen);
		btnPre.addActionListener(userActionListen);
		btnNext.addActionListener(userActionListen);
		btnLast.addActionListener(userActionListen);
		lblNum=new JLabel();
		lblCurrent=new JLabel();
		btnDelete.addActionListener(userActionListen);
		btnUpdate.addActionListener(userActionListen);
		
		JLabel lblid = new JLabel("\u6309\u7528\u6237ID\u67E5\u627E\uFF1A");
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		JLabel label = new JLabel("\u6309\u7528\u6237\u540D\u67E5\u627E\uFF1A");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JButton btnSearch1 = new JButton("\u67E5\u627E\u4E00");	
		JButton btnSeach2 = new JButton("\u67E5\u627E\u4E8C");
		btnSearch1.addActionListener(userActionListen);
		btnSeach2.addActionListener(userActionListen);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblid)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(200))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(240, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(lblCurrent, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNum, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(291))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(btnFirst)
					.addGap(5)
					.addComponent(btnPre)
					.addGap(5)
					.addComponent(btnNext)
					.addGap(5)
					.addComponent(btnLast)
					.addGap(85)
					.addComponent(btnDelete)
					.addGap(18)
					.addComponent(btnUpdate)
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(165)
					.addComponent(btnSearch1)
					.addPreferredGap(ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
					.addComponent(btnSeach2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(154))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch1)
						.addComponent(btnSeach2))
					.addGap(16)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCurrent)
						.addComponent(lblNum))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFirst)
						.addComponent(btnPre)
						.addComponent(btnNext)
						.addComponent(btnLast)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnUpdate)
							.addComponent(btnDelete)))
					.addGap(73))
		);
		setLayout(groupLayout);
		refresh();
	}
	public List refresh()
	{
		model.removeTableModelListener(userTableCahnged);
		int rowCount = model.getRowCount(); // 将表格清空
		for (int i = 0; i < rowCount; i++)
			model.removeRow(0);
		UserDao userDao=new UserDao();
		List<Users> list=userDao.queryAll();
		PageService pageService=new PageService(list);
		List<Users> listTemp=pageService.gotoFirst();
		lblNum.setText("共"+pageService.getTotalPage()+"页/");
		lblCurrent.setText("当前在第"+(pageService.getCurrentPage()+1)+"页");
		for (Users user : listTemp)
		{
			Object[] rowData =
			{ user.getId(), user.getName(), user.getPwd(),user.getIp(),user.getState(),user.getGender(), user.getEmail(),
					user.getLastLogin(),user.getLastExit(), user.getBirthday()};
			model.addRow(rowData);
		}	
		model.addTableModelListener(userTableCahnged);
		return list;
	}
}
