package jvegred.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import jvegred.figures.FigureManager;

public class Frame extends JFrame {

	// só pro vscode parar de reclamar
	private static final long serialVersionUID = 1L;

	public Frame(String title, int width, int height) {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setSize(width, height);
		
		InputListener listener = new InputListener();
		
		this.addKeyListener(listener);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// repainting background (necessary to focus feature to work)
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		FigureManager.paintFigures(g2d);
	}
}
