/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ventas_cxc;


import Controlador.seguridad.RelPerfApl;
import Modelo.seguridad.RelPerfAplDAO;
import Modelo.seguridad.AplicacionDAO;
import Controlador.seguridad.Aplicacion;
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


/**
 *
 * @author visitante
 */
public class TransaccionalVentasCC extends javax.swing.JInternalFrame {
int APLICACION=301;


public void llenadoDeCombos() {
         
        PerfilDAO perfilDAO = new PerfilDAO();
        List<Perfil> salon = perfilDAO.select();
        cboperfil.addItem("Seleccione un Id");
        for (int i = 0; i < salon.size(); i++) {
            cboperfil.addItem(String.valueOf(salon.get(i).getId_perfil()));
            cboperfil.addActionListener(e -> {
    // mandamos a traer el ID
    String idSelec = cboperfil.getSelectedItem().toString();
    int idSeleccionado = Integer.parseInt(idSelec);
    // Busca el perfil en la lista
    for (Perfil perfil : salon) {
        if (perfil.getId_perfil() == idSeleccionado) {
            txtper.setText(perfil.getNombre_perfil());
            
            break;
        }
    }});
            
    }
    AplicacionDAO aplicacionDAO = new AplicacionDAO();
    List<Aplicacion> aplicaciones = aplicacionDAO.select(); 
    DefaultListModel<String> modelo = new DefaultListModel<>();
    DefaultListModel<String> modelo2 = new DefaultListModel<>();
    //Recorre la lista :v
    for (Aplicacion app : aplicaciones) {
    modelo.addElement(app.getNombre_aplicacion()); 
}
lstAplicD.setModel(modelo);
lstAplicA.setModel(modelo2);

// Listener para detectar la selección del usuario
lstAplicA.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Evita doble evento
            String nombreAppSeleccionada = lstAplicA.getSelectedValue();
            
            if (nombreAppSeleccionada != null) {
                // Buscar el ID de la aplicación seleccionada
                for (Aplicacion app : aplicaciones) {
                    if (app.getNombre_aplicacion().equals(nombreAppSeleccionada)) {
                        int idAppSeleccionada = app.getId_aplicacion();
                        System.out.println("ID seleccionado: " + idAppSeleccionada); // Opcional: para debug
                        txtidApl.setText(String.valueOf(idAppSeleccionada)); // Asignar el ID a un campo
                        break;
                    }
                }
            }
        }
    }
}); 

    }




public void llenadoperfilesaplicaciones(){
// 1. Obtener todas las aplicaciones disponibles

AplicacionDAO aplicacionDAO = new AplicacionDAO();
List<Aplicacion> aplicaciones = aplicacionDAO.select();

// 2. Modelos para las listas
DefaultListModel<String> modelo = new DefaultListModel<>(); // Para listAplicD (todas las apps)
DefaultListModel<String> modelo2 = new DefaultListModel<>(); // Para listAplicA (apps del perfil)

// 3. Llenar listAplicD con TODAS las aplicaciones
for (Aplicacion aplicacion : aplicaciones) {
    modelo.addElement(aplicacion.getNombre_aplicacion());
}
lstAplicD.setModel(modelo);

// 4. Listener para cuando seleccionen un perfil
cboperfil.addActionListener(e -> {
    // Limpiar modelo2 antes de agregar nuevos elementos
    modelo2.clear();
    
    try {
        // Obtener perfil seleccionado
        String idSelec = cboperfil.getSelectedItem().toString();
        int idSeleccionado = Integer.parseInt(idSelec);
        
        // Obtener relaciones perfil-aplicación
        RelPerfAplDAO relPerfAplDAO = new RelPerfAplDAO();
        List<RelPerfApl> relaciones = relPerfAplDAO.select();
        
        // Filtrar aplicaciones del perfil seleccionado
        for (RelPerfApl relacion : relaciones) {
            if (relacion.getPerfil_codigo() == idSeleccionado) {
                // Buscar la aplicación por ID
                for (Aplicacion app : aplicaciones) {
                    if (app.getId_aplicacion()== relacion.getAplicacion_codigo()) {
                        modelo2.addElement(app.getNombre_aplicacion());
                        break; // Salir del for interno
                    }
                }
            }
        }
        
        lstAplicA.setModel(modelo2);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar aplicaciones: " + ex.getMessage());
    }
});

}


    public void llenarlistaUnoaUno() {
    int indice=0;
    String cadena; 
     
    indice = lstAplicD.getSelectedIndex();
    if (indice != -1) {
        
    cadena = (String) lstAplicD.getSelectedValue();
    DefaultListModel<String> modeloAplA;
    
    if (lstAplicA.getModel() == null) {
        modeloAplA = new DefaultListModel<>();
        lstAplicA.setModel(modeloAplA);
        
    } else {
        
        modeloAplA = (DefaultListModel<String>) lstAplicA.getModel();
                
    }
    modeloAplA.addElement(cadena);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una Aplicacion", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Una Aplicaciones");    
   
    }
    
    public void llenarlista() {
    AplicacionDAO aplicacionDAO = new AplicacionDAO();
    List<Aplicacion> aplicaciones = aplicacionDAO.select(); 
    DefaultListModel<String> modelo = new DefaultListModel<>(); 
    //Recorre la lista :v
    for (Aplicacion app : aplicaciones) {
    modelo.addElement(app.getNombre_aplicacion()); 
}
lstAplicA.setModel(modelo);
        
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Todas Las Aplicaciones");    
   
    }
    
    public void vaciarlista() {
 
    DefaultListModel<String> modelo = new DefaultListModel<>();
    
    modelo.clear();
    lstAplicA.setModel(modelo);
      
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar Todas Las Aplicaciones");    
   
    }
    
    public void vaciarlistaUnoaUno() {
    
    int indice = lstAplicA.getSelectedIndex();
    if (indice != -1) {
        ((DefaultListModel<String>) lstAplicA.getModel()).remove(indice);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una Aplicacion", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
     
    
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar una Aplicacion");    
   
    }
    
    public TransaccionalVentasCC() {
        initComponents(); 
        llenadoDeCombos(); 
        llenadoperfilesaplicaciones();
        
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
        btnAsignarT = new javax.swing.JButton();
        btnAsignarU = new javax.swing.JButton();
        btnEliminarT = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        btnEliminarU = new javax.swing.JButton();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        label6 = new javax.swing.JLabel();
        cboperfil = new javax.swing.JComboBox<>();
        txtper = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAplicA = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstAplicD = new javax.swing.JList<>();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        txtidApl = new javax.swing.JTextField();
        label9 = new javax.swing.JLabel();
        label10 = new javax.swing.JLabel();
        label11 = new javax.swing.JLabel();
        label12 = new javax.swing.JLabel();
        label13 = new javax.swing.JLabel();
        txtper1 = new javax.swing.JTextField();
        label14 = new javax.swing.JLabel();
        label15 = new javax.swing.JLabel();
        cboperfil1 = new javax.swing.JComboBox<>();
        txtper3 = new javax.swing.JTextField();
        label16 = new javax.swing.JLabel();
        txtper4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        label17 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MantenimientoAplicacion");
        setVisible(true);

        btnAsignarT.setBackground(new java.awt.Color(153, 153, 255));
        btnAsignarT.setText("▶▶");
        btnAsignarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarTActionPerformed(evt);
            }
        });

        btnAsignarU.setBackground(new java.awt.Color(153, 153, 255));
        btnAsignarU.setText("▶");
        btnAsignarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarUActionPerformed(evt);
            }
        });

        btnEliminarT.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarT.setText("◀◀️");
        btnEliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Productos Asignados");

        btnEliminarU.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarU.setText("◀️");
        btnEliminarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Cliente a selecionar");

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Asignar");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        btnEditar.setBackground(new java.awt.Color(204, 255, 204));
        btnEditar.setText("$");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Productos Disponibles");

        cboperfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboperfilActionPerformed(evt);
            }
        });

        txtper.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper.setEnabled(false);
        txtper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtperActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(lstAplicA);

        lstAplicD.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lstAplicDComponentAdded(evt);
            }
        });
        lstAplicD.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lstAplicDAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(lstAplicD);

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Quitar");

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Generar Venta");

        btnSalir.setBackground(new java.awt.Color(255, 153, 153));
        btnSalir.setText("🏃");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtidApl.setEnabled(false);

        label9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label9.setText("Id del producto seleccionado");

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("Nombre");

        label11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label11.setText("Id");

        label12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label12.setText("Precio producto:");

        label13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label13.setText("Cantidad Producto:");

        txtper1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper1.setEnabled(false);
        txtper1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtper1ActionPerformed(evt);
            }
        });

        label14.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label14.setText("Vendedor a selecionar");

        label15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label15.setText("Nombre");

        cboperfil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboperfil1ActionPerformed(evt);
            }
        });

        txtper3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper3.setEnabled(false);
        txtper3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtper3ActionPerformed(evt);
            }
        });

        label16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label16.setText("Id");

        txtper4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper4.setEnabled(false);
        txtper4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtper4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_venta", "id_vendedor", "Nombre", "Apellido", "Producto", "Cantidad", "Precio", "Saldo Anterior", "Plazo", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        label17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label17.setText("   Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(63, 63, 63)
                                                .addComponent(cboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtper, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(label10)
                                                .addGap(112, 112, 112)
                                                .addComponent(label11)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(label4)
                                        .addGap(88, 88, 88)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(label9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtidApl, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(66, 66, 66))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(345, 345, 345))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(label6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label3)
                                .addGap(83, 83, 83))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnAsignarT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnEliminarT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnEliminarU, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(label7))
                                            .addComponent(btnAsignarU, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label5)
                                        .addGap(76, 76, 76)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label12)
                                        .addGap(58, 58, 58)
                                        .addComponent(txtper4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label13)
                                        .addGap(42, 42, 42)
                                        .addComponent(txtper1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboperfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtper3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label14)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(label15)
                                                .addGap(110, 110, 110)
                                                .addComponent(label16)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(label10)
                                        .addGap(2, 2, 2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(label11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtidApl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(label9)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label6)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(label5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAsignarT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAsignarU)
                                .addGap(24, 24, 24)
                                .addComponent(label7)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarU)
                                .addGap(18, 18, 18)
                                .addComponent(label8)
                                .addGap(4, 4, 4)
                                .addComponent(btnEditar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(label15)
                                            .addComponent(label16)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label17)
                                        .addGap(4, 4, 4)
                                        .addComponent(btnSalir)))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboperfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtper3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addComponent(txtper1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label12)
                                        .addComponent(txtper4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(22, 22, 22)
                                    .addComponent(label13))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnAsignarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarTActionPerformed
        // TODO add your handling code here:
   llenarlista();
    }//GEN-LAST:event_btnAsignarTActionPerformed

    private void btnAsignarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarUActionPerformed
    llenarlistaUnoaUno();
    }//GEN-LAST:event_btnAsignarUActionPerformed

    private void btnEliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTActionPerformed
//        // TODO add your handling code here:
vaciarlista();
txtidApl.setText(" "); 
DefaultListModel<String> modelo2 = new DefaultListModel<>();
lstAplicA.setModel(modelo2);
    }//GEN-LAST:event_btnEliminarTActionPerformed

    private void btnEliminarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUActionPerformed
        vaciarlistaUnoaUno();
        txtidApl.setText(" ");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarUActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
       
        
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignacion DE Permiso a Perfil");    
   
    }//GEN-LAST:event_btnEditarActionPerformed

 
    
    private void lstAplicDComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lstAplicDComponentAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lstAplicDComponentAdded

    private void lstAplicDAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lstAplicDAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lstAplicDAncestorAdded

    private void cboperfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboperfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboperfilActionPerformed

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

    private void txtperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtperActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtperActionPerformed

    private void txtper1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtper1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtper1ActionPerformed

    private void cboperfil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboperfil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboperfil1ActionPerformed

    private void txtper3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtper3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtper3ActionPerformed

    private void txtper4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtper4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtper4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarT;
    private javax.swing.JButton btnAsignarU;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminarT;
    private javax.swing.JButton btnEliminarU;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboperfil;
    private javax.swing.JComboBox<String> cboperfil1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label13;
    private javax.swing.JLabel label14;
    private javax.swing.JLabel label15;
    private javax.swing.JLabel label16;
    private javax.swing.JLabel label17;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JList<String> lstAplicA;
    private javax.swing.JList<String> lstAplicD;
    private javax.swing.JTextField txtidApl;
    private javax.swing.JTextField txtper;
    private javax.swing.JTextField txtper1;
    private javax.swing.JTextField txtper3;
    private javax.swing.JTextField txtper4;
    // End of variables declaration//GEN-END:variables
}
