package jvegred.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.MouseInfo;

import java.awt.Color;

import jvegred.figures.*;

public class InputListener implements KeyListener, MouseInputListener {	

	public InputListener() {}

	// unused functions from keyListener
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	// unused functions from MouseInputListener
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}


	public void keyTyped(KeyEvent e) {
		int x = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX() - 25;
		int y = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY() - 25;
		
		switch(e.getKeyChar()) {
			case 'e':
				FigureManager.addFigureToList(new Ellipse(x, y, Color.WHITE, Color.BLACK, 3));
				break;
			case 'r':
				FigureManager.addFigureToList(new Rectangle(x, y, Color.WHITE, Color.BLACK, 3));
				break;
			case 't':
				FigureManager.addFigureToList(new Triangle(x, y, Color.WHITE, Color.BLACK, 3));
				break;
			case 'd':
				FigureManager.deleteFigureOnFocus();
				break;
			default:
				break;
		}

		e.getComponent().repaint();
	}


	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getXOnScreen() - e.getComponent().getX();
		int y = e.getYOnScreen() - e.getComponent().getY();

		FigureManager.focusFigureAt(x, y);

		e.getComponent().repaint();
	}

	public void mouseDragged(MouseEvent e) {

	}
}
