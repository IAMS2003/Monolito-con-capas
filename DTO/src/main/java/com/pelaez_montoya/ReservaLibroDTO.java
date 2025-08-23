package com.pelaez_montoya;

import java.util.Date;

public class ReservaLibroDTO {
    private Integer idLibro;
    private Integer idCliente;
    private Date fechaReserva;
    private Date fechaDevolucion;

    public ReservaLibroDTO() {}

    public ReservaLibroDTO(Integer idLibro, Integer idCliente, Date fechaReserva, Date fechaDevolucion) {
        this.idLibro = idLibro;
        this.idCliente = idCliente;
        this.fechaReserva = fechaReserva;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}