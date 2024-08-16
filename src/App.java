import com.formdev.flatlaf.FlatLightLaf;

import BusinessLogic.HormigueroBL;
import DataAccess.DTO.HormigueroDTO;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import UserInterface.Form.LoginPanel;
import UserInterface.Form.MainForm;
import UserInterface.Form.SplashScreenForm;

public class App {
    public static void main(String[] args) {
        try {
            // Configura el LookAndFeel para FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //SplashScreenForm.show();

        SwingUtilities.invokeLater(() -> {
            LoginPanel loginPanel = new LoginPanel();
            loginPanel.login();
        });

        // Crea e inicializa la ventana principal
        

        
    }
}
