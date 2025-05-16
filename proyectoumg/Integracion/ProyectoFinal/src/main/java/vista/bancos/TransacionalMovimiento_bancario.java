/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.bancos;

//import Controlador.bancos.tasa_cambio_diario;
import vista.seguridad.*;
import Modelo.bancos.tipo_operacion_bancariaDAO;
import Controlador.bancos.tipo_operacion_bancaria;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import Modelo.bancos.tasa_cambio_diarioDAO;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Controlador.bancos.movimiento_bancario;
import Modelo.Conexion;
import Modelo.bancos.MovimientoBancarioDAO;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author visitante
 */
public class TransacionalMovimiento_bancario extends javax.swing.JInternalFrame {

    int APLICACION = 105; // Ajustar según corresponda
    private MovimientoBancarioDAO movimientoDAO = new MovimientoBancarioDAO();

    public void llenadoDeCombos() {
        // Implementar si se necesita cargar tipos de cuenta
        cbox_empleado.addItem("Seleccione una opción");
        // Ejemplo: cargar tipos de cuenta desde la base de datos
        cboTipoSaldo = new javax.swing.JComboBox<>();
    cboTipoSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12));
    cboTipoSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Acreedor", "Deudor" }));
    
    // Crear el campo para saldo disponible (solo lectura)
    txtSaldo = new javax.swing.JTextField();
    lblSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12));
    lblSaldo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
    txtSaldo.setEditable(false);
    }

    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Movimiento");
        modelo.addColumn("ID Tipo Cuenta");
        modelo.addColumn("Fecha");
        modelo.addColumn("tipo Saldo");
        modelo.addColumn("Monto");
        List<movimiento_bancario> movimientos = movimientoDAO.select();
        tblMovimientos.setModel(modelo);
        
       String[] dato = new String[5];
        for (movimiento_bancario movimiento : movimientos) {
            dato[0] = String.valueOf(movimiento.getId_movimiento_bancario());
            dato[1] = String.valueOf(movimiento.getId_tipo_cuenta());
            dato[2] = movimiento.getFecha().toString();
            dato[3] = movimiento.getTipoSaldo();
            dato[4] = String.valueOf(movimiento.getMonto());
            modelo.addRow(dato);}
    }

   public void buscarMovimiento() {
       cboTipoSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Acreedor", "Deudor" }));
    try {
        // Validar que el campo de búsqueda no esté vacío
        if (txtbuscado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear y configurar el objeto de consulta
        movimiento_bancario movimientoConsulta = new movimiento_bancario();
        movimientoConsulta.setId_movimiento_bancario(Integer.parseInt(txtbuscado.getText()));
        
        // Ejecutar la consulta
        movimientoConsulta = movimientoDAO.query(movimientoConsulta);
        
        if (movimientoConsulta != null) {
            // Mostrar los datos en los campos correspondientes
            txtIdTipoCuenta.setText(String.valueOf(movimientoConsulta.getId_tipo_cuenta()));
            txtFecha.setText(movimientoConsulta.getFecha().toString());
            
            // Configurar el ComboBox con el valor del movimiento
            cboTipoSaldo.setSelectedItem(movimientoConsulta.getTipoSaldo());
            lblSaldo.setText(String.valueOf(movimientoConsulta.getMonto()));
            
            // Registrar en bitácora
            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Movimiento");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún movimiento con el ID especificado", 
                                         "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al buscar movimiento: " + e.getMessage(), 
                                     "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}


    public TransacionalMovimiento_bancario() {
        initComponents();
        
        // Configuración del campo de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        // Establecer texto guía con fecha actual
        txtFecha.setText(formatter.format(LocalDateTime.now()));
        txtFecha.setForeground(Color.GRAY);
        
        // Listener para manejar el placeholder dinámico
        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtFecha.getForeground().equals(Color.GRAY)) {
                    txtFecha.setText("");
                    txtFecha.setForeground(Color.BLACK);
                }
            }
            
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtFecha.getText().isEmpty()) {
                    txtFecha.setText(formatter.format(LocalDateTime.now()));
                    txtFecha.setForeground(Color.GRAY);
                }
            }
        });
        
        llenadoDeTablas();
        llenadoDeCombos();
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
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        txtbuscado = new javax.swing.JTextField();
        txtIdTipoCuenta = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        cbox_empleado = new javax.swing.JComboBox<>();
        label4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAyudasTasaDecambioDiario = new javax.swing.JButton();
        btnreporteTasaDecambioDiario = new javax.swing.JButton();
        cboTipoSaldo = new javax.swing.JComboBox<>();
        txtTipoSaldo = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Movimineto bancario");
        setVisible(true);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label1.setText("Movimientos Bancarias");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Id_tipo_cuenta");

        txtIdTipoCuenta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdTipoCuenta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tblMovimientos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_tipo_operacion", "tipo_operacion", "descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMovimientos);
        if (tblMovimientos.getColumnModel().getColumnCount() > 0) {
            tblMovimientos.getColumnModel().getColumn(0).setResizable(false);
        }

        cbox_empleado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbox_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_empleadoActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Empleado:");

        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Fecha ");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");

        btnAyudasTasaDecambioDiario.setText("Ayuda");
        btnAyudasTasaDecambioDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudasTasaDecambioDiarioActionPerformed(evt);
            }
        });

        btnreporteTasaDecambioDiario.setText("Reporte");
        btnreporteTasaDecambioDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreporteTasaDecambioDiarioActionPerformed(evt);
            }
        });

        cboTipoSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboTipoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoSaldoActionPerformed(evt);
            }
        });

        txtTipoSaldo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtTipoSaldo.setText("Tipo Saldo");

        lblSaldo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblSaldo.setText("Saldo");

        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSaldo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTipoSaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTipoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblSaldo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label3)
                                    .addComponent(label5))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFecha)
                                    .addComponent(txtIdTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAyudasTasaDecambioDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnreporteTasaDecambioDiario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(70, 70, 70)
                                .addComponent(label4)
                                .addGap(46, 46, 46)
                                .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(253, 253, 253))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTipoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipoSaldo))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSaldo)
                                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnModificar))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar)
                                    .addComponent(btnLimpiar))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAyudasTasaDecambioDiario)
                                    .addComponent(btnreporteTasaDecambioDiario)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    movimiento_bancario movimientoEliminar = new movimiento_bancario();
        movimientoEliminar.setId_movimiento_bancario(Integer.parseInt(txtbuscado.getText()));
        movimientoDAO.delete(movimientoEliminar);
        llenadoDeTablas();
        
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Movimiento");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                                
      try {
        movimiento_bancario movimientoActualizar = new movimiento_bancario();
        
        // Validar campos obligatorios
        if (txtbuscado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe buscar un movimiento primero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (cboTipoSaldo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de saldo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (lblSaldo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Configurar el movimiento
        movimientoActualizar.setId_movimiento_bancario(Integer.parseInt(txtbuscado.getText()));
        movimientoActualizar.setId_tipo_cuenta(Integer.parseInt(txtIdTipoCuenta.getText()));
        movimientoActualizar.setTipoSaldo(cboTipoSaldo.getSelectedItem().toString());
        movimientoActualizar.setMonto(Float.parseFloat(lblSaldo.getText()));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        movimientoActualizar.setFecha(LocalDateTime.parse(txtFecha.getText(), formatter));
        
        // Actualizar movimiento
        movimientoDAO.update(movimientoActualizar);
        llenadoDeTablas();
        
        // Registrar en bitácora
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Modificar Movimiento");
            
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error en formato numérico", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al modificar movimiento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
      buscarMovimiento();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        // TODO add your handling code here:
        try {
            movimiento_bancario movimientoActualizar = new movimiento_bancario();
            movimientoActualizar.setId_movimiento_bancario(Integer.parseInt(txtbuscado.getText()));
            movimientoActualizar.setId_tipo_cuenta(Integer.parseInt(txtIdTipoCuenta.getText()));
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            movimientoActualizar.setFecha(LocalDateTime.parse(txtFecha.getText(), formatter));
            
            movimientoDAO.update(movimientoActualizar);
            llenadoDeTablas();
            
            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Modificar Movimiento");
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
                cbox_empleado.setSelectedIndex(0);
        txtIdTipoCuenta.setText("");
        txtFecha.setText("");
        cboTipoSaldo.setSelectedIndex(0);;
        lblSaldo.setText("");
        txtbuscado.setText("");
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        int resultadoBitacora=0;
        // Se restablece la fecha y hora 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        txtFecha.setText(formatter.format(LocalDateTime.now()));
        txtFecha.setForeground(Color.GRAY);
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Se limpio Transaccional Movimiento bancario");    
   

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbox_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_empleadoActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_empleadoActionPerformed
/*
     // TODO add your handling code here:
        MantenimientoAula ventana = new MantenimientoAula();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    */
    private void btnAyudasTasaDecambioDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudasTasaDecambioDiarioActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\banco\\AyudasTasaCambioDiario.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudasTasaCambioDiario.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAyudasTasaDecambioDiarioActionPerformed
private Connection connectio = null;
    private void btnreporteTasaDecambioDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreporteTasaDecambioDiarioActionPerformed
        // TODO add your handling code here:
        
          Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
            + "/src/main/java/reporte/banco/reporteTasaCambioDiario.jrxml");
            
            print = JasperFillManager.fillReport(report, p, connectio);
            
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
        }
        
        
        
        
    }//GEN-LAST:event_btnreporteTasaDecambioDiarioActionPerformed

    private void cboTipoSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoSaldoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyudasTasaDecambioDiario;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnreporteTasaDecambioDiario;
    private javax.swing.JComboBox<String> cboTipoSaldo;
    private javax.swing.JComboBox<String> cbox_empleado;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tblMovimientos;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdTipoCuenta;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JLabel txtTipoSaldo;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables
}
