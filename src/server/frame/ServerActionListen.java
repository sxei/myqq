package server.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.MyTools;

import server.QQServer;

public class ServerActionListen implements ActionListener{

	QQServer qqServer=null;
	ServerMana serverMana=null;
	public ServerActionListen(ServerMana serverMana)
	{
		this.serverMana=serverMana;
	}
	public void actionPerformed(ActionEvent e) 
	{
		String cmd=e.getActionCommand();
		if(cmd.equals("启动MyQQ服务器"))
		{
			
			qqServer=new QQServer(MyTools.QQServerPort);
			serverMana.allTime=0;
			serverMana.startTime=System.currentTimeMillis();
			serverMana.isRun=true;
			serverMana.lblState.setText("服务器正在运行中...");
			this.serverMana.btnStart.setActionCommand("停止MyQQ服务器");
			
		}
		else if(cmd.equals("停止MyQQ服务器"))
		{
			if(qqServer!=null)
			{
				qqServer.closeServer();
				serverMana.isRun=false;
				serverMana.lblState.setText("服务器已关闭。");
				JOptionPane.showMessageDialog(null,"本次服务器总共运行"+serverMana.allTime+"秒");
				this.serverMana.btnStart.setActionCommand("启动MyQQ服务器");
			}
		}
	}
	
}
