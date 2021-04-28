import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Fire{
	private int x, y;

	
	public Fire(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}

public class Game extends JPanel implements KeyListener ,ActionListener{
	
	Timer timer = new Timer(5,this);//delay , actionListener

	private int count_time=0 ,count_fire=0 ;
	private BufferedImage image;
	
	private ArrayList<Fire> fire = new ArrayList<>();
	
	private int fireDir = 1;
	
	private int ballX = 0; 
	
	private int ballDirX = 2;
	
	private int shipX = 0;
	
	private int  shipDirX = 20; 
	
	
	public boolean Check() {
		
		for (Fire fires : fire) {
			
			
			if (new Rectangle(fires.getX(),fires.getY(),10,20).intersects(new Rectangle(ballX,0,20,20))) {
				
				return true;
			
			}
			
			
			
		}
		return false;
	}
	
	
	public Game() {
		
		try {
			image = ImageIO.read(new FileImageInputStream(new File("space.png")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(Color.BLACK);
		
		timer.start();//5ms 
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		count_time +=5; // +5 ms 
		g.setColor(Color.red);
		
		g.fillOval(ballX, 0, 20, 20);
		
		g.drawImage(image, shipX,490, image.getWidth()/60, image.getHeight()/80 ,this); 
		
		
	
		for (Fire fires : fire) {
				
			if (fires.getY()<=0) {
				
				fire.remove(fires);
			}
		}
		
		g.setColor(Color.BLUE);
		
		for (Fire fires : fire) {
			
			g.fillRect(fires.getX(), fires.getY(), 10, 20);
		}
		if (Check()) {
			
			timer.stop();
			String msg = "Kazandýn bravo....!!\n"
					+ "haracanan Ateþ : " + count_fire
					+"\nGeçen Süre:  "+count_time/1000.0+"/sn";
			
			JOptionPane.showMessageDialog(this, msg);
			
			System.exit(0);//game end
		}
		
		
	}
	
	@Override
	public void repaint() {
		super.repaint();
		
		
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (Fire fires : fire) {
			fires.setY(fires.getY() -fireDir);
			
		} 
		
		ballX += ballDirX;
		
		if (ballX>=760) {
			ballDirX = -ballDirX;  
		}
		if (ballX==0) {
			ballDirX = -ballDirX;
		}
		
		repaint(); 

	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int c = e.getKeyCode();
		
		if (c==KeyEvent.VK_LEFT) {
			if (shipX <= 0) {
				shipX =0;
			}else
				shipX -= shipDirX; 
			
			
		}else if (c== KeyEvent.VK_RIGHT) {
			
			if (shipX >=740) {
				shipX= 740;
			}else
				shipX += shipDirX;
			
		}else if (c == KeyEvent.VK_CONTROL) {
			fire.add(new Fire(shipX+15,480)); // We've added 15 more units to fit in the middle
			
			count_fire++;
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}


}
