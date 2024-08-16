
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
import javax.swing.table.TableColumnModel;

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
        // Crear un nuevo objeto HormigueroDTO con valores predeterminados
        HormigueroDTO nuevaHormiga = new HormigueroDTO();
        nuevaHormiga.setTipoHormiga("Larva"); // Valor predeterminado
        nuevaHormiga.setSexoId(1); // Asignar un valor predeterminado para Sexo ID
        nuevaHormiga.setProvinciaId((int) (Math.random() * 12) + 1); // Ubicación aleatoria entre 1 y 12
        nuevaHormiga.setGenoAlimentoId(1); // Asignar un valor predeterminado para Geno Alimento ID
        nuevaHormiga.setIngestaNativaId(1); // Asignar un valor predeterminado para Ingesta Nativa ID
        nuevaHormiga.setEstadoHorm("Viva"); // Valor predeterminado para Estado Horm
        nuevaHormiga.setEstado("A"); // Asignar un valor predeterminado para Estado
    
        try {
            if (hormigueroBL.add(nuevaHormiga)) {
                JOptionPane.showMessageDialog(this, "Hormiga añadida exitosamente.");
                loadRow();
                showRow();
                showTable(); // Actualizar la tabla para mostrar la nueva hormiga
            } else {
                JOptionPane.showMessageDialog(this, "Error al añadir la hormiga.");
            }
        } catch (Exception e) {
            AJEstilo.showMsg(e.getMessage());
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
        // Verificar si hay un registro seleccionado
        if (hormigueroDAO == null || hormigueroDAO.getId() == null) {
            AJEstilo.showMsgError("No hay un registro válido para eliminar.");
            return;
        }
    
        try {
            // Confirmar con el usuario la eliminación
            int opcion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas marcar este registro como inactivo?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION);
            
            if (opcion == JOptionPane.YES_OPTION) {
                // Cambiar el estado del registro a "X"
                hormigueroDAO.setEstado("X");
                boolean exito = hormigueroBL.update(hormigueroDAO);
                if (exito) {
                    // Actualizar la vista después de la actualización
                    loadRow();
                    showRow();
                    showTable(); // Actualizar la tabla para ocultar el registro marcado como "X"
                    JOptionPane.showMessageDialog(this, "Registro marcado como inactivo exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo marcar el registro como inactivo.");
                }
            }
        } catch (Exception e) {
            // Manejar excepciones inesperadas
            e.printStackTrace();
            AJEstilo.showMsgError("Ocurrió un error al intentar marcar el registro como inactivo.");
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

    // Filtrar los registros con estado "X"
    hormigueroList.removeIf(h -> "X".equals(h.getEstado()));

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

    // Desactiva el modo de ajuste automático de columnas
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    // Ajusta manualmente el ancho de las columnas
    TableColumnModel columnModel = table.getColumnModel();
    for (int i = 0; i < columnModel.getColumnCount(); i++) {
        columnModel.getColumn(i).setPreferredWidth(100);  // Establece un ancho más estrecho
    }

    // Ajusta el tamaño preferido de la tabla para que sea más compacta
    table.setPreferredScrollableViewportSize(new Dimension(600, 150));

    pnlTabla.removeAll();
    pnlTabla.setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(table);
    // Ajusta el tamaño preferido del JScrollPane
    scrollPane.setPreferredSize(new Dimension(600, 150));
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
// import javax.swing.table.TableColumnModel;

// public class AJHormigaPanel extends JPanel implements ActionListener {
//     private Integer rowNum = 0, idRowMaxHormiguero = 0;
//     private HormigueroBL hormigueroBL = new HormigueroBL();
//     private HormigueroDTO hormigueroDAO = null;

//      public AJHormigaPanel() {
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
//         // Crear un nuevo objeto HormigueroDTO con valores predeterminados
//         HormigueroDTO nuevaHormiga = new HormigueroDTO();
//         nuevaHormiga.setTipoHormiga("Larva"); // Valor predeterminado
//         nuevaHormiga.setSexoId(1); // Asignar un valor predeterminado para Sexo ID
//         nuevaHormiga.setProvinciaId((int) (Math.random() * 12) + 1); // Ubicación aleatoria entre 1 y 12
//         nuevaHormiga.setGenoAlimentoId(1); // Asignar un valor predeterminado para Geno Alimento ID
//         nuevaHormiga.setIngestaNativaId(1); // Asignar un valor predeterminado para Ingesta Nativa ID
//         nuevaHormiga.setEstadoHorm("Viva"); // Valor predeterminado para Estado Horm
//         nuevaHormiga.setEstado("A"); // Asignar un valor predeterminado para Estado
    
//         try {
//             if (hormigueroBL.add(nuevaHormiga)) {
//                 JOptionPane.showMessageDialog(this, "Hormiga añadida exitosamente.");
//                 loadRow();
//                 showRow();
//                 showTable(); // Actualizar la tabla para mostrar la nueva hormiga
//             } else {
//                 JOptionPane.showMessageDialog(this, "Error al añadir la hormiga.");
//             }
//         } catch (Exception e) {
//             AJEstilo.showMsg(e.getMessage());
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
//         // Verificar si hay un registro seleccionado
//         if (hormigueroDAO == null || hormigueroDAO.getId() == null) {
//             AJEstilo.showMsgError("No hay un registro válido para eliminar.");
//             return;
//         }
    
//         try {
//             // Confirmar con el usuario la eliminación
//             int opcion = JOptionPane.showConfirmDialog(this, 
//                 "¿Estás seguro de que deseas marcar este registro como inactivo?", 
//                 "Confirmar Eliminación", 
//                 JOptionPane.YES_NO_OPTION);
            
//             if (opcion == JOptionPane.YES_OPTION) {
//                 // Cambiar el estado del registro a "X"
//                 hormigueroDAO.setEstado("X");
//                 boolean exito = hormigueroBL.update(hormigueroDAO);
//                 if (exito) {
//                     // Actualizar la vista después de la actualización
//                     loadRow();
//                     showRow();
//                     showTable(); // Actualizar la tabla para ocultar el registro marcado como "X"
//                     JOptionPane.showMessageDialog(this, "Registro marcado como inactivo exitosamente.");
//                 } else {
//                     JOptionPane.showMessageDialog(this, "No se pudo marcar el registro como inactivo.");
//                 }
//             }
//         } catch (Exception e) {
//             // Manejar excepciones inesperadas
//             e.printStackTrace();
//             AJEstilo.showMsgError("Ocurrió un error al intentar marcar el registro como inactivo.");
//         }
//     }
    


    
    
    
// private void showTable() throws Exception {
//     String[] header = {"RowNum", "Id", "Tipo Hormiga", "Sexo ID", "Provincia ID", "Geno Alimento ID", "Ingesta Nativa ID", "Estado Horm", "Estado"};

//     List<HormigueroDTO> hormigueroList = hormigueroBL.getAll();
//     if (hormigueroList == null || hormigueroList.isEmpty()) {
//         pnlTabla.removeAll();
//         pnlTabla.add(new JLabel("No data available."));
//         pnlTabla.revalidate();
//         pnlTabla.repaint();
//         return;
//     }

//     // Filtrar los registros con estado "X"
//     hormigueroList.removeIf(h -> "X".equals(h.getEstado()));

//     Object[][] data = new Object[hormigueroList.size()][header.length];
//     int index = 0;
//     for (HormigueroDTO h : hormigueroList) {
//         data[index][0] = h.getRowNum();
//         data[index][1] = h.getId();
//         data[index][2] = h.getTipoHormiga();
//         data[index][3] = h.getSexoId();
//         data[index][4] = h.getProvinciaId();
//         data[index][5] = h.getGenoAlimentoId();
//         data[index][6] = h.getIngestaNativaId();
//         data[index][7] = h.getEstadoHorm();
//         data[index][8] = h.getEstado();
//         index++;
//     }

//     JTable table = new JTable(data, header);
//     table.setShowHorizontalLines(true);
//     table.setGridColor(Color.lightGray);
//     table.setRowSelectionAllowed(true);
//     table.setColumnSelectionAllowed(false);

//     // Desactiva el modo de ajuste automático de columnas
//     table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//     // Ajusta manualmente el ancho de las columnas
//     TableColumnModel columnModel = table.getColumnModel();
//     for (int i = 0; i < columnModel.getColumnCount(); i++) {
//         columnModel.getColumn(i).setPreferredWidth(100);  // Establece un ancho más estrecho
//     }

//     // Ajusta el tamaño preferido de la tabla para que sea más compacta
//     table.setPreferredScrollableViewportSize(new Dimension(600, 150));

//     pnlTabla.removeAll();
//     pnlTabla.setLayout(new BorderLayout());
//     JScrollPane scrollPane = new JScrollPane(table);
//     // Ajusta el tamaño preferido del JScrollPane
//     scrollPane.setPreferredSize(new Dimension(600, 150));
//     pnlTabla.add(scrollPane, BorderLayout.CENTER);
//     pnlTabla.revalidate();
//     pnlTabla.repaint();

//     table.addMouseListener(new MouseAdapter() {
//         @Override
//         public void mouseClicked(MouseEvent e) {
//             int row = table.rowAtPoint(e.getPoint());
//             if (row >= 0) {
//                 rowNum = (Integer) table.getModel().getValueAt(row, 0);
//                 try {
//                     hormigueroDAO = hormigueroBL.getBy(rowNum);
//                     showRow();
//                 } catch (Exception ignored) {
//                 }
//             }
//         }
//     });
// }

    
    
    
    

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
