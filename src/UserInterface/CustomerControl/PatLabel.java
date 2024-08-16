package UserInterface.CustomerControl;

import UserInterface.AJEstilo;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class PatLabel extends JLabel{
    public PatLabel(){
        customizeComponent();
    }
    public PatLabel(String text){
        setText(text);
        customizeComponent();
    }
    private void customizeComponent(){
        setCustomizeComponent(getText(), AJEstilo.FONT, AJEstilo.COLOR_FONT_LIGHT, AJEstilo.ALIGNMENT_LEFT);
    }
    public void setCustomizeComponent(String text, Font  font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);
        //setIcon(new ImageIcon(iconPath));
    }
}