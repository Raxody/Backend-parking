package com.ceiba.factura.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
@Setter
public class Factura {
    private static final String SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO = "Se debe ingresar la placa del vehiculo";
    private static final String LA_PLACA_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La placa debe tener una longitud mayor o igual a %s";
    private static final int LONGITUD_MINIMA_PLACA = 6;
    private static final String LA_CANTIDAD_DE_DIAS_DEBE_SER_MAYOR_O_IGUAL_A_0 = "La cantidad de dias debe ser mayor o igual a 0";
    private static final String LA_CANTIDAD_DE_DIAS_DEBE_SER_NUMERICO = "La cantidad de dias debe ser numerico";

    private Long idFactura;
    private String fechaIngreso;
    private int cantDias;
    private double valor;
    private String placaFk;

    public Factura(Long idFactura, String fechaIngreso, int cantDias, double valor, String placaFk) {
        validarObligatorio(placaFk, SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO);
        validarLongitud(placaFk, LONGITUD_MINIMA_PLACA, String.format(LA_PLACA_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A,LONGITUD_MINIMA_PLACA));
        validarPositivo(cantDias,LA_CANTIDAD_DE_DIAS_DEBE_SER_MAYOR_O_IGUAL_A_0);
        validarNumerico(String.valueOf(cantDias),LA_CANTIDAD_DE_DIAS_DEBE_SER_NUMERICO);
        this.idFactura = idFactura;
        this.fechaIngreso = fechaIngreso;
        this.cantDias = cantDias;
        this.valor = valor;
        this.placaFk = placaFk;
    }


    public Factura(String placaFk) {
        validarObligatorio(placaFk, SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO);
        validarLongitud(placaFk, LONGITUD_MINIMA_PLACA, String.format(LA_PLACA_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A,LONGITUD_MINIMA_PLACA));
        this.placaFk = placaFk;
    }
}