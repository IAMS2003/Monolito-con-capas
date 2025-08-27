package com.pelaez_montoya.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class ConexionDB {
    private static final String URL = "jdbc:h2:mem:Biblioteca;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static ConexionDB instance;
    private Connection conexion;

    private ConexionDB() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("org.h2.Driver");
            this.conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion h2 establecida con exito.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver h2 no encontrado.", e);
        }
    }

    public static ConexionDB getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConexionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conexion;
    }
}
