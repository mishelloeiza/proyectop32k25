package vista.seguridad;

import Modelo.seguridad.UsuarioDAO;
import Controlador.seguridad.Usuario;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import org.jfree.base.log.LogConfiguration;
import Modelo.seguridad.PerfilDAO;
import Controlador.seguridad.Perfil;
import javax.swing.DefaultListModel;
import Modelo.Conexion;
import Modelo.seguridad.RellenarCombos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JList;

public class MantenimientoRelPerfUsu extends javax.swing.JInternalFrame {
    int APLICACION = 101;
private DefaultListModel<String> modelPerfD = new DefaultListModel<>();
    private DefaultListModel<String> modelPerfA = new DefaultListModel<>();
    
    public void llenadoDeCombos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> salon = usuarioDAO.select();
        cbousuario.addItem("Seleccione una opción");
        for (int i = 0; i < salon.size(); i++) {
            cbousuario.addItem(String.valueOf(salon.get(i).getId_usuario()));
        }
        cbousuario.addActionListener(e -> {
            String idSelec = cbousuario.getSelectedItem().toString();
            if (!idSelec.equals("Seleccione una opción")) {
                int idSeleccionado = Integer.parseInt(idSelec);
                for (Usuario usuario : salon) {
                    if (usuario.getId_usuario() == idSeleccionado) {
                        txtusu.setText(usuario.getUsername());
                        break;
                    }
                }
            }
        });
        
        PerfilDAO perfilDAO = new PerfilDAO();
        List<Perfil> perfiles = perfilDAO.select(); 
        DefaultListModel<String> modelo = new DefaultListModel<>(); 
        for (Perfil app : perfiles) {
            modelo.addElement(app.getNombre_perfil()); 
        }
        
          
    modelPerfD.clear(); // Limpiar modelo existente
    
    for (Perfil app : perfiles) {
        modelPerfD.addElement(app.getNombre_perfil()); 
    }
    
    // Asignar modelos a los JList
    lstPerfD.setModel(modelPerfD);
    lstPerfA.setModel(modelPerfA);
        
        
        
        lstPerfD.setModel(modelo);
    }

    public void llenadoDeTablas() {
        // Código comentado, aún no implementado
    }

   private void insertarAsignacion(int idUsuario, int idPerfil) throws SQLException {
    String sql = "INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (?, ?)";
    
    // Mensaje de depuración
    System.out.println("idUsuario: " + idUsuario + ", idPerfil: " + idPerfil);

    try (Connection conexion = Conexion.getConnection();
         PreparedStatement pst = conexion.prepareStatement(sql)) {
        pst.setInt(1, idUsuario);
        pst.setInt(2, idPerfil);
        int filasAfectadas = pst.executeUpdate();
        
        // Mensaje de depuración
        System.out.println("Filas afectadas: " + filasAfectadas);

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Asignación guardada exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la asignación");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar la asignación: " + e.getMessage());
        e.printStackTrace();  // Para obtener más detalles del error
    }
}
    private void eliminarAsignacion(int idUsuario, int idPerfil) throws SQLException {
        String sql = "DELETE FROM usuario_perfil WHERE id_usuario = ? AND id_perfil = ?";

        // Mensaje de depuración
        System.out.println("Eliminando asignación - idUsuario: " + idUsuario + ", idPerfil: " + idPerfil);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idPerfil);
            int filasAfectadas = pst.executeUpdate();

            // Mensaje de depuración
            System.out.println("Filas afectadas: " + filasAfectadas);

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Asignación eliminada exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la asignación para eliminar");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la asignación: " + e.getMessage());
            e.printStackTrace();  
            throw e;  
        }
    }


   private int obtenerIdPerfil(String nombrePerfil) throws SQLException {
    int idPerfil = -1;
    String sql = "SELECT id_perfil FROM perfiles WHERE nombre_perfil = ?";

    try (Connection conexion = Conexion.getConnection();
         PreparedStatement pst = conexion.prepareStatement(sql)) {
        pst.setString(1, nombrePerfil);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            idPerfil = rs.getInt("id_perfil");
        }
    }
    return idPerfil;
}


    public MantenimientoRelPerfUsu() {
        initComponents();
        llenadoDeTablas();
        llenadoDeCombos();
        
        btnAgregarU.addActionListener(e -> {
            try {
                // --- Parte 1: Validaciones iniciales ---
                String usuarioSeleccionado = cbousuario.getSelectedItem().toString();
                int selectedIndex = lstPerfD.getSelectedIndex();

                if (usuarioSeleccionado.equals("Seleccione una opción") || selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario y un perfil");
                    return;
                }

                // --- Parte 2: Obtener datos ---
                String nombrePerfil = modelPerfD.getElementAt(selectedIndex);
                int idUsuario = Integer.parseInt(usuarioSeleccionado);
                int idPerfil = obtenerIdPerfil(nombrePerfil);

                // --- Parte 3: Lógica de base de datos ---
                insertarAsignacion(idUsuario, idPerfil);

                // --- Parte 4: Movimiento visual entre JList ---
                modelPerfA.addElement(nombrePerfil);
                modelPerfD.remove(selectedIndex);

                JOptionPane.showMessageDialog(null, "Perfil asignado correctamente");

            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
                    
    );
        btnEliminarU.addActionListener(e -> {
            try {
                // --- Parte 1: Validaciones iniciales ---
                String usuarioSeleccionado = cbousuario.getSelectedItem().toString();
                int selectedIndex = lstPerfA.getSelectedIndex();

                if (usuarioSeleccionado.equals("Seleccione una opción") || selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario y un perfil asignado");
                    return;
                }

                // --- Parte 2: Obtener datos ---
                String nombrePerfil = modelPerfA.getElementAt(selectedIndex);
                int idUsuario = Integer.parseInt(usuarioSeleccionado);
                int idPerfil = obtenerIdPerfil(nombrePerfil);

                // --- Parte 3: Lógica de base de datos (eliminación) ---
                eliminarAsignacion(idUsuario, idPerfil);

                // --- Parte 4: Movimiento visual entre JList ---
                modelPerfD.addElement(nombrePerfil);
                modelPerfA.remove(selectedIndex);

                JOptionPane.showMessageDialog(null, "Perfil retirado correctamente");

            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        cbousuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPerfD = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPerfA = new javax.swing.JList<>();
        btnAgregarU = new javax.swing.JButton();
        btnEliminarU = new javax.swing.JButton();
        txtusu = new javax.swing.JTextField();
        btnAsignarTodo = new javax.swing.JButton();
        btnRetirarTodo = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Usuario Perfil");
        setVisible(true);

        jLabel1.setText("Usuario");

        cbousuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbousuarioActionPerformed(evt);
            }
        });

        jLabel2.setText("Perfiles disponibles");

        btnBuscar.setText("Buscar Usuario");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("ASIGNAR");

        jLabel4.setText("RETIRAR");

        jScrollPane1.setViewportView(lstPerfD);

        jScrollPane3.setViewportView(lstPerfA);

        btnAgregarU.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregarU.setText("▶");
        btnAgregarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUActionPerformed(evt);
            }
        });

        btnEliminarU.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarU.setText("◀️");
        btnEliminarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUActionPerformed(evt);
            }
        });

        txtusu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtusu.setEnabled(false);

        btnAsignarTodo.setBackground(new java.awt.Color(153, 153, 255));
        btnAsignarTodo.setText("▶▶");
        btnAsignarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarTodoActionPerformed(evt);
            }
        });

        btnRetirarTodo.setBackground(new java.awt.Color(153, 153, 255));
        btnRetirarTodo.setText("◀◀️");
        btnRetirarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarTodoActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Periles Asignadas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAsignarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAgregarU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnRetirarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEliminarU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(78, 78, 78)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel2)))
                        .addGap(145, 145, 145)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(label3)))))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbousuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(label3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnAsignarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarU)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRetirarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarU))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbousuarioActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbousuarioActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try {
        // 1. Obtener nombre del usuario seleccionado
        String nombreUsuario = cbousuario.getSelectedItem().toString();
        
        // 2. Validar que no sea la opción por defecto
        if ("Perfiles de usuario son".equals(nombreUsuario)) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario válido");
            return;
        }
        
        // 3. Obtener id_usuario
        RellenarCombos re = new RellenarCombos();
        int idUsuario = re.obtenerIdPorNombre("usuario", "username", "id_usuario", nombreUsuario);
        
        // 4. Cargar perfiles asignados en el nuevo ComboBox
//        re.cargarPerfilesAsignados(idUsuario, cboPerfilesAsignados); // Asume que tienes un JComboBox llamado cboPerfilesAsignados
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUActionPerformed
                                                                                     
    try {
        // Obtener el ID del usuario seleccionado
        String idUsuario = cbousuario.getSelectedItem().toString();
        if ("Seleccione una opción".equals(idUsuario)) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario válido");
            return;  // Evitar continuar si no se selecciona un usuario válido
        }

        // Convertir el ID seleccionado en número
        int idUsuarioSeleccionado = Integer.parseInt(idUsuario);
        
        // Obtener el perfil seleccionado (del JList)
        String perfilSeleccionado = lstPerfD.getSelectedValue();
        if (perfilSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un perfil para agregar");
            return;  // Evitar continuar si no se selecciona un perfil válido
        }
        
        // Obtener ID del perfil desde la base de datos por nombre
        PerfilDAO perfilDAO = new PerfilDAO();
        int idPerfil = perfilDAO.obtenerIdPorNombre(perfilSeleccionado);
        
        // Insertar relación entre usuario y perfil
        AsignacionPerfilDAO asignacionPerfilDAO = new AsignacionPerfilDAO();
        asignacionPerfilDAO.insertarAsignacion(idUsuarioSeleccionado, idPerfil);
        
        JOptionPane.showMessageDialog(null, "Perfil agregado exitosamente.");
        llenadoDeTablas(); // Refrescar la tabla después de la inserción
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error al convertir el ID del usuario: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
    }
    

    }//GEN-LAST:event_btnAgregarUActionPerformed

    private void btnEliminarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUActionPerformed
                                                
    try {
        // Obtener el ID del usuario seleccionado
        String idUsuario = cbousuario.getSelectedItem().toString();
        if ("Seleccione una opción".equals(idUsuario)) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario válido");
            return;
        }
        // Convertir el ID seleccionado en número
        int idUsuarioSeleccionado = Integer.parseInt(idUsuario);
        
        // Obtener el perfil seleccionado (del JList)
        String perfilSeleccionado = lstPerfA.getSelectedValue();
        if (perfilSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un perfil para retirar");
            return;
        }
        
        // Obtener ID del perfil desde la base de datos por nombre (o lógica similar)
        PerfilDAO perfilDAO = new PerfilDAO();
        int idPerfil = perfilDAO.obtenerIdPorNombre(perfilSeleccionado);
        
        // Eliminar la relación entre usuario y perfil (deberías tener un DAO para esto)
        AsignacionPerfilDAO asignacionPerfilDAO = new AsignacionPerfilDAO();
        asignacionPerfilDAO.eliminarAsignacion(idUsuarioSeleccionado, idPerfil);
        
        JOptionPane.showMessageDialog(null, "Relación eliminada exitosamente.");
        llenadoDeTablas(); // Refrescar la tabla después de la eliminación
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar la relación: " + e.getMessage());
     }
    }//GEN-LAST:event_btnEliminarUActionPerformed

    private void btnAsignarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarTodoActionPerformed
       // TODO add your handling code here:
        /*
        AplicacionDAO aplicacionDAO = new AplicacionDAO();
        Aplicacion aplicacionAEliminar = new Aplicacion();
        aplicacionAEliminar.setId_aplicacion(Integer.parseInt(txtbuscado.getText()));
        aplicacionDAO.delete(aplicacionAEliminar);
        llenadoDeTablas();
         UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Eliminar Datos Aplicacion");
    */
    }//GEN-LAST:event_btnAsignarTodoActionPerformed

    private void btnRetirarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarTodoActionPerformed
                                                  
    try {
        // Obtener el ID del usuario seleccionado
        String idUsuario = cbousuario.getSelectedItem().toString();
        if ("Seleccione una opción".equals(idUsuario)) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario válido");
            return;
        }
        // Convertir el ID seleccionado en número
        int idUsuarioSeleccionado = Integer.parseInt(idUsuario);
        
        // Obtener el perfil seleccionado (del JList)
        String perfilSeleccionado = lstPerfA.getSelectedValue();
        if (perfilSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un perfil para modificar");
            return;
        }
        
        // Obtener ID del perfil desde la base de datos por nombre
        PerfilDAO perfilDAO = new PerfilDAO();
        int idPerfil = perfilDAO.obtenerIdPorNombre(perfilSeleccionado);
        
        // Modificar la relación entre usuario y perfil (deberías tener un DAO para esto)
        AsignacionPerfilDAO asignacionPerfilDAO = new AsignacionPerfilDAO();
        asignacionPerfilDAO.modificarAsignacion(idUsuarioSeleccionado, idPerfil);
        
        JOptionPane.showMessageDialog(null, "Relación modificada exitosamente.");
        llenadoDeTablas(); // Refrescar la tabla después de la modificación
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al modificar la relación: " + e.getMessage());
    }

    }//GEN-LAST:event_btnRetirarTodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarU;
    private javax.swing.JButton btnAsignarTodo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminarU;
    private javax.swing.JButton btnRetirarTodo;
    private javax.swing.JComboBox<String> cbousuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JList<String> lstPerfA;
    private javax.swing.JList<String> lstPerfD;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
