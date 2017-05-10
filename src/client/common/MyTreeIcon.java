package client.common;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import client.control.Main;

import com.MyTools;

public class MyTreeIcon extends DefaultTreeCellRenderer 
{
	//如果是：用户名;图片路径，那么就是好友节点
	//如果是：用户名;图片路径1;图片路径2,那么表示分组节点，
	//图片路径1表示未展开的分组，图片路径2表示展开了的分组
	ArrayList<String> nodeImages=null;
    public MyTreeIcon(ArrayList<String> nodeImages)
    {
    	this.nodeImages=nodeImages;
    }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) 
    {
        super.getTreeCellRendererComponent(tree, value, sel,expanded,
        		leaf, row, hasFocus);
        for(String str:nodeImages)
        {
        	String[] temp=str.split(MyTools.SPLIT1);
        	if(value.toString().startsWith(temp[0])&&!temp[0].equals(""))//注意Value一定要toString()
        	{
        		 try
				{
					//Image grayImage = GrayFilter.createDisabledImage(ImageIO.read(Main.class.getResource(temp[1])));
					if(temp.length==2)//如果是：用户名;图片路径，那么就是好友节点
	        			//this.setIcon(new ImageIcon(grayImage));
						this.setIcon(MyTools.getIcon(temp[1]));
	        		else if(temp.length==3)//如果是：用户名;图片路径1;图片路径2,那么表示分组节点，
	        		{
	        			if(!expanded)
	        				this.setIcon(MyTools.getIcon(temp[1]));
	        			else 
	        				this.setIcon(MyTools.getIcon(temp[2]));
	        		}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
        		
        	}
        }
        return this;
    }
}

