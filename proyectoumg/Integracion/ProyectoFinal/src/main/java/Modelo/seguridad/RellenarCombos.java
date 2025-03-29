/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Modelo.Conexion;
import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author visitante
 */

       public class RellenarCombos {

           public void RellenarComboBox(String tabla, String valor, JComboBox<String> combo) throws SQLException {
               // Limpiar el combo y agregar opción inicial
               combo.removeAllItems();
               combo.addItem("Seleccione una opción");

               String sql = "SELECT " + valor + " FROM " + tabla;  // Selecciona solo la columna necesaria

               // Usar try-with-resources para cierre automático
               try (Connection conexion = Conexion.getConnection(); Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {

                   while (rs.next()) {
                       combo.addItem(rs.getString(valor)); // Llenar con datos de la BD
                   }
               } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
       }

