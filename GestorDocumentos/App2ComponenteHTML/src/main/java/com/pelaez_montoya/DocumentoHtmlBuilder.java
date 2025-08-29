package com.pelaez_montoya;

public class DocumentoHtmlBuilder implements DocumentoBuilder<DocumentoHtml> {
    private final DocumentoHtml documento = new DocumentoHtml();

    @Override
    public DocumentoBuilder<DocumentoHtml> contenido(String contenido) {
        documento.setContenido(contenido);
        return this;
    }

    @Override
    public DocumentoHtml build() {
        return documento;
    }
}


