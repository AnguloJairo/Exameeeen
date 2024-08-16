package UserInterface.Form;

import java.awt.*;
import javax.swing.*;

public class MainForm extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel centerPanel = new JPanel(new BorderLayout()); // Panel con BorderLayout

    public MainForm(String titleApp) {
        customizeComponent(titleApp);
        
        // Configura el panel central solo con el AJHormigaPanel
        centerPanel.add(new AJHormigaPanel(), BorderLayout.CENTER);

        //MenuPanel menuPanel = new MenuPanel();
        AJEcuFaunaPanel frmMain = new AJEcuFaunaPanel();

        // Configuración del JFrame
        setTitle(titleApp);
        setSize(1000, 600); // Tamaño ajustado para incluir el panel con scroll y el panel central
        setResizable(true);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //add(menuPanel, BorderLayout.NORTH);
        add(frmMain, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void customizeComponent(String titleApp) {
        setTitle(titleApp);
        setSize(1200, 800);
        setResizable(true);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

