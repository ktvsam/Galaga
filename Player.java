import java.awt.*;


class Player extends Canvas
{
    private int speed;
    private int x, y, w, h;

    public Player( int ex, int wy, int wd, int ht, int sp)
    {
        x=ex;
        y=wy;
        w=wd;
        h=ht;
        speed=sp;
    }

    //getters
    public int getX( ){ return x; }
    public int getY( ){ return y; }
    public int getW(){ return w; }
    public int getH(){ return h; }
    public int getSpeed(){ return speed; }

    //setters
    public void setX( int ex ){ x = ex; }
    public void setY( int wy ){ y = wy; }
    public void setW( int wd ){ y = wd; }
    public void setH( int ht ){ y = ht; }
    public void setSpeed( int sp ){ speed = sp; }

    public void goLeft()
    {
        if(getX()>264){
            setX( getX() - speed );
        }
    }


    public void goRight()
    {
        if(getX()<1464-getW())
            setX( getX() + speed );
        // keep the paddle from going off the screen to the right.
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }

    //overidde paint to draw your Paddle
    public void paint( Graphics window )
    {

        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("ship.png");
        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);

    }

}
