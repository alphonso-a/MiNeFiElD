import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import javax.swing.JPanel;

public class Spaceship {
    private JPanel panel;
    private ScorePanel scorePanel;

    private Ellipse2D.Double outside, inside;
    private Rectangle2D.Double cannon;

    private int width = 25, height = 25;
    
    private int defaultX, defaultY;
    
    private int outsideX, outsideY;
    
    private int insideX, insideY;
    
    private int cannonX, cannonY;
    
    private int dx, dy;

    public Spaceship(JPanel gPanel, ScorePanel scorePanel, int xPos, int yPos){
        panel = gPanel;
        this.scorePanel = scorePanel;
        
        defaultX = xPos; 
        defaultY = yPos;

        setDefaultLoc();

        dx = 10; 
        dy = 10;
        
    }
    
    public void setDefaultLoc() {
    	outsideX = defaultX; 
        outsideY = defaultY;

        insideX = outsideX + (width/4);
        insideY = outsideY + (height/4);
        
        cannonX = insideX + (width/5);
        cannonY = insideY - (height/2);
    }
    
    public void setLoc(int x, int y) {
    	outsideX = x; 
        outsideY = y;

        insideX = outsideX + (width/4);
        insideY = outsideY + (height/4);
        
        cannonX = insideX + (width/5);
        cannonY = insideY - (height/2);
    }

    public void draw(){
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        outside = new Ellipse2D.Double(outsideX, outsideY, width, height);
        g2.setColor(Color.WHITE);
        g2.fill(outside);
        
        inside = new Ellipse2D.Double(insideX, insideY, width/2, height/2);
        g2.setColor(Color.BLACK);
        g2.fill(inside);
        
        cannon = new Rectangle2D.Double(cannonX, cannonY, 2.5, 15);
        g2.fill(cannon);
        
        g.dispose();
    }
    
    public void move(int direction) {
    	if (direction == 1) {
    		outsideY -= dy;
    		insideY -= dy;
    		cannonY -= dy;
    	}else if(direction == 2) {
    		outsideY += dy;
    		insideY += dy;
    		cannonY += dy;
    		
    		if((outsideY + height) > panel.getHeight()) {
    			setLoc(outsideX, panel.getHeight()-height);
    		}
    	}else if(direction == 3) {
    		outsideX -= dx;
    		insideX -= dx;
    		cannonX -= dx;
    		
    		if(outsideX < 0) {
    			setLoc(0, outsideY);
    		}
    	}else if(direction == 4) {
    		outsideX += dx;
    		insideX += dx;
    		cannonX += dx;
    		
    		if((outsideX + width) > panel.getWidth()) {
    			setLoc(panel.getWidth()-width, outsideY);
    		}
    	}
    	
    	if(outsideY < 0) {
    		erase();
			setDefaultLoc();
			draw();
			scorePanel.increaseScore();
    	}
    }
    
    public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void erase() {
    	Graphics g = panel.getGraphics();
    	Graphics2D g2 = (Graphics2D) g;
    	
    	g2.setColor(panel.getBackground());
    	g2.fill(new Rectangle2D.Double(outsideX, cannonY, width, (outsideY-cannonY)+height));
    	
    	g.dispose();
    }
    
    public Rectangle2D.Double getBoundingRectangle() {
		return new Rectangle2D.Double(outsideX, outsideY, width, height);
		
	}
}
