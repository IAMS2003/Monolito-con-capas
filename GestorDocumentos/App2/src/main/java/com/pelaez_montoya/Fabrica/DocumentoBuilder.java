package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;

public class DocumentoBuilder {

    private DocumentoFabrica.TipoDocumento tipo;
    private String contenido;

    public DocumentoBuilder conTipo(DocumentoFabrica.TipoDocumento tipo) {
        this.tipo = tipo;
        return this;
    }

    public DocumentoBuilder conContenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public Documento build() {
        Documento instanciaDocumento = DocumentoFabrica.getInstancia().crearDocumento(tipo);
        instanciaDocumento.setContenido(contenido);
        return instanciaDocumento;
    }
}