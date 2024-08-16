package UserInterface.Form;

import UserInterface.AJEstilo;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AJEcuFaunaPanel extends JPanel {

    // Variables globales estáticas
    public static final String CEDULA = "1751962000";
    public static final String NOMBRE = "Angulo Jairo";

    private JTextField cedulaField;
    private JTextField nombreField;

    public AJEcuFaunaPanel() {
        setLayout(new BorderLayout());

        // Panel superior con borde y campos centrados
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder(
                AJEstilo.createBorderRect(), "Información del Alumno",
                TitledBorder.CENTER, TitledBorder.TOP, AJEstilo.FONT_BOLD,
                AJEstilo.COLOR_FONT
        ));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel cedulaLabel = new JLabel("Cédula del alumno:");
        cedulaLabel.setFont(AJEstilo.FONT);
        cedulaLabel.setForeground(AJEstilo.COLOR_FONT);
        inputPanel.add(cedulaLabel);

        cedulaField = new JTextField(CEDULA); // Valor por defecto
        cedulaField.setFont(AJEstilo.FONT);
        cedulaField.setForeground(AJEstilo.COLOR_FONT);
        cedulaField.setBorder(AJEstilo.createBorderRect());
        inputPanel.add(cedulaField);

        JLabel nombreLabel = new JLabel("Nombre del alumno:");
        nombreLabel.setFont(AJEstilo.FONT);
        nombreLabel.setForeground(AJEstilo.COLOR_FONT);
        inputPanel.add(nombreLabel);

        nombreField = new JTextField(NOMBRE); // Valor por defecto
        nombreField.setFont(AJEstilo.FONT);
        nombreField.setForeground(AJEstilo.COLOR_FONT);
        nombreField.setBorder(AJEstilo.createBorderRect());
        inputPanel.add(nombreField);

        topPanel.add(inputPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
    }
}
