import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener{
	private GamePanel gamePanel;
	private ScorePanel scorePanel;
	
	private JButton startB;
	private JButton exitB;
	
	public ButtonPanel(){
		startB = new JButton("START");
		exitB = new JButton("QUIT");
		
		startB.addActionListener(this);
		exitB.addActionListener(this);
		
		add(startB);
		add(exitB);
	}
	
	
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}



	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals(startB.getText())) {
			scorePanel.startTimer();
			gamePanel.drawObstacles();
			gamePanel.drawUserEntities();
			gamePanel.generateObstacles();
			gamePanel.requestFocus();
		}else if(command.equals(exitB.getText())){
			System.exit(0);
		}
	}
	
}
