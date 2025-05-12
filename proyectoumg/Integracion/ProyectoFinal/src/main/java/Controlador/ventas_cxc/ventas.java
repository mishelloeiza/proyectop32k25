/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.ventas_cxc;

import Controlador.ventas_cxc.*;
import java.math.BigDecimal;

/**
 *
 * @author visitante
 */
public class ventas {


    int id_cliente;
    String no_venta;
    int id_producto ;
    int cantidad;
    Double precio_unitario;
    Double total;

    @Override
    public String toString() {
        return "ventas{" + "id_cliente=" + id_cliente + ", no_venta=" + no_venta + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + ", total=" + total + '}';
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNo_venta() {
        return no_venta;
    }

    public void setNo_venta(String no_venta) {
        this.no_venta = no_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ventas(int id_cliente, String no_venta, int id_producto, int cantidad, Double precio_unitario, Double total) {
        this.id_cliente = id_cliente;
        this.no_venta = no_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.total = total;
    }
    public ventas() {
       
}}