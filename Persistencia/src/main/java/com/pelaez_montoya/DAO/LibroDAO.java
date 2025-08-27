package com.pelaez_montoya.DAO;

import com.pelaez_montoya.Conexion.ConexionDB;
import com.pelaez_montoya.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO {
    private final ConexionDB conexionDB;

    public LibroDAO(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    public void guardar(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (idLibro, titulo, autor, editorial) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, libro.getIdLibro());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setString(4, libro.getEditorial());

            ps.executeUpdate();
        }
    }

    public Optional<Libro> findById(Integer idLibro) throws SQLException {
        String sql = "SELECT * FROM libros WHERE idLibro = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLibro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Libro libro = new Libro.Builder()
                            .idLibro(rs.getInt("idLibro"))
                            .titulo(rs.getString("titulo"))
                            .autor(rs.getString("autor"))
                            .editorial(rs.getString("editorial"))
                            .build();
                    return Optional.of(libro);
                }
            }
        }
        return Optional.empty();
    }

    public List<Libro> findAll() throws SQLException {
        String sql = "SELECT * FROM libros";
        List<Libro> libros = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro.Builder()
                        .idLibro(rs.getInt("idLibro"))
                        .titulo(rs.getString("titulo"))
                        .autor(rs.getString("autor"))
                        .editorial(rs.getString("editorial"))
                        .build();
                libros.add(libro);
            }
        }
        return libros;
    }

    public boolean actualizar(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, editorial = ? WHERE idLibro = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getEditorial());
            ps.setInt(4, libro.getIdLibro());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(Integer idLibro) throws SQLException {
        String sql = "DELETE FROM libros WHERE idLibro = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            return ps.executeUpdate() > 0;
        }
    }
}