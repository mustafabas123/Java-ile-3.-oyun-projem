package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean redUp,redDown,blueUp,blueDown;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		if(gp.gameState==gp.playState) {
			if(code==KeyEvent.VK_W) {

				blueUp=true;
			}
			if(code==KeyEvent.VK_S) {
				blueDown=true;
			}

			if(code==KeyEvent.VK_UP) {
				redUp=true;
			}

			if(code==KeyEvent.VK_DOWN) {
				redDown=true;
			}

			if(code==KeyEvent.VK_P) {
				gp.gameState=gp.pauseState;
			}
			
		}
		else if(gp.gameState==gp.pauseState) {
			if(code==KeyEvent.VK_P) {
				gp.gameState=gp.playState;
			}
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
		if(gp.gameState==gp.playState) {
			if(code==KeyEvent.VK_W) {

				blueUp=false;
			}

			if(code==KeyEvent.VK_S) {
				blueDown=false;
			}

			if(code==KeyEvent.VK_UP) {
				redUp=false;
			}

			if(code==KeyEvent.VK_DOWN) {
				redDown=false;
			}

			
		}
		
	}

}
