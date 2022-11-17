package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40,arial_30;
	
	public UI(GamePanel gp) {
		this.gp=gp;
		
		arial_40=new Font("Arial",Font.PLAIN,40);
		arial_30=new Font("Arial",Font.PLAIN,30);
		
		
	}
	public void draw(Graphics2D g2) {
		this.g2=g2;
		
		if(gp.gameState==gp.playState) {
			g2.setFont(arial_30);
			g2.setColor(Color.BLUE);
			String text="Mavi: ";
			g2.drawString(text+gp.BlueSkor,270,50);
			
			g2.setFont(arial_30);
			g2.setColor(Color.red);
			String text1="Kırmızı: ";
			g2.drawString(text1+gp.RedSkor,400,50);
			
			
		}
		if(gp.gameState==gp.pauseState) {
			drawPauseScreen();
		}
		if(gp.gameState==gp.finishState) {
			drawFinishState();
		}
	}
	public void drawPauseScreen() {
		//gp.gameTheard=null;
		
	}
	public void drawFinishState() {
		
	}

}
