package com.ceiba.factura.modelo;

public class CalculoValorParqueadero {
    private final Calendario calendario;
    private final String fecha;
    private int dia;
    private int mes;
    private static final long VALOR_DIAS_FINES_DE_SEMANA = 16000;
    private static final long VALOR_DIAS_ENTRE_SEMANA = 12000;
    private static final long VALOR_EXTRA_FINES_DE_SEMANA_POR_FESTIVOS = 20000 - VALOR_DIAS_FINES_DE_SEMANA;
    private static final long VALOR_EXTRA_ENTRE_SEMANA_POR_FESTIVOS = 20000 - VALOR_DIAS_ENTRE_SEMANA;

    public CalculoValorParqueadero(String fecha) {
        this.fecha = fecha;
        calendario = new Calendario();
    }

    private int obteneParteFecha(int posicionFecha) {
        String[] partes = fecha.split("-");
        return Integer.parseInt(partes[posicionFecha]);
    }

    private void asignadorRespectivaParteFecha() {
        dia = obteneParteFecha(2);
        mes = obteneParteFecha(1);
        mes--;
    }

    private int obtenerCantidadExtraDiasFestivosEntreSemana(int diasParqueo) {
        int diasFestivosEntreSemana = 0;
        asignadorRespectivaParteFecha();
        for (int i = 0; i < diasParqueo; i++) {
            if (dia == calendario.getCalendario2022().get(mes).getDias()) {
                mes++;
                dia = 0;
            }
            for (int j = 0; j < calendario.getCalendario2022().get(mes).getDiasFestivosEntreSemana().size(); j++) {
                if (dia <= calendario.getCalendario2022().get(mes).getDias() &&
                        calendario.getCalendario2022().get(mes).getDiasFestivosEntreSemana().get(j) == dia) {
                    diasFestivosEntreSemana++;
                    break;
                }
            }
            dia++;
        }
        mes++;
        return diasFestivosEntreSemana;
    }

    private int obtenerCantidadExtraDiasFestivosFinesSemana(int diasParqueo) {
        int diasFestivosFinesSemana = 0;
        asignadorRespectivaParteFecha();
        for (int i = 0; i < diasParqueo; i++) {
            if (dia == calendario.getCalendario2022().get(mes).getDias()) {
                mes++;
                dia = 0;
            }
            for (int j = 0; j < calendario.getCalendario2022().get(mes).getDiasFestivosFinesSemana().size(); j++) {
                if (dia <= calendario.getCalendario2022().get(mes).getDias() &&
                        calendario.getCalendario2022().get(mes).getDiasFestivosFinesSemana().get(j) == dia) {
                    diasFestivosFinesSemana++;
                    break;
                }
            }
            dia++;
        }
        mes++;

        return diasFestivosFinesSemana;
    }

    private int obtenerCantidadDiasFinesDeSemana(int diasParqueo) {
        int diasFinesSemana = 0;
        asignadorRespectivaParteFecha();
        for (int i = 0; i < diasParqueo; i++) {
            if (dia == calendario.getCalendario2022().get(mes).getDias()) {
                mes++;
                dia = 0;
            }
            for (int j = 0; j < calendario.getCalendario2022().get(mes).getDiasFinesSemana().size(); j++) {
                if (dia <= calendario.getCalendario2022().get(mes).getDias() &&
                        calendario.getCalendario2022().get(mes).getDiasFinesSemana().get(j) == dia) {
                    diasFinesSemana++;
                    break;
                }
            }
            dia++;
        }
        mes++;
        return diasFinesSemana;
    }

    private int obtenerCantidadDiasEntreSemana(int diasParqueo) {
        int diasEntreSemana = 0;
        asignadorRespectivaParteFecha();
        for (int i = 0; i < diasParqueo; i++) {
            boolean esFinDeSemana = false;
            if (dia == calendario.getCalendario2022().get(mes).getDias()) {
                mes++;
                dia = 0;
            }
            for (int j = 0; j < calendario.getCalendario2022().get(mes).getDiasFinesSemana().size(); j++) {
                if (dia <= calendario.getCalendario2022().get(mes).getDias() &&
                        calendario.getCalendario2022().get(mes).getDiasFinesSemana().get(j) == dia) {
                    esFinDeSemana = true;
                    break;
                }
            }
            if (!esFinDeSemana) {
                diasEntreSemana++;
            }
            dia++;
        }
        mes++;
        return diasEntreSemana;
    }

    private double aplicaDescuentoMayorSieteDias(int diasParqueo) {
        if (diasParqueo >= 7) {
            return 0.6;
        }
        return 0;
    }

    private boolean rangoUsoParqueaderoPermitido(int diasParqueo) {
        asignadorRespectivaParteFecha();
        int topeDiasParaParquear = 0;
        for (int i = mes; i < calendario.getCalendario2022().size(); i++) {
            topeDiasParaParquear += calendario.getCalendario2022().get(i).getDias();
        }
        topeDiasParaParquear -= dia;
        return diasParqueo <= topeDiasParaParquear;

    }

    public double obtenerValorPagoPorDiasParqueo(int diasParqueo) {
    if (this.aplicaDescuentoMayorSieteDias(diasParqueo) == 0.6 &&
                rangoUsoParqueaderoPermitido(diasParqueo)) {
            return ((this.obtenerCantidadDiasEntreSemana(diasParqueo) * VALOR_DIAS_ENTRE_SEMANA) +
                    (this.obtenerCantidadExtraDiasFestivosEntreSemana(diasParqueo) * VALOR_EXTRA_ENTRE_SEMANA_POR_FESTIVOS) +
                    (this.obtenerCantidadDiasFinesDeSemana(diasParqueo) * VALOR_DIAS_FINES_DE_SEMANA) +
                    (this.obtenerCantidadExtraDiasFestivosFinesSemana(diasParqueo) * VALOR_EXTRA_FINES_DE_SEMANA_POR_FESTIVOS)) *
                    this.aplicaDescuentoMayorSieteDias(diasParqueo);
        } else if (rangoUsoParqueaderoPermitido(diasParqueo)) {
            return ((this.obtenerCantidadDiasEntreSemana(diasParqueo) * VALOR_DIAS_ENTRE_SEMANA) +
                    (this.obtenerCantidadExtraDiasFestivosEntreSemana(diasParqueo) * VALOR_EXTRA_ENTRE_SEMANA_POR_FESTIVOS) +
                    (this.obtenerCantidadDiasFinesDeSemana(diasParqueo) * VALOR_DIAS_FINES_DE_SEMANA) +
                    (this.obtenerCantidadExtraDiasFestivosFinesSemana(diasParqueo) * VALOR_EXTRA_FINES_DE_SEMANA_POR_FESTIVOS));
        }
        return -1;
    }


}
