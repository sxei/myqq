package client.common;

import java.awt.Color;
import java.awt.Image;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.MyTools;

public class MyTextPane
{
	JTextPane textPane=null;
	StyledDocument  document=null;
	
	public MyTextPane(JTextPane textPane)
	{
		this.textPane=textPane;
		document=this.textPane.getStyledDocument();
	}
	public void addText(String text,SimpleAttributeSet font)
	{
		try
		{
			document.insertString(document.getLength(), text, font);
			StyleConstants.setIcon(getMyAttribute(), MyTools.getIcon("../img/QQ_64.png"));
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}
	public void addIcon(String imagePath,String friendName)
	{
		try
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			document.insertString(document.getLength(), friendName+" "+time+"\n", MyTextPane.getTimeAttribute());
			textPane.setCaretPosition(document.getLength());
			textPane.insertIcon(MyTools.getIcon(imagePath));
			document.insertString(document.getLength(), "\n", getFriendAttribute());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 临时写的，以后改进
	 * @param imagePath
	 * @param friendName
	 */
	public void addIcon(Image image,String friendName)
	{
		try
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			document.insertString(document.getLength(), friendName+" "+time+"\n", MyTextPane.getTimeAttribute());
			textPane.setCaretPosition(document.getLength());
			textPane.insertIcon(new ImageIcon(image));
			document.insertString(document.getLength(), "\n", getFriendAttribute());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 获取某种字体
	 * @param name 字体名称
	 * @param size 字体大小
	 * @param color 字体颜色
	 * @param bold 是否加粗
	 * @param underline 是否加下划线
	 * @return 返回获取的字体
	 */
	public static SimpleAttributeSet getFontAttribute(String name, int size, Color color,
			boolean bold, boolean underline)
	{
		SimpleAttributeSet attribute = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attribute, name);
		StyleConstants.setFontSize(attribute, size);
		StyleConstants.setForeground(attribute, color);
		StyleConstants.setBold(attribute, bold);
		StyleConstants.setUnderline(attribute, underline);
		return attribute;
	}
	public static SimpleAttributeSet getMyAttribute()
	{
		return getFontAttribute("楷体", 22, Color.red, false, true);
	}
	public static SimpleAttributeSet getFriendAttribute()
	{
		return getFontAttribute("黑体", 16, Color.blue, false, false);
	}
	public static SimpleAttributeSet getTimeAttribute()
	{
		return getFontAttribute("黑体", 14, Color.DARK_GRAY, false, false);
	}
}
