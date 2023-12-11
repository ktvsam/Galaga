import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;


class Alien extends Canvas {


    private int x, y, w, h, startx, starty, yhelp, xsp, ysp;
    private boolean loop;
    private double degrees;

    public Alien(int ex, int wy, int wd, int ht, int sp, double dg) {
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
    public void paintRotate( Graphics window){
        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("david.png"); //use .gif or .png, you can choose the image
//        AffineTransform rotation = AffineTransform.rotate(Math.toRadians(degrees),getX(),getY());
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
        Image img1 = Toolkit.getDefaultToolkit().getImage("david.png"); //use .gif or .png, you can choose the image
        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);

    }
    // public void enterUpLeftLine(){
    //     int slope = (800 - getY()) / (500 - getX());
    //     int yint = (slope)*(getX()) - getY();
    //     setX(getX()-xsp);
    //     setY(slope*getX()-yint);
    //     System.out.println(getX());
    //     System.out.println(getY());
    // }
    // public void enterDownLeftLine(){

    //     int slope = (800 - getY()) / (500 - getX());
    //     int yint = (slope)*(getX()) + getY();
    //     setX(getX()+xsp);
    //     setY(slope*getX()+yint);
    //     System.out.println(getX());
    //     System.out.println(getY());
    // }
    // public void enterLeftLoop(){

    // }
    public int getX( ){ return x; }
    public void setX( int ex ){ x = ex; }
    public int getY( ){ return y; }
    public void setY( int wy ){ y = wy; }
    public int getW(){ return w; }
    public int getH(){ return h; }
    public void setLoop( boolean bo ){ loop = bo; }
    public boolean getLoop(){ return loop; }
    public Rectangle getRec() {
        return new Rectangle(h, w);
    }

    public boolean intersects( Alien other )
    {
        Rectangle block = new Rectangle(x, y, w, h);
        Rectangle another = new Rectangle(other.getX(), other.getY(), other.getW(), other.getH());
        return block.intersects(another);
    }
}
