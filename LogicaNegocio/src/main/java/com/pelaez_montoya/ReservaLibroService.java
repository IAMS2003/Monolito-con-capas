package com.pelaez_montoya;

import com.pelaez_montoya.DAO.ClienteDAO;
import com.pelaez_montoya.DAO.LibroDAO;
import com.pelaez_montoya.DAO.ReservaLibroDAO;
import com.pelaez_montoya.ReservaLibroFabrica;
import com.pelaez_montoya.ReservaLibro;
import com.pelaez_montoya.ReservaLibroDTO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservaLibroService {

    private final ReservaLibroDAO reservaDAO;
    private final LibroDAO libroDAO;
    private final ClienteDAO clienteDAO;
    private final ReservaLibroFabrica reservaLibroFabrica;
    private static ReservaLibroService instance;

    private ReservaLibroService() throws SQLException, ClassNotFoundException {
        this.reservaDAO = ReservaLibroDAO.getInstance();
        this.libroDAO = LibroDAO.getInstance();
        this.clienteDAO = ClienteDAO.getInstance();
        this.reservaLibroFabrica = ReservaLibroFabrica.getInstance();
    }

    public static ReservaLibroService getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ReservaLibroService();
        }
        return instance;
    }

    public ReservaLibroDTO crearReserva(ReservaLibroDTO dto) throws SQLException {
        validarReservaDTO(dto);

        if (!libroDAO.findById(dto.getIdLibro()).isPresent()) {
            throw new IllegalArgumentException("Libro con ID " + dto.getIdLibro() + " no existe.");
        }

        if (!clienteDAO.findByDocumento(dto.getIdCliente()).isPresent()) {
            throw new IllegalArgumentException("Cliente con documento " + dto.getIdCliente() + " no existe.");
        }

        ReservaLibro reserva = reservaLibroFabrica.crearReservaLibro(
                dto.getIdReserva(),
                dto.getIdLibro(),
                dto.getIdCliente(),
                dto.getFechaReserva(),
                dto.getFechaDevolucion()
        );

        reservaDAO.guardar(reserva);

        return dto;
    }

    public Optional<ReservaLibroDTO> obtenerPorId(Integer idReserva) throws SQLException {
        return reservaDAO.findById(idReserva)
                .map(this::toDTO);
    }

    public List<ReservaLibroDTO> obtenerTodas() throws SQLException {
        return reservaDAO.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReservaLibroDTO actualizarReserva(Integer idReserva, ReservaLibroDTO dto) throws SQLException {
        Optional<ReservaLibro> reservaExistente = reservaDAO.findById(idReserva);
        if (reservaExistente.isEmpty()) {
            throw new IllegalArgumentException("Reserva con ID " + idReserva + " no encontrada.");
        }

        validarReservaDTO(dto);

        if (!libroDAO.findById(dto.getIdLibro()).isPresent()) {
            throw new IllegalArgumentException("Libro con ID " + dto.getIdLibro() + " no existe.");
        }
        if (!clienteDAO.findByDocumento(dto.getIdCliente()).isPresent()) {
            throw new IllegalArgumentException("Cliente con documento " + dto.getIdCliente() + " no existe.");
        }

        ReservaLibro reserva = reservaLibroFabrica.crearReservaLibro(
                idReserva,
                dto.getIdLibro(),
                dto.getIdCliente(),
                dto.getFechaReserva(),
                dto.getFechaDevolucion()
        );

        boolean actualizado = reservaDAO.actualizar(reserva);
        if (!actualizado) {
            throw new SQLException("No se pudo actualizar la reserva.");
        }

        return dto;
    }

    public void eliminarReserva(Integer idReserva) throws SQLException {
        Optional<ReservaLibro> reserva = reservaDAO.findById(idReserva);
        if (reserva.isEmpty()) {
            throw new IllegalArgumentException("Reserva con ID " + idReserva + " no encontrada.");
        }

        boolean eliminado = reservaDAO.eliminar(idReserva);
        if (!eliminado) {
            throw new SQLException("Error al eliminar la reserva.");
        }
    }

    private void validarReservaDTO(ReservaLibroDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        if (dto.getIdLibro() == null || dto.getIdLibro() <= 0) {
            throw new IllegalArgumentException("ID del libro es obligatorio y debe ser positivo.");
        }
        if (dto.getIdCliente() == null || dto.getIdCliente() <= 0) {
            throw new IllegalArgumentException("ID del cliente es obligatorio y debe ser positivo.");
        }
        if (dto.getFechaReserva() == null) {
            throw new IllegalArgumentException("La fecha de reserva es obligatoria.");
        }
        if (dto.getFechaDevolucion() == null) {
            throw new IllegalArgumentException("La fecha de devolución es obligatoria.");
        }
        if (dto.getFechaDevolucion().before(dto.getFechaReserva())) {
            throw new IllegalArgumentException("La fecha de devolución no puede ser anterior a la de reserva.");
        }
    }

    private ReservaLibroDTO toDTO(ReservaLibro reserva) {
        ReservaLibroDTO dto = new ReservaLibroDTO();
        dto.setIdReserva(reserva.getIdReservaLibro());
        dto.setIdLibro(reserva.getIdLibro());
        dto.setIdCliente(reserva.getIdCliente());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setFechaDevolucion(reserva.getFechaDevolucion());
        return dto;
    }
}