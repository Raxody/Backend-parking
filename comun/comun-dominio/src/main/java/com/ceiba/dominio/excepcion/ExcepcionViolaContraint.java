package com.ceiba.dominio.excepcion;

public class ExcepcionViolaContraint extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionViolaContraint(String message) {
        super(message);
    }
}
