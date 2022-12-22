package com.ceiba.factura.servicio.testdatabuilder;

import com.ceiba.factura.ComandoFacturaAsignarDias;

import java.time.LocalDateTime;

public class ComandoFacturaTestDataBuilder {
    private Long id_factura;
    private String fecha_ingreso;
    private int cant_dias;
    private double valor;
    private String placa_fk;


    public ComandoFacturaTestDataBuilder() {
        String fechaPartida [] = String.valueOf(LocalDateTime.now()).split("T");
        id_factura = 1L;
        fecha_ingreso = fechaPartida[0];
        placa_fk = "HHH174";
    }

    public ComandoFacturaTestDataBuilder conPlaca(String placa_fk) {
        this.placa_fk = placa_fk;
        return this;
    }

    public ComandoFacturaTestDataBuilder conDias(int cant_dias) {
        this.cant_dias = cant_dias;
        return this;
    }

    public ComandoFacturaTestDataBuilder conFecha(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
        return this;
    }

    public ComandoFacturaTestDataBuilder conIdFactura(Long id_factura) {
        this.id_factura = id_factura;
        return this;
    }

    public ComandoFacturaAsignarDias build() {
        return new ComandoFacturaAsignarDias(id_factura,fecha_ingreso, cant_dias, valor,placa_fk);
    }
}
