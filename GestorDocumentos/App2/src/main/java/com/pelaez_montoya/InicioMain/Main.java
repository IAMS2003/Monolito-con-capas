package com.pelaez_montoya.InicioMain;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.Fabrica.*;
import com.pelaez_montoya.*;

public class Main {
    public static void main(String[] args) {
        Documento documento;

        // PATRÓN 1: Factory Method + Singleton (integrado en las fábricas)
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

        System.out.println("\n");
        
        // PATRÓN 2: Builder Pattern
        Documento htmlConBuilder = new DocumentoHtmlBuilder()
                .contenido("<p>HTML con Builder</p>")
                .build();
        htmlConBuilder.dibujar();

        Documento textoConBuilder = new TextoPlanoBuilder()
                .contenido("Texto con Builder")
                .build();
        textoConBuilder.imprimir();

        // PATRÓN 3: Builder + Inyección de Dependencias + Singleton
        Documento pdfConBuilder = new DocumentoPdfBuilder(DocumentoPdfFactory.getComponentePDF())
                .contenido("PDF con Builder y Singleton")
                .build();
        pdfConBuilder.dibujar();
    }
}
