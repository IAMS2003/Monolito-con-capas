package com.pelaez_montoya;

public class ClaseA {

    private double id;
    private String nombres;
    private String apellidos;

    private ClaseA(Builder builder) {
        this.id = builder.id;
        this.nombres = builder.nombres;
        this.apellidos = builder.apellidos;
    }

    public static class Builder {
        private double id;
        private String nombres;
        private String apellidos;

        public Builder id(double id) {
            this.id = id;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public ClaseA build() {
            return new ClaseA(this);
        }
    }

    public String cargarInformacionTerceros() {
        System.out.println("Informacion enviada al sistema contable.");
        return "Informacion enviada al sistema contable.";
    }

    public double getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
}
