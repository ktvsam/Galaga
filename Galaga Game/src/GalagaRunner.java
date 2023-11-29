import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;


/*
 * Directions: Go to all the other labs and press F9 (build) on each of them in order.
 *          then come back to this runner and press F10 (run);
 *          Then follow the directions in the other labs starting with Lab01.
 */


class GalagaRunner extends JFrame
{
    private static final int WIDTH = 820;
    private static final int HEIGHT = 640;


    public GalagaRunner()
    {
        super("BreakOut");


        setSize(WIDTH,HEIGHT);


        //This line loads the BreakOut game
        getContentPane().add( new Galaga() );

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main( String args[] )
    {
        GalagaRunner run = new GalagaRunner();
    }
}

