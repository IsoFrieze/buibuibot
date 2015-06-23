package com.dotsarecool.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ChatWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	BufferedImage bufferedImage;
	
	Graphics2D g2d;
	
	int WIDTH = 800;
	int HEIGHT = 600;
	int MAX_MESSAGE = 20;
	int MAX_CHAR = 40;
	
	int LINE_SPACE = 30;
	int INDENT = 300;
	
	ArrayList<Tuple<String,String>> messages;
	
	public ChatWindow() {
		super("BuiBuiBot");		
		bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		g2d = bufferedImage.createGraphics();
		messages = new ArrayList<>();
		addMessage("","Starting up chat...");

		setIconImage((new ImageIcon("C:\\Users\\Alex\\Pictures\\reznor.ico")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		g2d.setBackground(Color.BLUE);
		g2d.clearRect(0,0,WIDTH,HEIGHT);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		
		int y = HEIGHT - LINE_SPACE;
		
		for (int i = messages.size() - 1; i >= 0; i--) {
			ArrayList<String> message = new ArrayList<>();
			String [] words = messages.get(i).b.split(" ");
			String line = "";
			
			for (int j = 0; j < words.length; j++) {
				if (line.length() + words[j].length() > MAX_CHAR) {
					if (line.length() == 0) {
						message.add(words[j]);
						continue;
					} else {
						message.add(line);
						line = "";
					}
				}
				line = line + " " + words[j];
			}
			message.add(line);
			
			for (int j = message.size() - 1; j >= 0; j--) {
				g2d.drawString(message.get(j), INDENT, y);
				y -= LINE_SPACE;
			}
			int w = g2d.getFontMetrics().stringWidth(messages.get(i).a);
			g2d.drawString(messages.get(i).a, INDENT - w - 10, y + LINE_SPACE);
		}
		
		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.drawImage(bufferedImage,0,0,null);
	}
	
	public void gameLoop() {
		
	}
	
	public void addMessage(String user, String text) {
		messages.add(new Tuple<String,String>(user, text));
		if (messages.size() > MAX_MESSAGE) {
			messages.remove(0);
		}
		
		repaint();
	}
	
}