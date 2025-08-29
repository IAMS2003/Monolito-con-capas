package com.pelaez_montoya;

import com.pelaez_montoya.Conexion.ConexionDBSistema;
import com.pelaez_montoya.Conexion.InicializadorDB;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static ClienteController clienteController;
    private static LibroController libroController;
    private static ReservaLibroController reservaLibroController;

    public static void main(String[] args) {
        try {
            // Inicializar base de datos
            InicializadorDB.iniciarConexion();

            // Crear conexión única
            ConexionDBSistema conexionDBSistema = ConexionDBSistema.getInstance();

            // ▶️ Iniciar GUI en el Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(() -> {
                VistaGUI gui = null;
                try {
                    gui = new VistaGUI();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                gui.setVisible(true);
            });

            // ▶️ Iniciar Consola en el hilo principal (o en hilo separado si prefieres)
            // Aquí lo hacemos en un hilo aparte para no bloquear
            Thread hiloConsola = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                VistaConsola vistaConsola = null;
                try {
                    vistaConsola = new VistaConsola(scanner);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                vistaConsola.mostrarMenuPrincipal();
                scanner.close();
            });

            hiloConsola.setDaemon(false);
            hiloConsola.start();

        } catch (Exception e) {
            System.err.println("Error crítico al iniciar la aplicación:");
            e.printStackTrace();
        }
    }
}