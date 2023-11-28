import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/*
 *	Directions: 
 *				In the paint method Choose if you want to draw your paddle or 
 *				if you want to use an image to make a paddle.
 *				
 *				Finish the goLeft & goRight methods
 *
 *				Go to BreakOut and do Step 1.
 *				Then test it out in the runner.
 *				If all of the paddle works move on to Ball
 */
 
 
class Ship extends Block
{
	private int speed; // a paddle has to have a speed to make it move
	private Polygon hitbox;
	private boolean lost;

	private Rectangle right;
	private Rectangle left;
	private boolean r;
	private boolean l;

	
	public Ship( int ex, int wy, int wd, int ht, int sp)
	{
		super(ex,wy,wd,ht);
		speed = sp;
		lost = false;
		l = true;
		r = true;
	}
	public void leave(){
		if(getY()>-200){
			setY(getY()-5);
		}
	}
	public void goLeft()
	{
	  if(getX()>0){
		  setX(getX()-speed);
	  }
	  if(getX()<0){
		  setX(0);
	  }
	}
	public void goRight() 
	{
	  if(getX()<900){
		  setX(getX()+speed);
	  }
	  if(getX()>900){
		  setX(900);
	  }
	}
	public void explode(){
		lost = true;
	}
	public Polygon getHitbox(){
		return hitbox;
	}

	public Rectangle getRight(){
		return right;
	}
	public Rectangle getLeft(){
		return left;
	}
	public void destroyL(){
		l = false;
	}
	public void destroyR(){
		r = false;
	}
	public boolean getR(){
		return r;
	}
	public boolean getL(){
		return l;
	}
	//overidde paint to draw your Paddle
	public void paint( Graphics window )
	{
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		hitbox = new Polygon(new int[]{x,x+(w/2),x+w}, new int[]{y+h,y,y+h}, 3);
		left = new Rectangle(x,y+(h/2),10,h/2);
		right = new Rectangle(x+90,y+(h/2),10,h/2);


		if(!lost){
			Graphics2D g2 = (Graphics2D) window;
			Image img1 = Toolkit.getDefaultToolkit().getImage("ship.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img1, getX(), getY(), getW(), getH(), this);
		}
		else{
			Graphics2D g2 = (Graphics2D) window;
			Image img1 = Toolkit.getDefaultToolkit().getImage("explosion.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img1, getX(), getY(), getW(), getH(), this);
		}

//		window.setColor(Color.ORANGE);
//		window.fillPolygon(hitbox);
//		window.setColor(Color.BLUE);
//		window.fillRect(x+90,y+(h/2),10,h/2);
//		window.setColor(Color.RED);
//		window.fillRect(x,y+(h/2),10,h/2);
	}	
		
}