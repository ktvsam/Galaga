import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class Game extends JPanel implements Runnable, KeyListener
{
	private boolean[] keys; Player player; private Alien[][] aliens; private Integer[][][] alienCoords;
	private boolean playing; private int bgscroll; private int twinklycounter; private int cooldown; private int alienTimer;
	//private ArrayList<Laser> lasers;
	private ArrayList<Blaster> blasters; private int lives; private boolean startLevel; int score; int highScore; int alienTarget; boolean makeNew; private ArrayList<Blaster> lasers; public boolean youLose;
	private List<Box> stuff;	
	
	public Game ()
	{
		setBackground(Color.WHITE);

		keys = new boolean[5];
		playing = false;
		player = new Player((1728/2)-40,1200,80,80,30);
		bgscroll = 0;
		twinklycounter = 0;
		blasters = new ArrayList<Blaster>();
		lasers = new ArrayList<Blaster>();
		cooldown = 0;                  //blaster cooldown
		lives = 2;
		startLevel = false;            //timer for "new level" screen
		score = 0;
		youLose = false;
		aliens = new Alien[5][10];    //holds actual aliens
		alienCoords = new Integer[5][10][2]; // Integer[Alien[] x coord][Alien[] y coord][x(0) or y(1)]
		alienTimer = 0;   //controls when to make a new alien row or move the alien row
		makeNew = true;   //controls whether to make a new alien row or to move the current alien row
		int ytrac = 20;
		for(int r=0;r<aliens.length;r++){
			int xtrac = 374;
			for(int c=0;c<aliens[r].length;c++){
				aliens[r][c] = null;
				alienCoords[r][c][0] = xtrac;
				alienCoords[r][c][1] = ytrac;
				xtrac += 100;
			}
			ytrac += 100;
		}
		addKeyListener( this );
		setFocusable( true );
		new Thread(this).start();
	}

	public void paint( Graphics window )
	{
		if(!playing){
			window.setColor(Color.BLACK);
			window.fillRect(0,0,1728,1344);
			Graphics2D g1 = (Graphics2D) window;
			Image img1 = Toolkit.getDefaultToolkit().getImage("startmenu.png"); //use .gif or .png, you can choose the image
			g1.drawImage(img1,0,0,1728,1344, this);

			if(keys[0]){
				playing = true;
				keys[0] = false;
			}
		}
		else if(youLose){
			Graphics2D g1 = (Graphics2D) window;
			Image img1 = Toolkit.getDefaultToolkit().getImage("ulose.png"); //use .gif or .png, you can choose the image
			g1.drawImage(img1,0,0,1728,1344, this);
		}
		else {
			Graphics2D g2 = (Graphics2D) window;
			if (twinklycounter == 0) {
				for (int x = 0; x < 19; x++) {
					for (int y = 0; y < 15; y++) {
						Image img1 = Toolkit.getDefaultToolkit().getImage("davidstar.png"); //use .gif or .png, you can choose the image
						g2.drawImage(img1, x * 120, y * 120, 120 + (x * 5), 120 + (y * 5), this);
					}
				}
				twinklycounter = 15;
			} else {
				for (int x = 0; x < 19; x++) {
					for (int y = 0; y < 15; y++) {
						Image img1 = Toolkit.getDefaultToolkit().getImage("davidstar.png"); //use .gif or .png, you can choose the image
						g2.drawImage(img1, x * 120, y * 120, 120 + (x * 6), 120 + (y * 5), this);
					}
				}
				twinklycounter--;
			}
//			Image img1 = Toolkit.getDefaultToolkit().getImage("startmenu.png"); //use .gif or .png, you can choose the image
//			g2.drawImage(img1,0,0,1728,1344, this);
			if (bgscroll < 0) {
				bgscroll += 10;
			} else {
				bgscroll = -12096;
			}
			if (cooldown < 4) {
				cooldown++;
			} else {
				cooldown = 0;
			}
			Image img1 = Toolkit.getDefaultToolkit().getImage("bg.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img1, 0, bgscroll, 1728, 13440, this);
			if (!startLevel) {
				window.setColor(Color.CYAN);
				window.drawString("PRESS SPACE TO PLAY NEXT LEVEL", 600, 100);
				player.paint(window);
				alienTimer = 0;
				if(keys[0]){
					startLevel = true;
					keys[0] = false;
				}
			}
			else {
				if(alienTimer == 0){
					int ex = 0;
					if (makeNew){
						for(int c=0;c<10;c++){
							aliens[3][c] = new Alien((1728-264) - 100 + ex,900,80,80,6);
							ex += 90;
							System.out.println(ex);
						}
						makeNew = false;
						System.out.println("making new");
					}
					else{
						boolean arrived = false;
						for(int c=9;c>=0;c--){
							if(aliens[3][c] != null && aliens[3][c].getPath(aliens[3][c].getX(),aliens[3][c].getY(),alienCoords[3][c][0],alienCoords[3][c][1])){
								arrived = true;
								System.out.println("making arrived true");
							}
							else if(aliens[3][c] == null && isRowEmpty(aliens[3])){
								arrived = true;
							}
							else if(aliens[3][c] != null && !aliens[3][c].getPath(aliens[3][c].getX(),aliens[3][c].getY(),alienCoords[3][c][0],alienCoords[3][c][1])){
								aliens[3][c].path(alienCoords[3][c][0],alienCoords[3][c][1]);
								arrived = false;
							}
						}
						if(arrived == true){
							makeNew = true;
							alienTimer++;
							System.out.println("all arrived");
						}
					}
				}
				else if(alienTimer == 100){
					int ex = 0;
					if (makeNew){
						for(int c=0;c<10;c++){
							aliens[4][c] = new Alien(-1050 + ex,900,80,80,6);
							ex += 90;
						}
						makeNew = false;
						System.out.println("making new");
					}
					else{
						boolean arrived = false;
						for(int c=0;c<10;c++){
							if(aliens[4][c] != null && aliens[4][c].getPath(aliens[4][c].getX(),aliens[4][c].getY(),alienCoords[4][c][0],alienCoords[4][c][1])){
								arrived = true;
								System.out.println("making arrived true");
							}
//							else if(aliens[3][c] == null && !isRowEmpty(aliens[3])){
//								arrived = true;
//							}
							else if(aliens[4][c] != null && !aliens[4][c].getPath(aliens[4][c].getX(),aliens[4][c].getY(),alienCoords[4][c][0],alienCoords[4][c][1])){
								aliens[4][c].path(alienCoords[4][c][0],alienCoords[4][c][1]);
								arrived = false;
							}
						}
						if(arrived == true){
							makeNew = true;
							alienTimer++;
							System.out.println("all arrived");
						}
					}
				}
				else{
					alienTimer++;
				}
				System.out.println(alienTimer);
				//add other alien timers
				for (int b = 0; b < blasters.size(); b++) {
					blasters.get(b).move();
					blasters.get(b).paint(window);
					if (blasters.get(b).getY() < 20) {
						blasters.remove(b);
					}
				}
				for (int b = 0; b < lasers.size(); b++) {
					lasers.get(b).move();
					lasers.get(b).paint(window);
					if (lasers.get(b).getY() > 2000) {
						lasers.remove(b);
					}
					if (lasers.get(b).intersects(player.getRect())){
						youLose = true;
					}
					for (int a = 0; a < blasters.size(); a++) {
						if(lasers.get(b).intersects(blasters.get(a))){
							lasers.remove(b);
							blasters.remove(a);
						}
					}
				}

				for (int r = 0; r < aliens.length; r++) {
					for (int c = 0; c < aliens[r].length; c++) {
						if (aliens[r][c] != null) {
							int rand = (int)(Math.random()*100);
							if(rand == 69){
								int tempx = aliens[r][c].getX();
								int tempy = aliens[r][c].getY();
								//lasers.add(new Blaster(tempx + (aliens[r][c].getW()/2)-5, tempy-30 , 10,26,25,false, aliens[r][c].getXsp()));
							}
							aliens[r][c].paint(window);
							for (int b = 0; b < blasters.size() && aliens[r][c] != null; b++) {
								if (aliens[r][c].getRect().intersects(blasters.get(b).getRect())) {
									aliens[r][c] = null;
									blasters.remove(b);
									if (r == 4 || r == 3) {
										score += 100;
									} else if (r == 2) {
										score += 200;
									} else if (r == 1) {
										score += 500;
									} else if (r == 0) {
										score += 100;
									}
								}
							}
						}
					}
				}


				if (player.getX() < 264) {
					player.setX(265);
				} else if (player.getX() > 1464 - player.getW()) {
					player.setX(1463 - player.getW());
				}
				player.paint(window);
				if (keys[1]) {
					player.goLeft();
					keys[1] = false;
				}
				if (keys[2]) {
					player.goRight();
					keys[2] = false;
				}
				if (keys[0] && cooldown == 0) {
					blasters.add(new Blaster(player.getX() + (player.getW() / 2) - 5, player.getY() + 36, 10, 26, 25,true,0));
					keys[0] = false;
				}
			}

			window.setColor(Color.BLACK);
			window.fillRect(0, 0, 264, 1344);
			window.fillRect(1728 - 264, 0, 264, 1344);
			window.setColor(Color.DARK_GRAY);
			window.drawRect(0, 0, 264, 1344);
			window.drawRect(1728 - 264, 0, 264, 1344);
			int templives = lives;
			for (int y = 1254; y > 0 && templives > 0; y -= 90) {
				Image img2 = Toolkit.getDefaultToolkit().getImage("ship.png"); //use .gif or .png, you can choose the image
				g2.drawImage(img2, 10, y, 80, 80, this);
				templives--;
			}
			Image img2 = Toolkit.getDefaultToolkit().getImage("highscore.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img2, 10, 10, 244, 122, this);
//
			Image img3 = Toolkit.getDefaultToolkit().getImage("yourscore.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img3,10,254,244,122, this);
			window.setColor(Color.WHITE);
			window.drawString(String.valueOf(score),10,396);
//
		}
	}
//	private static void modifyFile(String filePath, String oldString, String newString) {
//		File fileToBeModified = new File(filePath);
//		StringBuilder oldContent = new StringBuilder();
//		try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified))) {
//			String line = reader.readLine();
//			while (line != null) {
//				oldContent.append(line).append(System.lineSeparator());
//				line = reader.readLine();
//			}
//			String content = oldContent.toString();
//			String newContent = content.replaceAll(oldString, newString);
//			try (FileWriter writer = new FileWriter(fileToBeModified)) {
//				writer.write(newContent);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public void keyPressed(KeyEvent e)
	{
		//Java KeyEvent docs
		//https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyEvent.html
		if( e.getKeyCode()  == KeyEvent.VK_SPACE ) {keys[0]=true;}

		if( e.getKeyCode()  == KeyEvent.VK_LEFT ) {keys[1]=true;}

		if( e.getKeyCode()  == KeyEvent.VK_RIGHT ) {keys[2]=true;}

		if( e.getKeyCode()  == KeyEvent.VK_UP ) {keys[3] = true;}

		if( e.getKeyCode()  == KeyEvent.VK_DOWN ) {keys[4] = true;}
	}
	public void keyTyped(KeyEvent e) {keyPressed( e );}
	public void keyReleased(KeyEvent e) {}
	public boolean isRowEmpty(Alien[] arr){
		boolean ans = true;
		for(int i=0;i<arr.length;i++){
			if(arr[i] != null){
				ans = false;
			}
		}
		return ans;
	}
	public void run()
	{

		try
		{
			while( true )
			{	
			   Thread.sleep(33);
			   repaint();
			}
		}
		catch( Exception e )
		{
			
		}

	}
}