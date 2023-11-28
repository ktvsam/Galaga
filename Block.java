import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/*
 *	Directions:
 *
 *		Write the intersects method below.
 *
 *		Then move on to Block
 */

abstract class Block extends Canvas
{
	private int x, y, w, h; //these are instance variables
	
		//this is the constructor for a Block
	public Block( int ex, int wy, int wd, int ht)
	{
		x = ex;
		y = wy;
		w = wd;
		h = ht;
	}
	
	// All Blocks will have all of these set and get methods
	public int getX( ){ return x; }
	public void setX( int ex ){ x = ex; }
	public int getY( ){ return y; }
	public void setY( int wy ){ y = wy; }
	public int getW(){ return w; }
	public int getH(){ return h; }
	
	
	public boolean intersects(int oX, int oY, int oW, int oH)
	{
		Rectangle a = new Rectangle(x,y,w,h);
		Rectangle b = new Rectangle(oX,oY,oW,oH);
		return a.intersects(b);
	}
	public boolean intersects(Rectangle r){
		return new Rectangle(x,y,w,h).intersects(r);
	}
}