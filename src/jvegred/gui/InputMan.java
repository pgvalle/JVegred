package jvegred.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.MouseInfo;

import java.awt.Color;

import jvegred.figures.*;

public class InputMan implements KeyListener, MouseInputListener {

	// keybindings flags
	private boolean ctrlPressed = false;
	private boolean escPressed = false;
	
	// mousebindings flags
	private boolean rButtonPressed = false;
	private boolean rButtonReleased = false;
	private boolean rButtonClicked = false;
	private boolean rButtonDragged = false;

	private boolean lButtonPressed = false;
	private boolean lButtonReleased = false;
	private boolean lButtonClicked = false;
	private boolean lButtonDragged = false;


	public InputMan() {}
	
	// unused functions from MouseInputListener
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}


	public void keyPressed(KeyEvent e) {
		int kcode = e.getKeyCode();
		char kchar = e.getKeyChar();

		if (kcode == KeyEvent.VK_CONTROL) {
			this.ctrlPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int kcode = e.getKeyCode();
		char kchar = e.getKeyChar();

		if (kcode == KeyEvent.VK_CONTROL) {
			this.ctrlPressed = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		int x = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX() - 25;
		int y = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY() - 25;
		
		switch(e.getKeyChar()) {
			case 'e':
				FigureMan.addFigureToList(new Ellipse(x, y, 75, 75, Color.WHITE, Color.BLACK, 3));
				break;
			case 'r':
				FigureMan.addFigureToList(new Rectangle(x, y, 75, 75, Color.WHITE, Color.BLACK, 3));
				break;
			case 't':
				FigureMan.addFigureToList(new Triangle(x, y, 75, 75, Color.WHITE, Color.BLACK, 3));
				break;
			case 'd':
				FigureMan.deleteFiguresOnFocus();
				break;
			case 'q':
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

		FigureMan.focusFigureAt(x, y, false);

		e.getComponent().repaint();
	}

	public void mouseDragged(MouseEvent e) {

	}
}
