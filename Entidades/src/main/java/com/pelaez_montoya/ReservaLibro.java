package com.pelaez_montoya;

import java.util.Date;

public class ReservaLibro {
    private Integer idReservaLibro;
    private Integer idLibro;
    private Integer idCliente;
    private Date fechaReserva;
    private Date fechaDevolucion;

    public ReservaLibro(Builder builder) {
        this.idReservaLibro = builder.idReservaLibro;
        this.idLibro = builder.idLibro;
        this.idCliente = builder.idCliente;
        this.fechaReserva = builder.fechaReserva;
        this.fechaDevolucion = builder.fechaDevolucion;
    }

    public static class Builder {
        private Integer idReservaLibro;
        private Integer idLibro;
        private Integer idCliente;
        private Date fechaReserva;
        private Date fechaDevolucion;

        public Builder idReservaLibro(Integer idReservaLibro) {
            this.idReservaLibro = idReservaLibro;
            return this;
        }

        public Builder idLibro(Integer idLibro) {
            this.idLibro = idLibro;
            return this;
        }

        public Builder idCliente(Integer idCliente) {
            this.idCliente = idCliente;
            return this;
        }

        public Builder fechaReserva(Date fechaReserva) {
            this.fechaReserva = fechaReserva;
            return this;
        }

        public Builder fechaDevolucion(Date fechaDevolucion) {
            this.fechaDevolucion = fechaDevolucion;
            return this;
        }

        public ReservaLibro build(){
            return new ReservaLibro(this);
        }
    }

    public Integer getIdReservaLibro() {
        return idReservaLibro;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}
