import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;


class Blaster extends Canvas
{
    private int x, y, w, h, speed;


    public Blaster( int ex, int wy, int wd, int ht, int sp)
    {
        x=ex;
        y=wy;
        w=wd;
        h=ht;
        speed=sp;
    }


    public int getX( ){ return x; }
    public void setX( int ex ){ x = ex; }
    public int getY( ){ return y; }
    public void setY( int wy ){ y = wy; }
    public int getW(){ return w; }
    public void setW( int wd ){ y = wd; }
    public int getH(){ return h; }
    public void setH( int ht ){ y = ht; }
    public int getSpeed(){ return speed; }
    public void setSpeed( int sp ){ speed = sp; }
    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }

    public boolean intersects( Blaster other )
    {

        Rectangle yu= new Rectangle( x , y , w , h );
        Rectangle tu= new Rectangle( other.getX(), other.getY(), other.getW(), other.getH() );
        return yu.intersects(tu);

    }

    public void move(){
        if(getY()> -getH())
            setY(getY()-speed);
    }


    public void paint( Graphics window )
    {

//        window.setColor(Color.GREEN);
//        window.fillRect(getX(), getY(), getW(), getH());
        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("blaster.png");
        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);

    }
}


