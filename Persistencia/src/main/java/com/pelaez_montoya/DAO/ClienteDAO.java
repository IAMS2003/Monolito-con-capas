package com.pelaez_montoya.DAO;

import com.pelaez_montoya.Conexion.ConexionDB;
import com.pelaez_montoya.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    private final ConexionDB conexionDB;

    public ClienteDAO(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    public void guardar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (documento_identidad, nombre, apellido, edad, correo, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cliente.getDocumentoIdentidad());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setInt(4, cliente.getEdad());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getTelefono());

            ps.executeUpdate();
        }
    }

    public Optional<Cliente> findByDocumento(Integer documento) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE documento_identidad = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente.Builder()
                            .documentoIdentidad(rs.getInt("documento_identidad"))
                            .nombre(rs.getString("nombre"))
                            .apellido(rs.getString("apellido"))
                            .edad(rs.getInt("edad"))
                            .correo(rs.getString("correo"))
                            .telefono(rs.getString("telefono"))
                            .build();
                    return Optional.of(cliente);
                }
            }
        }
        return Optional.empty();
    }

    public List<Cliente> findAll() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente.Builder()
                        .documentoIdentidad(rs.getInt("documento_identidad"))
                        .nombre(rs.getString("nombre"))
                        .apellido(rs.getString("apellido"))
                        .edad(rs.getInt("edad"))
                        .correo(rs.getString("correo"))
                        .telefono(rs.getString("telefono"))
                        .build();
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public boolean actualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ? " +
                "WHERE documento_identidad = ?";

        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getEdad());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getTelefono());
            ps.setInt(6, cliente.getDocumentoIdentidad());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(Integer documento) throws SQLException {
        String sql = "DELETE FROM clientes WHERE documento_identidad = ?";
        try (Connection conn = conexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, documento);
            return ps.executeUpdate() > 0;
        }
    }
}