package com.pelaez_montoya;

public class ClaseCFabrica {
    private static ClaseCFabrica instance;

    private ClaseCFabrica() {}

    public static synchronized ClaseCFabrica getInstance() {
        if (instance == null) {
            instance = new ClaseCFabrica();
        }
        return instance;
    }

    public ClaseC crearClaseC(String texto) {
        return new ClaseC.Builder().texto(texto).build();
    }
}