package com.pelaez_montoya;

public class Cliente {
    private Integer documentoIdentidad;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String correo;
    private String telefono;

    public Cliente(Builder builder){
        this.documentoIdentidad = builder.documentoIdentidad;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.edad = builder.edad;
        this.correo = builder.correo;
        this.telefono = builder.telefono;
    }

    public static class Builder{
        private Integer documentoIdentidad;
        private String nombre;
        private String apellido;
        private Integer edad;
        private String correo;
        private String telefono;

        public Builder documentoIdentidad(Integer documentoIdentidad){
            this.documentoIdentidad = documentoIdentidad;
            return this;
        }

        public Builder nombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido){
            this.apellido = apellido;
            return this;
        }

        public Builder edad(Integer edad){
            this.edad = edad;
            return this;
        }

        public Builder correo(String correo){
            this.correo = correo;
            return this;
        }

        public Builder telefono(String telefono){
            this.telefono = telefono;
            return this;
        }

        public Cliente build(){
            return new Cliente(this);
        }
    }

    public Integer getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
}
