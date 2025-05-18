package vista.bancos;

import vista.seguridad.*;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import Modelo.Conexion;
import Modelo.bancos.ConciliacionBancariaDAO;
import Controlador.bancos.conciliacion_bancaria;
import Modelo.bancos.cuentas_bancariasDAO;
import Controlador.bancos.cuentas_bancarias;
import Modelo.bancos.MovimientoBancarioDAO;
import Controlador.bancos.movimiento_bancario;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
//Mishel Loeiza Ramirez
//Modificado por Anderson Rodriguez

public class TransacionalConciliacion_bancaria extends javax.swing.JInternalFrame {

    int APLICACION = 111; // Código de la aplicación para bitácora
    private ConciliacionBancariaDAO conciliacionDAO = new ConciliacionBancariaDAO();
    
    public void llenadoDeCombos() {
        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione", "CONCILIADO", "PENDIENTE"}));
    }

    public void llenadoDeTablas() {
         DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Conciliación");
        modelo.addColumn("ID Cuenta");
        modelo.addColumn("ID Movimiento Bancario");
        modelo.addColumn("Fecha");
        modelo.addColumn("Saldo");
        modelo.addColumn("Saldo Actualizado");
        modelo.addColumn("Estado");

        List<conciliacion_bancaria> lista = conciliacionDAO.select();
        tblMovimientos.setModel(modelo);

        String[] dato = new String[7];
        for (conciliacion_bancaria conc : lista) {
            dato[0] = String.valueOf(conc.getId_conciliacion());
            dato[1] = String.valueOf(conc.getId_cuenta());
            dato[2] = String.valueOf(conc.getId_movimiento_bancario());
            dato[3] = conc.getFecha().toString();
            dato[4] = String.valueOf(conc.getSaldo());
            dato[5] = String.valueOf(conc.getSaldo_actualizado());
            dato[6] = conc.getStatus();
            modelo.addRow(dato);
        }
    }

    public void buscarConciliacion() {
        try {
            if (txtbuscado.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conciliacion_bancaria consulta = new conciliacion_bancaria();
            consulta.setId_conciliacion(Integer.parseInt(txtbuscado.getText()));
            consulta = conciliacionDAO.query(consulta);

            if (consulta != null) {
                txtIdCuenta.setText(String.valueOf(consulta.getId_cuenta()));
                txtIdMovimiento.setText(String.valueOf(consulta.getId_movimiento_bancario()));
                txtFecha.setText(consulta.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                txtSaldo.setText(String.valueOf(consulta.getSaldo()));
                txtSaldoActualizado.setText(String.valueOf(consulta.getSaldo_actualizado()));
                cboStatus.setSelectedItem(consulta.getStatus());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna conciliación", "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public TransacionalConciliacion_bancaria() {
        initComponents();

        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtFecha.getForeground().equals(Color.GRAY)) {
                    txtFecha.setText(""); // Limpiar al seleccionar
                    txtFecha.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtFecha.getText().isEmpty()) {
                    txtFecha.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
                    txtFecha.setForeground(Color.GRAY);
                }
            }
        });

        txtIdCuenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!txtIdCuenta.getText().trim().isEmpty()) {
                    try {
                        int idCuenta = Integer.parseInt(txtIdCuenta.getText().trim());
                        cuentas_bancariasDAO cuentaDAO = new cuentas_bancariasDAO();
                        cuentas_bancarias cuenta = new cuentas_bancarias();
                        cuenta.setId_cuenta(idCuenta);
                        cuenta = cuentaDAO.query(cuenta);
                        if (cuenta != null) {
                            txtSaldo.setText(String.valueOf(cuenta.getSaldo()));
                        } else {
                            JOptionPane.showMessageDialog(null, "ID de cuenta no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID de cuenta debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        txtIdMovimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!txtIdMovimiento.getText().trim().isEmpty()) {
                    try {
                        int idMovimiento = Integer.parseInt(txtIdMovimiento.getText().trim());
                        MovimientoBancarioDAO movimientoDAO = new MovimientoBancarioDAO();
                        movimiento_bancario movimiento = new movimiento_bancario();
                        movimiento.setId_movimiento_bancario(idMovimiento);
                        movimiento = movimientoDAO.query(movimiento);
                        if (movimiento != null) {
                            txtSaldoActualizado.setText(String.valueOf(movimiento.getSaldoActualizado()));
                            // Comparar saldos y establecer estado
                            if (txtSaldo.getText().equals(txtSaldoActualizado.getText())) {
                                cboStatus.setSelectedItem("CONCILIADO");
                            } else {
                                cboStatus.setSelectedItem("PENDIENTE");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "ID de movimiento no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID de movimiento debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
        txtIdCuenta = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        txtFecha = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        btnAyudasTasaDecambioDiario = new javax.swing.JButton();
        btnreporteTasaDecambioDiario = new javax.swing.JButton();
        cboStatus = new javax.swing.JComboBox<>();
        label4 = new javax.swing.JLabel();
        txtIdMovimiento = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        label7 = new javax.swing.JLabel();
        txtSaldoActualizado = new javax.swing.JTextField();
        label8 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Conciliacion Bancaria");
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
        label1.setText("Conciliacion Bancaria");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Id_cuenta");

        txtIdCuenta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdCuenta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

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

        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Fecha ");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

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

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "CONCILIADO", " " }));

        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Id_movimiento");

        txtIdMovimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdMovimiento.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtIdMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMovimientoActionPerformed(evt);
            }
        });

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Saldo Cuenta");

        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSaldo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Saldo Act.");

        txtSaldoActualizado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSaldoActualizado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label4)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAyudasTasaDecambioDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnreporteTasaDecambioDiario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label7)
                                    .addComponent(label8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSaldoActualizado, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(cboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(label1)
                        .addGap(253, 253, 253))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 126, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(txtIdCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(txtIdMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(txtSaldoActualizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnreporteTasaDecambioDiario)
                            .addComponent(btnAyudasTasaDecambioDiario))
                        .addGap(40, 40, 40))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    try {
            conciliacion_bancaria conciliacionEliminar = new conciliacion_bancaria();
            String idConciliacionText = txtbuscado.getText().trim();

            if (idConciliacionText.isEmpty() || !idConciliacionText.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.");
                return;
            }

            conciliacionEliminar.setId_conciliacion(Integer.parseInt(idConciliacionText));
            conciliacionDAO.delete(conciliacionEliminar);
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Conciliación Bancaria");

            JOptionPane.showMessageDialog(this, "Conciliación eliminada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                        
      try {
            conciliacion_bancaria nuevaConciliacion = new conciliacion_bancaria();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (txtIdCuenta.getText().trim().isEmpty() || txtIdMovimiento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID de cuenta y un ID de movimiento", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDateTime fechaActual = LocalDateTime.now();
            nuevaConciliacion.setId_cuenta(Integer.parseInt(txtIdCuenta.getText()));
            nuevaConciliacion.setId_movimiento_bancario(Integer.parseInt(txtIdMovimiento.getText()));
            nuevaConciliacion.setFecha(fechaActual);
            nuevaConciliacion.setSaldo(Float.parseFloat(txtSaldo.getText()));
            nuevaConciliacion.setSaldo_actualizado(Float.parseFloat(txtSaldoActualizado.getText()));
            nuevaConciliacion.setStatus(cboStatus.getSelectedItem().toString());

            conciliacionDAO.insert(nuevaConciliacion);
            llenadoDeTablas();
            txtIdCuenta.setText("");
            txtIdMovimiento.setText("");
            txtSaldo.setText("");
            txtSaldoActualizado.setText("");
            cboStatus.setSelectedIndex(0);
            txtFecha.setText(formatter.format(fechaActual));
            txtFecha.setForeground(Color.BLACK);

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Registro de Conciliación");

            JOptionPane.showMessageDialog(this, "¡Registro exitoso! Fecha: " + formatter.format(fechaActual));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
      buscarConciliacion();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            conciliacion_bancaria conciliacionActualizar = new conciliacion_bancaria();

            if (txtbuscado.getText().trim().isEmpty() || txtIdCuenta.getText().trim().isEmpty() || txtIdMovimiento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID de conciliación, ID de cuenta y ID de movimiento son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conciliacionActualizar.setId_conciliacion(Integer.parseInt(txtbuscado.getText()));
            conciliacionActualizar.setId_cuenta(Integer.parseInt(txtIdCuenta.getText()));
            conciliacionActualizar.setId_movimiento_bancario(Integer.parseInt(txtIdMovimiento.getText()));
            conciliacionActualizar.setSaldo(Float.parseFloat(txtSaldo.getText()));
            conciliacionActualizar.setSaldo_actualizado(Float.parseFloat(txtSaldoActualizado.getText()));

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime fecha = LocalDateTime.parse(txtFecha.getText(), formatter);
                conciliacionActualizar.setFecha(fecha);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha debe ser: yyyy-MM-dd HH:mm:ss\nEjemplo: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conciliacionActualizar.setStatus(cboStatus.getSelectedItem().toString());
            conciliacionDAO.update(conciliacionActualizar);
            llenadoDeTablas();
            JOptionPane.showMessageDialog(this, "Conciliación modificada correctamente");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "IDs deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtIdCuenta.setText("");
        txtIdMovimiento.setText("");
        txtSaldo.setText("");
        txtSaldoActualizado.setText("");
        txtFecha.setText("yyyy-MM-dd HH:mm:ss");
        txtFecha.setForeground(Color.GRAY);
        cboStatus.setSelectedIndex(0);
        txtbuscado.setText("");

        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);

        Bitacora bitacoraRegistro = new Bitacora();
        int resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Limpiar Datos Conciliación Bancaria");

    }//GEN-LAST:event_btnLimpiarActionPerformed
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

    private void txtIdMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMovimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMovimientoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyudasTasaDecambioDiario;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnreporteTasaDecambioDiario;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tblMovimientos;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdCuenta;
    private javax.swing.JTextField txtIdMovimiento;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSaldoActualizado;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    /*private static class conciliacion_bancariaDAO {

        public conciliacion_bancariaDAO() {
        }

        private void delete(conciliacion_bancaria conciliacionEliminar) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }*/
}
