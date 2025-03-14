/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import datos.EmpleadoDAO;
import datos.AsignacioncursosmaestroDAO;
import domain.Empleado;
import domain.Asignacioncursosmaestro;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;

/**
 *
 * @author visitante
 */
public class MantenimientoAsignacioncursosmaestros extends javax.swing.JInternalFrame {

    public void llenadoDeCombos() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleados = empleadoDAO.select();
        cbox_empleado.addItem("Seleccione una opción");
        for (int i = 0; i < empleados.size(); i++) {
            cbox_empleado.addItem(empleados.get(i).getNombreEmpleado());
        }
    }

    public void llenadoDeTablas() {
        System.out.println("Llenado de tablas iniciado.");
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Carrera");
        modelo.addColumn("ID Sede");
        modelo.addColumn("ID Jornada");
        modelo.addColumn("ID Seccion");
        modelo.addColumn("ID Aula");
        modelo.addColumn("ID Curso");
        modelo.addColumn("ID Maestro");
        AsignacioncursosmaestroDAO asignacioncursosmaestroDAO = new AsignacioncursosmaestroDAO();
        List<Asignacioncursosmaestro> asignacioncursosmaestros = asignacioncursosmaestroDAO.select();
        tablaAsignacioncursosmaestros.setModel(modelo);
        String[] dato = new String[7];
        for (int i = 0; i < asignacioncursosmaestros.size(); i++) {
            dato[0] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoCarrera());
            dato[1] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoSede());
            dato[2] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoJornada());
            dato[3] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoSeccion());
            dato[4] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoAula());
            dato[5] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoCurso());
            dato[6] = Integer.toString(asignacioncursosmaestros.get(i).getCodigoMaestro());
            //System.out.println("carrera:" + asignacioncursosmaestros);
            modelo.addRow(dato);
        }
        System.out.println("Llenado de tablas completado.");
    }

    public void buscarAsignacioncursosmaestro() {
        Asignacioncursosmaestro asignacioncursosmaestroAConsultar = new Asignacioncursosmaestro();
        AsignacioncursosmaestroDAO asignacioncursosmaestroDAO = new AsignacioncursosmaestroDAO();
        // Obtener el texto del campo txtbuscado y dividirlo en partes usando la coma como delimitador
        String[] llaves = txtbuscado.getText().split(",");
        // Asegurarse de que hay suficientes valores en el arreglo llaves antes de establecerlos
        if (llaves.length == 7) {
            asignacioncursosmaestroAConsultar.setCodigoCarrera(Integer.parseInt(llaves[0]));
            asignacioncursosmaestroAConsultar.setCodigoSede(Integer.parseInt(llaves[1]));
            asignacioncursosmaestroAConsultar.setCodigoJornada(Integer.parseInt(llaves[2]));
            asignacioncursosmaestroAConsultar.setCodigoSeccion(Integer.parseInt(llaves[3]));
            asignacioncursosmaestroAConsultar.setCodigoAula(Integer.parseInt(llaves[4]));
            asignacioncursosmaestroAConsultar.setCodigoCurso(Integer.parseInt(llaves[5]));
            asignacioncursosmaestroAConsultar.setCodigoMaestro(Integer.parseInt(llaves[6]));
        
             // Realizar la consulta usando el objeto DAO
            asignacioncursosmaestroAConsultar = asignacioncursosmaestroDAO.query(asignacioncursosmaestroAConsultar);
        
            // Establecer los valores en los campos de texto correspondientes
            txtIdCarrera.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoCarrera()));
            txtIdSede.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoSede()));
            txtIdJornada.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoJornada()));
            txtIdSeccion.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoSeccion()));
            txtIdAula.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoAula()));
            txtIdCurso.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoCurso()));
            txtIdMaestro.setText(String.valueOf(asignacioncursosmaestroAConsultar.getCodigoMaestro()));
        } else {
        System.out.println("Error: El número de llaves no es correcto.");
        // Manejar el error según sea necesario
        }
    }

    public MantenimientoAsignacioncursosmaestros() {
        System.out.println("Constructor de MantenimientoAsignacioncursosmaestros llamado.");
        initComponents();
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
        txtIdCarrera = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAsignacioncursosmaestros = new javax.swing.JTable();
        cbox_empleado = new javax.swing.JComboBox<>();
        label4 = new javax.swing.JLabel();
        txtIdSede = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtIdJornada = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        txtIdSeccion = new javax.swing.JTextField();
        txtIdCurso = new javax.swing.JTextField();
        label9 = new javax.swing.JLabel();
        txtIdAula = new javax.swing.JTextField();
        txtIdMaestro = new javax.swing.JTextField();
        label10 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Asignacioncursosmaestros");
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
        label1.setText("Asignacioncursosmaestros");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Id Carrera");

        txtbuscado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscadoActionPerformed(evt);
            }
        });

        txtIdCarrera.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdCarrera.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaAsignacioncursosmaestros.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaAsignacioncursosmaestros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaAsignacioncursosmaestros);

        cbox_empleado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbox_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_empleadoActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Empleado:");

        txtIdSede.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdSede.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Id Sede");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtIdJornada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdJornada.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Id Jornada");

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Id Aula");

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Id Seccion");

        txtIdSeccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdSeccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtIdCurso.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdCurso.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label9.setText("Id Curso");

        txtIdAula.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdAula.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtIdMaestro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdMaestro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("Id Maestro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3)
                            .addComponent(label5)
                            .addComponent(label6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdSede, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(txtIdCarrera)
                            .addComponent(txtIdJornada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8)
                            .addComponent(label7)
                            .addComponent(label9))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdAula)
                            .addComponent(txtIdSeccion)
                            .addComponent(txtIdCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label10)
                        .addGap(29, 29, 29)
                        .addComponent(txtIdMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addGap(294, 294, 294))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(135, 135, 135)
                .addComponent(label4)
                .addGap(46, 46, 46)
                .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4)
                    .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        AsignacioncursosmaestroDAO asignacioncursosmaestroDAO = new AsignacioncursosmaestroDAO();
        Asignacioncursosmaestro asignacioncursosmaestroAEliminar = new Asignacioncursosmaestro();
   
        // Obtener el texto del campo txtbuscado y dividirlo en partes usando la coma como delimitador
        String[] llaves = txtbuscado.getText().split(",");
    
         // Asegurarse de que hay suficientes valores en el arreglo llaves antes de establecerlos
        if (llaves.length == 7) {
            asignacioncursosmaestroAEliminar.setCodigoCarrera(Integer.parseInt(llaves[0]));
            asignacioncursosmaestroAEliminar.setCodigoSede(Integer.parseInt(llaves[1]));
            asignacioncursosmaestroAEliminar.setCodigoJornada(Integer.parseInt(llaves[2]));
            asignacioncursosmaestroAEliminar.setCodigoSeccion(Integer.parseInt(llaves[3]));
            asignacioncursosmaestroAEliminar.setCodigoAula(Integer.parseInt(llaves[4]));
            asignacioncursosmaestroAEliminar.setCodigoCurso(Integer.parseInt(llaves[5]));
            asignacioncursosmaestroAEliminar.setCodigoMaestro(Integer.parseInt(llaves[6]));
            // Eliminar el registro usando el objeto DAO
            asignacioncursosmaestroDAO.delete(asignacioncursosmaestroAEliminar);
            // Actualizar la tabla
            llenadoDeTablas();
        } else {
            System.out.println("Error: El número de llaves no es correcto.");
            // Manejar el error según sea necesario
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        AsignacioncursosmaestroDAO asignacioncursosmaestroDAO = new AsignacioncursosmaestroDAO();
        Asignacioncursosmaestro asignacioncursosmaestroAInsertar = new Asignacioncursosmaestro();
        asignacioncursosmaestroAInsertar.setCodigoCarrera(Integer.parseInt(txtIdCarrera.getText()));
        asignacioncursosmaestroAInsertar.setCodigoSede(Integer.parseInt(txtIdSede.getText()));
        asignacioncursosmaestroAInsertar.setCodigoJornada(Integer.parseInt(txtIdJornada.getText()));
        asignacioncursosmaestroAInsertar.setCodigoSeccion(Integer.parseInt(txtIdSeccion.getText()));
        asignacioncursosmaestroAInsertar.setCodigoAula(Integer.parseInt(txtIdAula.getText()));
        asignacioncursosmaestroAInsertar.setCodigoCurso(Integer.parseInt(txtIdCurso.getText()));
        asignacioncursosmaestroAInsertar.setCodigoMaestro(Integer.parseInt(txtIdMaestro.getText()));
        asignacioncursosmaestroDAO.insert(asignacioncursosmaestroAInsertar);
        llenadoDeTablas();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarAsignacioncursosmaestro();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        AsignacioncursosmaestroDAO asignacioncursosmaestroDAO = new AsignacioncursosmaestroDAO();
        Asignacioncursosmaestro AsignacioncursosmaestroAActualizar = new Asignacioncursosmaestro();
        // Obtener el texto del campo txtbuscado y dividirlo en partes usando la coma como delimitador
        String[] llaves = txtbuscado.getText().split(",");
    
        // Asegurarse de que hay suficientes valores en el arreglo llaves antes de establecerlos
        if (llaves.length == 7) {
            AsignacioncursosmaestroAActualizar.setCodigoCarrera(Integer.parseInt(llaves[0]));
            AsignacioncursosmaestroAActualizar.setCodigoSede(Integer.parseInt(llaves[1]));
            AsignacioncursosmaestroAActualizar.setCodigoJornada(Integer.parseInt(llaves[2]));
            AsignacioncursosmaestroAActualizar.setCodigoSeccion(Integer.parseInt(llaves[3]));
            AsignacioncursosmaestroAActualizar.setCodigoAula(Integer.parseInt(llaves[4]));
            AsignacioncursosmaestroAActualizar.setCodigoCurso(Integer.parseInt(llaves[5]));
            AsignacioncursosmaestroAActualizar.setCodigoMaestro(Integer.parseInt(llaves[6]));
            // Establecer otros valores necesarios para la actualización
            AsignacioncursosmaestroAActualizar.setCodigoCarrera(Integer.parseInt(txtIdCarrera.getText()));
            AsignacioncursosmaestroAActualizar.setCodigoSede(Integer.parseInt(txtIdSede.getText()));
            AsignacioncursosmaestroAActualizar.setCodigoJornada(Integer.parseInt(txtIdJornada.getText()));
            AsignacioncursosmaestroAActualizar.setCodigoSeccion(Integer.parseInt(txtIdSeccion.getText()));
            AsignacioncursosmaestroAActualizar.setCodigoAula(Integer.parseInt(txtIdAula.getText()));
            AsignacioncursosmaestroAActualizar.setCodigoCurso(Integer.parseInt(txtIdCurso.getText()));
            AsignacioncursosmaestroAActualizar.setCodigoMaestro(Integer.parseInt(txtIdMaestro.getText()));
            // Actualizar el registro usando el objeto DAO
            asignacioncursosmaestroDAO.update(AsignacioncursosmaestroAActualizar);
            // Actualizar la tabla
            llenadoDeTablas();
        } else {
            System.out.println("Error: El número de llaves no es correcto.");
            // Manejar el error según sea necesario
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        cbox_empleado.setSelectedIndex(0);
        txtIdCarrera.setText("");
        txtIdSede.setText("");
        txtIdJornada.setText("");
        txtIdSeccion.setText("");
        txtIdAula.setText("");
        txtIdCurso.setText("");
        txtIdMaestro.setText("");
        txtbuscado.setText("");
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbox_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_empleadoActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_empleadoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\ProcesoMayor.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\ProcesoMayor.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbuscadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbox_empleado;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label10;
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
    private javax.swing.JTable tablaAsignacioncursosmaestros;
    private javax.swing.JTextField txtIdAula;
    private javax.swing.JTextField txtIdCarrera;
    private javax.swing.JTextField txtIdCurso;
    private javax.swing.JTextField txtIdJornada;
    private javax.swing.JTextField txtIdMaestro;
    private javax.swing.JTextField txtIdSeccion;
    private javax.swing.JTextField txtIdSede;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables
}
