package com.pelaez_montoya;

public class ClienteFabrica {
    private static ClienteFabrica instance;
    private ClienteFabrica() {}

    public static ClienteFabrica getInstance() {
        if (instance == null) {
            instance = new ClienteFabrica();
        }
        return instance;
    }

    public Cliente crearCliente(Integer documentoIdentidad, String nombre, String apellido, Integer edad, String correo,  String telefono) {
        return new Cliente.Builder()
                .documentoIdentidad(documentoIdentidad)
                .nombre(nombre)
                .apellido(apellido)
                .edad(edad)
                .correo(correo)
                .telefono(telefono)
                .build();
    }
}