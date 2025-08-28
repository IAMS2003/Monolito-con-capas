package com.pelaez_montoya.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorDB {

    public static void iniciarConexion() throws SQLException, ClassNotFoundException {
        Connection conexion = ConexionDB.getInstance().getConnection();
        crearTablas(conexion);
    }

    private static void crearTablas(Connection conexion) {
        String sql = """
            CREATE TABLE IF NOT EXISTS libros (
                ISBN INT PRIMARY KEY,
                titulo VARCHAR(100),
                autor VARCHAR(100),
                editorial VARCHAR(100)
            );

            CREATE TABLE IF NOT EXISTS clientes (
                documento_identidad INT PRIMARY KEY,
                nombre VARCHAR(100),
                apellido VARCHAR(100),
                edad INT,
                correo VARCHAR(100),
                telefono VARCHAR(100)
            );

            CREATE TABLE IF NOT EXISTS reservas_libros (
                id_reserva_libro INT AUTO_INCREMENT PRIMARY KEY,
                ISBN INT,
                documento_cliente INT,
                fecha_reserva DATE,
                fecha_devolucion DATE,
                CONSTRAINT fk_reserva_libro FOREIGN KEY (ISBN) REFERENCES libros(ISBN),
                CONSTRAINT fk_reserva_cliente FOREIGN KEY (documento_cliente) REFERENCES clientes(documento_identidad)
            );
            """;

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tablas creadas exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas:");
            e.printStackTrace();
        }
    }
}