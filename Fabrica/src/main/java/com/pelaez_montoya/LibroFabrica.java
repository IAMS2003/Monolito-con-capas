package com.pelaez_montoya;

public class LibroFabrica {
    public static Libro crearLibro(Integer idLibro, String titulo, String autor, String editorial) {
        return new Libro.Builder()
                .idLibro(idLibro)
                .titulo(titulo)
                .autor(autor)
                .editorial(editorial)
                .build();
    }
}
