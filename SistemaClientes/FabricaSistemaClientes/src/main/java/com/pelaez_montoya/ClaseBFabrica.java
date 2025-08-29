package com.pelaez_montoya;

public class ClaseBFabrica {
    private static ClaseBFabrica instance;

    private ClaseBFabrica() {}

    public static synchronized ClaseBFabrica getInstance() {
        if (instance == null) {
            instance = new ClaseBFabrica();
        }
        return instance;
    }

    public ClaseB crearClaseB(String destino, String mensaje) {
        return new ClaseB.Builder()
                .destino(destino)
                .mensaje(mensaje)
                .build();
    }
}