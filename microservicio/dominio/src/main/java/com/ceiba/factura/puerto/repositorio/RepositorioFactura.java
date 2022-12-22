package com.ceiba.factura.puerto.repositorio;

import com.ceiba.factura.modelo.entidad.Factura;

public interface RepositorioFactura {
    /**
     * Permite crear un vehiculo
     * @param factura
     * @return el id generado
     */
    Long crear(Factura factura);

    /**
     * Permite validar si existe un vehiculo con una placa
     * @param placaFk
     * @return si existe o no
     */
    boolean existe(String placaFk);

    /**
     * Permite validar si existe un vehiculo con una placa
     * @param placaFk
     * @return si existe o no
     */
    boolean existeVehiculo(String placaFk);


    /**
     * Permite actualizar un vehiculo
     * @param factura
     */
    void actualizar(Factura factura);

}