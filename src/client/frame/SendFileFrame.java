package client.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.control.RecieveThread;
import client.control.SendTread;

public class SendFileFrame extends JFrame
{

	public JPanel contentPane;
	public JLabel lbl;
	public  JProgressBar progressBar;
	public JLabel lblProgress;
	public RecieveThread recieveThread;

	/**
	 * Create the frame.
	 */
	public SendFileFrame()
	{
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl = new JLabel("进度条");
		lbl.setBounds(42, 35, 74, 33);
		contentPane.add(lbl);

		progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLUE);
		progressBar.setBounds(96, 35, 332, 33);
		contentPane.add(progressBar);

		lblProgress = new JLabel("");
		lblProgress.setBounds(64, 82, 386, 58);
		contentPane.add(lblProgress);
	}

	/**
	 * @param bar
	 * @param passlen
	 * @param sumlen
	 * @param lbl
	 */
	public void updateProgressBar(JProgressBar bar, long passlen, long sumlen,
			JLabel lbl, double passTime)
	{
		int percent = (int) (passlen * 100 / sumlen);
		bar.setValue(percent);
		if (lbl != null)
		{
			String msg = "正在接受文件....." + (passlen / 1024) + "kb" + "  /"
					+ (sumlen / 1024) + "kb" + "已接收 "+ percent + "%"
					+ "\n 发射点" + (int) ((passlen / 1024) / (passTime / 1000))
					+ "Kb/s";
			if (passlen == sumlen)
				msg = "传输完毕！";
			lblProgress.setText(msg);
		}
	}

	/**
	 * @param seletMode
	 * @return 
	 */
	public String showDialog(int seletMode)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(seletMode);
		chooser.setMultiSelectionEnabled(true);//璁剧疆澶氶?鏂囦欢
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String filePath = chooser.getSelectedFile().getAbsolutePath();
			return filePath;
		}
		return null;
	}
}
