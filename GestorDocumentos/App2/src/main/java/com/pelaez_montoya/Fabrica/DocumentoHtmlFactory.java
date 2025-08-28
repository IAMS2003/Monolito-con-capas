package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.DocumentoHtml;

public class DocumentoHtmlFactory extends DocumentoFactory{
    @Override
    public Documento crearDocumento() {
        return new DocumentoHtml();
    }
}
