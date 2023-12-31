package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//Create a window
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this lets the window properly close when user clicks the close "x" button
		window.setResizable(false); //so we cannot resize this window
		window.setTitle("Labyrinthe sboAa");
		
		//add the GamePanel to this window
		GamePanel gamePanel = new GamePanel();
		window.setLayout(new BorderLayout());
		window.add(gamePanel,BorderLayout.SOUTH);
		
		window.pack();//cause this window to be sized to fit the preferred size and layouts of its subcomponents (=GamePanel)
		
		window.setLocationRelativeTo(null);//not specify the location of the window = the window can be displayed at the center of the screen
		window.setVisible(true);//so we can see this window

		gamePanel.setupGame();
		gamePanel.startGameThread();
	}

}