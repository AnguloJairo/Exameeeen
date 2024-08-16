
// package UserInterface.Form;

// import BusinessLogic.HormigueroBL;
// import DataAccess.DTO.HormigueroDTO;
// import UserInterface.AJEstilo;
// import UserInterface.CustomerControl.PatButton;
// import UserInterface.CustomerControl.PatLabel;
// import UserInterface.CustomerControl.PatTextBox;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.util.List;
// import javax.swing.*;

// public class AJHormigaPanel extends JPanel implements ActionListener {
//     private Integer rowNum = 0, idRowMaxHormiguero = 0;
//     private HormigueroBL hormigueroBL = new HormigueroBL();
//     private HormigueroDTO hormigueroDAO = null;

//     public AJHormigaPanel() {
//         try {
//             customizeComponent();
//             loadRow();
//             showRow();
//             showTable();

//             btnRowIni.addActionListener(this);
//             btnRowAnt.addActionListener(this);
//             btnRowSig.addActionListener(this);
//             btnRowFin.addActionListener(this);
            
//             btnNuevo.addActionListener(e -> btnNuevoClick());
//             //btnGuardar.addActionListener(e -> btnGuardarClick());
//             btnEliminar.addActionListener(e -> btnEliminarClick());
//             btnCancelar.addActionListener(e -> btnCancelarClick());
//         } catch (Exception e) {
//             AJEstilo.showMsg(e.getMessage());
//         }
//     }

//     private void loadRow() {
//         try {
//             rowNum = 1;
//             hormigueroDAO = hormigueroBL.getBy(rowNum);
//             idRowMaxHormiguero = hormigueroBL.getRowCount();
//             System.out.println("Loaded row: " + rowNum);
//         } catch (Exception e) {
//             AJEstilo.showMsg(e.getMessage());
//         }
//     }
    
//     private void showRow() {
//         boolean hormigueroNull = (hormigueroDAO == null);
//         txtrowNum.setText((hormigueroNull || hormigueroDAO.getId() == null) ? " " : hormigueroDAO.getId().toString());
//         txtNombre.setText((hormigueroNull) ? " " : hormigueroDAO.getTipoHormiga());
//         lblTotalReg.setText(rowNum.toString() + " de " + idRowMaxHormiguero.toString());
//     }
    

//     private void btnNuevoClick() {
//         JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
    
//         JTextField txtTipoHormiga = new JTextField();
//         JTextField txtSexoId = new JTextField();
//         JTextField txtProvinciaId = new JTextField();
//         JTextField txtGenoAlimentoId = new JTextField();
//         JTextField txtIngestaNativaId = new JTextField();
//         JTextField txtEstadoHorm = new JTextField();
//         JTextField txtEstado = new JTextField();
    
//         panel.add(new JLabel("Tipo Hormiga:"));
//         panel.add(txtTipoHormiga);
//         panel.add(new JLabel("Sexo ID:"));
//         panel.add(txtSexoId);
//         panel.add(new JLabel("Provincia ID:"));
//         panel.add(txtProvinciaId);
//         panel.add(new JLabel("Geno Alimento ID:"));
//         panel.add(txtGenoAlimentoId);
//         panel.add(new JLabel("Ingesta Nativa ID:"));
//         panel.add(txtIngestaNativaId);
//         panel.add(new JLabel("Estado Horm:"));
//         panel.add(txtEstadoHorm);
//         panel.add(new JLabel("Estado:"));
//         panel.add(txtEstado);
    
//         int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese los datos de la nueva hormiga", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
//         if (result == JOptionPane.OK_OPTION) {
//             try {
//                 String tipoHormiga = txtTipoHormiga.getText().trim();
//                 Integer sexoId = Integer.parseInt(txtSexoId.getText().trim());
//                 Integer provinciaId = Integer.parseInt(txtProvinciaId.getText().trim());
//                 Integer genoAlimentoId = Integer.parseInt(txtGenoAlimentoId.getText().trim());
//                 Integer ingestaNativaId = Integer.parseInt(txtIngestaNativaId.getText().trim());
//                 String estadoHorm = txtEstadoHorm.getText().trim();
//                 String estado = txtEstado.getText().trim();
    
//                 // Validation
//                 if (tipoHormiga.isEmpty() || estadoHorm.isEmpty() || estado.isEmpty()) {
//                     JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.");
//                     return;
//                 }
    
//                 HormigueroDTO nuevaHormiga = new HormigueroDTO();
//                 nuevaHormiga.setTipoHormiga(tipoHormiga);
//                 nuevaHormiga.setSexoId(sexoId);
//                 nuevaHormiga.setProvinciaId(provinciaId);
//                 nuevaHormiga.setGenoAlimentoId(genoAlimentoId);
//                 nuevaHormiga.setIngestaNativaId(ingestaNativaId);
//                 nuevaHormiga.setEstadoHorm(estadoHorm);
//                 nuevaHormiga.setEstado(estado);
    
//                 if (hormigueroBL.add(nuevaHormiga)) {
//                     JOptionPane.showMessageDialog(this, "Hormiga añadida exitosamente.");
//                     loadRow();
//                     showRow();
//                     showTable();
//                 } else {
//                     JOptionPane.showMessageDialog(this, "Error al añadir la hormiga.");
//                 }
//             } catch (NumberFormatException ex) {
//                 JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para los IDs.");
//             } catch (Exception e) {
//                 AJEstilo.showMsg(e.getMessage());
//             }
//         }
//     }
//     private void btnCancelarClick() {
//         try {
//             if (hormigueroDAO == null)
//                 loadRow();
//             showRow();
//         } catch (Exception e) {}
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == btnRowIni)
//             rowNum = 1;
//         if (e.getSource() == btnRowAnt && (rowNum > 1))
//             rowNum--;
//         if (e.getSource() == btnRowSig && (rowNum < idRowMaxHormiguero))
//             rowNum++;
//         if (e.getSource() == btnRowFin)
//             rowNum = idRowMaxHormiguero;
//         try {
//             hormigueroDAO = hormigueroBL.getBy(rowNum);
//             showRow(); 
//         } catch (Exception ex) {}
//     }
//     private void btnEliminarClick() {
//         try {
//             // Verifica si el registro a eliminar está cargado y es válido
//             if (hormigueroDAO == null || hormigueroDAO.getId() == null) {
//                 AJEstilo.showMsgError("No hay un registro válido para eliminar.");
//                 return;
//             }
    
//             // Muestra el ID del registro que se va a eliminar para depuración
//             System.out.println("Eliminando registro con ID: " + hormigueroDAO.getId());
    
//             // Confirmación antes de eliminar
//             if (AJEstilo.showConfirmYesNo("¿Seguro que desea eliminar?")) {
//                 boolean success = hormigueroBL.delete(hormigueroDAO.getId());
//                 if (success) {
//                     JOptionPane.showMessageDialog(this, "Hormiga eliminada exitosamente.");
//                     rowNum = Math.max(1, rowNum - 1); // Ajusta el número de fila
//                     loadRow(); // Carga la fila actualizada
//                     showRow(); // Muestra la fila actualizada
//                     showTable(); // Muestra la tabla actualizada
//                 } else {
//                     throw new Exception("Error al eliminar la hormiga.");
//                 }
//             }
//         } catch (Exception e) {
//             AJEstilo.showMsgError(e.getMessage());
//         }
//     }
    
    
    
    
    
//     private void showTable() throws Exception {
//         String[] header = {"RowNum", "Id", "Tipo Hormiga", "Sexo ID", "Provincia ID", "Geno Alimento ID", "Ingesta Nativa ID", "Estado Horm", "Estado"};
        
//         List<HormigueroDTO> hormigueroList = hormigueroBL.getAll();
//         if (hormigueroList == null || hormigueroList.isEmpty()) {
//             pnlTabla.removeAll();
//             pnlTabla.add(new JLabel("No data available."));
//             pnlTabla.revalidate();
//             pnlTabla.repaint();
//             return;
//         }
        
//         Object[][] data = new Object[hormigueroList.size()][header.length];
//         int index = 0;
//         for (HormigueroDTO h : hormigueroList) {
//             data[index][0] = h.getRowNum();
//             data[index][1] = h.getId();
//             data[index][2] = h.getTipoHormiga();
//             data[index][3] = h.getSexoId();
//             data[index][4] = h.getProvinciaId();
//             data[index][5] = h.getGenoAlimentoId();
//             data[index][6] = h.getIngestaNativaId();
//             data[index][7] = h.getEstadoHorm();
//             data[index][8] = h.getEstado();
//             index++;
//         }
        
//         JTable table = new JTable(data, header);
//         table.setShowHorizontalLines(true);
//         table.setGridColor(Color.lightGray);
//         table.setRowSelectionAllowed(true);
//         table.setColumnSelectionAllowed(false);
        
//         table.setPreferredScrollableViewportSize(new Dimension(1200, 300));
//         table.setFillsViewportHeight(true);
        
//         pnlTabla.removeAll();
//         pnlTabla.setLayout(new BorderLayout());
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setPreferredSize(new Dimension(1200, 300));
//         pnlTabla.add(scrollPane, BorderLayout.CENTER);
//         pnlTabla.revalidate();
//         pnlTabla.repaint();
        
//         table.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 int row = table.rowAtPoint(e.getPoint());
//                 if (row >= 0) {
//                     rowNum = (Integer) table.getModel().getValueAt(row, 0);
//                     try {
//                         hormigueroDAO = hormigueroBL.getBy(rowNum);
//                         showRow();
//                     } catch (Exception ignored) {
//                     }
//                 }
//             }
//         });
//     }
    
    
    
    

// /************************
//  * FormDesing : pat_mic
//  ************************/ 
//     private PatLabel 
//             lblTitulo   = new PatLabel("Hormiguero Virtual"),
//             lblrowNum   = new PatLabel(" Codigo:      "),
//             lblNombre   = new PatLabel("*Descripción: "),
//             lblTotalReg = new PatLabel(" 0 de 0 ");
//     private PatTextBox 
//             txtrowNum   = new PatTextBox(),
//             txtNombre   = new PatTextBox();
//     private PatButton 
//             btnPageIni  = new PatButton(" |< "),
//             btnPageAnt  = new PatButton(" << "),
//             btnPageSig  = new PatButton(" >> "),
//             btnPageFin  = new PatButton(" >| "),

//             btnRowIni   = new PatButton(" |< "),
//             btnRowAnt   = new PatButton(" << "),
//             btnRowSig   = new PatButton(" >> "),
//             btnRowFin   = new PatButton(" >| "),

//             btnNuevo    = new PatButton("Nuevo"),
//             btnGuardar  = new PatButton("Guardar"),
//             btnCancelar = new PatButton("Cancelar"),
//             btnEliminar = new PatButton("Eliminar");
//     private JPanel 
//             pnlTabla    = new JPanel(),
//             pnlBtnRow   = new JPanel(new FlowLayout()),
//             pnlBtnPage  = new JPanel(new FlowLayout()),
//             pnlBtnCRUD  = new JPanel(new FlowLayout());
            
//             public void customizeComponent() {
//                 setLayout(new GridBagLayout());
//                 GridBagConstraints gbc = new GridBagConstraints();
            
//                 txtrowNum.setEnabled(false);
//                 txtrowNum.setBorderLine();
//                 txtNombre.setBorderLine();
            
//                 // Center the buttons in pnlBtnCRUD
//                 pnlBtnCRUD.setLayout(new FlowLayout(FlowLayout.CENTER));
//                 pnlBtnCRUD.add(btnNuevo);
//                 pnlBtnCRUD.add(btnGuardar);
//                 pnlBtnCRUD.add(btnCancelar);
//                 pnlBtnCRUD.add(btnEliminar);
//                 pnlBtnCRUD.setBorder(AJEstilo.createBorderRect());
            
//                 gbc.insets = new Insets(5, 5, 5, 5);
            
//                 gbc.gridy = 0;
//                 gbc.gridx = 0;
//                 gbc.gridwidth = 2;
//                 gbc.anchor = GridBagConstraints.CENTER;
//                 add(lblTitulo, gbc);
            
//                 gbc.gridy = 1;
//                 gbc.gridx = 0;
//                 gbc.gridwidth = 2;
//                 gbc.fill = GridBagConstraints.BOTH;
//                 gbc.ipady = 150;
//                 gbc.ipadx = 1200; // Increase width as needed
//                 pnlTabla.add(new JLabel("Loading data..."));
//                 add(pnlTabla, gbc);
            
//                 gbc.fill = GridBagConstraints.NONE;
//                 gbc.ipady = 0;
//                 gbc.ipadx = 0;
//                 gbc.gridy = 2;
//                 gbc.gridx = 0;
//                 gbc.gridwidth = 2;
//                 gbc.anchor = GridBagConstraints.CENTER;
//                 add(pnlBtnCRUD, gbc);
//             }      
// }

package UserInterface.Form;

import BusinessLogic.HormigueroBL;
import DataAccess.DTO.HormigueroDTO;
import UserInterface.AJEstilo;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

public class AJHormigaPanel extends JPanel implements ActionListener {
    private Integer rowNum = 0, idRowMaxHormiguero = 0;
    private HormigueroBL hormigueroBL = new HormigueroBL();
    private HormigueroDTO hormigueroDAO = null;

    public AJHormigaPanel() {
        try {
            customizeComponent();
            loadRow();
            showRow();
            showTable();

            btnRowIni.addActionListener(this);
            btnRowAnt.addActionListener(this);
            btnRowSig.addActionListener(this);
            btnRowFin.addActionListener(this);
            
            btnNuevo.addActionListener(e -> btnNuevoClick());
            //btnGuardar.addActionListener(e -> btnGuardarClick());
            btnEliminar.addActionListener(e -> btnEliminarClick());
            btnCancelar.addActionListener(e -> btnCancelarClick());
        } catch (Exception e) {
            AJEstilo.showMsg(e.getMessage());
        }
    }

    private void loadRow() {
        try {
            rowNum = 1;
            hormigueroDAO = hormigueroBL.getBy(rowNum);
            idRowMaxHormiguero = hormigueroBL.getRowCount();
            System.out.println("Loaded row: " + rowNum);
        } catch (Exception e) {
            AJEstilo.showMsg(e.getMessage());
        }
    }
    
    private void showRow() {
        boolean hormigueroNull = (hormigueroDAO == null);
        txtrowNum.setText((hormigueroNull || hormigueroDAO.getId() == null) ? " " : hormigueroDAO.getId().toString());
        txtNombre.setText((hormigueroNull) ? " " : hormigueroDAO.getTipoHormiga());
        lblTotalReg.setText(rowNum.toString() + " de " + idRowMaxHormiguero.toString());
    }
    

    private void btnNuevoClick() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
    
        JTextField txtTipoHormiga = new JTextField();
        JTextField txtSexoId = new JTextField();
        JTextField txtProvinciaId = new JTextField();
        JTextField txtGenoAlimentoId = new JTextField();
        JTextField txtIngestaNativaId = new JTextField();
        JTextField txtEstadoHorm = new JTextField();
        JTextField txtEstado = new JTextField();
    
        panel.add(new JLabel("Tipo Hormiga:"));
        panel.add(txtTipoHormiga);
        panel.add(new JLabel("Sexo ID:"));
        panel.add(txtSexoId);
        panel.add(new JLabel("Provincia ID:"));
        panel.add(txtProvinciaId);
        panel.add(new JLabel("Geno Alimento ID:"));
        panel.add(txtGenoAlimentoId);
        panel.add(new JLabel("Ingesta Nativa ID:"));
        panel.add(txtIngestaNativaId);
        panel.add(new JLabel("Estado Horm:"));
        panel.add(txtEstadoHorm);
        panel.add(new JLabel("Estado:"));
        panel.add(txtEstado);
    
        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese los datos de la nueva hormiga", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                String tipoHormiga = txtTipoHormiga.getText().trim();
                Integer sexoId = Integer.parseInt(txtSexoId.getText().trim());
                Integer provinciaId = Integer.parseInt(txtProvinciaId.getText().trim());
                Integer genoAlimentoId = Integer.parseInt(txtGenoAlimentoId.getText().trim());
                Integer ingestaNativaId = Integer.parseInt(txtIngestaNativaId.getText().trim());
                String estadoHorm = txtEstadoHorm.getText().trim();
                String estado = txtEstado.getText().trim();
    
                // Validation
                if (tipoHormiga.isEmpty() || estadoHorm.isEmpty() || estado.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.");
                    return;
                }
    
                HormigueroDTO nuevaHormiga = new HormigueroDTO();
                nuevaHormiga.setTipoHormiga(tipoHormiga);
                nuevaHormiga.setSexoId(sexoId);
                nuevaHormiga.setProvinciaId(provinciaId);
                nuevaHormiga.setGenoAlimentoId(genoAlimentoId);
                nuevaHormiga.setIngestaNativaId(ingestaNativaId);
                nuevaHormiga.setEstadoHorm(estadoHorm);
                nuevaHormiga.setEstado(estado);
    
                if (hormigueroBL.add(nuevaHormiga)) {
                    JOptionPane.showMessageDialog(this, "Hormiga añadida exitosamente.");
                    loadRow();
                    showRow();
                    showTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al añadir la hormiga.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para los IDs.");
            } catch (Exception e) {
                AJEstilo.showMsg(e.getMessage());
            }
        }
    }
    private void btnCancelarClick() {
        try {
            if (hormigueroDAO == null)
                loadRow();
            showRow();
        } catch (Exception e) {}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            rowNum = 1;
        if (e.getSource() == btnRowAnt && (rowNum > 1))
            rowNum--;
        if (e.getSource() == btnRowSig && (rowNum < idRowMaxHormiguero))
            rowNum++;
        if (e.getSource() == btnRowFin)
            rowNum = idRowMaxHormiguero;
        try {
            hormigueroDAO = hormigueroBL.getBy(rowNum);
            showRow(); 
        } catch (Exception ex) {}
    }
    private void btnEliminarClick() {
        try {
            if (hormigueroDAO == null || hormigueroDAO.getId() == null) {
                AJEstilo.showMsgError("No hay un registro válido para eliminar.");
                return;
            }
    
            if (AJEstilo.showConfirmYesNo("¿Seguro que desea eliminar?")) {
                boolean success = hormigueroBL.delete(hormigueroDAO.getId());
                if (success) {
                    JOptionPane.showMessageDialog(this, "Hormiga eliminada exitosamente.");
                    rowNum = Math.max(1, rowNum - 1); // Ajusta el número de fila
                    loadRow(); // Carga la fila actualizada
                    showRow(); // Muestra la fila actualizada
                    showTable(); // Muestra la tabla actualizada
                } else {
                    throw new Exception("Error al eliminar la hormiga.");
                }
            }
        } catch (Exception e) {
            AJEstilo.showMsgError(e.getMessage());
        }
    }
    
    
    
    private void showTable() throws Exception {
        String[] header = {"RowNum", "Id", "Tipo Hormiga", "Sexo ID", "Provincia ID", "Geno Alimento ID", "Ingesta Nativa ID", "Estado Horm", "Estado"};
    
        List<HormigueroDTO> hormigueroList = hormigueroBL.getAll();
        if (hormigueroList == null || hormigueroList.isEmpty()) {
            pnlTabla.removeAll();
            pnlTabla.add(new JLabel("No data available."));
            pnlTabla.revalidate();
            pnlTabla.repaint();
            return;
        }
    
        Object[][] data = new Object[hormigueroList.size()][header.length];
        int index = 0;
        for (HormigueroDTO h : hormigueroList) {
            data[index][0] = h.getRowNum();
            data[index][1] = h.getId();
            data[index][2] = h.getTipoHormiga();
            data[index][3] = h.getSexoId();
            data[index][4] = h.getProvinciaId();
            data[index][5] = h.getGenoAlimentoId();
            data[index][6] = h.getIngestaNativaId();
            data[index][7] = h.getEstadoHorm();
            data[index][8] = h.getEstado();
            index++;
        }
    
        JTable table = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
    
        table.setPreferredScrollableViewportSize(new Dimension(1200, 300));
        table.setFillsViewportHeight(true);
    
        pnlTabla.removeAll();
        pnlTabla.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 300));
        pnlTabla.add(scrollPane, BorderLayout.CENTER);
        pnlTabla.revalidate();
        pnlTabla.repaint();
    
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    rowNum = (Integer) table.getModel().getValueAt(row, 0);
                    try {
                        hormigueroDAO = hormigueroBL.getBy(rowNum);
                        showRow();
                    } catch (Exception ignored) {
                    }
                }
            }
        });
    }
    
    
    

/************************
 * FormDesing : pat_mic
 ************************/ 
    private PatLabel 
            lblTitulo   = new PatLabel("Hormiguero Virtual"),
            lblrowNum   = new PatLabel(" Codigo:      "),
            lblNombre   = new PatLabel("*Descripción: "),
            lblTotalReg = new PatLabel(" 0 de 0 ");
    private PatTextBox 
            txtrowNum   = new PatTextBox(),
            txtNombre   = new PatTextBox();
    private PatButton 
            btnPageIni  = new PatButton(" |< "),
            btnPageAnt  = new PatButton(" << "),
            btnPageSig  = new PatButton(" >> "),
            btnPageFin  = new PatButton(" >| "),

            btnRowIni   = new PatButton(" |< "),
            btnRowAnt   = new PatButton(" << "),
            btnRowSig   = new PatButton(" >> "),
            btnRowFin   = new PatButton(" >| "),

            btnNuevo    = new PatButton("Nuevo"),
            btnGuardar  = new PatButton("Guardar"),
            btnCancelar = new PatButton("Cancelar"),
            btnEliminar = new PatButton("Eliminar");
    private JPanel 
            pnlTabla    = new JPanel(),
            pnlBtnRow   = new JPanel(new FlowLayout()),
            pnlBtnPage  = new JPanel(new FlowLayout()),
            pnlBtnCRUD  = new JPanel(new FlowLayout());
            
            public void customizeComponent() {
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
            
                txtrowNum.setEnabled(false);
                txtrowNum.setBorderLine();
                txtNombre.setBorderLine();
            
                // Center the buttons in pnlBtnCRUD
                pnlBtnCRUD.setLayout(new FlowLayout(FlowLayout.CENTER));
                pnlBtnCRUD.add(btnNuevo);
                pnlBtnCRUD.add(btnGuardar);
                pnlBtnCRUD.add(btnCancelar);
                pnlBtnCRUD.add(btnEliminar);
                pnlBtnCRUD.setBorder(AJEstilo.createBorderRect());
            
                gbc.insets = new Insets(5, 5, 5, 5);
            
                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(lblTitulo, gbc);
            
                gbc.gridy = 1;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipady = 150;
                gbc.ipadx = 1200; // Increase width as needed
                pnlTabla.add(new JLabel("Loading data..."));
                add(pnlTabla, gbc);
            
                gbc.fill = GridBagConstraints.NONE;
                gbc.ipady = 0;
                gbc.ipadx = 0;
                gbc.gridy = 2;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(pnlBtnCRUD, gbc);
            }      
}
