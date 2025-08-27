package com.pelaez_montoya;

import com.pelaez_montoya.ReservaLibroDTO;
import com.pelaez_montoya.ReservaLibroService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReservaLibroController {

    private final ReservaLibroService reservaService;

    public ReservaLibroController(ReservaLibroService reservaService) {
        this.reservaService = reservaService;
    }

    public void crearReserva(ReservaLibroDTO dto) throws SQLException {
        reservaService.crearReserva(dto);
    }

    public Optional<ReservaLibroDTO> obtenerPorId(Integer id) throws SQLException {
        return reservaService.obtenerPorId(id);
    }

    public List<ReservaLibroDTO> obtenerTodas() throws SQLException {
        return reservaService.obtenerTodas();
    }

    public void actualizarReserva(Integer id, ReservaLibroDTO dto) throws SQLException {
        reservaService.actualizarReserva(id, dto);
    }

    public void eliminarReserva(Integer id) throws SQLException {
        reservaService.eliminarReserva(id);
    }
}