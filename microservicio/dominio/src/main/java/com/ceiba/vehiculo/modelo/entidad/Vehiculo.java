package com.ceiba.vehiculo.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;
import static com.ceiba.dominio.ValidadorArgumento.validarNumerico;

@Getter
public class Vehiculo {


    private static final String SE_DEBE_INGRESAR_EL_ID_DEL_PROPIETARIO = "Se debe ingresar el id del propietario";
    private static final String SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO = "Se debe ingresar la placa del vehiculo";
    private static final String SE_DEBE_INGRESAR_EL_TELEFONO_DE_CONTACTO_DEL_PROPIETARIO = "Se debe ingresar el teléfono de contacto del propietario";
    private static final String LA_PLACA_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La placa debe tener una longitud mayor o igual a %s";
    private static final String EL_TELEFONO_DE_CONTACTO_DEBE_SER_NUMERICO = "El teléfono de contacto debe ser numerico";
    private static final int LONGITUD_MINIMA_PLACA = 6;
    private static final String EL_TELEFONO_DE_CONTACTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "el teléfono de contacto debe tener una longitud mayor o igual a %s";
    private static final int LONGITUD_MINIMA_TELEFONO_DE_CONTACTO = 10;
    private static final String LA_IDENTIFICACION_DEL_PROPIETARIO_DEBE_SER_NUMERICO = "La identificación del propietario debe ser numerico";

    private String placa;
    private Long idPropietario;
    private Long telefonoContacto;

    public Vehiculo(String placa, Long idPropietario, Long telefonoContacto) {
        validarObligatorio(idPropietario, SE_DEBE_INGRESAR_EL_ID_DEL_PROPIETARIO);
        validarNumerico(String.valueOf(idPropietario),LA_IDENTIFICACION_DEL_PROPIETARIO_DEBE_SER_NUMERICO);
        validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO);
        validarLongitud(placa, LONGITUD_MINIMA_PLACA, String.format(LA_PLACA_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A,LONGITUD_MINIMA_PLACA));
        validarObligatorio(telefonoContacto, SE_DEBE_INGRESAR_EL_TELEFONO_DE_CONTACTO_DEL_PROPIETARIO);
        validarLongitud(String.valueOf(telefonoContacto),LONGITUD_MINIMA_TELEFONO_DE_CONTACTO,String.format(EL_TELEFONO_DE_CONTACTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A,LONGITUD_MINIMA_TELEFONO_DE_CONTACTO));
        validarNumerico(String.valueOf(telefonoContacto),EL_TELEFONO_DE_CONTACTO_DEBE_SER_NUMERICO);

        this.placa = placa;
        this.idPropietario = idPropietario;
        this.telefonoContacto = telefonoContacto;
    }
}
