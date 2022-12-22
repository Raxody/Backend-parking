package com.ceiba.factura.servicio.testdatabuilder;

import com.ceiba.factura.modelo.entidad.Factura;

public class FacturaTestDataBuilder {

    private Long id_factura;
    private String fecha_ingreso;
    private int cant_dias;
    private double valor;
    private String placa_fk;

    public FacturaTestDataBuilder() {
        id_factura = 1L;
        fecha_ingreso = "2022-04-12";
        cant_dias = 6;
        valor = 96000;
        placa_fk = "HKZ123";
    }

    public FacturaTestDataBuilder conIdFactura(Long id_factura) {
        this.id_factura = id_factura;
        return this;
    }

    public FacturaTestDataBuilder conFechaIngreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
        return this;
    }

    public FacturaTestDataBuilder conCantidadDias(int cant_dias) {
        this.cant_dias = cant_dias;
        return this;
    }

    public FacturaTestDataBuilder conValor(double valor) {
        this.valor = valor;
        return this;
    }

    public FacturaTestDataBuilder conPlacaFK(String placa_fk) {
        this.placa_fk = placa_fk;
        return this;
    }

    public Factura build() {
        return new Factura(id_factura, fecha_ingreso, cant_dias, valor, placa_fk);
    }
}
