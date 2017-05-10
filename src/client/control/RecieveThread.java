package client.control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import client.frame.SendFileFrame;


public class RecieveThread extends Thread
{
	private SendFileFrame fram;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream fileOut;
	private ServerSocket ss;
	private String serverPort;

	public RecieveThread(SendFileFrame fram, Socket socket)
	{
		this.fram = fram;
		this.socket = socket;
		
	}

	@Override
	public void run()
	{
		super.run();
		recieveFile();
	}

	public void recieveFile()
	{
		try
		{
			// ss = new ServerSocket(Integer.parseInt(serverPort));
			// socket = ss.accept();
			// TODO 测试连接是否成功
//			JOptionPane.showMessageDialog(null, "接收端已成功连接！");

			// socket = new Socket(ConnInfor.SERVER_IP, ConnInfor.CONN_PORT);
			in = new DataInputStream(socket.getInputStream());
			String filePath = null;
			// 读取发过来的文件名和文件长度
			String fileName = in.readUTF();
			long fileLen = in.readLong();
			// 弹出接收对话
			int result = JOptionPane.showConfirmDialog(null, "是否接收文件？" + fileName,
					"提示", JOptionPane.YES_NO_CANCEL_OPTION);
			// 选择对话框中的取消按钮的相应操作
			if (result == JOptionPane.CANCEL_OPTION)
			{

			}
			// 选择对话框中的否按钮的相应操
			else if (result == JOptionPane.NO_OPTION)
			{
				closeRecieve();
			}
			// 选择对话框中的是按钮的相应操
			else if (result == JOptionPane.OK_OPTION)
			{
				
				filePath = fram.showDialog(JFileChooser.DIRECTORIES_ONLY);
				fram.setVisible(true);
				fileOut = new DataOutputStream(new FileOutputStream(filePath
						+ "/"+fileName));

				byte[] buff = new byte[1024];

				int len = 0;

				long passLen = 0;

				long startTime = System.currentTimeMillis();
				long endTime = 0;
				long passTime = 0;

				while (true)
				{
					if (in != null)
					{
						len = in.read(buff);
						passLen += len;
					}
					endTime = System.currentTimeMillis();
					passTime = endTime - startTime;
					if (len == -1)
					{
						fram.updateProgressBar(fram.progressBar, fileLen,
								fileLen, fram.lblProgress, passTime);
						break;
					}
					fram.updateProgressBar(fram.progressBar, passLen,
							fileLen, fram.lblProgress, passTime);
					fileOut.write(buff, 0, len);
				}

			}

		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			closeRecieve();
		}
	}

	/**
	 * 关闭接收连接
	 * 
	 */
	public void closeRecieve()
	{

		try
		{
			if (fileOut != null)
				fileOut.close();
			if (in != null)
			{
				in.close();
			}
			if (socket != null)
			{
				socket.close();
			}
			if (ss != null)
			{
				ss.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}