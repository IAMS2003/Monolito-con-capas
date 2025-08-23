package com.pelaez_montoya;

public class Libro {
    private Integer idLibro;
    private String titulo;
    private String autor;
    private String editorial;

    public Libro(Builder builder){
        this.idLibro = builder.idLibro;
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.editorial = builder.editorial;
    }

    public static class Builder {
        private Integer idLibro;
        private String titulo;
        private String autor;
        private String editorial;

        public Builder idLibro(Integer idLibro) {
            this.idLibro = idLibro;
            return this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder autor(String autor) {
            this.autor = autor;
            return this;
        }

        public Builder editorial(String editorial) {
            this.editorial = editorial;
            return this;
        }

        public Libro build(){
            return new Libro(this);
        }
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }
}
