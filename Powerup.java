import java.awt.*;

public class Powerup extends Block{

    int ax; int ay; int aw; int ah;
    public Powerup(int x,int y,int w,int h){
        super(x,y,w,h);
        ax = x;
        ay = y;
        aw = w;
        ah = h;
    }
    public void movePowerup(){
        ay += 2;
        setY(getY()+2);
    }

    public boolean intersects(int oX, int oY, int oW, int oH)
    {
        Rectangle a = new Rectangle(ax,ay,aw,ah);
        Rectangle b = new Rectangle(oX,oY,oW,oH);
        return a.intersects(b);
    }

    public void paint(Graphics window){

        Color col = new Color(0,0,0,0);
        window.setColor(col);
        window.fillRect(ax,ay,aw,ah);

        Graphics2D g2 = (Graphics2D) window;
        Image img1 = Toolkit.getDefaultToolkit().getImage("powerup.png"); //use .gif or .png, you can choose the image
        g2.drawImage(img1, getX(), getY(), getW(), getH(), this);
    }
}
