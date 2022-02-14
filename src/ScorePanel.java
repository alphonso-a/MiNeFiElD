import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;


public class ScorePanel extends JPanel {
	GamePanel gamePanel;
	
	JLabel scoreLabel;
	JLabel timerLabel;
	
	private int score=0;;
	
	private int seconds, minutes;
	private String ddSeconds, ddMinutes;
	
	DecimalFormat dFormat = new DecimalFormat("00");
	
	Timer timer;
	
	public ScorePanel() {
		this.setLayout(new GridLayout());
		
		scoreLabel = new JLabel("Score: " + score);
		timerLabel = new JLabel("");
		
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.add(scoreLabel);
		this.add(timerLabel);
		
		timerLabel.setText("Time remaining: 02:00");
		
		seconds=0;
		minutes=2;
		
		countdownTimer();
	}
	
	public void countdownTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconds--;
				
				ddSeconds = dFormat.format(seconds);
				ddMinutes = dFormat.format(minutes);
				
				timerLabel.setText("Time remaining: " + ddMinutes + ":" + ddSeconds);
				
				if(seconds==-1) {
					seconds=59;
					minutes--;
					
					ddSeconds = dFormat.format(seconds);
					ddMinutes = dFormat.format(minutes);
					
					timerLabel.setText("Time remaining: " + ddMinutes + ":" + ddSeconds);
				}
				
				if(minutes==0 && seconds==0) {
					stopTimer();
					gamePanel.stop();
				}
			}
			
		});
	}
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void increaseScore() {
		score++;
		
		scoreLabel.setText("Score: " + score);
		
		gamePanel.increaseDifficulty();
	}
}
