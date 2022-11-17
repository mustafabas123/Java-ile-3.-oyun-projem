package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;


	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyHand);
	}
	//Screen's setting
	final int originalTileSize=16;//20pixels
	final int scale=3;
	public int TileSize=originalTileSize*scale;//20x3=60
	public int MaxScreenCol=16;
	public int MaxScreenRow=12;
	public int ScreenWidth=MaxScreenCol*TileSize;
	public int ScreenHeight=MaxScreenRow*TileSize;
	Random random;
	
	//game's setting
	public int blueY=200;
	public int redY=200;
	public int Movedir=20;
	
	public int TopX=ScreenWidth/2;
	public int TopY=ScreenHeight/2;
	public int TopDirX=5;
	public int TopDirY=5;
	public int BlueSkor=0;
	public int RedSkor=0;
	
	//FPS
	int FPS=60;
	
	//Game state
	public int gameState=1;
	public final int playState=2;
	public final int pauseState=3;
	public final int finishState=4;
	
	Thread gameTheard;
	KeyHandler keyHand=new KeyHandler(this);
	UI ui=new UI(this);
	

	@Override
	public void run() {
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		int drawCount=0;
		while(gameTheard!=null) {
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			if(delta>=1) {
				update();			
			    repaint();
			    delta--;
			    drawCount++;
			}
		
	}
		
		
	}
	public boolean KontrolEt1() {
		if(new Rectangle(TopX,TopY,20,20).intersects(new Rectangle(750,redY,20,150))) {
			return true;
		}
		return false;
	}
	public boolean KontrolEt2() {
		if(new Rectangle(TopX,TopY,20,20).intersects(new Rectangle(0,blueY,20,150))) {
			return true;
		}
		return false;
	}
	public boolean KontrolEt3() {
		if(new Rectangle(TopX,TopY,20,20).intersects(new Rectangle(0,0,ScreenWidth,10))) {
			return true;
		}
		return false;
	}
	public boolean KontrolEt4() {
		if(new Rectangle(TopX,TopY,20,20).intersects(new Rectangle(0,570,ScreenWidth,10))) {
			return true;
		}
		return false;
	}
	public boolean KontrolEt5() {
		if(new Rectangle(TopX,TopY,20,20).intersects(new Rectangle(-20,0,10,ScreenHeight))) {
			return true;
		}
		return false;
	}
	public boolean KontrolEt6() {
		if(new Rectangle(TopX,TopY,20,20).intersects(new Rectangle(760,0,10,ScreenHeight))) {
			return true;
		}
		return false;
	}
	public void setupGame() {
		gameState=playState;
	}
	public void update() {
		random=new Random();
		int randomXdirection=random.nextInt(2);
		if(gameState==playState) {
		
		if(keyHand.redUp ||keyHand.redDown || keyHand.blueDown || keyHand.blueUp) {
			if(keyHand.redUp) {
				if(redY<=0) {
					redY=0;
				}
				else {
					redY-=Movedir;
				}
			}
			else if(keyHand.redDown) {
				if(redY>=430) {
					redY=430;
				}
				else {
					redY+=Movedir;
				}
				
			}
			else if(keyHand.blueDown) {
				if(blueY>=430) {
					blueY=430;
				}
				else {
					blueY+=Movedir;
				}
				
			}
			else if(keyHand.blueUp) {
				if(blueY<=0) {
					blueY=0;
				}
				else {
					blueY-=Movedir;
				}
				
			}
		}
		TopY-=TopDirY;
		TopX+=TopDirX;
		if(KontrolEt1()) {
			TopDirX=-TopDirX;
		}
		if(KontrolEt2()) {
			TopDirX=-TopDirX;
		}

		if(KontrolEt3()) {
			TopDirY=-TopDirY;
		}
		if(KontrolEt4()) {
			TopDirY=-TopDirY;
		}
		if(KontrolEt5()) {
			RedSkor++;
			TopX=ScreenWidth/2;
			TopY=ScreenHeight/2;
			if(randomXdirection==0) {
				TopDirX=-TopDirX;
			}
			else if(randomXdirection==1) {
				TopDirX=TopDirX;
			}
			
//			gameState=pauseState;
			
		}
		if(KontrolEt6()) {
			BlueSkor++;
			TopX=ScreenWidth/2;
			TopY=ScreenHeight/2;
			if(randomXdirection==0) {
				TopDirX=-TopDirX;
			}
			else if(randomXdirection==1) {
				TopDirX=TopDirX;
			}
			
//			gameState=pauseState;
		}
		}
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		super.paintComponent(g);
		
		g2.setColor(Color.white);
		g2.fillOval(TopX,TopY,20,20);
				
		g2.setColor(Color.blue);
		g2.fillRect(0,blueY,20,150);
		g2.setColor(Color.red);
		g2.fillRect(748,redY,20,150);
		g2.setColor(Color.white);
		g2.drawLine(ScreenWidth/2,0,ScreenWidth/2,ScreenHeight);
		
		ui.draw(g2);
		
		g2.dispose();
	}
	public void startGameThread() {
		gameTheard=new Thread(this);
		gameTheard.start();
	}

}
