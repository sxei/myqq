package client.common;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import com.MyTools;

public class MyLabel
{
	//规定：常规状态图片索引为1，鼠标进入为2，鼠标按下为3，鼠标释放还是2
	JLabel jLabel=null;
	String fileName="";
	String extension="";
	int mode=1;//模式为1表示用图片，模式为2表示用Boder
	Color backColor=null;//Label的父容器的背景色
	/**
	 * 根据指定的若干张图片来实例化一个Label
	 * @param fileName 图片的路径,不包括图片索引和扩展名，例如
	 * 如果图片名为img/QQ_1.png，则写成"../img/QQ"
	 * @param extension 扩展名，不包括前面的点"."
	 */
	public MyLabel(JLabel jLabel,String fileName,String extension)
	{
		this.jLabel=jLabel;
		this.fileName=fileName;
		this.extension=extension;
	}
	public MyLabel(JLabel jLabel)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=this.jLabel.getParent().getBackground();
		setEtchedBorder(backColor);//将label的默认边框颜色设置的和背景色一样，以达到不现实边框的作用
	}
	public MyLabel(JLabel jLabel,Color color)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=color;
		setEtchedBorder(backColor);
	}

	/**
	 * 设置成一种边框，中文翻译作什么我也不知道
	 * @param color
	 */
	public void setEtchedBorder(Color color)
	{
		jLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(color, color));
	}
	/**
	 * 给jLabel添加事件
	 */
	public void addEvent()
	{
		jLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_1."+extension));
				else 
					setEtchedBorder(backColor);
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_3."+extension));
				else 
					jLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(
							javax.swing.border.BevelBorder.LOWERED));
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
		});
	}
}
