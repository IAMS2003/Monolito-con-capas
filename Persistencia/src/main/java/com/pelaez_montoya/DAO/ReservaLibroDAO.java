package com.pelaez_montoya.DAO;

import com.pelaez_montoya.Conexion.ConexionDB;
import com.pelaez_montoya.ReservaLibro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaLibroDAO {
    private final ConexionDB conexionDB;
    private static ReservaLibroDAO instance;

    private ReservaLibroDAO() throws SQLException, ClassNotFoundException {
        this.conexionDB = ConexionDB.getInstance();
    }

    public static ReservaLibroDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ReservaLibroDAO();
        }
        return instance;
    }

    public void guardar(ReservaLibro reserva) throws SQLException {
        String sql = "INSERT INTO reservas_libros (ISBN, documento_cliente, fecha_reserva, fecha_devolucion) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdLibro());
            ps.setInt(2, reserva.getIdCliente());
            ps.setDate(3, new java.sql.Date(reserva.getFechaReserva().getTime()));
            ps.setDate(4, new java.sql.Date(reserva.getFechaDevolucion().getTime()));

            ps.executeUpdate();
        }
    }

    public Optional<ReservaLibro> findById(Integer idReserva) throws SQLException {
        String sql = "SELECT * FROM reservas_libros WHERE id_reserva_libro = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ReservaLibro reserva = new ReservaLibro.Builder()
                            .idReservaLibro(rs.getInt("id_reserva_libro"))
                            .idLibro(rs.getInt("ISBN"))
                            .idCliente(rs.getInt("documento_cliente"))
                            .fechaReserva(rs.getDate("fecha_reserva"))
                            .fechaDevolucion(rs.getDate("fecha_devolucion"))
                            .build();
                    return Optional.of(reserva);
                }
            }
        }
        return Optional.empty();
    }

    public List<ReservaLibro> findAll() throws SQLException {
        String sql = "SELECT * FROM reservas_libros";
        List<ReservaLibro> reservas = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ReservaLibro reserva = new ReservaLibro.Builder()
                        .idReservaLibro(rs.getInt("id_reserva_libro"))
                        .idLibro(rs.getInt("ISBN"))
                        .idCliente(rs.getInt("documento_cliente"))
                        .fechaReserva(rs.getDate("fecha_reserva"))
                        .fechaDevolucion(rs.getDate("fecha_devolucion"))
                        .build();
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public boolean actualizar(ReservaLibro reserva) throws SQLException {
        String sql = "UPDATE reservas_libros SET ISBN = ?, documento_cliente = ?, fecha_reserva = ?, fecha_devolucion = ? " +
                "WHERE id_reserva_libro = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdLibro());
            ps.setInt(2, reserva.getIdCliente());
            ps.setDate(3, new java.sql.Date(reserva.getFechaReserva().getTime()));
            ps.setDate(4, new java.sql.Date(reserva.getFechaDevolucion().getTime()));
            ps.setInt(5, reserva.getIdReservaLibro());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(Integer idReserva) throws SQLException {
        String sql = "DELETE FROM reservas_libros WHERE id_reserva_libro = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;
        }
    }
}