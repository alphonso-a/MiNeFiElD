import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Mine extends Thread{
	
	private JPanel panel;
	
	private Spaceship spaceship;
	
	private int width=5, height=5;
	
	private int x=-100, y;
	
	private Ellipse2D.Double circle;
	
	private int dx;
	
	private String loc;
	
	public Mine(JPanel gPanel, Spaceship spaceship) {
		
		panel = gPanel;
		
		this.spaceship = spaceship;
		
		dx = 6;
	}
	
	public void draw() {
		Graphics g = panel.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		circle = new Ellipse2D.Double(x, y, width, height);
		
		g2.setColor(Color.ORANGE);
		g2.fill(circle);
		
		g.dispose();
	}
	
	
	public void move(String direction) {
		if(direction.equals("right")) {
			x += dx;
		}else if(direction.equals("left")) {
			x -= dx;
		}
		
		if(detectCollision()) {
			spaceship.erase();
			spaceship.setDefaultLoc();
			spaceship.draw();
		}
	}
	
	public void erase() {
		Graphics g = panel.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(panel.getBackground());
		g2.fill(new Rectangle2D.Double(x, y, width, height));
		
		g.dispose();
	}
	
	public void run() {
		try {
			for(int i = 1; i <= 5000; i++) {
				erase();
				
				if(x < 0) {
					loc="right";
				}else if((x > panel.getWidth())) {
					loc="left";
				}
				move(loc);
				
				draw();
				sleep(60);
			}
		}catch(InterruptedException e) {
			
		}
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle2D.Double getBoundingRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
	
	public boolean detectCollision() {
    	return getBoundingRectangle().intersects(spaceship.getBoundingRectangle());
    }
}
