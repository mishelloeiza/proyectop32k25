package vista.seguridad;

import Controlador.seguridad.RelPerfUsu;
import Modelo.seguridad.RelPerfUsuDAO;
import Modelo.seguridad.UsuarioDAO;
import Controlador.seguridad.Usuario;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import org.jfree.base.log.LogConfiguration;
import java.sql.*;
import Modelo.seguridad.PerfilDAO;
import Controlador.seguridad.Perfil;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
//R
    public void llenadoDeCombos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> salon = usuarioDAO.select();
        cbousuario.addItem("Seleccione una opción");
        for (int i = 0; i < salon.size(); i++) {
            cbousuario.addItem(String.valueOf(salon.get(i).getId_usuario()));
            cbousuario.addActionListener(e -> {
            // mandamos a traer el ID
            String idSelec = cbousuario.getSelectedItem().toString();
            int idSeleccionado = Integer.parseInt(idSelec);
            // Busca el perfil en la lista
            for (Usuario usuario : salon) {
                if (usuario.getId_usuario() == idSeleccionado) {
              txtusu.setText(usuario.getUsername());
            
             break;
             }
        }});
        }
         //A   
        PerfilDAO perfilDAO = new PerfilDAO();
        List<Perfil> perfiles = perfilDAO.select(); 
        DefaultListModel<String> modelo = new DefaultListModel<>();
        DefaultListModel<String> modelo2 = new DefaultListModel<>();
        for (Perfil app : perfiles) {
            modelo.addElement(app.getNombre_perfil()); 
        }
        lstPerfD.setModel(modelo);
        lstPerfA.setModel(modelo2);
        // Listener para detectar la selección del usuario
lstPerfA.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Evita doble evento
            String nombrePerfSeleccionado = lstPerfA.getSelectedValue();
            
            if (nombrePerfSeleccionado != null) {
                // Buscar el ID del perfil seleccionada
                for (Perfil app : perfiles) {
                    if (app.getNombre_perfil().equals(nombrePerfSeleccionado)) {
                        int idPerfSeleccionado = app.getId_perfil();
                        System.out.println("ID seleccionado: " + idPerfSeleccionado); // Opcional: para debug
                        txtidPerf.setText(String.valueOf(idPerfSeleccionado)); // Asignar el ID a un campo
                        break;
                    }
                }
            }
        }
    }
});
    }
  //G  
public void llenadousuariosperfiles(){
// 1. Obtener todos los perfiles disponibles

PerfilDAO perfilDAO = new PerfilDAO();
List<Perfil> perfiles = perfilDAO.select();

// 2. Modelos para las listas
DefaultListModel<String> modelo = new DefaultListModel<>(); // Para listAplicD (todas las apps)
DefaultListModel<String> modelo2 = new DefaultListModel<>(); // Para listAplicA (apps del perfil)

// 3. Llenar listAplicD con TODOS los perfiles
for (Perfil perfil : perfiles) {
    modelo.addElement(perfil.getNombre_perfil());
}
lstPerfD.setModel(modelo);

// 4. Listener para cuando seleccionen un perfil
cbousuario.addActionListener(e -> {
    // Limpiar modelo2 antes de agregar nuevos elementos
    modelo2.clear();
    
    try {
        // Obtener perfil seleccionado
        String idSelec = cbousuario.getSelectedItem().toString();
        if (!idSelec.equals("Seleccione una opción")) {
            int idSeleccionado = Integer.parseInt(idSelec);
        
            // Obtener relaciones perfil-aplicación
            RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
            List<RelPerfUsu> relaciones = relPerfUsuDAO.select();
        
            // Filtrar aplicaciones del perfil seleccionado
            for (RelPerfUsu relacion : relaciones) {
                if (relacion.getUsuario_codigo() == idSeleccionado) {
                    // Buscar la aplicación por ID
                    for (Perfil perfil : perfiles) {
                        if (perfil.getId_perfil() == relacion.getPerfil_codigo()) {
                            modelo2.addElement(perfil.getNombre_perfil());
                            break; // Salir del for interno
                    }
                }
            }
        }
        
        lstPerfA.setModel(modelo2);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar perfiles: " + ex.getMessage());
    }
});

}
//M
    public void llenarlistaUnoaUno() {
    int indice=0;
    String cadena; 
     
    indice = lstPerfD.getSelectedIndex();
    if (indice != -1) {
        
    cadena = (String) lstPerfD.getSelectedValue();
    DefaultListModel<String> modeloPerfA;
    
    if (lstPerfA.getModel() == null) {
        modeloPerfA = new DefaultListModel<>();
        lstPerfA.setModel(modeloPerfA);
        
    } else {
        
        modeloPerfA = (DefaultListModel<String>) lstPerfA.getModel();
                
    }
    modeloPerfA.addElement(cadena);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un Perfil", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Perfil a Usuario");    
   
    }
   //R 
    public void llenarlista() {
    PerfilDAO perfilDAO = new PerfilDAO();
    List<Perfil> perfiles = perfilDAO.select(); 
    DefaultListModel<String> modelo = new DefaultListModel<>(); 
    //Recorre la lista :v
    for (Perfil app : perfiles) {
    modelo.addElement(app.getNombre_perfil()); 
}
lstPerfA.setModel(modelo);

        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Todos Los Perfiles a Usuario");    
   
    }
    //G
    public void vaciarlista() {
 
    DefaultListModel<String> modelo = new DefaultListModel<>();
    
    modelo.clear();
    lstPerfA.setModel(modelo);
      
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar Todos Los Perfiles a Usuario");    
   
    }
    
    public void vaciarlistaUnoaUno() {
    int indice = lstPerfA.getSelectedIndex();
    if (indice != -1) {
        String perfilSeleccionado = lstPerfA.getSelectedValue();
        DefaultListModel<String> modeloAsignados = (DefaultListModel<String>) lstPerfA.getModel();
        modeloAsignados.remove(indice);

        try {
            // Validar si se seleccionó un usuario válido
            Object itemSeleccionado = cbousuario.getSelectedItem();
            if (itemSeleccionado == null || itemSeleccionado.toString().equals("Seleccione una opción")) {
                JOptionPane.showMessageDialog(this, "Selecciona un Usuario válido antes de eliminar un perfil", "Advertencia", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el ID del usuario seleccionado
            int codigoUsuario = Integer.parseInt(itemSeleccionado.toString());

            // Buscar el ID del perfil por su nombre
            PerfilDAO perfilDAO = new PerfilDAO();
            List<Perfil> perfiles = perfilDAO.select();
            int idPerfil = -1;

            for (Perfil perfil : perfiles) {
                if (perfil.getNombre_perfil().equals(perfilSeleccionado)) {
                    idPerfil = perfil.getId_perfil();
                    break;
                }
            }

            if (idPerfil != -1) {
                // Eliminar la relación de la base de datos
                RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
                RelPerfUsu relPerfUsu = new RelPerfUsu();
                relPerfUsu.setUsuario_codigo(codigoUsuario);
                relPerfUsu.setPerfil_codigo(idPerfil);
                relPerfUsuDAO.delete(relPerfUsu);

                JOptionPane.showMessageDialog(this, "Perfil eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar el perfil: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un Perfil para eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

    public MantenimientoRelPerfUsu() {
        initComponents(); 
        llenadoDeCombos(); 
        llenadousuariosperfiles();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPerfD = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPerfA = new javax.swing.JList<>();
        btnAgregarU = new javax.swing.JButton();
        btnEliminarU = new javax.swing.JButton();
        txtusu = new javax.swing.JTextField();
        btnAgregarT = new javax.swing.JButton();
        btnEliminarT = new javax.swing.JButton();
        txtidPerf = new javax.swing.JTextField();
        bntconfir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Usuario Perfil");
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel1.setText("Usuario");

        cbousuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbousuarioActionPerformed(evt);
            }
        });

        lstPerfD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lstPerfD.setFont(new java.awt.Font("Engravers MT", 1, 12)); // NOI18N
        jScrollPane1.setViewportView(lstPerfD);

        lstPerfA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lstPerfA.setFont(new java.awt.Font("Engravers MT", 1, 12)); // NOI18N
        jScrollPane3.setViewportView(lstPerfA);

        btnAgregarU.setBackground(new java.awt.Color(51, 255, 204));
        btnAgregarU.setText("▶");
        btnAgregarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUActionPerformed(evt);
            }
        });

        btnEliminarU.setBackground(new java.awt.Color(51, 255, 204));
        btnEliminarU.setText("◀️");
        btnEliminarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUActionPerformed(evt);
            }
        });

        txtusu.setBackground(new java.awt.Color(204, 204, 255));
        txtusu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtusu.setEnabled(false);

        btnAgregarT.setBackground(new java.awt.Color(51, 255, 204));
        btnAgregarT.setText("▶▶");
        btnAgregarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTActionPerformed(evt);
            }
        });

        btnEliminarT.setBackground(new java.awt.Color(51, 255, 204));
        btnEliminarT.setText("◀◀️");
        btnEliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTActionPerformed(evt);
            }
        });

        txtidPerf.setForeground(new java.awt.Color(204, 204, 255));
        txtidPerf.setEnabled(false);

        bntconfir.setBackground(new java.awt.Color(255, 204, 255));
        bntconfir.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        bntconfir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/seguridad/Guardar (1).png"))); // NOI18N
        bntconfir.setText("Confirmar ");
        bntconfir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntconfirActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(204, 204, 255));
        btnSalir.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/seguridad/Salir (1).png"))); // NOI18N
        btnSalir.setText("Salir del sistema");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel2.setText("PERFILES ASIGNADOS");

        jLabel5.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel5.setText("PERFILES DISPONIBLES");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/seguridad/EditarAndy (1).png"))); // NOI18N
        jButton12.setText("▶▶");

        jLabel6.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel6.setText("RETIRAR PERFIL");

        jLabel7.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel7.setText("ASIGNAR PERFIL");

        jLabel8.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel8.setText("Perfil Seleccionado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGap(9, 9, 9)))
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnEliminarT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEliminarU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnAgregarT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(58, 58, 58)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(128, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(txtidPerf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(147, 147, 147))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntconfir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir)
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntconfir)
                            .addComponent(btnSalir))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidPerf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbousuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarU))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbousuarioActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbousuarioActionPerformed

    private void btnAgregarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUActionPerformed
        // TODO add your handling code here:
        llenarlistaUnoaUno();
    }//GEN-LAST:event_btnAgregarUActionPerformed

    private void btnEliminarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUActionPerformed
        // TODO add your handling code here:
        vaciarlistaUnoaUno();
    txtidPerf.setText(""); // Limpiar el campo de texto que muestra el ID del perfil
    }//GEN-LAST:event_btnEliminarUActionPerformed

    private void btnAgregarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTActionPerformed
       // TODO add your handling code here:
        llenarlista();
    }//GEN-LAST:event_btnAgregarTActionPerformed

    private void btnEliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTActionPerformed
     vaciarlista(); // Limpia visualmente la lista de perfiles asignados
    txtidPerf.setText("");

    // Validar si se seleccionó un usuario válido
    Object itemSeleccionado = cbousuario.getSelectedItem();
    if (itemSeleccionado == null || itemSeleccionado.toString().equals("Seleccione una opción")) {
        JOptionPane.showMessageDialog(this, "Selecciona un Usuario válido antes de eliminar perfiles", "Advertencia", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Obtener el ID del usuario seleccionado
        int codigoUsuario = Integer.parseInt(itemSeleccionado.toString());

        // Instanciar el DAO para eliminar relaciones
        RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
        int registrosEliminados = relPerfUsuDAO.deleteByUserId(codigoUsuario);

        // Confirmar éxito
        JOptionPane.showMessageDialog(this, "Se eliminaron " + registrosEliminados + " perfiles del usuario seleccionado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar el usuario seleccionado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar los perfiles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarTActionPerformed

    private void bntconfirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntconfirActionPerformed
        // TODO add your handling code here:
        Object itemSeleccionado = cbousuario.getSelectedItem();
    if (itemSeleccionado == null || itemSeleccionado.toString().equals("Seleccione una opción")) {
        JOptionPane.showMessageDialog(this, "Selecciona un Usuario válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int codigoUsuario = Integer.parseInt(itemSeleccionado.toString());
        ListModel<String> modeloAsignados = lstPerfA.getModel();

        if (modeloAsignados.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "No hay perfiles asignados para confirmar", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        List<Perfil> perfiles = perfilDAO.select();

        // Insertar todos los perfiles asignados
        for (int i = 0; i < modeloAsignados.getSize(); i++) {
            String nombrePerfil = modeloAsignados.getElementAt(i);
            for (Perfil perfil : perfiles) {
                if (perfil.getNombre_perfil().equals(nombrePerfil)) {
                    RelPerfUsu relPerfUsu = new RelPerfUsu();
                    relPerfUsu.setUsuario_codigo(codigoUsuario);
                    relPerfUsu.setPerfil_codigo(perfil.getId_perfil());

                    relPerfUsuDAO.insert(relPerfUsu);
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Perfiles asignados correctamente al usuario", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_bntconfirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int valor=JOptionPane.showConfirmDialog(this,"¿Està seguro de salir del Mantenimiento?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor==JOptionPane.YES_OPTION)
        {
            this.dispose();

        }
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Salio de Mantenimiento RelAplPerf");

    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntconfir;
    private javax.swing.JButton btnAgregarT;
    private javax.swing.JButton btnAgregarU;
    private javax.swing.JButton btnEliminarT;
    private javax.swing.JButton btnEliminarU;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbousuario;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JList<String> lstPerfA;
    private javax.swing.JList<String> lstPerfD;
    private javax.swing.JTextField txtidPerf;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
