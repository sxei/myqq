package server.entity;

import java.sql.Date;

public class Msg
{
	private int msgId;
	private String msgContent;
	private int sendFrom;//信息发送者
	private int sendTo;//信息接收者
	private String sendTime;//信息发送时间
	private String remark;
	private String msgTye;//信息类型：离线消息和群公告
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "消息的全部信息是：\n"+"msgId:"+msgId+"  msgContent:"+msgContent+
				"  sendFrom:"+sendFrom+"  sendTo:"+sendTo+" sendTime :"+sendTime+
				"   remark:"+remark+"  msgTye:"+msgTye;
	}
	
	
	/**
	 * @param msgContent
	 * @param sendFrom
	 * @param sendTo
	 * @param remark
	 * @param msgTye
	 * 
	 * 除msgId 和 sendTime 参数之外  的构造参数
	 */
	public Msg(String msgContent, int sendFrom, int sendTo, String remark,
			String msgTye)
	{
		super();
		this.msgContent = msgContent;
		this.sendFrom = sendFrom;
		this.sendTo = sendTo;
		this.remark = remark;
		this.msgTye = msgTye;
	}

	
	
	

	/**
	 * @param msgId
	 * @param msgContent
	 * @param sendFrom
	 * @param sendTo
	 * @param remark
	 * @param msgTye
	 * 
	 *  含有除  sendTime参数的  构造函数
	 */
	public Msg(int msgId, String msgContent, int sendFrom, int sendTo,
			String remark, String msgTye)
	{
		super();
		this.msgId = msgId;
		this.msgContent = msgContent;
		this.sendFrom = sendFrom;
		this.sendTo = sendTo;
		this.remark = remark;
		this.msgTye = msgTye;
	}


	
	/**
	 * @param msgId
	 * @param msgContent
	 * @param sendFrom
	 * @param sendTo
	 * @param sendTime
	 * @param remark
	 * @param msgTye
	 * 含有全部构造参数
	 */
	public Msg(int msgId, String msgContent, int sendFrom, int sendTo,
			String sendTime, String remark, String msgTye)
	{
		super();
		this.msgId = msgId;
		this.msgContent = msgContent;
		this.sendFrom = sendFrom;
		this.sendTo = sendTo;
		this.sendTime = sendTime;
		this.remark = remark;
		this.msgTye = msgTye;
	}
	
	/**
	 * @param msgContent
	 * @param sendFrom
	 * @param sendTo
	 * @param sendTime
	 * @param remark
	 * @param msgTye
	 *   含有除msgId之外所有参数的构造方法
	 */
	public Msg(String msgContent, int sendFrom, int sendTo, String sendTime,
			String remark, String msgTye)
	{
		super();
		this.msgContent = msgContent;
		this.sendFrom = sendFrom;
		this.sendTo = sendTo;
		this.sendTime = sendTime;
		this.remark = remark;
		this.msgTye = msgTye;
	}
	public Msg()
	{
		// TODO Auto-generated constructor stub
	}

	public int getMsgId()
	{
		return msgId;
	}
	public void setMsgId(int msgId)
	{
		this.msgId = msgId;
	}
	public String getMsgContent()
	{
		return msgContent;
	}
	public void setMsgContent(String msgContent)
	{
		this.msgContent = msgContent;
	}
	public int getSendFrom()
	{
		return sendFrom;
	}
	public void setSendFrom(int sendFrom)
	{
		this.sendFrom = sendFrom;
	}
	public int getSendTo()
	{
		return sendTo;
	}
	public void setSendTo(int sendTo)
	{
		this.sendTo = sendTo;
	}
	public String getSendTime()
	{
		return sendTime;
	}
	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getMsgTye()
	{
		return msgTye;
	}
	public void setMsgTye(String msgTye)
	{
		this.msgTye = msgTye;
	}
	
}
