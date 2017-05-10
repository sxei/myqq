package server.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.text.DateFormat;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import server.common.JDBC;
import server.entity.Msg;

public class MsgDao implements IMsg
{
	JDBC jdbc = new JDBC();

	public boolean insertMsg(Msg msg) throws SQLException
	{
		String sql  = "insert into msg(msg_content,msg_sendfrom,msg_sendto,msg_sendtime,msg_remark,msg_type) values (?,?,?,CURRENT_TIMESTAMP,?,?)";
		String[] strArr = new String[]{msg.getMsgContent(), String.valueOf(msg.getSendFrom()),
				String.valueOf(msg.getSendTo()), msg.getRemark(), msg.getMsgTye(),
						};
		if(jdbc.update(sql, strArr) > 0)
			return true;
		return false;
	}
	
	@Override
	public boolean deleteMsg(int msgId) throws SQLException
	{
		String sql = "delete from msg where msg_id = ?";
		return jdbc.update(sql, new String[]{ String.valueOf(msgId) })>0;
	}
	
	@Override
	public int updateMsg(Msg msg) throws SQLException
	{
		// 定义日期时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "update msg set MSG_CONTENT = ? , MSG_SENDFROM = ? ,MSG_SENDTO =?"
				+",MSG_REMARK = ?  ,MSG_TYPE = ? where msg_id = ?";
		String[] strArr = new String[]
		{ msg.getMsgContent(), String.valueOf(msg.getSendFrom()),

		String.valueOf(msg.getSendTo()), msg.getRemark(), msg.getMsgTye(),
				String.valueOf(msg.getMsgId()) };

		// System.out.println(new java.util.Date());
		return jdbc.update(sql, strArr);
	}
	@Override
	public Msg selectAMsg(int msgId)
	{
		ResultSet rs = null;
		String sql = "select * from MSG where MSG_ID = ?";

		rs = jdbc.query(sql, new String[]
		{ String.valueOf(msgId) });
		try
		{
			if (rs.next())
			{
				String msgContent = rs.getString("MSG_CONTENT");
				int sendFrom = rs.getInt("MSG_SENDFROM");
				int sendTo = rs.getInt("MSG_SENDTO");
				String sendTime = rs.getString("MSG_SENDTIME");
				String remark = rs.getString("MSG_REMARK");
				String msgType = rs.getString("MSG_TYPE");

				Msg msg = new Msg(msgContent, sendFrom, sendTo, sendTime,
						remark, msgType);
				return msg;
			}
			else
				return null;

		}
		catch (SQLException e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			jdbc.closeConnection();
		}
	}

	@Override
	public ArrayList<Msg> selectMsgs(String sql)
	{
		ArrayList<Msg> list = new ArrayList<Msg>();

		ResultSet rs = null;

		rs = jdbc.query(sql, null);
		try
		{
			while (rs.next())
			{
				int msg_id = rs.getInt(1);
				String msgContent = rs.getString(2);
				int sendFrom = rs.getInt(3);
				int sendTo = rs.getInt(4);
				String sendTime = rs.getString(5);
				String remark = rs.getString(6);
				String msgType = rs.getString(7);

				Msg msg = new Msg(msg_id, msgContent, sendFrom, sendTo,
						sendTime, remark, msgType);
				list.add(msg);
			}
			return list;
		}
		catch (SQLException e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			jdbc.closeConnection();
		}
	}
	/*public ArrayList<Msg> QueryMessageBySendTO(int sendTo)
	{
		return selectMsgs("select * from MSG where MSG_SENDTO = "+sendTo);
	}*/

	public boolean deleteMsgBySendTO(int sendto) 
	{
		try
		{
			String sql = "delete from msg where msg_sendto = ?";
			return jdbc.update(sql, new String[]{ sendto+""})>0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * WZQ写的
	 * @param sendto
	 * @return
	 */
	public List<Msg> selectMsgBySendTo(int sendto)
	{
		ResultSet rs = null;
		String sql = "select * from MSG where MSG_SENDTO = ?";
		List<Msg> list=new ArrayList<Msg>();
		rs = jdbc.query(sql, new String[]
		{ String.valueOf(sendto) });
		try
		{
			while (rs.next())
			{
				String msgContent = rs.getString("MSG_CONTENT");
				int sendFrom = rs.getInt("MSG_SENDFROM");
				int sendTo = rs.getInt("MSG_SENDTO");
				String sendTime = rs.getString("MSG_SENDTIME");
				String remark = rs.getString("MSG_REMARK");
				String msgType = rs.getString("MSG_TYPE");
				Msg msg = new Msg(msgContent, sendFrom, sendTo, sendTime,
						remark, msgType);
				list.add(msg);
			}
			return list;
		}
		catch (SQLException e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
		}
	}

}
