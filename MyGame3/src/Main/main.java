package Main;

import javax.swing.JFrame;

public class main {
	public static void main(String[] args) {
		JFrame window=new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
        window.setTitle("game of mustafa");
        
        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);
        window.pack();// jpaneli jframe otomatik ayarlamayı sağlıyor
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
        
	}

}
