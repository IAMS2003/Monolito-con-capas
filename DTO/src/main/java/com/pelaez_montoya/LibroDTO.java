package com.pelaez_montoya;

public class LibroDTO {
    private String titulo;
    private String autor;
    private String editorial;

    public LibroDTO(Builder builder){
        this.titulo = builder.nombre;
        this.autor = builder.autor;
        this.editorial = builder.editorial;
    }

    public static class Builder {
        private String nombre;
        private String autor;
        private String editorial;

        public Builder nombre(String nombre) {
            this.nombre = nombre;
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

        public LibroDTO build(){
            return new LibroDTO(this);
        }
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
