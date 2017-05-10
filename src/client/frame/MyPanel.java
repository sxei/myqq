package client.frame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import com.MyTools;

public class MyPanel extends JPanel
{
	private Image image=null;
	public MyPanel(String imagePath)
	{
		this.setOpaque(false);
		image=MyTools.getIcon(imagePath).getImage();
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
