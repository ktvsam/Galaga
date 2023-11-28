import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/*
 *	Directions: extend the Block class to use the Block methods
 *
 *				Override the paint method to draw a Ball
 *				
 *				Make an xspeed and a yspeed instance variable in order to make the ball bounce
 *				
 *				Create a Ball constructor, using the Paddle constructor as an example, it can still take
 *				in just one speed, then set both instance variables to that one speed.
 *				
 *				Create a bounce method that keeps the ball on the screen but bounces around the screen.
 *				Do NOT test if the ball is touching the bricks or the paddle in this method, you will do that
 *				in the game logic in BreakOut.java.
 *
 *				Go to BreakOut and do Step 2 and test it in the runner.
 *
 *				If all works then move on to Brick
 *				
 */

class Laser extends Block
{
	private int ySp;
    private int xPos,yPos;
    private int w,h;

    public Laser(int x, int y,int we, int he,int yS){
        super(x,y,we,he);
        ySp = yS;
        int rand = (int)(Math.random()*2);
        xPos = x;
        yPos = y;
        w = we;
        h = he;
    }

    public Rectangle hitbox(){
        return new Rectangle(xPos,yPos,w,h);
    }
    public void move(){
        if(yPos<1000){
          yPos += ySp;
        }
    }
    public int getY(){
        return yPos;
    }
    public void paint(Graphics window){
        window.setColor(Color.RED);
        window.fillRect(xPos,yPos,w,h);
    }

}