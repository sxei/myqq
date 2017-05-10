package server.dao;

import java.sql.SQLException;
import java.util.List;

import server.entity.Msg;

public interface IMsg
{
	public boolean insertMsg(Msg msg)throws SQLException;
	public boolean deleteMsg(int msgId)throws SQLException;
	public int updateMsg(Msg msg)throws SQLException;
	public Msg selectAMsg(int msgId);
	public List<Msg> selectMsgs(String sql);
}
