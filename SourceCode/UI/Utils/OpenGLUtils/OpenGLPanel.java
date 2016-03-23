/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Utils.OpenGLUtils;

import javax.media.opengl.GLJPanel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.accessibility.Accessible;

/**
 *
 * @author TRL@SMU
 */
public class OpenGLPanel extends GLJPanel implements MouseMotionListener, MouseListener, FocusListener, Accessible {

    public OpenGLPanel() {
        Initialize();
    }

    private void Initialize() {
        setFocusable(true);
        addMouseListener(this);
        addFocusListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
