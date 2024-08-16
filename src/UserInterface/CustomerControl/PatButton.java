package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PatButton extends JButton implements MouseListener {
    public PatButton(String text) {
        super(text);
        customizeComponent();
    }

    public PatButton(String text, String iconPath) {
        super(text);
        customizeComponent(iconPath);
    }

    private void customizeComponent() {
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(this);
    }

    private void customizeComponent(String iconPath) {
        customizeComponent();
        setIcon(new ImageIcon(iconPath));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Optional: Handle mouse clicks here
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Optional: Handle mouse presses here
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Optional: Handle mouse releases here
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.BLACK);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.GRAY);
    }
}
