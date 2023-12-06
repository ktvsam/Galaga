import java.awt.*;
import java.awt.geom.AffineTransform;
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
    }

//    void applyRotation(double angleInDegrees) {
//        // Create a rotation transformation
//        AffineTransform rotation = AffineTransform.getRotateInstance(
//                Math.toRadians(angleInDegrees), x , y );
//
//
//        // Apply the rotation to the coordinates of the alien
//        Point2D rotatedPoint = rotation.transform(new Point2D.Double(x, y), null);
//        setX((int)rotatedPoint.getX());
//        setY((int)rotatedPoint.getY());
//    }
//    public void enter(){
//        System.out.println("BEFORE ANGLE:" + getX() + " " + getY());
//        applyRotation(90);
//        System.out.println("BEFORE:" + getX() + " " + getY());
//        x+=xsp;
//        y+=ysp;
//        System.out.println("AFTER: " + getX() + " " + getY());
//    }
    public void paintRotate( Graphics window){
        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("david.png"); //use .gif or .png, you can choose the image
        AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(15), x , y );
        g2.drawImage(img1, rotation, this);
    }

    public void paint( Graphics window )
    {
        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("david.png"); //use .gif or .png, you can choose the image
        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);

    }
    public void enterUpLeftLine(){

        int slope = (800 - getY()) / (500 - getX());
        int yint = (slope)*(getX()) - getY();
        setX(getX()-xsp);
        setY(slope*getX()-yint);
        System.out.println(getX());
        System.out.println(getY());
    }
    public void enterDownLeftLine(){

        int slope = (800 - getY()) / (500 - getX());
        int yint = (slope)*(getX()) + getY();
        setX(getX()+xsp);
        setY(slope*getX()+yint);
        System.out.println(getX());
        System.out.println(getY());
    }
    public void enterLeftLoop(){

    }
    public int getX( ){ return x; }
    public void setX( int ex ){ x = ex; }
    public int getY( ){ return y; }
    public void setY( int wy ){ y = wy; }
    public int getW(){ return w; }
    public int getH(){ return h; }



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
