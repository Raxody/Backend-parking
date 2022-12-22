package com.ceiba.factura.puerto.dao;



import com.ceiba.factura.modelo.dto.DtoFacturaAsignarDias;

import java.util.List;

public interface DaoFactura {
    /**
     * Permite listar los vehiculos
     * @return los Vehiculos
     */
    List<DtoFacturaAsignarDias> listar();
}
