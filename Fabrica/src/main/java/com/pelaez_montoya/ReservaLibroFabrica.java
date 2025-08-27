package com.pelaez_montoya;

import java.util.Date;

public class ReservaLibroFabrica {
    private static ReservaLibroFabrica instance;

    private ReservaLibroFabrica() {}

    public static ReservaLibroFabrica getInstance() {
        if (instance == null) {
            instance = new ReservaLibroFabrica();
        }
        return instance;
    }

    public ReservaLibro crearReservaLibro(Integer idReservaLibro, Integer idLibro, Integer idCliente, Date fechaReserva, Date fechaDevolucion) {
        return new ReservaLibro.Builder()
                .idReservaLibro(idReservaLibro)
                .idLibro(idLibro)
                .idCliente(idCliente)
                .fechaReserva(fechaReserva)
                .fechaDevolucion(fechaDevolucion)
                .build();
    }
}
