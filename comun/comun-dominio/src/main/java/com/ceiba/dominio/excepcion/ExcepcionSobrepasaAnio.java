package com.ceiba.dominio.excepcion;

public class ExcepcionSobrepasaAnio extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionSobrepasaAnio(String message) {
        super(message);
    }
}
