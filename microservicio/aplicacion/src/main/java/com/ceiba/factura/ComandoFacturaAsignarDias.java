package com.ceiba.factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoFacturaAsignarDias {
    private Long idFactura;
    private String fechaIngreso;
    private int cantDias;
    private double valor;
    private String placaFk;
}
