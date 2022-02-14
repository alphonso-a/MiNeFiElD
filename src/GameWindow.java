import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame implements KeyListener{
    
	private ButtonPanel buttonPanel;
    private ScorePanel scorePanel;
    private GamePanel gamePanel;

    public GameWindow(){
    	setTitle("MiNeFiElD");
        setSize(600, 600);
        
        scorePanel = new ScorePanel();
        gamePanel = new GamePanel();
        buttonPanel = new ButtonPanel();
        
        scorePanel.setGamePanel(gamePanel);
        
        gamePanel.setScorePanel(scorePanel);
        
        buttonPanel.setGamePanel(gamePanel);
        buttonPanel.setScorePanel(scorePanel);
        
        gamePanel.setBackground(Color.RED);
        scorePanel.setBackground(Color.WHITE);
        
        add(buttonPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);     
        
        gamePanel.createGameEntities();
        
        gamePanel.addKeyListener(this);
        
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_UP) {
			gamePanel.updateUserEntities(1);
			gamePanel.drawUserEntities();
		}
		
		if(keyCode == KeyEvent.VK_DOWN) {
			gamePanel.updateUserEntities(2);
			gamePanel.drawUserEntities();
		}
		
		if(keyCode == KeyEvent.VK_LEFT) {
			gamePanel.updateUserEntities(3);
			gamePanel.drawUserEntities();
		}
		
		if(keyCode == KeyEvent.VK_RIGHT) {
			gamePanel.updateUserEntities(4);
			gamePanel.drawUserEntities();
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}
