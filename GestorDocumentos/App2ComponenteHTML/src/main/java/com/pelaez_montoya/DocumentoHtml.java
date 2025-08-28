package com.pelaez_montoya;

public class DocumentoHtml implements Documento {
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = "<html><body>" + contenido + "</body></html>";
    }

    @Override
    public void dibujar() {
        System.out.println("Mostrando en navegador: " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo HTML: " + contenido);
    }
}
