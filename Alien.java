import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;


/*
* Directions:
*          Make the brick extend Block
*
*          Write the Brick constructor that takes an x, y, w, h to create a block.
*
*          Override the paint method to draw a Brick


           Choose to draw or import an image.
*
*          Go to BreakOut and do Step 3, then test in the runner.
*
*          If all works correctly then move on to Steps 4 - 6 in BreakOut.
*/
//https://youtu.be/AheaTd_l5Is?t=152
class Alien extends Canvas {


    private int x, y, w, h, startx, starty, yhelp, xsp, ysp;
    private boolean loop;
    private double degrees;

    public Alien(int ex, int wy, int wd, int ht, int sp) {
        x = ex;
        y = wy;
        w = wd;
        h = ht;
        xsp = sp;
        ysp = sp;
        startx = 100;
        starty = 0;
        yhelp = 1;
        degrees = 10;
        loop = false;
    }

    public void enter(){
        System.out.println("BEFORE:" + getX() + " " + getY());
        y+=ysp;
        System.out.println("AFTER: " + getX() + " " + getY());
    }
    //paint rotate spins it
    public void paintRotate( Graphics window){
        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("bug1.png"); //use .gif or .png, you can choose the image
        AffineTransform rotation = new AffineTransform();
        rotation.translate(getX(), getY());
        rotation.rotate(Math.toRadians(degrees));
        rotation.scale(.55, .55);
        System.out.println(getX() + " " + getY());
        g2.drawImage(img1, rotation, this);
        degrees+=10;
        if(degrees == 360){
            setLoop(true);
        }
    }

    public void paint( Graphics window )
    {
        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("bug1.png"); //use .gif or .png, you can choose the image
        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);

    }
    //path takes the ship to a point
    public void path(int xx, int yy){
            double distance = Math.sqrt(Math.abs(getX()-xx)*Math.abs(getX()-xx)+(getY()-yy)*(getY()-yy));
            double moveX = xsp * (xx-getX()) / distance;
            double moveY = ysp * (yy-getY()) / distance;

            setX(getX() + (int) moveX);
            setY(getY() + (int) moveY);
    }
    public boolean getPath(int curx, int cury, int targx, int targy){
        if(curx == targx && cury == targy){
            return true;
        }
        return false;
    }
//    public void paintPath( Graphics window , int xx, int yy)
//    {
//        Graphics2D g2 = (Graphics2D) window;
//        Image img1 = Toolkit.getDefaultToolkit().getImage("david.png"); //use .gif or .png, you can choose the image
//        Path2D.Double n= new Path2D.Double();
//
//        setX(getX()+xsp);
//        setY(getY()-ysp);
//        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);
//
//
//    }

    public int getX( ){ return x; }
    public void setX( int ex ){ x = ex; }
    public int getY( ){ return y; }
    public void setY( int wy ){ y = wy; }
    public int getW(){ return w; }
    public int getH(){ return h; }
    public void setLoop( boolean bo ){ loop = bo; }
    public boolean getLoop(){ return loop; }
    public Rectangle getRect() {
        return new Rectangle(x,y,w,h);
    }


    public void movement1(){
        this.setY(getY() + ysp*(int)Math.sin(30));


    }
    public boolean intersects( Alien other )
    {
        Rectangle block = new Rectangle(x, y, w, h);
        Rectangle another = new Rectangle(other.getX(), other.getY(), other.getW(), other.getH());
        return block.intersects(another);
    }
}
