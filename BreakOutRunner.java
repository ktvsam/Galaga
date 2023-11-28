import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
class BreakOutRunner extends JFrame
{
	private static final int WIDTH = 1016;
	private static final int HEIGHT = 1039;

	public BreakOutRunner() 
	{
		super("Space Invaders!");
		setSize(WIDTH,HEIGHT);
		getContentPane().add( new BreakOut() );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main( String args[] ) {
		BreakOutRunner run = new BreakOutRunner();
	}
}  

