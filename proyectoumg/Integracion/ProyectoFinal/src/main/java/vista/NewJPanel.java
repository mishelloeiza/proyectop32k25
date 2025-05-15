package vista;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class NewJPanel extends javax.swing.JPanel {

    public NewJPanel() {
        initComponents();
    }
//CREADO POR MISHEL LOEIZA 9959-23-3457
    // Método para convertir ResultSet en DefaultTableModel
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaDatos = rs.getMetaData();

        Vector<String> columnas = new Vector<>();
        int columnCount = metaDatos.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnas.add(metaDatos.getColumnName(i));
        }

        Vector<Vector<Object>> datos = new Vector<>();
        while (rs.next()) {
            Vector<Object> fila = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                fila.add(rs.getObject(i));
            }
            datos.add(fila);
        }

        return new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // tabla no editable
            }
        };
    }

    // Método que conecta y carga datos de la base de datos
 private DefaultTableModel cargarPermisosUsuarioDesdeBD() {
    DefaultTableModel modelo = null;
    String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    String usuario = "MISHEL";  // tu usuario
    String password = "311";    // tu contraseña

    try (Connection cn = DriverManager.getConnection(JDBC_URL, usuario, password);
         PreparedStatement pst = cn.prepareStatement("SELECT * FROM permisos_usuario");
         ResultSet rs = pst.executeQuery()) {

        modelo = buildTableModel(rs);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar permisos: " + e.getMessage());
    }
    return modelo;
}


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TABLAB = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtPermisoMantenimiento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPermisoProcesos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jScrollPane1.setBackground(new java.awt.Color(153, 204, 255));

        TABLAB.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TABLAB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Vendedor", "ID Empleado", "Correo", "Telefono", "Direccion", "Porcentaje", "Comision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TABLAB);

        jButton1.setText("MOSTRAR PERMISOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ACTUALIZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID_USUARIO");

        jLabel2.setText("PERMISO MANTENIMINETOS");

        jLabel3.setText("PERMISO PROCESOS");

        jButton3.setText("AGREGAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPermisoMantenimiento)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtId)
                            .addComponent(jLabel1))
                        .addComponent(jLabel3))
                    .addComponent(txtPermisoProcesos, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPermisoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtPermisoProcesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 // Acción del botón para cargar datos y poner en la tabla
        DefaultTableModel model = cargarPermisosUsuarioDesdeBD();
        if (model != null) {
            TABLAB.setModel(model);
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                                         
    try {
        int id = Integer.parseInt(txtId.getText());
        int permisoMantenimiento = Integer.parseInt(txtPermisoMantenimiento.getText()); // 0 o 1
        int permisoProcesos = Integer.parseInt(txtPermisoProcesos.getText());           // 0 o 1

        if ((permisoMantenimiento != 0 && permisoMantenimiento != 1) || 
            (permisoProcesos != 0 && permisoProcesos != 1)) {
            JOptionPane.showMessageDialog(this, "Los permisos deben ser 0 o 1.");
            return;
        }

        String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String usuario = "MISHEL";
        String password = "311";

        try (Connection cn = DriverManager.getConnection(JDBC_URL, usuario, password);
             PreparedStatement pst = cn.prepareStatement(
                 "UPDATE permisos_usuario SET puede_mantenimiento = ?, puede_procesos = ? WHERE id_usuario = ?")) {

            pst.setInt(1, permisoMantenimiento);
            pst.setInt(2, permisoProcesos);
            pst.setInt(3, id);

            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Permisos actualizados correctamente.");
                TABLAB.setModel(cargarPermisosUsuarioDesdeBD()); // Recarga la tabla
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el usuario con ese ID.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar permisos: " + e.getMessage());
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID y permisos deben ser números válidos (0 o 1).");
    }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:                                          
    try {
        int idUsuario = Integer.parseInt(txtId.getText());
        int permisoMantenimiento = Integer.parseInt(txtPermisoMantenimiento.getText());
        int permisoProcesos = Integer.parseInt(txtPermisoProcesos.getText());

        if ((permisoMantenimiento != 0 && permisoMantenimiento != 1) || 
            (permisoProcesos != 0 && permisoProcesos != 1)) {
            JOptionPane.showMessageDialog(this, "Los permisos deben ser 0 o 1.");
            return;
        }

        String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String usuario = "MISHEL";
        String password = "311";

        try (Connection cn = DriverManager.getConnection(JDBC_URL, usuario, password);
             PreparedStatement pst = cn.prepareStatement(
                "INSERT INTO permisos_usuario (id_usuario, puede_mantenimiento, puede_procesos) VALUES (?, ?, ?)")) {

            pst.setInt(1, idUsuario);
            pst.setInt(2, permisoMantenimiento);
            pst.setInt(3, permisoProcesos);

            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Permisos agregados correctamente.");
                TABLAB.setModel(cargarPermisosUsuarioDesdeBD()); // Recarga tabla
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar los permisos.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar permisos: " + e.getMessage());
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID y permisos deben ser números válidos (0 o 1).");
    }

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TABLAB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPermisoMantenimiento;
    private javax.swing.JTextField txtPermisoProcesos;
    // End of variables declaration//GEN-END:variables

   
}
