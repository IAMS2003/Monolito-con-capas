package com.pelaez_montoya;

public class DocumentoPdfBuilder implements DocumentoBuilder<DocumentoPdf> {
    private final DocumentoPdf documento;

    public DocumentoPdfBuilder(ComponentePDF componentePDF) {
        this.documento = new DocumentoPdf(componentePDF);
    }

    @Override
    public DocumentoBuilder<DocumentoPdf> contenido(String contenido) {
        documento.setContenido(contenido);
        return this;
    }

    @Override
    public DocumentoPdf build() {
        return documento;
    }
}


