import javax.swing.JPanel;
import java.util.Random;
import java.lang.Math;

public class GamePanel extends JPanel{
	private ScorePanel scorePanel;
	
	private Mine[] mine;
    private Spaceship spaceship;
    
    private Random rand = new Random();
    
    private String startLoc;

	private int startX;
    
    public GamePanel() {
    	
    	mine = new Mine[75];
    	
    	spaceship = null;
    }
    
    public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}
    
    public void createGameEntities() {
    	spaceship = new Spaceship(this, scorePanel, 50, 450);
    	
    	for(int i=0; i<75; i++)
    		mine[i] = new Mine(this, spaceship);
    }

	public void drawUserEntities() {
		
		spaceship.draw();
		
	}
	
	public void drawObstacles() {
		for(Mine m : mine) {
			if(rand.nextInt(3) == 1) {
				startLoc = "right";
			}else {
				startLoc = "left";
			}
			
			if(startLoc.equals("right")) {
				startX = (int)(Math.random() * getWidth()+200) + getWidth();
			}else if(startLoc.equals("left")) {
				startX = (int)(Math.random() * -400-200) + 0;
			}
			
			m.setX(startX);
			m.setY(rand.nextInt(getHeight()-100));
			m.draw();
		}
	}
	
	public void generateObstacles() {
		for(Mine m : mine) {
			m.start();
		}
	}
	
	public void updateUserEntities(int direction) {
		if(spaceship != null) {
			
			spaceship.erase();
			
			spaceship.move(direction);
		}
	}
	
	public void increaseDifficulty() {
		for (Mine m : mine) {
			m.setDx(m.getDx()+1);
		}
	}
	
	public void stop() {
		for (Mine m : mine) {
			m.interrupt();
		}
		spaceship.setDx(0);
		spaceship.setDy(0);
	}
}
