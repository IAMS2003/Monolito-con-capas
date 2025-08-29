package com.pelaez_montoya.DAO;

import com.pelaez_montoya.ClaseBFabrica;
import com.pelaez_montoya.Conexion.ConexionDBSistema;
import com.pelaez_montoya.ClaseB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaseBDao {
    private final Connection conexion;
    private static ClaseBDao instance;

    private ClaseBDao() throws SQLException {
        this.conexion = ConexionDBSistema.getInstance().getConnection();
        crearTablaSiNoExiste();
    }

    public static ClaseBDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new ClaseBDao();
        }
        return instance;
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ClaseB (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    destino VARCHAR(255),
                    remitente VARCHAR(255),
                    mensaje VARCHAR(1000)
                )
                """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla ClaseB verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error creando/verificando tabla ClaseB: " + e.getMessage());
        }
    }

    public void insertar(ClaseB claseB) {
        String sql = "INSERT INTO ClaseB (destino, remitente, mensaje) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, claseB.getDestino());
            pstmt.setString(2, claseB.getRemitente());
            pstmt.setString(3, claseB.getMensaje());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en ClaseB");
        } catch (SQLException e) {
            System.err.println("Error al insertar en ClaseB: " + e.getMessage());
        }
        claseB.mensajeEnviadoFinal();
    }
 
    public List<ClaseB> obtenerTodos() {
        List<ClaseB> lista = new ArrayList<>();
        String sql = "SELECT * FROM ClaseB";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                ClaseB claseB = ClaseBFabrica.getInstance().crearClaseB(
                        rs.getString("destino"),
                        rs.getString("mensaje")
                );
                lista.add(claseB);
            }
        } catch(SQLException e) {
            System.err.println("Error al obtener todos los registros de ClaseB: " + e.getMessage());
        }
        return lista;
    }
}
