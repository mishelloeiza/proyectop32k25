package vista;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class NewJPanel extends javax.swing.JPanel {

    public NewJPanel() {
        initComponents();
    }

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
                return false;
            }
        };
    }

    private DefaultTableModel cargarPermisosUsuarioDesdeBD() {
        DefaultTableModel modelo = null;
        String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String usuario = "MISHEL";
        String password = "311";

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
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtPermisoMantenimiento = new javax.swing.JCheckBox();
        txtPermisoProcesos = new javax.swing.JCheckBox();
        txtPermisoEliminar = new javax.swing.JCheckBox();
        txtPermisoModificar = new javax.swing.JCheckBox();
        txtPermisoRegistrar = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();

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

        jButton1.setText("MOSTRAR PERMISOS DE LOS USUARIOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ACTUALIZAR PERMISOS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID_USUARIO_EXISTENTE");

        jButton3.setText("AGREGAR PERMISOS ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtPermisoMantenimiento.setText("MANTENIMIENTOS");

        txtPermisoProcesos.setText("PROCESOS");

        txtPermisoEliminar.setText("ELIMINAR");

        txtPermisoModificar.setText("MODIFICAR");

        txtPermisoRegistrar.setText("REGISTRAR");

        jButton4.setText("ELIMINAR PERMISOS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPermisoMantenimiento)
                            .addComponent(txtPermisoEliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPermisoProcesos)
                            .addComponent(txtPermisoRegistrar)))
                    .addComponent(txtPermisoModificar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPermisoMantenimiento)
                            .addComponent(txtPermisoProcesos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPermisoEliminar)
                            .addComponent(txtPermisoRegistrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPermisoModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
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
            int pm = txtPermisoMantenimiento.isSelected() ? 1 : 0;
            int pp = txtPermisoProcesos.isSelected() ? 1 : 0;
            int pe = txtPermisoEliminar.isSelected() ? 1 : 0;
            int pr = txtPermisoRegistrar.isSelected() ? 1 : 0;
            int pmf = txtPermisoModificar.isSelected() ? 1 : 0;

            String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String usuario = "MISHEL";
            String password = "311";

            try (Connection cn = DriverManager.getConnection(JDBC_URL, usuario, password);
                 PreparedStatement pst = cn.prepareStatement("UPDATE permisos_usuario SET puede_mantenimiento=?, puede_procesos=?, puede_eliminar=?, puede_registrar=?, puede_modificar=? WHERE id_usuario=?")) {

                pst.setInt(1, pm);
                pst.setInt(2, pp);
                pst.setInt(3, pe);
                pst.setInt(4, pr);
                pst.setInt(5, pmf);
                pst.setInt(6, id);

                int filasAfectadas = pst.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Permisos actualizados correctamente.");
                    TABLAB.setModel(cargarPermisosUsuarioDesdeBD());
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el usuario con ese ID.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar permisos: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:                                         
        try {
            int id = Integer.parseInt(txtId.getText());
            int pm = txtPermisoMantenimiento.isSelected() ? 1 : 0;
            int pp = txtPermisoProcesos.isSelected() ? 1 : 0;
            int pe = txtPermisoEliminar.isSelected() ? 1 : 0;
            int pr = txtPermisoRegistrar.isSelected() ? 1 : 0;
            int pmf = txtPermisoModificar.isSelected() ? 1 : 0;

            String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String usuario = "MISHEL";
            String password = "311";

            try (Connection cn = DriverManager.getConnection(JDBC_URL, usuario, password);
                 PreparedStatement pst = cn.prepareStatement("INSERT INTO permisos_usuario (id_usuario, puede_mantenimiento, puede_procesos, puede_eliminar, puede_registrar, puede_modificar) VALUES (?, ?, ?, ?, ?, ?)")) {

                pst.setInt(1, id);
                pst.setInt(2, pm);
                pst.setInt(3, pp);
                pst.setInt(4, pe);
                pst.setInt(5, pr);
                pst.setInt(6, pmf);

                int filasAfectadas = pst.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Permisos agregados correctamente.");
                    TABLAB.setModel(cargarPermisosUsuarioDesdeBD());
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo agregar los permisos.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar permisos: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
                                                
    try {
        int id = Integer.parseInt(txtId.getText());

        String JDBC_URL = "jdbc:mysql://localhost/sig2k25?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String usuario = "MISHEL";
        String password = "311";

        try (Connection cn = DriverManager.getConnection(JDBC_URL, usuario, password);
             PreparedStatement pst = cn.prepareStatement("DELETE FROM permisos_usuario WHERE id_usuario=?")) {

            pst.setInt(1, id);

            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Permisos eliminados correctamente.");
                TABLAB.setModel(cargarPermisosUsuarioDesdeBD());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el usuario con ese ID.");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar permisos: " + e.getMessage());
    }

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TABLAB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtId;
    private javax.swing.JCheckBox txtPermisoEliminar;
    private javax.swing.JCheckBox txtPermisoMantenimiento;
    private javax.swing.JCheckBox txtPermisoModificar;
    private javax.swing.JCheckBox txtPermisoProcesos;
    private javax.swing.JCheckBox txtPermisoRegistrar;
    // End of variables declaration//GEN-END:variables

   
}
