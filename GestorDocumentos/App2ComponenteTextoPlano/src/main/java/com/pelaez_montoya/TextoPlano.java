package com.pelaez_montoya;

public class TextoPlano implements Documento{
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Mostrando en consola: " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo texto plano: " + contenido);
    }
}

