package com.pelaez_montoya;

public class ClienteFabrica {
    public static Cliente crearCliente(Integer documentoIdentidad, String nombre, String apellido, Integer edad, String correo,  String telefono) {
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