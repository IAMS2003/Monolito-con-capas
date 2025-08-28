package com.pelaez_montoya;

public class Libro {
    private Integer ISBN;
    private String titulo;
    private String autor;
    private String editorial;

    public Libro(Builder builder){
        this.ISBN = builder.ISBN;
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.editorial = builder.editorial;
    }

    public static class Builder {
        private Integer ISBN;
        private String titulo;
        private String autor;
        private String editorial;

        public Builder ISBN(Integer ISBN) {
            this.ISBN = ISBN;
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

    public Integer getISBN() { return ISBN; }

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
