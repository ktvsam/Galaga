import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

class BreakOut extends JPanel implements Runnable, KeyListener
{
	private boolean[] keys; private Ship ship; private Alien[][] aliens; private ArrayList<Bullet> bulls; private boolean lose; private ArrayList<Laser> lasers; boolean left;
	private ArrayList<Alien> alienShooters; private boolean win; private boolean right; private int relPos; private int ticker; private boolean firstStart; private int ticker2;
	public BreakOut()
	{
		setBackground(Color.WHITE);


		lose = false;
		keys = new boolean[5];
		ship = new Ship(450,900,100,100,40);
		bulls = new ArrayList<Bullet>();
		lasers = new ArrayList<Laser>();
		left = true;
		alienShooters = new ArrayList<Alien>();
		right = true;
		relPos = 0;
		ticker = 0;
		firstStart = true;
		ticker2 = 0;

		int ex = 25;
		int ey = 25;
		aliens = new Alien[5][8];
		for(int r=0;r<aliens.length;r++){
			for(int c=0;c<aliens[r].length;c++){
				aliens[r][c] = new Alien(ex,ey,75,75);
				ex += 125;
			}
			ex = 25;
			ey += 100;
		}

		addKeyListener( this );
		setFocusable( true );
		new Thread(this).start();
	}

	public void paint( Graphics window )
	{
		if(!lose && !win){
			Graphics2D g2 = (Graphics2D) window;
			Image img1 = Toolkit.getDefaultToolkit().getImage("bg1.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img1,0,0,1100,1100, this);
			ship.paint(window);
			int alienCount = 0;
			alienShooters.clear();
/*Alien Movement*/	if(ticker>=20){
				ticker = 0;
				if(right && relPos<20){
					relPos++;
				}
				if(!right && relPos>-20){
					relPos--;
				}
				if(relPos>=20){
					relPos = 19;
					right = false;
				}
				if(relPos<=-20){
					relPos=-19;
					right = true;
				}
			}
			else{
				ticker++;
			}
/*Alien Functions*/	for(int r=0;r<aliens.length;r++){
				for(int c=0;c<aliens[r].length;c++){
					if(aliens[r][c]!=null){
						if(ticker==20 && right){
							aliens[r][c].setX(aliens[r][c].getX()+1);
						}
						else if(ticker==20 && !right){
							aliens[r][c].setX(aliens[r][c].getX()-1);
						}
						alienCount++;
						aliens[r][c].paint(window);
						try{
							if(aliens[r][c]!=null && (r==4 || aliens[r+1][c]==null)){
								alienShooters.add(aliens[r][c]);
							}
						}
						catch(Exception e){}
						for(int i=bulls.size()-1;i>=0;i--){
							try{
								if(aliens[r][c].getHitbox().intersects(bulls.get(i).hitbox())){
									aliens[r][c] = null;
									bulls.remove(i);
								}
							}
							catch(Exception e){}
						}
					}
				}
			}
			if(alienCount==0){
				win = true;
			}
/*Alien Shooting*/	for(Alien alien:alienShooters){
				if(alienShooters.size()>7){
					int rand = (int)(Math.random()*551);
					if(rand==5){
						lasers.add(new Laser(alien.getX()+(int)(Math.random()*70),alien.getY()+70,10,40,5));
					}
				}
				else if(alienShooters.size()>3){
					int rand = (int)(Math.random()*351);
					if(rand==5){
						lasers.add(new Laser(alien.getX()+(int)(Math.random()*70),alien.getY()+70,10,40,5));
					}
				}
				else{
					int rand = (int)(Math.random()*151);
					if(rand==5){
						lasers.add(new Laser(alien.getX()+(int)(Math.random()*70),alien.getY()+70,10,40,5));
					}
				}
			}
/*Laser Movement & Culling*/ for(int i=lasers.size()-1;i>=0;i--){
				lasers.get(i).paint(window);
				lasers.get(i).move();
				if(ship.getHitbox().intersects(lasers.get(i).hitbox()) && !ship.getLeft().intersects(lasers.get(i).hitbox()) && !ship.getRight().intersects(lasers.get(i).hitbox())){
					lose = true;
				}
				if(lasers.get(i).getY()>955){
					lasers.remove(i);
				}
				for(int z=bulls.size()-1;z>=0;z--){
					try{
						if(lasers.get(i).hitbox().intersects(bulls.get(z).hitbox())){
							lasers.remove(i);
							bulls.remove(z);
						}
						if(bulls.get(i).intersects(ship.getLeft())){
							ship.destroyL();
							lasers.remove(i);
							System.out.println("destroyed left");
						}
						if(bulls.get(i).intersects(ship.getRight())){
							ship.destroyR();
							lasers.remove(i);
							System.out.println("destroyed right");
						}
					}
					catch(Exception e){}
				}
			}
/*Bullet Movement & Culling*/ for(int i=bulls.size()-1;i>=0;i--){
				bulls.get(i).paint(window);
				bulls.get(i).move();
				try{
					if(bulls.get(i).getY()<5){
						bulls.remove(i);
					}
				}
				catch(Exception e){}
			}
			if(keys[0]) {
				if(bulls.size()<=4){
					if(ship.getL() && ship.getR()){
						if(left){
							bulls.add(new Bullet(ship.getX()+1,ship.getY(),10,50,5));
							left = false;
						}
						else{
							bulls.add(new Bullet(ship.getX()+90,ship.getY(),10,50,5));
							left = true;
						}
					}
					else if(ship.getL() && !ship.getR()){
						bulls.add(new Bullet(ship.getX()+1,ship.getY(),10,50,5));
					}
					else if(!ship.getL() && ship.getR()){
						bulls.add(new Bullet(ship.getX()+90,ship.getY(),10,50,5));
					}
					else{
						lose = true;
					}
				}
				keys[0] = false;
			}
		}
		else if(lose & !win){
			youLose(window);
		}
		else{
			Graphics2D g2 = (Graphics2D) window;
			Image img1 = Toolkit.getDefaultToolkit().getImage("bg1.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img1,0,0,1100,1100, this);
			ship.paint(window);
			ship.leave();
			Image img2 = Toolkit.getDefaultToolkit().getImage("old_powerup.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img2,250,250,500,500, this);
		}
		                                   // Key listeners (0 = space, 1 = left arrow, 2 = right arrow)
		if(keys[1] && !lose && !win) {ship.goLeft();keys[1] = false;}
		if(keys[2] && !lose && !win) {ship.goRight();keys[2] = false;}
		if(keys[3] && !lose) {winGame(); keys[3] = false;}
		if(keys[4] && !lose) {loseGame(); keys[4] = false;}
	}

	public void winGame(){
		for(int i=0;i<100;i++){
			bulls.add(new Bullet(0,900,1000,500,20));
		}
	}

	public void loseGame(){
		for(int r=0;r<aliens.length;r++){
			for(int c=0;c<aliens[r].length;c++){
				if(aliens[r][c]!=null){
					try{
						alienShooters.add(aliens[r][c]);
					}
					catch(Exception e){}
				}
			}
		}
		for(int i=0;i<10;i++){
			for(Alien alien:alienShooters){
				lasers.add(new Laser(alien.getX()+(int)(Math.random()*125),alien.getY()+70,100,40,10));
			}
			lasers.add(new Laser(0,0,1000,100,30));
		}
	}
	public void youLose(Graphics window){
		Graphics2D g2 = (Graphics2D) window;
		Image img1 = Toolkit.getDefaultToolkit().getImage("bg2.png"); //use .gif or .png, you can choose the image
		g2.drawImage(img1,0,0,1100,1100, this);

		if(ticker2<250){
			ship.explode();
			ship.paint(window);
			ticker2++;
		}
		else{
			Image img2 = Toolkit.getDefaultToolkit().getImage("youlose_usable.png"); //use .gif or .png, you can choose the image
			g2.drawImage(img2,0,0,1100,1100, this);
		}
	}

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

	public void keyTyped(KeyEvent e)
	{
		keyPressed( e );			
	}		
	public void keyReleased(KeyEvent e) {}
	
	public void run()
	{
		try
		{
			while( true )
			{
				if(firstStart){
					try{
						Thread.sleep(1000);
					}
					catch(Exception e){}
					firstStart = false;
				}
				else {
					Thread.sleep(6);
					repaint();
				}
			}
		}
		catch( Exception e )
		{			
		}
	}
}








//testing balls