package jvegred.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import jvegred.figures.FigureMan;

public class Frame extends JFrame {

	public Frame(String title, int width, int height) {
		// really basic setup
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setSize(width, height);
		
		// adding listener properly
		InputMan listener = new InputMan();
		
		this.addKeyListener(listener);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// repainting background is necessary to focus feature to work
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		FigureMan.drawFigures(g2d);
	}
}
