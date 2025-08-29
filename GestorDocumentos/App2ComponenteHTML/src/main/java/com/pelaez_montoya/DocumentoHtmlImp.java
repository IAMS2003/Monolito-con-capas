package com.pelaez_montoya;

public class DocumentoHtmlImp implements Documento {
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido=contenido;
    }

    @Override
    public String dibujar() {
        System.out.println("<h1> " + contenido + " </h1>");
        return "<h1> " + contenido + " </h1>";
    }

    @Override
    public String imprimir() {
        System.out.println("Imprimiendo DocumentoHTML." + contenido);
        return "Imprimiendo DocumentoHTML." + contenido;
    }
}
