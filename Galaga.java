import javax.swing.JFrame;

public class Galaga extends JFrame
{
	private static final int WIDTH = 1744; //1728
	private static final int HEIGHT = 1383; //1344
	public Galaga()
	{
		super("Galaga");
		setSize(WIDTH,HEIGHT);
		getContentPane().add(new Game() );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main( String args[] )
	{
		Galaga run = new Galaga();
	}
}