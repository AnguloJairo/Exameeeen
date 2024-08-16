import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import UserInterface.Form.AJLoginPanel;
import UserInterface.Form.SplashScreenForm;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SplashScreenForm.show();

        SwingUtilities.invokeLater(() -> {
            AJLoginPanel loginPanel = new AJLoginPanel();
            loginPanel.login();
        });
        

        
    }
}
