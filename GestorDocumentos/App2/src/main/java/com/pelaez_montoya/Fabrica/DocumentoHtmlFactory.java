package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.DocumentoHtml;

public class DocumentoHtmlFactory extends DocumentoFactory{
    private static DocumentoHtml documentoHtml;
    
    @Override
    public Documento crearDocumento() {
        if (documentoHtml == null) {
            documentoHtml = new DocumentoHtml();
        }
        return documentoHtml;
    }
    
    public static DocumentoHtml getDocumentoHtml() {
        if (documentoHtml == null) {
            documentoHtml = new DocumentoHtml();
        }
        return documentoHtml;
    }
}
