package com.ceiba.factura.modelo;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Calendario {
    private List<Integer> diasFestivosEntreSemana;
    private List<Integer> diasFestivosFinesSemana;
    private  List<Integer> diasFinesSemana;
    private int dias;
    private int anio;
    private List<Calendario> calendario2022 = new ArrayList<>();

    public Calendario(List<Integer> diasFestivosEntreSemana, List<Integer> diasFestivosFinesSemana, List<Integer> diasFinesSemana, int dias, int anio) {
        this.diasFestivosEntreSemana = diasFestivosEntreSemana;
        this.diasFestivosFinesSemana = diasFestivosFinesSemana;
        this.diasFinesSemana = diasFinesSemana;
        this.dias = dias;
        this.anio = anio;
    }

    public Calendario() {
        calendario2022.add(new Calendario(Arrays.asList(10), Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30), Arrays.asList(1), 31, 2022)); //Enero
        calendario2022.add(new Calendario( Arrays.asList(), Arrays.asList(),Arrays.asList(5, 6, 12, 13, 19, 20, 26, 27), 28, 2022)); //Febrero
        calendario2022.add(new Calendario( Arrays.asList(21), Arrays.asList(), Arrays.asList(5, 6, 12, 13, 19, 20, 26, 27),31, 2022)); //Marzo
        calendario2022.add(new Calendario( Arrays.asList(14, 15), Arrays.asList(),Arrays.asList(2, 3, 9, 10, 16, 17, 23, 24, 30),  30, 2022)); //Abril
        calendario2022.add(new Calendario( Arrays.asList(30), Arrays.asList(1),Arrays.asList(1, 7, 8, 14, 15, 21, 22, 28, 29), 31, 2022)); //Mayo
        calendario2022.add(new Calendario( Arrays.asList(20, 27), Arrays.asList(),Arrays.asList(4, 5, 11, 12, 18, 19, 25, 26), 30, 2022)); //Junio
        calendario2022.add(new Calendario( Arrays.asList(4, 20), Arrays.asList(),Arrays.asList(2, 3, 9, 10, 16, 17, 23, 24, 30, 31), 31, 2022)); //Julio
        calendario2022.add(new Calendario( Arrays.asList(15), Arrays.asList(7),Arrays.asList(6, 7, 13, 14, 20, 21, 27, 28), 31, 2022)); // Agosto
        calendario2022.add(new Calendario( Arrays.asList(), Arrays.asList(), Arrays.asList(3, 4, 10, 11, 17, 18, 24, 25), 30, 2022)); //Septiembre
        calendario2022.add(new Calendario( Arrays.asList(17), Arrays.asList(),Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30), 31, 2022)); //Octubre
        calendario2022.add(new Calendario( Arrays.asList(7, 14), Arrays.asList(),Arrays.asList(5, 6, 12, 13, 19, 20, 26, 27), 30, 2022)); //Noviembre
        calendario2022.add(new Calendario(Arrays.asList(8), Arrays.asList(25),Arrays.asList(3, 4, 10, 11, 17, 18, 24, 25, 31),  31, 2022)); //Diciembre
    }





}