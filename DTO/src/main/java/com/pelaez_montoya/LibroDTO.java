package com.pelaez_montoya;

public class LibroDTO {
    private Integer ISBN;
    private String titulo;
    private String autor;
    private String editorial;

    public LibroDTO() {}

    public LibroDTO(Integer ISBN, String titulo, String autor, String editorial) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }
    public Integer getISBN() { return ISBN; }

    public void setISBN(Integer ISBN) { this.ISBN = ISBN; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}