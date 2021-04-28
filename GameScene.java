import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameScene extends JFrame{
	
	
	
	public GameScene(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String[] args) {
		GameScene ekran = new GameScene("Uzay Oyunu");

		ekran.setResizable(false);
		ekran.setFocusable(false);
		ekran.setSize(800,600);
		ekran.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Game oyun= new Game();
		oyun.requestFocus(); 
		
		oyun.addKeyListener(oyun);
		
		oyun.setFocusable(true);
		oyun.setFocusTraversalKeysEnabled(false);//klavye iþlemleri için
		
		ekran.add(oyun);
		
		ekran.setVisible(true);
		
		
		
		
	}
	

}
