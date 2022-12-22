package com.ceiba.factura.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoFacturaAsignarDias {
    private Long idFactura;
    private String fechaIngreso;
    private int cantDias;
    private double valor;
    private String placaFk;
}

