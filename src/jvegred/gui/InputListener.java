package jvegred.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.MouseInfo;

import java.awt.Color;

import jvegred.figures.*;

public class InputListener implements KeyListener, MouseInputListener {	
	
	private Frame parent;

	public InputListener(Frame parent) {
		this.parent = parent;
	}

	// unused functions from keyListener
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	// unused functions from MouseInputListener
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}


	public void keyTyped(KeyEvent e) {
		int x = MouseInfo.getPointerInfo().getLocation().x - parent.getX() - 25;
		int y = MouseInfo.getPointerInfo().getLocation().y - parent.getY() - 25;
		
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
				System.out.println(e.getKeyChar());
				break;
		}

		parent.repaint();
	}


	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		System.out.format("%d %d\n", e.getXOnScreen() - this.parent.getLocation().x, + e.getYOnScreen() - this.parent.getLocation().y);	
		FigureManager.focusFigureAt(e.getXOnScreen() - this.parent.getLocation().x, e.getYOnScreen() - this.parent.getLocation().y);
		parent.repaint();
	}

	public void mouseDragged(MouseEvent e) {

	}
}
