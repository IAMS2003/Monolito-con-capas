package com.pelaez_montoya.DAO;

import com.pelaez_montoya.ClaseAFabrica;
import com.pelaez_montoya.Conexion.ConexionDBSistema;
import com.pelaez_montoya.ClaseA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaseADao {
    private final Connection conexion;
    private static ClaseADao instance;

    private ClaseADao() throws SQLException {
        this.conexion = ConexionDBSistema.getInstance().getConnection();
        crearTablaSiNoExiste();
    }

    public static synchronized ClaseADao getInstance() throws SQLException {
        if (instance == null) {
            instance = new ClaseADao();
        }
        return instance;
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ClaseA (
                    id DOUBLE PRIMARY KEY,
                    nombres VARCHAR(255),
                    apellidos VARCHAR(255)
                )
                """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla ClaseA verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error creando/verificando tabla ClaseA: " + e.getMessage());
        }
    }

    public void insertar(ClaseA claseA) {
        String sql = "INSERT INTO ClaseA (id, nombres, apellidos) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setDouble(1, claseA.getId());
            pstmt.setString(2, claseA.getNombres());
            pstmt.setString(3, claseA.getApellidos());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en ClaseA");
        } catch (SQLException e) {
            System.err.println("Error al insertar en ClaseA: " + e.getMessage());
        }
    }
    
    public List<ClaseA> obtenerTodos() {
        List<ClaseA> lista = new ArrayList<>();
        String sql = "SELECT * FROM ClaseA";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                ClaseA claseA = ClaseAFabrica.getInstance().crearClaseA(
                        rs.getDouble("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos")
                );
                lista.add(claseA);
            }
        } catch(SQLException e) {
            System.err.println("Error al obtener todos los registros de ClaseA: " + e.getMessage());
        }
        return lista;
    }
}
