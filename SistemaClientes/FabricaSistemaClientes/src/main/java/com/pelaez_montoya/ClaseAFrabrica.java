package com.pelaez_montoya;

public class ClaseAFabrica {
    private static ClaseAFabrica instance;

    private ClaseAFabrica() {}

    public static synchronized ClaseAFabrica getInstance() {
        if (instance == null) {
            instance = new ClaseAFabrica();
        }
        return instance;
    }

    public ClaseA crearClaseA(double id, String nombres, String apellidos) {
        return new ClaseA.Builder()
                .id(id)
                .nombres(nombres)
                .apellidos(apellidos)
                .build();
    }
}