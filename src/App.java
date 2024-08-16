import com.formdev.flatlaf.FlatLightLaf;

import BusinessLogic.HormigueroBL;
import DataAccess.DTO.HormigueroDTO;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import UserInterface.Form.MainForm;

public class App {
    public static void main(String[] args) {
        try {
            // Configura el LookAndFeel para FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Crea e inicializa la ventana principal
        MainForm frmMain = new MainForm("EcuFauna");

        

        // try {
        //     HormigueroBL ecBL = new HormigueroBL();
        //     for (HormigueroDTO reg : ecBL.getAll()) {
        //         System.out.println(reg.toString());
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace(); // Muestra el error en la consola
        // }
        
    }
}
