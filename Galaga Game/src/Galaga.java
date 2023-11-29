import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

class Galaga extends JPanel implements Runnable, KeyListener
{
    private boolean[] keys; // this stores booleans for when a key is pressed or not.

    private Player player;    // this is the paddle that moves across the bottom of the screen


    public Galaga()
    {
        setBackground(Color.WHITE);

        keys = new boolean[3];

        player = new Player( 220, 540, 80, 50, 15);

        addKeyListener( this );    //
        setFocusable( true );     // Do NOT DELETE these three lines
        new Thread(this).start();  //
    }


    public void paint( Graphics window )// all other paint methods and game logic goes in here.
    {
        window.setColor(Color.WHITE); window.fillRect( 0,0, 800, 600); // makes the background white
        window.setColor(Color.BLACK); window.drawRect( 0,0, 800, 600); // draws a black box around the outside


        window.setColor(Color.BLUE); // to change fonts, color, etc: go to the Graphics Intro Folder


        window.drawString("Mouse  coordinates " + "(" + MouseInfo.getPointerInfo().getLocation().x + "   " + MouseInfo.getPointerInfo().getLocation().y + ")", 250, 30 );

        player.paint( window );   //(1A) paints the paddle



        if(keys[0]) // space is pressed
        {
            /* (2B) make the ball bounce, this runs after you press the space bar */
            //ball.Bounce();

        }
        if(keys[1]) // Left Arrow is pressed
        {
            /* (1B) move paddle left */
            player.goLeft();

            keys[1] = false;
        }
        if(keys[2]) // Right Arrow is pressed
        {
            /*(1B) move paddle right */
            player.goRight();

            keys[2] = false;
        }


    }



    // only edit if you would like to add more key functions
    public void keyPressed(KeyEvent e)
    {
        //Java KeyEvent docs
        //https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyEvent.html

        if( e.getKeyCode()  == KeyEvent.VK_SPACE )
        {
            keys[0]=true;
        }

        if( e.getKeyCode()  == KeyEvent.VK_LEFT )
        {
            keys[1]=true;
        }

        if( e.getKeyCode()  == KeyEvent.VK_RIGHT )
        {
            keys[2]=true;
        }
    }
    /*****~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*****/
// do not edit anything from this point on!!!
    public void keyTyped(KeyEvent e)
    {
        keyPressed( e );
    }
    public void keyReleased(KeyEvent e)
    {

    }

    public void run()
    {
        try
        {
            while( true )
            {
                Thread.sleep( 10 );
                repaint();
            }
        }
        catch( Exception e )
        {
        }
    }
}
