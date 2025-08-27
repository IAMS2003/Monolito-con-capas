package com.pelaez_montoya;

public class LibroFabrica {
    private static LibroFabrica instance;

    private LibroFabrica() {}

    public static LibroFabrica getInstance() {
        if (instance == null) {
            instance = new LibroFabrica();
        }
        return instance;
    }

    public Libro crearLibro(Integer idLibro, String titulo, String autor, String editorial) {
        return new Libro.Builder()
                .idLibro(idLibro)
                .titulo(titulo)
                .autor(autor)
                .editorial(editorial)
                .build();
    }
}
