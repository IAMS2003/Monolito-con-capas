package com.pelaez_montoya;

import java.util.Date;

public class ReservaLibroFabrica {
    public static ReservaLibro crearReservaLibro(Integer idReservaLibro, Integer idLibro, Integer idCliente, Date fechaReserva, Date fechaDevolucion) {
        return new ReservaLibro.Builder()
                .idReservaLibro(idReservaLibro)
                .idLibro(idLibro)
                .idCliente(idCliente)
                .fechaReserva(fechaReserva)
                .fechaDevolucion(fechaDevolucion)
                .build();
    }
}
