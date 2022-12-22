package com.ceiba.factura.consulta;

import com.ceiba.factura.modelo.dto.DtoFacturaAsignarDias;
import com.ceiba.factura.puerto.dao.DaoFactura;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarFactura {

    private final DaoFactura daoFactura;

    public ManejadorListarFactura(DaoFactura daoFactura) {
        this.daoFactura = daoFactura;
    }

    public List<DtoFacturaAsignarDias> ejecutar() {

        return this.daoFactura.listar();
    }
}