package com.pelaez_montoya.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "biblioteca_mysql";
    private static final String USER = "root";
    private static final String PASS = "root"; // Cambia esto por tu contraseña de MySQL
    private static final String PARAMS = "?useSSL=false&serverTimezone=UTC";

    private static final String SERVER_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + PARAMS;
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + PARAMS;

    private static ConexionDB instance;
    private Connection conexion;

    private ConexionDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            crearBaseDeDatosSiNoExiste();

            this.conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("✅ Conexión a la base de datos '" + DB_NAME + "' establecida con éxito.");

        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado. Asegúrate de que el conector JAR de MySQL esté en el classpath.", e);
        }
    }

    private void crearBaseDeDatosSiNoExiste() throws SQLException {
        try (Connection serverConnection = DriverManager.getConnection(SERVER_URL, USER, PASS);
             Statement stmt = serverConnection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Base de datos '" + DB_NAME + "' verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error crítico al intentar crear la base de datos '" + DB_NAME + "'.");
            throw e;
        }
    }

    public static synchronized ConexionDB getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexionDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        // Si la conexión se cerró o es nula, la restablece.
        if (this.conexion == null || this.conexion.isClosed()) {
            this.conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("ℹ️ Conexión a MySQL restablecida.");
        }
        return this.conexion;
    }
}
