package com.pelaez_montoya.InicioMain;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.Fabrica.*;

public class Main {
    public static void main(String[] args) {
        Documento documento;

        documento = new DocumentoHtmlFactory().crearDocumento();
        System.out.println("\n");
        documento.setContenido("<h1>Hola mundo</h1>");
        documento.dibujar();
        documento.imprimir();

        documento = new DocumentoPdfFactory().crearDocumento();
        System.out.println("\n");
        documento.setContenido("...PDF...PDF...PDF...");
        documento.dibujar();
        documento.imprimir();

        documento = new TextoPlanoFactory().crearDocumento();
        System.out.println("\n");
        documento.setContenido("Este es un texto plano");
        documento.dibujar();
        documento.imprimir();
    }
}
