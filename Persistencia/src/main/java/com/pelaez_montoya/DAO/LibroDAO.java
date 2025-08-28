package com.pelaez_montoya.DAO;

import com.pelaez_montoya.Conexion.ConexionDB;
import com.pelaez_montoya.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO {
    private final ConexionDB conexionDB;
    private static LibroDAO instance;

    private LibroDAO() throws SQLException, ClassNotFoundException {
        this.conexionDB = ConexionDB.getInstance();
    }

    public static LibroDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new LibroDAO();
        }
        return instance;
    }

    public void guardar(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (ISBN, titulo, autor, editorial) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, libro.getISBN());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setString(4, libro.getEditorial());

            ps.executeUpdate();
        }
    }

    public Optional<Libro> findById(Integer ISBN) throws SQLException {
        String sql = "SELECT * FROM libros WHERE ISBN = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ISBN);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Libro libro = new Libro.Builder()
                            .ISBN(rs.getInt("ISBN"))
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
                        .ISBN(rs.getInt("ISBN"))
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
        String sql = "UPDATE libros SET titulo = ?, autor = ?, editorial = ? WHERE ISBN = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getEditorial());
            ps.setInt(4, libro.getISBN());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(Integer ISBN) throws SQLException {
        String sql = "DELETE FROM libros WHERE ISBN = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ISBN);
            return ps.executeUpdate() > 0;
        }
    }
}