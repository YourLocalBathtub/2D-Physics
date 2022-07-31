package com.main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.event.*;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static JButton enter = new JButton("Play");
	public static JButton exit = new JButton("Exit");
	
	public Main() {
		
		this.setBounds(0, 0, 300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Launch Menu");
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(100, 100, 100));
		this.setResizable(false);
		this.setFocusable(true);
		this.setVisible(true);
		
		enter.setBounds(50, 50, 200, 50);
		enter.setFont(new Font("Courier New", Font.BOLD, 15));
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new PhysicsDisplay();
				new SettingsDisplay();
				
			}
			
		});
		this.add(enter);
		
		exit.setBounds(50, 150, 200, 50);
		exit.setFont(new Font("Courier New", Font.BOLD, 15));
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(1);
				
			}
			
		});
		this.add(exit);
		
		this.setSize(301, 301);
		this.setSize(300, 300);
		
	}

	public static void main(String[] args) {
		
		new Main();
		
	}
	
}

class PhysicsDisplay extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static JLabel block = new JLabel();
	public static float block_dy = 0;
	public static float yGravity = 1;
	public static float tickSpeed = 60;
	//REMOVE IN FINAL VERSION!!!!!
	public static JLabel warning = new JLabel("BUGGY. STILL IN BETA");
	
	public static ActionListener timerTicker = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Modify Physics Variables
			block_dy += yGravity;
			
			//Update Window
			block.setLocation(block.getX(), (int) (block.getY() + block_dy));
			
			//Bounces and Other If's
			if (block.getY() + 50 >= 500) {
				
				block_dy *= -1;
				
			}
			
			if (block.getY() <= 0) {
				
				block_dy *= -1;
				
			}
			
		}
		
	};
	
	public static Timer update = new Timer((int) (1000 / tickSpeed), timerTicker);

	public PhysicsDisplay() {
		
		this.setBounds(300, 0, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("2D Physics");
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(100, 100, 100));
		this.setResizable(false);
		this.setFocusable(true);
		this.setVisible(true);
		
		block.setBounds(225, 50, 50, 50);
		block.setBackground(new Color(255, 0, 0));
		block.setOpaque(true);
		this.add(block);
		
		warning.setBounds(125, 0, 350, 50);
		warning.setFont(new Font("Courier New", Font.BOLD, 20));
		warning.setBackground(new Color(50, 50, 50));
		this.add(warning);
		
		update.start();
		
		this.setSize(501, 501);
		this.setSize(500, 500);
		
	}
	
}

class SettingsDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JButton stop = new JButton("Stop Simulation");
	public static JButton start = new JButton("Start/Resume Simulation");
	public static JButton restart = new JButton("Restart Simulation");
	public static JTextField gravity = new JTextField();
	public static JButton submitGravity = new JButton("Submit Gravity Value");
	
	public SettingsDisplay() {
		
		this.setBounds(800, 0, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("2D Physics MENU");
		this.setLayout(null);
		this.setResizable(false);
		this.setFocusable(true);
		this.setVisible(true);
		
		stop.setBounds(100, 50, 300, 50);
		stop.setFont(new Font("Courier New", Font.BOLD, 15));
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				PhysicsDisplay.update.stop();
				
			}
			
		});
		this.add(stop);
		
		start.setBounds(100, 120, 300, 50);
		start.setFont(new Font("Courier New", Font.BOLD, 15));
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				PhysicsDisplay.update.start();
				
			}
			
		});
		this.add(start);
		
		restart.setBounds(100, 190, 300, 50);
		restart.setFont(new Font("Courier New", Font.BOLD, 15));
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				PhysicsDisplay.update.stop();
				PhysicsDisplay.block_dy = 0;
				PhysicsDisplay.block.setLocation(225, 50);
				
			}
			
		});
		this.add(restart);
		
		gravity.setBounds(100, 300, 300, 50);
		this.add(gravity);
		
		submitGravity.setBounds(100, 370, 300, 50);
		submitGravity.setFont(new Font("Courier New", Font.BOLD, 15));
		submitGravity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				PhysicsDisplay.yGravity = Float.parseFloat(gravity.getText());
				
			}
			
		});
		this.add(submitGravity);
		
		this.setSize(501, 501);
		this.setSize(500, 500);
		
	}
	
}