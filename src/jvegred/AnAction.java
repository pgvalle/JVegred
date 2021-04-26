package jvegred;

import javax.swing.*;
import java.awt.event.*;
import java.beans.*;

/**
 * Just for convenience :)
 */
public class AnAction implements Action {

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public void setEnabled(boolean enabled) {}

    @Override
    public void putValue(String s, Object o) {}
    
    @Override
    public Object getValue(String s) { return "AnAction"; }
}