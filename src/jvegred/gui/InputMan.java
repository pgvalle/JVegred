package jvegred.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.event.MouseInputListener;

import jvegred.figures.*;

public class InputMan implements KeyListener, MouseInputListener {

	private boolean controlPressed = false;
	private boolean selectionPressed = false;

	private int mx, my;
	
	public InputMan() {}
	
	// unused functions from superinterfaces
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.controlPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.controlPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			FigureMan.setAllFiguresFocus(false);
			e.getComponent().repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			FigureMan.setAllFiguresFocus(true);
			e.getComponent().repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			FigureMan.deleteFocusedFigures();
			e.getComponent().repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int mx = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX();
		int my = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY();
		
		if (e.getKeyChar() == 'e') {
			FigureMan.addFigureToList(new Elipse(mx, my, 50, 50, Color.WHITE, Color.BLACK, 5));
		} else if (e.getKeyChar() == 't') {
			FigureMan.addFigureToList(new Texto(mx, my, 100, 100, Color.BLACK, "Mansabling"));
		}

		e.getComponent().repaint();
	}


	@Override
	public void mousePressed(MouseEvent e) {	
		this.mx = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX();
		this.my = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY();

		if (FigureMan.focusedFiguresContain(this.mx, this.my)) {
			this.selectionPressed = true;
		} else if (!this.controlPressed) {
			int findex = FigureMan.unfocusedFiguresContain(this.mx, this.my);
			
			if (findex != -1) {
				FigureMan.setAllFiguresFocus(false);
				FigureMan.focusFigureAt(findex);
				this.selectionPressed = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mx = 0;
		this.my = 0;

		this.selectionPressed = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX();
		int my = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY();

		FigureMan.focusFigureAt(mx, my, this.controlPressed);

		e.getComponent().repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int mx = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX();
		int my = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY();
		
		if (this.selectionPressed) {
			FigureMan.dragFocusedFigures(mx - this.mx, my - this.my);

			this.mx = mx;
			this.my = my;
		}

		e.getComponent().repaint();
	}
}
